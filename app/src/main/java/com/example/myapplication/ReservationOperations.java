package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

public class ReservationOperations {

    public static final String LOGTAG = "RES_MNGMNT_SYS";

    SQLiteOpenHelper dbhandler;
    SQLiteDatabase database;

    private static final String[] allColumns = {
            ReservationDBHandler.COLUMN_ID,
            ReservationDBHandler.COLUMN_FIRST_NAME,
            ReservationDBHandler.COLUMN_LAST_NAME,
            ReservationDBHandler.COLUMN_ROOM_TYPE,
            ReservationDBHandler.COLUMN_CHECK_IN,
            ReservationDBHandler.COLUMN_CHECK_OUT

    };

    public ReservationOperations(Context context){
        dbhandler = new ReservationDBHandler(context);
    }

    public void open(){
        Log.i(LOGTAG,"Database Opened");
        database = dbhandler.getWritableDatabase();


    }
    public void close(){
        Log.i(LOGTAG, "Database Closed");
        dbhandler.close();

    }
    public Reservation addReservation(Reservation Reservation){
        ContentValues values  = new ContentValues();
        values.put(ReservationDBHandler.COLUMN_FIRST_NAME,Reservation.getFirstname());
        values.put(ReservationDBHandler.COLUMN_LAST_NAME,Reservation.getLastname());
        values.put(ReservationDBHandler.COLUMN_ROOM_TYPE, Reservation.getRoomtype());
        values.put(ReservationDBHandler.COLUMN_CHECK_IN, Reservation.getCheckin());
        values.put(ReservationDBHandler.COLUMN_CHECK_OUT, Reservation.getChheckout());
        long insertid = database.insert(ReservationDBHandler.TABLE_RESERVATIONS,null,values);
        Reservation.setResId(insertid);
        return Reservation;

    }

    // Getting single Reservation
    public Reservation getReservation(long id) {

        Cursor cursor = database.query(ReservationDBHandler.TABLE_RESERVATIONS,allColumns,ReservationDBHandler.COLUMN_ID + "=?",new String[]{String.valueOf(id)},null,null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Reservation r = new Reservation(Long.parseLong(cursor.getString(0)),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5));
        // return Reservation
        return r;
    }

    public List<Reservation> getAllReservations() {

        Cursor cursor = database.query(ReservationDBHandler.TABLE_RESERVATIONS,allColumns,null,null,null, null, null);

        List<Reservation> reservations = new ArrayList<>();
        if(cursor.getCount() > 0){
            while(cursor.moveToNext()){
                Reservation reservation = new Reservation();
                reservation.setResId(cursor.getLong(cursor.getColumnIndex(ReservationDBHandler.COLUMN_ID)));
                reservation.setFirstname(cursor.getString(cursor.getColumnIndex(ReservationDBHandler.COLUMN_FIRST_NAME)));
                reservation.setLastname(cursor.getString(cursor.getColumnIndex(ReservationDBHandler.COLUMN_LAST_NAME)));
                reservation.setRoomtype(cursor.getString(cursor.getColumnIndex(ReservationDBHandler.COLUMN_ROOM_TYPE)));
                reservation.setCheckin(cursor.getString(cursor.getColumnIndex(ReservationDBHandler.COLUMN_CHECK_IN)));
                reservation.setChheckout(cursor.getString(cursor.getColumnIndex(ReservationDBHandler.COLUMN_CHECK_OUT)));
                reservations.add(reservation);
            }
        }
        // return All Reservation
        return reservations;
    }




    // Updating Reservation
    public int updateReservation(Reservation reservation) {

        ContentValues values = new ContentValues();
        values.put(ReservationDBHandler.COLUMN_FIRST_NAME, reservation.getFirstname());
        values.put(ReservationDBHandler.COLUMN_LAST_NAME, reservation.getLastname());
        values.put(ReservationDBHandler.COLUMN_ROOM_TYPE, reservation.getRoomtype());
        values.put(ReservationDBHandler.COLUMN_CHECK_IN, reservation.getCheckin());
        values.put(ReservationDBHandler.COLUMN_CHECK_OUT, reservation.getChheckout());

        // updating row
        return database.update(ReservationDBHandler.TABLE_RESERVATIONS, values,
                ReservationDBHandler.COLUMN_ID + "=?",new String[] { String.valueOf(reservation.getResId())});
    }

    // Deleting Reservation
    public void removeReservation(Reservation reservation) {

        database.delete(ReservationDBHandler.TABLE_RESERVATIONS, ReservationDBHandler.COLUMN_ID + "=" + reservation.getResId(), null);
    }
}
