package com.ericmatelyan_schoolmobileapp.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
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
import com.ericmatelyan_schoolmobileapp.Entity.TermEntity;
import com.ericmatelyan_schoolmobileapp.R;
import com.ericmatelyan_schoolmobileapp.Utility.DateConverter;
import com.ericmatelyan_schoolmobileapp.Utility.SpinnerManager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class EditAssignmentActivity extends AppCompatActivity {

    private static final String TAG = "EditAssignmentActivity";
    private Context context;
    private AssignmentEntity assignment;
    private int assignmentId;
    private String assignmentName;
    private String assocCourse;
    private String startDate;
    private String endDate;
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
        setContentView(R.layout.activity_edit_assignment);
        context = EditAssignmentActivity.this;
        repository = new SchoolCalendarRepo(getApplication());
        assignment = (AssignmentEntity) getIntent().getSerializableExtra("assignment");

        assignmentName = assignment.getAssignmentName();
        startDateClass = assignment.getStartDate();
        endDateClass = assignment.getEndDate();
        startDate = DateConverter.dateToString(startDateClass);
        endDate = DateConverter.dateToString(endDateClass);
        assocCourse = assignment.getAssocCourse();
        type = assignment.getAssignmentType();

        titleText = findViewById(R.id.assignment_edit_title_text);
        startText = findViewById(R.id.assignment_edit_start_text);
        endText = findViewById(R.id.assignment_edit_end_text);

        titleText.setText(assignmentName);
        startText.setText(startDate);
        endText.setText(endDate);

        //Start Date----------------
        LocalDate startLocalDate = DateConverter.dateToLocalDate(startDateClass);
        startCalendar = Calendar.getInstance();
        int startYear = startLocalDate.getYear();
        int startMonth = startLocalDate.getMonthValue() - 1;
        int startDay = startLocalDate.getDayOfMonth();
        startCalendar.set(Calendar.YEAR, startYear);
        startCalendar.set(Calendar.MONTH, startMonth);
        startCalendar.set(Calendar.DAY_OF_MONTH, startDay);
        startCalendar = DateConverter.onClickStartDate(context, startText, startCalendar);


        //End Date---------------------
        LocalDate endLocalDate = DateConverter.dateToLocalDate(endDateClass);
        endCalendar = Calendar.getInstance();
        int endYear = endLocalDate.getYear();
        int endMonth = endLocalDate.getMonthValue() - 1;
        int endDay = endLocalDate.getDayOfMonth();
        endCalendar.set(Calendar.YEAR, endYear);
        endCalendar.set(Calendar.MONTH, endMonth);
        endCalendar.set(Calendar.DAY_OF_MONTH, endDay);
        endCalendar = DateConverter.onClickEndDate(context, endText, endCalendar);

        //Spinners---------------
        assocCourseSpinner = findViewById(R.id.assignment_edit_course_spinner);
        typeSpinner = findViewById(R.id.assignment_edit_type_spinner);
        List<String> typeStrings = Arrays.asList(getResources().getStringArray(R.array.assignment_types_array));
        List<CourseEntity> allCourses = repository.getAllCourses();
        List<String> allCoursesStrings = new ArrayList<>();
        for(CourseEntity course : allCourses) {
            allCoursesStrings.add(course.getCourseName());
        }
        ArrayAdapter<String> courseArrayAdapter = SpinnerManager.setSpinnerItems(context, assocCourseSpinner, allCoursesStrings);
        SpinnerManager.setSpinnerSelection(assocCourseSpinner, assocCourse, courseArrayAdapter);
        ArrayAdapter<String> typeArrayAdapter = SpinnerManager.setSpinnerItems(context, typeSpinner, typeStrings);
        SpinnerManager.setSpinnerSelection(typeSpinner, type, typeArrayAdapter);
    }

    public void edit_assignment_save(View view) {
        assignmentId = assignment.getAssignmentId();
        assignmentName = titleText.getText().toString();
        assocCourse = assocCourseSpinner.getSelectedItem().toString();
        Date startDate = startCalendar.getTime();
        Date endDate = endCalendar.getTime();
        type = typeSpinner.getSelectedItem().toString();

        AssignmentEntity updateAssignment = new AssignmentEntity(
                assignmentId,
                assignmentName,
                assocCourse,
                type,
                startDate,
                endDate);
        repository.update(updateAssignment);

        Intent intent = new Intent(this, AssignmentDetailsActivity.class);
        intent.putExtra("assignment", updateAssignment);
        startActivity(intent);
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