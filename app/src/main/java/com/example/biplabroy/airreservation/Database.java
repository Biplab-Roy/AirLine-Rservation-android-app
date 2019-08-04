package com.example.biplabroy.airreservation;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {

    public Database(Context context) {
        super(context, "customers", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table customers(id INTEGER PRIMARY KEY, phone TEXT, name TEXT, fro TEXT, dest TEXT, noft TEXT, price TEXT, date TEXT, time TEXT, password TEXT)");
        db.execSQL("create table travel(id INTEGER PRIMARY KEY, fro TEXT, dest TEXT, price TEXT, time TEXT)");
        db.execSQL("insert into travel(fro, dest, price, time) values ('Kolkata','Dehli','7000','11.00AM')");
        db.execSQL("insert into travel(fro, dest, price, time) values ('Kolkata','Dehli','7000','3.00PM')");
        db.execSQL("insert into travel(fro, dest, price, time) values ('Kolkata','Dehli','7000','9.00AM')");
        db.execSQL("insert into travel(fro, dest, price, time) values ('Kolkata','Mumbai','5000','12.00AM')");
        db.execSQL("insert into travel(fro, dest, price, time) values ('Kolkata','Mumbai','5000','4.00PM')");
        db.execSQL("insert into travel(fro, dest, price, time) values ('Kolkata','Mumbai','5000','7.00PM')");
        db.execSQL("insert into travel(fro, dest, price, time) values ('Dehli','Kolkata','7000','11.00AM')");
        db.execSQL("insert into travel(fro, dest, price, time) values ('Dehli','Kolkata','7000','3.00PM')");
        db.execSQL("insert into travel(fro, dest, price, time) values ('Dehli','Kolkata','7000','7.00PM')");
        db.execSQL("insert into travel(fro, dest, price, time) values ('Dehli','Mumbai','4000','10.00AM')");
        db.execSQL("insert into travel(fro, dest, price, time) values ('Dehli','Mumbai','4000','6.00PM')");
        db.execSQL("insert into travel(fro, dest, price, time) values ('Dehli','Mumbai','4000','7.00PM')");
        db.execSQL("insert into travel(fro, dest, price, time) values ('Mumbai','Kolkata','5000','12.00AM')");
        db.execSQL("insert into travel(fro, dest, price, time) values ('Mumbai','Kolkata','5000','2.00PM')");
        db.execSQL("insert into travel(fro, dest, price, time) values ('Mumbai','Kolkata','5000','6.00PM')");
        db.execSQL("insert into travel(fro, dest, price, time) values ('Mumbai','Dehli','4000','3.00PM')");
        db.execSQL("insert into travel(fro, dest, price, time) values ('Mumbai','Dehli','4000','6.00PM')");
        db.execSQL("insert into travel(fro, dest, price, time) values ('Mumbai','Dehli','4000','9.00PM')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table customers");
        this.onCreate(db);
    }

    public void addData(Details data){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("insert into customers(phone, name, fro, dest, noft, price, date, time, password) values ('" + data.phone + "','" + data.name + "','" + data.from + "','" + data.to + "','" + data.not + "','" + data.price + "','" + data.date + "','" + data.time + "','" + data.pass + "')");
    }


    public void delData(String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from customers where phone='" + phone + "'");
    }

    public ArrayList<Details> getAllData() {
        ArrayList<Details> data = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from customers",null);

        if(cursor.moveToFirst()) {
            do {
                data.add(new Details(cursor.getString(0),cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getString(9)));
            } while (cursor.moveToNext());
        }
        return data;
    }

    public Details data(String phone) {
        Details data = null;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from customers where phone='" + phone + "'",null);
        if(cursor.moveToFirst())
            data = new Details(cursor.getString(0),cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getString(9));
        return data;
    }

    public void updateData(Details detail) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update customers set phone = '" + detail.phone + "', name = '" + detail.name + "', fro = '" + detail.from + "', dest = '" + detail.to + "', noft = '" + detail.not + "', price = '" + detail.price + "', date = '" + detail.date + "', time = '" + detail.time + "', password = '" + detail.pass + "' where id = '" + detail.id + "'");
    }

    public JourneyDetails getJourneyDetails(String from, String to){
        JourneyDetails journeyDetails=null;
        Cursor cursor = this.getReadableDatabase().rawQuery("select * from travel where fro = '" + from + "' and dest = '" + to + "'",null);
        List<String> s = new ArrayList<>();
        if(cursor.moveToFirst()){
            journeyDetails = new JourneyDetails(from,to,cursor.getString(3),s);
            do {
                s.add(cursor.getString(4));
            }while (cursor.moveToNext());
        }
        journeyDetails.time = s;
        return journeyDetails;
    }

    public String getPrice(String from, String to) {
        Cursor cursor = this.getReadableDatabase().rawQuery("select * from travel where fro = '" + from + "' and dest = '" + to + "'",null);
        if(cursor.moveToFirst()){
            return cursor.getString(3);
        }
        return "";
    }

    public void cancleFlight(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from travel where id='" + id + "'");
    }

    public void addFlight(String fro, String dest, String price, String time) {
        this.getWritableDatabase().execSQL("insert into travel(fro, dest, price, time) values ('"+fro+"','"+dest+"','"+price+"','"+time+"')");
    }

    public ArrayList<FlightDetails> getAllFlights() {
        ArrayList<FlightDetails> flights = new ArrayList<>();
        Cursor cursor = this.getReadableDatabase().rawQuery("select * from travel",null);
        if(cursor.moveToFirst()){
            do{
                flights.add(new FlightDetails(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4)));
            }while (cursor.moveToNext());
        }
        return flights;
    }
}
