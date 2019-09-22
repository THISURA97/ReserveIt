package com.example.reservrit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        Button Reserve_btn = findViewById(R.id.Reserve_btn);
        Reserve_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentR = new Intent(Home.this, AddR.class);
                startActivity(intentR);
            }
        });

        Button Event_btn = findViewById(R.id.Event_btn);
        Event_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentE = new Intent(Home.this, Events.class);
                startActivity(intentE);
            }
        });

        Button Travel_btn = findViewById(R.id.Travel_btn);
        Travel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentT = new Intent(Home.this, Travels.class);
                startActivity(intentT);
            }
        });


    }
}
