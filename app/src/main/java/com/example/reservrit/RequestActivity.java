package com.example.reservrit;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;


public class RequestActivity extends AppCompatActivity {

    DatabaseHelper mydb;

    EditText txt_date, txt_pickup, txt_drop, txt_contact, txt_pasengers, txt_vehicles, txt_Id;
    Button btn_Save;
    Button btn_View;
    Button btn_Update;
    Button btn_Delete;

    Spinner spinnerType;
    String[] types = {"Car", "Van", "Bus"};
    ArrayAdapter<String> typesAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);
        mydb = new DatabaseHelper(this);

        txt_date =findViewById(R.id.txt_date);

        spinnerType = findViewById(R.id.spinnerType);
        typesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, types);
        spinnerType.setAdapter(typesAdapter);

        txt_pickup =  findViewById(R.id.txt_pickup);
        txt_drop =  findViewById(R.id.txt_drop);
        txt_contact =  findViewById(R.id.txt_contact);
        txt_pasengers =  findViewById(R.id.txt_pasengers);
        txt_vehicles =  findViewById(R.id.txt_vehicles);
        txt_Id =  findViewById(R.id.txt_Id);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        txt_date.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DatePickerDialog datePickerDialog = new DatePickerDialog(
                                RequestActivity.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int day) {
                                month = month + 1;
                                String date = day + "/" + month + "/" + year;
                                txt_date.setText(date);
                            }
                        }, year, month, day);
                        datePickerDialog.show();
                    }
                }
        );

        btn_Save = findViewById(R.id.btn_Save);
        btn_View = findViewById(R.id.btn_View);
        btn_Update = findViewById(R.id.btn_Update);
        btn_Delete = findViewById(R.id.btn_Delete);

        Save();
        View();
        Update();
        Delete();
    }

    public void Delete(){
        btn_Delete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Integer deleteRows = mydb.deleteData1(Integer.parseInt(txt_Id.getText().toString()));
                        if (deleteRows > 0)
                            Toast.makeText(RequestActivity.this,"Data Deleted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(RequestActivity.this,"Data Not Deleted",Toast.LENGTH_LONG).show();
                    }
                }

        );
    }

    public void Update(){
        btn_Update.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (txt_date.getText().toString().isEmpty()) {
                            txt_date.setError("Date cant be empty");
                        } else if (txt_pickup.getText().toString().isEmpty()) {
                            txt_pickup.setError("Pickup Location cant be empty");
                        } else if (txt_drop.getText().toString().isEmpty()) {
                            txt_drop.setError("Destination cant be empty");
                        } else if (String.valueOf(txt_contact).length() != 10) {
                            txt_contact.setError("Mobile number should be 10 digits");
                        } else if (String.valueOf(txt_pasengers).isEmpty()) {
                            txt_pasengers.setError("You must give the Number");
                        } else if (String.valueOf(txt_vehicles).isEmpty()) {
                            txt_vehicles.setError("You must give the Number");
                        }  else if(spinnerType.getSelectedItemPosition() == 0 && Integer.parseInt(txt_pasengers.getText().toString()) > 4){
                            txt_pasengers.setError("You cant choose one car more than 4 passengers");
                        }else if (spinnerType.getSelectedItemPosition() == 1 && Integer.parseInt(txt_pasengers.getText().toString()) > 10) {
                            txt_pasengers.setError("You cant choose one van more than 10 passengers");
                        }else if (spinnerType.getSelectedItemPosition() == 2 && Integer.parseInt(txt_pasengers.getText().toString()) > 40) {
                            txt_pasengers.setError("You cant choose one bus more than 40 passengers");

                        }else {
                            boolean isUpdated = mydb.updateData1(
                                    Integer.parseInt(txt_Id.getText().toString()),
                                    txt_date.getText().toString(),
                                    spinnerType.getSelectedItem().toString(),
                                    txt_pickup.getText().toString(),
                                    txt_drop.getText().toString(),
                                    Integer.parseInt(txt_contact.getText().toString()),
                                    Integer.parseInt(txt_pasengers.getText().toString()),
                                    Integer.parseInt(txt_vehicles.getText().toString()));
                            if (isUpdated == true)
                                Toast.makeText(RequestActivity.this, "Data Updated", Toast.LENGTH_LONG).show();
                            else
                                Toast.makeText(RequestActivity.this, "Data Not Updated", Toast.LENGTH_LONG).show();
                        }
                    }
                }

        );
    }

    public void Save(){
        btn_Save.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (txt_date.getText().toString().isEmpty()) {
                            txt_date.setError("Date cant be empty");
                        } else if (txt_pickup.getText().toString().isEmpty()) {
                            txt_pickup.setError("Pickup Location cant be empty");
                        } else if (txt_drop.getText().toString().isEmpty()) {
                            txt_drop.setError("Destination cant be empty");
                        } else if (String.valueOf(txt_pasengers).isEmpty()) {
                            txt_pasengers.setError("You must give the Number");
                        } else if (String.valueOf(txt_vehicles).isEmpty()) {
                            txt_vehicles.setError("You must give the Number");
                        } else if(spinnerType.getSelectedItemPosition() == 0 && Integer.parseInt(txt_pasengers.getText().toString()) > 4){
                            txt_pasengers.setError("You cant choose one car more than 4 passengers");
                        }else if (spinnerType.getSelectedItemPosition() == 1 && Integer.parseInt(txt_pasengers.getText().toString()) > 10) {
                            txt_pasengers.setError("You cant choose one van more than 10 passengers");
                        }else if (spinnerType.getSelectedItemPosition() == 2 && Integer.parseInt(txt_pasengers.getText().toString()) > 40) {
                            txt_pasengers.setError("You cant choose one bus more than 40 passengers");
                        }else {
                            boolean isInserted = mydb.insertData1(
                                    txt_date.getText().toString(),
                                    spinnerType.getSelectedItem().toString(),
                                    txt_pickup.getText().toString(),
                                    txt_drop.getText().toString(),
                                    Integer.parseInt(txt_contact.getText().toString()),
                                    Integer.parseInt(txt_pasengers.getText().toString()),
                                    Integer.parseInt(txt_vehicles.getText().toString()));

                            if (isInserted = true)
                                Toast.makeText(RequestActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                            else
                                Toast.makeText(RequestActivity.this, "Data not Inserted", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );

    }

    public void View(){
        btn_View.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Cursor res = mydb.getAllData1();
                        if (res.getCount() == 0){
                            //show message
                            showMessage("Error","Nothing found");
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()){
                            buffer.append("ID : "+res.getInt(0)+"\n");
                            buffer.append("DATE : "+res.getString(1)+"\n");
                            buffer.append("TYPE : "+res.getString(2)+"\n");
                            buffer.append("PICKUP : "+res.getString(3)+"\n");
                            buffer.append("DESTINATION : "+res.getString(4)+"\n");
                            buffer.append("CONTACT : "+res.getInt(5)+"\n");
                            buffer.append("PASENGERS : "+res.getString(6)+"\n");
                            buffer.append("VEHICLES : "+res.getString(7)+"\n");

                        }
                        //Show all data
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
