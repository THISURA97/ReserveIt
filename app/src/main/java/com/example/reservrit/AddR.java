package com.example.reservrit;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;




public class AddR extends AppCompatActivity {


    DatabaseHelper myDb;
    Button AddR, ViewR, updateR,DeleteR;
    EditText editFirstNameR, editLastNameR, editMobileR, editCheckInR, editCheckOutR, editIdR;


//on create method

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_r);

        myDb = new DatabaseHelper(this);

        editFirstNameR = findViewById(R.id.editFirstNameR);
        editLastNameR = findViewById(R.id.editLastNameR);
        editMobileR = findViewById(R.id.editMobileR);
        editCheckInR = findViewById(R.id.editCheckInR);
        editCheckOutR = findViewById(R.id.editCheckOutR);
        editIdR = findViewById(R.id.editIdR);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);


        editCheckInR.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DatePickerDialog datePickerDialog = new DatePickerDialog(
                                AddR.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int day) {
                                month = month + 1;
                                String date = day + "/" + month + "/" + year;
                                editCheckInR.setText(date);
                            }
                        }, year, month, day);
                        datePickerDialog.show();
                    }
                }
        );

        editCheckOutR.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DatePickerDialog datePickerDialog = new DatePickerDialog(
                                AddR.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int day) {
                                month = month + 1;
                                String date = day + "/" + month + "/" + year;
                                editCheckOutR.setText(date);
                            }
                        }, year, month, day);
                        datePickerDialog.show();
                    }
                }
        );


        AddR = findViewById(R.id.AddR_btn);
        ViewR = findViewById(R.id.ViewR_btn);
        updateR = findViewById(R.id.updateR_btn);
        DeleteR = findViewById(R.id.DeleteR_btn);

        AddData();
        viewAll();
        UpdateData();
        DeleteData();

    }
    public void DeleteData() {
        DeleteR.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRows = myDb.deleteData(editIdR.getText().toString());
                        if(deletedRows > 0)
                            Toast.makeText(AddR.this,"Data Deleted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(AddR.this,"Data not Deleted",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
    public void UpdateData() {
        updateR.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                        public void onClick (View v){
                            if (editFirstNameR.getText().toString().isEmpty()) {
                                editFirstNameR.setError("First Name cant be empty");
                            }
                            else if (editLastNameR.getText().toString().isEmpty()) {
                                editLastNameR.setError("Last Name cant be empty");
                            }
                            else if (editMobileR.getText().toString().isEmpty()) {
                                editMobileR.setError("Mobile cant be empty");
                            }
                            else if (editMobileR.getText().toString().length() != 10) {
                                editMobileR.setError("Mobile number should be 10 digits");
                            }
                            else if (editCheckInR.getText().toString().isEmpty()) {
                                editCheckInR.setError("Need check in date");
                            }
                            else if (editCheckOutR.getText().toString().isEmpty()) {
                                editCheckOutR.setError("Need check out date");
                            }
                            else {
                                boolean isUpdate = myDb.updateData(
                                        editIdR.getText().toString(),
                                        editFirstNameR.getText().toString(),
                                        editLastNameR.getText().toString(),
                                        editMobileR.getText().toString(),
                                        editCheckInR.getText().toString(),
                                        editCheckOutR.getText().toString());
                                if (isUpdate == true)
                                    Toast.makeText(AddR.this, "Data Update", Toast.LENGTH_LONG).show();
                                else
                                    Toast.makeText(AddR.this, "Data not Updated", Toast.LENGTH_LONG).show();
                            }
                        }
                }
        );
    }
    public  void AddData() {
        AddR.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                      if(editFirstNameR.getText().toString().isEmpty()){
                          editFirstNameR.setError("First Name cant be empty");}
                        else if(editLastNameR.getText().toString().isEmpty()){
                           editLastNameR.setError("Last Name cant be empty");}

                      else if(editMobileR.getText().toString().isEmpty()){
                          editMobileR.setError("Mobile cant be empty");}

                      else if(editMobileR.getText().toString().length() != 10){
                                  editMobileR.setError("Mobile number should be 10 digits");}
                      else if(editCheckInR.getText().toString().isEmpty()){
                          editCheckInR.setError("Need check in date");}
                      else if(editCheckOutR.getText().toString().isEmpty()){
                          editCheckOutR.setError("Need check out date");}

                      else{
                        boolean isInserted = myDb.insertData(
                                editFirstNameR.getText().toString(),
                                editLastNameR.getText().toString(),
                                editMobileR.getText().toString(),
                                editCheckInR.getText().toString(),
                                editCheckOutR.getText().toString());
                                    if(isInserted == true)

                                            Toast.makeText(AddR.this,"Data Inserted",Toast.LENGTH_LONG).show();
                                    else
                                            Toast.makeText(AddR.this,"Data not Inserted",Toast.LENGTH_LONG).show();
                    }

                }
        }
        );
    }
    public void viewAll() {
        ViewR.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDb.getAllData();
                        if(res.getCount() == 0) {
                            // show message
                            showMessage("Error","Nothing found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Id :"+ res.getString(0)+"\n");
                            buffer.append("First Name :"+ res.getString(1)+"\n");
                            buffer.append("Last Name :"+ res.getString(2)+"\n");
                            buffer.append("Mobile :"+ res.getString(3)+"\n");
                            buffer.append("Check In :"+ res.getString(4)+"\n");
                            buffer.append("Check Out :"+ res.getString(5)+"\n\n");
                        }

                        // Show all data
                        showMessage("Data",buffer.toString());
                    }
                }
        );
    }

    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }


}






