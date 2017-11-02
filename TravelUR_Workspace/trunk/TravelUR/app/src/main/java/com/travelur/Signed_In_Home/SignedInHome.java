package com.travelur.Signed_In_Home;

import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.travelur.R;

public class SignedInHome extends Fragment {

    public static SignedInHome newInstance() {
        SignedInHome fragment = new SignedInHome();
        return fragment;
    }
    TextView camera,write_post,like,comment,like_filled,comment_filled,share;
    ImageView profile_photo;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.signed_in_home, null, false);
        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "fontawesome-webfont.ttf");
        camera= (TextView) v.findViewById(R.id.camera);
        write_post= (TextView) v.findViewById(R.id.write_post);
        like= (TextView) v.findViewById(R.id.like);
        comment= (TextView) v.findViewById(R.id.comment);
        like_filled= (TextView) v.findViewById(R.id.like_filled);
        comment_filled= (TextView) v.findViewById(R.id.comment_filled);
        share= (TextView) v.findViewById(R.id.share);
        profile_photo= (ImageView) v.findViewById(R.id.profile_pic);
        /*profile_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), Friends.class);
                startActivity(intent);
            }
        });*/
        camera.setTypeface(font);
        like.setTypeface(font);
        comment_filled.setTypeface(font);
        like_filled.setTypeface(font);
        comment.setTypeface(font);
        share.setTypeface(font);
        return v;
    }

}
