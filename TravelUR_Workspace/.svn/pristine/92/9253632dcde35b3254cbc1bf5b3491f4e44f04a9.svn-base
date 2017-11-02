package com.travelur.travelconnect.friends;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.travelur.R;
import com.travelur.backend.VolleyRequest;
import com.travelur.general.BaseFragment;
import com.travelur.travelconnect.friends.adapters.YourFriendsListAdapter;
import com.travelur.travelconnect.friends.adapters.YourFriendsSuggestionListAdapter;
import com.travelur.utility.AppConfig;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author by Abhijit.
 */

public class YourFriendsSuggestion extends BaseFragment{

    public static YourFriendsSuggestion newInstance() {
        YourFriendsSuggestion fragment = new YourFriendsSuggestion();
        return fragment;
    }

    TextView next,invite_friends, pending_requests_count;
    ListView frinds_list;
    SearchView search;
    TextView layout_Title, friends_count;
    Button pending_requests;

    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;
    private JSONObject jsonObject;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.friends_suggestion_toolbar, null, false);
        setHasOptionsMenu(true);//to activate search option in Menu
        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "fontawesome-webfont.ttf");

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        search = (SearchView) getActivity().findViewById(R.id.search);
        /*layout_Title = (TextView) getActivity().findViewById(R.id.action_bar_title_1);
        layout_Title.setText(R.string.text_your_friends);*/
        friends_count = (TextView)view.findViewById(R.id.friends_count);
        pending_requests_count = (TextView)view.findViewById(R.id.pending_requests_count);
        AppConfig.friends_count = friends_count;
        AppConfig.pending_requests_count = pending_requests_count;
        pending_requests= (Button)view.findViewById(R.id.pending_requests);
        search.setQueryHint("Search");

        // Calling the RecyclerView
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);

        // The number of Columns
        mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new YourFriendsSuggestionListAdapter(getContext(), AppConfig.yourFriendsSuggestionListItems);
        AppConfig.friendsSuggestionAdapter = mAdapter;
        mRecyclerView.setAdapter(mAdapter);
    }

}
