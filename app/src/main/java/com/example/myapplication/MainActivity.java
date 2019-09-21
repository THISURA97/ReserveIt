package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    private Button addReservationButton;
    private Button editReservationButton;
    private Button deleteReservationButton;
    private Button viewAllReservationButton;
    private ReservationOperations reservationOps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        addReservationButton =findViewById(R.id.button_add_reservation);
        editReservationButton = findViewById(R.id.button_edit_reservation);
        deleteReservationButton = findViewById(R.id.button_delete_reservation);
        viewAllReservationButton = findViewById(R.id.button_view_reservation);



        addReservationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,AddUpdateReservation.class);
                i.putExtra(EXTRA_ADD_UPDATE, "Add");
                startActivity(i);
            }
        });
        editReservationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getResIdAndUpdateRes();
            }
        });
        deleteReservationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getResIdAndRemoveRes();
            }
        });
        viewAllReservationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ViewAllReservations.class);
                startActivity(i);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.reservation_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_item_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void getResIdAndUpdateRes(){

        LayoutInflater li = LayoutInflater.from(this);
        View getResIdView = li.inflate(R.layout.dialog_get_res_id, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        // set dialog_get_res_id.xml to alertdialog builder
        alertDialogBuilder.setView(getResIdView);

        final EditText userInput = (EditText) getResIdView.findViewById(R.id.editTextDialogUserInput);

        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // get user input and set it to result
                        // edit text
                        Intent i = new Intent(MainActivity.this,AddUpdateReservation.class);
                        i.putExtra(EXTRA_ADD_UPDATE, "Update");
                        i.putExtra(EXTRA_RES_ID, Long.parseLong(userInput.getText().toString()));
                        startActivity(i);
                    }
                }).create()
                .show();

    }


    public void getResIdAndRemoveRes(){

        LayoutInflater li = LayoutInflater.from(this);
        View getResIdView = li.inflate(R.layout.dialog_get_res_id, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        // set dialog_get_res_id.xml to alertdialog builder
        alertDialogBuilder.setView(getResIdView);

        final EditText userInput =  getResIdView.findViewById(R.id.editTextDialogUserInput);

        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // get user input and set it to result
                        // edit text
                        reservationOps = new ReservationOperations(MainActivity.this);
                        reservationOps.removeReservation(reservationOps.getReservation(Long.parseLong(userInput.getText().toString())));
                        Toast t = Toast.makeText(MainActivity.this,"Reservation removed successfully!",Toast.LENGTH_SHORT);
                        t.show();
                    }
                }).create()
                .show();

    }

    @Override
    protected void onResume() {
        super.onResume();
        reservationOps.open();
    }

    @Override
    protected void onPause() {
        super.onPause();
        reservationOps.close();

    }
}