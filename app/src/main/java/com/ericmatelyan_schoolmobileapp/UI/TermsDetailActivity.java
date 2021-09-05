package com.ericmatelyan_schoolmobileapp.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.ericmatelyan_schoolmobileapp.Database.SchoolCalendarRepo;
import com.ericmatelyan_schoolmobileapp.Entity.TermEntity;
import com.ericmatelyan_schoolmobileapp.R;
import com.ericmatelyan_schoolmobileapp.Utility.DateConverter;

import java.util.Date;

public class TermsDetailActivity extends AppCompatActivity {

    private int termId;
    private String termName;
    private String startDate;
    private String endDate;
    private TextView titleText;
    private TextView startText;
    private TextView endText;
    private SchoolCalendarRepo repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_detail);

        repository = new SchoolCalendarRepo(getApplication());

        termId = getIntent().getIntExtra("termId", -1);
        termName = getIntent().getStringExtra("termName");
        startDate = getIntent().getStringExtra("startDate");
        endDate = getIntent().getStringExtra("endDate");

        titleText = findViewById(R.id.term_details_title_text);
        startText = findViewById(R.id.term_details_start_text);
        endText = findViewById(R.id.term_details_end_text);

        titleText.setText(termName);
        startText.setText(startDate);
        endText.setText(endDate);
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
                editIntent.putExtra("termId", termId);
                editIntent.putExtra("termName", termName);
                editIntent.putExtra("startDate", startDate);
                editIntent.putExtra("endDate", endDate);
                startActivity(editIntent);
                return true;

            case R.id.delete_menu_item:
                Date startDateClass = DateConverter.stringToDate(startDate);
                Date endDateClass = DateConverter.stringToDate(endDate);
                TermEntity deleteTerm = new TermEntity(termId, termName, startDateClass, endDateClass);
                repository.delete(deleteTerm);
                Intent deleteIntent = new Intent(this, TermsActivity.class);
                startActivity(deleteIntent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}