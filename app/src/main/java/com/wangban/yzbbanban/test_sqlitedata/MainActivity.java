package com.wangban.yzbbanban.test_sqlitedata;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private MySQLiteHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new MySQLiteHelper(this, "BookStore.db", null, 3);
        dbHelper.getWritableDatabase();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues valus = new ContentValues();
        valus.put("name", "god");
        valus.put("price", 100.2);
        valus.put("author", "ban");
        db.insert("book", null, valus);
        valus.clear();
        valus.put("name", "devil");
        valus.put("price", 99.22);
        valus.put("author", "yzb");
        db.insert("book", null, valus);

    }

    public void deleteData(View view) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
//        db.delete("book", "name=?", new String[]{"god"});
//        ContentValues values=new ContentValues();
//        values.put("price",300.5);
//        db.update("book",values,"name=?",new String[]{"devil"});

        Cursor c = db.query("book", null, null, null, null, null, null);
        if (c.moveToFirst()) {
            do {
                String name = c.getString(c.getColumnIndex("name"));
                String author = c.getString(c.getColumnIndex("author"));
                double price = c.getDouble(c.getColumnIndex("price"));
                Log.i("supergirl", "name: " + name + "\nauthor: " + author + "\nprice: " + price);
            } while (c.moveToNext());

        }

        db.close();
    }

}
