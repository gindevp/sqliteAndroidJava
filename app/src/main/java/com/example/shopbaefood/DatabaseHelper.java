package com.example.shopbaefood;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DatabaseHelper extends SQLiteOpenHelper {
    private final String TEXT="TEXT";
    private final String INTEGER="INTEGER";
    private final String BLOB="BLOB";
    private String tableName;
    private String[][] columns;

    public DatabaseHelper(Context context, String databaseName, int databaseVersion, String tableName, String[][] columns) {
        super(context, databaseName, null, databaseVersion);
        this.tableName = tableName;
        this.columns = columns;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tableCreate = "CREATE TABLE " + tableName + " (";
        tableCreate += "id INTEGER PRIMARY KEY AUTOINCREMENT, ";
        for (String[] column : columns) {
            tableCreate += column[0] + " " + column[1] + ", ";
        }
        tableCreate = tableCreate.substring(0, tableCreate.length() - 2); // Xóa dấu phẩy cuối cùng
        tableCreate += ")";
        Log.d("sql:",tableCreate);
        db.execSQL(tableCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + tableName);
        onCreate(db);
    }
}

