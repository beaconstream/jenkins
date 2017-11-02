package com.travelur.Utility;

import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;

import com.travelur.R;

/**
 * Created by Abhijit on 6/6/2017.
 */

public class Utils {

    public static String KEY_NAME = "first_name";
    public static String KEY_LASTNAME = "last_name";
    public static String KEY_EMAIL = "email";

    public static String KEY_USER_ID = "user_id";
    public static String KEY_PASSWORD = "password";

    public static String KEY_USERNAME = "username";

    public static  final String REGISTER_URL = "https://travelurdev001.azurewebsites.net/userwebservice/register_on_light_box";
    public static  final String SET_PASSWORD_URL = "https://travelurdev001.azurewebsites.net/userwebservice/create_password_mobile";
    public static  final String SIGNIN_URL = "https://travelurdev001.azurewebsites.net/userwebservice/login";

    public static final boolean emailValidate(Context context, String email)
    {

        boolean status = false;
        String EMAIL = email;

        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (EMAIL.matches(emailPattern))
        {
            status = true;
            Toast.makeText(context,"Registered Successfully",Toast.LENGTH_SHORT).show();
        }
        else
        {
            status = false;
            Toast.makeText(context,"Invalid email address", Toast.LENGTH_SHORT).show();
        }

        return status;
    }
}
