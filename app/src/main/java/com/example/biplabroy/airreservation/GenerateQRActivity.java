package com.example.biplabroy.airreservation;
import android.Manifest;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
public class GenerateQRActivity extends AppCompatActivity {
    ImageView iv;
    Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_qr);
        iv = (ImageView)findViewById(R.id.showqr);
        SharedPreferences sh = getSharedPreferences("user",GenerateQRActivity.this.MODE_PRIVATE);
        SharedPreferences.Editor ed = sh.edit();
        ed.remove("qr");
        ed.commit();
        String phone = getIntent().getExtras().getString("phone").trim();
        Details data = (new Database(GenerateQRActivity.this).data(phone));
        String input = data.id + " " + data.name + " " + data.phone + " " + data.from + " " + data.to + " " + data.not + " " + data.price + " " + data.date + " " + data.time + " " + data.pass;
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try{
            BitMatrix bitMatrix = multiFormatWriter.encode(input, BarcodeFormat.QR_CODE,600,600);
            BarcodeEncoder b = new BarcodeEncoder();
            bitmap = b.createBitmap(bitMatrix);
            iv.setImageBitmap(bitmap);
        }catch (WriterException w){
            w.printStackTrace();
        }
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(GenerateQRActivity.this,MainActivity.class));
    }
    public void download(View view) {
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},0);
        }
        else {
            String dir = Environment.getExternalStorageDirectory().getAbsolutePath();
            File mypath = new File(dir + "/Demo/");
            mypath.mkdir();
            File file = new File(mypath, System.currentTimeMillis() + ".png");
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } finally {
                Toast.makeText(GenerateQRActivity.this, "Image Saved in Internal Storage", Toast.LENGTH_LONG).show();
            }
            try {
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
