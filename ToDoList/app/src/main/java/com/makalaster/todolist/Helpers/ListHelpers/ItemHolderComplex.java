package com.makalaster.todolist.Helpers.ListHelpers;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.makalaster.todolist.R;

/**
 * Created by Makalaster on 3/22/17.
 */

public class ItemHolderComplex extends RecyclerView.ViewHolder {
    public CheckBox mCheckBox;
    public TextView mItemTitle, mItemDescription;

    public ItemHolderComplex(View itemView) {
        super(itemView);

        mCheckBox = (CheckBox) itemView.findViewById(R.id.complex_checkbox);
        mItemTitle = (TextView) itemView.findViewById(R.id.complex_title);
        mItemDescription = (TextView) itemView.findViewById(R.id.complex_description);
    }
}
