package com.makalaster.todolist;

import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.makalaster.todolist.Helpers.MainHelpers.ToDoListRecyclerViewAdapter;
import com.makalaster.todolist.ToDos.ListBook;
import com.makalaster.todolist.ToDos.ToDoList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RecyclerView listRecylcer = (RecyclerView) findViewById(R.id.list_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        listRecylcer.setLayoutManager(layoutManager);
        ToDoListRecyclerViewAdapter adapter = new ToDoListRecyclerViewAdapter(ListBook.getInstance().getLists());
        listRecylcer.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                LayoutInflater inflater = MainActivity.this.getLayoutInflater();

                View editView = inflater.inflate(R.layout.new_list_dialog, null);

                builder.setView(editView);

                final EditText newListName = (EditText) editView.findViewById(R.id.new_list_title);
                newListName.setError("Please enter a list name");

                builder.setMessage("Enter the list title:").setTitle("Add a new list")
                        .setPositiveButton("Done", null)
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast toast = Toast.makeText(MainActivity.this, "You chose Cancel!", Toast.LENGTH_SHORT);
                                toast.show();
                            }
                        });
                builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ListBook listBook = ListBook.getInstance();
                        listBook.addList(new ToDoList(newListName.getText().toString()));
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }
}
