package com.travelwithme.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.travelwithme.R;

public class HomeActivity extends AppCompatActivity {

    private Button btnBookings;
    private MaterialButton btnHelp, btnAbout, btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        initViews();
        initAdapters();
        initListeners();
    }

    private void initViews() {
        btnBookings = (Button) findViewById(R.id.btnBookings);
        btnHelp = (MaterialButton) findViewById(R.id.btn_help);
        btnAbout = (MaterialButton) findViewById(R.id.btn_about);
        btnLogout = (MaterialButton) findViewById(R.id.btn_logout);
    }

    private void initAdapters() {
        String[] cities = getResources().getStringArray(R.array.city);
        ArrayAdapter<String> adapterEdcType =
                new ArrayAdapter<String>(
                        getApplicationContext(),
                        R.layout.dropdown_menu_popup_item,
                        cities);

        AutoCompleteTextView editTextFilledExposedDropdownEdcType = findViewById(R.id.filled_exposed_dropdown_edc_type);
//        editTextFilledExposedDropdown.setText(edcType[0]);
        editTextFilledExposedDropdownEdcType.setAdapter(adapterEdcType);
        editTextFilledExposedDropdownEdcType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i==0) {
                    Intent intent = new Intent(HomeActivity.this, AnuradhapuraActivity.class);
                    startActivity(intent);
                } else if (i == 1) {
                    Intent intent = new Intent(HomeActivity.this, KandyActivity.class);
                    startActivity(intent);
                } else if (i == 2) {
                    Intent intent = new Intent(HomeActivity.this, ColomboActivity.class);
                    startActivity(intent);
                } else if(i == 3) {
                    Intent intent = new Intent(HomeActivity.this, NuwaraEliyaActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void initListeners() {
        btnBookings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ViewBookingsActivity.class);
                startActivity(intent);
            }
        });
        btnHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, HelpActivity.class);
                startActivity(intent);
            }
        });
        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, AboutActivity.class);
                startActivity(intent);
            }
        });
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearUserIdInCache();
                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });
    }

    private void clearUserIdInCache() {
        SharedPreferences sharedPreferences = getSharedPreferences("EASY-TRAVEL", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putString("TOKEN", "");
        myEdit.apply();
    }
}
