package com.kodcu.facade;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Date;

public class TravelFacade
{

    private HotelBooker hotelBooker;
    private FlightBooker flightBooker;

    public void getFlightsAndHotels(String from, String to)
    {
        ArrayList<Flight> flights = flightBooker.getFlightsFor(from, to);
        ArrayList<Hotel> hotels = hotelBooker.getHotelNamesFor(from, to);

        //process and return

    }

}
