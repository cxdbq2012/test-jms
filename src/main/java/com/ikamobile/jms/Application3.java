package com.ikamobile.jms;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;


import java.util.Random;


/**
 * Created by xdb20 on 2015/12/3.
 */
public class Application3 {

   static class T implements Runnable {
        public void run() {
            try {

                int i = new Random().nextInt(100000);

                System.out.println("=========="+i);
                StringEntity myEntity = null;
                myEntity = new StringEntity("");

                myEntity.setContentType("application/xml; charset=UTF-8");
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost("http://www.baidu.com");
                httpPost.setEntity(myEntity);
                HttpResponse response = httpclient.execute(httpPost);
                HttpEntity entity = response.getEntity();
                String rp = EntityUtils.toString(entity);

                Thread.sleep(100 * new Random().nextInt(10));
                httpclient.getConnectionManager().shutdown();
                System.out.println(rp+"==========="+i);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        T t = new T();
        Thread t1 = new Thread(t);
        Thread t2 = new Thread(t);
        Thread t3 = new Thread(t);
        Thread t4 = new Thread(t);
        Thread t5 = new Thread(t);
        Thread t6 = new Thread(t);
        Thread t7 = new Thread(t);
        Thread t8 = new Thread(t);
        Thread t9 = new Thread(t);
        Thread t10 = new Thread(t);
        Thread t11 = new Thread(t);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();
        t8.start();
        t9.start();
        t10.start();
        t11.start();


    }
}
