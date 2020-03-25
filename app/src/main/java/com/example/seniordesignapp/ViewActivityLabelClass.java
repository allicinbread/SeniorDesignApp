package com.example.seniordesignapp;

public class ViewActivityLabelClass {

    private Boolean Check;
    private Float Confidence;
    private String Date;
    private String Label;
    private String Time;

    public ViewActivityLabelClass() {
    }

    public ViewActivityLabelClass(Boolean check, Float confidence, String date, String label, String time) {
        Check = check;
        Confidence = confidence;
        Date = date;
        Label = label;
        Time = time;
    }

    public Boolean getCheck() {
        return Check;
    }

    public void setCheck(Boolean check) {
        Check = check;
    }

    public Float getConfidence() {
        return Confidence;
    }

    public void setConfidence(Float confidence) {
        Confidence = confidence;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getLabel() {
        return Label;
    }

    public void setLabel(String label) {
        Label = label;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }
}
