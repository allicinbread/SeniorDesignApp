package com.example.seniordesignapp;

public class HRByActivity {

        private String Activity;
        private String Date;
        private Integer HR;

    public HRByActivity() {
    }

    public HRByActivity(String activity, String date, Integer HR) {
        Activity = activity;
        Date = date;
        this.HR = HR;
    }

    public String getActivity() {
        return Activity;
    }

    public void setActivity(String activity) {
        Activity = activity;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public Integer getHR() {
        return HR;
    }

    public void setHR(Integer HR) {
        this.HR = HR;
    }
}
