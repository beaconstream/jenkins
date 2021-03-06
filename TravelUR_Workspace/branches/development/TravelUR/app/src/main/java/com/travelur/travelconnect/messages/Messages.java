package com.travelur.travelconnect.messages;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.fonts.FontAwesomeIcons;
import com.travelur.R;
import com.travelur.travelconnect.alerts.AlertListAdapter;

import java.util.ArrayList;
import java.util.List;

/*
 * @author by Abhijit .
 */

public class Messages extends Fragment {

    public static Messages newInstance() {
        Messages fragment = new Messages();
        return fragment;
    }

    AlertListAdapter adapter;

    SearchView search;

    List<String> arrayList= new ArrayList<>();

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

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.notification, null, false);
        setHasOptionsMenu(true);//to activate search option in Menu

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ListView listView= (ListView) view.findViewById(R.id.alert_list);
        search = (SearchView) view.findViewById(R.id.search);
        search.setQueryHint("Search messages...");
        adapter = new AlertListAdapter(getActivity(),arrayList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getActivity(),ChatBox.class);
                startActivity(intent);
            }
        });

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main3, menu);
        menu.findItem(R.id.action_messages).setIcon(
                new IconDrawable(getContext(), FontAwesomeIcons.fa_pencil_square_o)
                        .colorRes(R.color.White)
                        .actionBarSize());
    }
}
