package com.example.appstud3;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    DataBaseHelper dbHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DataBaseHelper(this);
        db = dbHelper.getWritableDatabase();

        Button btnShow = findViewById(R.id.button2);
        Button btnAdd = findViewById(R.id.button);
        Button btnUpdate = findViewById(R.id.button3);

        btnShow.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            startActivity(intent);
        });

        btnAdd.setOnClickListener(v -> {
            ContentValues contentValues = new ContentValues();
            contentValues.put("LastName", "Новый");
            contentValues.put("FirstName", "Одногруппник");
            contentValues.put("MiddleName", "Панфилов");
            db.insert("classmates", null, contentValues);
        });

        btnUpdate.setOnClickListener(v -> {
            updateLastRecord();
        });
    }

    private void updateLastRecord() {
        Cursor cursor = db.rawQuery("SELECT * FROM classmates ORDER BY ID DESC LIMIT 1", null);
        if (cursor != null && cursor.moveToFirst()) {
            @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("ID"));
            ContentValues contentValues = new ContentValues();
            contentValues.put("LastName", "Иванов");
            contentValues.put("FirstName", "Иван");
            contentValues.put("MiddleName", "Иванович");
            db.update("classmates", contentValues, "ID = ?", new String[]{String.valueOf(id)});
        }
        cursor.close();
    }
}