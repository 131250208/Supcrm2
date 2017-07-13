package com.example.a15850.supcrm;

import android.app.Application;

/**
 * Created by 15850 on 2016/6/17.
 */
public class MyApp extends Application {
    private Staff staff;

    private String staffID="115";

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }
}
