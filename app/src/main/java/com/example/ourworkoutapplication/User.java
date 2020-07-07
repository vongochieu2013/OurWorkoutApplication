package com.example.ourworkoutapplication;

public class User implements PotentialUsers {

  private String email;
  private String fullName;
  private String password;
  private int age;
  private int weight;
  private int calories;


  public User() {

  }

  public User(String email, String fullName, String password, int age, int weight, int calories) {
    this.email = email;
    this.fullName = fullName;
    this.password = password;
    this.age = age;
    this.weight = weight;
    this.calories = calories;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getfullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public int getCalories() {
    return calories;
  }

  public void setCalories(int calories) {
    this.calories = calories;
  }

  public int getWeight() {
    return weight;
  }

  public void setWeight(int weight) {
    this.weight = weight;
  }
}
