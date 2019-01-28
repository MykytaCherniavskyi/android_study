package com.example.mikit.lab5;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    /*
    * В данном классе принято создавать открытые строковые константы для работы с БД
    * */
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "person";
    public static final String TABLE_CONTACTS = "user";

    public static final String KEY_PERSON_ID = "_person_id";
    public static final String KEY_FIRST_NAME = "_first_name";
    public static final String KEY_SECOND_NAME = "_second_name";
    public static final String KEY_EMAIL = "_email";
    public static final String KEY_HOME_ADDRESS = "_home_address";

    /*
    * Принимает контекст активити в которой должна быть создана
    * */
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_CONTACTS + " ( " +
                KEY_PERSON_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                KEY_FIRST_NAME + " TEXT NOT NULL, " +
                KEY_SECOND_NAME + " TEXT NOT NULL," +
                KEY_EMAIL + " TEXT NOT NULL, " +
                KEY_HOME_ADDRESS + " TEXT" +
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_CONTACTS);

        onCreate(db);
    }
}

