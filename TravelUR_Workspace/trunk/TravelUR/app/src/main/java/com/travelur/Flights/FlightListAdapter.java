package com.travelur.Flights;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.travelur.R;

/**
 * Created by Priyanka on 27-04-2017.
 */

public class FlightListAdapter extends BaseAdapter {
    Context context;
    public  FlightListAdapter(Context context){
        this.context=context;
    }
    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view= inflater.inflate(R.layout.flight_list_item,null);

        return view;
    }
}
