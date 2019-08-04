package com.example.biplabroy.airreservation;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
public class ListActivity extends AppCompatActivity {
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        lv = (ListView) findViewById(R.id.lv);
        ArrayList<Details> data;
        Database db = new Database(ListActivity.this);
        data = db.getAllData();
        OwnAdapter adapter = new OwnAdapter(ListActivity.this,data);
        lv.setAdapter(adapter);
    }
    public void logout(View view) {
        SharedPreferences sh = getSharedPreferences("user",this.MODE_PRIVATE);
        SharedPreferences.Editor ed = sh.edit();
        ed.clear();
        ed.commit();
        startActivity(new Intent(ListActivity.this,MainActivity.class));
    }
    @Override
    public void onBackPressed() {
        return;
    }

    public void addFlight(View view) {
        startActivity(new Intent(this,AddFlightActivity.class));
    }

    public void cancleFlight(View view) {
        startActivity(new Intent(this,FlightActivity.class).putExtra("comeFrom","Admin"));
    }
}
