package com.example.biplabroy.airreservation;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
public class MainActivity extends AppCompatActivity {
    Button btreg, btstatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btreg = (Button) findViewById(R.id.register);
        btstatus = (Button) findViewById(R.id.status);

        btreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,RegistrationActivity1.class));
            }
        });

        btstatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,StatusLoginActivity.class));
            }
        });

    }
    public void admin(View view) {
        startActivity(new Intent(MainActivity.this,AdminActivity.class));
    }
    @Override
    public void onBackPressed() {
        return;
    }
    public void fl_list(View view) {
        startActivity(new Intent(MainActivity.this,FlightActivity.class).putExtra("comeFrom","Main"));
    }
    public void genarateQR(View view) {
        SharedPreferences sh = getSharedPreferences("user",MainActivity.this.MODE_PRIVATE);
        SharedPreferences.Editor ed = sh.edit();
        ed.putString("qr","yes");
        ed.commit();
        startActivity(new Intent(MainActivity.this,StatusLoginActivity.class));
    }
    public void exit(View view) {
        return;
    }
}
