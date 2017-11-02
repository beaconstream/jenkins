package com.travelur.Signed_In_Home;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.travelur.R;

/**
 * Created by Priyanka on 27-04-2017.
 */

public class ProfileFragment extends Fragment {
TextView photos_icon,videos_icon,groups_icon,friends_icon,trips_icon,warning_icon,camera_icon,like,comment,share,like_filled,comment_filled;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.profile, null, false);
        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "fontawesome-webfont.ttf");
        Typeface font1 = Typeface.createFromAsset(getActivity().getAssets(), "Close.ttf");
        photos_icon= (TextView) v.findViewById(R.id.photos_icon);
        videos_icon= (TextView) v.findViewById(R.id.videos_icon);
        groups_icon= (TextView) v.findViewById(R.id.groups_icon);
        friends_icon= (TextView) v.findViewById(R.id.friends_icon);
        trips_icon= (TextView) v.findViewById(R.id.trips_icon);
        warning_icon= (TextView) v.findViewById(R.id.warning_icon);
        camera_icon= (TextView) v.findViewById(R.id.camera_icon);
        like= (TextView) v.findViewById(R.id.like);
        comment= (TextView) v.findViewById(R.id.comment);
        like_filled= (TextView) v.findViewById(R.id.like_filled);
        comment_filled= (TextView) v.findViewById(R.id.comment_filled);
        share= (TextView) v.findViewById(R.id.share);
        photos_icon.setTypeface(font);
        videos_icon.setTypeface(font);
        groups_icon.setTypeface(font1);
        friends_icon.setTypeface(font);
        trips_icon.setTypeface(font);
        warning_icon.setTypeface(font);
        camera_icon.setTypeface(font);
        like.setTypeface(font);
        comment_filled.setTypeface(font);
        like_filled.setTypeface(font);
        comment.setTypeface(font);
        share.setTypeface(font);
        return v;
    }
}
