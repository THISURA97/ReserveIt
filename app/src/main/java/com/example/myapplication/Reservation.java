package com.example.myapplication;

public class Reservation {
    private long resId;
    private String firstname;
    private String lastname;
    private String roomtype;
    private String checkin;
    private String chheckout;

    public Reservation(long resId, String firstname, String lastname, String roomtype, String checkin, String chheckout) {
        this.resId = resId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.roomtype = roomtype;
        this.checkin = checkin;
        this.chheckout = chheckout;
    }

    public Reservation() {

    }

    public long getResId() {
        return resId;
    }

    public void setResId(long resId) {
        this.resId = resId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getRoomtype() {
        return roomtype;
    }

    public void setRoomtype(String roomtype) {
        this.roomtype = roomtype;
    }

    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getChheckout() {
        return chheckout;
    }

    public void setChheckout(String chheckout) {
        this.chheckout = chheckout;
    }
    public String toString(){
        return "Emp id: "+getResId()+ "\n" +
                "Name: "+getFirstname() + " " + getLastname() + "\n" +
                "Room Type: "+getRoomtype() + "\n" +
                "From: "+getCheckin() + "\n" +
                "To : "+getChheckout();


    }
}
