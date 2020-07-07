package com.example.ourworkoutapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {
  private static final Pattern PASSWORD_PATTERN =
    Pattern.compile("^" +
      //"(?=.*[0-9])" +       //at least 1 digit
      //"(?=.*[a-z])" +       //at least 1 lower case letter
      //"(?=.*[A-Z])" +       //at least 1 upper case letter
      "(?=.*[a-zA-Z])" +      //any letter
      //"(?=.*[@#$%^&+=])" +  //at least 1 special character
      "(?=\\S+$)" +           //no white spaces
      ".{4,}" +               //at least 6 characters
      "$");
  private TextInputLayout emailTextInput;
  private TextInputLayout fullNameTextInput;
  private TextInputLayout passwordTextInput;
  private TextInputLayout ageTextInput;
  private TextInputLayout caloriesTextInput;
  private TextInputLayout weightTextInput;
  private Button confirmButton;
  public static final String TAG = "TAG";

  private FirebaseFirestore db = FirebaseFirestore.getInstance();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_signup);
    setUp();
    confirmButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (!validateEmail()  | !validateName() | !validatePassword() | !validateAge() | !validateWeight() | !validateCalories()) {
          return;
        }
        Toast.makeText(SignUpActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
        User newUser = setUpUser();
        CollectionReference c = db.collection("users");
        c.document(newUser.getEmail()).set(newUser);
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
      }
    });
  }


  public void setUp() {
    emailTextInput = findViewById(R.id.emailTextInput);
    fullNameTextInput = findViewById(R.id.fullnameTextInput);
    passwordTextInput = findViewById(R.id.passwordTextInput);
    confirmButton = findViewById(R.id.confirmButton);
    ageTextInput = findViewById(R.id.ageTextInput);
    weightTextInput = findViewById(R.id.weightTextInput);
    caloriesTextInput = findViewById(R.id.caloriesTextInput);
  }


  public boolean validateEmail() {
    String emailInput = emailTextInput.getEditText().getText().toString().trim();
    if (emailInput.isEmpty()) {
      emailTextInput.setError("Field can't be empty");
      return false;
    } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
      emailTextInput.setError("Please enter a valid email address");
      return false;
    } else {
      emailTextInput.setError(null);
      return true;
    }
  }

  public boolean validateName() {
    String fullNameInput = fullNameTextInput.getEditText().getText().toString().trim();
    if (fullNameInput.isEmpty()) {
      fullNameTextInput.setError("Field can't be empty");
      return false;
    } else {
      fullNameTextInput.setError(null);
      return true;
    }
  }

  public boolean validatePassword() {
    String passwordInput = passwordTextInput.getEditText().getText().toString().trim();
    if (passwordInput.isEmpty()) {
      passwordTextInput.setError("Field can't be empty");
      return false;
    } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
      passwordTextInput.setError("Password must contain at least one alphabet letter and at least 6 characters");
      return false;
    } else if (passwordInput.length() > 20) {
      passwordTextInput.setError("Password cannot be more than 20 characters");
      return false;
    } else {
      passwordTextInput.setError(null);
      return true;
    }
  }

  public boolean validateAge() {
    String ageInput = ageTextInput.getEditText().getText().toString().trim();
    if (ageInput.isEmpty()) {
      ageTextInput.setError("Field can't be empty");
      return false;
    } else if (Integer.parseInt(ageInput) < 0) {
      ageTextInput.setError("The age cannot be less than zero");
      return false;
    } else {
      ageTextInput.setError(null);
      return true;
    }
  }

  public boolean validateWeight() {
    String weightInput = weightTextInput.getEditText().getText().toString().trim();
    if (weightInput.isEmpty()) {
      weightTextInput.setError("Field can't be empty");
      return false;
    } else if (Integer.parseInt(weightInput) < 0) {
      ageTextInput.setError("The weight cannot be less than zero");
      return false;
    } else {
      weightTextInput.setError(null);
      return true;
    }
  }

  public boolean validateCalories() {
    String caloriesInput = caloriesTextInput.getEditText().getText().toString().trim();
    if (caloriesInput.isEmpty()) {
      caloriesTextInput.setError("Field can't be empty");
      return false;
    } else if (Integer.parseInt(caloriesInput) < 0) {
      ageTextInput.setError("The weight cannot be less than zero");
      return false;
    } else {
      caloriesTextInput.setError(null);
      return true;
    }
  }

  public User setUpUser() {
    final String emailInput = emailTextInput.getEditText().getText().toString().trim();
    final String fullNameInput = fullNameTextInput.getEditText().getText().toString().trim();
    String passwordInput = passwordTextInput.getEditText().getText().toString().trim();
    int age = Integer.parseInt(ageTextInput.getEditText().getText().toString().trim());
    int weight = Integer.parseInt(weightTextInput.getEditText().getText().toString().trim());
    int calories = 0;
    if (!caloriesTextInput.getEditText().getText().toString().matches("")) {
      calories = Integer.parseInt(caloriesTextInput.getEditText().getText().toString().trim());
    }
    return new User(emailInput, fullNameInput, passwordInput, age, weight, calories);
  }
}







