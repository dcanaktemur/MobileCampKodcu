package com.kodcu.service;

/**
 * Created by ME99964 on 8/13/2014.
 */
public class TradingService {

    public Stock newStock(String stockName, double threshold) {
            return new Stock(stockName, threshold);
    }
}
