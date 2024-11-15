package com.coreintegra.pftrust.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class NumberFormatter {

    public static String formatNumbers(BigDecimal bigDecimal){

        if (bigDecimal == null || bigDecimal.doubleValue() <= 1){
            return "0.00";
        }

        NumberFormat formatter = new DecimalFormat("##,##,###.00");
        return formatter.format(bigDecimal);
    }

    public static String formatNumbers(Float value){

        if(value == null) value = 0f;

        String s = value.toString();

        return s + "0";

    }

}
