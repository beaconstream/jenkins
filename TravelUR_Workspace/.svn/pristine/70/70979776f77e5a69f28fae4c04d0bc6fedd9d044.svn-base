package com.travelur.travelconnect.flights;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.travelur.R;

/*
 * @author by Abhijit .
 */

public class FlightList extends AppCompatActivity {
    ListView flight_list;
    FlightListAdapter adapter;
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flight_list);
        flight_list= (ListView) findViewById(R.id.flight_list);
        adapter=new FlightListAdapter(this);
        flight_list.setAdapter(adapter);
    }
}
