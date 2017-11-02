package com.travelur.App_Loading;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.google.android.gms.appinvite.AppInvite;
import com.google.android.gms.appinvite.AppInviteInvitationResult;
import com.google.android.gms.appinvite.AppInviteReferral;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.travelur.R;
import com.travelur.Signed_In_Home.MainActivity;

public class SplashScreen extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    private long splashdeay = 5000;
    ImageView img1,img2,img4,img5,img6;
    ObjectAnimator objectanimator1;
    private GoogleApiClient mGoogleApiClient;
    private String TAG = "[DEBUG]";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_splash_screen);

        Intent intent = getIntent();
        String action = intent.getAction();
        Uri data = intent.getData();

        if(null!=data)
        {
            openDeepLink(data);
        }

        // Build GoogleApiClient with AppInvite API for receiving deep links
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(AppInvite.API)
                .build();

        // Check if this app was launched from a deep link. Setting autoLaunchDeepLink to true
        // would automatically launch the deep link if one is found.
        boolean autoLaunchDeepLink = false;
        AppInvite.AppInviteApi.getInvitation(mGoogleApiClient, this, autoLaunchDeepLink)
                .setResultCallback(
                        new ResultCallback<AppInviteInvitationResult>() {
                            @Override
                            public void onResult(@NonNull AppInviteInvitationResult result) {
                                if (result.getStatus().isSuccess()) {
                                    // Extract deep link from Intent
                                    Intent intent = result.getInvitationIntent();
                                    String deepLink = AppInviteReferral.getDeepLink(intent);

                                    // Handle the deep link. For example, open the linked
                                    // content, or apply promotional credit to the user's
                                    // account.

                                    // ...
                                } else {
                                    Log.d(TAG, "getInvitation: no deep link found.");
                                }
                            }
                        });

        /*
        Changed by Abhijit on 24-05-2017
         */
        img1 = (ImageView)findViewById(R.id.imageView3);
        img2 = (ImageView)findViewById(R.id.imageView2);
        img4 = (ImageView)findViewById(R.id.imageView4);
        img5 = (ImageView)findViewById(R.id.imageView5);
        img6 = (ImageView)findViewById(R.id.imageView6);

        objectanimator1 = ObjectAnimator.ofFloat(img1,"y",1500,800);//here 'y' is for vertical floating of he image
        objectanimator1.setDuration(2000);
        objectanimator1.start();

       /* //Parse the SVG file from the resource
        SVG svg = SVGParser.getSVGFromResource(getResources(), R.raw.anim_logo);
        //Get a drawable from the parsed SVG and apply to ImageView
        img2.setImageDrawable(svg.createPictureDrawable());*/

        ObjectAnimator scaleX = ObjectAnimator.ofFloat(img2, "scaleX", 1.7f, 2.0f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(img2, "scaleY", 1.7f, 2.0f);
        ObjectAnimator scaleX4 = ObjectAnimator.ofFloat(img4, "scaleX", 1.7f, 2.0f);
        ObjectAnimator scaleY4 = ObjectAnimator.ofFloat(img4, "scaleY", 1.7f, 2.0f);
        ObjectAnimator scaleX5 = ObjectAnimator.ofFloat(img5, "scaleX", 1.7f, 2.0f);
        ObjectAnimator scaleY5 = ObjectAnimator.ofFloat(img5, "scaleY", 1.7f, 2.0f);
        ObjectAnimator scaleX6 = ObjectAnimator.ofFloat(img6, "scaleX", 1.7f, 2.0f);
        ObjectAnimator scaleY6 = ObjectAnimator.ofFloat(img6, "scaleY", 1.7f, 2.0f);

        AnimatorSet scaleAnim = new AnimatorSet();
        AnimatorSet scaleAnim4 = new AnimatorSet();
        AnimatorSet scaleAnim5 = new AnimatorSet();
        AnimatorSet scaleAnim6 = new AnimatorSet();

        scaleAnim.setDuration(400);
        scaleAnim4.setDuration(450);
        scaleAnim5.setDuration(500);
        scaleAnim6.setDuration(550);
        scaleAnim.play(scaleX).with(scaleY);
        scaleAnim4.play(scaleX4).with(scaleY4);
        scaleAnim5.play(scaleX5).with(scaleY5);
        scaleAnim6.play(scaleX6).with(scaleY6);

        scaleX.setRepeatCount(ObjectAnimator.INFINITE);
        scaleX.setRepeatMode(ObjectAnimator.REVERSE);
        scaleY.setRepeatCount(ObjectAnimator.INFINITE);
        scaleY.setRepeatMode(ObjectAnimator.REVERSE);
        scaleX4.setRepeatCount(ObjectAnimator.INFINITE);
        scaleX4.setRepeatMode(ObjectAnimator.REVERSE);
        scaleY4.setRepeatCount(ObjectAnimator.INFINITE);
        scaleY4.setRepeatMode(ObjectAnimator.REVERSE);
        scaleX5.setRepeatCount(ObjectAnimator.INFINITE);
        scaleX5.setRepeatMode(ObjectAnimator.REVERSE);
        scaleY5.setRepeatCount(ObjectAnimator.INFINITE);
        scaleY5.setRepeatMode(ObjectAnimator.REVERSE);
        scaleX6.setRepeatCount(ObjectAnimator.INFINITE);
        scaleX6.setRepeatMode(ObjectAnimator.REVERSE);
        scaleY6.setRepeatCount(ObjectAnimator.INFINITE);
        scaleY6.setRepeatMode(ObjectAnimator.REVERSE);

        scaleAnim.start();
        scaleAnim4.start();
        scaleAnim5.start();
        scaleAnim6.start();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashScreen.this, StartupScreen.class);
                startActivity(i);
                finish();
            }
        }, splashdeay);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    // To handle Deep link, opening different activities for different path/pathPrefix
    private void openDeepLink(Uri deepLink) {
        String path = deepLink.getPath();

        if ("setPassword".equals(path)) {
// Launch preferences
            startActivity(new Intent(this, MainActivity.class));
            //selectedFragment = SignedInHome.newInstance();
        } /*else if ("video".equals(path)) {
// Launch the inbox activity
            startActivity(new Intent(this, InboxActivity.class));
        } else {
// Fall back to the main activity
            startActivity(new Intent(this, MainActivity.class));
        }*/
    }
}
