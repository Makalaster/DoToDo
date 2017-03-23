package com.makalaster.todolist.ToDos;

import android.graphics.Color;

/**
 * Created by Makalaster on 3/22/17.
 */

public class SimpleToDoItem implements ToDoItem{
    private String mItemTitle;
    private boolean mIsChecked;

    public SimpleToDoItem(String itemTitle) {
        mItemTitle = itemTitle;
    }

    public String getItemTitle() {
        return mItemTitle;
    }

    public void setItemTitle(String itemTitle) {
        mItemTitle = itemTitle;
    }

    public boolean isChecked() {
        return mIsChecked;
    }

    public void setChecked(boolean checked) {
        mIsChecked = checked;
    }
}
