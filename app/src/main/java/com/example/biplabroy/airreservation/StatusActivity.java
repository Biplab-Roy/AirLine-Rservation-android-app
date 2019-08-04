package com.example.biplabroy.airreservation;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
public class StatusActivity extends AppCompatActivity {
    TextView name, phone, journey, price, tickets, date, time;
    Details data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);
        name = (TextView) findViewById(R.id.name);
        phone = (TextView) findViewById(R.id.phone);
        journey = (TextView) findViewById(R.id.journey);
        price = (TextView) findViewById(R.id.price);
        tickets = (TextView) findViewById(R.id.not);
        date = (TextView) findViewById(R.id.date);
        time = (TextView) findViewById(R.id.time);
        data = new Database(StatusActivity.this).data(getIntent().getExtras().getString("phone"));
        name.setText("Name: " + data.name);
        phone.setText("Phone: " + data.phone);
        journey.setText("Journey: From " + data.from + " to " + data.to);
        price.setText("Total Price: " + String.valueOf(Integer.parseInt(data.price)*Integer.parseInt(data.not)));
        tickets.setText("Total Seats: " + data.not);
        date.setText("Date: " + data.date);
        time.setText("Time: " + data.time);
    }
    public void logout(View view) {
        SharedPreferences sh = getSharedPreferences("user",this.MODE_PRIVATE);
        SharedPreferences.Editor ed = sh.edit();
        ed.clear();
        ed.commit();
        startActivity(new Intent(StatusActivity.this,MainActivity.class));
    }
    @Override
    public void onBackPressed() {

    }

    public void edit(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(StatusActivity.this);
        builder.setTitle("Want to edit?");
        builder.setMessage("Are you sure?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(StatusActivity.this,EditRegistrationActivity1.class).putExtra("data",data.phone));
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    public void delete(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(StatusActivity.this);

        builder.setTitle("Cancle your reservation!!!");

        builder.setMessage("Are you sure ?");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                (new Database(StatusActivity.this)).delData(data.phone);
                startActivity(new Intent(StatusActivity.this, MainActivity.class));
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
