package com.tikal.themoviedb.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Yarden Rosenberg on 08/03/2016.
 */
public class DateFormatter {

    public static final String TMDB_DATE_PATTERN = "yyyy-MM-dd";
    private static final SimpleDateFormat sTmbdDateFormat = new SimpleDateFormat(TMDB_DATE_PATTERN);

    public static String getYear(String TmbdDateStr){
        String year = "";
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(sTmbdDateFormat.parse(TmbdDateStr));
            year = "" + calendar.get(Calendar.YEAR);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return year;
    }
}
