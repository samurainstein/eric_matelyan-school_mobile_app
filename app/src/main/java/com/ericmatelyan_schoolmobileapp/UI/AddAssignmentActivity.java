package com.ericmatelyan_schoolmobileapp.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.ericmatelyan_schoolmobileapp.Database.SchoolCalendarRepo;
import com.ericmatelyan_schoolmobileapp.Entity.AssignmentEntity;
import com.ericmatelyan_schoolmobileapp.Entity.CourseEntity;
import com.ericmatelyan_schoolmobileapp.R;
import com.ericmatelyan_schoolmobileapp.Utility.DateConverter;
import com.ericmatelyan_schoolmobileapp.Utility.IdManager;
import com.ericmatelyan_schoolmobileapp.Utility.SpinnerManager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AddAssignmentActivity extends AppCompatActivity {

    private static final String TAG = "AddAssignmentActivity";
    private Context context;
    private int assignmentId;
    private String assignmentName;
    private String assocCourse;
    private Date startDateClass;
    private Date endDateClass;
    private String type;

    private EditText titleText;
    private TextView startText;
    private TextView endText;
    private Calendar startCalendar;
    private Calendar endCalendar;
    private Spinner assocCourseSpinner;
    private Spinner typeSpinner;

    private SchoolCalendarRepo repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_assignment);

        context = AddAssignmentActivity.this;
        repository = new SchoolCalendarRepo(getApplication());

        titleText = findViewById(R.id.assignment_add_title_text);
        startText = findViewById(R.id.assignment_add_start_text);
        endText = findViewById(R.id.assignment_add_end_text);

        //Start Date----------------
        startCalendar = Calendar.getInstance();
        startCalendar = DateConverter.onClickStartDate(context, startText, startCalendar);


        //End Date---------------------
        endCalendar = Calendar.getInstance();
        endCalendar = DateConverter.onClickEndDate(context, endText, endCalendar);

        //Spinners---------------
        assocCourseSpinner = findViewById(R.id.assignment_add_course_spinner);
        typeSpinner = findViewById(R.id.assignment_add_type_spinner);
        List<String> typeStrings = Arrays.asList(getResources().getStringArray(R.array.assignment_types_array));
        List<CourseEntity> allCourses = repository.getAllCourses();
        List<String> allCoursesStrings = new ArrayList<>();
        for(CourseEntity course : allCourses) {
            allCoursesStrings.add(course.getCourseName());
        }
        SpinnerManager.setSpinnerItems(context, assocCourseSpinner, allCoursesStrings);
        SpinnerManager.setSpinnerItems(context, typeSpinner, typeStrings);
    }

    public void add_assignment_save(View view) {
        assignmentId = IdManager.getNextAssignmentId();
        assignmentName = titleText.getText().toString();
        assocCourse = assocCourseSpinner.getSelectedItem().toString();
        startDateClass = startCalendar.getTime();
        endDateClass = endCalendar.getTime();
        type = typeSpinner.getSelectedItem().toString();

        AssignmentEntity newAssignment = new AssignmentEntity(
                assignmentId,
                assignmentName,
                assocCourse,
                type,
                startDateClass,
                endDateClass);
        repository.insert(newAssignment);

        Intent intent = new Intent(this, AssignmentDetailsActivity.class);
        intent.putExtra("assignment", newAssignment);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
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

    //To make the soft keyboard disappear when clicking outside of EditText
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if ( v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
                    Log.d("focus", "touchevent");
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent(event);
    }
}