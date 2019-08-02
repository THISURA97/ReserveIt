package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Reservations extends AppCompatActivity {
Button b4,b5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservations);

        b4=findViewById(R.id.button4);
        b4.setText("Submit");

        b5=findViewById(R.id.button5);
        b5.setText("Cancel");

        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {
                backHome();
            }
        });
    }
    public void backHome(){
        Intent intent1 = new Intent(this,MainActivity.class);
        startActivity(intent1);
    }
}
