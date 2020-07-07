package com.example.ourworkoutapplication;

import java.util.Date;

public class UserHistory {
  private Date date;
  private String time;
  private String workoutType;
  private double distance;

  public UserHistory() {
    // no arg constructor
  }

  public UserHistory(Date date, String time, String workoutType, double distance) {
    this.date = date;
    this.time = time;
    this.workoutType = workoutType;
    this.distance = distance;
  }

  public Date getDate() {
    return date;
  }

  public String getTime() {
    return time;
  }

  public String getWorkoutType() {
    return workoutType;
  }

  public double getDistance() {
    return distance;
  }
}
