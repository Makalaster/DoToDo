package com.makalaster.todolist.ToDos;

import android.graphics.Color;

/**
 * Created by Makalaster on 3/22/17.
 */

public class SimpleToDoItem implements ToDoItem{
    private int mId;
    private String mItemTitle;
    private boolean mIsChecked;

    public SimpleToDoItem(int id, String itemTitle) {
        mItemTitle = itemTitle;
        mId = id;
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

    public int getId() {
        return mId;
    }
}
