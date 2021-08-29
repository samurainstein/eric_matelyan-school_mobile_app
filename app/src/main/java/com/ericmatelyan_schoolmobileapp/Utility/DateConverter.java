package com.ericmatelyan_schoolmobileapp.Utility;

import androidx.room.TypeConverter;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public abstract class DateConverter {
//    private static String pattern = "dd-MM-yyyy";
//    private static SimpleDateFormat formatter = new SimpleDateFormat(pattern);
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static LocalDate localDate;
    private static String stringDate;

    @TypeConverter
    public static LocalDate stringToDate(String date) {
        if(date == null)
            localDate = null;
        else if(date != null) {
            localDate = LocalDate.parse(date);

        }
        return localDate;
    }

    @TypeConverter
    public static String dateToString(LocalDate date) {
        if(date == null)
            stringDate = null;
        else if(date != null) {
            stringDate = date.format(formatter);
        }
        return stringDate;
    }
}
