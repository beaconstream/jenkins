package com.travelur.Web_Services;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.travelur.Utility.AppController;
import com.travelur.Utility.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.travelur.Utility.Utils.REGISTER_URL;

/**
 * Created by Abhijit on 6/8/2017.
 */

public class VolleyRequest extends Utils {
    static ProgressDialog pDialog;
    static Context context;
    private static String NAME = null;
    private static String LAST_NAME = null;
    private static String EMAIL = null;

    private static String USER_ID = null;
    private static String PASSWORD = null;

    private static String USERNAME = null;

    public VolleyRequest(Context context) {
        this.context = context;
    }

    public static void signUpUser(JSONObject jsonObject){
        // Tag used to cancel the request
        final String  signUpUser_string_req = "signUpUser_string_req";

        pDialog = new ProgressDialog(context);
        try {
            if(null!=jsonObject){
                JSONObject jsonObject1 = new JSONObject(jsonObject.toString());
                if(jsonObject1.has(KEY_NAME) && jsonObject1.has(KEY_LASTNAME) && jsonObject1.has(KEY_EMAIL))
                {
                    NAME = jsonObject1.get(KEY_NAME).toString();
                    LAST_NAME = jsonObject1.get(KEY_LASTNAME).toString();
                    EMAIL = jsonObject1.get(KEY_EMAIL).toString();
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        pDialog.setMessage("Registering...");
        pDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(StartupScreen.this,response,Toast.LENGTH_LONG).show();
                        Log.d(signUpUser_string_req, response.toString());
                        pDialog.hide();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(StartupScreen.this,error.toString(),Toast.LENGTH_LONG).show();
                        VolleyLog.d(signUpUser_string_req, "Error: " + error.getMessage());
                        pDialog.hide();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put(KEY_NAME, NAME);
                params.put(KEY_LASTNAME,LAST_NAME);
                params.put(KEY_EMAIL, EMAIL);
                return params;
            }
            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }

        };

        /*RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);*/

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(stringRequest, signUpUser_string_req);
    }

    public static void setPasswordUser(JSONObject jsonObject){
        // Tag used to cancel the request
        final String  setPasswordUser_string_req = "setPasswordUser_string_req";

        pDialog = new ProgressDialog(context);
        try {
            if(null!=jsonObject){
                JSONObject jsonObject1 = new JSONObject(jsonObject.toString());
                if(jsonObject1.has(KEY_NAME) && jsonObject1.has(KEY_LASTNAME) && jsonObject1.has(KEY_EMAIL))
                {
                    USER_ID = jsonObject1.get(KEY_NAME).toString();
                    PASSWORD = jsonObject1.get(KEY_LASTNAME).toString();
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        pDialog.setMessage("Setting Password...");
        pDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, SET_PASSWORD_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(StartupScreen.this,response,Toast.LENGTH_LONG).show();
                        Log.d(setPasswordUser_string_req, response.toString());
                        pDialog.hide();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(StartupScreen.this,error.toString(),Toast.LENGTH_LONG).show();
                        VolleyLog.d(setPasswordUser_string_req, "Error: " + error.getMessage());
                        pDialog.hide();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put(KEY_USER_ID, USER_ID);
                params.put(KEY_PASSWORD,PASSWORD);
                return params;
            }
            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }

        };

        /*RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);*/

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(stringRequest, setPasswordUser_string_req);
    }

    public static void signInUser(JSONObject jsonObject){
        // Tag used to cancel the request
        final String  signInUser_string_req = "signInUser_string_req";

        pDialog = new ProgressDialog(context);
        try {
            if(null!=jsonObject){
                JSONObject jsonObject1 = new JSONObject(jsonObject.toString());
                if(jsonObject1.has(KEY_NAME) && jsonObject1.has(KEY_LASTNAME) && jsonObject1.has(KEY_EMAIL))
                {
                    USERNAME = jsonObject1.get(KEY_NAME).toString();
                    PASSWORD = jsonObject1.get(KEY_LASTNAME).toString();
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        pDialog.setMessage("SigningIn...");
        pDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, SIGNIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(StartupScreen.this,response,Toast.LENGTH_LONG).show();
                        Log.d(signInUser_string_req, response.toString());
                        pDialog.hide();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(StartupScreen.this,error.toString(),Toast.LENGTH_LONG).show();
                        VolleyLog.d(signInUser_string_req, "Error: " + error.getMessage());
                        pDialog.hide();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put(KEY_USERNAME, USERNAME);
                params.put(KEY_PASSWORD,PASSWORD);
                return params;
            }
            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }

        };

        /*RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);*/

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(stringRequest, signInUser_string_req);
    }
}
