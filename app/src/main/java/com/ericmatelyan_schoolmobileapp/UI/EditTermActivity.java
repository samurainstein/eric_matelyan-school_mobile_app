package com.ericmatelyan_schoolmobileapp.UI;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ericmatelyan_schoolmobileapp.Database.SchoolCalendarRepo;
import com.ericmatelyan_schoolmobileapp.Entity.TermEntity;
import com.ericmatelyan_schoolmobileapp.R;
import com.ericmatelyan_schoolmobileapp.Utility.DateConverter;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class EditTermActivity extends AppCompatActivity {

    private static final String TAG = "EditTermActivity";
    private Context context = EditTermActivity.this;
    private TermEntity term;
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
    private Calendar startCalendar;
    private Calendar endCalendar;
    Date startDateClass;
    Date endDateClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_term);
        //This fixes the issue where the app crashes when hitting the back button on the edit screens
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //Set Fields-----------------------
        term = (TermEntity) getIntent().getSerializableExtra("term");
        termId = term.getTermId();
        termName = term.getTermName();
        startDateClass = term.getStartDate();
        endDateClass = term.getEndDate();
        startDate = DateConverter.dateToString(startDateClass);
        endDate = DateConverter.dateToString(endDateClass);

        titleText = findViewById(R.id.term_edit_title_text);
        startText = findViewById(R.id.term_edit_start_text);
        endText = findViewById(R.id.term_edit_end_text);

        titleText.setText(termName);
        startText.setText(startDate);
        endText.setText(endDate);


        LocalDate startLocalDate = DateConverter.dateToLocalDate(startDateClass);
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
        int endMonth = endLocalDate.getMonthValue() - 1;
        int endDay = endLocalDate.getDayOfMonth();
        endCalendar.set(Calendar.YEAR, endYear);
        endCalendar.set(Calendar.MONTH, endMonth);
        endCalendar.set(Calendar.DAY_OF_MONTH, endDay);
        endCalendar = DateConverter.onClickEndDate(context, displayEndDate, endCalendar);
    }

    //This fixes the issue where the app crashes when hitting the back button on the edit screens
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return true;
    }


    public void add_term_save(View view) {
        String title = titleText.getText().toString();
        Date startDate = startCalendar.getTime();
        Date endDate = endCalendar.getTime();
        if(title.isEmpty()) {
            Context context = getApplicationContext();
            CharSequence text = "Please fill in all required fields";
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

        else {
            TermEntity updateTerm = new TermEntity(termId, title, startDate, endDate);
            repository.update(updateTerm);

            Intent intent = new Intent(this, TermDetailsActivity.class);
            intent.putExtra("term", updateTerm);
            startActivity(intent);
        }
    }

    //To make the soft keyboard disappear when clicking outside of EditText
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if ( v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
                    Log.d("focus", "touchevent");
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent(event);
    }
}