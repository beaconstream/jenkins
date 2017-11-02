package com.travelur.backend;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.travelur.R;
import com.travelur.helper.AppHelper;
import com.travelur.helper.SQLiteHandler;
import com.travelur.helper.SessionManager;
import com.travelur.travelconnect.friends.adapters.YourFriendsListAdapter;
import com.travelur.travelconnect.friends.models.YourFriendsListItems;
import com.travelur.travelconnect.friends.models.YourFriendsSuggestionListItems;
import com.travelur.travelconnect.home.models.CommentListItem;
import com.travelur.travelconnect.home.models.Home;
import com.travelur.travelconnect.photos.adapters.ImageGalleryAdapter;
import com.travelur.travelconnect.photos.models.PhotoDataModel;
import com.travelur.travelconnect.photos.models.VideoDataModel;
import com.travelur.travelconnect.profile.models.Profile;
import com.travelur.travelconnect.profile.models.ProfileDetailsDataModel;
import com.travelur.travelconnect.rewards.models.Rewards;
import com.travelur.travelconnect.settings.adapters.QuestionListCheckBoxAdapter;
import com.travelur.travelconnect.settings.adapters.QuestionListInputBoxAdapter;
import com.travelur.travelconnect.settings.adapters.QuestionListRadioButtonAdapter;
import com.travelur.travelconnect.settings.models.AccountSettingsDataModel;
import com.travelur.travelconnect.settings.models.YourPreferencesDataModel;
import com.travelur.travelconnect.settings.models.questionlisttypedatamodel.QuestionListCheckBoxDataModel;
import com.travelur.travelconnect.settings.models.questionlisttypedatamodel.QuestionListCheckBoxDataModelOptions;
import com.travelur.travelconnect.settings.models.questionlisttypedatamodel.QuestionListInputBoxDataModel;
import com.travelur.travelconnect.settings.models.questionlisttypedatamodel.QuestionListInputBoxDataModelOptions;
import com.travelur.travelconnect.settings.models.questionlisttypedatamodel.QuestionListRadioButtonDataModel;
import com.travelur.travelconnect.settings.models.questionlisttypedatamodel.QuestionListRadioButtonDataModelOptions;
import com.travelur.travelconnect.signedinhome.MainActivity;
import com.travelur.travelconnect.vacationpackages.models.BudgetListItem;
import com.travelur.travelconnect.vacationpackages.models.DestinationListItem;
import com.travelur.travelconnect.vacationpackages.models.VacationPackageListItem;
import com.travelur.travelconnect.vacationpackages.models.VacationPackageMoreDetailsCancellationPolicyListItem;
import com.travelur.travelconnect.vacationpackages.models.VacationPackageMoreDetailsDetaileItenaryListItem;
import com.travelur.travelconnect.vacationpackages.models.VacationPackageMoreDetailsListItem;
import com.travelur.travelconnect.vacationpackages.models.VacationPackageMoreDetailsPricePolicyListItem;
import com.travelur.travelconnect.vacationpackages.models.VacationPackageMoreDetailsTermsConditionListItem;
import com.travelur.travelconnect.vacationpackages.models.VacationTypeListItem;
import com.travelur.utility.AppConfig;
import com.travelur.utility.AppController;
import com.travelur.utility.GlideCircleTransformation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.dmoral.toasty.Toasty;

import static com.travelur.utility.AppConfig.BASE_URL;
import static com.travelur.utility.AppConfig.KEY_ADDRESS;
import static com.travelur.utility.AppConfig.KEY_ANSWER;
import static com.travelur.utility.AppConfig.KEY_CITY;
import static com.travelur.utility.AppConfig.KEY_COMMENT;
import static com.travelur.utility.AppConfig.KEY_CONFIRM_PASSWORD;
import static com.travelur.utility.AppConfig.KEY_COUNTRY_CODE;
import static com.travelur.utility.AppConfig.KEY_COUNTRY_NAME;
import static com.travelur.utility.AppConfig.KEY_DATE_OF_BIRTH;
import static com.travelur.utility.AppConfig.KEY_DURATION;
import static com.travelur.utility.AppConfig.KEY_EMAIL;
import static com.travelur.utility.AppConfig.KEY_FIRST_NAME;
import static com.travelur.utility.AppConfig.KEY_GROUP_DESC;
import static com.travelur.utility.AppConfig.KEY_GROUP_ID;
import static com.travelur.utility.AppConfig.KEY_GROUP_NAME;
import static com.travelur.utility.AppConfig.KEY_HOME_AIRPORT;
import static com.travelur.utility.AppConfig.KEY_ID;
import static com.travelur.utility.AppConfig.KEY_IMAGE;
import static com.travelur.utility.AppConfig.KEY_IMAGEPATH;
import static com.travelur.utility.AppConfig.KEY_LASTNAME;
import static com.travelur.utility.AppConfig.KEY_LAST_NAME;
import static com.travelur.utility.AppConfig.KEY_MESSAGE;
import static com.travelur.utility.AppConfig.KEY_MOBILENO;
import static com.travelur.utility.AppConfig.KEY_NAME;
import static com.travelur.utility.AppConfig.KEY_OLD_PASSWORD;
import static com.travelur.utility.AppConfig.KEY_PACKAGE_COUNTRY;
import static com.travelur.utility.AppConfig.KEY_PACKAGE_ID;
import static com.travelur.utility.AppConfig.KEY_PACKAGE_TYPE;
import static com.travelur.utility.AppConfig.KEY_PASSWORD;
import static com.travelur.utility.AppConfig.KEY_PHONE;
import static com.travelur.utility.AppConfig.KEY_PIN_CODE;
import static com.travelur.utility.AppConfig.KEY_QID;
import static com.travelur.utility.AppConfig.KEY_STATE;
import static com.travelur.utility.AppConfig.KEY_TYPE;
import static com.travelur.utility.AppConfig.KEY_USERNAME;
import static com.travelur.utility.AppConfig.KEY_USER_ID;
import static com.travelur.utility.AppConfig.KEY_WALL_ID;
import static com.travelur.utility.AppConfig.adapter_checkbox;
import static com.travelur.utility.AppConfig.adapter_inputbox;
import static com.travelur.utility.AppConfig.adapter_radiobutton;

import static com.travelur.utility.AppConfig.destination_layout;
import static com.travelur.utility.AppConfig.destination_listItems;
import static com.travelur.utility.AppConfig.profile_details_List;
import static com.travelur.utility.AppConfig.setCheckbox_destination;
import static com.travelur.utility.AppConfig.setCheckbox_vacation_types;
import static com.travelur.utility.AppConfig.urphoto_List;
import static com.travelur.utility.AppConfig.vacation_listItems;
import static com.travelur.utility.AppConfig.vacation_type_layout;
import static com.travelur.utility.AppConfig.vacationpackages_moredetails_cancellationPolicy_listitem;
import static com.travelur.utility.AppConfig.vacationpackages_moredetails_detaileditenary_listitem;
import static com.travelur.utility.AppConfig.vacationpackages_moredetails_gallery_listitem;
import static com.travelur.utility.AppConfig.vacationpackages_moredetails_listitem;
import static com.travelur.utility.AppConfig.vacationpackages_moredetails_pricePolicy_listitem;
import static com.travelur.utility.AppConfig.vacationpackages_moredetails_termscondition_listitem;
import static com.travelur.utility.AppConfig.yourFriendsListItems;
import static com.travelur.utility.AppConfig.yourFriendsSuggestionListItems;


/*
 * @author by Abhijit.
 */

public class VolleyRequest {

    private static ProgressDialog pDialog;
    private static Context context;
    private static String NAME = null;
    private static String LAST_NAME = null;
    private static String EMAIL = null;
    private static String USER_ID = null;
    private static String PASSWORD = null;
    private static String USERNAME = null;
    private static String MOBILENO = null;
    private static String MESSAGE = null;
    private static String WALL_ID = null;
    private static String GROUP_ID = null;
    private static String COMMENT = null;
    private static String GROUP_NAME = null;
    private static String GROUP_DESC = null;
    private static String IMAGEPATH = null;
    private static String TYPE = null;
    private static String ID = null;
    private static String PACKAGE_ID = null;
    private static final String boundary = "apiclient-" + System.currentTimeMillis();
    private static List<String> IMAGEPATH_LIST;

    private static String FIRST_NAME = null;
    private static String COUNTRY_NAME = null;
    private static String PIN_CODE = null;
    private static String ADDRESS = null;
    private static String STATE = null;
    private static String COUNTRY_CODE = null;
    private static String DATE_OF_BIRTH = null;
    private static String PHONE = null;
    private static String HOME_AIRPORT = null;
    private static String IMAGE = null;
    private static String CITY = null;

    private static String PACKAGE_COUNTRY = null;
    private static String PACKAGE_TYPE = null;
    private static String PRICES = null;
    private static String DURATION = null;

    private static String OLD_PASSWORD = null;

    private static String QID = null;
    private static String ANSWER = null;

    private static SessionManager session;
    private static SQLiteHandler db;
    private static RecyclerView.Adapter volleyAdapter;
    private static QuestionListCheckBoxAdapter volleyQuestionListAdapter;
    private static QuestionListRadioButtonAdapter volleyQuestionListAdapter_radioButton;
    private static QuestionListInputBoxAdapter volleyQuestionListAdapter_InputBox;
    private static SwipeRefreshLayout volleySwipeRefreshLayout;

    public VolleyRequest(Context context) {
        this.context = context;
    }

