package com.travelur.travelconnect.videos;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.graphics.Palette;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.travelur.R;
import com.travelur.backend.VolleyRequest;
import com.travelur.general.BaseFragment;
import com.travelur.travelconnect.home.adapters.CardAdapter;
import com.travelur.travelconnect.home.models.Home;
import com.travelur.travelconnect.photos.adapters.DetailPhoto;
import com.travelur.travelconnect.photos.models.PhotoDataModel;
import com.travelur.travelconnect.photos.models.VideoDataModel;
import com.travelur.utility.AppConfig;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import static com.travelur.R.id.custom_toolbar_menu_item;

/**
 * @author by Abhijit.
 */

public class DetailVideo  extends BaseFragment implements View.OnClickListener{
    private MediaController mediaControls;
    private int videoPosition =0;
    private boolean isPlaying = false;
    private TextView delete;
    private JSONObject jsonObject;

    public static DetailVideo newInstance() {
        DetailVideo fragment = new DetailVideo();
        return fragment;
    }
    public static final String EXTRA_GALLERY_VIDEO = "VideoFragment.GALLERY_VIDEO";
    private VideoView uploaded_video;
    private ImageButton play_pause_button;
    private VideoDataModel spaceVideo;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.detail_video, null, false);

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.setClickable(true);
        delete = (TextView)view.findViewById(R.id.delete);
        close = (ImageView) view.findViewById(R.id.close);
        close.setBackgroundResource(R.drawable.ic_action_arrowleft);
        title = (TextView) view.findViewById(R.id.action_bar_title);
        title.setText("             ");
        custom_toolbar_menu_item = (TextView) view.findViewById(R.id.custom_toolbar_menu_item);
        custom_toolbar_menu_item.setText("Share");

        play_pause_button = (ImageButton)view.findViewById(R.id.play_pause_toggle_button);
        uploaded_video = (VideoView) view.findViewById(R.id.video);
        //PhotoDataModel spacePhoto = getActivity().getIntent().getParcelableExtra(EXTRA_GALLERY_PHOTO);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            spaceVideo = bundle.getParcelable(EXTRA_GALLERY_VIDEO);
            onSwitch_UploadVideo(uploaded_video, spaceVideo);
          /*  spacePhoto = bundle.getParcelable(EXTRA_GALLERY_PHOTO);
            Glide.with(this)
                    .load(spacePhoto.getUrl())
                    .asBitmap()
                    //.error(R.drawable.error_icon)
                    .placeholder(R.drawable.place_holder)
                    .listener(new RequestListener<String, Bitmap>() {

                        @Override
                        public boolean onException(Exception e, String model, Target<Bitmap> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Bitmap resource, String model, Target<Bitmap> target, boolean isFromMemoryCache, boolean isFirstResource) {

                            onPalette(Palette.from(resource).generate());
                            mImageView.setImageBitmap(resource);

                            return false;
                        }

                        public void onPalette(Palette palette) {
                            if (null != palette) {
                                ViewGroup parent = (ViewGroup) mImageView.getParent();
                                parent.setBackgroundColor(palette.getDarkVibrantColor(Color.GRAY));
                            }
                        }
                    })
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(mImageView);*/
            play_pause_button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (isPlaying) {
                        uploaded_video.pause();
                        play_pause_button.setBackgroundResource(R.drawable.ic_action_video_play);
                    }else{
                        uploaded_video.start();
                        play_pause_button.setBackgroundResource(R.drawable.ic_action_video_pause);
                    }
                    isPlaying = !isPlaying;
                }
            });
            close.setOnClickListener(this);
            delete.setOnClickListener(this);
            custom_toolbar_menu_item.setOnClickListener(this);
        }

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

                break;
            case R.id.delete:
                jsonObject = new JSONObject();
                try {
                    if(null!= AppConfig.getUser_id())
                    {
                        jsonObject.put("id", spaceVideo.getId());
                        VolleyRequest volleyRequest = new VolleyRequest(getContext());
                        volleyRequest.urvideos_delete(jsonObject, getFragmentManager());
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    public void onSwitch_UploadVideo(final VideoView uploaded_video, VideoDataModel list) {

        // create a progress bar while the video file is loading
        //progressDialog = new ProgressDialog(holder.itemView.getContext());
        //set the media controller buttons

        mediaControls = new MediaController(getContext());

        /*// set a title for the progress bar
        progressDialog.setTitle("Please wait. We are loading the video...");
        // set a message for the progress bar
        progressDialog.setMessage("Loading...");
        //set the progress bar not cancelable on users' touch
        progressDialog.setCancelable(true);

        progressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "DISMISS", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                progressDialog.dismiss();
            }
        });*/

        try {
            //set the media controller in the VideoView
            uploaded_video.setMediaController(mediaControls);

            //set the uri of the video to be played
            uploaded_video.setVideoURI(Uri.parse(list.getUrl()));

        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }

        //holder.uploaded_video.requestFocus();
        //we also set an setOnPreparedListener in order to know when the video file is ready for playback
        uploaded_video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            public void onPrepared(MediaPlayer mediaPlayer) {
                // close the progress bar and play the video
                //progressDialog.dismiss();
                //if we have a position on savedInstanceState, the video playback should start from here
                uploaded_video.seekTo(videoPosition);
                if (videoPosition == 0) {
                    //holder.uploaded_video.start();
                } else {
                    //if we come from a resumed activity, video playback will be paused
                    uploaded_video.pause();
                }
            }
        });

        uploaded_video.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                Log.d("video", "setOnErrorListener ");
                return true;
            }
        });
        uploaded_video.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent motionEvent) {
                if (uploaded_video.isPlaying()) {
                    uploaded_video.pause();
                    mediaControls.show(0);
                    videoPosition = uploaded_video.getCurrentPosition();
                    return false;
                } else {
                    //progressDialog.show();
                    mediaControls.hide();
                  uploaded_video.seekTo(videoPosition);
                    uploaded_video.start();
                    return false;
                }
            }
        });

        uploaded_video.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                //here
                uploaded_video.seekTo(1);
            }
        });


        //here
        if (videoPosition != 1) {
            uploaded_video.seekTo(videoPosition);
            //holder.uploaded_video.start();
        } else {
            //here
            uploaded_video.seekTo(1);
        }
    }
}
