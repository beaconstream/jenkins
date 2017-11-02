package com.travelur.travelconnect.profile;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.travelur.R;
import com.travelur.backend.VolleyRequest;
import com.travelur.general.BaseActivity;
import com.travelur.travelconnect.friends.Friends;
import com.travelur.travelconnect.friends.YourFriendsActivity;
import com.travelur.travelconnect.groups.GroupsFragment;
import com.travelur.travelconnect.home.Comment;
import com.travelur.travelconnect.home.Post;
import com.travelur.travelconnect.home.adapters.CardAdapter;
import com.travelur.travelconnect.home.adapters.ProfileCardAdapter;
import com.travelur.travelconnect.photos.Photo;
import com.travelur.travelconnect.profile.models.Profile;
import com.travelur.travelconnect.rewards.Listners.RecyclerItemClickListener;
import com.travelur.travelconnect.settings.Setting;
import com.travelur.travelconnect.settings.settingtype.AccountSettings;
import com.travelur.travelconnect.settings.settingtype.YourPreferences;
import com.travelur.travelconnect.videos.Video;
import com.travelur.utility.AppConfig;
import com.travelur.utility.GlideCircleTransformation;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import static com.travelur.utility.AppConfig.home_List;

public class ProfileActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, SearchView.OnQueryTextListener, View.OnClickListener {

