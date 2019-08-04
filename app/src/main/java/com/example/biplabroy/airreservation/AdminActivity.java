package com.example.biplabroy.airreservation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
    }

    public void login(View view) {

        EditText edid, edpass;
        edid = (EditText)findViewById(R.id.edid);
        edpass = (EditText)findViewById(R.id.edpass);

        if(edid.getText().toString().equals("admin") && edpass.getText().toString().equals("admin")){
            SharedPreferences sh = getSharedPreferences("user",this.MODE_PRIVATE);
            SharedPreferences.Editor ed = sh.edit();
            ed.putString("admin",edid.getText().toString());
            ed.commit();
            startActivity(new Intent(AdminActivity.this,ListActivity.class));
        }
        else {
            Toast.makeText(AdminActivity.this,"Invalid credential",Toast.LENGTH_LONG).show();
        }
    }
}
