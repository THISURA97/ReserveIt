package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class ReservationDBHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "reservations.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_RESERVATIONS = "reservations";
    public static final String COLUMN_ID = "resId";
    public static final String COLUMN_FIRST_NAME = "firstname";
    public static final String COLUMN_LAST_NAME = "lastname";
    public static final String COLUMN_ROOM_TYPE = "roomtype";
    public static final String COLUMN_CHECK_IN= "checkin";
    public static final String COLUMN_CHECK_OUT= "checkout";


    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_RESERVATIONS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_FIRST_NAME + " TEXT, " +
                    COLUMN_LAST_NAME + " TEXT, " +
                    COLUMN_ROOM_TYPE + " TEXT, " +
                    COLUMN_CHECK_IN + " NUMERIC, " +
                    COLUMN_CHECK_OUT + " NUMERIC " +
                    ")";



    public ReservationDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_RESERVATIONS);
        db.execSQL(TABLE_CREATE);
    }
}
