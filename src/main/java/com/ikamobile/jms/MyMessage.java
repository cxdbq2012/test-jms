package com.ikamobile.jms;

import java.util.List;

/**
 * Created by xdb20 on 2015/12/7.
 */
public class MyMessage {
    private String version;
    private String name;
    private int size;
    private List<String> abc;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<String> getAbc() {
        return abc;
    }

    public void setAbc(List<String> abc) {
        this.abc = abc;
    }
}
