package com.makalaster.todolist;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.makalaster.todolist.ToDos.ListBook;
import com.makalaster.todolist.ToDos.ToDoList;

import java.util.List;

public class ListActivity extends AppCompatActivity {
    private ToDoList mOpenedList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Intent openedItem = getIntent();
        int pos = openedItem.getIntExtra("LIST", 0);
        mOpenedList = ListBook.getInstance().getLists().get(pos);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_todo);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ListActivity.this);

                LayoutInflater inflater = ListActivity.this.getLayoutInflater();
                View newItemView = inflater.inflate(R.layout.new_list_item_dialog, null);

                builder.setView(newItemView);

                EditText newItemTitle = (EditText) newItemView.findViewById(R.id.new_item_title);
                newItemTitle.setError("Please enter an item title");
                newItemTitle.requestFocus();
                EditText newItemBody = (EditText) newItemView.findViewById(R.id.new_item_body);

                builder.setTitle("Create a new list item")
                        .setPositiveButton("Done", null)
                        .setNegativeButton("Cancel", null);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }
}
