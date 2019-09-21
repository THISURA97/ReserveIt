package com.example.reservrit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "Reservation.db";
    public static final String TABLE_NAME = "reservation_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "FIRST_NAME";
    public static final String COL_3 = "LAST_NAME";
    public static final String COL_4 = "MOBILE";
    public static final String COL_5 = "CHECK_IN";
    public static final String COL_6 = "CHECK_OUT";


    public DatabaseHelper(Context context) {

        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "FIRST_NAME TEXT," +
                "LAST_NAME TEXT," +
                "MOBILE TEXT," +
                "CHECK_IN TEXT," +
                "CHECK_OUT TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String firstname,String lastname,String mobile,String checkin,String checkout) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_2,firstname);
        contentValues.put(COL_3,lastname);
        contentValues.put(COL_4,mobile);
        contentValues.put(COL_5,checkin);
        contentValues.put(COL_6,checkout);
        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }



    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }


    public boolean updateData(String id, String firstname, String lastname, String mobile,String checkin,String checkout) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,firstname);
        contentValues.put(COL_3,lastname);
        contentValues.put(COL_4,mobile);
        contentValues.put(COL_5,checkin);
        contentValues.put(COL_6,checkout);

        db.update(TABLE_NAME, contentValues, "ID = ?",new String[] { id });
        return true;
    }

    public Integer deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?",new String[] {id});
    }

}
























