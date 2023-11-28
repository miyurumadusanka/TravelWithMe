package com.travelwithme.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.travelwithme.Data.Booking;
import com.travelwithme.Data.Tour;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    private SQLiteDatabase dbWrite = this.getWritableDatabase();
    private SQLiteDatabase dbRead = this.getReadableDatabase();

    private final String TABLE_USERS = "users";
    private final String KEY_ID = "id";
    private final String USER_ID = "user_code";
    private final String EMAIL = "email";
    private final String USERNAME = "username";
    private final String PASSWORD = "password";

    Context mContext;

    ArrayList<Tour> tourArrayList = new ArrayList<Tour>();
    ArrayList<Booking> bookingArrayList = new ArrayList<Booking>();

    private final String CREATE_TABLE_USER = "CREATE TABLE IF NOT EXISTS "
            + TABLE_USERS + "(" + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + USER_ID + " TEXT UNIQUE ,"
            + EMAIL + " TEXT UNIQUE ,"
            + USERNAME + " TEXT DEFAULT '' ,"
            + PASSWORD + " TEXT DEFAULT ''"+ ");";

    public DatabaseHelper(Context context) {
        super(context, "Travel", null, 1);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USER);
        db.execSQL("CREATE TABLE IF NOT EXISTS Tour('tourID' VARCHAR(10) PRIMARY KEY, 'tourPackage' VARCHAR(20), 'hotelType' VARCHAR(10), 'foodBudget' DOUBLE, 'numOfTravellers' DOUBLE, 'totalCost' DOUBLE)");
        db.execSQL("CREATE TABLE IF NOT EXISTS Booking('bookingID' VARCHAR(10) PRIMARY KEY, 'tourPackage' VARCHAR(20), 'hotelType' VARCHAR(10), 'foodBudget' DOUBLE, 'numOfTravellers' DOUBLE, 'tourCost' DOUBLE, 'dayCount' DOUBLE, 'vehicleType' VARCHAR(20), 'accomadationType' VARCHAR(20), 'totalCost' DOUBLE)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS Tour");
        db.execSQL("DROP TABLE IF EXISTS Booking");
    }

    public boolean checkTourExists(String tourID) {
        boolean check = false;

        Cursor cursor = dbRead.rawQuery("SELECT * FROM Tour WHERE tourID = ?", new String[]{tourID});

        if (cursor.getCount() > 0) {
            check = true;
        } else {
            check = false;
        }

        return check;
    }

    public void insertUser(String userID, String email, String username, String password) {
        dbWrite.execSQL("INSERT INTO users (user_code, email, username, password) VALUES('"+ userID +"', '"+ email +"', '"+ username +"', '"+ password +"')");
    }

    public String validateUser(String username, String password) {
        Cursor cursor = dbRead.rawQuery("SELECT * FROM users WHERE username = ? AND password = ?", new String[]{username, password});
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            return cursor.getString(1);
        } else {
            return null;
        }
    }

    public void insertTour(String tourID, String tourPackage, String hotelType, double foodBudget, double numTravellers, double tot) {
        dbWrite.execSQL("INSERT INTO Tour VALUES('"+ tourID +"', '"+ tourPackage +"', '"+ hotelType +"', '"+ foodBudget +"', '"+ numTravellers +"', '"+ tot +"')");
        Toast.makeText(this.mContext, "Data Inserted", Toast.LENGTH_SHORT).show();

//        System.out.println("UserID = " + userID);
//        System.out.println("tot = " + tot);
    }

    public void insertBooking(String bookingID, String tourPackage, String hotelType, double foodBudget, double numTravellers, double tourCost, double dayCount, String vehicleType, String accomadationType, double totalCost) {
        dbWrite.execSQL("INSERT INTO Booking VALUES('"+ bookingID +"', '"+ tourPackage +"', '"+ hotelType +"', '"+ foodBudget +"', '"+ numTravellers +"', '"+ tourCost +"', '"+ dayCount +"', '"+ vehicleType +"', '"+ accomadationType +"', '"+ totalCost +"')");
        Toast.makeText(this.mContext, "Data Inserted", Toast.LENGTH_SHORT).show();

//        System.out.println("UserID = " + bookingID);
//        System.out.println("Tour = " + tourPackage);
//        System.out.println("VehicleType = " + vehicleType);
    }

    public ArrayList<Booking> getAllBookingsData() {
        Cursor cursor = dbRead.rawQuery("SELECT * FROM Booking", null);

        if (cursor.moveToFirst()) {
            do {
                Booking booking = new Booking();
                booking.setBookingID(cursor.getString(0));
                booking.setTourPackage(cursor.getString(1));
                booking.setHotelType(cursor.getString(2));
                booking.setFoodBudget(cursor.getDouble(3));
                booking.setNumOfTravellers(cursor.getDouble(4));
                booking.setTourCost(cursor.getDouble(5));
                booking.setDayCount(cursor.getDouble(6));
                booking.setVehicleType(cursor.getString(7));
                booking.setAccomadationType(cursor.getString(8));
                booking.setTotalCost(cursor.getDouble(9));

                bookingArrayList.add(booking);
            } while (cursor.moveToNext());
        }

        return bookingArrayList;
    }

    public boolean checkBookingExists(String bookingID) {
        boolean check = false;

        Cursor cursor = dbRead.rawQuery("SELECT * FROM Booking WHERE bookingID = ?", new String[]{bookingID});

        if (cursor.getCount() > 0) {
            check = true;
        } else {
            check = false;
        }

        return check;
    }

    public ArrayList<Tour> getAllTourData(String tourPackage) {
        Cursor cursor = dbRead.rawQuery("SELECT * FROM Tour WHERE tourPackage = ?", new String[]{tourPackage});

        if (cursor.moveToFirst()) {
            do {
                Tour tour = new Tour();
                tour.setTourID(cursor.getString(0));
                tour.setTourPackage(cursor.getString(1));
                tour.setHotelType(cursor.getString(2));
                tour.setFoodBudget(cursor.getDouble(3));
                tour.setNumOfTravellers(cursor.getDouble(4));
                tour.setTotalCost(cursor.getDouble(5));

                tourArrayList.add(tour);
            } while (cursor.moveToNext());
        }

        return tourArrayList;
    }

    public void deleteBookingData(String bookingID) {
        dbWrite.execSQL("DELETE FROM Booking WHERE bookingID = '"+ bookingID +"'");
        Toast.makeText(this.mContext, "Data Deleted", Toast.LENGTH_SHORT).show();
    }

    public void deleteTour(String tourID) {
        dbWrite.execSQL("DELETE FROM Tour WHERE tourID = '"+ tourID +"'");
        Toast.makeText(this.mContext, "Data Deleted", Toast.LENGTH_SHORT).show();
    }
}
