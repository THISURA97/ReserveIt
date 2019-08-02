package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class Reservations extends AppCompatActivity {
Button b4,b5;
TextView t1,t2,t3,t4,t5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservations);

        t5=findViewById(R.id.textView8);
        t5.setText("Room Details");

        t1=findViewById(R.id.textView);
        t1.setText("Room Type");

        Spinner spinner1 = findViewById(R.id.spinner1);

        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(Reservations.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.room_types));

        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);

        t2=findViewById(R.id.textView2);
        t2.setText("No of rooms");

        t3=findViewById(R.id.textView3);
        t3.setText("Check In");

        t4=findViewById(R.id.textView4);
        t4.setText("Check Out");

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
