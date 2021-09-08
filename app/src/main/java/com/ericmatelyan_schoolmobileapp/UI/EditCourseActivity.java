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

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class EditCourseActivity extends AppCompatActivity {

    private static final String TAG = "EditCourseActivity";
    private Context context;
    private CourseEntity course;
    private int courseId;
    private String courseName;
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
    private EditText statusText;
    private EditText instNameText;
    private EditText instPhoneText;
    private EditText instEmailText;

    private SchoolCalendarRepo repository;
    private TermEntity assocTermEntity;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_course);
        context = EditCourseActivity.this;
        repository = new SchoolCalendarRepo(getApplication());
        course = (CourseEntity) getIntent().getSerializableExtra("course");
        courseName = course.getCourseName();
        //FIX THIS: course.getAssocTerm()     Entity Value
        assocTermEntity = repository.getAllTerms().get(0);
        startDateClass = course.getStartDate();
        endDateClass = course.getEndDate();
        startDate = DateConverter.dateToString(startDateClass);
        endDate = DateConverter.dateToString(endDateClass);
        //FIX THIS: Change to ENUM;
        status = course.getStatus();
        instName = course.getInstructorName();
        instPhone = course.getInstructorPhone();
        instEmail = course.getInstructorEmail();

        titleText = findViewById(R.id.course_edit_title_text);
        startText = findViewById(R.id.course_edit_start_text);
        endText = findViewById(R.id.course_edit_end_text);
//        FIX THIS: change to spinner;
        statusText = findViewById(R.id.course_edit_status_text);
        instNameText = findViewById(R.id.course_edit_inst_name_text);
        instPhoneText = findViewById(R.id.course_edit_inst_phone_text);
        instEmailText = findViewById(R.id.course_edit_inst_email_text);

        titleText.setText(courseName);
        startText.setText(startDate);
        endText.setText(endDate);
        //FIX THIS: change to spinner
        statusText.setText(status);
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

        List<TermEntity> allTerms = repository.getAllTerms();
        spinner = findViewById(R.id.course_edit_assoc_term_spinner);
        ArrayAdapter<TermEntity> adapter = new ArrayAdapter<TermEntity>(this,
                android.R.layout.simple_spinner_item, allTerms);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        if (assocTermEntity != null) {
            int spinnerPosition = adapter.getPosition(assocTermEntity);
            spinner.setSelection(spinnerPosition);
        }
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                TermEntity term = (TermEntity) parent.getSelectedItem();
//                displayUserData(term);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void edit_course_save(View view) {
        //FIX THIS: Make sure all fields are filled in.
        courseId = course.getCourseId();
        String title = titleText.getText().toString();
        //FIX THIS: Change assocTerm to entity
        String assocTerm = assocTermEntity.getTermName();
        Date startDate = startCalendar.getTime();
        Date endDate = endCalendar.getTime();
        //FIX THIS: change status to Enum
        status = statusText.getText().toString();
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