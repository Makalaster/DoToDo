package com.makalaster.todolist.Helpers.MainHelpers;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
    public void onBindViewHolder(ToDoListHolder holder, int position) {
        holder.mListName.setText(mToDoLists.get(position).getName());
        holder.mItemCount.setText(mToDoLists.get(position).getToDoItems().size() + " Items");
        //notifyItemChanged(position);
    }

    @Override
    public int getItemCount() {
        return mToDoLists.size();
    }
}
