package com.coreintegra.pftrust.util;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateFormatterUtil {

    public static String formatDate(Date date){
        return format(date, "dd.MM.yyyy", "Asia/Kolkata");
    }

    public static String formatDateWS(Date date){
        return format(date, "dd/MM/yyyy", "Asia/Kolkata");
    }

    public static String formatDateR(Date date){
        return format(date, "dd-MM-yyyy", "Asia/Kolkata");
    }

    public static String formatDateR(Long timestamp){
        if(timestamp == null){
            return "";
        }
        return formatDateR(new Date(timestamp));
    }

    public static String formatDatePay(Date date){
        return format(date, "ddMMyyyy", "Asia/Kolkata");
    }

    public static String format(Date date, String format){

        if(date == null){
            return "";
        }

        SimpleDateFormat formatter = new SimpleDateFormat(format);
        formatter.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
        return formatter.format(date);
    }

    public static String format(Date date, String format, String timeZone){

        if(date == null){
            return "";
        }

        SimpleDateFormat formatter = new SimpleDateFormat(format);
        formatter.setTimeZone(TimeZone.getTimeZone(timeZone));
        return formatter.format(date);
    }

    public static LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public static Period period(Date date) {

        LocalDate localDate = DateFormatterUtil.convertToLocalDateViaInstant(date);
        LocalDate currentDate = DateFormatterUtil.convertToLocalDateViaInstant(new Date());

        return Period.between(localDate, currentDate);

    }

    public static String formateDateInWord(Date date){

        if(date == null){
            return null;
        }

        SimpleDateFormat formatter = new SimpleDateFormat("MMMMM yyyy", Locale.ENGLISH);
        formatter.setTimeZone(TimeZone.getTimeZone("IST"));
        String strDate= formatter.format(date);

        Calendar instance = Calendar.getInstance();

        instance.setTimeInMillis(date.getTime());

        return instance.get(Calendar.DATE) + getDayOfMonthSuffix(instance.get(Calendar.DATE)) + " " + strDate;
    }

    public static String getDayOfMonthSuffix(final int n) {
        if (n >= 11 && n <= 13) {
            return "th";
        }
        switch (n % 10) {
            case 1:  return "st";
            case 2:  return "nd";
            case 3:  return "rd";
            default: return "th";
        }
    }

    public static Period getPeriodBetweenDate(Date startDate, Date endDate){
        return Period.between(Instant.ofEpochMilli(startDate.getTime()).atZone(ZoneId.of("IST",  ZoneId.SHORT_IDS)).toLocalDate(),
                Instant.ofEpochMilli(endDate.getTime()).atZone(ZoneId.of("IST", ZoneId.SHORT_IDS)).toLocalDate());
    }

}
