package com.ericmatelyan_schoolmobileapp.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ericmatelyan_schoolmobileapp.Database.SchoolCalendarRepo;
import com.ericmatelyan_schoolmobileapp.Entity.TermEntity;
import com.ericmatelyan_schoolmobileapp.R;

import java.time.LocalDate;
import java.util.List;

public class TermsActivity extends AppCompatActivity {

    private SchoolCalendarRepo repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms);

        repository = new SchoolCalendarRepo(getApplication());
        //FIX THIS: Crashes program (due to Date Converter)
        List<TermEntity> allTerms = repository.getAllTerms();
        
        RecyclerView recyclerView = findViewById(R.id.terms_recycler);
        final TermAdapter termAdapter = new TermAdapter(this);
        recyclerView.setAdapter(termAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        termAdapter.setTerms(allTerms);
    }

    public void addTerm(View view) {
        Intent intent = new Intent(this, AddTermActivity.class);
        startActivity(intent);
    }
}