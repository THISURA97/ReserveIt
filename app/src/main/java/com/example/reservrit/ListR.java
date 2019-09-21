/*package com.example.reservrit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ListR extends AppCompatActivity {

private static final String TAG = "EditDataActivity";

private Button SaveR,DeleteR;
private EditText EditFullName,EditEmail,EditMobile,EditCheckIn,EditCheckOut;

DatabaseHelper myDb;

private String selectedFullName,selectedEmail,selectedMobile,selectedCheckIn,selectedCheckOut;
private int selectedID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_r);

        SaveR = findViewById(R.id.SaveR_btn);
        DeleteR = findViewById(R.id.DeleteR_btn);
        EditFullName = findViewById(R.id.listFullNameR);
        EditEmail = findViewById(R.id.listEmailR);
        EditMobile = findViewById(R.id.listMobileR);
        EditCheckIn = findViewById(R.id.listCheckInR);
        EditCheckOut = findViewById(R.id.listCheckOutR);

        Intent receivedIntent = getIntent();
        selectedID = receivedIntent.getIntExtra("id",-1);

        selectedFullName = receivedIntent.getStringExtra("FULL_NAME");
        selectedEmail = receivedIntent.getStringExtra("EMAIL");
        selectedMobile = receivedIntent.getStringExtra("MOBILE");
        selectedCheckIn = receivedIntent.getStringExtra("CHECK_IN");
        selectedCheckOut = receivedIntent.getStringExtra("CHECK_OUT");

        EditFullName.setText(selectedFullName);
        EditEmail.setText(selectedEmail);
        EditMobile.setText(selectedMobile);
        EditCheckIn.setText(selectedCheckIn);
        EditCheckOut.setText(selectedCheckOut);

        SaveR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String item = editable_item.getText().toString();
                if(!item.equals("")){
                    mDatabaseHelper.updateName(item,selectedID,selectedName);
                }else{
                    toastMessage("You must enter a name");
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabaseHelper.deleteName(selectedID,selectedName);
                editable_item.setText("");
                toastMessage("removed from database");
            }




    }
}
*/