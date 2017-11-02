package com.travelur.utility;

import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.MediaController;
import android.widget.VideoView;

import com.travelur.R;

/**
 * Author by Abhijit.
 */

public class CustomVideoView extends VideoView {

    MediaController mMediaController = null;
    private String mVideoUrl = null;

    public CustomVideoView(Context context) {
        super(context);
    }

    public CustomVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Initialize all the basic elements here.
     */
    private void initialize() {
        mMediaController = new MediaController(getContext());
    }

    /**
     * Starts up the video.
     * Throws a null pointer exception if the URL is not set
     */
    public void startVideo() {

        initialize();
        // Return as is if there is no URL
        if (mVideoUrl == null) {
            throw new NullPointerException(getContext().getString(R.string.video_null_pointer_message));
        }

        mMediaController.setAnchorView(this);
        setMediaController(mMediaController);
        start();
    }

    public void setmVideoUrl(String mVideoUrl) {
        this.mVideoUrl = mVideoUrl;
        Uri vidUri = Uri.parse(mVideoUrl);
        setVideoURI(vidUri);
    }

    public String getmVideoUrl() {
        return mVideoUrl;
    }
}