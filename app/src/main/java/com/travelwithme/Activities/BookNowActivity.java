package com.travelwithme.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.travelwithme.Database.DatabaseHelper;
import com.travelwithme.R;

public class BookNowActivity extends AppCompatActivity {
    private String tourID, tourType, hotelType, foodBudget, travellerCount, tourCost;
    private String vehicleType = "";
    private String numOfDays = "";
    private double totalCost = 0;
    private String accomadationType = "";
    private int days = 0;

    private double car = 2500;
    private double van = 5000;
    private double jeep = 7500;
    private double bus = 10000;

    private double indoor = 0;
    private double camping = 3000;


    private TextView txtTourID, txtHotelType, txtFoodBudget, txtNumOfTravellers, txtTotCost, txtTourType;
    private CheckBox cbIndoor, cbCamp;
    private CheckBox cbCar, cbVan, cbJeep, cbBus;
    private EditText txtNumOfDays;
    private Button btnCancel, btnSubmit;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_now);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Book Now");

        databaseHelper = new DatabaseHelper(this);

        tourID = getIntent().getExtras().getString("tourID");
        tourType = getIntent().getExtras().getString("tourType");
        hotelType = getIntent().getExtras().getString("hotelType");
        foodBudget = getIntent().getExtras().getString("foodBudget");
        travellerCount = getIntent().getExtras().getString("travellerCount");
        tourCost = getIntent().getExtras().getString("tourCost");

        txtTourID = (TextView) findViewById(R.id.txtTourID);
        txtHotelType = (TextView) findViewById(R.id.txtHotelType);
        txtFoodBudget = (TextView) findViewById(R.id.txtFoodBudget);
        txtNumOfTravellers = (TextView) findViewById(R.id.txtNumOfTravelers);
        txtTotCost = (TextView) findViewById(R.id.txtTotCost);
        txtTourType = (TextView) findViewById(R.id.txtTourType);

        txtTourID.setText(tourID);
        txtTourType.setText(tourType);
        txtHotelType.setText(hotelType);
        txtFoodBudget.setText(foodBudget);
        txtNumOfTravellers.setText(travellerCount);
        txtTotCost.setText(tourCost);

        //accomadation type
        cbIndoor = (CheckBox) findViewById(R.id.cbIndoor);
        cbCamp = (CheckBox) findViewById(R.id.cbCamp);

        cbIndoor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbIndoor.isChecked()) {
                    cbCamp.setEnabled(false);

                    accomadationType = "Hotel";
                } else {
                    cbCamp.setEnabled(true);

                    accomadationType = "";
                }
            }
        });

        cbCamp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbCamp.isChecked()) {
                    cbIndoor.setEnabled(false);

                    accomadationType = "Camping";
                } else {
                    cbIndoor.setEnabled(true);

                    accomadationType = "";
                }
            }
        });

        //vehicle type
        cbCar = (CheckBox) findViewById(R.id.cbCar);
        cbVan = (CheckBox) findViewById(R.id.cbVan);
        cbJeep = (CheckBox) findViewById(R.id.cbJeep);
        cbBus = (CheckBox) findViewById(R.id.cbBus);

        cbCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbCar.isChecked()) {
                    cbVan.setEnabled(false);
                    cbJeep.setEnabled(false);
                    cbBus.setEnabled(false);

                    vehicleType = "Car";
                } else {
                    cbVan.setEnabled(false);
                    cbJeep.setEnabled(false);
                    cbBus.setEnabled(false);

                    vehicleType = "";
                }
            }
        });

        cbVan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbVan.isChecked()) {
                    cbCar.setEnabled(false);
                    cbJeep.setEnabled(false);
                    cbBus.setEnabled(false);

                    vehicleType = "Van";
                } else {
                    cbCar.setEnabled(false);
                    cbJeep.setEnabled(false);
                    cbBus.setEnabled(false);

                    vehicleType = "";
                }
            }
        });

        cbJeep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbJeep.isChecked()) {
                    cbVan.setEnabled(false);
                    cbCar.setEnabled(false);
                    cbBus.setEnabled(false);

                    vehicleType = "Jeep";
                } else {
                    cbVan.setEnabled(false);
                    cbCar.setEnabled(false);
                    cbBus.setEnabled(false);

                    vehicleType = "";
                }
            }
        });

        cbBus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbBus.isChecked()) {
                    cbVan.setEnabled(false);
                    cbCar.setEnabled(false);
                    cbJeep.setEnabled(false);

                    vehicleType = "Bus";
                } else {
                    cbVan.setEnabled(false);
                    cbCar.setEnabled(false);
                    cbJeep.setEnabled(false);

                    vehicleType = "";
                }
            }
        });

        //num of days
        txtNumOfDays = (EditText) findViewById(R.id.txtNumOfDays);

        //buttons
        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });

        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!txtNumOfDays.getText().toString().isEmpty()) {

                    days = Integer.parseInt(txtNumOfDays.getText().toString().trim());

                    totalCost = Double.valueOf(tourCost) + calculateVehicleCharge() + calculateAccomadationCharge(days);

                    if (days != 0 && !vehicleType.isEmpty() && !accomadationType.isEmpty()) {

                        Toast.makeText(BookNowActivity.this, String.valueOf(totalCost), Toast.LENGTH_LONG).show();

                        if (!databaseHelper.checkBookingExists(tourID)) {
                            databaseHelper.insertBooking(tourID, tourType, hotelType, Double.valueOf(foodBudget), Double.valueOf(travellerCount), Double.valueOf(tourCost), days, vehicleType, accomadationType, totalCost);
                            goBack();
                        } else {
                            Toast.makeText(BookNowActivity.this, "Booking Exists", Toast.LENGTH_LONG).show();
                            txtTourID.setError("Add different tourID");
                        }
                    } else {
                        Toast.makeText(BookNowActivity.this, "All fields should be filled", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(BookNowActivity.this, "All fields should be filled", Toast.LENGTH_SHORT).show();
                }
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
        Intent intent = new Intent(BookNowActivity.this, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private double calculateVehicleCharge() {
        double total = 0;

        if (vehicleType.equals("Car")) {
            total = car;
        } else if (vehicleType.equals("Van")) {
            total = van;
        } else if (vehicleType.equals("Jeep")) {
            total = jeep;
        } else if (vehicleType.equals("Bus")) {
            total = bus;
        }

        return total;
    }

    private double calculateAccomadationCharge(int days) {
        double total = 0;

        if (accomadationType.equals("Hotel")) {
            total = indoor;
        } else if (accomadationType.equals("Camping")) {
            total = camping;
        }

        return total * days;
    }
}
