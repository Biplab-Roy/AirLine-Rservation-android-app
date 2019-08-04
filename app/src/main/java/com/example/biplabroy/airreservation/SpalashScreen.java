package com.example.biplabroy.airreservation;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SpalashScreen extends AppCompatActivity {

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spalash_screen);
        context = SpalashScreen.this;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sh = getSharedPreferences("user",context.MODE_PRIVATE);
                String cphone = sh.getString("cphone","");
                String admin = sh.getString("admin","");
                if(cphone.length()!=0){
                    startActivity(new Intent(SpalashScreen.this,StatusActivity.class).putExtra("phone",cphone));
                }
                else if(admin.length()!=0) {
                    startActivity(new Intent(SpalashScreen.this,ListActivity.class));
                }
                else {
                    startActivity(new Intent(SpalashScreen.this,MainActivity.class));
                }
            }
        },2000);

    }
}
