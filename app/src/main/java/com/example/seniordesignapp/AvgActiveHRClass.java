package com.example.seniordesignapp;

public class AvgActiveHRClass {

        private String Date;
        private String Day;
        private Integer HR;

    public AvgActiveHRClass() {
    }

    public AvgActiveHRClass(String date, String day, Integer HR) {
        Date = date;
        Day = day;
        this.HR = HR;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getDay() {
        return Day;
    }

    public void setDay(String day) {
        Day = day;
    }

    public Integer getHR() {
        return HR;
    }

    public void setHR(Integer HR) {
        this.HR = HR;
    }
}
