package com.ericmatelyan_schoolmobileapp.UI;

import android.os.Bundle;

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
}