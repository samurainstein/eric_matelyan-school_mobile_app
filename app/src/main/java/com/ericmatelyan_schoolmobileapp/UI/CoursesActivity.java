package com.ericmatelyan_schoolmobileapp.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ericmatelyan_schoolmobileapp.Database.SchoolCalendarRepo;
import com.ericmatelyan_schoolmobileapp.Entity.CourseEntity;
import com.ericmatelyan_schoolmobileapp.Entity.TermEntity;
import com.ericmatelyan_schoolmobileapp.R;
import com.ericmatelyan_schoolmobileapp.Utility.IdManager;

import java.util.Date;
import java.util.List;

public class CoursesActivity extends AppCompatActivity {

    private SchoolCalendarRepo repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        repository = new SchoolCalendarRepo(getApplication());
        List<CourseEntity> allCourses = repository.getAllCourses();

        RecyclerView recyclerView = findViewById(R.id.courses_recycler);
        final CourseAdapter courseAdapter = new CourseAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(courseAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        courseAdapter.setCourses(allCourses);
    }

    public void addCourse(View view) {
        Intent intent = new Intent(this, AddCourseActivity.class);
        startActivity(intent);

    }
}