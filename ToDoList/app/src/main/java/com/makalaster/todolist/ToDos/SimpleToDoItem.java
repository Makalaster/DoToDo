package com.makalaster.todolist.ToDos;

/**
 * Created by Makalaster on 3/22/17.
 */

public class SimpleToDoItem implements ToDoItem{
    private String mItemTitle;

    public SimpleToDoItem(String itemTitle) {
        mItemTitle = itemTitle;
    }

    public String getItemTitle() {
        return mItemTitle;
    }

    public void setItemTitle(String itemTitle) {
        mItemTitle = itemTitle;
    }
}
