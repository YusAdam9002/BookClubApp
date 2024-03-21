package com.example.bookclubapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    DBHelper dbHelper;
    Button btnLogin, goToReg, forgot;
    EditText etUserName, etPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DBHelper(this);
        etUserName = findViewById(R.id.userName);
        etPass = findViewById(R.id.password);
        goToReg = findViewById(R.id.goToRegister);
        forgot = findViewById(R.id.forgPassword);
        btnLogin = findViewById(R.id.logButton);

        // Set click listener for the "Go to Register" button
        goToReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ActivitySignUp.class);
                startActivity(intent);
            }
        });

        // Set click listener for the "Login" button
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                String username = etUserName.getText().toString().trim();
                String password = etPass.getText().toString().trim();

                // Validate username and password (optional)
                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter username and password", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Check user credentials
                boolean isLogged = dbHelper.checkUser(username, password);
                if(isLogged){
                    Intent intent = new Intent(MainActivity.this, ActivityUser.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Set click listener for the "Forgot Password" button
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ActivityForgotPass.class);
                startActivity(intent);
            }
        });
    }
}
