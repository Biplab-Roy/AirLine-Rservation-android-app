package com.example.biplabroy.airreservation;

public class Details{
    String id;
    String phone;
    String name;
    String from;
    String to;
    String not;
    String price;
    String date;
    String time;
    String pass;

    Details(String id, String phone, String name, String from, String to, String not, String price,  String date, String time, String pass) {
        this.phone = phone;
        this.name = name;
        this.from = from;
        this.to = to;
        this.not = not;
        this.price = price;
        this.time = time;
        this.date = date;
        this.pass = pass;
        this.id = id;
    }

}
