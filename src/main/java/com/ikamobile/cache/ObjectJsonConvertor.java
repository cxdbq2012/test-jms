package com.ikamobile.cache;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class ObjectJsonConvertor {

    private static final String CLASS_OBJECT_BREAKER = "@@";

    public static String toString(Object object, SerializerFeature... features) {
        return object == null ? null : new StringBuilder()
                .append(object.getClass().getName())
                .append(CLASS_OBJECT_BREAKER)
                .append(JSON.toJSONString(object, features))
                .toString();
    }

    public static String toString(Object object) {
        return toString(object, SerializerFeature.WriteClassName);
    }

    public static Object toObject(String string) {
        if (string == null || string.trim().length() == 0) {
            return null;
        }
        String[] classObjectPairs = string.split(CLASS_OBJECT_BREAKER, 2);
        if (classObjectPairs.length != 2) {
            //log.error("classObjectPairs length is not 2\n" + string);
            return null;
        }
        Class<?> clazz = null;
        try {
            clazz = Class.forName(classObjectPairs[0]);
        } catch (ClassNotFoundException e) {
            //log.error("convert object error, not found class: " + classObjectPairs[0], e);
        }
        return JSON.parseObject(classObjectPairs[1], clazz);
    }

    public static Object toObject(byte[] bytes) throws ClassNotFoundException {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        return toObject(new String(bytes));
    }

}
