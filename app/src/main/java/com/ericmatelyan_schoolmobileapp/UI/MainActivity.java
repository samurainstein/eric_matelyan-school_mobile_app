package com.ericmatelyan_schoolmobileapp.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.ericmatelyan_schoolmobileapp.Database.SchoolCalendarRepo;
import com.ericmatelyan_schoolmobileapp.Entity.TermEntity;
import com.ericmatelyan_schoolmobileapp.R;
import com.ericmatelyan_schoolmobileapp.Utility.IdManager;

import java.time.LocalDate;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private Date testDate = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SchoolCalendarRepo repository = new SchoolCalendarRepo(getApplication());
//        TermEntity term1 = new TermEntity(IdManager.getNextTermId(),  "Term 1", testDate, testDate);
//        repository.insert(term1);
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