package com.travelwithme.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.travelwithme.Activities.BookNowActivity;
import com.travelwithme.Data.Tour;
import com.travelwithme.Database.DatabaseHelper;
import com.travelwithme.R;

import java.util.ArrayList;

public class TourListAdapter extends ArrayAdapter {
    private Context mContext;
    private ArrayList<Tour> mArrayList;

    DatabaseHelper databaseHelper;

    private TextView txtTourID, txtHotelType, txtFoodBudget, txtNumOfTravellers, txtTotCost, txtTourType;
    private Button btnBook, btnDelete;

    public TourListAdapter(Context context, ArrayList<Tour> tourArrayList) {
        super(context, R.layout.layout_view_tour, tourArrayList);

        this.mContext = context;
        this.mArrayList = tourArrayList;
        this.databaseHelper = new DatabaseHelper(context);
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.layout_view_tour, null);

        txtTourID = (TextView) view.findViewById(R.id.txtTourID);
        txtHotelType = (TextView) view.findViewById(R.id.txtHotelType);
        txtFoodBudget = (TextView) view.findViewById(R.id.txtFoodBudget);
        txtNumOfTravellers = (TextView) view.findViewById(R.id.txtNumOfTravelers);
        txtTotCost = (TextView) view.findViewById(R.id.txtTotCost);
        txtTourType = (TextView) view.findViewById(R.id.txtTourType);

        txtTourID.setText(mArrayList.get(position).getTourID());
        txtHotelType.setText(mArrayList.get(position).getHotelType());
        txtFoodBudget.setText(String.valueOf(mArrayList.get(position).getFoodBudget()));
        txtNumOfTravellers.setText(String.valueOf(mArrayList.get(position).getNumOfTravellers()));
        txtTotCost.setText(String.valueOf(mArrayList.get(position).getTotalCost()));
        txtTourType.setText(mArrayList.get(position).getTourPackage());

        btnDelete = (Button) view.findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper.deleteTour(mArrayList.get(position).getTourID());
                notifyDataSetChanged();
                refreshpage();
            }
        });

        btnBook = (Button) view.findViewById(R.id.btnBook);
        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, BookNowActivity.class);
                intent.putExtra("tourID", txtTourID.getText().toString());
                intent.putExtra("tourType", txtTourType.getText().toString());
                intent.putExtra("hotelType", txtHotelType.getText().toString());
                intent.putExtra("foodBudget", txtFoodBudget.getText().toString());
                intent.putExtra("travellerCount", txtNumOfTravellers.getText().toString());
                intent.putExtra("tourCost", txtTotCost.getText().toString());
                mContext.startActivity(intent);
            }
        });

        return view;
    }

    private void refreshpage() {
        Intent intent = new Intent(mContext, mContext.getClass());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        mContext.startActivity(intent);
    }
}
