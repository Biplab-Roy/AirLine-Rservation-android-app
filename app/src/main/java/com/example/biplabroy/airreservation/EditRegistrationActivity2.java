package com.example.biplabroy.airreservation;
import android.content.Intent;
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
public class EditRegistrationActivity2 extends AppCompatActivity {
    List<String> times;
    EditText  edpass;
    DatePicker eddate;
    Spinner time;
    Button bt;
    Details data = new Details("","","","","","","","","","");
    Details Detail = new Details("","","","","","","","","","");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_registration2);
        edpass = (EditText) findViewById(R.id.edpass);
        eddate = (DatePicker)findViewById(R.id.date);
        bt = (Button) findViewById(R.id.save);
        time = (Spinner)findViewById(R.id.time);
        eddate.setMinDate(System.currentTimeMillis()-1000);
        Bundle bundle = getIntent().getExtras();
        Detail.name = bundle.get("name").toString();
        Detail.phone = bundle.get("phone").toString();
        Detail.from = bundle.get("from").toString();
        Detail.to =bundle.get("to").toString();
        Detail.price = bundle.get("price").toString();
        Detail.not = bundle.get("not").toString();
        String rph = bundle.get("rph").toString();
        this.data = (new Database(EditRegistrationActivity2.this)).data(rph);
        edpass.setText(this.data.pass);
        times = (new Database(EditRegistrationActivity2.this)).getJourneyDetails(Detail.from,Detail.to).time;
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(EditRegistrationActivity2.this, android.R.layout.simple_spinner_item, times);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        time.setAdapter(adapter);
        time.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Detail.time = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Detail.time = "";
            }
        });
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Detail.pass = edpass.getText().toString();
                Database db = new Database(EditRegistrationActivity2.this);
                Detail.id = data.id;
                String date = String.valueOf(eddate.getDayOfMonth()) + "/" + String.valueOf(eddate.getMonth()) + "/" + String.valueOf(eddate.getYear());
                Detail.date = date;
                db.updateData(Detail);
                startActivity(new Intent(EditRegistrationActivity2.this, StatusActivity.class).putExtra("phone",Detail.phone));
            }
        });
    }
}
