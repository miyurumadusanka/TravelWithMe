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

import com.travelwithme.Data.Booking;
import com.travelwithme.Data.Tour;
import com.travelwithme.Database.DatabaseHelper;
import com.travelwithme.R;

import java.util.ArrayList;

public class BookingListAdapter extends ArrayAdapter {
    private TextView txtBookingID, txtHotelType, txtFoodBudget, txtNumOfTravellers, txtTourCost, txtTourType, txtDayCount, txtVehicleType, txtAccomadationType, txtTotCost;
    private Button btnDelete;

    private Context mContext;
    private ArrayList<Booking> mArrayList;

    DatabaseHelper databaseHelper;

    public BookingListAdapter(Context context, ArrayList<Booking> bookingArrayList) {
        super(context, R.layout.layout_view_tour, bookingArrayList);

        this.mContext = context;
        this.mArrayList = bookingArrayList;
        this.databaseHelper = new DatabaseHelper(context);
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.layout_view_booking, null);

        txtBookingID = (TextView) view.findViewById(R.id.txtBookingID);
        txtHotelType = (TextView) view.findViewById(R.id.txtHotelType);
        txtFoodBudget = (TextView) view.findViewById(R.id.txtFoodBudget);
        txtNumOfTravellers = (TextView) view.findViewById(R.id.txtNumOfTravelers);
        txtTourCost = (TextView) view.findViewById(R.id.txtTourCost);
        txtTourType = (TextView) view.findViewById(R.id.txtTourType);
        txtDayCount = (TextView) view.findViewById(R.id.txtDayCount);
        txtVehicleType = (TextView) view.findViewById(R.id.txtVehicleType);
        txtAccomadationType = (TextView) view.findViewById(R.id.txtAccomadationType);
        txtTotCost = (TextView) view.findViewById(R.id.txtTotCost);

        txtBookingID.setText(mArrayList.get(position).getBookingID());
        txtHotelType.setText(mArrayList.get(position).getHotelType());
        txtFoodBudget.setText(String.valueOf(mArrayList.get(position).getFoodBudget()));
        txtNumOfTravellers.setText(String.valueOf(mArrayList.get(position).getNumOfTravellers()));
        txtTourCost.setText(String.valueOf(mArrayList.get(position).getTourCost()));
        txtTourType.setText(mArrayList.get(position).getTourPackage());
        txtDayCount.setText(String.valueOf(mArrayList.get(position).getDayCount()));
        txtVehicleType.setText(mArrayList.get(position).getVehicleType());
        txtAccomadationType.setText(mArrayList.get(position).getAccomadationType());
        txtTotCost.setText(String.valueOf(mArrayList.get(position).getTotalCost()));

        btnDelete = (Button) view.findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper.deleteBookingData(mArrayList.get(position).getBookingID());
                notifyDataSetChanged();
                refreshPage();
            }
        });

        return view;
    }

    private void refreshPage() {
        Intent intent = new Intent(mContext, mContext.getClass());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        mContext.startActivity(intent);
    }
}
