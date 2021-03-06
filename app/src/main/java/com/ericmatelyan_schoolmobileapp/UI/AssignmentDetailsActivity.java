package com.ericmatelyan_schoolmobileapp.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.ericmatelyan_schoolmobileapp.Database.SchoolCalendarRepo;
import com.ericmatelyan_schoolmobileapp.Entity.AssignmentEntity;
import com.ericmatelyan_schoolmobileapp.R;
import com.ericmatelyan_schoolmobileapp.Utility.AlertManager;
import com.ericmatelyan_schoolmobileapp.Utility.DateConverter;

import java.util.Date;

public class AssignmentDetailsActivity extends AppCompatActivity {

    private AssignmentEntity assignment;
    private String assignmentName;
    private String assocCourse;
    private String assignmentType;
    private Date startDateClass;
    private Date endDateClass;
    private String startDate;
    private String endDate;

    private TextView titleText;
    private TextView assocCourseText;
    private TextView typeText;
    private TextView startText;
    private TextView endText;
    private SchoolCalendarRepo repository;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment_details);
        context = AssignmentDetailsActivity.this;

        repository = new SchoolCalendarRepo(getApplication());

        assignment = (AssignmentEntity) getIntent().getSerializableExtra("assignment");
        assignmentName = assignment.getAssignmentName();
        assocCourse = assignment.getAssocCourse();
        assignmentType = assignment.getAssignmentType();
        startDateClass = assignment.getStartDate();
        endDateClass = assignment.getEndDate();
        startDate = DateConverter.dateToString(startDateClass);
        endDate = DateConverter.dateToString(endDateClass);

        titleText = findViewById(R.id.assignment_details_title_text);
        assocCourseText = findViewById(R.id.assignment_details_course_text);
        typeText = findViewById(R.id.assignment_details_type_text);
        startText = findViewById(R.id.assignment_details_start_text);
        endText = findViewById(R.id.assignment_details_end_text);

        titleText.setText(assignmentName);
        assocCourseText.setText(assocCourse);
        typeText.setText(assignmentType);
        startText.setText(startDate);
        endText.setText(endDate);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.assignment_details_menu, menu);
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
                Intent editIntent = new Intent(this, EditAssignmentActivity.class);
                editIntent.putExtra("assignment", assignment);
                startActivity(editIntent);
                return true;

            case R.id.delete_menu_item:
                repository.delete(assignment);
                Intent deleteIntent = new Intent(this, AssignmentsActivity.class);
                startActivity(deleteIntent);
                return true;

            case R.id.notify_menu_item:
                Long startTrigger = startDateClass.getTime();
                Long endTrigger = endDateClass.getTime();
                String startMessage = assignmentName + " starts on " + startDate;
                String endMessage = assignmentName + " ends on " + endDate;
                String startTitle = "Assignment Start";
                String endTitle = "Assignment End";
                AlertManager.createAlert(context, startTrigger, startTitle, startMessage);
                AlertManager.createAlert(context, endTrigger, endTitle, endMessage);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}