package com.coreintegra.pftrust.util;

import org.json.JSONObject;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;

public class DataUtil {

    public static String avoidNull(Object o, String method){
        try {

            if(o == null) return "";

            Method method1 = o.getClass().getMethod(method);
            Object result = method1.invoke(o);

            if(result == null) return "";

            return result.toString();

        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return "";
    }


    public static BigDecimal avoidNull(BigDecimal bigDecimal){
        return bigDecimal == null ? BigDecimal.ZERO : bigDecimal;
    }

    public static Object avoidNull(JSONObject jsonObject, String key) {
        return jsonObject.get(key) == null ? null : jsonObject.getString(key);
    }

    public static String getString(JSONObject jsonObject, String key){

        if (checkForNull(jsonObject, key)) return null;

        return jsonObject.getString(key);
    }

    private static boolean checkForNull(JSONObject jsonObject, String key) {
        return !jsonObject.has(key) || jsonObject.get(key) == null || jsonObject.get(key).toString().equalsIgnoreCase("null");
    }

    public static BigDecimal getBigDecimal(JSONObject jsonObject, String key){

        if (checkForNull(jsonObject, key)) return null;

        return jsonObject.getBigDecimal(key);
    }

}
