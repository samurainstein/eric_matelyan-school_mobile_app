package com.ericmatelyan_schoolmobileapp.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.ericmatelyan_schoolmobileapp.Database.SchoolCalendarRepo;
import com.ericmatelyan_schoolmobileapp.Entity.CourseEntity;
import com.ericmatelyan_schoolmobileapp.Entity.TermEntity;
import com.ericmatelyan_schoolmobileapp.R;
import com.ericmatelyan_schoolmobileapp.Utility.DateConverter;

import java.util.Date;
import java.util.List;

public class TermDetailsActivity extends AppCompatActivity {

    private TermEntity term;
    private String termName;
    private Date startDateClass;
    private Date endDateClass;
    private String startDate;
    private String endDate;
    private TextView titleText;
    private TextView startText;
    private TextView endText;
    private SchoolCalendarRepo repository;
    private List<CourseEntity> assocCourses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_details);

        repository = new SchoolCalendarRepo(getApplication());

        term = (TermEntity) getIntent().getSerializableExtra("term");
        termName = term.getTermName();
        startDateClass = term.getStartDate();
        endDateClass = term.getEndDate();
        startDate = DateConverter.dateToString(startDateClass);
        endDate = DateConverter.dateToString(endDateClass);

        titleText = findViewById(R.id.term_details_title_text);
        startText = findViewById(R.id.term_details_start_text);
        endText = findViewById(R.id.term_details_end_text);

        titleText.setText(termName);
        startText.setText(startDate);
        endText.setText(endDate);

        assocCourses = repository.getAssociatedCourses(termName);

        RecyclerView recyclerView = findViewById(R.id.assoc_courses_recycler);
        final CourseAdapter courseAdapter = new CourseAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(courseAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        courseAdapter.setCourses(assocCourses);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.editmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home_menu_item:
                Intent homeIntent = new Intent(this, MainActivity.class);
                startActivity(homeIntent);
                return true;

            case R.id.edit_menu_item:
                Intent editIntent = new Intent(this, EditTermActivity.class);
                editIntent.putExtra("term", term);
                startActivity(editIntent);
                return true;

            case R.id.delete_menu_item:
                repository.delete(term);
                Intent deleteIntent = new Intent(this, TermsActivity.class);
                startActivity(deleteIntent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}