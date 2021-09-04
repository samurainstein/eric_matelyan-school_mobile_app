package com.ericmatelyan_schoolmobileapp.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.ericmatelyan_schoolmobileapp.Database.SchoolCalendarRepo;
import com.ericmatelyan_schoolmobileapp.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class EditTermActivity extends AppCompatActivity {

    private static final String TAG = "EditTermActivity";
    private String termName;
    private String startDate;
    private String endDate;
    private EditText titleText;
    private TextView startText;
    private TextView endText;
    SchoolCalendarRepo repository;
    private TextView displayStartDate;
    private TextView displayEndDate;
    private DatePickerDialog.OnDateSetListener startDateSetListener;
    private DatePickerDialog.OnDateSetListener endDateSetListener;
    Calendar startCalendar = Calendar.getInstance();
    Calendar endCalendar = Calendar.getInstance();
    Date startDateClass;
    Date endDateClass;
    String dateFormat = "MM/dd/yy";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat, Locale.US);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_term);

        termName = getIntent().getStringExtra("termName");
        startDate = getIntent().getStringExtra("startDate");
        endDate = getIntent().getStringExtra("endDate");

        titleText = findViewById(R.id.term_edit_title_text);
        startText = findViewById(R.id.term_edit_start_text);
        endText = findViewById(R.id.term_edit_end_text);

        titleText.setText(termName);
        startText.setText(startDate);
        endText.setText(endDate);

        try {
            startDateClass = simpleDateFormat.parse(startDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        LocalDate startLocalDate = startDateClass.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        try {
            endDateClass = simpleDateFormat.parse(startDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        LocalDate endLocalDate = endDateClass.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        repository = new SchoolCalendarRepo(getApplication());

        //Start Date----------------
        displayStartDate = findViewById(R.id.term_edit_start_text);
        displayStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                int year = startCalendar.get(Calendar.YEAR);
//                int month = startCalendar.get(Calendar.MONTH);
//                int day = startCalendar.get(Calendar.DAY_OF_MONTH);
//                int year = startEditDate.getYear();
//                int month = startEditDate.getMonth();
//                int day = startEditDate.getDay();
                int year = startLocalDate.getYear();
                int month = startLocalDate.getMonthValue();
                int day = startLocalDate.getDayOfMonth();

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        EditTermActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog,
                        startDateSetListener,
                        year,
                        month-1,
                        day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        startDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                startCalendar.set(Calendar.YEAR, year);
                startCalendar.set(Calendar.MONTH, month);
                startCalendar.set(Calendar.DAY_OF_MONTH, day);
                updateStartDateLabel();
            }
        };

        //End Date---------------------
        displayEndDate = findViewById(R.id.term_edit_end_text);
        displayEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                int year = endCalendar.get(Calendar.YEAR);
//                int month = endCalendar.get(Calendar.MONTH);
//                int day = endCalendar.get(Calendar.DAY_OF_MONTH);
                int year = endLocalDate.getYear();
                int month = endLocalDate.getMonthValue();
                int day = endLocalDate.getDayOfMonth();

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        EditTermActivity.this,
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
                endCalendar.set(Calendar.YEAR, year);
                endCalendar.set(Calendar.MONTH, month);
                endCalendar.set(Calendar.DAY_OF_MONTH, day);
                updateEndDateLabel();
            }
        };
    }

    private void updateStartDateLabel() {
        displayStartDate.setText(simpleDateFormat.format(startCalendar.getTime()));
    }

    private void updateEndDateLabel() {
        displayEndDate.setText(simpleDateFormat.format(endCalendar.getTime()));
    }

    public void add_term_save(View view) {
    }
}