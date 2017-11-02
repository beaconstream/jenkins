package com.travelur.travelconnect.vacationpackages;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.travelur.R;
import com.travelur.backend.VolleyRequest;
import com.travelur.general.BaseFragment;
import com.travelur.travelconnect.apploading.StartupScreen;
import com.travelur.travelconnect.settings.Setting;
import com.travelur.utility.AppConfig;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author by Abhijit.
 */

public class SendEnquiry extends BaseFragment implements View.OnClickListener {

    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;
    private TextView phone, cancel;
    private ImageView close;
    private EditText your_name, emai_id, mob_no, message;
    private Spinner no_of_person;
    private Button update;
    private Intent surf;
    private JSONObject jsonObject;

    private String firstName, email, phone_text, message_text, mobile_no, no_person;
    private TextInputLayout NameTil, EmailTil, MessageTil, MobileNoTil;

    public static SendEnquiry newInstance() {
        SendEnquiry fragment = new SendEnquiry();
        return fragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.vactionpackage_moredetails_sendenquiry, null, false);

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        phone = (TextView)view.findViewById(R.id.phone);
        close = (ImageView) view.findViewById(R.id.close);
        cancel = (TextView)view.findViewById(R.id.cancel);
        your_name = (EditText) view.findViewById(R.id.your_name);
        emai_id = (EditText) view.findViewById(R.id.email_id);
        mob_no = (EditText) view.findViewById(R.id.mobile_no);
        no_of_person = (Spinner) view.findViewById(R.id.no_of_person);
        message = (EditText) view.findViewById(R.id.message);
        update = (Button) view.findViewById(R.id.update);

        NameTil = (TextInputLayout) view.findViewById(R.id.input_layout_name);
        EmailTil = (TextInputLayout) view.findViewById(R.id.input_layout_email);
        MobileNoTil = (TextInputLayout) view.findViewById(R.id.input_layout_mobile_no);
        String no_of_person_array[] = {"1","2","3","4","5"};
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, no_of_person_array); //selected item will look like a spinner set from XML
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        no_of_person.setAdapter(spinnerArrayAdapter);
        MessageTil = (TextInputLayout) view.findViewById(R.id.input_layout_message);

        update.setOnClickListener(this);
        phone.setTypeface(font);
        phone.setOnClickListener(this);
        close.setOnClickListener(this);
        cancel.setOnClickListener(this);
        String number = "18008436411";
        Uri call = Uri.parse("tel:" + number);
        surf = new Intent(Intent.ACTION_CALL, call);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.close:
                if (getFragmentManager().getBackStackEntryCount() > 0) {
                    getFragmentManager().popBackStack();
                }
                break;
            case R.id.cancel:
                if (getFragmentManager().getBackStackEntryCount() > 0) {
                    getFragmentManager().popBackStack();
                }
                break;
            case R.id.your_name:
                break;
            case R.id.email_id:
                break;
            case R.id.mobile_no:
                break;
            case R.id.message:
                break;
            case R.id.phone:

                if (ContextCompat.checkSelfPermission(getContext(),
                        Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED) {

                    requestPermissions(
                            new String[]{Manifest.permission.CALL_PHONE},
                            MY_PERMISSIONS_REQUEST_CALL_PHONE);

                    // MY_PERMISSIONS_REQUEST_CALL_PHONE is an
                    // app-defined int constant. The callback method gets the
                    // result of the request.
                } else {
                    //You already have permission
                    try {
                        startActivity(surf);
                    } catch(SecurityException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case R.id.update:
                /*if(your_name.getText().length()>0 && emai_id.getText().length()>0 && phone.getText().length()>0 && message.getText().length()>0)
                {*/
                    firstName = your_name.getText().toString();
                    email = emai_id.getText().toString();
                    phone_text = phone.getText().toString();
                    message_text = message.getText().toString();
                    mobile_no = mob_no.getText().toString();
                    no_person = no_of_person.getSelectedItem().toString();

                    NameTil.setErrorEnabled(false);
                    MessageTil.setErrorEnabled(false);
                    MobileNoTil.setErrorEnabled(false);
                    EmailTil.setErrorEnabled(false);

                    if(firstName.isEmpty()){
                        //signUpLastNameTil.setErrorEnabled(false);
                        NameTil.setError("Please enter name");
                    }/*else if(!valueName.isEmpty() && valueName.length() < 3){
                        signUpNameTil.setError("the First Name field must be at least 2 characters in length");
                    }*/
                    else if(email.isEmpty()){
                        //signUpNameTil.setErrorEnabled(false);
                        EmailTil.setError("Please enter email");
                    }/*else if(!valueLastName.isEmpty() && valueLastName.length() < 3){
                        signUpLastNameTil.setError("the Last Name field must be at least 2 characters in length");
                    }*/
                    else if(mobile_no.isEmpty()){
                        //signUpLastNameTil.setErrorEnabled(false);
                        MobileNoTil.setError("Please enter phone");
                    }else if(no_person.isEmpty()){
                        //signUpLastNameTil.setErrorEnabled(false);
                        ((TextView)no_of_person.getSelectedView()).setError("Please enter no of person");
                    }
                    else if(message_text.isEmpty()){
                        //signUpLastNameTil.setErrorEnabled(false);
                        MessageTil.setError("Please enter message.");
                    }else {

                        EmailTil.setErrorEnabled(false);
                        if (AppConfig.validateEmail(email)) {
                           /* //signUpEmailTil.setErrorEnabled(false);
                            if (AppConfig.validateEmail(valueConfirmEmail)) {
                                //signUpConfirmEmailTil.setErrorEnabled(false);
                                if (valueEmail.equals(valueConfirmEmail)) {
                                    //signUpEmailTil.setErrorEnabled(false);
                                    //signUpConfirmEmailTil.setErrorEnabled(false);*/
                           sendQuery();

                                /*} else {
                                    signUpEmailTil.setError("*both email address should match");
                                    signUpConfirmEmailTil.setError("*both email address should match");
                                }
                            }else{
                                signUpConfirmEmailTil.setError("The Email field must contain a valid email address");
                            }*/
                        } else {
                            EmailTil.setError("Please enter valid email");
                        }
                    }
               /* }else{
                    Toast.makeText(getContext(),"Please fill the form completely!", Toast.LENGTH_SHORT).show();
                }*/
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CALL_PHONE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the phone call
                    startActivity(surf);
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(getContext(), "No permission", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    private void sendQuery()
    {
        jsonObject = new JSONObject();
        try {
            if(null!= AppConfig.getUser_id())
            {
                jsonObject.put("package_id", AppConfig.vacation_package_seletedppackege);
                jsonObject.put("first_name", firstName);
                jsonObject.put("email", email);
                jsonObject.put("phone", phone_text);
                jsonObject.put("message", message_text);
                VolleyRequest volleyRequest = new VolleyRequest(getContext());
                volleyRequest.vacation_package_moreDetails_sendEnquiry(jsonObject, getView());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
