package com.travelur.travelconnect.photos;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.travelur.R;
import com.travelur.backend.VolleyRequest;
import com.travelur.general.BaseFragment;
import com.travelur.travelconnect.gallery.MediaActivity;
import com.travelur.travelconnect.gallery.model.Media;
import com.travelur.travelconnect.photos.adapters.ImageGalleryAdapter;
import com.travelur.travelconnect.photos.models.PhotoDataModel;
import com.travelur.travelconnect.profile.ProfileActivity;
import com.travelur.travelconnect.settings.Setting;
import com.travelur.travelconnect.signedinhome.MainActivity;
import com.travelur.utility.AppConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;
import static com.travelur.utility.AppConfig.MEDIA_TYPE;
import static com.travelur.utility.AppConfig.MEDIA_TYPE_PHOTOS;
import static com.travelur.utility.AppConfig.MEDIA_TYPE_VIDEOS;
import static com.travelur.utility.AppConfig.PHOTO_LIST;
import static com.travelur.utility.AppConfig.REQUEST_CODE_PHOTOS;
import static com.travelur.utility.AppConfig.REQUEST_CODE_VIDEOS;
import static com.travelur.utility.AppConfig.VIDEO_EXTRA;

/**
 * @author by Abhijit.
 */

public class Photo extends BaseFragment implements View.OnClickListener {

    private ArrayList<String> selectionResult = new ArrayList<>();
    public static final String TAG = ProfileActivity.class.getSimpleName();
    private JSONObject jsonObject = null;
    private boolean isImage_data = false;
    private RecyclerView.Adapter adapter;

    public static Photo newInstance() {
        Photo fragment = new Photo();
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
        title.setText("Photos");
        custom_toolbar_menu_item = (TextView) view.findViewById(R.id.custom_toolbar_menu_item);
        custom_toolbar_menu_item.setText("Add");

        close.setOnClickListener(this);
        custom_toolbar_menu_item.setOnClickListener(this);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 4);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv_images);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        //ImageGalleryAdapter adapter = new ImageGalleryAdapter(getContext(), AppConfig.urphoto_List, getActivity().getSupportFragmentManager());
        adapter = new ImageGalleryAdapter(getContext(), AppConfig.urphoto_List, getActivity().getSupportFragmentManager());
        AppConfig.volleyRecyclerViewAdapter = adapter;
        recyclerView.setAdapter(adapter);

      /*  jsonObject = new JSONObject();
        try {
            if(null!= AppConfig.getUser_id())
            {*/
                //jsonObject.put("user_id", AppConfig.getUser_id());
                VolleyRequest volleyRequest = new VolleyRequest(getContext());
                //volleyRequest.signedInHome_Profile_Photos(jsonObject, adapter);
                volleyRequest.signedInHome_Profile_Photos();
           /* }
        } catch (JSONException e) {
            e.printStackTrace();
        }*/

       /* Glide.with(this)
                .load(spacePhoto.getUrl())
                .asBitmap()
                .error(R.drawable.error_icon)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(mImageView);*/

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
                }else {
                    //captureImage();
                    Intent intent = new Intent(getContext(), MediaActivity.class);
                    intent.putExtra(MEDIA_TYPE, MEDIA_TYPE_PHOTOS);
                    startActivityForResult(intent, REQUEST_CODE_PHOTOS);
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

            case REQUEST_CODE_PHOTOS:
                if (resultCode == RESULT_OK && data != null) {
                    ArrayList<Media> mediaArrayList = data.getParcelableArrayListExtra(PHOTO_LIST);
                    Log.d(TAG, "number of selected images = " + mediaArrayList.size());
                    for(int i = 0; i<mediaArrayList.size(); i++)
                        selectionResult.add(mediaArrayList.get(i).path);
                }
                JSONObject jsonObject = new JSONObject();
                //JSONArray jsonArray =  new JSONArray(IMAGEPTHLIST);
                JSONArray jsonArray =  new JSONArray(selectionResult);
                try {
                    if(null!= AppConfig.getUser_id())
                    {
                        jsonObject.put(AppConfig.KEY_USER_ID, AppConfig.getUser_id());
                        jsonObject.put(AppConfig.KEY_IMAGEPATH, jsonArray);


                        jsonObject.put(AppConfig.KEY_GROUP_ID, "1");
                        VolleyRequest volleyRequest = new VolleyRequest(getContext());
                        volleyRequest.signedInHome_Profile_UploadPhotos(jsonObject);
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
