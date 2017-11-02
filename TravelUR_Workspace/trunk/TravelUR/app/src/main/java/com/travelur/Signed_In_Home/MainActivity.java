package com.travelur.Signed_In_Home;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.appinvite.AppInvite;
import com.google.android.gms.appinvite.AppInviteInvitationResult;
import com.google.android.gms.appinvite.AppInviteReferral;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.travelur.Alerts.Alert;
import com.travelur.App_Loading.StartupScreen;
import com.travelur.Friendss.Friends;
import com.travelur.Messagess.Messages;
import com.travelur.R;

public class MainActivity extends AppCompatActivity implements NavigationDrawerFragment.DrawerListener {
    NavigationDrawerFragment navigationDrawerFragment;
    TextView layout_Title;
    Toolbar toolbar;
    FragmentManager fragmentManaager;
    FragmentTransaction trans;
    Fragment newFragment;
    DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private SearchView searchView;

    Fragment selectedFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout_Title = (TextView) findViewById(R.id.action_bar_title_1);
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        /*getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(false);*/

        fragmentManaager=getSupportFragmentManager();
        trans = fragmentManaager.beginTransaction();
        drawerLayout=(DrawerLayout) findViewById(R.id.drawer_layout);
        intitialiseFirstFragment();
        navigationDrawerFragment=(NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        navigationDrawerFragment.setUp(R.id.fragment_navigation_drawer,drawerLayout,toolbar);

        toolbar.post(new Runnable() {
            @Override
            public void run() {
                Drawable d = ResourcesCompat.getDrawable(getResources(), R.drawable.profl_pic, null);
                toolbar.setNavigationIcon(d);
            }
        });

        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_item1:
                                selectedFragment = SignedInHome.newInstance();
                                //searchView.onActionViewExpanded();
                                layout_Title.setVisibility(View.INVISIBLE);
                                break;
                            case R.id.action_item2:
                                selectedFragment = Friends.newInstance();
                                layout_Title.setVisibility(View.VISIBLE);
                                layout_Title.setText(R.string.Friends_Title);
                                break;
                            case R.id.action_item3:
                                selectedFragment = Messages.newInstance();
                                layout_Title.setVisibility(View.VISIBLE);
                                layout_Title.setText(R.string.Messages_Title);
                                break;
                            case R.id.action_item4:
                                selectedFragment = Alert.newInstance();
                                layout_Title.setVisibility(View.INVISIBLE);
                                break;
                            case R.id.action_item5:
                                selectedFragment = Alert.newInstance();
                                break;
                            /*case R.id.action_item6:
                                selectedFragment = Alert.newInstance();
                                break;*/
                        }
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame, selectedFragment);
                        transaction.commit();
                        return true;
                    }
                });

        //Manually displaying the first fragment - one time only
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, SignedInHome.newInstance());
        transaction.commit();

        //Used to select an item programmatically
        //bottomNavigationView.getMenu().getItem(2).setChecked(true);

    }
    public void intitialiseFirstFragment(){

        newFragment = new SignedInHome();
        trans.replace(R.id.frame, newFragment, newFragment.toString());
        trans.commit();
    }

    @Override
    public void onDrawerItemClick(int id) {
        switch (id){
            case R.id.profile_layout:
                newFragment=new ProfileFragment();
                break;

        }
        fragmentManaager=getSupportFragmentManager();
        trans = fragmentManaager.beginTransaction();
        trans.replace(R.id.frame, newFragment, newFragment.toString());
        trans.commit();
        drawerLayout.closeDrawer(Gravity.LEFT);
    }

    /* @Override
     public void onPostCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
         super.onPostCreate(savedInstanceState, persistentState);
         mDrawerToggle.syncState();
     }*/

    /*@Override
    public boolean onCreateOptionsMenu( Menu menu) {
        getMenuInflater().inflate( R.menu.menu_main, menu);

        final MenuItem myActionMenuItem = menu.findItem( R.id.action_search);
        searchView = (SearchView) myActionMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                if( ! searchView.isIconified()) {
                    searchView.setIconified(true);
                }
                myActionMenuItem.collapseActionView();
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                // UserFeedback.show( "SearchOnQueryTextChanged: " + s);
                return false;
            }
        });
        return true;
    }*/

}
