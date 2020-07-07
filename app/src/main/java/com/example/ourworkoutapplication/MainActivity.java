package com.example.ourworkoutapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

  private static User currentUser;
  private Button signUpButton;
  private Button signInButton;
  private TextInputLayout emailMain;
  private TextInputLayout passwordMain;
  private TextView attemptText;
  private static final String PASSWORD = "password";

  String emailInput;
  String passwordInput;

  private FirebaseFirestore db = FirebaseFirestore.getInstance();
  private static final String TAG = "TAG";
  private int counter = 5;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    setUp();

    signUpButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        startActivity(new Intent(getApplicationContext(), SignUpActivity.class));
      }
    });

    signInButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (!validateEmptyEmail() | !validateEmptyPassword()) {
          decreaseAttempt();
          return;
        }
        final DocumentReference docRef = db.collection("users").document(emailInput);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
          @Override
          public void onComplete(@NonNull Task<DocumentSnapshot> task) {
            if (task.isSuccessful()) {
              DocumentSnapshot document = task.getResult();
              if (document.exists()) {
                String correctPassword = document.getData().get(PASSWORD).toString();
                validatePassword(passwordInput, correctPassword, document);
              } else {
                emailMain.setError("Incorrect email. Please try again.");
                attemptText.setText("Attempts remaining " + (--counter));
                if (counter == 0) {
                  signInButton.setEnabled(false);
                }
              }
            } else {
              Log.d(TAG, "get failed with ", task.getException());
            }
          }
        });
      }
    });
  }

  public void setUp() {
    emailMain = findViewById(R.id.emailMain);
    passwordMain = findViewById(R.id.passwordMain);
    attemptText = findViewById(R.id.AttemptText);
    attemptText.setText("Attempts remaining: 5");
    signInButton = findViewById(R.id.SignInButton);
    signUpButton = findViewById(R.id.SignUpButton);
  }

  private boolean validateEmptyEmail() {
    emailInput = emailMain.getEditText().getText().toString().trim();
    if (emailInput.isEmpty()) {
      emailMain.setError("Email is required");
      return false;
    } else {
      emailMain.setError(null);
    }
    return true;
  }

  private boolean validateEmptyPassword() {
    passwordInput = passwordMain.getEditText().getText().toString().trim();
    if (passwordInput.isEmpty()) {
      passwordMain.setError("Password is required");
      return false;
    } else {
      passwordMain.setError(null);
    }
    return true;
  }

  private void validatePassword(String passwordInput, String correctPassword, DocumentSnapshot document) {
    if (passwordInput.equals(correctPassword)) {
      Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
      currentUser = document.toObject(User.class);
      Intent intent = new Intent(getApplicationContext(), HomeScreenActivity.class);
      startActivity(intent);
    } else {
      passwordMain.setError("Incorrect password. Please try again.");
      decreaseAttempt();
    }
  }

  private void decreaseAttempt() {
    counter--;
    attemptText.setText("Attempts remaining " + counter);
    if (counter == 0) {
      signInButton.setEnabled(false);
    }
  }

  public static User getCurrentUser() {
    return currentUser;
  }

}
