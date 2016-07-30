package com.wangban.yzbbanban.test_sqlitedata;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by YZBbanban on 16/7/30.
 */

public class DatabaseProvider extends ContentProvider {
    public static final int BOOK_DIR = 0;
    public static final int BOOK_ITEM = 1;
    public static final int CATEGORY_DIR = 2;
    public static final int CATGORY_ITEM = 3;
    private static final String AUTHORITY = "com.wangban.yzbbanban.test_contentprovider";
    private static UriMatcher uriMatcher;
    private MySQLiteHelper dbHelper;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, "table1", BOOK_DIR);
        uriMatcher.addURI(AUTHORITY, "table1/#", BOOK_ITEM);
        uriMatcher.addURI(AUTHORITY, "table2", CATEGORY_DIR);
        uriMatcher.addURI(AUTHORITY, "table2/#", CATGORY_ITEM);
    }

    @Override
    public boolean onCreate() {
        dbHelper = new MySQLiteHelper(getContext(), "BookStore.db", null, 2);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor c = null;
        switch (uriMatcher.match(uri)) {
            case BOOK_DIR:
                c = db.query("book", projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case BOOK_ITEM:
                String bookId = uri.getPathSegments().get(1);
                c = db.query("book", projection, "id=?", new String[]{bookId}, null, null, sortOrder);
                break;
            case CATEGORY_DIR:
                c = db.query("category", projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case CATGORY_ITEM:
                String categoryId = uri.getPathSegments().get(1);
                c = db.query("category", projection, "id=?", new String[]{categoryId}, null, null, sortOrder);
                break;

        }
        return c;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)) {
            case BOOK_DIR:

                break;
            case BOOK_ITEM:

                break;
            case CATEGORY_DIR:

                break;
            case CATGORY_ITEM:

                break;
        }
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        switch (uriMatcher.match(uri)) {
            case BOOK_DIR:
            case BOOK_ITEM:
                db.insert("book", null, contentValues);
            case CATEGORY_DIR:
            case CATGORY_ITEM:
                db.insert("category", null, contentValues);
        }
        return null;
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        return 0;
    }
}
