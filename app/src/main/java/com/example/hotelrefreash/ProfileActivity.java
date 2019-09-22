package com.example.hotelrefreash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {

    private TextView textViewId, textViewFulname, textViewUsername, textViewEmail;
    private Button buttonChangePassword, buttonDelete;
    Databasehelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        db = new Databasehelper(this);
        textViewId = findViewById(R.id.textViewId);
        textViewFulname = findViewById(R.id.textViewFullname);
        textViewUsername = findViewById(R.id.textViewUsername);
        textViewEmail = findViewById(R.id.textViewEmail);
        buttonChangePassword = findViewById(R.id.buttonChangePassword);
        buttonDelete = findViewById(R.id.buttonDelete);

        User user = db.getUserData(String.valueOf(MainActivity.id));

        textViewId.setText(""+user.getId());
        textViewEmail.setText(user.getEmail());
        textViewFulname.setText(user.getFullname());
        textViewUsername.setText(user.getUsername());


        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer deleteRows = db.deleteUser(String.valueOf(MainActivity.id));
                if(deleteRows > 0)
                    Toast.makeText(ProfileActivity.this, "Data is deleted", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(ProfileActivity.this, "Data has not found", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }

}
