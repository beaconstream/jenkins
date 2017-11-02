package com.travelur.helper;

/*
 * @author by Abhijit .
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashMap;

public class SQLiteHandler extends SQLiteOpenHelper {

    private static final String TAG = SQLiteHandler.class.getSimpleName();
    private static boolean GUEST_USER = false;

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "android_travelur";

    // Login table name
    private static final String TABLE_USER = "user";

    // Login Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_STATUS = "status";
    private static final String KEY_UID = "user_id";
    private static final String KEY_FIRST_NAME = "first_name";
    private static final String KEY_LAST_NAME = "last_name";

    private static final String KEY_DATA = "user_id";
    private static final String KEY_MOBILE_NO = "mobilenumber";
    private static final String KEY_USERNAME = "username";


    public SQLiteHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public SQLiteHandler(Context context, boolean user_guest) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        GUEST_USER = user_guest;
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

            String CREATE_LOGIN_TABLE = "CREATE TABLE " + TABLE_USER + "("
                    + KEY_ID + " INTEGER PRIMARY KEY," + KEY_STATUS + " TEXT,"
                    + KEY_UID + " TEXT UNIQUE," + KEY_FIRST_NAME + " TEXT,"
                    + KEY_LAST_NAME + " TEXT" + ")";
            db.execSQL(CREATE_LOGIN_TABLE);

            Log.d(TAG, "Database_User tables created");
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        // Create tables again
        onCreate(db);
    }

    /**
     * Storing user details in database
     * */
    public void addUser(boolean status, String user_id, String first_name, String last_name) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_STATUS, status); // Status
        values.put(KEY_UID, user_id); // User_Id
        values.put(KEY_FIRST_NAME, first_name); // First Name
        values.put(KEY_LAST_NAME, last_name); // Last Name

        // Inserting Row
        long id = db.insert(TABLE_USER, null, values);
        db.close(); // Closing database connection

        Log.d(TAG, "New user inserted into sqlite: " + id);
    }

    /**
     * Getting user data from database
     * */
    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<String, String>();
        String selectQuery = "SELECT  * FROM " + TABLE_USER;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            user.put(KEY_STATUS, cursor.getString(1));
            user.put(KEY_UID, cursor.getString(2));
            user.put(KEY_FIRST_NAME, cursor.getString(3));
            user.put(KEY_LAST_NAME, cursor.getString(4));
        }
        cursor.close();
        db.close();
        // return user
        Log.d(TAG, "Fetching user from Sqlite: " + user.toString());

        return user;
    }

    /**
     * Re crate database Delete all tables and create them again
     * */
    public void deleteUsers() {
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.delete(TABLE_USER, null, null);
        db.close();

        Log.d(TAG, "Deleted all user info from sqlite");
    }

}
