package com.example.reservrit;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {

    private TextView textViewFullname, textViewUsername, textViewEmail, textViewPassword, textViewConfirm;
    private Button buttonSignup, buttonCancel, btn;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        db = new DatabaseHelper(this);
        textViewFullname = findViewById(R.id.editTextFullname);
        textViewUsername = findViewById(R.id.editTextUsername);
        textViewEmail = findViewById(R.id.editTextEmail);
        textViewPassword = findViewById(R.id.editTextPassword);
        textViewConfirm = findViewById(R.id.editTextConfirm);
        buttonSignup = findViewById(R.id.buttonSignup);
        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signup_onClick(view);
            }
        });

        buttonCancel = findViewById(R.id.buttonCancel);
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewAll(view);
            }
        });

    }

    public void signup_onClick(View view) {
        String fullname = textViewFullname.getText().toString();
        String username = textViewUsername.getText().toString();
        String email = textViewEmail.getText().toString();
        String password = textViewPassword.getText().toString();
        String confirm = textViewConfirm.getText().toString();

        if(password.equals(confirm)){
            long val = db.create(fullname,username,email,password);
            if(val > 0) {
                Toast.makeText(SignupActivity.this, "Successfully Registered!", Toast.LENGTH_LONG).show();
                Intent intent1 = new Intent(SignupActivity.this, Home.class);
                startActivity(intent1);
            }
            else{
                Toast.makeText(SignupActivity.this, "Registration Failed!", Toast.LENGTH_LONG).show();
            }
        }
        else{
            Toast.makeText(SignupActivity.this, "Passwords are not matching!", Toast.LENGTH_LONG).show();
        }

    }

    public void viewAll(View view){
        Cursor res = db.getAllData3();
        StringBuffer buffer = new StringBuffer();
        if( res.getCount() == 0){
            message("error", "nothing");
            return;
        }
        while (res.moveToNext()){
            buffer.append(" ID : " + res.getString(0) + "\n");
            buffer.append(" ID : " + res.getString(1) + "\n");
            buffer.append(" ID : " + res.getString(2) + "\n");
            buffer.append(" ID : " + res.getString(3) + "\n");
            buffer.append(" ID : " + res.getString(4) + "\n\n");
        }
        message("Data", buffer.toString());

    }

    public void message(String title, String Message){
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

}
