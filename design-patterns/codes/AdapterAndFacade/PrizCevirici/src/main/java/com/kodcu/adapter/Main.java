package com.kodcu.adapter;


public class Main {

    public static void main(String[] args) {

        // You are in NY
        USElecStandard usEL=new USElecStandartImpl();

        // buy a conventer
        ElectricAdapter electricAdapter = new ElectricAdapter(usEL);

        // tr electric based Laptop
        Laptop laptop=new Laptop();
        laptop.calis(electricAdapter);


    }

}
