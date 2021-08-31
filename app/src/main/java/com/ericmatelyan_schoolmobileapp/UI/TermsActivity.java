package com.ericmatelyan_schoolmobileapp.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

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
        //FIX THIS: Crashes program
//        List<TermEntity> allTerms = repository.getAllTerms();
        List<TermEntity> allTerms = null;
//        TermEntity term1 = new TermEntity(2, "Term 2", LocalDate.now(), LocalDate.now().plusDays(90));
//        allTerms.add(term1);
        
        RecyclerView recyclerView = findViewById(R.id.terms_recycler);
        final TermAdapter termAdapter = new TermAdapter(this);
        recyclerView.setAdapter(termAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        termAdapter.setTerms(allTerms);
    }
}