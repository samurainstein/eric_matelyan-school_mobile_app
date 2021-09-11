package com.ericmatelyan_schoolmobileapp.UI;

import android.app.Activity;
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
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.ericmatelyan_schoolmobileapp.Database.SchoolCalendarRepo;
import com.ericmatelyan_schoolmobileapp.Entity.TermEntity;
import com.ericmatelyan_schoolmobileapp.R;
import com.ericmatelyan_schoolmobileapp.Utility.DateConverter;
import com.ericmatelyan_schoolmobileapp.Utility.IdManager;

import java.util.Calendar;
import java.util.Date;

public class AddTermActivity extends AppCompatActivity {

    private static final String TAG = "AddTermActivity";
    private Context context = AddTermActivity.this;
    private EditText titleText;
    private TextView displayStartDate;
    private TextView displayEndDate;
    private Calendar startCalendar;
    private Calendar endCalendar;
    private SchoolCalendarRepo repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_term);

        titleText = findViewById(R.id.term_add_title_text);

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
        //FIX THIS: Make sure all fields are filled in.
        int termId = IdManager.getNextTermId();
        String title = titleText.getText().toString();
        Date startDate = startCalendar.getTime();
        Date endDate = endCalendar.getTime();
        TermEntity newTerm = new TermEntity(termId, title, startDate, endDate);
        repository.insert(newTerm);

        Intent intent = new Intent(this, TermsActivity.class);
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