package com.makalaster.todolist.ToDos;

import java.util.ArrayList;

/**
 * Created by Makalaster on 3/22/17.
 */

public class ToDoList {
    private int mId;
    private String mName;
    private ArrayList<ToDoItem> mToDoItems;

    public ToDoList(String name) {
        mName = name;
        mToDoItems = new ArrayList<>();
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public void addItem(ToDoItem item) {
        mToDoItems.add(item);
    }

    public ArrayList<ToDoItem> getToDoItems() {
        return mToDoItems;
    }
}
