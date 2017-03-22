package com.makalaster.todolist.ToDos;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Makalaster on 3/22/17.
 */

public class ListBook {
    private static ListBook sInstance;
    private ArrayList<ToDoList> mToDoLists;

    private ListBook() {
        mToDoLists = new ArrayList<>();
    }

    public static ListBook getInstance() {
        if (sInstance == null) {
            sInstance = new ListBook();
        }
        return sInstance;
    }

    public void addList(ToDoList list) {
        mToDoLists.add(list);
    }

    public ArrayList<ToDoList> getLists() {
        return mToDoLists;
    }
}
