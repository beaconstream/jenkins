package com.travelur.Friendss;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import com.travelur.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Priyanka on 20-04-2017.
 */

public class Friends extends Fragment  implements SearchView.OnQueryTextListener{

    public static Friends newInstance() {
        Friends fragment = new Friends();
        return fragment;
    }

    TextView next,invite_friends;
    ListView frinds_list;
    FriendsListAdapter adapter;
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.friends, null, false);
        setHasOptionsMenu(true);//to activate search option in Menu
        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "fontawesome-webfont.ttf");
        next= (TextView) v.findViewById(R.id.next);
        invite_friends= (TextView) v.findViewById(R.id.invite_friend);
        frinds_list = (ListView) v.findViewById(R.id.list);
        adapter = new FriendsListAdapter(getActivity(),arrayList);
        frinds_list.setAdapter(adapter);
        frinds_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            }
        });
        next.setTypeface(font);
        invite_friends.setTypeface(font);
        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main2, menu);

        final MenuItem item = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(this);

        MenuItemCompat.setOnActionExpandListener(item,
                new MenuItemCompat.OnActionExpandListener() {
                    @Override
                    public boolean onMenuItemActionCollapse(MenuItem item) {
                        // Do something when collapsed
                        //adapter.setFilter(mCountryModel);
                        return true; // Return true to collapse action view
                    }

                    @Override
                    public boolean onMenuItemActionExpand(MenuItem item) {
                        // Do something when expanded
                        return true; // Return true to expand action view
                    }
                });
    }

    @Override
    public boolean onQueryTextChange(String newText) {
       /* final List<CountryModel> filteredModelList = filter(mCountryModel, newText);
        adapter.setFilter(filteredModelList);*/
        adapter.getFilter().filter(newText);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

}
