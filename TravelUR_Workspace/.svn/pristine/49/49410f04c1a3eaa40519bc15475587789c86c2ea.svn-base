package com.travelur.Flights;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.travelur.R;

/**
 * Created by Priyanka on 28-04-2017.
 */

public class FlightFragment extends Fragment {
    Button find_fights;
    EditText flight_options;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.flight_search, null, false);
        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "fontawesome-webfont.ttf");
        find_fights= (Button) v.findViewById(R.id.find_flights);
        flight_options= (EditText) v.findViewById(R.id.options);

        return v;
    }
}
