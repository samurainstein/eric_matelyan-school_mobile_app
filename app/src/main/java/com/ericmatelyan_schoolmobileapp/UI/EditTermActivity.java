package com.ericmatelyan_schoolmobileapp.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.ericmatelyan_schoolmobileapp.Database.SchoolCalendarRepo;
import com.ericmatelyan_schoolmobileapp.Entity.TermEntity;
import com.ericmatelyan_schoolmobileapp.R;
import com.ericmatelyan_schoolmobileapp.Utility.DateConverter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class EditTermActivity extends AppCompatActivity {

    private static final String TAG = "EditTermActivity";
    private Context context = EditTermActivity.this;
    private int termId;
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
    private Calendar startCalendar;
    private Calendar endCalendar;
    Date startDateClass;
    Date endDateClass;
    String dateFormat = "MM/dd/yy";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat, Locale.US);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_term);

        //Set Fields-----------------------
        termId = getIntent().getIntExtra("termId", -1);
        termName = getIntent().getStringExtra("termName");
        startDate = getIntent().getStringExtra("startDate");
        endDate = getIntent().getStringExtra("endDate");

        titleText = findViewById(R.id.term_edit_title_text);
        startText = findViewById(R.id.term_edit_start_text);
        endText = findViewById(R.id.term_edit_end_text);

        titleText.setText(termName);
        startText.setText(startDate);
        endText.setText(endDate);

        startDateClass = DateConverter.stringToDate(startDate);
        LocalDate startLocalDate = DateConverter.dateToLocalDate(startDateClass);

        endDateClass = DateConverter.stringToDate(endDate);
        LocalDate endLocalDate = DateConverter.dateToLocalDate(endDateClass);

        repository = new SchoolCalendarRepo(getApplication());

        //Start Date----------------
        displayStartDate = findViewById(R.id.term_edit_start_text);
        startCalendar = Calendar.getInstance();
        int startYear = startLocalDate.getYear();
        int startMonth = startLocalDate.getMonthValue() - 1;
        int startDay = startLocalDate.getDayOfMonth();
        startCalendar.set(Calendar.YEAR, startYear);
        startCalendar.set(Calendar.MONTH, startMonth);
        startCalendar.set(Calendar.DAY_OF_MONTH, startDay);
        startCalendar = DateConverter.onClickStartDate(context, displayStartDate, startCalendar);


        //End Date---------------------
        displayEndDate = findViewById(R.id.term_edit_end_text);
        endCalendar = Calendar.getInstance();
        int endYear = endLocalDate.getYear();
        int endMonth = endLocalDate.getMonthValue();
        int endDay = endLocalDate.getDayOfMonth();
        endCalendar.set(Calendar.YEAR, endYear);
        endCalendar.set(Calendar.MONTH, endMonth);
        endCalendar.set(Calendar.DAY_OF_MONTH, endDay);
        endCalendar = DateConverter.onClickEndDate(context, displayEndDate, endCalendar);
    }


    public void add_term_save(View view) {
        String title = titleText.getText().toString();
        Date startDate = startCalendar.getTime();
        Date endDate = endCalendar.getTime();
        TermEntity updateTerm = new TermEntity(termId, title, startDate, endDate);
        repository.update(updateTerm);

        Intent intent = new Intent(this, TermsDetailActivity.class);
        intent.putExtra("termId", termId);
        intent.putExtra("termName", title);
        intent.putExtra("startDate", simpleDateFormat.format(startDate));
        intent.putExtra("endDate", simpleDateFormat.format(endDate));
        startActivity(intent);
    }
}