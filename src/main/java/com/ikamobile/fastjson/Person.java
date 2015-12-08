package com.ikamobile.fastjson;

import com.ikamobile.Versionable;

import java.util.Date;
import java.util.List;

/**
 * Created by xdb20 on 2015/12/7.
 */
public class Person implements Versionable {
    private String name;
    private int age;
    private Address address;
    private List<String> loves;
    private Gender gender;
    private Date birthday;

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<String> getLoves() {
        return loves;
    }

    public void setLoves(List<String> loves) {
        this.loves = loves;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getVersion() {
        return "2.3V";
    }
}
