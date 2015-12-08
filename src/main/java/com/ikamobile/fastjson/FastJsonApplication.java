package com.ikamobile.fastjson;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by xdb20 on 2015/12/7.
 */
public class FastJsonApplication {

    public static void main(String[]args){

        Person p = new Person();
        Area a = new Area();
        a.setName("大洋洲");

        Address address = new Address();
        address.setArea(a);
        address.setLocation("魔古村临济街3巷");

        p.setName("历史");
        p.setAddress(address);
        p.setAge(23);
        p.setGender(Gender.FEMALE);
        p.setLoves(Arrays.asList("ss", "sdf", "sdfdd"));
        p.setBirthday(new Date());

        String jsonStrng = JSON.toJSONString(p);

        System.out.println(jsonStrng);

        Person pp = JSON.parseObject(jsonStrng,Person.class);

        List<String> list = JSON.parseArray("[\"ss\",\n" +
                "\t\"sdf\",\n" +
                "\t\"sdfdd\"]",String.class);

        System.out.println(list);
    }
}
