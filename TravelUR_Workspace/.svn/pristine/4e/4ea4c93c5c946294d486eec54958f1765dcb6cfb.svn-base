package com.travelur.travelconnect.friends;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
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
import com.travelur.general.BaseFragment;
import com.travelur.travelconnect.friends.adapters.AddFriendsListAdapter;
import com.travelur.utility.AppConfig;

import java.util.ArrayList;
import java.util.List;

/*
 * @author by Abhijit .
 */

public class Friends extends BaseFragment{

    public static Friends newInstance() {
        Friends fragment = new Friends();
        return fragment;
    }

    TextView next,invite_friends;
    ListView frinds_list;
    AddFriendsListAdapter adapter;
    SearchView search;
    TextView layout_Title;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.friends, null, false);
        setHasOptionsMenu(true);//to activate search option in Menu
        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "fontawesome-webfont.ttf");

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        search = (SearchView) getActivity().findViewById(R.id.search);
        layout_Title = (TextView) getActivity().findViewById(R.id.action_bar_title_1);
        layout_Title.setText(R.string.text_add_friend);
        search.setQueryHint("Search Friends...");
        search.clearFocus();
        frinds_list = (ListView) view.findViewById(R.id.list);
        ViewCompat.setNestedScrollingEnabled(frinds_list,true);
        adapter = new AddFriendsListAdapter(getActivity(), AppConfig.yourFriendsSuggestionListItems);
        frinds_list.setAdapter(adapter);
        frinds_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
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

    /*@Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main2, menu);

        final MenuItem item = menu.findItem(R.id.action_search);

    }*/

}
