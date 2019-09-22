package com.example.reservrit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView textViewUsername, textViewPassword;
    private Button buttonLogin, buttonSignup;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        db = new DatabaseHelper(this);
        textViewUsername = findViewById(R.id.editTextUsername);
        textViewPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = textViewUsername.getText().toString().trim();
                String password = textViewPassword.getText().toString().trim();
                Boolean res = db.checkUser(user,password);
                if(res == true){
                    Toast.makeText(MainActivity.this, "Successfully Logged In", Toast.LENGTH_LONG).show();
                    Intent intent1 = new Intent(MainActivity.this, Home.class);
                    startActivity(intent1);
                }
                else{
                    Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_LONG).show();
                }
            }
        });

        buttonSignup = findViewById(R.id.buttonSignup);
        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });
    }
}
