package com.example.mow.childrenvaccine;

/**
 * Created by mow on 6/25/2015.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class VaccineDBHandler {
    // All Static variables
    public static final String VACCINE_TABLE = CommonDBAdapter.VACCINE_TABLE_NAME;
    public static final String ROW_ID = CommonDBAdapter.ID;
    public static final String DISEASE_NAME = CommonDBAdapter.DISEASE_NAME;
    public static final String VACCINE_NAME = CommonDBAdapter.VACCINE_NAME;
    public static final String DETAILS = CommonDBAdapter.DETAILS;

    public static final String[] COLUMNS = new String[]{
            ROW_ID,
            DISEASE_NAME,
            VACCINE_NAME,
            DETAILS
    };


    private CommonDBAdapter mDbHelper;
    private Context mContext;
    private SQLiteDatabase db;
    public VaccineDBHandler(Context context) {
        this.mContext = context;
        mDbHelper = new CommonDBAdapter(context);
    }


    void addChildVaccine(Vaccine addVaccine) {

        SQLiteDatabase db = mDbHelper.open();

        ContentValues values = new ContentValues();
        values.put(DISEASE_NAME, addVaccine.getDiseaseName());
        values.put(VACCINE_NAME, addVaccine.getVaccineName());
        values.put(DETAILS, addVaccine.getDetails()== null ? "" : addVaccine.getDetails());


        // Inserting Row
        long result = db.insert(VACCINE_TABLE, null, values);
//        Log.d("=========== Name ==========", "=====================" + result);

        db.close(); // Closing database connection
    }

    public Cursor getAllCursorProfile(){
        open();
        Cursor cursor;

        cursor = db.query(VACCINE_TABLE, COLUMNS, null, null, null, null, null);
        if (cursor != null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public List<Vaccine> getAllProfile() {
        List<Vaccine> vaccineList = new ArrayList<Vaccine>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + VACCINE_TABLE;

        open();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Vaccine addVaccine = new Vaccine();
                addVaccine.setVaccineID(cursor.getInt(0));
                addVaccine.setDiseaseName(cursor.getString(1));
                addVaccine.setVaccineName(cursor.getString(2));
                addVaccine.setDetails(cursor.getString(3));

                vaccineList.add(addVaccine);
            } while (cursor.moveToNext());
        }
        db.close();
        return vaccineList;
    }

    public Vaccine getProfileByID(int id) {
        String selectQuery = "SELECT  * FROM " + VACCINE_TABLE + " where _id = " + id;

        open();
        Cursor cursor = db.rawQuery(selectQuery, null);
        Vaccine addVaccine = new Vaccine();

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {

            addVaccine.setVaccineID(cursor.getInt(0));
            addVaccine.setDiseaseName(cursor.getString(1));
            addVaccine.setVaccineName(cursor.getString(2));
            addVaccine.setDetails(cursor.getString(3));


        }
        db.close();
        return addVaccine;
    }


    public int updateProfile(Vaccine addVaccine) {
        SQLiteDatabase db = mDbHelper.open();

        ContentValues values = new ContentValues();

        values.put(ROW_ID, addVaccine.getVaccineID());
        values.put(DISEASE_NAME, addVaccine.getDiseaseName());
        values.put(VACCINE_NAME, addVaccine.getVaccineName());
        values.put(DETAILS, addVaccine.getDetails());

        return db.update(VACCINE_TABLE, values, ROW_ID + " = ?",
                new String[]{String.valueOf(addVaccine.getVaccineID())});
    }

    void deleteProfile(int id){
        SQLiteDatabase db = mDbHelper.open();
        db.delete(VACCINE_TABLE, ROW_ID +"= ?", new String[] {String.valueOf(id)});
        db.close();
    }
    public void open() throws SQLException {
        db = mDbHelper.open();
    }
}

