package com.example.biplabroy.airreservation;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
public class StatusLoginActivity extends AppCompatActivity {
    EditText edid, edpass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_login);
        edid = (EditText) findViewById(R.id.edid);
        edpass = (EditText) findViewById(R.id.edpass);
    }
    public void login(View view) {
        String phone = edid.getText().toString();
        String pass = edpass.getText().toString();
        Details data = new Database(StatusLoginActivity.this).data(phone);
        if (data != null && pass.equals(data.pass)) {
            SharedPreferences sh = getSharedPreferences("user",StatusLoginActivity.this.MODE_PRIVATE);
            String s = sh.getString("qr","");
            if (s.length()==0) {
                SharedPreferences.Editor ed = sh.edit();
                ed.putString("cphone", phone);
                ed.commit();
                startActivity(new Intent(StatusLoginActivity.this, StatusActivity.class).putExtra("phone", phone));
            }
            else {
                startActivity(new Intent(StatusLoginActivity.this, GenerateQRActivity.class).putExtra("phone",phone));
            }
        } else {
            Toast.makeText(StatusLoginActivity.this, "Invalid phone or password", Toast.LENGTH_LONG).show();
        }
    }
}
