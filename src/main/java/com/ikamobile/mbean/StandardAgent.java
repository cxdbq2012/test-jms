package com.ikamobile.mbean;

import javax.management.Attribute;
import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

public class StandardAgent {
//    private MBeanServer mBeanServer = null;
//
//    public StandardAgent() {
//        mBeanServer = MBeanServerFactory.newMBeanServer();
//    }
//
//    public MBeanServer getMBeanServer() {
//        return mBeanServer;
//    }
//
//    public ObjectName createObjectName(String name) {
//        ObjectName objectName = null;
//        try {
//            objectName = new ObjectName(name);
//        } catch (Exception e) {
//        }
//        return objectName;
//    }
//
//    private void createStandardBean(ObjectName objectName, String managedResourceClassName) {
//        try {
//            mBeanServer.createMBean(managedResourceClassName, objectName);
//        } catch (Exception e) {
//        }
//    }

    public static void main(String[] args) throws Exception{
//        StandardAgent agent = new StandardAgent();
//        MBeanServer mBeanServer = agent.getMBeanServer();
//        String domain = mBeanServer.getDefaultDomain();
//        String managedResourceClassName = "com.ikamobile.mbean.Car";
//        System.out.println(domain + ":type=" + managedResourceClassName);
//        ObjectName objectName = agent.createObjectName(domain + ":type=" + managedResourceClassName);
//        System.out.println("objectName: " + objectName);
//        agent.createStandardBean(objectName, managedResourceClassName);
//
//        try {
//            Attribute colorAttribute = new Attribute("Color", "blue");
//            mBeanServer.setAttribute(objectName, colorAttribute);
//            System.out.println(mBeanServer.getAttribute(objectName, "Color"));
//            mBeanServer.invoke(objectName, "drive", null, null);
//
//
//            Thread.sleep(1000*60*60);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();

        ObjectName name = new ObjectName(mbs.getDefaultDomain()+":type=com.ikamobile.mbean.Car");
        Car mbean = new Car();
        mbs.registerMBean(mbean, name);


        System.out.println("Waiting forever...");
        Thread.sleep(Long.MAX_VALUE);


















    }
}
