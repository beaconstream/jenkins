package com.travelur.Hotels;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.travelur.R;

/**
 * Created by Priyanka on 28-04-2017.
 */

public class HotelFragment extends Fragment {
    Button find_hotels;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.hotel_search, null, false);
        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "fontawesome-webfont.ttf");
        find_hotels= (Button) v.findViewById(R.id.find_hotels);

        return v;
    }
}
