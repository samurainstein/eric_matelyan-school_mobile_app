package com.ericmatelyan_schoolmobileapp.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.ericmatelyan_schoolmobileapp.Database.SchoolCalendarRepo;
import com.ericmatelyan_schoolmobileapp.Entity.TermEntity;
import com.ericmatelyan_schoolmobileapp.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EditCourseActivity extends AppCompatActivity {

    private SchoolCalendarRepo repository;
    private TermEntity assocTermEntity;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_course);

        repository = new SchoolCalendarRepo(getApplication());
        List<TermEntity> termsList = new ArrayList<>();
        List<TermEntity> allTerms = repository.getAllTerms();

        spinner = findViewById(R.id.assoc_term_spinner);

        ArrayAdapter<TermEntity> adapter = new ArrayAdapter<TermEntity>(this,
                android.R.layout.simple_spinner_item, allTerms);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                TermEntity term = (TermEntity) parent.getSelectedItem();
//                displayUserData(term);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

}