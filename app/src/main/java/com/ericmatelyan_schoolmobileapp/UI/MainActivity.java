package com.ericmatelyan_schoolmobileapp.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.ericmatelyan_schoolmobileapp.Database.SchoolCalendarRepo;
import com.ericmatelyan_schoolmobileapp.Entity.AssignmentEntity;
import com.ericmatelyan_schoolmobileapp.Entity.CourseEntity;
import com.ericmatelyan_schoolmobileapp.Entity.TermEntity;
import com.ericmatelyan_schoolmobileapp.R;
import com.ericmatelyan_schoolmobileapp.Utility.IdManager;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SchoolCalendarRepo repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        repository = new SchoolCalendarRepo(getApplication());
        List<TermEntity> allTerms = repository.getAllTerms();
        List<CourseEntity> allCourses = repository.getAllCourses();
        List<AssignmentEntity> allAssignments = repository.getAllAssignments();

        if(allTerms.size()==0) {
            TermEntity term = new TermEntity(IdManager.getNextTermId(),
                    "Test Term 1",
                    new Date(),
                    new Date());
            repository.insert(term);
        }

        if(allCourses.size()==0) {
            CourseEntity course = new CourseEntity(IdManager.getNextCourseId(),
                    "Test Course 1",
                    new Date(),
                    new Date(),
                    "In Progress",
                    "Instructor Name",
                    "Instructor Phone",
                    "Instructor Email",
                    "Test Term 1",
                    "Test notes");
            repository.insert(course);
        }

        if(allAssignments.size()==0) {
            AssignmentEntity assignment = new AssignmentEntity(
                    IdManager.getNextAssignmentId(),
                    "Test Assignment 1",
                    "Test Course 1",
                    "Performance Assessment",
                    new Date(),
                    new Date());
            repository.insert(assignment);
        }
    }

    public void termsButton(View view) {
        Intent intent = new Intent(this, TermsActivity.class);
        startActivity(intent);
    }

    public void coursesButton(View view) {
        Intent intent = new Intent(this, CoursesActivity.class);
        startActivity(intent);
    }

    public void assignmentsButton(View view) {
        Intent intent = new Intent(this, AssignmentsActivity.class);
        startActivity(intent);
    }
}