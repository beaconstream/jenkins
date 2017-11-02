package com.travelur.App_Loading;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.travelur.Signed_In_Home.MainActivity;
import com.travelur.R;
import com.travelur.Utility.AppController;
import com.travelur.Utility.Utils;
import com.travelur.Web_Services.VolleyRequest;
import com.viewpagerindicator.CirclePageIndicator;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.travelur.Utility.Utils.REGISTER_URL;
import static com.travelur.Web_Services.VolleyRequest.signUpUser;

public class StartupScreen extends AppCompatActivity implements View.OnClickListener {
    TextView skip, new_member, new_member1, old_memeber, old_memeber1, forget_your_password, signup_clos, signin_close, forgetpassword_close, setpassword_close;
    EditText name, lastName, email, confirmEmail;
    View video_layout, sign_up_layout, sign_in_layout, forget_password, set_pwd_layout;
    LinearLayout main_screen;
    Button sign_up, sign_in, sign_inn, join_now;
    ViewPager viewPager;
    ViewPagerAdapter adapter;
    Animation animation, animation1;
    CirclePageIndicator circlePageIndicator;

    private VideoView myVideoView;
    private int position = 0;
    private ProgressDialog progressDialog;
    private MediaController mediaControls;
    String imageUri = "drawable://" + R.drawable.video_layout;
    private Intent intent;
    private String KEY_NAME, KEY_LASTNAME, KEY_EMAIL, NAME, LAST_NAME, EMAIL;
    ProgressDialog pDialog;
    private JSONObject register_User_JsonObject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startup_screen);
        pDialog = new ProgressDialog(this);
        intialize();
        animation = AnimationUtils.loadAnimation(this, R.anim.slide_up);
        animation1 = AnimationUtils.loadAnimation(this, R.anim.flip_around);
        //initialize the VideoView
        myVideoView = (VideoView) findViewById(R.id.videoView);

        /*Bitmap thumb = ThumbnailUtils.createVideoThumbnail(imageUri,
                MediaStore.Images.Thumbnails.MINI_KIND);

        BitmapDrawable bitmapDrawable = new BitmapDrawable(thumb);
        myVideoView.setBackground(bitmapDrawable);*/
        //myVideoView.setBackgroundResource(R.drawable.video_layout);
    }

    public void intialize() {
        Typeface font = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");

        skip = (TextView) findViewById(R.id.skip);
        video_layout = findViewById(R.id.video_lay);
        main_screen = (LinearLayout) findViewById(R.id.main_screen);
        skip.setOnClickListener(this);
        sign_up_layout = findViewById(R.id.sign_up);
        set_pwd_layout = findViewById(R.id.set_pwd);
        sign_in_layout = findViewById(R.id.sign_in);
        forget_password = findViewById(R.id.forget);
        new_member = (TextView) sign_in_layout.findViewById(R.id.new_member);
        old_memeber = (TextView) sign_up_layout.findViewById(R.id.old_member);
        old_memeber1 = (TextView) set_pwd_layout.findViewById(R.id.old_member1);
        sign_up = (Button) video_layout.findViewById(R.id.join_now);
        sign_in = (Button) video_layout.findViewById(R.id.sign_inn);
        new_member1 = (TextView) forget_password.findViewById(R.id.new_member1);
        forget_your_password = (TextView) sign_in_layout.findViewById(R.id.forget_your_password);
        signup_clos = (TextView) sign_up_layout.findViewById(R.id.close);
        signin_close = (TextView) sign_in_layout.findViewById(R.id.close1);
        forgetpassword_close = (TextView) forget_password.findViewById(R.id.close2);
        setpassword_close = (TextView) set_pwd_layout.findViewById(R.id.close3);
        sign_inn = (Button) sign_in_layout.findViewById(R.id.signn_in);
        join_now = (Button) sign_up_layout.findViewById(R.id.join_now);

        name = (EditText) sign_up_layout.findViewById(R.id.name);
        lastName = (EditText) sign_up_layout.findViewById(R.id.lastname);
        email = (EditText) sign_up_layout.findViewById(R.id.email);
        confirmEmail = (EditText) sign_up_layout.findViewById(R.id.confirm_email);

        signup_clos.setTypeface(font);
        signin_close.setTypeface(font);
        setpassword_close.setTypeface(font);
        forgetpassword_close.setTypeface(font);
        sign_up.setOnClickListener(this);
        sign_in.setOnClickListener(this);
        new_member.setOnClickListener(this);
        old_memeber.setOnClickListener(this);
        old_memeber1.setOnClickListener(this);
        new_member1.setOnClickListener(this);
        sign_inn.setOnClickListener(this);
        join_now.setOnClickListener(this);
        forget_your_password.setOnClickListener(this);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        circlePageIndicator = (CirclePageIndicator) findViewById(R.id.titles);
        adapter = new ViewPagerAdapter(this, viewPager);
        viewPager.setAdapter(adapter);
        circlePageIndicator.setViewPager(viewPager);

    }

    @Override
    public void onClick(final View view) {
        switch (view.getId()) {
            case R.id.skip:
                main_screen.setVisibility(View.GONE);
                video_layout.setVisibility(View.VISIBLE);
                /*intent = new Intent(getApplicationContext(), com.travelur.recyclerview.MainActivity.class);
                startActivity(intent);*/
                break;
            case R.id.join_now:
                //video_layout.setVisibility(View.GONE);
                //sign_up_layout.setVisibility(View.GONE);
                //set_pwd_layout.setVisibility(View.VISIBLE);
                //sign_up_layout.startAnimation(animation);
                //set_pwd_layout.startAnimation(animation);

                //call Web_Services
                NAME = name.getText().toString().trim();
                LAST_NAME = lastName.getText().toString().trim();
                EMAIL = email.getText().toString().trim();
                register_User_JsonObject = new JSONObject();
                try {
                    register_User_JsonObject.put(Utils.KEY_NAME,NAME);
                    register_User_JsonObject.put(Utils.KEY_LASTNAME,LAST_NAME);
                    register_User_JsonObject.put(Utils.KEY_EMAIL,EMAIL);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (Utils.emailValidate(getBaseContext(), EMAIL)) {
                    VolleyRequest volleyRequest = new VolleyRequest(StartupScreen.this);
                    volleyRequest.signUpUser(register_User_JsonObject);
                }

                break;
            case R.id.sign_inn:
                video_layout.setVisibility(View.GONE);
                sign_in_layout.setVisibility(View.VISIBLE);
                sign_in_layout.startAnimation(animation);
                break;
            case R.id.new_member:
                video_layout.setVisibility(View.GONE);
                sign_in_layout.setVisibility(View.GONE);
                sign_up_layout.setVisibility(View.VISIBLE);
                sign_up_layout.startAnimation(animation1);
                break;
            case R.id.old_member:
                video_layout.setVisibility(View.GONE);
                sign_up_layout.setVisibility(View.GONE);
                sign_in_layout.setVisibility(View.VISIBLE);
                break;
            case R.id.old_member1:
                video_layout.setVisibility(View.GONE);
                set_pwd_layout.setVisibility(View.GONE);
                sign_in_layout.setVisibility(View.VISIBLE);
                break;
            case R.id.forget_your_password:
                video_layout.setVisibility(View.GONE);
                sign_in_layout.setVisibility(View.GONE);
                forget_password.setVisibility(View.VISIBLE);
                forget_password.setAnimation(animation1);
                break;
            case R.id.new_member1:
                video_layout.setVisibility(View.GONE);
                forget_password.setVisibility(View.GONE);
                sign_up_layout.setVisibility(View.VISIBLE);
                break;
            case R.id.signn_in:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
        }

        //set the media controller buttons
        if (mediaControls == null) {
            mediaControls = new MediaController(StartupScreen.this);
        }

        // create a progress bar while the video file is loading
        progressDialog = new ProgressDialog(StartupScreen.this);
        // set a title for the progress bar
        progressDialog.setTitle("Please wait. We are loading the video...");
        // set a message for the progress bar
        progressDialog.setMessage("Loading...");
        //set the progress bar not cancelable on users' touch
        progressDialog.setCancelable(true);
        // show the progress bar
       /* if(view.getId() == R.id.skip)
        {
            progressDialog.show();
        }*/
        progressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "DISMISS", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        //progressDialog.show();

        try {
            //set the media controller in the VideoView
            myVideoView.setMediaController(mediaControls);

            //set the uri of the video to be played
            myVideoView.setVideoURI(Uri.parse("https://www.travelur.com/extras/assets/video/Travel_UR.mp4"));

        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }

        myVideoView.requestFocus();
        //we also set an setOnPreparedListener in order to know when the video file is ready for playback
        myVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            public void onPrepared(MediaPlayer mediaPlayer) {
                // close the progress bar and play the video
                progressDialog.dismiss();
                //if we have a position on savedInstanceState, the video playback should start from here
                myVideoView.seekTo(position);
                if (position == 0 && view.getId() == R.id.skip) {
                    myVideoView.start();
                    myVideoView.setBackground(null);
                } else {
                    //if we come from a resumed activity, video playback will be paused
                    myVideoView.pause();
                }
            }
        });
        myVideoView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent motionEvent) {
                if (myVideoView.isPlaying()) {
                    myVideoView.pause();
                   /* if (!getActionBar().isShowing()) {
                        getActionBar().show();
                        mediaControls.show(0);
                        progressDialog.show();
                    }*/
                    mediaControls.show(0);
                    position = myVideoView.getCurrentPosition();
                    return false;
                } else {
                    /*if (getActionBar().isShowing()) {
                        getActionBar().hide();
                        mediaControls.hide();
                    }*/
                    progressDialog.show();
                    mediaControls.hide();
                    myVideoView.seekTo(position);
                    myVideoView.start();
                    return false;
                }
            }
        });

        myVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                //here
                myVideoView.seekTo(1);
            }
        });
        //here
        if (position != 1) {
            myVideoView.seekTo(position);
            myVideoView.start();
        } else {
            //here
            myVideoView.seekTo(1);
        }
}

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        //we use onSaveInstanceState in order to store the video playback position for orientation change
        if (myVideoView != null)
        {
            savedInstanceState.putInt("position", myVideoView.getCurrentPosition());
        }
        myVideoView.pause();
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        //we use onRestoreInstanceState in order to play the video playback from the stored position
        position = savedInstanceState.getInt("Position");
        myVideoView.seekTo(position);
    }

}
