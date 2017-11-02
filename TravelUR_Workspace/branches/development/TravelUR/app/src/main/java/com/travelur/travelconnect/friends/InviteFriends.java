package com.travelur.travelconnect.friends;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.travelur.R;
import com.travelur.general.BaseFragment;
import com.travelur.utility.AppConfig;

import static com.travelur.utility.AppConfig.yourFriendsListItems;

/**
 * @author by Abhijit.
 */

public class InviteFriends extends BaseFragment implements View.OnClickListener{

    private Button invite_friends;
    private TextView pending_requests_count;
    public static InviteFriends newInstance() {
        InviteFriends fragment = new InviteFriends();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.add_friends, null, false);
        setHasOptionsMenu(true);//to activate search option in Menu
        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "fontawesome-webfont.ttf");

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.setClickable(true);
        invite_friends = (Button)view.findViewById(R.id.invite_friends);
        pending_requests_count = (TextView)view.findViewById(R.id.pending_requests_count);
        if(!yourFriendsListItems.isEmpty())
        pending_requests_count.setText(yourFriendsListItems.get(0).getPending_requests());
        invite_friends.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.invite_friends:
                selectedFragmentHeader = InviteFriendsPopUp.newInstance();
                intitialiseFragment_BottomToTopTransition(selectedFragmentHeader);
                break;
        }
    }
}
