package com.makalaster.todolist;

import android.app.ActionBar;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.makalaster.todolist.Helpers.ListHelpers.ItemRecycleViewAdapter;
import com.makalaster.todolist.Helpers.ListHelpers.OnItemRemoved;
import com.makalaster.todolist.ToDos.ComplexToDoItem;
import com.makalaster.todolist.ToDos.ListBook;
import com.makalaster.todolist.ToDos.SimpleToDoItem;
import com.makalaster.todolist.ToDos.ToDoList;

public class ListActivity extends AppCompatActivity implements OnItemRemoved {
    private ToDoList mOpenedList;
    private RecyclerView mItemRecycler;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    private boolean mComplex, mSizeChanged;
    private int mPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        mComplex = true;
        mSizeChanged = false;

        Intent openedItem = getIntent();
        mPos = openedItem.getIntExtra("LIST", 0);
        mOpenedList = ListBook.getInstance().getLists().get(mPos);

        String title = mOpenedList.getName();
        Toolbar listToolbar = (Toolbar) findViewById(R.id.list_toolbar);
        setSupportActionBar(listToolbar);
        getSupportActionBar().setTitle(title);

        mItemRecycler = (RecyclerView) findViewById(R.id.item_recycler);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mItemRecycler.setLayoutManager(mLayoutManager);
        mAdapter = new ItemRecycleViewAdapter(mOpenedList.getToDoItems(), this);
        mItemRecycler.setAdapter(mAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_todo);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ListActivity.this);

                LayoutInflater inflater = ListActivity.this.getLayoutInflater();
                View newItemView = inflater.inflate(R.layout.new_list_item_dialog, null);

                builder.setView(newItemView);

                final EditText newItemTitle = (EditText) newItemView.findViewById(R.id.new_item_title);

                final EditText newItemBody = (EditText) newItemView.findViewById(R.id.new_item_body);

                builder.setTitle("Create a new list item")
                        .setPositiveButton("Done", null)
                        .setNegativeButton("Cancel", null);
                final AlertDialog dialog = builder.create();
                dialog.show();

                dialog.getButton(AlertDialog.BUTTON_POSITIVE)
                        .setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            if (newItemTitle.getText().toString().isEmpty()) {
                                newItemTitle.setError("Please enter an item title");
                                newItemTitle.requestFocus();
                            } else if (newItemBody.getText().length() == 0){
                                mOpenedList.addItem(new SimpleToDoItem(0, newItemTitle.getText().toString()));
                                dialog.dismiss();
                                mSizeChanged = true;
                            } else {
                                mOpenedList.addItem(new ComplexToDoItem(0, newItemTitle.getText().toString(), newItemBody.getText().toString()));
                                dialog.dismiss();
                                mSizeChanged = true;
                            }
                            }
                        });
            }
        });

        fab.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                if (mComplex) {
                    mOpenedList.addItem(new SimpleToDoItem(0, "QUICKTITLE"));
                    mComplex = !mComplex;
                    mSizeChanged = true;
                    mAdapter.notifyItemInserted(mOpenedList.getToDoItems().size() - 1);
                } else {
                    mOpenedList.addItem(new ComplexToDoItem(0, "QUICKTITLE", "QUICKDESCRIPTION"));
                    mComplex = !mComplex;
                    mSizeChanged = true;
                    mAdapter.notifyItemInserted(mOpenedList.getToDoItems().size() - 1);
                }
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent resultIntent = new Intent();
        resultIntent.putExtra("SIZE_CHANGED", mSizeChanged);
        resultIntent.putExtra("POSITION", mPos);

        setResult(RESULT_OK, resultIntent);
        finish();
    }

    @Override
    public void onItemRemoved() {
        mSizeChanged = true;
    }

}
