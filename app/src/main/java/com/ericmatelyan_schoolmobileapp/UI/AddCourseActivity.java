package com.ericmatelyan_schoolmobileapp.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ericmatelyan_schoolmobileapp.Database.SchoolCalendarRepo;
import com.ericmatelyan_schoolmobileapp.Entity.CourseEntity;
import com.ericmatelyan_schoolmobileapp.Entity.TermEntity;
import com.ericmatelyan_schoolmobileapp.R;
import com.ericmatelyan_schoolmobileapp.Utility.DateConverter;
import com.ericmatelyan_schoolmobileapp.Utility.IdManager;
import com.ericmatelyan_schoolmobileapp.Utility.SpinnerManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AddCourseActivity extends AppCompatActivity {

    private static final String TAG = "AddCourseActivity";
    private Context context;
    private int courseId;
    private String courseName;
    private String assocTerm;
    private Date startDateClass;
    private Date endDateClass;
    private String status;
    private String instName;
    private String instPhone;
    private String instEmail;
    private String notes;

    private EditText titleText;
    private TextView startText;
    private TextView endText;
    private Calendar startCalendar;
    private Calendar endCalendar;
    private Spinner assocTermSpinner;
    private Spinner statusSpinner;
    private EditText instNameText;
    private EditText instPhoneText;
    private EditText instEmailText;
    private EditText notesText;

    private SchoolCalendarRepo repository;
    private List<CourseEntity> allCourses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        context = AddCourseActivity.this;
        repository = new SchoolCalendarRepo(getApplication());
        allCourses = repository.getAllCourses();
        if(!allCourses.isEmpty()) {
            IdManager.setNextCourseId(repository);
        }

        titleText = findViewById(R.id.course_add_title_text);
        startText = findViewById(R.id.course_add_start_text);
        endText = findViewById(R.id.course_add_end_text);
        instNameText = findViewById(R.id.course_add_inst_name_text);
        instPhoneText = findViewById(R.id.course_add_inst_phone_text);
        instEmailText = findViewById(R.id.course_add_inst_email_text);
        notesText = findViewById(R.id.course_add_notes_text);



        //Start Date----------------
        startCalendar = Calendar.getInstance();
        startCalendar = DateConverter.onClickStartDate(context, startText, startCalendar);

        //End Date---------------------
        endCalendar = Calendar.getInstance();
        endCalendar = DateConverter.onClickEndDate(context, endText, endCalendar);

        //Spinners---------------
        assocTermSpinner = findViewById(R.id.course_add_assoc_term_spinner);
        statusSpinner = findViewById(R.id.course_add_status_spinner);
        List<String> statusStrings = Arrays.asList(getResources().getStringArray(R.array.status_array));
        List<TermEntity> allTerms = repository.getAllTerms();
        List<String> allTermsStrings = new ArrayList<>();
        for(TermEntity term : allTerms) {
            allTermsStrings.add(term.getTermName());
        }
        SpinnerManager.setSpinnerItems(context, assocTermSpinner, allTermsStrings);
        SpinnerManager.setSpinnerItems(context, statusSpinner, statusStrings);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home_menu_item:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void edit_course_save(View view) {
        courseId = IdManager.getNextCourseId();
        courseName = titleText.getText().toString();
        startDateClass = startCalendar.getTime();
        endDateClass = endCalendar.getTime();
        status = statusSpinner.getSelectedItem().toString();
        instName = instNameText.getText().toString();
        instPhone = instPhoneText.getText().toString();
        instEmail = instEmailText.getText().toString();
        assocTerm = assocTermSpinner.getSelectedItem().toString();
        notes = notesText.getText().toString();

        if(courseName.isEmpty() || instName.isEmpty() || instPhone.isEmpty() || instEmail.isEmpty()) {
            Context context = getApplicationContext();
            CharSequence text = "Please fill in all required fields";
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

        else {
            CourseEntity newCourse = new CourseEntity(
                    courseId,
                    courseName,
                    startDateClass,
                    endDateClass,
                    status,
                    instName,
                    instPhone,
                    instEmail,
                    assocTerm,
                    notes);
            repository.insert(newCourse);

            Intent intent = new Intent(this, CoursesActivity.class);
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