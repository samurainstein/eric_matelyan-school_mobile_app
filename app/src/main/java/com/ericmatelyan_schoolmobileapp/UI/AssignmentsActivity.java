package com.ericmatelyan_schoolmobileapp.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ericmatelyan_schoolmobileapp.Database.SchoolCalendarRepo;
import com.ericmatelyan_schoolmobileapp.Entity.AssignmentEntity;
import com.ericmatelyan_schoolmobileapp.Entity.CourseEntity;
import com.ericmatelyan_schoolmobileapp.R;
import com.ericmatelyan_schoolmobileapp.Utility.IdManager;

import java.util.Date;
import java.util.List;

public class AssignmentsActivity extends AppCompatActivity {

    private SchoolCalendarRepo repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        repository = new SchoolCalendarRepo(getApplication());
        List<AssignmentEntity> allAssignments = repository.getAllAssignments();


        RecyclerView recyclerView = findViewById(R.id.courses_recycler);
        final AssignmentAdapter assignmentAdapter = new AssignmentAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(assignmentAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        assignmentAdapter.setAssignments(allAssignments);
    }

    public void addAssignment(View view) {
        Intent intent = new Intent(this, AddCourseActivity.class);
        startActivity(intent);

    }
}