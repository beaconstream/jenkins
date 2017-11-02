package com.travelur.travelconnect.friends;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
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
import com.travelur.backend.VolleyRequest;
import com.travelur.general.BaseFragment;
import com.travelur.travelconnect.friends.adapters.YourFriendsListAdapter;
import com.travelur.utility.AppConfig;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author by Abhijit.
 */

public class YourFriends extends BaseFragment {

    public static YourFriends newInstance() {
        YourFriends fragment = new YourFriends();
        return fragment;
    }

    TextView next,invite_friends;
    ListView frinds_list;
    YourFriendsListAdapter adapter;
    SearchView search;
    TextView layout_Title;

    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    private JSONObject jsonObject;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.your_friends, null, false);
        setHasOptionsMenu(true);//to activate search option in Menu
        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "fontawesome-webfont.ttf");

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        search = (SearchView) getActivity().findViewById(R.id.search);
        layout_Title = (TextView) getActivity().findViewById(R.id.action_bar_title_1);
        layout_Title.setText(R.string.text_your_friends);
        search.setQueryHint("Search");

        frinds_list = (ListView) view.findViewById(R.id.list);
        ViewCompat.setNestedScrollingEnabled(frinds_list,true);
        adapter = new YourFriendsListAdapter(getContext(),AppConfig.yourFriendsListItems);
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
        refreshContent();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main2, menu);

        final MenuItem item = menu.findItem(R.id.action_search);
        //setMenuTextColor(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /*return super.onOptionsItemSelected(item);*/
        switch (item.getItemId()){
            case R.id.action_add:
                selectedFragmentHeader = InviteFriends.newInstance();
                intitialiseFragment_LeftToRightTransition_addFriendsHeader(selectedFragmentHeader);

                selectedFragmentList = Friends.newInstance();
                intitialiseFragment_LeftToRightTransition_addFriendsList(selectedFragmentList);
                break;
        }
        return true;
    }

    /* private void setMenuTextColor(Menu menu, int menuResource, int menuTextResource) {
            MenuItem item = menu.findItem(menuResource);
            SpannableString s = new SpannableString(getString(menuTextResource));
            s.setSpan(new ForegroundColorSpan(Color.CYAN), 0, s.length(), 0);
            item.setTitle(s);
        }*/
   private void refreshContent() {
       //swipeRefreshLayout.setRefreshing(true);
       jsonObject = new JSONObject();
       try {
           if(null!=AppConfig.getUser_id())
           {
               jsonObject.put("user_id", AppConfig.getUser_id());
               VolleyRequest volleyRequest = new VolleyRequest(getContext());
               volleyRequest.signedInHome_Profile_Friends(jsonObject, adapter, AppConfig.friendsSuggestionAdapter);
           }
       } catch (JSONException e) {
           e.printStackTrace();
       }
       //swipeRefreshLayout.setRefreshing(false);
   }
}
