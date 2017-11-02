package com.travelur.travelconnect.signedinhome;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.travelur.R;
import com.travelur.general.BaseFragment;
import com.travelur.travelconnect.home.SignedInHome;
import com.travelur.travelconnect.signedinhome.navigationtype.CancellationFragment;
import com.travelur.travelconnect.signedinhome.navigationtype.PrivacyFragment;
import com.travelur.travelconnect.signedinhome.navigationtype.TermsFragment;
import com.travelur.utility.GlideCircleTransformation;

/*
 * @author by Abhijit.
 */

public class NavigationDrawerFragment extends BaseFragment implements View.OnClickListener{
    ImageView profile_pic, close, profile_icon,trip_icon,settings_icon,reward_icon,flight_icon,hotel_icon,bus_icon,vacation_icon;
    Button sign_out;
    DrawerLayout mDrawerLayout;
    View containerView;
    DrawerListener listener;
    ActionBarDrawerToggle mDrawerToogle;
    LinearLayout profile_layout,setting_layout,trip_layout,reward_layout,flight_layout,hotel_layout,bus_layout,vacation_layout;
    TextView termsOfUse, help, cancellation;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.navigation_drawer, null, false);
        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "fontawesome-webfont.ttf");
        initialise(v,font);
        return v;
    }
    public void initialise(View v,Typeface font){
        profile_pic = (ImageView) v.findViewById(R.id.navigation_profile_pic);
        close = (ImageView) v.findViewById(R.id.close);
        profile_icon= (ImageView) v.findViewById(R.id.profile_icon);

        trip_icon= (ImageView) v.findViewById(R.id.trip_icon);
        settings_icon= (ImageView) v.findViewById(R.id.settings);
        reward_icon= (ImageView) v.findViewById(R.id.reward_icon);
        flight_icon= (ImageView) v.findViewById(R.id.flight_icon);
        hotel_icon= (ImageView) v.findViewById(R.id.hotel_icon);
        bus_icon= (ImageView) v.findViewById(R.id.bus_icon);
        vacation_icon= (ImageView) v.findViewById(R.id.vacation_icon);
        profile_layout= (LinearLayout) v.findViewById(R.id.profile_layout);
        setting_layout= (LinearLayout) v.findViewById(R.id.settings_layout);
        trip_layout= (LinearLayout) v.findViewById(R.id.trip_layout);
        reward_layout= (LinearLayout) v.findViewById(R.id.rewar_layout);
        flight_layout= (LinearLayout) v.findViewById(R.id.flight_layout);
        hotel_layout= (LinearLayout) v.findViewById(R.id.hotel_layout);
        bus_layout= (LinearLayout) v.findViewById(R.id.bus_layout);
        vacation_layout= (LinearLayout) v.findViewById(R.id.vacation_layout);
        termsOfUse= (TextView) v.findViewById(R.id.terms_of_use);
        help= (TextView) v.findViewById(R.id.help);
        cancellation= (TextView) v.findViewById(R.id.cancellation);

        profile_layout.setOnClickListener(this);
        close.setOnClickListener(this);
        setting_layout.setOnClickListener(this);
        trip_layout.setOnClickListener(this);
        reward_layout.setOnClickListener(this);
        flight_layout.setOnClickListener(this);
        hotel_layout.setOnClickListener(this);
        bus_layout.setOnClickListener(this);
        vacation_layout.setOnClickListener(this);
        termsOfUse.setOnClickListener(this);
        help.setOnClickListener(this);
        cancellation.setOnClickListener(this);

//    trip_layout.setOnClickListener(this);
//    reward_layout.setOnClickListener(this);
//    flight_layout.setOnClickListener(this);
//    hotel_layout.setOnClickListener(this);
//    bus_layout.setOnClickListener(this);
//    vacation_layout.setOnClickListener(this);
    }
    private void setTranslucentStatusFlag(boolean on) {
        if (Build.VERSION.SDK_INT >= 19) {
            Window win = getActivity().getWindow();
            WindowManager.LayoutParams winParams = win.getAttributes();
            final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            if (on) {
                winParams.flags |= bits;
            } else {
                winParams.flags &= ~bits;
            }
            win.setAttributes(winParams);
        }
    }
    public void setUp(int fragmentId, DrawerLayout drawerLayout,
                      final Toolbar toolbar) {
        containerView = getActivity().findViewById(fragmentId);
        containerView.setFitsSystemWindows(true);
        mDrawerLayout = drawerLayout;
        mDrawerToogle = new ActionBarDrawerToggle(getActivity(), drawerLayout,
                toolbar, R.string.open, R.string.close_) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
                    //enable translucent statusbar via flags
                    setTranslucentStatusFlag(true);

                }
                if (Build.VERSION.SDK_INT >= 19) {
                    getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
                }
                if (Build.VERSION.SDK_INT >= 21) {
                    //we don't need the translucent flag this is handled by the theme
                    //set the statusbarcolor transparent to remove the black shadow
                    setTranslucentStatusFlag(false);
                }

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                // getActivity().invalidateOptionsMenu();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                }
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                toolbar.setAlpha(1 - slideOffset / 2);
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToogle);

        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToogle.syncState();
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.profile_layout:
                break;
            case R.id.trip_layout:
                break;
            case R.id.rewar_layout:
                break;
            case R.id.flight_layout:
                break;
            case R.id.hotel_layout:
                break;
            case R.id.bus_layout:
                break;
            case R.id.vacation_layout:
                break;



        }
        listener.onDrawerItemClick(v.getId());
    }

    public interface DrawerListener {
        public void onDrawerItemClick(int id);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener= (DrawerListener) context;
    }
}
