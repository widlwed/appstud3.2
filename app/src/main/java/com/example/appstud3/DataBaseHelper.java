package com.example.appstud3;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "students.db";
    private static final int DATABASE_VERSION = 2;

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createClassMatesTable(db);
        insertInitialData(db);
    }

    private void createClassMatesTable(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE IF NOT EXISTS classmates ("
                + "ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "LastName TEXT, FirstName TEXT, MiddleName TEXT, "
                + "added_time DATETIME DEFAULT CURRENT_TIMESTAMP"
                + ");");
    }

    private void insertInitialData(SQLiteDatabase db) {

        for (int i = 1; i <= 5; i++) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("LastName", "Фамилия " + i);
            contentValues.put("FirstName", "Имя " + i);
            contentValues.put("MiddleName", "Отчество " + i);
            db.insert("classmates", null, contentValues);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS classmates;");
        createClassMatesTable(db);
        insertInitialData(db);
    }
}

