package com.ericmatelyan_schoolmobileapp.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ericmatelyan_schoolmobileapp.Database.SchoolCalendarRepo;
import com.ericmatelyan_schoolmobileapp.Entity.AssignmentEntity;
import com.ericmatelyan_schoolmobileapp.R;

import java.util.List;

public class AssignmentsActivity extends AppCompatActivity {

    private SchoolCalendarRepo repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignments);

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
        Intent intent = new Intent(this, AddAssignmentActivity.class);
        startActivity(intent);

    }
}