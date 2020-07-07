package com.example.ourworkoutapplication;

import java.util.Date;

public class UserWC {
    private Date date;
    private String workoutType;
    private String workoutDesc;
    private int rep1;
    private int rep2;
    private int rep3;

    public UserWC() {
        // no arg constructor
    }

    public UserWC(Date date, String workoutType, String workoutDesc, int rep1, int rep2, int rep3) {
        this.date = date;
        this.workoutType = workoutType;
        this.workoutDesc = workoutDesc;
        this.rep1 = rep1;
        this.rep2 = rep2;
        this.rep3 = rep3;

    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getWorkoutType() {
        return workoutType;
    }

    public void setWorkoutType(String workoutType) {
        this.workoutType = workoutType;
    }

    public String getWorkoutDesc() { return workoutDesc; }

    public void setWorkoutDesc(String workoutDesc) { this.workoutDesc = workoutDesc; }

    public int getRep1() {
        return rep1;
    }

    public void setRep1(int rep1) {
        this.rep1 = rep1;
    }

    public int getRep2() {
        return rep2;
    }

    public void setRep2(int rep2) {
        this.rep2 = rep2;
    }

    public int getRep3() {
        return rep3;
    }

    public void setRep3(int rep3) {
        this.rep3 = rep3;
    }



}

