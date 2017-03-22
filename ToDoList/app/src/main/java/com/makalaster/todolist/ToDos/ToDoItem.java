package com.makalaster.todolist.ToDos;

/**
 * Created by Makalaster on 3/22/17.
 */

public class ToDoItem {
    private String mItemTitle, mItemBody;

    public ToDoItem(String itemTitle) {
        mItemTitle = itemTitle;
    }

    public ToDoItem(String itemTitle, String itemBody) {
        mItemTitle = itemTitle;
        mItemBody = itemBody;
    }

    public String getItemTitle() {
        return mItemTitle;
    }

    public void setItemTitle(String itemTitle) {
        mItemTitle = itemTitle;
    }

    public String getItemBody() {
        return mItemBody;
    }

    public void setItemBody(String itemBody) {
        mItemBody = itemBody;
    }
}
