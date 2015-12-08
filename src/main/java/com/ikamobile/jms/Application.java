package com.ikamobile.jms;

import com.alibaba.fastjson.JSON;
import com.ikamobile.cache.RedisCache;
import com.ikamobile.cache.RedisCacheManager;
import com.ikamobile.cache.RedisManager;
import org.springframework.cache.Cache;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.Destination;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by xdb20 on 2015/11/19.
 */
public class Application {



    public static void main(String[]args){

        ClassPathXmlApplicationContext cpx = new ClassPathXmlApplicationContext("applicationContext.xml");

//        JmsTemplate template = (JmsTemplate) cpx.getBean("jmsTemplate");
//
        ProducerServiceImpl p = (ProducerServiceImpl) cpx.getBean("producerServiceImpl");
//
        for (int i=0;i<200;i++)
        {
            MyMessage mm =  new MyMessage();
            mm.setName("tom");
            mm.setSize(i);
            mm.setVersion("22.2");
            mm.setAbc(Arrays.asList("abc", "啊几", "阿吉"));

            String mmJsno = JSON.toJSONString(mm);

            p.sendMessage((Destination) cpx.getBean("queueDestination"),mmJsno);
        }


//       RedisCacheManager cacheManager = (RedisCacheManager) cpx.getBean("cacheManager");
//
//        Cache c =  cacheManager.getCache("flightCache");
//
//        c.put("tom1","1sda23");
//        c.put("to2m","12wqe3");
//        c.put("to12m","123");
//        c.put("to31m","12asd3");
//        c.put("to12m","1asd23");
//        c.put("tom","12asd3");
//        c.put("to324m","asd123");
//        c.put("tsdom", "1asd23");

//        RedisManager redisManager = (RedisManager) cpx.getBean("redisManager");
//
//        redisManager.set("abc:aa","123",80);
//        redisManager.set("abc:aa:bb","as123",80);
//        redisManager.set("abc:aacc","12sdf3",80);

//        try {
//            Thread.sleep(30000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        cpx.close();
    }

    public static String TEXT = "先启动activeMQ，再打开两个命令窗口，都进入D:\\activemq\\example，一个运行：ant consumer，一个运行：ant producer，如果成功发送/接收了消息就OK了";
}
