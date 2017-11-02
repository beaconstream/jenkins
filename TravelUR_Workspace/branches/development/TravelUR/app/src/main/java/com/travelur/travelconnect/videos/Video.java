package com.travelur.travelconnect.videos;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;

import com.travelur.R;
import com.travelur.backend.VolleyRequest;
import com.travelur.general.BaseFragment;
import com.travelur.travelconnect.gallery.MediaActivity;
import com.travelur.travelconnect.gallery.model.Media;
import com.travelur.travelconnect.photos.adapters.DetailPhoto;
import com.travelur.travelconnect.photos.adapters.ImageGalleryAdapter;
import com.travelur.travelconnect.photos.adapters.VideoGalleryAdapter;
import com.travelur.travelconnect.profile.ProfileActivity;
import com.travelur.utility.AppConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;
import static com.travelur.utility.AppConfig.MEDIA_TYPE;
import static com.travelur.utility.AppConfig.MEDIA_TYPE_VIDEOS;
import static com.travelur.utility.AppConfig.PHOTO_LIST;
import static com.travelur.utility.AppConfig.REQUEST_CODE_PHOTOS;
import static com.travelur.utility.AppConfig.REQUEST_CODE_VIDEOS;
import static com.travelur.utility.AppConfig.VIDEO_EXTRA;

/**
 * @author by Abhijit.
 */

public class Video extends BaseFragment implements View.OnClickListener{

    private ArrayList<String> selectionResult = new ArrayList<>();
    private static final String TAG = ProfileActivity.class.getSimpleName();
    private JSONObject jsonObject = null;
    private RecyclerView.Adapter adapter;
    public static Video newInstance() {
        Video fragment = new Video();
        return fragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.media_recycler_view, null, false);

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.setClickable(true);
        close = (ImageView) view.findViewById(R.id.close);
        close.setBackgroundResource(R.drawable.ic_action_arrowleft);
        title = (TextView) view.findViewById(R.id.action_bar_title);
        title.setText("Your Videos");
        custom_toolbar_menu_item = (TextView) view.findViewById(R.id.custom_toolbar_menu_item);
        custom_toolbar_menu_item.setText("Add");

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 4);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv_images);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        //ImageGalleryAdapter adapter = new ImageGalleryAdapter(getContext(), AppConfig.urphoto_List, getActivity().getSupportFragmentManager());
        adapter = new VideoGalleryAdapter(getContext(), AppConfig.urvideo_List, getActivity().getSupportFragmentManager());
        AppConfig.volleyRecyclerViewAdapter = adapter;
        recyclerView.setAdapter(adapter);

      /*  jsonObject = new JSONObject();
        try {
            if(null!= AppConfig.getUser_id())
            {*/
        //jsonObject.put("user_id", AppConfig.getUser_id());
        VolleyRequest volleyRequest = new VolleyRequest(getContext());
        //volleyRequest.signedInHome_Profile_Photos(jsonObject, adapter);
        volleyRequest.signedInHome_Profile_Videos();
        close.setOnClickListener(this);
        custom_toolbar_menu_item.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.close:
                if (getFragmentManager().getBackStackEntryCount() > 0) {
                    getFragmentManager().popBackStack();
                }
                break;
            case R.id.custom_toolbar_menu_item:
                // Checking camera availability
                if (!isDeviceSupportCamera()) {
                    Toast.makeText(getContext(),
                            "Sorry! Your device doesn't support camera",
                            Toast.LENGTH_LONG).show();
                    // will close the app if the device does't have camera
                    getActivity().finish();
                }else{
                    //recordVideo();
                    Intent intent = new Intent(getContext(), MediaActivity.class);
                    intent.putExtra(MEDIA_TYPE, MEDIA_TYPE_VIDEOS);
                    startActivityForResult(intent, REQUEST_CODE_VIDEOS);
                }
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(!selectionResult.isEmpty()){
            selectionResult.clear();
        }
        switch (requestCode) {

            case REQUEST_CODE_VIDEOS:

                if (resultCode == RESULT_OK && data != null) {
                    Media selectedVideo = data.getParcelableExtra(VIDEO_EXTRA);
                    Log.d(TAG, "selected video path = " + selectedVideo.path);
                    selectionResult.add(selectedVideo.path);
                }
                JSONObject jsonObject = new JSONObject();
                //JSONArray jsonArray =  new JSONArray(IMAGEPTHLIST);
                JSONArray jsonArray =  new JSONArray(selectionResult);
                try {
                    if(null!= AppConfig.getUser_id())
                    {
                        jsonObject.put(AppConfig.KEY_USER_ID, AppConfig.getUser_id());
                        jsonObject.put(AppConfig.KEY_IMAGEPATH, jsonArray);

                        VolleyRequest volleyRequest = new VolleyRequest(getContext());
                        volleyRequest.signedInHome_Profile_UploadVideos(jsonObject);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;

        }
    }
    /**
     * Checking device has camera hardware or not
     * */
    private boolean isDeviceSupportCamera() {
        if (getContext().getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_CAMERA)) {
            // this device has a camera
            return true;
        } else {
            // no camera on this device
            return false;
        }
    }
}
