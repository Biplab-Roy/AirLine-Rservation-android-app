package com.example.biplabroy.airreservation;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;
public class RegistrationActivity2 extends AppCompatActivity {
    EditText  edpass;
    DatePicker eddate;
    Spinner time;
    List<String> times;
    String selected_time;
    Button bt;
    Details Detail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration2);
        edpass = (EditText) findViewById(R.id.edpass);
        eddate = (DatePicker)findViewById(R.id.date);
        bt = (Button) findViewById(R.id.save);
        time = (Spinner)findViewById(R.id.time);
        eddate.setMinDate(System.currentTimeMillis()-1000);
        Bundle bundle = getIntent().getExtras();
        final String name = bundle.get("name").toString();
        final String phone = bundle.get("phone").toString();
        final String from = bundle.get("from").toString();
        final String to =bundle.get("to").toString();
        final String price = bundle.get("price").toString();
        final String not = bundle.get("not").toString();
        times = (new Database(RegistrationActivity2.this)).getJourneyDetails(from,to).time;
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(RegistrationActivity2.this, android.R.layout.simple_spinner_item, times);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        time.setAdapter(adapter);
        time.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selected_time = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selected_time = "";
            }
        });
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pass = edpass.getText().toString();
                Database db = new Database(RegistrationActivity2.this);
                String date = String.valueOf(eddate.getDayOfMonth()) + "/" + String.valueOf(eddate.getMonth()) + "/" + String.valueOf(eddate.getYear());
                Detail = new Details("",phone,name,from,to,not,price,date,selected_time,pass);
                db.addData(Detail);
                startActivity(new Intent(RegistrationActivity2.this, MainActivity.class));
            }
        });
    }
}
