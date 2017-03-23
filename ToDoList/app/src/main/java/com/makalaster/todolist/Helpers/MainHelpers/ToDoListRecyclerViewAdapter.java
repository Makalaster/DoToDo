package com.makalaster.todolist.Helpers.MainHelpers;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.makalaster.todolist.ListActivity;
import com.makalaster.todolist.MainActivity;
import com.makalaster.todolist.R;
import com.makalaster.todolist.ToDos.ToDoList;

import java.util.ArrayList;

/**
 * Created by Makalaster on 3/22/17.
 */

public class ToDoListRecyclerViewAdapter extends RecyclerView.Adapter<ToDoListHolder> {
    private ArrayList<ToDoList> mToDoLists;

    public ToDoListRecyclerViewAdapter(ArrayList<ToDoList> toDoLists) {
        mToDoLists = toDoLists;
    }

    @Override
    public ToDoListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View parentView = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_list_item, parent, false);
        return new ToDoListHolder(parentView);
    }

    @Override
    public void onBindViewHolder(final ToDoListHolder holder, int position) {
        holder.mListName.setText(mToDoLists.get(position).getName());
        holder.mItemCount.setText(mToDoLists.get(position).getToDoItems().size() + " Items");

        holder.mListItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openList = new Intent(v.getContext(), ListActivity.class);
                openList.putExtra("LIST", holder.getAdapterPosition());
                v.getContext().startActivity(openList);
            }
        });

        holder.mListItem.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Confirm")
                        .setMessage("Please confirm removal of this item")
                        .setPositiveButton("Confirm", null)
                        .setNegativeButton("Cancel", null);

                final AlertDialog dialog = builder.create();
                dialog.show();

                builder.setPositiveButton("Confim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mToDoLists.remove(holder.getAdapterPosition());
                        notifyItemRemoved(holder.getAdapterPosition());
                        dialog.dismiss();
                    }
                });

                return true;
            }
        });


    }

    @Override
    public int getItemCount() {
        return mToDoLists.size();
    }
}
