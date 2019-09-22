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

    public static final String TABLE_NAME2 = "TRAVEL_TABLE";
    public static final String COL_a = "ID";
    public static final String COL_b = "DATE";
    public static final String COL_c = "TYPE";
    public static final String COL_d = "PICKUP";
    public static final String COL_e = "DESTINATION";
    public static final String COL_f = "CONTACT";
    public static final String COL_g = "PASENGERS";
    public static final String COL_h = "VEHICLES";

    private static final String TABLE_NAME3 = "account_table";
    private static final String col_aa = "id";
    private static final String col_bb= "fullname";
    private static final String col_cc= "username";
    private static final String col_dd= "email";
    private static final String col_ee= "password";


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

        db.execSQL("create table " + TABLE_NAME3 + "(id INTEGER PRIMARY KEY AUTOINCREMENT, fullname TEXT, username TEXT, email TEXT, password TEXT)");


        db.execSQL("create table " + TABLE_NAME2 + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,TYPE TEXT,PICKUP TEXT,DESTINATION TEXT,CONTACT INTEGER,PASENGERS INTEGER,VEHICLES INTEGER)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2);
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME3);
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
    public long create(String fullname, String username, String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_bb, fullname);
        contentValues.put(col_cc, username);
        contentValues.put(col_dd, email);
        contentValues.put(col_ee, password);
        long result = db.insert(TABLE_NAME3, null, contentValues);
        return result;
    }

    public boolean insertData1(String date, String type, String pickup, String destination, int contact, int pasengers, int vehicles) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_b,date);
        contentValues.put(COL_c,type);
        contentValues.put(COL_d,pickup);
        contentValues.put(COL_e,destination);
        contentValues.put(COL_f,contact);
        contentValues.put(COL_g,pasengers);
        contentValues.put(COL_h,vehicles);
        long result = db.insert(TABLE_NAME2,null,contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }


    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }
    public Cursor getAllData1(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME2,null);
        return res;
    }
    public Cursor getAllData3(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("Select * from " + TABLE_NAME3, null );
        return res;
    }

    public boolean checkUser(String username, String password){
        String[] columns = { col_aa };
        SQLiteDatabase db = getReadableDatabase();
        String selection = col_cc + "=?" + " and " + col_ee + "=?";
        String[] selectionArgs = { username, password };
        Cursor cursor = db.query(TABLE_NAME3, columns,selection,selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        if(count > 0)
            return true;
        else
            return false;
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
    public boolean updateData1(int Id,String date,String type,String pickup,String destination,int contact,int pasengers,int vehicles){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_a,Id);
        contentValues.put(COL_b,date);
        contentValues.put(COL_c,type);
        contentValues.put(COL_d,pickup);
        contentValues.put(COL_e,destination);
        contentValues.put(COL_f,contact);
        contentValues.put(COL_g,pasengers);
        contentValues.put(COL_h,vehicles);
        db.update(TABLE_NAME2,contentValues, "ID = ?",new String[] {String.valueOf(Id)});
        return true;
    }
    public Integer deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?",new String[] {id});

    }

        public Integer deleteData1(int Id) {
            SQLiteDatabase db = this.getWritableDatabase();
            return db.delete(TABLE_NAME2, "ID = ?", new String[]{String.valueOf(Id)});

        }


}
























