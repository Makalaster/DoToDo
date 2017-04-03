package com.makalaster.todolist.ToDos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Makalaster on 3/22/17.
 */

public class ListBook extends SQLiteOpenHelper {
    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "listbook.db";

    private static final String LIST_TABLE_NAME = "list_table";
    private static final String COL_LIST_ID = "id";
    private static final String COL_LIST_NAME = "name";
    private static final String CREATE_LIST_TABLE = "CREATE TABLE " + LIST_TABLE_NAME + " (" +
            COL_LIST_ID + " INTEGER PRIMARY KEY," +
            COL_LIST_NAME + " TEXT)";

    private static final String ITEM_TABLE_NAME = "item_table";
    private static final String COL_ITEM_ID = "id";
    private static final String COL_PARENT_LIST_ID = "parent_list_id";
    private static final String COL_ITEM_TITLE = "item_title";
    private static final String COL_ITEM_DESCRIPTION = "item_description";
    private static final String CREATE_ITEM_TABLE = "CREATE TABLE " + ITEM_TABLE_NAME + " (" +
            COL_ITEM_ID + " INTEGER PRIMARY KEY," +
            COL_PARENT_LIST_ID + " INTEGER FOREIGN KEY," +
            COL_ITEM_TITLE + " TEXT," +
            COL_ITEM_DESCRIPTION + " TEXT)";

    private static ListBook sInstance;
    private ArrayList<ToDoList> mToDoLists;

    public ListBook(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        mToDoLists = new ArrayList<>();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_ITEM_TABLE);
        db.execSQL(CREATE_LIST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ITEM_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + LIST_TABLE_NAME);
        onCreate(db);
    }

    public static ListBook getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new ListBook(context.getApplicationContext());
        }
        return sInstance;
    }

    public void addList(ToDoList list) {
        mToDoLists.add(list);
    }

    public ArrayList<ToDoList> getLists() {
        return mToDoLists;
    }

    public void addItemToList() {

    }

    public void removeItemFromList() {

    }

}
