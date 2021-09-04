package com.ericmatelyan_schoolmobileapp.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
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
import com.ericmatelyan_schoolmobileapp.Utility.IdManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddTermActivity extends AppCompatActivity {

    EditText editTitle;
    SchoolCalendarRepo repository;
    private static final String TAG = "AddTermActivity";

    private TextView displayStartDate;
    private TextView displayEndDate;
    private DatePickerDialog.OnDateSetListener startDateSetListener;
    private DatePickerDialog.OnDateSetListener endDateSetListener;
    Calendar startCalendar = Calendar.getInstance();
    Calendar endCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_term);
        editTitle = findViewById(R.id.add_term_edit_title);
        repository = new SchoolCalendarRepo(getApplication());
//        IdManager.setNextTermId(repository);

        //Start Date----------------
        displayStartDate = findViewById(R.id.add_term_start_date);
        displayStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int year = startCalendar.get(Calendar.YEAR);
                int month = startCalendar.get(Calendar.MONTH);
                int day = startCalendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        AddTermActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog,
                        startDateSetListener,
                        year,
                        month,
                        day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        startDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                startCalendar.set(Calendar.YEAR, year);
                startCalendar.set(Calendar.MONTH, month);
                startCalendar.set(Calendar.DAY_OF_MONTH, day);
                updateStartDateLabel();
            }
        };

        //End Date---------------------
        displayEndDate = findViewById(R.id.add_term_end_date);
        displayEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int year = endCalendar.get(Calendar.YEAR);
                int month = endCalendar.get(Calendar.MONTH);
                int day = endCalendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        AddTermActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog,
                        endDateSetListener,
                        year,
                        month,
                        day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        endDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                endCalendar.set(Calendar.YEAR, year);
                endCalendar.set(Calendar.MONTH, month);
                endCalendar.set(Calendar.DAY_OF_MONTH, day);
                updateEndDateLabel();
            }
        };
    }

    private void updateStartDateLabel() {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        displayStartDate.setText(sdf.format(startCalendar.getTime()));
    }

    private void updateEndDateLabel() {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        displayEndDate.setText(sdf.format(endCalendar.getTime()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
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
        int Id = IdManager.getNextTermId();
        String title = editTitle.getText().toString();
        Date startDate = startCalendar.getTime();
        Date endDate = endCalendar.getTime();
//       FIX THIS:
        TermEntity newTerm = new TermEntity(Id, title, startDate, endDate);
        repository.insert(newTerm);

        Intent intent = new Intent(this, TermsActivity.class);
        startActivity(intent);
    }
}