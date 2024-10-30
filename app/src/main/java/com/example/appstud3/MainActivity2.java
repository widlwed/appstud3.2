package com.example.appstud3;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    DataBaseHelper dbHelper;
    SQLiteDatabase db;
    ListView listView;
    ArrayAdapter<String> adapter;
    ArrayList<String> studentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        dbHelper = new DataBaseHelper(this);
        db = dbHelper.getReadableDatabase();
        listView = findViewById(R.id.listView);

        studentList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, studentList);
        listView.setAdapter(adapter);
        loadRecords();
    }

    private void loadRecords() {
        Cursor cursor = db.rawQuery("SELECT * FROM classmates", null);
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String lastName = cursor.getString(cursor.getColumnIndex("LastName"));
                @SuppressLint("Range") String firstName = cursor.getString(cursor.getColumnIndex("FirstName"));
                @SuppressLint("Range") String middleName = cursor.getString(cursor.getColumnIndex("MiddleName"));
                @SuppressLint("Range") String addedTime = cursor.getString(cursor.getColumnIndex("added_time"));
                studentList.add(lastName + " " + firstName + " " + middleName + " - " + addedTime);
            } while (cursor.moveToNext());
        }
        cursor.close();
        adapter.notifyDataSetChanged();
    }
}