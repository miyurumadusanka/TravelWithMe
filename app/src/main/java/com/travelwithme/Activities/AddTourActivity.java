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

public class AddTourActivity extends AppCompatActivity {
    private String tourType = "";
    private String hotelType = "";
    private double foodBudget = 0;
    private String numOfTravelers = "";

    private double dTotalCost = 0;
    private double twoStar = 1500;
    private double threeStar = 3000;
    private double fourStar = 5000;
    private double fiveStar = 10000;

    private double days = 0;
    private double travellers = 0;

    private TextView txtDescription;
    private CheckBox cbTwoStar, cbThreeStar, cbFourStar, cbFiveStar;
    private CheckBox cbFood1000, cbFood2000, cbFood3000;
    private EditText txtNumOfTravelers, txtTourID;
    private Button btnCancel, btnSubmit;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tour);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setTitle("Add New Tour");

        databaseHelper = new DatabaseHelper(this);

        tourType = getIntent().getExtras().getString("tourType");

        //setting description
        txtDescription = (TextView) findViewById(R.id.txtDescription);
        if (tourType.equals("Anuradhapura")) {
            txtDescription.setText(R.string.anuDesc);
        } else if (tourType.equals("Kandy")) {
            txtDescription.setText(R.string.kandyDesc);
        } else if (tourType.equals("Colombo")) {
            txtDescription.setText(R.string.colomboDesc);
        } else if (tourType.equals("NuwaraEliya")) {
            txtDescription.setText(R.string.nuwaraeliyaDesc);
        } else {
            txtDescription.setText("");
        }

        //userID
        txtTourID = (EditText) findViewById(R.id.txtTourID);

        //hotel choice
        cbTwoStar = (CheckBox) findViewById(R.id.cbTwoStar);
        cbThreeStar = (CheckBox) findViewById(R.id.cbThreeStar);
        cbFourStar = (CheckBox) findViewById(R.id.cbFourStar);
        cbFiveStar = (CheckBox) findViewById(R.id.cbFiveStar);

        cbTwoStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbTwoStar.isChecked()) {
                    cbThreeStar.setEnabled(false);
                    cbFourStar.setEnabled(false);
                    cbFiveStar.setEnabled(false);

                    hotelType = "2 Star";
                } else {
                    cbThreeStar.setEnabled(true);
                    cbFourStar.setEnabled(true);
                    cbFiveStar.setEnabled(true);

                    hotelType = "";
                }
            }
        });

        cbThreeStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbThreeStar.isChecked()) {
                    cbTwoStar.setEnabled(false);
                    cbFourStar.setEnabled(false);
                    cbFiveStar.setEnabled(false);

                    hotelType = "3 Star";
                } else {
                    cbTwoStar.setEnabled(true);
                    cbFourStar.setEnabled(true);
                    cbFiveStar.setEnabled(true);

                    hotelType = "";
                }
            }
        });

        cbFourStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbFourStar.isChecked()) {
                    cbThreeStar.setEnabled(false);
                    cbTwoStar.setEnabled(false);
                    cbFiveStar.setEnabled(false);

                    hotelType = "4 Star";
                } else {
                    cbThreeStar.setEnabled(true);
                    cbTwoStar.setEnabled(true);
                    cbFiveStar.setEnabled(true);

                    hotelType = "";
                }
            }
        });

        cbFiveStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbFiveStar.isChecked()) {
                    cbThreeStar.setEnabled(false);
                    cbFourStar.setEnabled(false);
                    cbTwoStar.setEnabled(false);

                    hotelType = "5 Star";
                } else {
                    cbThreeStar.setEnabled(true);
                    cbFourStar.setEnabled(true);
                    cbTwoStar.setEnabled(true);

                    hotelType = "";
                }
            }
        });

        //food budget
        cbFood1000 = (CheckBox) findViewById(R.id.cbFood1000);
        cbFood2000 = (CheckBox) findViewById(R.id.cbFood2000);
        cbFood3000 = (CheckBox) findViewById(R.id.cbFood3000);

        cbFood1000.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbFood1000.isChecked()) {
                    cbFood2000.setEnabled(false);
                    cbFood3000.setEnabled(false);

                    foodBudget = 1000;
                } else {
                    cbFood2000.setEnabled(true);
                    cbFood3000.setEnabled(true);

                    foodBudget = 0;
                }
            }
        });

        cbFood2000.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbFood2000.isChecked()) {
                    cbFood1000.setEnabled(false);
                    cbFood3000.setEnabled(false);

                    foodBudget = 2000;
                } else {
                    cbFood1000.setEnabled(true);
                    cbFood3000.setEnabled(true);

                    foodBudget = 0;
                }
            }
        });

        cbFood3000.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbFood3000.isChecked()) {
                    cbFood1000.setEnabled(false);
                    cbFood2000.setEnabled(false);

                    foodBudget = 3000;
                } else {
                    cbFood1000.setEnabled(true);
                    cbFood2000.setEnabled(true);

                    foodBudget = 0;
                }
            }
        });

        //num of travelers
        txtNumOfTravelers = (EditText) findViewById(R.id.txtNumOfTravelers);

        //cancel button
        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });

        //submit button
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDataToDB();
            }
        });
    }

    private void addDataToDB() {
        String tourID = txtTourID.getText().toString();

        if (!txtNumOfTravelers.getText().toString().isEmpty()) {
            numOfTravelers = txtNumOfTravelers.getText().toString().trim();
            travellers = Double.valueOf(numOfTravelers);

            dTotalCost = ((calculateHotelCharge() + foodBudget) * travellers);

            if (!tourID.isEmpty() || !tourType.isEmpty() || !hotelType.isEmpty() || foodBudget != 0 || travellers != 0 || dTotalCost != 0) {
                if (!databaseHelper.checkTourExists(tourID)) {
                    databaseHelper.insertTour(tourID, tourType, hotelType, foodBudget, travellers, dTotalCost);
                    goBack();
                } else {
                    Toast.makeText(this, "Tour Exists", Toast.LENGTH_LONG).show();
                    txtTourID.setError("Add different tourID");
                }
            } else {
                Toast.makeText(this, "All fields should be filled", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "All fields should be filled", Toast.LENGTH_SHORT).show();
        }
    }

    private double calculateHotelCharge() {
        double total = 0;

        if (hotelType.equals("2 Star")) {
            total = twoStar;
        } else if (hotelType.equals("3 Star")) {
            total = threeStar;
        } else if (hotelType.equals("4 Star")) {
            total = fourStar;
        } else if (hotelType.equals("5 Star")) {
            total = fiveStar;
        }

        return total;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                goBack();
            }
        }

        return super.onOptionsItemSelected(item);
    }

    private void goBack() {
        Intent intent = new Intent(AddTourActivity.this, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
