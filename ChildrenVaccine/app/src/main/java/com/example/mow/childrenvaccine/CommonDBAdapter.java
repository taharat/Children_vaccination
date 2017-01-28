package com.example.mow.childrenvaccine;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by mow on 6/25/2015.
 */

public class CommonDBAdapter {
    public static final String DATABASE_NAME = "iCareDB";
    public static final int DATABASE_VERSION = 1;

    /*========================== Vaccine Table ==========================*/

    public static final String VACCINE_TABLE_NAME = "tbl_vaccine";

    public static final String ID = "_id";
    public static final String DISEASE_NAME = "disease_name";
    public static final String VACCINE_NAME = "vaccine_name";
    public static final String DETAILS = "details";

String SQL_CREATE_VACCINE_TABLE = "CREATE TABLE " + VACCINE_TABLE_NAME + " ("
            + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + DISEASE_NAME + " TEXT," + VACCINE_NAME + " TEXT, "
            + DETAILS + " TEXT)";

    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public CommonDBAdapter(Context context) {
        this.context = context;
        this.DBHelper = new DatabaseHelper(this.context);
    }

    public class DatabaseHelper extends SQLiteOpenHelper {

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_VACCINE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + VACCINE_TABLE_NAME);
            // Create tables again
            onCreate(db);
        }
    }

    public SQLiteDatabase open(){
        this.db = this.DBHelper.getWritableDatabase();
        return db;
    }

    public void close(){
        this.DBHelper.close();
    }
}
