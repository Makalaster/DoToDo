package com.makalaster.todolist.ToDos;

import android.graphics.Color;

/**
 * Created by Makalaster on 3/22/17.
 */

public class ComplexToDoItem implements ToDoItem {
    private String mItemTitle, mItemDescription;
    private boolean mIsChecked;

    public ComplexToDoItem(String itemTitle, String itemDescription) {
        mItemTitle = itemTitle;
        mItemDescription = itemDescription;
    }

    public String getItemTitle() {
        return mItemTitle;
    }

    public void setItemTitle(String itemTitle) {
        mItemTitle = itemTitle;
    }

    public String getItemDescription() {
        return mItemDescription;
    }

    public void setItemDescription(String itemDescription) {
        mItemDescription = itemDescription;
    }

    public boolean isChecked() {
        return mIsChecked;
    }

    public void setChecked(boolean checked) {
        mIsChecked = checked;
    }

}
