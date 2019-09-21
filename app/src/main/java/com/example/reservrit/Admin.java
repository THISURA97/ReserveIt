package com.example.reservrit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Admin extends AppCompatActivity {
    Button AddP,EditP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);


        AddP.findViewById(R.id.editP);
        EditP.findViewById(R.id.AddP);

        AddP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin.this,ActivityPackageAdd.class);
                startActivity(intent);
            }
        });
        EditP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin.this,ActivityPackageEdit.class);
                startActivity(intent);
            }
        });
    }
}
