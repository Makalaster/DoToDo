package com.makalaster.todolist.Helpers.ListHelpers;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.makalaster.todolist.Helpers.MainHelpers.OnItemClickListener;
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
                final ItemHolderSimple simpleHolder = (ItemHolderSimple) holder;
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

                simpleHolder.mListItemSimple.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder simpleBuilder = new AlertDialog.Builder(v.getContext());

                        LayoutInflater inflater = LayoutInflater.from(v.getContext());
                        View simpleEditView = inflater.inflate(R.layout.new_list_dialog, null);

                        simpleBuilder.setView(simpleEditView);

                        final EditText newItemName = (EditText) simpleEditView.findViewById(R.id.new_list_title);
                        newItemName.setText(simpleHolder.mItemTitle.getText());

                        simpleBuilder.setTitle("Edit item")
                                .setMessage("Enter a new item title:")
                                .setPositiveButton("Done", null)
                                .setNegativeButton("Cancel", null);

                        final AlertDialog simpleDialog = simpleBuilder.create();
                        simpleDialog.show();

                        simpleDialog.getButton(AlertDialog.BUTTON_POSITIVE)
                                .setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (newItemName.getText().toString().isEmpty()) {
                                            newItemName.setError("Please enter a new title or press cancel");
                                            newItemName.requestFocus();
                                        } else {
                                            simpleHolder.mItemTitle.setText(newItemName.getText().toString());
                                            simpleItem.setItemTitle(newItemName.getText().toString());
                                            notifyItemChanged(holder.getAdapterPosition());
                                            simpleDialog.dismiss();
                                        }
                                    }
                                });
                    }
                });

                simpleHolder.mListItemSimple.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                        builder.setTitle("Confirm")
                                .setMessage("Please confirm removal of this item")
                                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        mToDoItems.remove(holder.getAdapterPosition());
                                        notifyItemRemoved(holder.getAdapterPosition());
                                        dialog.dismiss();
                                    }
                                })
                                .setNegativeButton("Cancel", null);

                        AlertDialog dialog = builder.create();
                        dialog.show();

                        return true;
                    }
                });

                break;
            case COMPLEX:
                final ItemHolderComplex complexHolder = (ItemHolderComplex) holder;
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

                complexHolder.mListItemComplex.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder complexBuilder = new AlertDialog.Builder(v.getContext());

                        LayoutInflater inflater = LayoutInflater.from(v.getContext());
                        View complexEditView = inflater.inflate(R.layout.edit_list_item_dialog, null);

                        complexBuilder.setView(complexEditView);

                        final EditText newItemName = (EditText) complexEditView.findViewById(R.id.edit_item_title);
                        newItemName.setText(complexHolder.mItemTitle.getText().toString());
                        final EditText newItemBody = (EditText) complexEditView.findViewById(R.id.edit_item_body);
                        newItemBody.setText(complexHolder.mItemDescription.getText().toString());

                        complexBuilder.setTitle("Edit item")
                                .setNegativeButton("Cancel", null)
                                .setPositiveButton("Done", null);

                        final AlertDialog complexDialog = complexBuilder.create();
                        complexDialog.show();

                        complexDialog.getButton(AlertDialog.BUTTON_POSITIVE)
                                .setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (newItemName.getText().toString().isEmpty()) {
                                            newItemName.setError("Please enter a new title or press cancel");
                                            newItemName.requestFocus();
                                        } else if (newItemBody.getText().toString().isEmpty()) {
                                            newItemBody.setError("Please enter a new description or press cancel");
                                            newItemBody.requestFocus();
                                        } else {
                                            complexHolder.mItemTitle.setText(newItemName.getText().toString());
                                            complexItem.setItemTitle(newItemName.getText().toString());
                                            complexHolder.mItemDescription.setText(newItemName.getText().toString());
                                            complexItem.setItemDescription(newItemBody.getText().toString());
                                            notifyItemChanged(complexHolder.getAdapterPosition());
                                            complexDialog.dismiss();
                                        }
                                    }
                                });
                    }
                });

                complexHolder.mListItemComplex.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                        builder.setTitle("Confirm")
                                .setMessage("Please confirm removal of this item")
                                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        mToDoItems.remove(holder.getAdapterPosition());
                                        notifyItemRemoved(holder.getAdapterPosition());
                                        dialog.dismiss();
                                    }
                                })
                                .setNegativeButton("Cancel", null);

                        AlertDialog dialog = builder.create();
                        dialog.show();

                        return true;
                    }
                });

                break;
            default:
                ItemHolderSimple simpleDefaultHolder = (ItemHolderSimple) holder;
                SimpleToDoItem simpleDefaultItem = ((SimpleToDoItem)mToDoItems.get(position));

                simpleDefaultHolder.mCheckBox.setChecked(simpleDefaultItem.isChecked());
                simpleDefaultHolder.mItemTitle.setText(simpleDefaultItem.getItemTitle());
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
