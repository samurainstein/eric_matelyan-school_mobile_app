package com.ericmatelyan_schoolmobileapp.Utility;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.room.TypeConverter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public abstract class DateConverter {

    private static String dateFormat = "MM/dd/yy";
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat, Locale.US);
    private static DatePickerDialog.OnDateSetListener startDateSetListener;
    private static DatePickerDialog.OnDateSetListener endDateSetListener;

    @TypeConverter
    public static Date toDate(Long timestamp) {
        return timestamp == null ? null : new Date(timestamp);
    }

    @TypeConverter
    public static Long toTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }

    public static void updateDateLabel(TextView dateLabel, Calendar calendar) {
        dateLabel.setText(simpleDateFormat.format(calendar.getTime()));
    }

    public static Calendar onClickStartDate(Context context, TextView dateText, Calendar calendar) {
        dateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        context,
                        android.R.style.Theme_Holo_Light_Dialog,
                        startDateSetListener,
                        year,
                        month,
                        day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        startDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, day);
                DateConverter.updateDateLabel(dateText, calendar);
            }
        };
        return calendar;
    }

    public static Calendar onClickEndDate(Context context, TextView dateText, Calendar calendar) {
        dateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        context,
                        android.R.style.Theme_Holo_Light_Dialog,
                        endDateSetListener,
                        year,
                        month,
                        day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        endDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, day);
                DateConverter.updateDateLabel(dateText, calendar);
            }
        };
        return calendar;
    }
}