    public static void signedInHome_Profile(final JSONObject jsonObject, final RecyclerView.Adapter adapter, final SwipeRefreshLayout swipeRefreshLayout, final TextView profile_name, final ImageView profile_pic){
        // Tag used to cancel the request
        final String  signedInHome_Profile_string_req = "signedInHome_Profile_string_req";

        pDialog = new ProgressDialog(context);
        try {
            if(null!=jsonObject){
                JSONObject jsonObject1 = new JSONObject(jsonObject.toString());
                if(jsonObject1.has(KEY_USER_ID))
                {
                    USER_ID = jsonObject1.get(KEY_USER_ID).toString();
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        pDialog.setMessage("Fetching profile details...");
        //pDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL.concat("profile"),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(StartupScreen.this,response,Toast.LENGTH_LONG).show();
                        Log.d(signedInHome_Profile_string_req, response.toString());
                        pDialog.hide();

                        try {
                            JSONObject jObj = new JSONObject(response);
                            boolean status = jObj.getBoolean("status");

                            // Check for error node in json
                            if (status) {
                                // user successfully Registered

                                JSONObject profile_details = jObj.optJSONObject("profile_details");
                                String user_id = profile_details.optString("user_id");
                                String image = profile_details.optString("image");
                                String first_name = profile_details.optString("first_name");
                                String last_name = profile_details.optString("last_name");
                                String country_name = profile_details.optString("country_name");
                                String pin_code = profile_details.optString("pin_code");
                                String address = profile_details.optString("address");
                                String state = profile_details.optString("state");
                                String country_code = profile_details.optString("country_code");
                                String date_of_birth = profile_details.optString("date_of_birth");
                                String phone = profile_details.optString("phone");
                                String home_airport = profile_details.optString("home_airport");
                                String city = profile_details.optString("city");
                                String user_image_path = profile_details.optString("user_image_path");

                                AppConfig.volleyRecyclerViewAdapter = adapter;
                                AppConfig.volleySwipeRefreshLayout = swipeRefreshLayout;
                                //signedInHome_Travel_Connect();
                                signedInHome_Profile_TravelConnectProfile();
                                profile_name.setText(first_name);
                                Glide.with(context)
                                        .load(user_image_path)
                                        .crossFade()
                                        .placeholder(R.drawable.profile_placeholder)
                                        //.error(R.drawable.error_icon)
                                        .bitmapTransform(new GlideCircleTransformation(context))
                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                        .into(profile_pic);

                            } else {
                                // Error in Registeration. Get the error message
                                String data = jObj.optString("message");
                                Toast.makeText(context,
                                        data, Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            // JSON error
                            e.printStackTrace();
                            Toast.makeText(context, "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(StartupScreen.this,error.toString(),Toast.LENGTH_LONG).show();
                        VolleyLog.d(signedInHome_Profile_string_req, "Error: " + error.getMessage());
                        pDialog.hide();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put(KEY_USER_ID, USER_ID);
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
        AppController.getInstance().addToRequestQueue(stringRequest, signedInHome_Profile_string_req);
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

        StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL.concat("register_on_light_box"),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(StartupScreen.this,response,Toast.LENGTH_LONG).show();
                        Log.d(signUpUser_string_req, response.toString());
                        pDialog.hide();

                        try {
                            JSONObject jObj = new JSONObject(response);
                            boolean status = jObj.getBoolean("status");

                            // Check for error node in json
                            if (status) {
                                // user successfully Registered

                                String data = jObj.getString("data");
                                String user_id = jObj.getString("user_id");

                                Toasty.success(context, data, Toast.LENGTH_SHORT, true).show();
                                // Launch Sign_In Layout
                                if(context instanceof Activity){
                                    ((Activity) context).findViewById(R.id.signUp_layout).setVisibility(View.GONE);
                                    ((Activity) context).findViewById(R.id.signIn_layout).setVisibility(View.VISIBLE);}

                            } else {
                                // Error in Registeration. Get the error message
                                String data = jObj.getString("data");
                                Toasty.error(context, data, Toast.LENGTH_SHORT, true).show();
                            }
                        } catch (JSONException e) {
                            // JSON error
                            e.printStackTrace();
                            Toast.makeText(context, "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }

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
        // SQLite database handler
        db = new SQLiteHandler(context);
        // Session manager
        session = new SessionManager(context);

        pDialog = new ProgressDialog(context);
        try {
            if(null!=jsonObject){
                JSONObject jsonObject1 = new JSONObject(jsonObject.toString());
                if(jsonObject1.has(KEY_USER_ID) && jsonObject1.has(KEY_PASSWORD) && jsonObject1.has(KEY_CONFIRM_PASSWORD))
                {
                    USER_ID = jsonObject1.get(KEY_USER_ID).toString();
                    PASSWORD = jsonObject1.get(KEY_PASSWORD).toString();
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        pDialog.setMessage("Setting Password...");
        pDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL.concat("create_password_mobile"),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(StartupScreen.this,response,Toast.LENGTH_LONG).show();
                        Log.d(setPasswordUser_string_req, response.toString());
                        pDialog.hide();

                        try {
                            JSONObject jObj = new JSONObject(response);
                            boolean status = jObj.getBoolean("status");
                            if (status) {
                                // user successfully logged in
                                // Create login session
                                session.setLogin(true);

                                // User successfully stored in MySQL
                                // Now store the user in sqlite
                                String user_id = jObj.getString("user_id");
                                String first_name = jObj.getString("first_name");
                                String last_name = jObj.getString("last_name");

                                // Inserting row in users table
                                db.addUser(status, user_id, first_name, last_name);

                                Toast.makeText(context, "Password set successfully.", Toast.LENGTH_LONG).show();

                                // Launch login activity
                                Intent intent = new Intent(
                                        context,
                                        MainActivity.class);
                                intent.putExtra("Activity","StartupScreen_Set_Password");
                                intent.putExtra("status",status);
                                context.startActivity(intent);
                                //finish();
                                if(context instanceof Activity){
                                    ((Activity)context).finish(); }
                            } else {

                                // Error occurred in set Password. Get the error
                                // message
                                String errorMsg = jObj.getString("status");
                                Toast.makeText(context,
                                        errorMsg, Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context,error.toString(),Toast.LENGTH_LONG).show();
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

        // SQLite database handler
        db = new SQLiteHandler(context);
        // Session manager
        session = new SessionManager(context);

        pDialog = new ProgressDialog(context);
        try {
            if(null!=jsonObject){
                JSONObject jsonObject1 = new JSONObject(jsonObject.toString());
                if(jsonObject1.has(KEY_USERNAME) && jsonObject1.has(KEY_PASSWORD))
                {
                    USERNAME = jsonObject1.get(KEY_USERNAME).toString();
                    PASSWORD = jsonObject1.get(KEY_PASSWORD).toString();
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        pDialog.setMessage("SigningIn...");
        pDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL.concat("login"),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(StartupScreen.this,response,Toast.LENGTH_LONG).show();
                        Log.d(signInUser_string_req, response.toString());
                        pDialog.hide();

                        try {
                            JSONObject jObj = new JSONObject(response);
                            boolean status = jObj.getBoolean("status");

                            // Check for error node in json
                            if (status) {
                                // user successfully logged in
                                // Create login session
                                session.setLogin(true);

                                // Now store the user in SQLite
                                String user_id = jObj.getString("user_id");
                                String first_name = jObj.getString("first_name");
                                String last_name = jObj.getString("last_name");

                                // Inserting row in users table
                                db.addUser(status, user_id, first_name, last_name);
                                AppConfig.setUser_id(user_id);

                                // Launch main activity
                                Intent intent = new Intent(context,
                                        MainActivity.class);
                                context.startActivity(intent);
                                if(context instanceof Activity){
                                    ((Activity)context).finish(); }
                            } else {
                                // Error in login. Get the error message
                                String data = jObj.getString("data");
                                /*Toast.makeText(context,
                                        data, Toast.LENGTH_LONG).show();*/
                                Toasty.error(context, data, Toast.LENGTH_SHORT, true).show();
                            }
                        } catch (JSONException e) {
                            // JSON error
                            e.printStackTrace();
                            Toast.makeText(context, "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
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

    public static void forgotPasswordUser(JSONObject jsonObject){
        // Tag used to cancel the request
        final String  forgotPasswordUser_string_req = "forgotPasswordUser_string_req";

        // SQLite database handler
        db = new SQLiteHandler(context);
        // Session manager
        session = new SessionManager(context);

        pDialog = new ProgressDialog(context);
        try {
            if(null!=jsonObject){
                JSONObject jsonObject1 = new JSONObject(jsonObject.toString());
                if(jsonObject1.has(KEY_EMAIL))
                {
                    EMAIL = jsonObject1.get(KEY_EMAIL).toString();
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        pDialog.setMessage("Sending Mail...");
        pDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL.concat("forgot_password"),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(StartupScreen.this,response,Toast.LENGTH_LONG).show();
                        Log.d(forgotPasswordUser_string_req, response.toString());
                        pDialog.hide();

                        try {
                            JSONObject jObj = new JSONObject(response);
                            boolean status = jObj.getBoolean("status");

                            // Check for error node in json
                            if (status) {
                                // user successfully sent reset password mail

                                String data = jObj.getString("data");

                                Toasty.success(context, data, Toast.LENGTH_SHORT, true).show();
                                // Launch Sign_In Layout
                                if(context instanceof Activity){
                                    ((Activity) context).findViewById(R.id.forgetPassword_layout).setVisibility(View.GONE);
                                    ((Activity) context).findViewById(R.id.signIn_layout).setVisibility(View.VISIBLE);}
                            } else {
                                // Error in login. Get the error message
                                String data = jObj.getString("data");
                                /*Toast.makeText(context,
                                        data, Toast.LENGTH_LONG).show();*/
                                Toasty.error(context, data , Toast.LENGTH_SHORT, true).show();
                            }
                        } catch (JSONException e) {
                            // JSON error
                            e.printStackTrace();
                            Toast.makeText(context, "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(StartupScreen.this,error.toString(),Toast.LENGTH_LONG).show();
                        VolleyLog.d(forgotPasswordUser_string_req, "Error: " + error.getMessage());
                        pDialog.hide();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
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
        AppController.getInstance().addToRequestQueue(stringRequest, forgotPasswordUser_string_req);
    }

    public static void signUpGuestLogin(JSONObject jsonObject){
        // Tag used to cancel the request
        final String  signUpGuestLogin_string_req = "signUpGuestLogin_string_req";

        // SQLite database handler
        db = new SQLiteHandler(context, true);
        // Session manager
        session = new SessionManager(context);

        pDialog = new ProgressDialog(context);
        try {
            if(null!=jsonObject){
                JSONObject jsonObject1 = new JSONObject(jsonObject.toString());
                if(jsonObject1.has(KEY_USERNAME) && jsonObject1.has(KEY_MOBILENO))
                {
                    USERNAME = jsonObject1.get(KEY_USERNAME).toString();
                    MOBILENO = jsonObject1.get(KEY_MOBILENO).toString();
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        pDialog.setMessage("Signing In Guest...");
        pDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL.concat("register_guest_user"),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(StartupScreen.this,response,Toast.LENGTH_LONG).show();
                        Log.d(signUpGuestLogin_string_req, response.toString());
                        pDialog.hide();

                        try {
                            JSONObject jObj = new JSONObject(response);
                            boolean status = jObj.getBoolean("status");

                            // Check for error node in json
                            if (status) {
                                // user successfully logged in
                                // Create login session
                                session.setGuestLogin(true);

                                // Now store the user in SQLite
                                String user_id = jObj.getString("user_id");
                                String mobilenumber = jObj.getString("mobilenumber");
                                String username = jObj.getString("username");

                                // Inserting row in users table
                                db.addUser(status, user_id, username, mobilenumber);

                                // Launch main activity
                                // Launch main activity
                                Intent intent = new Intent(context,
                                        MainActivity.class);
                                context.startActivity(intent);
                                if(context instanceof Activity){
                                    ((Activity)context).finish(); }
                            } else {
                                // Error in login. Get the error message
                                String errorMsg = jObj.getString("data");
                                Toast.makeText(context,
                                        errorMsg, Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            // JSON error
                            e.printStackTrace();
                            Toast.makeText(context, "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(StartupScreen.this,error.toString(),Toast.LENGTH_LONG).show();
                        VolleyLog.d(signUpGuestLogin_string_req, "Error: " + error.getMessage());
                        pDialog.hide();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put(KEY_USERNAME, USERNAME);
                params.put(KEY_MOBILENO,MOBILENO);
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
        AppController.getInstance().addToRequestQueue(stringRequest, signUpGuestLogin_string_req);
    }

    public static void signedInHome_Travel_Connect(){
        /*volleyAdapter = adapter;
        volleySwipeRefreshLayout = swipeRefreshLayout;*/
        // Tag used to cancel the request
        final String  signedInHome_Travel_Connect = "signedInHome_Travel_Connect";

        if(null!=AppConfig.user_id){
            USER_ID = AppConfig.user_id;

        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL.concat("travelconnect"),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(StartupScreen.this,response,Toast.LENGTH_LONG).show();
                        Log.d(signedInHome_Travel_Connect, response.toString());

                        //Home home = new Home();
                        try {
                            JSONObject jObj = new JSONObject(response);
                            boolean status = jObj.optBoolean("status");

                            List<String> images_List = null;
                            List<String> videos_List = null;
                            List<CommentListItem> comment_List =null;

                            // Check for error node in json
                            if (status) {
                                if(!AppConfig.home_List.isEmpty())
                                    AppConfig.home_List.clear();

                                JSONObject data_jsonObject = jObj.optJSONObject("data");
                                JSONArray result_jsonArray = data_jsonObject.optJSONArray("result");
                                if(null != result_jsonArray)
                                {
                                    for(int i = 0; i<result_jsonArray.length(); i++){

                                        JSONObject result_jsonArray_jsonObject = result_jsonArray.optJSONObject(i);
                                        String message = result_jsonArray_jsonObject.optString("message");
                                        String user_id = result_jsonArray_jsonObject.optString("user_id");
                                        String first_name = result_jsonArray_jsonObject.optString("first_name");
                                        String value = result_jsonArray_jsonObject.optString("value");
                                        String type = result_jsonArray_jsonObject.optString("type");
                                        String likes = result_jsonArray_jsonObject.optString("likes");
                                        String created_date = result_jsonArray_jsonObject.optString("created_date");
                                        String post_id = result_jsonArray_jsonObject.optString("post_id");
                                        String comments_count = result_jsonArray_jsonObject.optString("comments_count");
                                        String user_image = result_jsonArray_jsonObject.optString("user_image");
                                        String images = result_jsonArray_jsonObject.optString("images");
                                        if(null!=images){
                                            images_List = Arrays.asList(images.split(","));
                                        }
                                        String videos = result_jsonArray_jsonObject.optString("video");
                                        if(null!=videos){
                                            videos_List = Arrays.asList(videos.split(","));
                                        }

                                        comment_List = new ArrayList<>();
                                        if(!comment_List.isEmpty())
                                            comment_List.clear();

                                        JSONArray comments_message_array = result_jsonArray_jsonObject.optJSONArray("comments_message");
                                        for(int j=0; j<comments_message_array.length(); j++){
                                            JSONObject comments_message_jsonObject = comments_message_array.optJSONObject(j);
                                            String comment = comments_message_jsonObject.optString("comment");
                                            String comment_id = comments_message_jsonObject.optString("comment_id");
                                            String time = comments_message_jsonObject.optString("time");
                                            String comment_first_name = comments_message_jsonObject.optString("first_name");
                                            String comment_last_name = comments_message_jsonObject.optString("last_name");
                                            String comment_user_id = comments_message_jsonObject.optString("user_id");
                                            String comment_image = comments_message_jsonObject.optString("image");

                                            comment_List.add(new CommentListItem(comment_image, comment_first_name+" "+comment_last_name, comment, time, user_id, comment_id));

                                        }

                                        //List<CommentListItem> comment_list_clone = AppConfig.comment_List;
                                        /*home.setMessage(message);
                                        home.setUser_id(user_id);
                                        home.setFirst_name(first_name);
                                        home.setValue(value);
                                        home.setType(type);
                                        home.setLikes(likes);
                                        home.setCreated_date(created_date);
                                        home.setPost_id(post_id);
                                        home.setComments_count(comments_count);
                                        home.setUser_image(user_image);
                                        home.setImages(images_List);
                                        home.setVideos(videos_List);*/

                                        AppConfig.home_List.add(new Home(message, user_id, first_name, value, type, likes, created_date,  post_id, comments_count, user_image, images_List, videos_List, comment_List, AppConfig.account_setting_List, profile_details_List));

                                    }
                                    // notifying list adapter about data changes
                                    // so that it renders the list view with updated data
                                    if(null!=AppConfig.volleyRecyclerViewAdapter)
                                        AppConfig.volleyRecyclerViewAdapter.notifyDataSetChanged();
                                    if(null!=AppConfig.volleyCommentListAdapter)
                                        AppConfig.volleyCommentListAdapter.notifyDataSetChanged();
                                }

                            } else {
                                // Error in login. Get the error message
                                String errorMsg = jObj.getString("data");
                                Toast.makeText(context,
                                        errorMsg, Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            // JSON error
                            e.printStackTrace();
                            Toast.makeText(context, "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                        if(null!=AppConfig.volleySwipeRefreshLayout)
                            AppConfig.volleySwipeRefreshLayout.setRefreshing(false);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if(null!=AppConfig.volleySwipeRefreshLayout)
                            AppConfig.volleySwipeRefreshLayout.setRefreshing(false);
                        //volleySwipeRefreshLayout.setRefreshing(false);
                        //Toast.makeText(StartupScreen.this,error.toString(),Toast.LENGTH_LONG).show();
                        VolleyLog.d(signedInHome_Travel_Connect, "Error: " + error.getMessage());
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put(KEY_USER_ID, USER_ID);
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
        AppController.getInstance().addToRequestQueue(stringRequest, signedInHome_Travel_Connect);
    }

    public static void signedInHome_Like(JSONObject jsonObject, final RecyclerView.Adapter adapter){
        // Tag used to cancel the request
        final String  travelConnect_Like_Post_string_req = "travelConnect_Like_Post_string_req";

        try {
            if(null!=jsonObject){
                JSONObject jsonObject1 = new JSONObject(jsonObject.toString());
                if(jsonObject1.has(KEY_TYPE) && jsonObject1.has(KEY_USER_ID) && jsonObject1.has(KEY_ID))
                {
                    TYPE = jsonObject1.get(KEY_TYPE).toString();
                    USER_ID = jsonObject1.get(KEY_USER_ID).toString();
                    ID = jsonObject1.get(KEY_ID).toString();
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL.concat("like"),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(StartupScreen.this,response,Toast.LENGTH_LONG).show();
                        Log.d(travelConnect_Like_Post_string_req, response.toString());

                        try {
                            JSONObject jObj = new JSONObject(response);
                            String status = jObj.optString("status");

                            // Check for error node in json
                            if (status.equals("0") || status.equals("1")) {
                                String data = jObj.optString("data");
                                Toast.makeText(context,
                                        data, Toast.LENGTH_LONG).show();
                                AppConfig.volleyRecyclerViewAdapter = adapter;
                                //signedInHome_Travel_Connect();
                                signedInHome_Profile_TravelConnectProfile();
                            } else {
                                // Error in login. Get the error message
                                String data = jObj.optString("data");
                                Toast.makeText(context,
                                        data, Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            // JSON error
                            e.printStackTrace();
                            Toast.makeText(context, response.toString(), Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(StartupScreen.this,error.toString(),Toast.LENGTH_LONG).show();
                        VolleyLog.d(travelConnect_Like_Post_string_req, "Error: " + error.getMessage());
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put(KEY_TYPE, TYPE);
                params.put(KEY_USER_ID,USER_ID);
                params.put(KEY_ID,ID);
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
        AppController.getInstance().addToRequestQueue(stringRequest, travelConnect_Like_Post_string_req);
    }

    public static void signedInHome_Comment(JSONObject jsonObject){
        // Tag used to cancel the request
        final String  signedInHome_Comment_string_req = "signedInHome_Comment_string_req";

        try {
            if(null!=jsonObject){
                JSONObject jsonObject1 = new JSONObject(jsonObject.toString());
                if(jsonObject1.has(KEY_USER_ID) && jsonObject1.has(KEY_ID) && jsonObject1.has(KEY_COMMENT))
                {
                    USER_ID = jsonObject1.get(KEY_USER_ID).toString();
                    ID = jsonObject1.get(KEY_ID).toString();
                    COMMENT = jsonObject1.get(KEY_COMMENT).toString();
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL.concat("comment"),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(StartupScreen.this,response,Toast.LENGTH_LONG).show();
                        Log.d(signedInHome_Comment_string_req, response.toString());

                        try {
                            JSONObject jObj = new JSONObject(response);
                            boolean status = jObj.getBoolean("status");

                            // Check for error node in json
                            if (status) {

                                JSONObject data = jObj.optJSONObject("data");
                                String success = data.optString("success");

                                // Launch main activity
                                //finish();
                                /*if(context instanceof Activity){
                                    ((Activity)context).finish(); }*/
                                //signedInHome_Travel_Connect();
                                signedInHome_Profile_TravelConnectProfile();
                            } else {
                                // Error in login. Get the error message
                                String errorMsg = jObj.getString("data");
                                Toast.makeText(context,
                                        errorMsg, Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            // JSON error
                            e.printStackTrace();
                            Toast.makeText(context, "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(StartupScreen.this,error.toString(),Toast.LENGTH_LONG).show();
                        VolleyLog.d(signedInHome_Comment_string_req, "Error: " + error.getMessage());
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put(KEY_USER_ID, USER_ID);
                params.put(KEY_ID,ID);
                params.put(KEY_COMMENT,COMMENT);
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
        AppController.getInstance().addToRequestQueue(stringRequest, signedInHome_Comment_string_req);
    }

    public static void signedInHome_Share(JSONObject jsonObject){
        // Tag used to cancel the request
        final String  signedInHome_Share_string_req = "signedInHome_Share_string_req";
        pDialog = new ProgressDialog(context);
        try {
            if(null!=jsonObject){
                JSONObject jsonObject1 = new JSONObject(jsonObject.toString());
                if(jsonObject1.has(KEY_USER_ID) && jsonObject1.has(KEY_ID))
                {
                    USER_ID = jsonObject1.get(KEY_USER_ID).toString();
                    ID = jsonObject1.get(KEY_ID).toString();
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        pDialog.setMessage("Please wait, Sharing...");
        pDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL.concat("share"),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(StartupScreen.this,response,Toast.LENGTH_LONG).show();
                        Log.d(signedInHome_Share_string_req, response.toString());
                        pDialog.hide();

                        try {
                            JSONObject jObj = new JSONObject(response);
                            boolean status = jObj.getBoolean("status");

                            // Check for error node in json
                            if (status) {

                                // Now store the user in SQLite
                                JSONObject data = jObj.optJSONObject("data");
                                String success = data.getString("success");
                                String message = jObj.getString("message");
                                Toast.makeText(context, message.toString(), Toast.LENGTH_LONG).show();
                                //signedInHome_Travel_Connect();
                                signedInHome_Profile_TravelConnectProfile();

                            } else {
                                // Error in login. Get the error message
                                String errorMsg = jObj.getString("data");
                                Toast.makeText(context,
                                        errorMsg, Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            // JSON error
                            e.printStackTrace();
                            Toast.makeText(context, "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(StartupScreen.this,error.toString(),Toast.LENGTH_LONG).show();
                        VolleyLog.d(signedInHome_Share_string_req, "Error: " + error.getMessage());
                        pDialog.hide();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put(KEY_USER_ID, USER_ID);
                params.put(KEY_ID,ID);
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
        AppController.getInstance().addToRequestQueue(stringRequest, signedInHome_Share_string_req);
    }

    public static void signedInHome_Edit(JSONObject jsonObject, boolean isImage){
        final boolean isImage_data = isImage;
        // Tag used to cancel the request
        final String  signedInHome_Edit_Multipart_req = "signedInHome_Edit_Multipart_req";

        try {
            if(null!=jsonObject){
                JSONObject jsonObject1 = new JSONObject(jsonObject.toString());
                JSONArray jsonArray1;
                IMAGEPATH_LIST = new ArrayList<String>();
                if(jsonObject1.has(KEY_USER_ID) && jsonObject1.has(KEY_IMAGEPATH) && jsonObject1.has(KEY_MESSAGE) && jsonObject1.has(KEY_WALL_ID) && jsonObject1.has(KEY_GROUP_ID))
                {
                    USER_ID = jsonObject1.optString(KEY_USER_ID);
                    jsonArray1 = jsonObject1.optJSONArray(KEY_IMAGEPATH);
                    for (int i=0; i<jsonArray1.length(); i++) {
                        IMAGEPATH_LIST.add(jsonArray1.getString(i));
                    }
                    MESSAGE = jsonObject1.optString(KEY_MESSAGE);
                    WALL_ID = jsonObject1.optString(KEY_WALL_ID);
                    GROUP_ID = jsonObject1.optString(KEY_GROUP_ID);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        final VolleyMultipartRequest stringRequest = new VolleyMultipartRequest(Request.Method.POST, BASE_URL.concat("edit"),
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(StartupScreen.this,response,Toast.LENGTH_LONG).show();
                        Log.d(signedInHome_Edit_Multipart_req, response.toString());

                        try {
                            JSONObject jObj = new JSONObject(response);
                            boolean status = jObj.getBoolean("status");

                            // Check for error node in json
                            if (status) {

                                JSONObject data = jObj.optJSONObject("data");
                                String message = data.optString("message");
                                String post_id = data.optString("post_id");

                            } else {
                                // Error in postdata
                                String errorMsg = jObj.optJSONObject("data").toString();
                                Toast.makeText(context,
                                        errorMsg, Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            // JSON error
                            e.printStackTrace();
                            Toast.makeText(context, "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(StartupScreen.this,error.toString(),Toast.LENGTH_LONG).show();
                        VolleyLog.d(signedInHome_Edit_Multipart_req, "Error: " + error.getMessage());
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put(KEY_USER_ID, USER_ID);
                params.put(KEY_MESSAGE,MESSAGE);
                params.put(KEY_WALL_ID, WALL_ID);
                params.put(KEY_GROUP_ID,GROUP_ID);
                return params;
            }
            @Override
            protected Map<String, DataPart> getByteData() {
                Map<String, DataPart> params = new HashMap<>();
                // file name could found file base or direct access from real path
                // for now just get bitmap data from ImageView


                for(int i = 0 ; i< IMAGEPATH_LIST.size() ; i++) {
                    if(isImage_data) {
                        params.put("userFiles[" + i + "]", new DataPart("file.jpg", AppHelper.getFileDataFromImagePath(context, IMAGEPATH_LIST.get(i)), "image/jpeg"));
                    }else{
                        try {
                            params.put("userFiles[" + i + "]", new DataPart("file.mp4", AppHelper.getFileDataFromVideoPath(context, IMAGEPATH_LIST.get(i)), "video/mp4"));

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                return params;
            }

        };

        /*RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);*/

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(stringRequest, signedInHome_Edit_Multipart_req);
    }

    public static void signedInHome_Delete(JSONObject jsonObject){
        // Tag used to cancel the request
        final String  signedInHome_Delete_string_req = "signedInHome_Delete_string_req";
        pDialog = new ProgressDialog(context);
        try {
            if(null!=jsonObject){
                JSONObject jsonObject1 = new JSONObject(jsonObject.toString());
                if(jsonObject1.has(KEY_TYPE) && jsonObject1.has(KEY_USER_ID) && jsonObject1.has(KEY_ID))
                {
                    TYPE = jsonObject1.get(KEY_TYPE).toString();
                    USER_ID = jsonObject1.get(KEY_USER_ID).toString();
                    ID = jsonObject1.get(KEY_ID).toString();
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        pDialog.setMessage("Please wait, Deleting...");
        pDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL.concat("delete"),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(StartupScreen.this,response,Toast.LENGTH_LONG).show();
                        Log.d(signedInHome_Delete_string_req, response.toString());
                        pDialog.hide();

                        try {
                            JSONObject jObj = new JSONObject(response);
                            boolean status = jObj.getBoolean("status");

                            // Check for error node in json
                            if (status) {

                                JSONObject data = jObj.optJSONObject("data");
                                String success = data.optString("success");
                               /* AppConfig.volleyRecyclerViewAdapter.notifyItemRemoved(AppConfig.signInHome_ImageSelected);
                                AppConfig.volleyRecyclerViewAdapter.notifyItemChanged(AppConfig.signInHome_ImageSelected);
                                AppConfig.volleyRecyclerViewAdapter.notifyDataSetChanged();*/
                                AppConfig.volleyRecyclerViewAdapter.notifyItemRemoved(AppConfig.signInHome_ImageSelected);
                                AppConfig.volleyRecyclerViewAdapter.notifyItemRangeChanged(AppConfig.signInHome_ImageSelected,AppConfig.home_List.size());
                                AppConfig.volleyRecyclerViewAdapter.notifyDataSetChanged();
                                //signedInHome_Travel_Connect();
                                signedInHome_Profile_TravelConnectProfile();

                            } else {
                                // Error in login. Get the error message
                                String errorMsg = jObj.getString("data");
                                Toast.makeText(context,
                                        errorMsg, Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            // JSON error
                            e.printStackTrace();
                            Toast.makeText(context, "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(StartupScreen.this,error.toString(),Toast.LENGTH_LONG).show();
                        VolleyLog.d(signedInHome_Delete_string_req, "Error: " + error.getMessage());
                        pDialog.hide();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put(KEY_TYPE, TYPE);
                params.put(KEY_USER_ID,USER_ID);
                params.put(KEY_ID,ID);
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
        AppController.getInstance().addToRequestQueue(stringRequest, signedInHome_Delete_string_req);
    }

    public static void signedInHome_Add_Post(JSONObject jsonObject, boolean isImage, final FragmentManager fragmentManager){
        final boolean isImage_data = isImage;
        pDialog = new ProgressDialog(context);
        // Tag used to cancel the request
        final String  signedInHome_Add_Post_Multipart_req = "signedInHome_Add_Post_Multipart_req";

        try {
            if(null!=jsonObject){
                JSONObject jsonObject1 = new JSONObject(jsonObject.toString());
                JSONArray jsonArray1;
                IMAGEPATH_LIST = new ArrayList<String>();
                if(jsonObject1.has(KEY_USER_ID) && jsonObject1.has(KEY_IMAGEPATH) && jsonObject1.has(KEY_MESSAGE) && jsonObject1.has(KEY_WALL_ID) && jsonObject1.has(KEY_GROUP_ID))
                {
                    USER_ID = jsonObject1.optString(KEY_USER_ID);
                    jsonArray1 = jsonObject1.optJSONArray(KEY_IMAGEPATH);
                    for (int i=0; i<jsonArray1.length(); i++) {
                        IMAGEPATH_LIST.add(jsonArray1.getString(i));
                    }
                    MESSAGE = jsonObject1.optString(KEY_MESSAGE);
                    WALL_ID = jsonObject1.optString(KEY_WALL_ID);
                    GROUP_ID = jsonObject1.optString(KEY_GROUP_ID);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        pDialog.setMessage("Please wait, Adding post...");
        pDialog.show();

        final VolleyMultipartRequest stringRequest = new VolleyMultipartRequest(Request.Method.POST, BASE_URL.concat("add_post"),
                new Response.Listener<String>() {


                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(StartupScreen.this,response,Toast.LENGTH_LONG).show();
                        Log.d(signedInHome_Add_Post_Multipart_req, response.toString());
                        pDialog.hide();

                        try {
                            JSONObject jObj = new JSONObject(response);
                            boolean status = jObj.getBoolean("status");

                            // Check for error node in json
                            if (status) {

                                JSONObject data = jObj.optJSONObject("data");
                                String message = data.optString("message");
                                String post_id = data.optString("post_id");
                                signedInHome_Profile_TravelConnectProfile();
                                if (fragmentManager.getBackStackEntryCount() > 0) {
                                    fragmentManager.popBackStack();
                                }

                            } else {
                                // Error in postdata
                                String errorMsg = jObj.optJSONObject("data").toString();
                                Toast.makeText(context,
                                        errorMsg, Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            // JSON error
                            e.printStackTrace();
                            Toast.makeText(context, "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(StartupScreen.this,error.toString(),Toast.LENGTH_LONG).show();
                        VolleyLog.d(signedInHome_Add_Post_Multipart_req, "Error: " + error.getMessage());
                        pDialog.hide();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put(KEY_USER_ID, USER_ID);
                params.put(KEY_MESSAGE,MESSAGE);
                params.put(KEY_WALL_ID, WALL_ID);
                params.put(KEY_GROUP_ID,GROUP_ID);
                return params;
            }
            @Override
            protected Map<String, DataPart> getByteData() {
                Map<String, DataPart> params = new HashMap<>();
                // file name could found file base or direct access from real path
                // for now just get bitmap data from ImageView


                for(int i = 0 ; i< IMAGEPATH_LIST.size() ; i++) {
                    if(isImage_data) {
                        params.put("userFiles[" + i + "]", new DataPart("file.jpg", AppHelper.getFileDataFromImagePath(context, IMAGEPATH_LIST.get(i)), "image/jpeg"));
                    }else{
                        try {
                            params.put("userFiles[" + i + "]", new DataPart("file.mp4", AppHelper.getFileDataFromVideoPath(context, IMAGEPATH_LIST.get(i)), "video/mp4"));

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                return params;
            }

        };

        /*RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);*/

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(stringRequest, signedInHome_Add_Post_Multipart_req);
    }

    public static void signedInHome_Profile_TravelConnectProfile(){
        // Tag used to cancel the request
        final String  signedInHome_Profile_TravelConnectProfile_string_req = "signedInHome_Profile_TravelConnectProfile_string_req";

       /* volleyAdapter = adapter;
        try {
            if(null!=jsonObject){
                JSONObject jsonObject1 = new JSONObject(jsonObject.toString());
                if(jsonObject1.has(KEY_USER_ID))
                {
                    USER_ID = jsonObject1.get(KEY_USER_ID).toString();
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }*/

        StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL.concat("travelconnectprofile"),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(StartupScreen.this,response,Toast.LENGTH_LONG).show();
                        Log.d(signedInHome_Profile_TravelConnectProfile_string_req, response.toString());

                        try {
                            JSONObject jObj = new JSONObject(response);
                            boolean status = jObj.optBoolean("status");

                            List<String> images_List = null;
                            List<String> videos_List = null;
                            List<CommentListItem> comment_List = null;

                            // Check for error node in json
                            if (status) {
                                if(!AppConfig.home_Profile_List.isEmpty())
                                    AppConfig.home_Profile_List.clear();
                                if(!AppConfig.home_List.isEmpty())
                                    AppConfig.home_List.clear();
                                if(!AppConfig.account_setting_List.isEmpty())
                                    AppConfig.account_setting_List.clear();
                                if(!AppConfig.profile_details_List.isEmpty())
                                    AppConfig.profile_details_List.clear();

                                JSONObject data_jsonObject = jObj.optJSONObject("data");
                                JSONObject profile_details = jObj.optJSONObject("profile_details");
                                JSONObject posts_count = jObj.optJSONObject("posts_count");
                                JSONObject friend_count = jObj.optJSONObject("friends_count");
                                JSONObject group_count = jObj.optJSONObject("group_count");
                                JSONObject photo_count = jObj.optJSONObject("photos_count");
                                JSONObject video_count = jObj.optJSONObject("videos_count");
                                JSONArray result_jsonArray = data_jsonObject.optJSONArray("result");
                                if(null != profile_details)
                                {
                                    String user_id = profile_details.optString("user_id");
                                    String email = profile_details.optString("email");
                                    String image = profile_details.optString("image");
                                    String first_name = profile_details.optString("first_name");
                                    String last_name = profile_details.optString("last_name");
                                    String country_name = profile_details.optString("country_name");
                                    String pin_code = profile_details.optString("pin_code");
                                    String profile_per = profile_details.optString("profile_percentage");
                                    String address = profile_details.optString("address");
                                    String state = profile_details.optString("state");
                                    String country_code = profile_details.optString("country_code");
                                    String date_of_birth = profile_details.optString("date_of_birth");
                                    String phone = profile_details.optString("phone");
                                    String home_airport = profile_details.optString("home_airport");
                                    String city = profile_details.optString("city");
                                    String user_image_path = profile_details.optString("user_image_path");

                                    String postcount = posts_count.optString("cpost");
                                    String friendscount = friend_count.optString("friends_count");
                                    String groupcount = group_count.optString("group_count");
                                    String photoscount = photo_count.optString("photos_count");
                                    String videoscount = video_count.optString("videos_count");
                                    int profilepercentage = Integer.parseInt(profile_per.substring(0,profile_per.length()-1));

                                    AppConfig.profile_details_List.add(new ProfileDetailsDataModel(postcount, friendscount, groupcount, photoscount, videoscount, profilepercentage, email));
                                   /* Glide.with(context)
                                            .load(user_image_path)
                                            .crossFade()
                                            .placeholder(R.drawable.profile_placeholder)
                                            .error(R.drawable.error_icon)
                                            .bitmapTransform(new GlideCircleTransformation(context))
                                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                                            .into(edit_profile_pic);

                                    profile_name.setText(first_name+" "+last_name);
                                    place.setText(city+", "+state);
                                    post_count.setText(postcount);
                                    friends_count.setText(friendscount);
                                    photos_count.setText(photoscount);
                                    videos_count.setText(videoscount);
                                    groups_count.setText(groupcount);

                                    profile_percentage.setProgress(Integer.parseInt(profile_per.replace("%","")));*/

                                    AppConfig.profile_percentage = Integer.parseInt(profile_per.substring(0,profile_per.length()-1));
                                    AppConfig.account_setting_List.add(new AccountSettingsDataModel(user_image_path, first_name, last_name, country_code, phone, date_of_birth, home_airport, country_name, pin_code, address, city, state));
                                }

                                if(null != result_jsonArray)
                                {

                                    for(int i = 0; i<result_jsonArray.length(); i++){
                                        JSONObject result_jsonArray_jsonObject = result_jsonArray.optJSONObject(i);
                                        String message = result_jsonArray_jsonObject.optString("message");
                                        String user_id = result_jsonArray_jsonObject.optString("user_id");
                                        String first_name = result_jsonArray_jsonObject.optString("first_name");
                                        String value = result_jsonArray_jsonObject.optString("value");
                                        String type = result_jsonArray_jsonObject.optString("type");
                                        String likes = result_jsonArray_jsonObject.optString("likes");
                                        String created_date = result_jsonArray_jsonObject.optString("created_date");
                                        String post_id = result_jsonArray_jsonObject.optString("post_id");
                                        String comments_count = result_jsonArray_jsonObject.optString("comments_count");
                                        String user_image = result_jsonArray_jsonObject.optString("user_image");
                                        String images = result_jsonArray_jsonObject.optString("images");
                                        if(null!=images){
                                            images_List = Arrays.asList(images.split(","));
                                        }
                                        String videos = result_jsonArray_jsonObject.optString("video");
                                        if(null!=videos){
                                            videos_List = Arrays.asList(videos.split(","));
                                        }
                                        comment_List = new ArrayList<>();
                                        if(!comment_List.isEmpty())
                                            comment_List.clear();
                                        JSONArray comments_message_array = result_jsonArray_jsonObject.optJSONArray("comments_message");
                                        for(int j=0; j<comments_message_array.length(); j++){
                                            JSONObject comments_message_jsonObject = comments_message_array.optJSONObject(j);
                                            String comment = comments_message_jsonObject.optString("comment");
                                            String time = comments_message_jsonObject.optString("time");
                                            String comment_id = comments_message_jsonObject.optString("comment_id");
                                            String comment_first_name = comments_message_jsonObject.optString("first_name");
                                            String comment_last_name = comments_message_jsonObject.optString("last_name");
                                            String comment_user_id = comments_message_jsonObject.optString("user_id");
                                            String comment_image = comments_message_jsonObject.optString("image");

                                            comment_List.add(new CommentListItem(comment_image, comment_first_name+" "+comment_last_name, comment, time, comment_user_id, comment_id));
                                        }

                                        if(user_id.equalsIgnoreCase(AppConfig.getUser_id()))
                                            AppConfig.home_Profile_List.add(new Home(message, user_id, first_name, value, type, likes, created_date,  post_id, comments_count, user_image, images_List, videos_List, comment_List, AppConfig.account_setting_List, profile_details_List));

                                        AppConfig.home_List.add(new Home(message, user_id, first_name, value, type, likes, created_date,  post_id, comments_count, user_image, images_List, videos_List, comment_List, AppConfig.account_setting_List, profile_details_List));

                                    }

                                    // notifying list adapter about data changes
                                    // so that it renders the list view with updated data
                                    //AppConfig.volleyRecyclerViewAdapter.notifyDataSetChanged();
                                    if(null!=AppConfig.volleyRecyclerViewAdapter)
                                        AppConfig.volleyRecyclerViewAdapter.notifyDataSetChanged();
                                    if(null!=AppConfig.volleyCommentListAdapter)
                                        AppConfig.volleyCommentListAdapter.notifyDataSetChanged();
                                }

                            }else {
                                // Error in login. Get the error message
                                String errorMsg = jObj.getString("data");
                                Toast.makeText(context,
                                        errorMsg, Toast.LENGTH_LONG).show();
                            }

                        }catch (JSONException e) {
                            // JSON error
                            e.printStackTrace();
                            Toast.makeText(context, "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                        //volleySwipeRefreshLayout.setRefreshing(false);
                        if(null!=AppConfig.volleySwipeRefreshLayout)
                            AppConfig.volleySwipeRefreshLayout.setRefreshing(false);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if(null!=AppConfig.volleySwipeRefreshLayout)
                            AppConfig.volleySwipeRefreshLayout.setRefreshing(false);
                        // volleySwipeRefreshLayout.setRefreshing(false);
                        //Toast.makeText(StartupScreen.this,error.toString(),Toast.LENGTH_LONG).show();
                        VolleyLog.d(signedInHome_Profile_TravelConnectProfile_string_req, "Error: " + error.getMessage());
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                //params.put(KEY_USER_ID, USER_ID);
                params.put(KEY_USER_ID, AppConfig.getUser_id());
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
        AppController.getInstance().addToRequestQueue(stringRequest, signedInHome_Profile_TravelConnectProfile_string_req);
    }

    public static void signedInHome_Profile_Photos(){
        // Tag used to cancel the request
        final String  signedInHome_Profile_Photos_string_req = "signedInHome_Profile_Photos_string_req";

        pDialog = new ProgressDialog(context);
        /*try {
            if(null!=jsonObject){
                JSONObject jsonObject1 = new JSONObject(jsonObject.toString());
                if(jsonObject1.has(KEY_USER_ID))
                {
                    USER_ID = jsonObject1.get(KEY_USER_ID).toString();
                    if(null != AppConfig.urphoto_List)
                        AppConfig.urphoto_List.clear();
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }*/

        pDialog.setMessage("Showing Photos...");
        pDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL.concat("urphotos"),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(StartupScreen.this,response,Toast.LENGTH_LONG).show();
                        Log.d(signedInHome_Profile_Photos_string_req, response.toString());
                        pDialog.hide();

                        try {

                            JSONObject jObj = new JSONObject(response);
                            boolean status = jObj.getBoolean("status");

                            // Check for error node in json
                            if (status) {

                                if(!AppConfig.urphoto_List.isEmpty())
                                    AppConfig.urphoto_List.clear();
                                JSONObject data = jObj.optJSONObject("data");
                                JSONArray url = data.optJSONArray("url");
                                JSONArray id = data.optJSONArray("id");

                                for(int i=0; i<url.length();i++)
                                {
                                    String urphotos = url.optString(i);
                                    String ids = id.getString(i);
                                    urphoto_List.add(new PhotoDataModel(urphotos, ids));
                                }
                                AppConfig.volleyRecyclerViewAdapter.notifyDataSetChanged();
                            } else {
                                // Error in downloading. Get the error message
                                String errorMsg = jObj.getString("data");
                                Toast.makeText(context,
                                        errorMsg, Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            // JSON error
                            e.printStackTrace();
                            Toast.makeText(context, "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(StartupScreen.this,error.toString(),Toast.LENGTH_LONG).show();
                        VolleyLog.d(signedInHome_Profile_Photos_string_req, "Error: " + error.getMessage());
                        pDialog.hide();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put(KEY_USER_ID, AppConfig.getUser_id());
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
        AppController.getInstance().addToRequestQueue(stringRequest, signedInHome_Profile_Photos_string_req);
    }

    public static void signedInHome_Profile_Videos(){
        // Tag used to cancel the request
        final String  signedInHome_Profile_Videos_string_req = "signedInHome_Profile_Videos_string_req";

        pDialog = new ProgressDialog(context);
        try {
            if(!AppConfig.getUser_id().isEmpty()){
                JSONObject jsonObject1 = new JSONObject(AppConfig.getUser_id());
                if(jsonObject1.has(KEY_USER_ID))
                {
                    USER_ID = jsonObject1.get(KEY_USER_ID).toString();
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        pDialog.setMessage("Showing Videos...");
        pDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL.concat("urvideos"),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(StartupScreen.this,response,Toast.LENGTH_LONG).show();
                        Log.d(signedInHome_Profile_Videos_string_req, response.toString());
                        pDialog.hide();

                        try {
                            JSONObject jObj = new JSONObject(response);
                            boolean status = jObj.getBoolean("status");

                            // Check for error node in json
                            if (status) {

                                if(!AppConfig.urvideo_List.isEmpty())
                                    AppConfig.urvideo_List.clear();
                                JSONObject data = jObj.optJSONObject("data");
                                JSONArray video_thumbnail_jsonArray = data.optJSONArray("video_thumbnail");
                                JSONArray url_jsonArray = data.optJSONArray("url");
                                JSONArray id_jsonArray = data.optJSONArray("id");
                                for(int i=0; i<url_jsonArray.length();i++){
                                    String url = url_jsonArray.optString(i);
                                    String id = id_jsonArray.optString(i);
                                    String thumbnail = video_thumbnail_jsonArray.optString(i);
                                    AppConfig.urvideo_List.add(new VideoDataModel(url, thumbnail, id));
                                }

                                AppConfig.volleyRecyclerViewAdapter.notifyDataSetChanged();

                            } else {
                                // Error in downloading. Get the error message
                                String errorMsg = jObj.getString("data");
                                Toast.makeText(context,
                                        errorMsg, Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            // JSON error
                            e.printStackTrace();
                            Toast.makeText(context, "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(StartupScreen.this,error.toString(),Toast.LENGTH_LONG).show();
                        VolleyLog.d(signedInHome_Profile_Videos_string_req, "Error: " + error.getMessage());
                        pDialog.hide();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put(KEY_USER_ID, USER_ID);
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
        AppController.getInstance().addToRequestQueue(stringRequest, signedInHome_Profile_Videos_string_req);
    }

    public static void signedInHome_Profile_Friends(JSONObject jsonObject, final YourFriendsListAdapter adapter, final RecyclerView.Adapter mAdapter){
        // Tag used to cancel the request
        final String  signedInHome_Profile_Friends_string_req = "signedInHome_Profile_Friends_string_req";

        //pDialog = new ProgressDialog(context);
        try {
            if(null!=jsonObject){
                JSONObject jsonObject1 = new JSONObject(jsonObject.toString());
                if(jsonObject1.has(KEY_USER_ID))
                {
                    if(!AppConfig.yourFriendsListItems.isEmpty() && !AppConfig.yourFriendsSuggestionListItems.isEmpty()){
                        yourFriendsListItems.clear();
                        yourFriendsSuggestionListItems.clear();
                    }
                    USER_ID = jsonObject1.get(KEY_USER_ID).toString();
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

       /* pDialog.setMessage("Showing Friends...");
        pDialog.show();*/

        StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL.concat("userfriends"),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(StartupScreen.this,response,Toast.LENGTH_LONG).show();
                        Log.d(signedInHome_Profile_Friends_string_req, response.toString());
                        //pDialog.hide();

                        try {
                            JSONObject jObj = new JSONObject(response);
                            boolean status = jObj.getBoolean("status");
                            String count_posts = jObj.optString("count_posts");
                            JSONObject pending_request = jObj.optJSONObject("pending_request");
                            String pending_request1 = pending_request.optString("pending_request");

                            // Check for error node in json
                            if (status) {

                                // Now store the user in SQLite
                                JSONObject data = jObj.optJSONObject("data");
                                String count_friends = data.optString("count_friends");
                                JSONArray my_friends = data.optJSONArray("my_friends");
                                for(int i=0; i<my_friends.length(); i++){
                                    JSONObject my_friends_jsonObject = my_friends.optJSONObject(i);
                                    String user_id = my_friends_jsonObject.optString("user_id");
                                    String first_name = my_friends_jsonObject.optString("first_name");
                                    String last_name = my_friends_jsonObject.optString("last_name");
                                    String created_datetime = my_friends_jsonObject.optString("created_datetime");
                                    String city = my_friends_jsonObject.optString("city");
                                    String state = my_friends_jsonObject.optString("state");
                                    String country_name = my_friends_jsonObject.optString("country_name");
                                    String user_image_path = my_friends_jsonObject.optString("user_image_path");

                                    AppConfig.yourFriendsListItems.add(new YourFriendsListItems(user_id, first_name, last_name, user_image_path, created_datetime, city, state, country_name, pending_request1, count_friends));
                                }
                                JSONArray all_users = data.optJSONArray("all_users");
                                for(int i=0; i<all_users.length(); i++){
                                    JSONObject my_friends_jsonObject = all_users.optJSONObject(i);
                                    String user_id = my_friends_jsonObject.optString("user_id");
                                    String first_name = my_friends_jsonObject.optString("first_name");
                                    String last_name = my_friends_jsonObject.optString("last_name");
                                    String created_datetime = my_friends_jsonObject.optString("created_datetime");
                                    String city = my_friends_jsonObject.optString("city");
                                    String state = my_friends_jsonObject.optString("state");
                                    String country_name = my_friends_jsonObject.optString("country_name");
                                    String user_image_path = my_friends_jsonObject.optString("user_image_path");

                                    AppConfig.yourFriendsSuggestionListItems.add(new YourFriendsSuggestionListItems(user_id, first_name, last_name, user_image_path, created_datetime, city, state, country_name));
                                }
                                adapter.notifyDataSetChanged();
                                mAdapter.notifyDataSetChanged();
                            } else {
                                // Error in login. Get the error message
                                String errorMsg = jObj.getString("data");
                                Toast.makeText(context,
                                        errorMsg, Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            // JSON error
                            e.printStackTrace();
                            Toast.makeText(context, "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(StartupScreen.this,error.toString(),Toast.LENGTH_LONG).show();
                        VolleyLog.d(signedInHome_Profile_Friends_string_req, "Error: " + error.getMessage());
                        //pDialog.hide();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put(KEY_USER_ID, USER_ID);
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
        AppController.getInstance().addToRequestQueue(stringRequest, signedInHome_Profile_Friends_string_req);
    }

    public static void signedInHome_Profile_addFriends(JSONObject jsonObject, final YourFriendsListAdapter adapter, final RecyclerView.Adapter mAdapter){
        //Tag used to cancel the request
        final String  signedInHome_Profile_Friends_string_req = "signedInHome_Profile_Friends_string_req";

        //pDialog = new ProgressDialog(context);
        try {
            if(null!=jsonObject){
                JSONObject jsonObject1 = new JSONObject(jsonObject.toString());
                if(jsonObject1.has(KEY_USER_ID))
                {
                    USER_ID = jsonObject1.get(KEY_USER_ID).toString();
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

       /* pDialog.setMessage("Showing Friends...");
        pDialog.show();*/

        StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL.concat("userfriends"),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(StartupScreen.this,response,Toast.LENGTH_LONG).show();
                        Log.d(signedInHome_Profile_Friends_string_req, response.toString());
                        //pDialog.hide();

                        try {
                            JSONObject jObj = new JSONObject(response);
                            boolean status = jObj.getBoolean("status");
                            String count_friends = jObj.optString("count_friends");
                            String count_posts = jObj.optString("count_posts");
                            JSONObject pending_request = jObj.optJSONObject("pending_request");
                            String pending_request1 = pending_request.optString("pending_request");

                            // Check for error node in json
                            if (status) {

                                // Now store the user in SQLite
                                JSONObject data = jObj.optJSONObject("data");
                                JSONArray my_friends = data.optJSONArray("my_friends");
                                for(int i=0; i<my_friends.length(); i++){
                                    JSONObject my_friends_jsonObject = my_friends.optJSONObject(i);
                                    String user_id = my_friends_jsonObject.optString("user_id");
                                    String first_name = my_friends_jsonObject.optString("first_name");
                                    String last_name = my_friends_jsonObject.optString("last_name");
                                    String created_datetime = my_friends_jsonObject.optString("created_datetime");
                                    String city = my_friends_jsonObject.optString("city");
                                    String state = my_friends_jsonObject.optString("state");
                                    String country_name = my_friends_jsonObject.optString("country_name");
                                    String user_image_path = my_friends_jsonObject.optString("user_image_path");

                                    AppConfig.yourFriendsListItems.add(new YourFriendsListItems(user_id, first_name, last_name, user_image_path, created_datetime, city, state, country_name, pending_request1, count_friends));
                                }
                                JSONArray all_users = data.optJSONArray("all_users");
                                for(int i=0; i<all_users.length(); i++){
                                    JSONObject my_friends_jsonObject = all_users.optJSONObject(i);
                                    String user_id = my_friends_jsonObject.optString("user_id");
                                    String first_name = my_friends_jsonObject.optString("first_name");
                                    String last_name = my_friends_jsonObject.optString("last_name");
                                    String created_datetime = my_friends_jsonObject.optString("created_datetime");
                                    String city = my_friends_jsonObject.optString("city");
                                    String state = my_friends_jsonObject.optString("state");
                                    String country_name = my_friends_jsonObject.optString("country_name");
                                    String user_image_path = my_friends_jsonObject.optString("user_image_path");

                                    AppConfig.yourFriendsSuggestionListItems.add(new YourFriendsSuggestionListItems(user_id, first_name, last_name, user_image_path, created_datetime, city, state, country_name));
                                }
                                adapter.notifyDataSetChanged();
                                mAdapter.notifyDataSetChanged();
                            } else {
                                // Error in login. Get the error message
                                String errorMsg = jObj.getString("data");
                                Toast.makeText(context,
                                        errorMsg, Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            // JSON error
                            e.printStackTrace();
                            Toast.makeText(context, "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(StartupScreen.this,error.toString(),Toast.LENGTH_LONG).show();
                        VolleyLog.d(signedInHome_Profile_Friends_string_req, "Error: " + error.getMessage());
                        //pDialog.hide();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put(KEY_USER_ID, USER_ID);
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
        AppController.getInstance().addToRequestQueue(stringRequest, signedInHome_Profile_Friends_string_req);
    }

    public static void signedInHome_Profile_UploadPhotos(JSONObject jsonObject){
        // Tag used to cancel the request
        final Date date= new Date();
        final String  signedInHome_Profile_UploadPhotos_Multipart_req = "signedInHome_Profile_UploadPhotos_Multipart_req";

        try {
            if(null!=jsonObject){
                JSONObject jsonObject1 = new JSONObject(jsonObject.toString());
                JSONArray jsonArray1;
                IMAGEPATH_LIST = new ArrayList<String>();
                if(!IMAGEPATH_LIST.isEmpty())
                    IMAGEPATH_LIST.clear();
                if(jsonObject1.has(KEY_USER_ID) && jsonObject1.has(KEY_IMAGEPATH))
                {
                    USER_ID = jsonObject1.optString(KEY_USER_ID);
                    jsonArray1 = jsonObject1.optJSONArray(KEY_IMAGEPATH);
                    for (int i=0; i<jsonArray1.length(); i++) {
                        IMAGEPATH_LIST.add(jsonArray1.getString(i));
                    }
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        final VolleyMultipartRequest stringRequest = new VolleyMultipartRequest(Request.Method.POST, BASE_URL.concat("upload_images"),
                new Response.Listener<String>() {


                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(StartupScreen.this,response,Toast.LENGTH_LONG).show();
                        Log.d(signedInHome_Profile_UploadPhotos_Multipart_req, response.toString());

                        try {
                            JSONObject jObj = new JSONObject(response);
                            boolean status = jObj.getBoolean("status");

                            // Check for error node in json
                            if (status) {

                                JSONObject data = jObj.optJSONObject("data");
                                String message = data.optString("message");
                                signedInHome_Profile_Photos();

                            } else {
                                // Error in postdata
                                String errorMsg = jObj.optJSONObject("data").toString();
                                Toast.makeText(context,
                                        errorMsg, Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            // JSON error
                            e.printStackTrace();
                            Toast.makeText(context, "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(StartupScreen.this,error.toString(),Toast.LENGTH_LONG).show();
                        VolleyLog.d(signedInHome_Profile_UploadPhotos_Multipart_req, "Error: " + error.getMessage());
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put(KEY_USER_ID, USER_ID);
                return params;
            }
            @Override
            protected Map<String, DataPart> getByteData() {
                Map<String, DataPart> params = new HashMap<>();
                // file name could found file base or direct access from real path
                // for now just get bitmap data from ImageView

                for(int i = 0 ; i< IMAGEPATH_LIST.size() ; i++) {
                    params.put("userFiles[" + i + "]", new DataPart("image_" + date.getTime() + "_.mp4", AppHelper.getFileDataFromImagePath(context, IMAGEPATH_LIST.get(i)), "image/jpeg"));
                }

                return params;
            }

        };

        /*RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);*/

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(stringRequest, signedInHome_Profile_UploadPhotos_Multipart_req);
    }

    public static void signedInHome_Profile_UploadVideos(JSONObject jsonObject){
        // Tag used to cancel the request
        final String  signedInHome_Profile_UploadVideos_Multipart_req = "signedInHome_Profile_UploadVideos_Multipart_req";
        final Date date= new Date();

        try {
            if(null!=jsonObject){
                JSONObject jsonObject1 = new JSONObject(jsonObject.toString());
                JSONArray jsonArray1;
                IMAGEPATH_LIST = new ArrayList<String>();
                if(jsonObject1.has(KEY_USER_ID) && jsonObject1.has(KEY_IMAGEPATH))
                {
                    USER_ID = jsonObject1.optString(KEY_USER_ID);
                    jsonArray1 = jsonObject1.optJSONArray(KEY_IMAGEPATH);
                    for (int i=0; i<jsonArray1.length(); i++) {
                        IMAGEPATH_LIST.add(jsonArray1.getString(i));
                    }
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        final VolleyMultipartRequest stringRequest = new VolleyMultipartRequest(Request.Method.POST, BASE_URL.concat("upload_videos"),
                new Response.Listener<String>() {


                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(StartupScreen.this,response,Toast.LENGTH_LONG).show();
                        Log.d(signedInHome_Profile_UploadVideos_Multipart_req, response.toString());

                        try {
                            JSONObject jObj = new JSONObject(response);
                            boolean status = jObj.getBoolean("status");

                            // Check for error node in json
                            if (status) {

                                JSONObject data = jObj.optJSONObject("data");
                                String message = data.optString("message");
                                signedInHome_Profile_Videos();

                            } else {
                                // Error in postdata
                                String errorMsg = jObj.optJSONObject("data").toString();
                                Toast.makeText(context,
                                        errorMsg, Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            // JSON error
                            e.printStackTrace();
                            Toast.makeText(context, "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(StartupScreen.this,error.toString(),Toast.LENGTH_LONG).show();
                        VolleyLog.d(signedInHome_Profile_UploadVideos_Multipart_req, "Error: " + error.getMessage());
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put(KEY_USER_ID, USER_ID);
                return params;
            }
            @Override
            protected Map<String, DataPart> getByteData() {
                Map<String, DataPart> params = new HashMap<>();
                // file name could found file base or direct access from real path
                // for now just get bitmap data from ImageView


                for(int i = 0 ; i< IMAGEPATH_LIST.size() ; i++) {
                    try {
                        params.put("images[]", new DataPart("video_"+date.getTime()+"_.mp4", AppHelper.getFileDataFromVideoPath(context, IMAGEPATH_LIST.get(i)), "video/mp4"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return params;
            }

        };

        /*RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);*/

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(stringRequest, signedInHome_Profile_UploadVideos_Multipart_req);
    }

    public static void signedInHome_Profile_CreateGroup(JSONObject jsonObject){

        // Tag used to cancel the request
        final String  signedInHome_Profile_CreateGroup_Multipart_req = "signedInHome_Profile_CreateGroup_Multipart_req";

        try {
            if(null!=jsonObject){
                JSONObject jsonObject1 = new JSONObject(jsonObject.toString());
                JSONArray jsonArray1;
                IMAGEPATH_LIST = new ArrayList<String>();
                if(jsonObject1.has(KEY_USER_ID) && jsonObject1.has(KEY_GROUP_NAME) && jsonObject1.has(KEY_GROUP_DESC) && jsonObject1.has(KEY_IMAGEPATH))
                {
                    USER_ID = jsonObject1.optString(KEY_USER_ID);
                    jsonArray1 = jsonObject1.optJSONArray(KEY_IMAGEPATH);
                    for (int i=0; i<jsonArray1.length(); i++) {
                        IMAGEPATH_LIST.add(jsonArray1.getString(i));
                    }
                    GROUP_ID = jsonObject1.optString(KEY_GROUP_ID);
                    GROUP_DESC = jsonObject1.optString(KEY_GROUP_DESC);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        final VolleyMultipartRequest stringRequest = new VolleyMultipartRequest(Request.Method.POST, BASE_URL.concat("creategroup"),
                new Response.Listener<String>() {


                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(StartupScreen.this,response,Toast.LENGTH_LONG).show();
                        Log.d(signedInHome_Profile_CreateGroup_Multipart_req, response.toString());

                        try {
                            JSONObject jObj = new JSONObject(response);
                            boolean status = jObj.getBoolean("status");

                            // Check for error node in json
                            if (status) {

                                JSONObject data = jObj.optJSONObject("data");
                                String group_id = data.optString("group_id");

                            } else {
                                // Error in postdata
                                String errorMsg = jObj.optJSONObject("data").toString();
                                Toast.makeText(context,
                                        errorMsg, Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            // JSON error
                            e.printStackTrace();
                            Toast.makeText(context, "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(StartupScreen.this,error.toString(),Toast.LENGTH_LONG).show();
                        VolleyLog.d(signedInHome_Profile_CreateGroup_Multipart_req, "Error: " + error.getMessage());
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put(KEY_USER_ID, USER_ID);
                params.put(KEY_GROUP_ID, GROUP_ID);
                params.put(KEY_GROUP_DESC, GROUP_DESC);
                return params;
            }
            @Override
            protected Map<String, DataPart> getByteData() {
                Map<String, DataPart> params = new HashMap<>();
                // file name could found file base or direct access from real path
                // for now just get bitmap data from ImageView

                for(int i = 0 ; i< IMAGEPATH_LIST.size() ; i++) {
                    params.put("banner_image", new DataPart("file.jpg", AppHelper.getFileDataFromImagePath(context, IMAGEPATH_LIST.get(i)), "image/jpeg"));
                }
                return params;
            }

        };

        /*RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);*/

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(stringRequest, signedInHome_Profile_CreateGroup_Multipart_req);
    }

    public static void signedInHome_Profile_URGroup(JSONObject jsonObject){
        // Tag used to cancel the request
        final String  signedInHome_Profile_URGroup_string_req = "signedInHome_Profile_URGroup_string_req";


        try {
            if(null!=jsonObject){
                JSONObject jsonObject1 = new JSONObject(jsonObject.toString());
                if(jsonObject1.has(KEY_USER_ID))
                {
                    USER_ID = jsonObject1.get(KEY_USER_ID).toString();
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL.concat("urgroup"),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(StartupScreen.this,response,Toast.LENGTH_LONG).show();
                        Log.d(signedInHome_Profile_URGroup_string_req, response.toString());

                        //Home home = new Home();
                        try {
                            JSONObject jObj = new JSONObject(response);
                            boolean status = jObj.optBoolean("status");

                            List<String> images_List = null;
                            List<String> videos_List = null;

                            // Check for error node in json
                            if (status) {

                                JSONObject data_jsonObject = jObj.optJSONObject("data");
                                JSONArray result_jsonArray = data_jsonObject.optJSONArray("my_friends");
                                if(null != result_jsonArray)
                                {
                                    for(int i = 0; i<result_jsonArray.length(); i++){
                                        JSONObject result_jsonArray_jsonObject = result_jsonArray.optJSONObject(i);
                                        String user_id = result_jsonArray_jsonObject.optString("user_id");
                                        String group_name = result_jsonArray_jsonObject.optString("first_name");
                                        String group_pic = result_jsonArray_jsonObject.optString("value");
                                        String group_desc = result_jsonArray_jsonObject.optString("type");
                                        String group_id = result_jsonArray_jsonObject.optString("likes");
                                        String group_slug = result_jsonArray_jsonObject.optString("created_date");
                                        String group_image_path = result_jsonArray_jsonObject.optString("post_id");

                                        /*home.setMessage(message);
                                        home.setUser_id(user_id);
                                        home.setFirst_name(first_name);
                                        home.setValue(value);
                                        home.setType(type);
                                        home.setLikes(likes);
                                        home.setCreated_date(created_date);
                                        home.setPost_id(post_id);
                                        home.setComments_count(comments_count);
                                        home.setUser_image(user_image);
                                        home.setImages(images_List);
                                        home.setVideos(videos_List);*/

                                        AppConfig.group_List.add(new Profile(user_id, group_name, group_pic, group_desc, group_id, group_slug,  group_image_path));

                                    }
                                    // notifying list adapter about data changes
                                    // so that it renders the list view with updated data
                                    volleyAdapter.notifyDataSetChanged();
                                }

                            } else {
                                // Error in login. Get the error message
                                String errorMsg = jObj.getString("data");
                                Toast.makeText(context,
                                        errorMsg, Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            // JSON error
                            e.printStackTrace();
                            Toast.makeText(context, "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                        volleySwipeRefreshLayout.setRefreshing(false);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        volleySwipeRefreshLayout.setRefreshing(false);
                        //Toast.makeText(StartupScreen.this,error.toString(),Toast.LENGTH_LONG).show();
                        VolleyLog.d(signedInHome_Profile_URGroup_string_req, "Error: " + error.getMessage());
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put(KEY_USER_ID, USER_ID);
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
        AppController.getInstance().addToRequestQueue(stringRequest, signedInHome_Profile_URGroup_string_req);
    }

    public static void settings_Your_Prefernces(JSONObject jsonObject, TextView question){
        volleyQuestionListAdapter = adapter_checkbox;
        volleyQuestionListAdapter_radioButton = adapter_radiobutton;
        volleyQuestionListAdapter_InputBox = adapter_inputbox;
        //volleySwipeRefreshLayout = swipeRefreshLayout;
        // Tag used to cancel the request
        final String  settings_Your_Prefernces = "settings_Your_Prefernces";
        final TextView question_list = question;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, BASE_URL.concat("question_list"),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(StartupScreen.this,response,Toast.LENGTH_LONG).show();
                        Log.d(settings_Your_Prefernces, response.toString());

                        //Home home = new Home();
                        try {
                            JSONObject jObj = new JSONObject(response);
                            boolean status = jObj.optBoolean("status");

                            ArrayList<String> option_List = null;

                            // Check for error node in json
                            if (status) {

                                if(!AppConfig.question_List_checkBox.isEmpty())
                                {
                                    AppConfig.question_List_checkBox.clear();
                                }
                                if(!AppConfig.question_List_radioButton.isEmpty())
                                {
                                    AppConfig.question_List_radioButton.clear();
                                }
                                if(!AppConfig.question_List_inputBox.isEmpty())
                                {
                                    AppConfig.question_List_inputBox.clear();
                                }
                                JSONObject data_jsonObject = jObj.optJSONObject("data");
                                for(int i=1; i<=20; i++){

                                }
                                if(null != data_jsonObject) {
                                    for (int i = 1; i <= 20; i++) {
                                        JSONObject result_jsonObject = data_jsonObject.optJSONObject(i+"");
                                        String qid = result_jsonObject.optString("qid");
                                        String question = result_jsonObject.optString("question");
                                        String question_type = result_jsonObject.optString("question_type");
                                        String options = result_jsonObject.optString("options");
                                        if (null != options) {
                                            option_List = new ArrayList(Arrays.asList(options.split(",")));
                                        }
                                        if(question_type.equals("1"))
                                        {
                                            AppConfig.question_List_checkBox.add(new QuestionListCheckBoxDataModel(qid, question, question_type, option_List));
                                        }
                                        if(question_type.equals("2"))
                                        {
                                            AppConfig.question_List_radioButton.add(new QuestionListRadioButtonDataModel(qid, question, question_type, option_List));
                                        }
                                        if(question_type.equals("3"))
                                        {
                                            AppConfig.question_List_inputBox.add(new QuestionListInputBoxDataModel(qid, question, question_type, option_List));
                                        }
                                        AppConfig.question_List.add(new YourPreferencesDataModel(qid, question, question_type, option_List));
                                    }
                                    for(int j=0; j<AppConfig.question_List_checkBox.get(0).options.size(); j++)
                                    {
                                        AppConfig.question_List_checkBox_optionType.add(new QuestionListCheckBoxDataModelOptions(AppConfig.question_List_checkBox.get(0).options.get(j)));
                                    }

                                    for(int j=0; j<AppConfig.question_List_radioButton.get(0).options.size(); j++)
                                    {
                                        AppConfig.question_List_radioButton_optionType.add(new QuestionListRadioButtonDataModelOptions(AppConfig.question_List_radioButton.get(0).options.get(j)));
                                    }

                                    for(int j=0; j<AppConfig.question_List_inputBox.get(0).options.size(); j++)
                                    {
                                        AppConfig.question_List_inputBox_optionType.add(new QuestionListInputBoxDataModelOptions(AppConfig.question_List_inputBox.get(0).options.get(j)));
                                    }

                                    // notifying list adapter about data changes
                                    // so that it renders the list view with updated data
                                    if(volleyQuestionListAdapter!=null){
                                        question_list.setText(AppConfig.question_List_checkBox.get(0).question);
                                        volleyQuestionListAdapter.notifyDataSetChanged();
                                    }

                                    if(volleyQuestionListAdapter_radioButton!=null){
                                        question_list.setText(AppConfig.question_List_radioButton.get(0).question);
                                        volleyQuestionListAdapter_radioButton.notifyDataSetChanged();
                                    }

                                    if(volleyQuestionListAdapter_InputBox!=null)
                                    {
                                        question_list.setText(AppConfig.question_List_inputBox.get(0).question);
                                        volleyQuestionListAdapter_InputBox.notifyDataSetChanged();
                                    }

                                }

                            }else {
                                // Error in login. Get the error message
                                String errorMsg = jObj.getString("data");
                                Toast.makeText(context,
                                        errorMsg, Toast.LENGTH_LONG).show();
                            }

                        } catch (JSONException e1) {
                            e1.printStackTrace();
                        }
                        //volleySwipeRefreshLayout.setRefreshing(false);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //volleySwipeRefreshLayout.setRefreshing(false);
                        //Toast.makeText(StartupScreen.this,error.toString(),Toast.LENGTH_LONG).show();
                        VolleyLog.d(settings_Your_Prefernces, "Error: " + error.getMessage());
                    }
                }){

            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }

        };
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(stringRequest, settings_Your_Prefernces);
    }

    public static void settings_Account_settings(final JSONObject jsonObject, final FragmentManager fragmentManager){
        // Tag used to cancel the request
        final String  settings_Account_settings_Multipart_req = "settings_Account_settings_Multipart_req";

        pDialog = new ProgressDialog(context);
        try {
            if(null!=jsonObject){
                JSONObject jsonObject1 = new JSONObject(jsonObject.toString());
                if(jsonObject1.has(KEY_USER_ID) && jsonObject.has(KEY_FIRST_NAME) && jsonObject1.has(KEY_LAST_NAME) && jsonObject1.has(KEY_COUNTRY_CODE) && jsonObject.has(KEY_DATE_OF_BIRTH) && jsonObject1.has(KEY_PHONE))
                {
                    USER_ID = jsonObject1.optString(KEY_USER_ID);
                    FIRST_NAME = jsonObject1.optString(KEY_FIRST_NAME);
                    LAST_NAME = jsonObject1.optString(KEY_LAST_NAME);
                    COUNTRY_NAME = jsonObject1.optString(KEY_COUNTRY_NAME);
                    PIN_CODE = jsonObject1.optString(KEY_PIN_CODE);
                    ADDRESS = jsonObject1.optString(KEY_ADDRESS);
                    STATE = jsonObject1.optString(KEY_STATE);
                    COUNTRY_CODE = jsonObject1.optString(KEY_COUNTRY_CODE);
                    DATE_OF_BIRTH = jsonObject1.optString(KEY_DATE_OF_BIRTH);
                    PHONE = jsonObject1.optString(KEY_PHONE);
                    HOME_AIRPORT = jsonObject1.optString(KEY_HOME_AIRPORT);
                    CITY = jsonObject1.optString(KEY_CITY).toString();
                    IMAGE = jsonObject1.optString(KEY_IMAGE);

                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        pDialog.setMessage("Updating profile details...");
        pDialog.show();

        final VolleyMultipartRequest stringRequest = new VolleyMultipartRequest(Request.Method.POST, BASE_URL.concat("editprofile"),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(StartupScreen.this,response,Toast.LENGTH_LONG).show();
                        Log.d(settings_Account_settings_Multipart_req, response.toString());
                        pDialog.hide();

                        try {
                            JSONObject jObj = new JSONObject(response);
                            boolean status = jObj.getBoolean("status");

                            // Check for error node in json
                            if (status) {
                                // user successfully Registered

                                JSONObject message = jObj.optJSONObject("message");
                                signedInHome_Profile_TravelConnectProfile();
                                if (fragmentManager.getBackStackEntryCount() > 0) {
                                    fragmentManager.popBackStack();
                                }

                            } else {
                                // Error in Registeration. Get the error message
                                String data = jObj.optString("message");
                                Toast.makeText(context,
                                        data, Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            // JSON error
                            e.printStackTrace();
                            Toast.makeText(context, "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(StartupScreen.this,error.toString(),Toast.LENGTH_LONG).show();
                        VolleyLog.d(settings_Account_settings_Multipart_req, "Error: " + error.getMessage());
                        pDialog.hide();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put(KEY_USER_ID, USER_ID);
                params.put(KEY_FIRST_NAME, FIRST_NAME);
                params.put(KEY_COUNTRY_CODE, COUNTRY_CODE);
                params.put(KEY_PHONE, PHONE);
                params.put(KEY_DATE_OF_BIRTH, DATE_OF_BIRTH);
                params.put(KEY_HOME_AIRPORT, HOME_AIRPORT);
                params.put(KEY_COUNTRY_NAME, COUNTRY_NAME);
                params.put(KEY_PIN_CODE, PIN_CODE);
                params.put(KEY_ADDRESS, ADDRESS);
                params.put(KEY_CITY, CITY);
                params.put(KEY_STATE, STATE);
                /*params.put(KEY_IMAGE, IMAGE);*/
                params.put(KEY_LAST_NAME, LAST_NAME);
                return params;
            }
            @Override
            protected Map<String, VolleyMultipartRequest.DataPart> getByteData() {
                Map<String, VolleyMultipartRequest.DataPart> params = new HashMap<>();
                // file name could found file base or direct access from real path
                // for now just get bitmap data from ImageView


                if(!IMAGE.isEmpty())
                    //for(int i = 0 ; i< IMAGEPATH_LIST.size() ; i++) {
                    params.put("image", new VolleyMultipartRequest.DataPart("file.jpg", AppHelper.getFileDataFromImagePath(context, IMAGE), "image/jpeg"));
                //}
                return params;
            }

        };

        /*RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);*/

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(stringRequest, settings_Account_settings_Multipart_req);
    }

    public static void settings_Account_settings_YourPreference_QuestionSubmit(final JSONObject jsonObject, final ProgressBar profile_percentage, final TextView profile_percentage_value){
        // Tag used to cancel the request
        final String  settings_Account_settings_YourPreference_QuestionSubmit_string_req = "settings_Account_settings_YourPreference_QuestionSubmit_string_req";

        pDialog = new ProgressDialog(context);
        try {
            if(null!=jsonObject){
                JSONObject jsonObject1 = new JSONObject(jsonObject.toString());
                if(jsonObject1.has(KEY_USER_ID) && jsonObject.has(KEY_QID) && jsonObject1.has(KEY_ANSWER))
                {
                    USER_ID = jsonObject1.get(KEY_USER_ID).toString();
                    QID = jsonObject1.get(KEY_QID).toString();
                    ANSWER = jsonObject1.get(KEY_ANSWER).toString();

                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        pDialog.setMessage("Submitting Answers...");
        pDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL.concat("question_submit"),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(StartupScreen.this,response,Toast.LENGTH_LONG).show();
                        Log.d(settings_Account_settings_YourPreference_QuestionSubmit_string_req, response.toString());
                        pDialog.hide();

                        try {
                            JSONObject jObj = new JSONObject(response);
                            boolean status = jObj.getBoolean("status");

                            // Check for error node in json
                            if (status) {
                                // user successfully Registered

                                int data = jObj.optInt("data");
                                AppConfig.profile_percentage = data;
                                profile_percentage.setProgress(data);
                                profile_percentage_value.setText(data+"/100");

                            } else {
                                // Error in Registeration. Get the error message
                                String data = jObj.optString("data");
                                Toast.makeText(context,
                                        data, Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            // JSON error
                            e.printStackTrace();
                            Toast.makeText(context, "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(StartupScreen.this,error.toString(),Toast.LENGTH_LONG).show();
                        VolleyLog.d(settings_Account_settings_YourPreference_QuestionSubmit_string_req, "Error: " + error.getMessage());
                        pDialog.hide();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put(KEY_USER_ID, USER_ID);
                params.put(KEY_QID, QID);
                params.put(KEY_ANSWER, ANSWER);
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
        AppController.getInstance().addToRequestQueue(stringRequest, settings_Account_settings_YourPreference_QuestionSubmit_string_req);
    }

    public static void rewards(final JSONObject jsonObject, final RecyclerView.Adapter adapter, final SwipeRefreshLayout swipeRefreshLayout, final List<Integer> tour_image, final List<String> tour_name, final int fragment_no){
        // Tag used to cancel the request
        final String  rewards_string_req = "rewards_string_req";

        //pDialog = new ProgressDialog(context);
        try {
            if(null!=jsonObject){
                JSONObject jsonObject1 = new JSONObject(jsonObject.toString());
                if(jsonObject1.has(KEY_USER_ID))
                {
                    USER_ID = jsonObject1.get(KEY_USER_ID).toString();
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

      /*  pDialog.setMessage("Fetching Rewards...");
        pDialog.show();*/

        StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL.concat("rewards"),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(StartupScreen.this,response,Toast.LENGTH_LONG).show();
                        Log.d(rewards_string_req, response.toString());
                        //pDialog.hide();
                        swipeRefreshLayout.setRefreshing(false);

                        try {
                            JSONObject jObj = new JSONObject(response);
                            boolean status = jObj.getBoolean("status");

                            // Check for error node in json
                            if (status) {
                                // user successfully Registered

                                JSONObject data = jObj.optJSONObject("data");
                                String count_friends = data.optString("count_friends");
                                String count_posts = data.optString("count_posts");
                                JSONArray userpercentage = data.optJSONArray("userpercentage");
                                JSONObject percentage = userpercentage.optJSONObject(0);
                                String profile_percentage = percentage.optString("profile_percentage");
                                String total_transactions = data.optString("total_transactions");
                                JSONArray rewards_time = data.optJSONArray("rewards_time");
                                JSONObject time = rewards_time.optJSONObject(0);
                                String startingd_time = time.optString("startingd_time");
                                String endingd_time = time.optString("endingd_time");

                                switch (fragment_no){
                                    case 0:
                                        if(!AppConfig.reward_worldtour_list.isEmpty())
                                            AppConfig.reward_worldtour_list.clear();
                                        for(int i=0; i<tour_image.size(); i++)
                                        {
                                            AppConfig.reward_worldtour_list.add(new Rewards(tour_name.get(i), tour_image.get(i),count_friends, count_posts, profile_percentage, total_transactions, startingd_time, endingd_time));
                                        }
                                        break;
                                    case 1:
                                        if(!AppConfig.reward_europeantour_list.isEmpty())
                                            AppConfig.reward_europeantour_list.clear();
                                        for(int i=0; i<tour_image.size(); i++)
                                        {
                                            AppConfig.reward_europeantour_list.add(new Rewards(tour_name.get(i), tour_image.get(i),count_friends, count_posts, profile_percentage, total_transactions, startingd_time, endingd_time));
                                        }
                                        break;
                                    case 2:
                                        if(!AppConfig.reward_couplesgateway_international_list.isEmpty())
                                            AppConfig.reward_couplesgateway_international_list.clear();
                                        for(int i=0; i<tour_image.size(); i++)
                                        {
                                            AppConfig.reward_couplesgateway_international_list.add(new Rewards(tour_name.get(i), tour_image.get(i),count_friends, count_posts, profile_percentage, total_transactions, startingd_time, endingd_time));
                                        }
                                        break;
                                    case 3:
                                        if(!AppConfig.reward_couplesgateway_domestic_list.isEmpty())
                                            AppConfig.reward_couplesgateway_domestic_list.clear();
                                        for(int i=0; i<tour_image.size(); i++)
                                        {
                                            AppConfig.reward_couplesgateway_domestic_list.add(new Rewards(tour_name.get(i), tour_image.get(i),count_friends, count_posts, profile_percentage, total_transactions, startingd_time, endingd_time));
                                        }
                                        break;
                                    case 4:
                                        if(!AppConfig.reward_knowurneighbour_list.isEmpty())
                                            AppConfig.reward_knowurneighbour_list.clear();
                                        for(int i=0; i<tour_image.size(); i++)
                                        {
                                            AppConfig.reward_knowurneighbour_list.add(new Rewards(tour_name.get(i), tour_image.get(i),count_friends, count_posts, profile_percentage, total_transactions, startingd_time, endingd_time));
                                        }
                                        break;
                                    case 5:
                                        if(!AppConfig.reward_localsplendors_list.isEmpty())
                                            AppConfig.reward_localsplendors_list.clear();
                                        for(int i=0; i<tour_image.size(); i++)
                                        {
                                            AppConfig.reward_localsplendors_list.add(new Rewards(tour_name.get(i), tour_image.get(i),count_friends, count_posts, profile_percentage, total_transactions, startingd_time, endingd_time));
                                        }
                                        break;
                                }
                                adapter.notifyDataSetChanged();
                            } else {
                                // Error in Registeration. Get the error message
                                String data = jObj.optString("data");
                                Toast.makeText(context,
                                        data, Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            // JSON error
                            e.printStackTrace();
                            Toast.makeText(context, "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(StartupScreen.this,error.toString(),Toast.LENGTH_LONG).show();
                        VolleyLog.d(rewards_string_req, "Error: " + error.getMessage());
                        //pDialog.hide();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put(KEY_USER_ID, USER_ID);
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
        AppController.getInstance().addToRequestQueue(stringRequest, rewards_string_req);
    }

    public static void vacation_package_getAllPackages(final JSONObject jsonObject, final RecyclerView.Adapter adapter, final SwipeRefreshLayout swipeRefreshLayout){
        // Tag used to cancel the request
        final String  vacation_package_getAllPackages_string_req = "vacation_package_getAllPackages_string_req";

        //pDialog = new ProgressDialog(context);
        try {
            if(null!=jsonObject){
                JSONObject jsonObject1 = new JSONObject(jsonObject.toString());
                if(jsonObject1.has(KEY_USER_ID))
                {
                    if(!AppConfig.vacationpackages_get_all.isEmpty())
                        AppConfig.vacationpackages_get_all.clear();
                    USER_ID = jsonObject1.get(KEY_USER_ID).toString();
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

       /* pDialog.setMessage("Fetching All Packages...");
        pDialog.show();*/

        StringRequest stringRequest = new StringRequest(Request.Method.GET, BASE_URL.concat("getAllPackages"),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(StartupScreen.this,response,Toast.LENGTH_LONG).show();
                        Log.d(vacation_package_getAllPackages_string_req, response.toString());
                        //pDialog.hide();
                        swipeRefreshLayout.setRefreshing(false);

                        try {
                            JSONObject jObj = new JSONObject(response);
                            boolean status = jObj.getBoolean("status");

                            // Check for error node in json
                            if (status) {
                                // user successfully Registered

                                JSONArray data = jObj.optJSONArray("data");
                                for(int i=0; i<data.length(); i++){
                                    JSONObject jsonArrayObject = data.optJSONObject(i);
                                    String image_url = jsonArrayObject.optString("package_image_path");
                                    String package_name = jsonArrayObject.optString("package_name");
                                    String package_price = jsonArrayObject.optString("price");
                                    String package_id = jsonArrayObject.optString("package_id");
                                    String package_includes = jsonArrayObject.optString("package_includes");

                                    AppConfig.vacationpackages_get_all.add(new VacationPackageListItem(image_url,package_name,package_price, package_id, package_includes));

                                }

                                adapter.notifyDataSetChanged();
                            } else {
                                // Error in Registeration. Get the error message
                                String data = jObj.optString("data");
                                Toast.makeText(context,
                                        data, Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            // JSON error
                            e.printStackTrace();
                            Toast.makeText(context, "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(StartupScreen.this,error.toString(),Toast.LENGTH_LONG).show();
                        VolleyLog.d(vacation_package_getAllPackages_string_req, "Error: " + error.getMessage());
                        //pDialog.hide();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put(KEY_USER_ID, USER_ID);
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
        AppController.getInstance().addToRequestQueue(stringRequest, vacation_package_getAllPackages_string_req);
    }

    public static void vacation_package_moreDetails(final JSONObject jsonObject, final RecyclerView.Adapter adapter, final SwipeRefreshLayout swipeRefreshLayout){
        // Tag used to cancel the request
        final String  vacation_package_moreDetails_string_req = "vacation_package_moreDetails_string_req";

        //pDialog = new ProgressDialog(context);
        try {
            if(null!=jsonObject){
                JSONObject jsonObject1 = new JSONObject(jsonObject.toString());
                if(jsonObject1.has(KEY_PACKAGE_ID))
                {
                    if(!AppConfig.vacationpackages_moredetails_listitem.isEmpty()){
                        vacationpackages_moredetails_listitem.clear();
                    }
                    PACKAGE_ID = jsonObject1.get(KEY_PACKAGE_ID).toString();
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

       /* pDialog.setMessage("Fetching More Details...");
        pDialog.show();*/

        StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL.concat("getPackage"),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(StartupScreen.this,response,Toast.LENGTH_LONG).show();
                        Log.d(vacation_package_moreDetails_string_req, response.toString());
                        //pDialog.hide();
                        swipeRefreshLayout.setRefreshing(false);

                        try {
                            JSONObject jObj = new JSONObject(response);
                            boolean status = jObj.getBoolean("status");

                            // Check for error node in json
                            if (status) {
                                // user successfully Registered

                                JSONArray data = jObj.optJSONArray("data");
                                for(int i=0; i<data.length(); i++){
                                    JSONObject jsonObject1 = data.optJSONObject(i);
                                    String package_description = jsonObject1.optString("package_description");
                                    String package_name = jsonObject1.optString("package_name");
                                    String package_price = jsonObject1.optString("price");
                                    String package_image = jsonObject1.optString("package_image_path");
                                    AppConfig.vacationpackages_moredetails_listitem.add((new VacationPackageMoreDetailsListItem(package_description, package_name, package_price, package_image)));
                                }

                                adapter.notifyDataSetChanged();
                            } else {
                                // Error in Registeration. Get the error message
                                String data = jObj.optString("data");
                                Toast.makeText(context,
                                        data, Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            // JSON error
                            e.printStackTrace();
                            Toast.makeText(context, "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(StartupScreen.this,error.toString(),Toast.LENGTH_LONG).show();
                        VolleyLog.d(vacation_package_moreDetails_string_req, "Error: " + error.getMessage());
                        //pDialog.hide();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put(KEY_PACKAGE_ID, PACKAGE_ID);

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
        AppController.getInstance().addToRequestQueue(stringRequest, vacation_package_moreDetails_string_req);
    }

    public static void vacation_package_moreDetails_detailedItenary(final JSONObject jsonObject, final RecyclerView.Adapter adapter, final SwipeRefreshLayout swipeRefreshLayout){
        // Tag used to cancel the request
        final String  vacation_package_moreDetails_detailedItenary_string_req = "vacation_package_moreDetails_detailedItenary_string_req";

        //pDialog = new ProgressDialog(context);
        try {
            if(null!=jsonObject){
                JSONObject jsonObject1 = new JSONObject(jsonObject.toString());
                if(jsonObject1.has(KEY_PACKAGE_ID))
                {
                    if(!vacationpackages_moredetails_detaileditenary_listitem.isEmpty()){
                        vacationpackages_moredetails_detaileditenary_listitem.clear();
                    }
                    PACKAGE_ID = jsonObject1.get(KEY_PACKAGE_ID).toString();
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        /*pDialog.setMessage("Fetching More Details...");
        pDialog.show();*/

        StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL.concat("getPackageItinerary"),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(StartupScreen.this,response,Toast.LENGTH_LONG).show();
                        Log.d(vacation_package_moreDetails_detailedItenary_string_req, response.toString());
                        //pDialog.hide();
                        swipeRefreshLayout.setRefreshing(false);

                        try {
                            JSONObject jObj = new JSONObject(response);
                            boolean status = jObj.getBoolean("status");

                            // Check for error node in json
                            if (status) {
                                // user successfully Registered

                                String itinerary_description = "";
                                JSONArray data = jObj.optJSONArray("data");
                                for(int i=0; i<data.length(); i++){
                                    JSONObject jsonObject1 = data.optJSONObject(i);
                                    String day = jsonObject1.optString("day");
                                    String place = jsonObject1.optString("place");
                                    itinerary_description = itinerary_description +"<span style=\"color:cyan\">"+"Day "+day+": "+"</span>"+place+"\n\n"+jsonObject1.optString("itinerary_description")+"\n\n\n";
                                   /* String package_name = jsonObject1.optString("place");
                                    String package_image = jsonObject1.optString("itinerary_image");*/
                                    //vacationpackages_moredetails_detaileditenary_listitem.add((new VacationPackageMoreDetailsDetaileItenaryListItem(itinerary_description, day, place)));
                                }
                                vacationpackages_moredetails_detaileditenary_listitem.add((new VacationPackageMoreDetailsDetaileItenaryListItem(itinerary_description)));
                                adapter.notifyDataSetChanged();
                            } else {
                                // Error in Registeration. Get the error message
                                String data = jObj.optString("data");
                                Toast.makeText(context,
                                        data, Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            // JSON error
                            e.printStackTrace();
                            Toast.makeText(context, "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(StartupScreen.this,error.toString(),Toast.LENGTH_LONG).show();
                        VolleyLog.d(vacation_package_moreDetails_detailedItenary_string_req, "Error: " + error.getMessage());
                        //pDialog.hide();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put(KEY_PACKAGE_ID, PACKAGE_ID);

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
        AppController.getInstance().addToRequestQueue(stringRequest, vacation_package_moreDetails_detailedItenary_string_req);
    }

    public static void vacation_package_moreDetails_gallery(final JSONObject jsonObject, final RecyclerView.Adapter adapter, final SwipeRefreshLayout swipeRefreshLayout){
        // Tag used to cancel the request
        final String  vacation_package_moreDetails_string_req = "vacation_package_moreDetails_string_req";

        //pDialog = new ProgressDialog(context);
        try {
            if(null!=jsonObject){
                JSONObject jsonObject1 = new JSONObject(jsonObject.toString());
                if(jsonObject1.has(KEY_PACKAGE_ID))
                {
                    if(!AppConfig.vacationpackages_moredetails_gallery_listitem.isEmpty()){
                        vacationpackages_moredetails_gallery_listitem.clear();
                    }
                    PACKAGE_ID = jsonObject1.get(KEY_PACKAGE_ID).toString();
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        /*pDialog.setMessage("Fetching More Details...");
        pDialog.show();*/

        StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL.concat("getTravellerPhotos"),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(StartupScreen.this,response,Toast.LENGTH_LONG).show();
                        Log.d(vacation_package_moreDetails_string_req, response.toString());
                        //pDialog.hide();
                        swipeRefreshLayout.setRefreshing(false);

                        try {
                            JSONObject jObj = new JSONObject(response);
                            boolean status = jObj.getBoolean("status");

                            // Check for error node in json
                            if (status) {
                                // user successfully Registered

                                JSONArray data = jObj.optJSONArray("data");
                                for(int i=0; i<data.length(); i++){
                                    JSONObject jsonObject1 = data.optJSONObject(i);

                                    String package_gallery_path = jsonObject1.optString("package_gallery_path");
                                    String img_id = jsonObject1.optString("img_id");
                                    vacationpackages_moredetails_gallery_listitem.add(new PhotoDataModel(package_gallery_path, img_id));
                                }

                                adapter.notifyDataSetChanged();
                            } else {
                                // Error in Registeration. Get the error message
                                String data = jObj.optString("data");
                                Toast.makeText(context,
                                        data, Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            // JSON error
                            e.printStackTrace();
                            Toast.makeText(context, "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(StartupScreen.this,error.toString(),Toast.LENGTH_LONG).show();
                        VolleyLog.d(vacation_package_moreDetails_string_req, "Error: " + error.getMessage());
                        //pDialog.hide();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put(KEY_PACKAGE_ID, PACKAGE_ID);

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
        AppController.getInstance().addToRequestQueue(stringRequest, vacation_package_moreDetails_string_req);
    }

    public static void vacation_package_moreDetails_pricePolicy(final JSONObject jsonObject, final RecyclerView.Adapter adapter, final SwipeRefreshLayout swipeRefreshLayout){
        // Tag used to cancel the request
        final String  vacation_package_moreDetails_pricePolicy_string_req = "vacation_package_moreDetails_pricePolicy_string_req";

        //pDialog = new ProgressDialog(context);
        try {
            if(null!=jsonObject){
                JSONObject jsonObject1 = new JSONObject(jsonObject.toString());
                if(jsonObject1.has(KEY_PACKAGE_ID))
                {
                    if(!vacationpackages_moredetails_pricePolicy_listitem.isEmpty()){
                        vacationpackages_moredetails_pricePolicy_listitem.clear();
                    }
                    PACKAGE_ID = jsonObject1.get(KEY_PACKAGE_ID).toString();
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        /*pDialog.setMessage("Fetching More Details...");
        pDialog.show();*/

        StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL.concat("getPackagePricePolicy"),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(StartupScreen.this,response,Toast.LENGTH_LONG).show();
                        Log.d(vacation_package_moreDetails_pricePolicy_string_req, response.toString());
                        //pDialog.hide();
                        swipeRefreshLayout.setRefreshing(false);

                        try {
                            JSONObject jObj = new JSONObject(response);
                            boolean status = jObj.getBoolean("status");

                            // Check for error node in json
                            if (status) {
                                // user successfully Registered

                                JSONArray data = jObj.optJSONArray("data");
                                for(int i=0; i<data.length(); i++){
                                    JSONObject jsonObject1 = data.optJSONObject(i);
                                    String price_includes = "<span style=\"color:cyan\">"+"Price Includes: "+"</span>"+jsonObject1.optString("price_includes")+"\n\n";
                                    String price_excludes = "<span style=\"color:cyan\">"+"Price Excludes: "+"</span>"+jsonObject1.optString("price_excludes")+"\n\n";

                                    vacationpackages_moredetails_pricePolicy_listitem.add((new VacationPackageMoreDetailsPricePolicyListItem(price_includes, price_excludes)));

                                }
                                vacation_package_moreDetails_cancellationPolicy(jsonObject, adapter, swipeRefreshLayout);
                                //adapter.notifyDataSetChanged();
                            } else {
                                // Error in Registeration. Get the error message
                                String data = jObj.optString("data");
                                Toast.makeText(context,
                                        data, Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            // JSON error
                            e.printStackTrace();
                            Toast.makeText(context, "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(StartupScreen.this,error.toString(),Toast.LENGTH_LONG).show();
                        VolleyLog.d(vacation_package_moreDetails_pricePolicy_string_req, "Error: " + error.getMessage());
                        //pDialog.hide();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put(KEY_PACKAGE_ID, PACKAGE_ID);

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
        AppController.getInstance().addToRequestQueue(stringRequest, vacation_package_moreDetails_pricePolicy_string_req);
    }

    public static void vacation_package_moreDetails_cancellationPolicy(final JSONObject jsonObject, final RecyclerView.Adapter adapter, final SwipeRefreshLayout swipeRefreshLayout){
        // Tag used to cancel the request
        final String  vacation_package_moreDetails_cancellationPolicy_string_req = "vacation_package_moreDetails_cancellationPolicy_string_req";

        //pDialog = new ProgressDialog(context);
        try {
            if(null!=jsonObject){
                JSONObject jsonObject1 = new JSONObject(jsonObject.toString());
                if(jsonObject1.has(KEY_PACKAGE_ID))
                {
                    if(!vacationpackages_moredetails_cancellationPolicy_listitem.isEmpty()){
                        vacationpackages_moredetails_cancellationPolicy_listitem.clear();
                    }
                    PACKAGE_ID = jsonObject1.get(KEY_PACKAGE_ID).toString();
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

       /* pDialog.setMessage("Fetching More Details...");
        pDialog.show();*/

        StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL.concat("getPackageCancelPolicy"),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(StartupScreen.this,response,Toast.LENGTH_LONG).show();
                        Log.d(vacation_package_moreDetails_cancellationPolicy_string_req, response.toString());
                        //pDialog.hide();
                        swipeRefreshLayout.setRefreshing(false);

                        try {
                            JSONObject jObj = new JSONObject(response);
                            boolean status = jObj.getBoolean("status");

                            // Check for error node in json
                            if (status) {
                                // user successfully Registered

                                JSONArray data = jObj.optJSONArray("data");
                                for(int i=0; i<data.length(); i++){
                                    JSONObject jsonObject1 = data.optJSONObject(i);
                                    String cancellation_advance ="<span style=\"color:cyan\">"+"Cancellation Policy: "+"</span>"+jsonObject1.optString("cancellation_advance")+"\n\n";
                                    String cancellation_penality = "<span style=\"color:cyan\">"+"Cancellation Penality: "+"</span>"+jsonObject1.optString("cancellation_penality")+"\n\n";

                                    vacationpackages_moredetails_cancellationPolicy_listitem.add((new VacationPackageMoreDetailsCancellationPolicyListItem(cancellation_advance, cancellation_penality)));
                                }

                                AppConfig.vacationpackages_moredetails_termscondition_listitem.add(new VacationPackageMoreDetailsTermsConditionListItem(AppConfig.vacationpackages_moredetails_pricePolicy_listitem.get(0).getPrice_includes()+AppConfig.vacationpackages_moredetails_pricePolicy_listitem.get(0).getPrice_excludes()+vacationpackages_moredetails_cancellationPolicy_listitem.get(0).getCancellation_advance()/*+vacationpackages_moredetails_cancellationPolicy_listitem.get(0).getCancellation_penality()*/));
                                /*AppConfig.vacationpackages_moredetails_termscondition_listitem.add(new VacationPackageMoreDetailsTermsConditionListItem(AppConfig.vacationpackages_moredetails_pricePolicy_listitem.get(0).getPrice_excludes()));
                                AppConfig.vacationpackages_moredetails_termscondition_listitem.add(new VacationPackageMoreDetailsTermsConditionListItem(vacationpackages_moredetails_cancellationPolicy_listitem.get(0).getCancellation_advance()));
                                AppConfig.vacationpackages_moredetails_termscondition_listitem.add(new VacationPackageMoreDetailsTermsConditionListItem(vacationpackages_moredetails_cancellationPolicy_listitem.get(0).getCancellation_penality()));*/
                                adapter.notifyDataSetChanged();
                            } else {
                                // Error in Registeration. Get the error message
                                String data = jObj.optString("data");
                                Toast.makeText(context,
                                        data, Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            // JSON error
                            e.printStackTrace();
                            Toast.makeText(context, "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(StartupScreen.this,error.toString(),Toast.LENGTH_LONG).show();
                        VolleyLog.d(vacation_package_moreDetails_cancellationPolicy_string_req, "Error: " + error.getMessage());
                        // pDialog.hide();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put(KEY_PACKAGE_ID, PACKAGE_ID);

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
        AppController.getInstance().addToRequestQueue(stringRequest, vacation_package_moreDetails_cancellationPolicy_string_req);
    }

    public static void vacation_package_moreDetails_termscondition(final JSONObject jsonObject, final RecyclerView.Adapter adapter, final SwipeRefreshLayout swipeRefreshLayout){

        try {
            if(null!=jsonObject){
                JSONObject jsonObject1 = new JSONObject(jsonObject.toString());
                if(jsonObject1.has(KEY_PACKAGE_ID))
                {
                    if(!vacationpackages_moredetails_termscondition_listitem.isEmpty()){
                        vacationpackages_moredetails_termscondition_listitem.clear();
                    }
                    PACKAGE_ID = jsonObject1.get(KEY_PACKAGE_ID).toString();
                    vacation_package_moreDetails_pricePolicy(jsonObject, adapter, swipeRefreshLayout);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void vacation_package_vactionType(){
        // Tag used to cancel the request
        final String  vacation_package_vactionType_string_req = "vacation_package_vactionType_string_req";

       /* pDialog = new ProgressDialog(context);

        pDialog.setMessage("Fetching Vacation Type...");
        pDialog.show();*/

        StringRequest stringRequest = new StringRequest(Request.Method.GET, BASE_URL.concat("getPackageTypes"),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(StartupScreen.this,response,Toast.LENGTH_LONG).show();
                        Log.d(vacation_package_vactionType_string_req, response.toString());
                        //pDialog.hide();

                        try {
                            JSONObject jObj = new JSONObject(response);
                            boolean status = jObj.optBoolean("status");

                            // Check for error node in json
                            if (status) {
                                // user successfully Registered

                                if(!vacation_listItems.isEmpty())
                                    vacation_listItems.clear();
                                JSONArray data = jObj.optJSONArray("data");

                                for(int i=0; i<data.length(); i++){
                                    JSONObject data_jsonObject = data.optJSONObject(i);
                                    String package_types_id = data_jsonObject.optString("package_types_id");
                                    String package_types_name = data_jsonObject.optString("package_types_name");
                                    String domain_list_fk = data_jsonObject.optString("domain_list_fk");

                                    vacation_listItems.add(new VacationTypeListItem(package_types_id, package_types_name, domain_list_fk));

                                }
                                setCheckbox_vacation_types(context, vacation_type_layout, vacation_listItems);
                                AppConfig.budget_listItems.add(new BudgetListItem("budjet < 15000", "Unders ₹15000"));
                                AppConfig.budget_listItems.add(new BudgetListItem("BETWEEN 15001 AND 25000", "₹15001 ‑ ₹25000"));
                                AppConfig.budget_listItems.add(new BudgetListItem("BETWEEN 25501 AND 50000,", "₹25501 ‑ ₹50000"));
                                AppConfig.budget_listItems.add(new BudgetListItem("BETWEEN 50001 AND 75000", "₹50001 ‑ ₹75000"));
                                AppConfig.budget_listItems.add(new BudgetListItem("budjet < 15000", "Above ₹75000"));

                                AppConfig.setCheckbox_budget(context, AppConfig.budget_layout, AppConfig.budget_listItems);
                                vacation_package_destination();

                            } else {
                                // Error in Registeration. Get the error message
                                String data = jObj.optString("message");
                                Toast.makeText(context,
                                        data, Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            // JSON error
                            e.printStackTrace();
                            Toast.makeText(context, "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(StartupScreen.this,error.toString(),Toast.LENGTH_LONG).show();
                        VolleyLog.d(vacation_package_vactionType_string_req, "Error: " + error.getMessage());
                        //pDialog.hide();
                    }
                }){
            /* @Override
             protected Map<String,String> getParams(){
                 Map<String,String> params = new HashMap<String, String>();
                 params.put(KEY_PACKAGE_ID, PACKAGE_ID);
                 params.put(KEY_FIRST_NAME, FIRST_NAME);
                 params.put(KEY_EMAIL, EMAIL);
                 params.put(KEY_PHONE, PHONE);
                 params.put(KEY_MESSAGE, MESSAGE);

                 return params;
             }*/
            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }

        };

        /*RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);*/

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(stringRequest, vacation_package_vactionType_string_req);
    }

    public static void vacation_package_destination(){
        // Tag used to cancel the request
        final String  vacation_package_destination_string_req = "vacation_package_destination_string_req";

       /* pDialog = new ProgressDialog(context);

        pDialog.setMessage("Fetching Destinations...");
        pDialog.show();*/

        StringRequest stringRequest = new StringRequest(Request.Method.GET, BASE_URL.concat("getPackageCountries"),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(StartupScreen.this,response,Toast.LENGTH_LONG).show();
                        Log.d(vacation_package_destination_string_req, response.toString());
                        //pDialog.hide();

                        try {
                            JSONObject jObj = new JSONObject(response);
                            boolean status = jObj.optBoolean("status");

                            // Check for error node in json
                            if (status) {
                                // user successfully Registered

                                if(!destination_listItems.isEmpty())
                                    destination_listItems.clear();
                                JSONArray data = jObj.optJSONArray("data");

                                for(int i=0; i<data.length(); i++){
                                    JSONObject data_jsonObject = data.optJSONObject(i);
                                    String package_country = data_jsonObject.optString("package_country");
                                    String country_name = data_jsonObject.optString("country_name");
                                    String domain_list_fk = data_jsonObject.optString("domain_list_fk");

                                    destination_listItems.add(new DestinationListItem(package_country, country_name));

                                }
                                setCheckbox_destination(context, destination_layout, destination_listItems);

                            } else {
                                // Error in Registeration. Get the error message
                                String data = jObj.optString("message");
                                Toast.makeText(context,
                                        data, Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            // JSON error
                            e.printStackTrace();
                            Toast.makeText(context, "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(StartupScreen.this,error.toString(),Toast.LENGTH_LONG).show();
                        VolleyLog.d(vacation_package_destination_string_req, "Error: " + error.getMessage());
                        //pDialog.hide();
                    }
                }){

            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }

        };

        /*RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);*/

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(stringRequest, vacation_package_destination_string_req);
    }

    public static void vacation_package_moreDetails_sendEnquiry(final JSONObject jsonObject, final View view){
        // Tag used to cancel the request
        final String  vacation_package_moreDetails_sendEnquiry_string_req = "vacation_package_moreDetails_sendEnquiry_string_req";

        pDialog = new ProgressDialog(context);
        try {
            if(null!=jsonObject){
                JSONObject jsonObject1 = new JSONObject(jsonObject.toString());
                if(jsonObject1.has(KEY_PACKAGE_ID) && jsonObject1.has(KEY_FIRST_NAME) && jsonObject1.has(KEY_EMAIL) && jsonObject1.has(KEY_PHONE) && jsonObject1.has(KEY_MESSAGE))
                {
                    PACKAGE_ID = jsonObject1.get(KEY_PACKAGE_ID).toString();
                    FIRST_NAME = jsonObject1.get(KEY_FIRST_NAME).toString();
                    EMAIL = jsonObject1.get(KEY_EMAIL).toString();
                    PHONE = jsonObject1.get(KEY_PHONE).toString();
                    MESSAGE = jsonObject1.get(KEY_MESSAGE).toString();
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        pDialog.setMessage("Sending Enquiry...");
        pDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL.concat("saveEnquiry"),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(StartupScreen.this,response,Toast.LENGTH_LONG).show();
                        Log.d(vacation_package_moreDetails_sendEnquiry_string_req, response.toString());
                        pDialog.hide();

                        try {
                            JSONObject jObj = new JSONObject(response);
                            boolean status = jObj.getBoolean("status");

                            // Check for error node in json
                            if (status) {
                                // user successfully Registered

                                String data = jObj.optString("message");

                                View thankPopUp = AppConfig.displayPopupWindow(view, context);
                                ImageView close = (ImageView) thankPopUp.findViewById(R.id.close);
                                close.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        AppConfig.popup.dismiss();
                                    }
                                });
                            } else {
                                // Error in Registeration. Get the error message
                                String data = jObj.optString("message");
                                Toast.makeText(context,
                                        data, Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            // JSON error
                            e.printStackTrace();
                            Toast.makeText(context, "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(StartupScreen.this,error.toString(),Toast.LENGTH_LONG).show();
                        VolleyLog.d(vacation_package_moreDetails_sendEnquiry_string_req, "Error: " + error.getMessage());
                        pDialog.hide();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put(KEY_PACKAGE_ID, PACKAGE_ID);
                params.put(KEY_FIRST_NAME, FIRST_NAME);
                params.put(KEY_EMAIL, EMAIL);
                params.put(KEY_PHONE, PHONE);
                params.put(KEY_MESSAGE, MESSAGE);

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
        AppController.getInstance().addToRequestQueue(stringRequest, vacation_package_moreDetails_sendEnquiry_string_req);
    }

    public static void vacation_package_filter_apply(final JSONObject jsonObject, final FragmentManager fragmentManager){
        // Tag used to cancel the request
        final String  vacation_package_moreDetails_sendEnquiry_string_req = "vacation_package_moreDetails_sendEnquiry_string_req";

        pDialog = new ProgressDialog(context);
        try {
            if(null!=jsonObject){
                JSONObject jsonObject1 = new JSONObject(jsonObject.toString());
                if(jsonObject1.has(KEY_PACKAGE_COUNTRY) && jsonObject1.has(KEY_PACKAGE_TYPE)  && jsonObject1.has(KEY_DURATION))
                {
                    PACKAGE_COUNTRY = jsonObject1.get(KEY_PACKAGE_COUNTRY).toString();
                    PACKAGE_TYPE = jsonObject1.get(KEY_PACKAGE_TYPE).toString();
                    //PRICES = jsonObject1.get(KEY_PRICES).toString();
                    DURATION = jsonObject1.get(KEY_DURATION).toString();
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        pDialog.setMessage("Filter by...");
        pDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL.concat("filter"),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(StartupScreen.this,response,Toast.LENGTH_LONG).show();
                        Log.d(vacation_package_moreDetails_sendEnquiry_string_req, response.toString());
                        pDialog.hide();

                        try {
                            JSONObject jObj = new JSONObject(response);
                            boolean status = jObj.getBoolean("status");

                            // Check for error node in json
                            if (status) {
                                // user successfully Registered

                                JSONArray data = jObj.optJSONArray("data");
                                for(int i=0; i<data.length(); i++){
                                    JSONObject jsonArrayObject = data.optJSONObject(i);
                                    String image_url = jsonArrayObject.optString("package_image_path");
                                    String package_name = jsonArrayObject.optString("package_name");
                                    String package_price = jsonArrayObject.optString("price");
                                    String package_id = jsonArrayObject.optString("package_id");
                                    String package_includes = jsonArrayObject.optString("package_includes");

                                    AppConfig.vacationpackages_get_all_filter.add(new VacationPackageListItem(image_url,package_name,package_price, package_id, package_includes));


                                }
                                if (fragmentManager.getBackStackEntryCount() > 0) {
                                    fragmentManager.popBackStack();
                                }
                                //adapter.notifyDataSetChanged();
                            } else {
                                // Error in Registeration. Get the error message
                                String data = jObj.optString("data");
                                Toast.makeText(context,
                                        data, Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            // JSON error
                            e.printStackTrace();
                            Toast.makeText(context, "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(StartupScreen.this,error.toString(),Toast.LENGTH_LONG).show();
                        VolleyLog.d(vacation_package_moreDetails_sendEnquiry_string_req, "Error: " + error.getMessage());
                        pDialog.hide();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put(KEY_PACKAGE_COUNTRY, PACKAGE_COUNTRY);
                params.put(KEY_PACKAGE_TYPE, PACKAGE_TYPE);
                //params.put(KEY_PRICES, PRICES);
                params.put(KEY_DURATION, DURATION);


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
        AppController.getInstance().addToRequestQueue(stringRequest, vacation_package_moreDetails_sendEnquiry_string_req);
    }

    public static void settings_checkoldPassword(final FragmentManager fragmentManager){
        // Tag used to cancel the request
        final String settings_checkoldPassword_string_req = "settings_checkoldPassword_string_req";
        pDialog = new ProgressDialog(context);
        /*try {
            if(null!=jsonObject){
                JSONObject jsonObject1 = new JSONObject(jsonObject.toString());
                if(jsonObject1.has(KEY_PACKAGE_COUNTRY) && jsonObject1.has(KEY_PACKAGE_TYPE)  && jsonObject1.has(KEY_DURATION))
                {
                    PACKAGE_COUNTRY = jsonObject1.get(KEY_PACKAGE_COUNTRY).toString();
                    PACKAGE_TYPE = jsonObject1.get(KEY_PACKAGE_TYPE).toString();
                    //PRICES = jsonObject1.get(KEY_PRICES).toString();
                    DURATION = jsonObject1.get(KEY_DURATION).toString();
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }*/

        pDialog.setMessage("Checking old Password...");
        pDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL.concat("check_oldpassword"),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(StartupScreen.this,response,Toast.LENGTH_LONG).show();
                        Log.d(settings_checkoldPassword_string_req, response.toString());
                        pDialog.hide();
                        int status;
                        try {
                            JSONObject jObj = new JSONObject(response);
                            status = jObj.optInt("status");

                            // Check for error node in json
                            if (status==1) {
                                // user successfully Registered
                                //adapter.notifyDataSetChanged();
                                settings_submit_emailpassor(fragmentManager);
                            }else if(status == 0){
                                AppConfig.changePassword_oldPassword_til.setError("Current password incorrect");
                            }
                            else {
                                // Error in Registeration. Get the error message
                                status = jObj.optInt("status");
                                Toast.makeText(context,
                                        status, Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            // JSON error
                            e.printStackTrace();
                            Toast.makeText(context, "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(StartupScreen.this,error.toString(),Toast.LENGTH_LONG).show();
                        VolleyLog.d(settings_checkoldPassword_string_req, "Error: " + error.getMessage());
                        pDialog.hide();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put(KEY_USER_ID, AppConfig.getUser_id());
                params.put(KEY_OLD_PASSWORD, AppConfig.changePassword_old_password);


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
        AppController.getInstance().addToRequestQueue(stringRequest, settings_checkoldPassword_string_req);
    }

    public static void settings_submit_emailpassor(final FragmentManager fragmentManager){
        // Tag used to cancel the request
        final String  settings_submit_emailpassord_string_req = "settings_submit_emailpassord_string_req";

        pDialog = new ProgressDialog(context);

        pDialog.setMessage("Submitting New password...");
        pDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL.concat("changepass"),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(StartupScreen.this,response,Toast.LENGTH_LONG).show();
                        Log.d(settings_submit_emailpassord_string_req, response.toString());
                        pDialog.hide();

                        try {
                            JSONObject jObj = new JSONObject(response);
                            int status = jObj.optInt("status");

                            // Check for error node in json
                            if (status==1) {
                                // user successfully Registered

                                //adapter.notifyDataSetChanged();
                                if (fragmentManager.getBackStackEntryCount() > 0) {
                                    fragmentManager.popBackStack();
                                }

                            } else {
                                // Error in Registeration. Get the error message
                                String data = jObj.optString("data");
                                Toast.makeText(context,
                                        data, Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            // JSON error
                            e.printStackTrace();
                            Toast.makeText(context, "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(StartupScreen.this,error.toString(),Toast.LENGTH_LONG).show();
                        VolleyLog.d(settings_submit_emailpassord_string_req, "Error: " + error.getMessage());
                        pDialog.hide();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put(KEY_USER_ID, AppConfig.getUser_id());
                params.put(KEY_OLD_PASSWORD, AppConfig.changePassword_old_password);
                //params.put(KEY_PRICES, PRICES);
                params.put(KEY_PASSWORD, AppConfig.changePassword_password);


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
        AppController.getInstance().addToRequestQueue(stringRequest, settings_submit_emailpassord_string_req);
    }

    public static void urphotos_delete(final JSONObject jsonObject, final FragmentManager fragmentManager){
        // Tag used to cancel the request
        final String urphotos_delete_string_req = "urphotos_delete_string_req";
        pDialog = new ProgressDialog(context);
        try {
            if(null!=jsonObject){
                JSONObject jsonObject1 = new JSONObject(jsonObject.toString());
                if(jsonObject1.has(KEY_ID))
                {
                    ID = jsonObject1.get(KEY_ID).toString();
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        pDialog.setMessage("Deleting Photos...");
        pDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL.concat("delete_user_photo"),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(StartupScreen.this,response,Toast.LENGTH_LONG).show();
                        Log.d(urphotos_delete_string_req, response.toString());
                        pDialog.hide();
                        try {

                            JSONObject jObj = new JSONObject(response);
                            boolean status = jObj.optBoolean("status");

                            // Check for error node in json
                            if (status) {
                                // user successfully Registered

                                JSONObject data = jObj.optJSONObject("data");
                                String message = data.optString("message");
                                signedInHome_Profile_Photos();
                                if (fragmentManager.getBackStackEntryCount() > 0) {
                                    fragmentManager.popBackStack();
                                }
                            }else{

                                Toast.makeText(context,status+"",Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            // JSON error
                            e.printStackTrace();
                            Toast.makeText(context, "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(StartupScreen.this,error.toString(),Toast.LENGTH_LONG).show();
                        VolleyLog.d(urphotos_delete_string_req, "Error: " + error.getMessage());
                        pDialog.hide();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put(KEY_ID, jsonObject.optString("id"));

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
        AppController.getInstance().addToRequestQueue(stringRequest, urphotos_delete_string_req);
    }

    public static void urvideos_delete(final JSONObject jsonObject, final FragmentManager fragmentManager){
        // Tag used to cancel the request
        final String urvideos_delete_string_req = "urvideos_delete_string_req";
        pDialog = new ProgressDialog(context);
        try {
            if(null!=jsonObject){
                JSONObject jsonObject1 = new JSONObject(jsonObject.toString());
                if(jsonObject1.has(KEY_ID))
                {
                    ID = jsonObject1.get(KEY_ID).toString();
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        pDialog.setMessage("Deleting Videos...");
        pDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL.concat("delete_user_video"),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(StartupScreen.this,response,Toast.LENGTH_LONG).show();
                        Log.d(urvideos_delete_string_req, response.toString());
                        pDialog.hide();
                        try {

                            JSONObject jObj = new JSONObject(response);
                            boolean status = jObj.optBoolean("status");

                            // Check for error node in json
                            if (status) {
                                // user successfully Registered
                                JSONObject data = jObj.optJSONObject("data");
                                String message = data.optString("message");
                                signedInHome_Profile_Videos();
                                if (fragmentManager.getBackStackEntryCount() > 0) {
                                    fragmentManager.popBackStack();
                                }
                            }else{

                                Toast.makeText(context,status+"",Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            // JSON error
                            e.printStackTrace();
                            Toast.makeText(context, "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(StartupScreen.this,error.toString(),Toast.LENGTH_LONG).show();
                        VolleyLog.d(urvideos_delete_string_req, "Error: " + error.getMessage());
                        pDialog.hide();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put(KEY_ID, jsonObject.optString("id"));

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
        AppController.getInstance().addToRequestQueue(stringRequest, urvideos_delete_string_req);
    }

  /*  public static void urphotos_share(final JSONObject jsonObject){
        // Tag used to cancel the request
        final String settings_checkoldPassword_string_req = "settings_checkoldPassword_string_req";
        pDialog = new ProgressDialog(context);
        try {
            if(null!=jsonObject){
                JSONObject jsonObject1 = new JSONObject(jsonObject.toString());
                if(jsonObject1.has(KEY_PACKAGE_COUNTRY) && jsonObject1.has(KEY_PACKAGE_TYPE)  && jsonObject1.has(KEY_DURATION))
                {
                    PACKAGE_COUNTRY = jsonObject1.get(KEY_PACKAGE_COUNTRY).toString();
                    PACKAGE_TYPE = jsonObject1.get(KEY_PACKAGE_TYPE).toString();
                    //PRICES = jsonObject1.get(KEY_PRICES).toString();
                    DURATION = jsonObject1.get(KEY_DURATION).toString();
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }*//*

        pDialog.setMessage("Checking old Password...");
        pDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL.concat("postconnectshare"),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(StartupScreen.this,response,Toast.LENGTH_LONG).show();
                        Log.d(settings_checkoldPassword_string_req, response.toString());
                        pDialog.hide();
                        int status;
                        try {
                            JSONObject jObj = new JSONObject(response);
                            status = jObj.optInt("status");

                            // Check for error node in json
                            if (status==1) {
                                // user successfully Registered

                                settings_submit_emailpassord();
                                //adapter.notifyDataSetChanged();
                            }else if(status == 0){
                                AppConfig.changePassword_oldPassword_til.setError("Current password incorrect");
                            }
                            else {
                                // Error in Registeration. Get the error message
                                status = jObj.optInt("status");
                                Toast.makeText(context,
                                        status, Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            // JSON error
                            e.printStackTrace();
                            Toast.makeText(context, "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(StartupScreen.this,error.toString(),Toast.LENGTH_LONG).show();
                        VolleyLog.d(settings_checkoldPassword_string_req, "Error: " + error.getMessage());
                        pDialog.hide();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put(KEY_USER_ID, AppConfig.getUser_id());
                params.put(KEY_OLD_PASSWORD, AppConfig.changePassword_old_password);


                return params;
            }
            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }

        };

        *//*RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);*//*

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(stringRequest, settings_checkoldPassword_string_req);
    }

    public static void urvideos_share(){
        // Tag used to cancel the request
        final String  settings_submit_emailpassord_string_req = "settings_submit_emailpassord_string_req";

        pDialog = new ProgressDialog(context);

        pDialog.setMessage("Submitting New password...");
        pDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL.concat("postconnectvideoshare"),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(StartupScreen.this,response,Toast.LENGTH_LONG).show();
                        Log.d(settings_submit_emailpassord_string_req, response.toString());
                        pDialog.hide();

                        try {
                            JSONObject jObj = new JSONObject(response);
                            boolean status = jObj.getBoolean("status");

                            // Check for error node in json
                            if (status) {
                                // user successfully Registered

                                //adapter.notifyDataSetChanged();
                            } else {
                                // Error in Registeration. Get the error message
                                String data = jObj.optString("data");
                                Toast.makeText(context,
                                        data, Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            // JSON error
                            e.printStackTrace();
                            Toast.makeText(context, "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(StartupScreen.this,error.toString(),Toast.LENGTH_LONG).show();
                        VolleyLog.d(settings_submit_emailpassord_string_req, "Error: " + error.getMessage());
                        pDialog.hide();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put(KEY_USER_ID, AppConfig.getUser_id());
                params.put(KEY_OLD_PASSWORD, AppConfig.changePassword_old_password);
                //params.put(KEY_PRICES, PRICES);
                params.put(KEY_PASSWORD, AppConfig.changePassword_password);


                return params;
            }
            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }

        };

        *//*RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);*//*

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(stringRequest, settings_submit_emailpassord_string_req);
    }*/

}
