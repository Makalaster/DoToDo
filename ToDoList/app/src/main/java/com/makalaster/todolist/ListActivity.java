package com.makalaster.todolist;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.makalaster.todolist.Helpers.ListHelpers.ItemRecycleViewAdapter;
import com.makalaster.todolist.ToDos.ComplexToDoItem;
import com.makalaster.todolist.ToDos.ListBook;
import com.makalaster.todolist.ToDos.SimpleToDoItem;
import com.makalaster.todolist.ToDos.ToDoList;

public class ListActivity extends AppCompatActivity {
    private ToDoList mOpenedList;
    private RecyclerView mItemRecycler;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Intent openedItem = getIntent();
        int pos = openedItem.getIntExtra("LIST", 0);
        mOpenedList = ListBook.getInstance().getLists().get(pos);

        mItemRecycler = (RecyclerView) findViewById(R.id.item_recycler);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mItemRecycler.setLayoutManager(mLayoutManager);
        mAdapter = new ItemRecycleViewAdapter(mOpenedList.getToDoItems());
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
                newItemTitle.setError("Please enter an item title");
                newItemTitle.requestFocus();
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
                                if (newItemBody.getText().length() == 0) {
                                    mOpenedList.addItem(new SimpleToDoItem(newItemTitle.getText().toString()));
                                } else {
                                    mOpenedList.addItem(new ComplexToDoItem(newItemTitle.getText().toString(), newItemBody.getText().toString()));
                                }
                                dialog.dismiss();
                            }
                        });
            }
        });
    }
}
