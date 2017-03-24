package com.makalaster.todolist;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.makalaster.todolist.Helpers.MainHelpers.OnItemClickListener;
import com.makalaster.todolist.Helpers.MainHelpers.ToDoListRecyclerViewAdapter;
import com.makalaster.todolist.ToDos.ListBook;
import com.makalaster.todolist.ToDos.ToDoList;

public class MainActivity extends AppCompatActivity implements OnItemClickListener {
    private static final int NAME_REQUEST = 20;
    private RecyclerView mListRecycler;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar mainToolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(mainToolbar);
        getSupportActionBar().setTitle(R.string.app_name);

        mListRecycler = (RecyclerView) findViewById(R.id.list_recycler);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mListRecycler.setLayoutManager(mLayoutManager);
        mAdapter = new ToDoListRecyclerViewAdapter(ListBook.getInstance().getLists(), this);
        mListRecycler.setAdapter(mAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_main);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                LayoutInflater inflater = MainActivity.this.getLayoutInflater();

                View editView = inflater.inflate(R.layout.new_list_dialog, null);

                builder.setView(editView);

                final EditText newListName = (EditText) editView.findViewById(R.id.new_list_title);

                builder.setMessage("Enter the list title:").setTitle("Add a new list")
                        .setPositiveButton("Done", null)
                        .setNegativeButton("Cancel", null);

                final AlertDialog dialog = builder.create();
                dialog.show();

                dialog.getButton(AlertDialog.BUTTON_POSITIVE)
                        .setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (newListName.getText().toString().isEmpty()) {
                                    newListName.setError("Please enter a list name");
                                    newListName.requestFocus();
                                } else {
                                    ListBook listBook = ListBook.getInstance();
                                    listBook.addList(new ToDoList(newListName.getText().toString()));
                                    dialog.dismiss();
                                    Intent openList = new Intent(MainActivity.this, ListActivity.class);
                                    openList.putExtra("LIST", listBook.getLists().size() - 1);
                                    startActivity(openList);
                                }
                            }
                        });
            }
        });

        fab.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ListBook listBook = ListBook.getInstance();
                listBook.addList(new ToDoList("QUICKLIST"));
                mAdapter.notifyItemInserted(listBook.getLists().size() - 1);
                return true;
            }
        });
    }

    @Override
    public void onItemClicked(int position) {
        Intent openList = new Intent(MainActivity.this, ListActivity.class);
        openList.putExtra("LIST", position);
        startActivityForResult(openList, 20);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check what request we're responding to...
        if (requestCode == NAME_REQUEST) {
            // Make sure the request was successful...
            if (resultCode == RESULT_OK) {
                if (data.getBooleanExtra("SIZE_CHANGED", false)) {
                    mAdapter.notifyItemChanged(data.getIntExtra("POSITION", 0));
                }
            }
        }
    }
}