    private ImageView edit_profile_pic, posts_icon, photos_icon,videos_icon,groups_icon,friends_icon,trips_icon,warning_icon,camera_icon,like,comment,share,like_filled,comment_filled;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private JSONObject jsonObject;
    private Toolbar toolbar;
    private TextView emptyView;
    private Animation animationSlideUp;
    private ImageView edit_profile;
    private TextView profile_name, place, post_count, photos_count, videos_count, friends_count, groups_count;
    private ProgressBar profile_percentage;
    private LinearLayout your_posts, your_photos, your_videos, your_friends, your_groups;
    private Button updatButton,postButton;
    private AppBarLayout appbar_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        appbar_layout = (AppBarLayout)findViewById(R.id.appbar_layout);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_action_arrowleft);
        edit_profile_pic =AppConfig.edit_profile_pic= (ImageView) findViewById(R.id.edit_profile_pic);
        profile_name =AppConfig.profile_name= (TextView) findViewById(R.id.profile_name) ;
        place =AppConfig.place= (TextView) findViewById(R.id.place) ;
        post_count =AppConfig.post_count= (TextView) findViewById(R.id.post_count) ;
        photos_count =AppConfig.photos_count= (TextView) findViewById(R.id.photos_count) ;
        videos_count =AppConfig.videos_count= (TextView) findViewById(R.id.videos_count) ;
        friends_count =AppConfig.friends_count= (TextView) findViewById(R.id.friends_count) ;
        groups_count =AppConfig.groups_count= (TextView) findViewById(R.id.groups_count) ;
        profile_percentage =AppConfig.profile_percentage_progressbar= (ProgressBar) findViewById(R.id.profile_percentage) ;
        posts_icon = (ImageView) findViewById(R.id.posts_icon);
        photos_icon= (ImageView) findViewById(R.id.photos_icon);
        videos_icon= (ImageView) findViewById(R.id.videos_icon);
        groups_icon= (ImageView) findViewById(R.id.groups_icon);
        friends_icon= (ImageView) findViewById(R.id.friends_icon);
        camera_icon= (ImageView) findViewById(R.id.camera_icon);
        edit_profile= (ImageView) findViewById(R.id.edit_profile);

        updatButton = (Button)findViewById(R.id.update);
        postButton = (Button)findViewById(R.id.post);

        your_posts =  (LinearLayout) findViewById(R.id.your_posts);
        your_photos = (LinearLayout) findViewById(R.id.your_photos);
        your_videos = (LinearLayout) findViewById(R.id.your_videos);
        your_friends = (LinearLayout) findViewById(R.id.your_friends);
        your_groups = (LinearLayout) findViewById(R.id.your_groups);

        /*swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorAccent,R.color.Ash_Gray,R.color.colorPrimary);*/
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        emptyView = (TextView) findViewById(R.id.empty_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        //Finally initializing our adapter
        adapter = new ProfileCardAdapter(AppConfig.home_Profile_List, ProfileActivity.this);
        AppConfig.volleyRecyclerViewAdapter = adapter;
        //Adding adapter to recyclerview
        //recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        //swipeRefreshLayout.setOnRefreshListener(this);
        edit_profile.setOnClickListener(this);
        your_posts.setOnClickListener(this);
        your_photos.setOnClickListener(this);
        your_videos.setOnClickListener(this);
        your_friends.setOnClickListener(this);
        your_groups.setOnClickListener(this);

        updatButton.setOnClickListener(this);
        postButton.setOnClickListener(this);

        refreshContent();

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, final int position) {
                        // TODO Handle item click
                        comment_filled = (ImageView) view.findViewById(R.id.comment_filled);
                        like_filled= (ImageView) view.findViewById(R.id.like_filled);
                        share = (ImageView) view.findViewById(R.id.share);
                        comment_filled.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                switch(v.getId()){
                                    case R.id.comment:
                                       /* selectedFragment = RewardsMoreDetailsCouplesGatewayInternational.newInstance();
                                        intitialiseFragment_LeftToRightTransition(selectedFragment);*/
                                        break;
                                }
                            }
                        });
                        share.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                alertDialog(position);
                            }
                        });
                        like_filled.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                jsonObject = new JSONObject();
                                try {
                                    if(null!= AppConfig.getUser_id())
                                    {
                                        jsonObject.put("type", "1");
                                        jsonObject.put("user_id", home_List.get(position).getUser_id());
                                        jsonObject.put("id", home_List.get(position).getPost_id());
                                        VolleyRequest volleyRequest = new VolleyRequest(getApplicationContext());
                                        volleyRequest.signedInHome_Like(jsonObject, adapter);
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });

                        comment_filled.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                selectedFragment = Comment.newInstance(position);
                                intitialiseFragment_BottomToTopTransition(selectedFragment);
                            }
                        });
                    }
                })
        );
    }

    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        /*swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
                //Calling method to get data
                refreshContent();
            }
        });*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.profile_menu, menu);
        final MenuItem search = menu.findItem(R.id.action_search);
        final MenuItem settings = menu.findItem(R.id.action_settings);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(search);
        //searchView.setBackground(getResources().getDrawable(R.drawable.bg_white_rounded));
        searchView.setQueryHint("Search");
        //searchView.setIconified(false);
        //searchView.onActionViewExpanded();
        searchView.setOnQueryTextListener(this);
        MenuItemCompat.setOnActionExpandListener(search,
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

        /*menu.findItem(R.id.action_your_trips).setIcon(
                new IconDrawable(getActivity().getApplicationContext(), FontAwesomeIcons.fa_ticket)
                        .colorRes(R.color.White)
                        .actionBarSize());*/
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings :
            {
                selectedFragment = Setting.newInstance();
                intitialiseFragment(selectedFragment);
                return true;
            }
            case android.R.id.home:
                // do what ever you want here
                finish();
                return true;
            default:
                break;
        }
        return false;
    }

    @Override
    public void onRefresh() {
        //Calling method to get data
        swipeRefreshLayout.setRefreshing(true);
        refreshContent();
    }

    @Override
    public boolean onQueryTextChange(String newText) {
       /* final List<CountryModel> filteredModelList = filter(mCountryModel, newText);
        adapter.setFilter(filteredModelList);*/
        //adapter.getFilter().filter(newText);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    private void refreshContent() {
        //swipeRefreshLayout.setRefreshing(true);
        jsonObject = new JSONObject();
        try {
            if(null!= AppConfig.getUser_id())
            {
                jsonObject.put("user_id", AppConfig.getUser_id());
                VolleyRequest volleyRequest = new VolleyRequest(getApplicationContext());
                //volleyRequest.signedInHome_Profile_TravelConnectProfile(jsonObject, adapter, edit_profile_pic, profile_name, place, post_count,photos_count, videos_count, friends_count, groups_count, profile_percentage);
                volleyRequest.signedInHome_Profile_TravelConnectProfile();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //swipeRefreshLayout.setRefreshing(false);
    }

    private void checEmptyView() {
        if (AppConfig.home_List.isEmpty()) {
            recyclerView.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
        }
        else {
            recyclerView.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.edit_profile:
                selectedFragment = AccountSettings.newInstance();
                intitialiseFragment(selectedFragment);
                break;
            case R.id.your_posts:
                appbar_layout.setExpanded(false);
                break;
            case R.id.your_photos:
                selectedFragment = Photo.newInstance();
                intitialiseFragment_LeftToRightTransition(selectedFragment);
                break;
            case R.id.your_videos:
                selectedFragment = Video.newInstance();
                intitialiseFragment_LeftToRightTransition(selectedFragment);
                break;
            case R.id.your_friends:
                Intent intent = new Intent(this, YourFriendsActivity.class);
                startActivity(intent);
                break;
            case R.id.your_groups:
                selectedFragment = GroupsFragment.newInstance();
                intitialiseFragment_LeftToRightTransition(selectedFragment);
                break;
            case R.id.post:
                selectedFragment = Post.newInstance();
                intitialiseFragment_BottomToTopTransition(selectedFragment);
                break;
            case R.id.update:
                selectedFragment = YourPreferences.newInstance();
                intitialiseFragment_LeftToRightTransition(selectedFragment);
                break;
        }
    }
    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    public void alertDialog(final int position){
        // custom dialog
        final Dialog dialog = new Dialog(ProfileActivity.this);
        dialog.setContentView(R.layout.share);
        // dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setTitle("Title...");

        // set the custom dialog components - text, image and button
        TextView share_content = (TextView) dialog.findViewById(R.id.share_content);
        share_content.setText(R.string.share_travelconnect);


        TextView cancel = (TextView) dialog.findViewById(R.id.cancel);
        TextView ok = (TextView) dialog.findViewById(R.id.ok);
        // if button is clicked, close the custom dialog
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                jsonObject = new JSONObject();
                try {
                    if(null!= AppConfig.getUser_id())
                    {
                        jsonObject.put("user_id", AppConfig.getUser_id());
                        jsonObject.put("id", home_List.get(position).getPost_id());
                        VolleyRequest volleyRequest = new VolleyRequest(ProfileActivity.this);
                        AppConfig.volleyRecyclerViewAdapter = adapter;
                        volleyRequest.signedInHome_Share(jsonObject);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        dialog.show();
    }
}
