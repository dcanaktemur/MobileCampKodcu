package com.kodcu.run;

import java.sql.Timestamp;

import com.kodcu.dao.interfaces.ResultDao;
import com.kodcu.dao.service.BMICalculator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.kodcu.entity.Result;

/**
 * @author kodcu
 * 
 */
public class Run {

	/**
	 * @param args
	 */
	public static void main(String[] args) {


        BMICalculator bmiCalculator = new BMICalculator();
        bmiCalculator.startProcess(90, 1.75);
	}

}
