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
import com.ericmatelyan_schoolmobileapp.Entity.CourseEntity;
import com.ericmatelyan_schoolmobileapp.Entity.TermEntity;
import com.ericmatelyan_schoolmobileapp.R;
import com.ericmatelyan_schoolmobileapp.Utility.IdManager;

import java.util.Date;
import java.util.List;

public class CoursesActivity extends AppCompatActivity {

    private SchoolCalendarRepo repository;
    private List<TermEntity> allTerms;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);
        context = CoursesActivity.this;

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
        allTerms = repository.getAllTerms();
        if(allTerms.isEmpty()) {
            CharSequence text = "Please create a term before creating a course";
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        else {
            Intent intent = new Intent(this, AddCourseActivity.class);
            startActivity(intent);
        }
    }
}