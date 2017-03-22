package com.makalaster.todolist.Helpers.MainHelpers;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.makalaster.todolist.R;

/**
 * Created by Makalaster on 3/22/17.
 */

public class ToDoListHolder extends RecyclerView.ViewHolder {
    public TextView mListName, mItemCount;


    public ToDoListHolder(View itemView) {
        super(itemView);

        mListName = (TextView) itemView.findViewById(R.id.list_name);
        mItemCount = (TextView) itemView.findViewById(R.id.list_item_count);
    }
}
