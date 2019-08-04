package com.example.biplabroy.airreservation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddFlightActivity extends AppCompatActivity {

    EditText from,to,price,time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_flight);
        from = (EditText)findViewById(R.id.from);
        to = (EditText)findViewById(R.id.to);
        price = (EditText)findViewById(R.id.price);
        time = (EditText)findViewById(R.id.time);
    }

    public void svAndBk(View view) {
        (new Database(this)).addFlight(from.getText().toString(),to.getText().toString(),price.getText().toString(),time.getText().toString());
        startActivity(new Intent(this,ListActivity.class));
    }
}
