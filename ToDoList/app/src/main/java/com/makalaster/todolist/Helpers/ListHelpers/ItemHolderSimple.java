package com.makalaster.todolist.Helpers.ListHelpers;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.makalaster.todolist.R;

/**
 * Created by Makalaster on 3/22/17.
 */

public class ItemHolderSimple extends RecyclerView.ViewHolder {
    public CheckBox mCheckBox;
    public TextView mItemTitle;
    public View mListItemSimple;

    public ItemHolderSimple(View itemView) {
        super(itemView);

        mCheckBox = (CheckBox) itemView.findViewById(R.id.simple_checkbox);
        mItemTitle = (TextView) itemView.findViewById(R.id.simple_item_title);
        mListItemSimple = itemView.findViewById(R.id.list_item_simple);
    }
}
