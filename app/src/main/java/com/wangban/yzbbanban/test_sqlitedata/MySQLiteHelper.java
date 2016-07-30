package com.wangban.yzbbanban.test_sqlitedata;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by YZBbanban on 16/7/27.
 */

public class MySQLiteHelper extends SQLiteOpenHelper {
    private Context context;

    public MySQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table book(id integer primary key autoincrement,name text,price real,author text)");
        sqLiteDatabase.execSQL("create table category(id integer primary key autoincrement,category_name text,category_code integer)");
//        Toast.makeText(context, "创建成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists book");
        sqLiteDatabase.execSQL("drop table if exists category");
        onCreate(sqLiteDatabase);
    }
}
