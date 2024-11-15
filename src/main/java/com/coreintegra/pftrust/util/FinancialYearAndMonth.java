package com.coreintegra.pftrust.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class FinancialYearAndMonth {

    private final static Logger logger = LoggerFactory.getLogger(FinancialYearAndMonth.class);

    public static Date getDate(String date) throws ParseException {
        return getDate(date, "yyyy-MM-dd");
    }

    public static Date getDate(String date, String dateFormat) throws ParseException {

        if(date == null || date.equals("null") || date.trim().isEmpty()) {
            return null;
        }else {
            DateFormat format = new SimpleDateFormat(dateFormat, Locale.ENGLISH);
            format.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
            return format.parse(date);
        }

    }

    public static int getFinancialYear(Date date) {

        Calendar dateDetails = getDateDetails(date);

        int year = dateDetails.get(Calendar.YEAR);

        int month = dateDetails.get(Calendar.MONTH);

        return (month + 1) <= 3 ? year : (year+1);

    }

    public static Calendar getDateDetails(Date date) {

        Calendar cal = Calendar.getInstance();

        cal.setTime(date);

        return cal;

    }


    public static Integer getFinancialMonth(Date date) {

        Calendar dateDetails = getDateDetails(date);

        int fmonth;

        if((dateDetails.get(Calendar.MONTH) + 1) < 4) {
            fmonth = (dateDetails.get(Calendar.MONTH) + 1)  + 9;
        }
        else {
            fmonth = (dateDetails.get(Calendar.MONTH) + 1)  - 3;
        }

        return fmonth;
    }



    public static Date getStandardRecoveryDate(Integer financialYear, Integer financialMonth) {

        Integer month = getMonthBasedOnFinancialMonth(financialMonth);

        Integer year = getYearBasedOnFinancialYear(financialYear, financialMonth);

        Calendar instance = Calendar.getInstance();

        instance.set(Calendar.YEAR, year);
        instance.set(Calendar.MONTH, month);
        instance.set(Calendar.DAY_OF_MONTH, 1);
        instance.add(Calendar.DATE, -1);

        return instance.getTime();
    }


    public static Integer getMonthBasedOnFinancialMonth(Integer financialMonth){
        if(financialMonth >= 10) {
            return financialMonth - 9;
        }
        else {
            return financialMonth + 3;
        }
    }

    public static Integer getYearBasedOnFinancialYear(Integer financialYear, Integer financialMonth){
        if(financialMonth >= 10) {
            return financialYear;
        }
        else {
            return financialYear-1;
        }
    }

    public static Date getYearOpeningFromFY(Integer year) throws ParseException {

        String d = (year-1) + "-" + "04-01";

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        format.setTimeZone(TimeZone.getTimeZone("IST"));
        return format.parse(d);

    }

    public static Date getClosingDate(Integer year) throws ParseException {


        if(year == getFinancialYear(new Date())){
            return lastDateOfMonth();
        }else {


            String d = (year) + "-" + "03-31";

            DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            format.setTimeZone(TimeZone.getTimeZone("IST"));
            return format.parse(d);

        }

    }

    public static Date lastDateOfMonth(){

        Date today = new Date();


        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);


        calendar.set(Calendar.DAY_OF_MONTH, 1);

        calendar.add(Calendar.DATE, -1);

        return calendar.getTime();
    }

    public static Date getYearOpeningFromDate(Date date) throws ParseException {

        Calendar dateDetails = getDateDetails(date);

        int year = dateDetails.get(Calendar.YEAR);

        int month = dateDetails.get(Calendar.MONTH);

        String d = ((month + 1) > 3 ? year : (year-1)) + "-" + "04-01";

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        format.setTimeZone(TimeZone.getTimeZone("IST"));

        return format.parse(d);

    }

    public static Long getDateDiff(Date date) {

        java.util.Date endDate = new java.util.Date();

        long duration = endDate.getTime() - date.getTime();

        return TimeUnit.MILLISECONDS.toDays(duration);

    }

}
