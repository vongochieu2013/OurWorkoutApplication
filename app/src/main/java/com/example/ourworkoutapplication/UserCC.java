package com.example.ourworkoutapplication;

import java.util.Date;

public class UserCC {
  private Date date;
  private String mealDescriptions;
  private int calories;

  public UserCC() {
    // no arg constructor
  }

  public UserCC(Date date, String mealDescriptions, int calories) {
    this.date = date;
    this.mealDescriptions = mealDescriptions;
    this.calories = calories;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public String getMealDescriptions() {
    return mealDescriptions;
  }

  public void setMealDescriptions(String mealDescriptions) {
    this.mealDescriptions = mealDescriptions;
  }

  public int getCalories() {
    return calories;
  }

  public void setCalories(int calories) {
    this.calories = calories;
  }


}
