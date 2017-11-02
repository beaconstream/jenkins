package com.travelur.Alerts;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.travelur.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Priyanka on 25-04-2017.
 */

public class Alert extends Fragment {

    SearchView search;

    List<String> arrayList= new ArrayList<>();

    public static Alert newInstance() {
        Alert fragment = new Alert();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        arrayList.add("Abhijit Srivastava");
        arrayList.add("Amit Srivastava");
        arrayList.add("Harshit Chawla");
        arrayList.add("Anoop Kumar");
        arrayList.add("Prashant Kumar");
        arrayList.add("Rahul Vijay");
        arrayList.add("Arindam Das");
        arrayList.add("Vijay Kumar");
        arrayList.add("Francis Jues");
        arrayList.add("Subham Kumar");
        arrayList.add("Ankit Kumar");
        arrayList.add("Arindam Das");
    }

    AlertListAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.alerts, null, false);
        ListView listView= (ListView) v.findViewById(R.id.alert_list);
        search = (SearchView) v.findViewById(R.id.search);
        adapter=new AlertListAdapter(getActivity(), arrayList);
        listView.setAdapter(adapter);
        search.setVisibility(View.GONE);
        return v;
    }


}
