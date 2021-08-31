package com.ericmatelyan_schoolmobileapp.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import com.ericmatelyan_schoolmobileapp.R;

public class TermsDetailActivity extends AppCompatActivity {

    private String termName;
    EditText editName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_detail);

        termName = getIntent().getStringExtra("termName");
        editName = findViewById(R.id.term_details_edit);
    }
}