package com.example.biplabroy.airreservation;

import java.util.List;

public class JourneyDetails{

    String from;
    String to;
    String price;
    List<String> time;

    JourneyDetails(String from, String to, String price, List<String> time){
        this.from = from;
        this.to = to;
        this.price = price;
        this.time = time;
    }

}
