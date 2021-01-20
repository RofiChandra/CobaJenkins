package com.example.movierating;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

public class MovieRatingDataHelper {
    private static final String DATABASE_NAME = "MovieRating.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "Review";
    private Context context;
    private SQLiteDatabase db;
    private SQLiteStatement insertStmt;
    private static final String INSERT = "insert into " + TABLE_NAME + "(name, genre, year, duration, rating, review, starcast, director) values (?,?,?,?,?,?,?,?)";

    public MovieRatingDataHelper(Context context) {
        this.context = context;
        OpenHelper openHelper = new OpenHelper(this.context);
        this.db = openHelper.getWritableDatabase();
        this.insertStmt = this.db.compileStatement(INSERT);
    }

    public long insert(String name, String genre, String year, String duration, double rating, String review, String starcast, String director) {
        this.insertStmt.bindString(1, name);
        this.insertStmt.bindString(2, genre);
        this.insertStmt.bindString(3, year);
        this.insertStmt.bindString(4, duration);
        this.insertStmt.bindDouble(5, rating);
        this.insertStmt.bindString(6, review);
        this.insertStmt.bindString(7, starcast);
        this.insertStmt.bindString(8, director);
        return this.insertStmt.executeInsert();
    }

    public List<String> selectById(int id) {
        List<String> list = new ArrayList<String>();
        Cursor cursor = this.db.query(TABLE_NAME, new String[] {"name", "genre", "year", "duration", "rating", "review", "starcast","director"}, "id="+id, null, null, null, null);
        if (cursor.moveToFirst()) { list.add(cursor.getString(0)+";"+cursor.getString(1)+";"+cursor.getString(2) +";"+cursor.getString(3)+";"+cursor.getDouble(4)+";"+cursor.getString(5)+";" +cursor.getString(6)+";"+cursor.getString(7));
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return list;
    }

    public List<String> selectName() {
        List<String> list = new ArrayList<String>();
        Cursor cursor = this.db.query(TABLE_NAME, new String[]{"name"}, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                list.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return list;
    }

    private static class OpenHelper extends SQLiteOpenHelper {
        OpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE " + TABLE_NAME + "(id INTEGER PRIMARY KEY, name TEXT, genre TEXT, year TEXT, duration TEXT, rating DOUBLE, review TEXT, starcast TEXT, director TEXT)");
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int
                newVersion) {
            Log.w("Example", "Upgrading database, this will drop tables and recreate.");
                    db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }
}
