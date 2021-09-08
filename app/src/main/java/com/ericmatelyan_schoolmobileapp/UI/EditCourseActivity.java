package com.ericmatelyan_schoolmobileapp.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.ericmatelyan_schoolmobileapp.Database.SchoolCalendarRepo;
import com.ericmatelyan_schoolmobileapp.Entity.CourseEntity;
import com.ericmatelyan_schoolmobileapp.Entity.TermEntity;
import com.ericmatelyan_schoolmobileapp.R;
import com.ericmatelyan_schoolmobileapp.Utility.DateConverter;
import com.ericmatelyan_schoolmobileapp.Utility.SpinnerManager;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class EditCourseActivity extends AppCompatActivity {

    private static final String TAG = "EditCourseActivity";
    private Context context;
    private CourseEntity course;
    private int courseId;
    private String courseName;
    private String assocTerm;
    private String startDate;
    private String endDate;
    private Date startDateClass;
    private Date endDateClass;
    private String status;
    private String instName;
    private String instPhone;
    private String instEmail;

    private EditText titleText;
    private TextView startText;
    private TextView endText;
    private TextView displayStartDate;
    private TextView displayEndDate;
    private Calendar startCalendar;
    private Calendar endCalendar;
    private Spinner assocTermSpinner;
    private Spinner statusSpinner;
    private EditText statusText;
    private EditText instNameText;
    private EditText instPhoneText;
    private EditText instEmailText;

    private SchoolCalendarRepo repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_course);
        context = EditCourseActivity.this;
        repository = new SchoolCalendarRepo(getApplication());
        course = (CourseEntity) getIntent().getSerializableExtra("course");
        courseName = course.getCourseName();
        assocTerm = course.getAssocTerm();
        startDateClass = course.getStartDate();
        endDateClass = course.getEndDate();
        startDate = DateConverter.dateToString(startDateClass);
        endDate = DateConverter.dateToString(endDateClass);
        status = course.getStatus();
        instName = course.getInstructorName();
        instPhone = course.getInstructorPhone();
        instEmail = course.getInstructorEmail();

        titleText = findViewById(R.id.course_edit_title_text);
        startText = findViewById(R.id.course_edit_start_text);
        endText = findViewById(R.id.course_edit_end_text);
        instNameText = findViewById(R.id.course_edit_inst_name_text);
        instPhoneText = findViewById(R.id.course_edit_inst_phone_text);
        instEmailText = findViewById(R.id.course_edit_inst_email_text);

        titleText.setText(courseName);
        startText.setText(startDate);
        endText.setText(endDate);
        instNameText.setText(instName);
        instPhoneText.setText(instPhone);
        instEmailText.setText(instEmail);

        //Start Date----------------
        LocalDate startLocalDate = DateConverter.dateToLocalDate(startDateClass);
        displayStartDate = findViewById(R.id.course_edit_start_text);
        startCalendar = Calendar.getInstance();
        int startYear = startLocalDate.getYear();
        int startMonth = startLocalDate.getMonthValue() - 1;
        int startDay = startLocalDate.getDayOfMonth();
        startCalendar.set(Calendar.YEAR, startYear);
        startCalendar.set(Calendar.MONTH, startMonth);
        startCalendar.set(Calendar.DAY_OF_MONTH, startDay);
        startCalendar = DateConverter.onClickStartDate(context, displayStartDate, startCalendar);


        //End Date---------------------
        LocalDate endLocalDate = DateConverter.dateToLocalDate(endDateClass);
        displayEndDate = findViewById(R.id.course_edit_end_text);
        endCalendar = Calendar.getInstance();
        int endYear = endLocalDate.getYear();
        int endMonth = endLocalDate.getMonthValue() - 1;
        int endDay = endLocalDate.getDayOfMonth();
        endCalendar.set(Calendar.YEAR, endYear);
        endCalendar.set(Calendar.MONTH, endMonth);
        endCalendar.set(Calendar.DAY_OF_MONTH, endDay);
        endCalendar = DateConverter.onClickEndDate(context, displayEndDate, endCalendar);

        //Spinners---------------
        assocTermSpinner = findViewById(R.id.course_edit_assoc_term_spinner);
        statusSpinner = findViewById(R.id.course_edit_status_spinner);
        List<String> statusStrings = Arrays.asList(getResources().getStringArray(R.array.status_array));
        List<TermEntity> allTerms = repository.getAllTerms();
        List<String> allTermsStrings = new ArrayList<>();
        for(TermEntity term : allTerms) {
            allTermsStrings.add(term.getTermName());
        }
        ArrayAdapter<String> termArrayAdapter = SpinnerManager.setSpinnerItems(context, assocTermSpinner, allTermsStrings);
        SpinnerManager.setSpinnerSelection(assocTermSpinner, assocTerm, termArrayAdapter);
        ArrayAdapter<String> statusArrayAdapter = SpinnerManager.setSpinnerItems(context, statusSpinner, statusStrings);
        SpinnerManager.setSpinnerSelection(statusSpinner, status, statusArrayAdapter);

    }

    public void edit_course_save(View view) {
        //FIX THIS: Make sure all fields are filled in.
        courseId = course.getCourseId();
        String title = titleText.getText().toString();
        assocTerm = assocTermSpinner.toString();
        Date startDate = startCalendar.getTime();
        Date endDate = endCalendar.getTime();
        status = statusSpinner.toString();
        instName = instNameText.getText().toString();
        instPhone = instPhoneText.getText().toString();
        instEmail = instEmailText.getText().toString();

        CourseEntity updateCourse = new CourseEntity(courseId,
                title,
                startDate,
                endDate,
                status,
                instName,
                instPhone,
                instEmail,
                assocTerm);
        repository.update(updateCourse);

        Intent intent = new Intent(this, CourseDetailsActivity.class);
        intent.putExtra("course", updateCourse);
        startActivity(intent);
    }
}