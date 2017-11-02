package com.travelur.travelconnect.friends;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.travelur.R;
import com.travelur.general.BaseActivity;
import com.travelur.travelconnect.home.Post;

public class YourFriendsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_friends);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_action_arrowleft);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        selectedFragmentHeader = YourFriendsSuggestion.newInstance();
        intitialiseFragment_LeftToRightTransition_friendsHeader(selectedFragmentHeader);

        selectedFragmentList = YourFriends.newInstance();
        intitialiseFragment_LeftToRightTransition_yourFriendsList(selectedFragmentList);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
