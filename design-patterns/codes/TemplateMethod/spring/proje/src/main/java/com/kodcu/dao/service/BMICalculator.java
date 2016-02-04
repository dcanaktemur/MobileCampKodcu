package com.kodcu.dao.service;

/**
 * Created by ME99964 on 8/20/2014.
 */
public class BMICalculator extends  Calculator {

    public double  calculate(double mass, double height)  {
        double valueBMI = mass / (height * height);
        return valueBMI;

    }

}
