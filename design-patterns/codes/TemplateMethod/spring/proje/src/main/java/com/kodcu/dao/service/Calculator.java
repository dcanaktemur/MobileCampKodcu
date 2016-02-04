package com.kodcu.dao.service;

import com.kodcu.dao.interfaces.ResultDao;
import com.kodcu.entity.Result;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Timestamp;

/**
 * Created by ME99964 on 8/20/2014.
 */
public abstract class Calculator {

    protected double result;

    public final void startProcess(double mass, double height) {
        result = this.calculate(mass, height);
        this.display();
        this.writeToDatabase();
    }

    abstract double calculate(double mass, double height) ;

    public void display() {
        System.out.println(" Result is " + result);
    }

    public void writeToDatabase() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        ResultDao eventDao = (ResultDao) ctx.getBean("eventDao");
        Result event = new Result();
        event.setBmiResult("event name");
        event.setDescription("description");
        event.setDate(new Timestamp(System.currentTimeMillis()));

        event = eventDao.save(event);
        System.out.println(event);
    }

}
