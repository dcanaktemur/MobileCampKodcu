package com.kodcu.service;

/**
 * Created by ME99964 on 8/13/2014.
 */
public class Stock {

    private double price ;
    private double threshold;
    private String status;
    private String name;

    public Stock(String name, double threshold) {
        this.name = name;
        this.threshold = threshold;

    }

    public void tradeAt(double price) {
        this.price = price;
        if(price < threshold) {
            this.status = "OFF";
        } else {
            this.status = "ON";
        }
    }

    public void setStatus(String status) {
        this.status = status ;
    }

    public String getStatus() {
        return status;
    }
}
