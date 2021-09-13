package com.ericmatelyan_schoolmobileapp.UI;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ericmatelyan_schoolmobileapp.Database.SchoolCalendarRepo;
import com.ericmatelyan_schoolmobileapp.Entity.AssignmentEntity;
import com.ericmatelyan_schoolmobileapp.Entity.CourseEntity;
import com.ericmatelyan_schoolmobileapp.R;
import com.ericmatelyan_schoolmobileapp.Utility.AlertManager;
import com.ericmatelyan_schoolmobileapp.Utility.DateConverter;

import java.util.Date;
import java.util.List;

public class CourseDetailsActivity extends AppCompatActivity {

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
    private String assocTerm;
    private String notes;

    private TextView titleText;
    private TextView startText;
    private TextView endText;
    private TextView statusText;
    private TextView instNameText;
    private TextView instPhoneText;
    private TextView instEmailText;
    private TextView assocTermText;
    private TextView notesText;

    private SchoolCalendarRepo repository;
    private List<AssignmentEntity> assocAssignments;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details);
        context = CourseDetailsActivity.this;

        repository = new SchoolCalendarRepo(getApplication());
        course = (CourseEntity) getIntent().getSerializableExtra("course");

        courseName = course.getCourseName();
        startDateClass = course.getStartDate();
        endDateClass = course.getEndDate();
        status = course.getStatus();
        instName = course.getInstructorName();
        instPhone = course.getInstructorPhone();
        instEmail = course.getInstructorEmail();
        assocTerm = course.getAssocTerm();
        notes = course.getNotes();

        startDate = DateConverter.dateToString(startDateClass);
        endDate = DateConverter.dateToString(endDateClass);

        titleText = findViewById(R.id.course_details_title_text);
        startText = findViewById(R.id.course_details_start_text);
        endText = findViewById(R.id.course_details_end_text);
        statusText = findViewById(R.id.course_details_status_text);
        instNameText = findViewById(R.id.course_details_inst_name_text);
        instPhoneText = findViewById(R.id.course_details_inst_phone_text);
        instEmailText = findViewById(R.id.course_details_inst_email_text);
        assocTermText = findViewById(R.id.course_details_assoc_term_text);
        notesText = findViewById(R.id.course_details_notes_text);

        titleText.setText(courseName);
        startText.setText(startDate);
        endText.setText(endDate);
        statusText.setText(status);
        instNameText.setText(instName);
        instPhoneText.setText(instPhone);
        instEmailText.setText(instEmail);
        assocTermText.setText(assocTerm);
        notesText.setText(notes);

        assocAssignments = repository.getAssociatedAssignments(courseName);

        RecyclerView recyclerView = findViewById(R.id.assoc_assignments_recycler);
        final AssignmentAdapter assignmentAdapter = new AssignmentAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(assignmentAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        assignmentAdapter.setAssignments(assocAssignments);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.course_details_menu, menu);
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
                editIntent.putExtra("course", course);
                startActivity(editIntent);
                return true;

            case R.id.delete_menu_item:
                repository.delete(course);
                Intent deleteIntent = new Intent(this, CoursesActivity.class);
                startActivity(deleteIntent);
                return true;

            case R.id.share_menu_item:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, notes);
                sendIntent.putExtra(Intent.EXTRA_TITLE, "Notes");
                sendIntent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);
                return true;

            case R.id.notify_menu_item:
                Long startTrigger = startDateClass.getTime();
                Long endTrigger = endDateClass.getTime();
                String startMessage = courseName + " starts on " + startDate;
                String endMessage = courseName + " ends on " + endDate;
                String startTitle = "Course Start";
                String endTitle = "Course End";
                AlertManager.createAlert(context, startTrigger, startTitle, startMessage);
                AlertManager.createAlert(context, endTrigger, endTitle, endMessage);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}