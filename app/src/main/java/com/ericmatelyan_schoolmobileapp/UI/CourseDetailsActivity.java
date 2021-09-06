package com.ericmatelyan_schoolmobileapp.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import com.ericmatelyan_schoolmobileapp.Database.SchoolCalendarRepo;
import com.ericmatelyan_schoolmobileapp.R;

public class CourseDetailsActivity extends AppCompatActivity {

    private int courseId;
    private String courseName;
    private String startDate;
    private String endDate;
    private String status;
    private String instName;
    private String instPhone;
    private String instEmail;

    private TextView titleText;
    private TextView startText;
    private TextView endText;
    private TextView statusText;
    private TextView instNameText;
    private TextView instPhoneText;
    private TextView instEmailText;

    private SchoolCalendarRepo repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details);

        repository = new SchoolCalendarRepo(getApplication());

        courseId = getIntent().getIntExtra("courseId", -1);
        courseName = getIntent().getStringExtra("courseName");
        startDate = getIntent().getStringExtra("startDate");
        endDate = getIntent().getStringExtra("endDate");
        status = getIntent().getStringExtra("status");
        instName = getIntent().getStringExtra("instName");
        instPhone = getIntent().getStringExtra("instPhone");
        instEmail = getIntent().getStringExtra("instEmail");

        titleText = findViewById(R.id.course_details_title_text);
        startText = findViewById(R.id.course_details_start_text);
        endText = findViewById(R.id.course_details_end_text);
        statusText = findViewById(R.id.course_details_status_text);
        instNameText = findViewById(R.id.course_details_inst_name_text);
        instPhoneText = findViewById(R.id.course_details_inst_phone_text);
        instEmailText = findViewById(R.id.course_details_inst_email_text);


        titleText.setText(courseName);
        startText.setText(startDate);
        endText.setText(endDate);
        statusText.setText(status);
        instNameText.setText(instName);
        instPhoneText.setText(instPhone);
        instEmailText.setText(instEmail);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.editmenu, menu);
        return true;
    }
}