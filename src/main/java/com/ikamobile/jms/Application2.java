package com.ikamobile.jms;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.Destination;

/**
 * Created by xdb20 on 2015/11/19.
 */
public class Application2 {


    public static void main(String[]args) throws Exception {

        ClassPathXmlApplicationContext cpx = new ClassPathXmlApplicationContext("applicationContext2.xml");

        Thread.sleep(Integer.MAX_VALUE);

        cpx.close();


    }

}
