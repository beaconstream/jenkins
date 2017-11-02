package com.travelur.travelconnect.apploading;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
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
import com.travelur.general.BaseActivity;
import com.travelur.travelconnect.signedinhome.MainActivity;
import com.travelur.utility.AppConfig;
import static com.travelur.utility.AppConfig.splashdeay;

/*
 * @author by Abhijit .
 */

public class SplashScreen extends BaseActivity implements GoogleApiClient.OnConnectionFailedListener {
    private ImageView img1,img2,img4,img5,img6;
    private ObjectAnimator objectanimator1;
    private GoogleApiClient mGoogleApiClient;
    private String TAG = "[DEBUG]";
    private String user_id, email;
    private Uri data = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash_screen);

        img1 = (ImageView)findViewById(R.id.imageView3);
        img2 = (ImageView)findViewById(R.id.imageView2);
        img4 = (ImageView)findViewById(R.id.imageView4);
        img5 = (ImageView)findViewById(R.id.imageView5);
        img6 = (ImageView)findViewById(R.id.imageView6);

        Intent intent = getIntent();
        String action = intent.getAction();
        data = intent.getData();

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

        objectanimator1 = ObjectAnimator.ofFloat(img1,"y",1500,800);//here 'y' is for vertical floating of he image
        objectanimator1.setDuration(2000);
        objectanimator1.start();

        ObjectAnimator scaleX = ObjectAnimator.ofFloat(img2, "scaleX", 1.7f, 2.5f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(img2, "scaleY", 1.7f, 2.5f);
        ObjectAnimator scaleX4 = ObjectAnimator.ofFloat(img4, "scaleX", 1.7f, 2.5f);
        ObjectAnimator scaleY4 = ObjectAnimator.ofFloat(img4, "scaleY", 1.7f, 2.5f);
        ObjectAnimator scaleX5 = ObjectAnimator.ofFloat(img5, "scaleX", 1.7f, 2.5f);
        ObjectAnimator scaleY5 = ObjectAnimator.ofFloat(img5, "scaleY", 1.7f, 2.5f);
        ObjectAnimator scaleX6 = ObjectAnimator.ofFloat(img6, "scaleX", 1.7f, 2.5f);
        ObjectAnimator scaleY6 = ObjectAnimator.ofFloat(img6, "scaleY", 1.7f, 2.5f);

        final AnimatorSet scaleAnim = new AnimatorSet();

        scaleAnim.setDuration(AppConfig.DURATION);

        scaleX.setRepeatCount(ObjectAnimator.RESTART);
        scaleX.setRepeatMode(ObjectAnimator.REVERSE);
        scaleY.setRepeatCount(ObjectAnimator.RESTART);
        scaleY.setRepeatMode(ObjectAnimator.REVERSE);
        scaleX4.setRepeatCount(ObjectAnimator.RESTART);
        scaleX4.setRepeatMode(ObjectAnimator.REVERSE);
        scaleY4.setRepeatCount(ObjectAnimator.RESTART);
        scaleY4.setRepeatMode(ObjectAnimator.REVERSE);
        scaleX5.setRepeatCount(ObjectAnimator.RESTART);
        scaleX5.setRepeatMode(ObjectAnimator.REVERSE);
        scaleY5.setRepeatCount(ObjectAnimator.RESTART);
        scaleY5.setRepeatMode(ObjectAnimator.REVERSE);
        scaleX6.setRepeatCount(ObjectAnimator.RESTART);
        scaleX6.setRepeatMode(ObjectAnimator.REVERSE);
        scaleY6.setRepeatCount(ObjectAnimator.RESTART);
        scaleY6.setRepeatMode(ObjectAnimator.REVERSE);

        scaleAnim.play(scaleX).with(scaleY).before(scaleX4).with(scaleY4);
        scaleAnim.play(scaleX4).with(scaleY4).before(scaleX5).with(scaleY5);
        scaleAnim.play(scaleX5).with(scaleY5).before(scaleX6).with(scaleY6);
        scaleAnim.play(scaleX6);

        objectanimator1.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

                img2.setVisibility(View.VISIBLE);
                img4.setVisibility(View.VISIBLE);
                img5.setVisibility(View.VISIBLE);
                img6.setVisibility(View.VISIBLE);
                scaleAnim.start();
                scaleAnim.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        scaleAnim.start();
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(null!=data)
                {
                    scaleAnim.end();
                    if(null!=data.getQueryParameter("userid") && null!=data.getQueryParameter("email"))
                    {
                        user_id = data.getQueryParameter("userid");
                        email = data.getQueryParameter("email");
                    }
                    Log.d("[DEBUG]",user_id+" , "+email);
                    openDeepLink(data);
                }// Check if user is already logged in or not
                else if (session.isLoggedIn()) {
                    // User is already logged in. Take him to main activity
                    Intent intent = new Intent(SplashScreen.this,
                            MainActivity.class);
                    startActivity(intent);
                    finish();
                }else if(session.isGuestLoggedIn()){
                    // User is already logged in. Take him to main activity
                    Intent intent = new Intent(SplashScreen.this,
                            MainActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    scaleAnim.end();
                    Intent i = new Intent(SplashScreen.this, StartupScreen.class);
                    startActivity(i);
                    finish();
                }

            }
        }, splashdeay);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    // To handle Deep link, opening different activities for different path/pathPrefix
    private void openDeepLink(Uri deepLink) {
        String path = deepLink.getPath();

        Log.d("[DEBUG]",path);
        if ("/general/travelur_set_password".equals(path)) {
            // Launch preferences
            Intent i = new Intent(SplashScreen.this, StartupScreen.class);
            i.putExtra("user_id",user_id);
            i.putExtra("email",email);
            startActivity(i);
            finish();
        }
    }
}
