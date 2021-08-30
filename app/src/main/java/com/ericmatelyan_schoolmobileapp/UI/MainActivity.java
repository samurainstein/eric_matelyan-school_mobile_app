package com.ericmatelyan_schoolmobileapp.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.ericmatelyan_schoolmobileapp.Database.SchoolCalendarRepo;
import com.ericmatelyan_schoolmobileapp.Entity.TermEntity;
import com.ericmatelyan_schoolmobileapp.R;

import java.time.LocalDate;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SchoolCalendarRepo repository = new SchoolCalendarRepo(getApplication());
        //FIX THIS: Date converter for room database?
        TermEntity term1 = new TermEntity(1,  "Term 1", LocalDate.now(), LocalDate.now().plusDays(90));
        repository.insert(term1);
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