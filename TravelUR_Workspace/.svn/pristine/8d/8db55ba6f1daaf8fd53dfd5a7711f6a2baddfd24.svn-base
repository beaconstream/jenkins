package com.travelur.Hotels;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.travelur.R;

/**
 * Created by Priyanka on 27-04-2017.
 */

public class HotelList extends AppCompatActivity {
    ListView hotel_list;
    HotelListAdapter adapter;
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hotel_list);
        hotel_list= (ListView) findViewById(R.id.hotel_list);
        adapter=new HotelListAdapter(this);
        hotel_list.setAdapter(adapter);
    }
}
