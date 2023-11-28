package com.travelwithme.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.travelwithme.Adapters.BookingListAdapter;
import com.travelwithme.Adapters.TourListAdapter;
import com.travelwithme.Data.Booking;
import com.travelwithme.Data.Tour;
import com.travelwithme.Database.DatabaseHelper;
import com.travelwithme.R;

import java.util.ArrayList;

public class ViewBookingsActivity extends AppCompatActivity {
    private ListView lvBookingView;

    DatabaseHelper databaseHelper;

    ArrayList<Booking> bookingArrayList = new ArrayList<Booking>();

    BookingListAdapter bookingListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_bookings);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Bookings");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        databaseHelper = new DatabaseHelper(this);
        bookingArrayList = databaseHelper.getAllBookingsData();

        lvBookingView = (ListView) findViewById(R.id.lvBookingView);

        bookingListAdapter = new BookingListAdapter(ViewBookingsActivity.this, bookingArrayList);
        bookingListAdapter.notifyDataSetChanged();
        lvBookingView.setAdapter(bookingListAdapter);
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
        Intent intent = new Intent(ViewBookingsActivity.this, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
