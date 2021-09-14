package com.ericmatelyan_schoolmobileapp.UI;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ericmatelyan_schoolmobileapp.Database.SchoolCalendarRepo;
import com.ericmatelyan_schoolmobileapp.Entity.AssignmentEntity;
import com.ericmatelyan_schoolmobileapp.Entity.CourseEntity;
import com.ericmatelyan_schoolmobileapp.R;

import java.util.List;

public class AssignmentsActivity extends AppCompatActivity {

    private SchoolCalendarRepo repository;
    Context context;
    private List<CourseEntity> allCourses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignments);
        context = AssignmentsActivity.this;

        repository = new SchoolCalendarRepo(getApplication());
        List<AssignmentEntity> allAssignments = repository.getAllAssignments();


        RecyclerView recyclerView = findViewById(R.id.assignments_recycler);
        final AssignmentAdapter assignmentAdapter = new AssignmentAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(assignmentAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        assignmentAdapter.setAssignments(allAssignments);
    }

    public void addAssignment(View view) {
        allCourses = repository.getAllCourses();
        if(allCourses.isEmpty()) {
            CharSequence text = "Please create a course before creating an assignment";
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

        else {
            Intent intent = new Intent(this, AddAssignmentActivity.class);
            startActivity(intent);
        }
    }
}