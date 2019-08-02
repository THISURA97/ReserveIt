package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button b1, b2, b3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1 = findViewById(R.id.button);
        b1.setText("reservations");

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v1) {
                openReservation();
            }
        });

        b2 = findViewById(R.id.button2);
        b2.setText("events");

        b3 = findViewById(R.id.button3);
        b3.setText("travels");

    }


    public void openReservation() {
        Intent intent = new Intent(this,Reservations.class);
        startActivity(intent);
    }
}