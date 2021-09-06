package com.ericmatelyan_schoolmobileapp.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.ericmatelyan_schoolmobileapp.Database.SchoolCalendarRepo;
import com.ericmatelyan_schoolmobileapp.Entity.CourseEntity;
import com.ericmatelyan_schoolmobileapp.Entity.TermEntity;
import com.ericmatelyan_schoolmobileapp.R;
import com.ericmatelyan_schoolmobileapp.Utility.DateConverter;

import java.util.Date;

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home_menu_item:
                Intent homeIntent = new Intent(this, MainActivity.class);
                startActivity(homeIntent);
                return true;

            case R.id.edit_menu_item:
                Intent editIntent = new Intent(this, EditCourseActivity.class);
                editIntent.putExtra("courseId", courseId);
                editIntent.putExtra("courseName", courseName);
                editIntent.putExtra("startDate", startDate);
                editIntent.putExtra("endDate", endDate);
                editIntent.putExtra("status", status);
                editIntent.putExtra("instName", instName);
                editIntent.putExtra("instPhone", instPhone);
                editIntent.putExtra("instEmail", instEmail);
                startActivity(editIntent);
                return true;

            case R.id.delete_menu_item:
                Date startDateClass = DateConverter.stringToDate(startDate);
                Date endDateClass = DateConverter.stringToDate(endDate);
                CourseEntity deleteCourse = new CourseEntity(
                        courseId,
                        courseName,
                        startDateClass,
                        endDateClass,
                        status,
                        instName,
                        instPhone,
                        instEmail);
                repository.delete(deleteCourse);
                Intent deleteIntent = new Intent(this, CoursesActivity.class);
                startActivity(deleteIntent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}