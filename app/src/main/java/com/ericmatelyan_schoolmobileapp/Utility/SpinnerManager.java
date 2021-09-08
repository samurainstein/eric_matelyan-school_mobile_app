package com.ericmatelyan_schoolmobileapp.Utility;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public abstract class SpinnerManager {

    public static ArrayAdapter<String> setSpinnerItems(Context context, Spinner spinner, List<String> strings) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, strings);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinner.setSelection(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return adapter;
    }

    public static void setSpinnerSelection(Spinner spinner, String assocTerm, ArrayAdapter<String> adapter) {
        if (assocTerm != null) {
            int spinnerPosition = adapter.getPosition(assocTerm);
            spinner.setSelection(spinnerPosition);
        }
    }
}
