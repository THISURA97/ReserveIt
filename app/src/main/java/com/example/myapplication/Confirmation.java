package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Confirmation extends AppCompatActivity {


    Button bx,by;
    TextView t6,t5,t7,t9,t13;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        t13=findViewById(R.id.textView13);
        t13.setText(getIntent().getStringExtra("CI"));


        t6=findViewById(R.id.textView6);
        t6.setText("Room Type");

        t5=findViewById(R.id.textView5);
        t5.setText("No of rooms");

        t7=findViewById(R.id.textView7);
        t7.setText("Check in");

        t9=findViewById(R.id.textView9);
        t9.setText("Check out");

        bx = findViewById(R.id.button6);
        bx.setText("Update");

        bx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v10) {
                updateDetails();

            }
        });

        by=findViewById(R.id.button7);
        by.setText("Reserve");

        by.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v11) {
                Reservation();

            }
        });

    }
    public void updateDetails() {
        Intent intent4 = new Intent(this, Submission.class);
        startActivity(intent4);
    }
    public void Reservation() {
        Intent intent5 = new Intent(this, Reservation.class);
        startActivity(intent5);
    }
}

