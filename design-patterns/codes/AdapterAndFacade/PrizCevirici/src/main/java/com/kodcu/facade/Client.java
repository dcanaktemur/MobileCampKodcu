package com.kodcu.facade;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Client
{

    public static void main(String[] args)
    {
        TravelFacade facade = new TravelFacade();

        SimpleDateFormat from  = new SimpleDateFormat("dd/M/yyyy");
        String fromDate = from.format(new Date());

        SimpleDateFormat to  = new SimpleDateFormat("dd/M/yyyy");
        String toDate = to.format(new Date());

        facade.getFlightsAndHotels(fromDate, toDate);
    }
}