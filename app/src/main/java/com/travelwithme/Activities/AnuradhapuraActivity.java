package com.travelwithme.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.travelwithme.Adapters.TourListAdapter;
import com.travelwithme.Data.Tour;
import com.travelwithme.Database.DatabaseHelper;
import com.travelwithme.R;

import java.util.ArrayList;

public class AnuradhapuraActivity extends AppCompatActivity {
    private Button btnAddNewAnuTour;
    private ListView lvTourView;

    DatabaseHelper databaseHelper;

    ArrayList<Tour> tourArrayList = new ArrayList<Tour>();

    TourListAdapter tourListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anuradhapura);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Anuradhapura Tour");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        databaseHelper = new DatabaseHelper(this);
        tourArrayList = databaseHelper.getAllTourData("Anuradhapura");

        btnAddNewAnuTour = (Button) findViewById(R.id.btnAddNewAnuTour);
        lvTourView = (ListView) findViewById(R.id.lvTourView);

        tourListAdapter = new TourListAdapter(AnuradhapuraActivity.this, tourArrayList);
        tourListAdapter.notifyDataSetChanged();
        lvTourView.setAdapter(tourListAdapter);

        btnAddNewAnuTour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AnuradhapuraActivity.this, AddTourActivity.class);
                intent.putExtra("tourType", "Anuradhapura");
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                goBack();
        }

        return super.onOptionsItemSelected(item);
    }

    private void goBack() {
        Intent intent = new Intent(AnuradhapuraActivity.this, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
