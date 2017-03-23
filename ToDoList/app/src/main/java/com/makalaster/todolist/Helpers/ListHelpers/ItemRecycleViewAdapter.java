package com.makalaster.todolist.Helpers.ListHelpers;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.makalaster.todolist.Helpers.MainHelpers.ToDoListHolder;
import com.makalaster.todolist.R;
import com.makalaster.todolist.ToDos.ComplexToDoItem;
import com.makalaster.todolist.ToDos.SimpleToDoItem;
import com.makalaster.todolist.ToDos.ToDoItem;

import java.util.ArrayList;

/**
 * Created by Makalaster on 3/22/17.
 * Multiple views adapted from https://guides.codepath.com/android/Heterogenous-Layouts-inside-RecyclerView
 */

public class ItemRecycleViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<ToDoItem> mToDoItems;
    private final int SIMPLE = 0, COMPLEX = 1;

    public ItemRecycleViewAdapter(ArrayList<ToDoItem> toDoItems) {
        mToDoItems = toDoItems;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View parentView;
        switch (viewType) {
            case SIMPLE:
                parentView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_simple, parent, false);
                return new ItemHolderSimple(parentView);
            case COMPLEX:
                parentView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_complex, parent, false);
                return new ItemHolderComplex(parentView);
            default:
                parentView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_simple, parent, false);
                return new ItemHolderSimple(parentView);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case SIMPLE:
                ItemHolderSimple simpleHolder = (ItemHolderSimple) holder;
                final SimpleToDoItem simpleItem = ((SimpleToDoItem)mToDoItems.get(position));

                simpleHolder.mCheckBox.setChecked(simpleItem.isChecked());
                simpleHolder.mItemTitle.setText(simpleItem.getItemTitle());

                simpleHolder.mCheckBox.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isChecked = simpleItem.isChecked();
                        simpleItem.setChecked(!isChecked);
                        notifyItemChanged(holder.getAdapterPosition());
                    }
                });
                break;
            case COMPLEX:
                ItemHolderComplex complexHolder = (ItemHolderComplex) holder;
                final ComplexToDoItem complexItem = ((ComplexToDoItem)mToDoItems.get(position));

                complexHolder.mCheckBox.setChecked(complexItem.isChecked());
                complexHolder.mItemTitle.setText(complexItem.getItemTitle());
                complexHolder.mItemDescription.setText(complexItem.getItemDescription());

                complexHolder.mCheckBox.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isChecked = complexItem.isChecked();
                        complexItem.setChecked(!isChecked);
                        notifyItemChanged(holder.getAdapterPosition());
                    }
                });
                break;
            default:
                simpleHolder = (ItemHolderSimple) holder;
                SimpleToDoItem simpleDefaultItem = ((SimpleToDoItem)mToDoItems.get(position));

                simpleHolder.mCheckBox.setChecked(simpleDefaultItem.isChecked());
                simpleHolder.mItemTitle.setText(simpleDefaultItem.getItemTitle());
        }


    }

    @Override
    public int getItemCount() {
        return mToDoItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (mToDoItems.get(position) instanceof SimpleToDoItem) {
            return SIMPLE;
        } else {
            return COMPLEX;
        }
    }
}
