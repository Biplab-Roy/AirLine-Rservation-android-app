package com.example.biplabroy.airreservation;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
public class RegistrationActivity1 extends AppCompatActivity {
    EditText edname,edphone,ednot;
    Spinner spfrom, spto;
    TextView price;
    String from = "Kolkata",to = "Kolkata";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        edname = (EditText)findViewById(R.id.edname);
        edphone = (EditText)findViewById(R.id.edphone);
        ednot = (EditText)findViewById(R.id.not);
        spfrom = (Spinner)findViewById(R.id.spfrom);
        spto = (Spinner)findViewById(R.id.spto);
        price = (TextView) findViewById(R.id.price);
        final Database db = new Database(RegistrationActivity1.this);
        List<String> list = new ArrayList<>();
        list.add("Kolkata");
        list.add("Dehli");
        list.add("Mumbai");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(RegistrationActivity1.this,android.R.layout.simple_spinner_item,list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spfrom.setAdapter(adapter);
        spto.setAdapter(adapter);
        spfrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                from = parent.getItemAtPosition(position).toString();
                price.setText(db.getPrice(from,to));
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                from = "";
            }
        });
        spto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                to = parent.getItemAtPosition(position).toString();
                price.setText(db.getPrice(from,to));
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                to = "";
            }
        });
    }
    public void next(View view) {
        if(edname.getText().toString().equals("")){
            Toast.makeText(RegistrationActivity1.this,"Enter name",Toast.LENGTH_LONG).show();
            return;
        }
        if(edphone.getText().toString().equals("") || edphone.getText().toString().length()!=10){
            Toast.makeText(RegistrationActivity1.this,"Enter phone number of length ten",Toast.LENGTH_LONG).show();
            return;
        }if(ednot.getText().toString().equals("")){
            Toast.makeText(RegistrationActivity1.this,"Enter Number of tickets",Toast.LENGTH_LONG).show();
            return;
        }
        if(from.equals(to) || from.equals("") || to.equals("")){
            Toast.makeText(RegistrationActivity1.this,"Invalid Destination",Toast.LENGTH_LONG).show();
            return;
        }
        if(price.getText().toString().equals("")){
            Toast.makeText(this,"Flights Not Available",Toast.LENGTH_LONG).show();
            return;
        }
        startActivity(new Intent(RegistrationActivity1.this,RegistrationActivity2.class).putExtra("name",edname.getText().toString()).putExtra("phone",edphone.getText().toString()).putExtra("from",from).putExtra("to",to).putExtra("price",price.getText().toString()).putExtra("not",ednot.getText().toString()));
    }
}
