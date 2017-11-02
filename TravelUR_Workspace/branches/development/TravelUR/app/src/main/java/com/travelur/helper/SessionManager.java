package com.travelur.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/*
 * @author by Abhijit .
 */

public class SessionManager {
    // LogCat tag
    private static String TAG = SessionManager.class.getSimpleName();

    // Shared Preferences
    SharedPreferences pref, pref_status;

    SharedPreferences.Editor editor;
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "TravelURLogin";

    private static final String KEY_IS_LOGGEDIN = "isLoggedIn";
    private static final String KEY_IS_GUEST_LOGGEDIN = "isGuestLoggedIn";
    private static final String KEY_IS_FIRST_TIME_LOGIN_STATUS = "isisFirstTimeLogin";

    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setLogin(boolean isLoggedIn) {

        editor.putBoolean(KEY_IS_LOGGEDIN, isLoggedIn);

        // commit changes
        editor.commit();

        Log.d(TAG, "User login session modified!");
    }
    public void setGuestLogin(boolean isGuestLoggedIn) {

        editor.putBoolean(KEY_IS_GUEST_LOGGEDIN, isGuestLoggedIn);

        // commit changes
        editor.commit();

        Log.d(TAG, "GuestUser login session modified!");
    }
    public void setFirstTimeLoginStatus(boolean isFirstTimeLogin) {

        editor.putBoolean(KEY_IS_FIRST_TIME_LOGIN_STATUS, isFirstTimeLogin);

        // commit changes
        editor.commit();

        Log.d(TAG, "Application Loged in for first time");
    }

    public boolean isLoggedIn(){
        return pref.getBoolean(KEY_IS_LOGGEDIN, false);
    }
    public boolean isGuestLoggedIn(){
        return pref.getBoolean(KEY_IS_GUEST_LOGGEDIN, false);
    }
    public boolean isLoggedInFirstTime(){
        return pref.getBoolean(KEY_IS_FIRST_TIME_LOGIN_STATUS, false);
    }
}
