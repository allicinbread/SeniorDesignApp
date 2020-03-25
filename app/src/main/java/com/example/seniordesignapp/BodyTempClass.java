package com.example.seniordesignapp;

public class BodyTempClass {

    private Float BT;
    private String Date;
    private String Time;

    public BodyTempClass() {
    }

    public BodyTempClass(Float BT, String date, String time) {
        this.BT = BT;
        Date = date;
        Time = time;
    }

    public Float getBT() {
        return BT;
    }

    public void setBT(Float BT) {
        this.BT = BT;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }
}
