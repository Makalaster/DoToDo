package com.makalaster.todolist.ToDos;

/**
 * Created by Makalaster on 3/22/17.
 */

public class ComplexToDoItem implements ToDoItem {
    private String mItemTitle, mItemDescription;

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
}
