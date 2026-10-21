package com.example.yashi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MoodDatabase extends SQLiteOpenHelper {

    private static final String DB_NAME =
            "MoodJournal.db";
    private static final int DB_VERSION = 1;

    public MoodDatabase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE moods (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "mood TEXT NOT NULL, " +
                        "reason TEXT, " +
                        "entry_date TEXT NOT NULL)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,
                          int oldVersion, int newVersion) {
        // Preserve existing entries during this version.
    }

    public long saveMood(String mood, String reason, String date) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("mood", mood);
        values.put("reason", reason);
        values.put("entry_date", date);

        return db.insert("moods", null, values);
    }

    public Cursor getAllMoods() {
        return getReadableDatabase().query(
                "moods",
                new String[]{"id", "mood", "reason", "entry_date"},
                null, null, null, null,
                "id DESC"
        );
    }
}
