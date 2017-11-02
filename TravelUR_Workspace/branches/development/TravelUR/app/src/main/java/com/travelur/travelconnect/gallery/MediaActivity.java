package com.travelur.travelconnect.gallery;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.travelur.R;
import com.travelur.general.BaseActivity;
import com.travelur.travelconnect.gallery.model.Media;
import com.travelur.utility.AppConfig;

import java.util.ArrayList;
import static com.travelur.utility.AppConfig.MEDIA_TYPE_PHOTOS;
import static com.travelur.utility.AppConfig.MEDIA_TYPE_PHOTOS_SINGLE_SELECTION;
import static com.travelur.utility.AppConfig.MEDIA_TYPE_VIDEOS;
import static com.travelur.utility.AppConfig.PHOTO_LIST;
import static com.travelur.utility.AppConfig.SINGLE_PHOTO_LIST;
import static com.travelur.utility.AppConfig.VIDEO_EXTRA;

/*
 * @author by Abhijit .
 */

public class MediaActivity extends BaseActivity implements PhotoFragmentSingleSelection.OnFragmentInteractionListener, PhotosFragment.OnFragmentInteractionListener, VideosFragment.OnFragmentInteractionListener, View.OnClickListener {

    private ImageView close;
    private TextView title;
    private PhotoFragmentSingleSelection photoFragmentSingleSelection;
    private PhotosFragment photosFragment;
    private VideosFragment videosFragment;

    private static final int REQUEST_PERMISSIONS = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if ((ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) && (ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
            if ((ActivityCompat.shouldShowRequestPermissionRationale(MediaActivity.this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) && (ActivityCompat.shouldShowRequestPermissionRationale(MediaActivity.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE))) {

            } else {
                ActivityCompat.requestPermissions(MediaActivity.this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_PERMISSIONS);
            }
        }else {
            Log.e("Else","Else");
            displayMedia();
        }

        setContentView(R.layout.activity_media);
        close = (ImageView) findViewById(R.id.close);
        close.setBackgroundResource(R.drawable.ic_action_arrowleft);
        title = (TextView) findViewById(R.id.action_bar_title);
        title.setText("Camera Roll");
        close.setOnClickListener(this);
    }

    void displayMedia() {
        Intent intent = getIntent();
        if (intent != null) {
            int mediaType = intent.getIntExtra(AppConfig.MEDIA_TYPE, AppConfig.DEFAULT);
            if (mediaType == MEDIA_TYPE_PHOTOS) {
                if (photosFragment == null) {
                    photosFragment = PhotosFragment.newInstance();
                    //intitialiseFragment_LeftToRightTransition_mediaType(photosFragment);
                }
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.frameContainer, photosFragment);
                ft.commitAllowingStateLoss();
            } else if (mediaType == MEDIA_TYPE_VIDEOS) {
                if (videosFragment == null) {
                    videosFragment = VideosFragment.newInstance();
                    //intitialiseFragment_LeftToRightTransition_mediaType(videosFragment);
                }
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.frameContainer, videosFragment);
                ft.commit();
            }else if (mediaType == MEDIA_TYPE_PHOTOS_SINGLE_SELECTION) {
                if (photoFragmentSingleSelection == null) {
                    photoFragmentSingleSelection = PhotoFragmentSingleSelection.newInstance();
                    //intitialiseFragment_LeftToRightTransition_mediaType(photosFragment);
                }
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.frameContainer, photoFragmentSingleSelection);
                ft.commitAllowingStateLoss();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case REQUEST_PERMISSIONS: {
                for (int i = 0; i < grantResults.length; i++) {
                    if (grantResults.length > 0 && grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                        displayMedia();
                    } else {
                        Toast.makeText(MediaActivity.this, "The app was not allowed to read or write to your storage. Hence, it cannot function properly. Please consider granting it this permission", Toast.LENGTH_LONG).show();
                    }
                }
            }
        }
    }

    @Override
    public void onPhotosSelected(ArrayList<Media> photoList) {
        Intent intent = new Intent();
        intent.putParcelableArrayListExtra(PHOTO_LIST, photoList);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onVideoSelected(Media media) {
        Intent intent = new Intent();
        intent.putExtra(VIDEO_EXTRA, media);
        setResult(RESULT_OK, intent);
        finish();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.close:
               finish();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onImageSelected(Media media) {
        Intent intent = new Intent();
        intent.putExtra(SINGLE_PHOTO_LIST, media);
        setResult(RESULT_OK, intent);
        finish();
    }
}
