package com.ericmatelyan_schoolmobileapp.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.ericmatelyan_schoolmobileapp.R;

public class TermsDetailActivity extends AppCompatActivity {

    private String termName;
    private String startDate;
    private String endDate;
    private TextView titleText;
    private TextView startText;
    private TextView endText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_detail);

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
                editIntent.putExtra("termName", termName);
                editIntent.putExtra("startDate", startDate);
                editIntent.putExtra("endDate", endDate);
                startActivity(editIntent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}