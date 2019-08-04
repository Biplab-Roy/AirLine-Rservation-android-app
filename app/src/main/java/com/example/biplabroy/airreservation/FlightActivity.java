package com.example.biplabroy.airreservation;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import java.util.ArrayList;

public class FlightActivity extends AppCompatActivity {
    ListView lv;
    static String str;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight);
        str = getIntent().getExtras().getString("comeFrom");
        lv = (ListView) findViewById(R.id.lv);
        ArrayList<FlightDetails> flights = (new Database(this)).getAllFlights();
        FlightAdapter adapter = new FlightAdapter(this,flights);
        lv.setAdapter(adapter);
    }

    public void back(View view) {
        if(str.equals("Main"))
            startActivity(new Intent(this,MainActivity.class));
        if(str.equals("Admin"))
            startActivity(new Intent(this,ListActivity.class));
    }

}
