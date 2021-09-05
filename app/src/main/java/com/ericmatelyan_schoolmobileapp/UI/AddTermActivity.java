package com.ericmatelyan_schoolmobileapp.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.ericmatelyan_schoolmobileapp.Database.SchoolCalendarRepo;
import com.ericmatelyan_schoolmobileapp.Entity.TermEntity;
import com.ericmatelyan_schoolmobileapp.R;
import com.ericmatelyan_schoolmobileapp.Utility.DateConverter;
import com.ericmatelyan_schoolmobileapp.Utility.IdManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddTermActivity extends AppCompatActivity {

    EditText termName;
    SchoolCalendarRepo repository;
    private static final String TAG = "AddTermActivity";

    Context context = AddTermActivity.this;
    private TextView displayStartDate;
    private TextView displayEndDate;
    private Calendar startCalendar;
    private Calendar endCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_term);
        termName = findViewById(R.id.term_add_title_text);
        repository = new SchoolCalendarRepo(getApplication());
        IdManager.setNextTermId(repository);

        displayStartDate = findViewById(R.id.term_add_start_text);
        displayEndDate = findViewById(R.id.term_add_end_text);

        startCalendar = Calendar.getInstance();
        endCalendar = Calendar.getInstance();
        startCalendar = DateConverter.onClickStartDate(context, displayStartDate, startCalendar);
        endCalendar = DateConverter.onClickEndDate(context, displayEndDate, endCalendar);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.homemenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home_menu_item:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void add_term_save(View view) {
        int termId = IdManager.getNextTermId();
        String title = termName.getText().toString();
        Date startDate = startCalendar.getTime();
        Date endDate = endCalendar.getTime();
        TermEntity newTerm = new TermEntity(termId, title, startDate, endDate);
        repository.insert(newTerm);

        Intent intent = new Intent(this, TermsActivity.class);
        startActivity(intent);
    }
}