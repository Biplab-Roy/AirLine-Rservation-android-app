package com.example.biplabroy.airreservation;

public class FlightDetails{

    String from,to,time,price,id;

    public FlightDetails(String id, String from, String to,String price, String time){
        this.id = id;
        this.from = from;
        this.to = to;
        this.time = time;
        this.price = price;
    }
}
