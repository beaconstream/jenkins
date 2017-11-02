package com.travelur.travelconnect.vacationpackages.moredetails;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;
import com.travelur.R;
import com.travelur.general.BaseActivity;
import com.travelur.travelconnect.rewards.rewardstype.RewardsCouplesGatewayDomestic;
import com.travelur.travelconnect.rewards.rewardstype.RewardsCouplesGatewayInternational;
import com.travelur.travelconnect.rewards.rewardstype.RewardsEuropeanTour;
import com.travelur.travelconnect.rewards.rewardstype.RewardsKnowUrNeighbourGateway;
import com.travelur.travelconnect.rewards.rewardstype.RewardsLocalSplendors;
import com.travelur.travelconnect.rewards.rewardstype.RewardsWorldTour;
import com.travelur.travelconnect.settings.settingtype.EmailPassword;
import com.travelur.travelconnect.settings.settingtype.YourPreferences;
import com.travelur.travelconnect.vacationpackages.SendEnquiry;
import com.travelur.utility.AppConfig;

public class MoreDetailsActivity extends BaseActivity implements View.OnClickListener{

    ImageView imageView;
    TextView package_name, price;
    Button send_enquiry;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_details);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_action_arrowleft);
        toolbar.setTitle("More Details");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), FragmentPagerItems.with(this)
                //.add(R.string.all, RewardAll.class)
                .add(R.string.overview, MoreDetailsOverview.class)
                .add(R.string.detaile_itenary, MoreDetailsDetailedItenary.class)
                .add(R.string.gallery, MoreDetailsGallery.class)
                .add(R.string.termscondition, MoreDetailsTermsCondition.class)
                .create());

        imageView = (ImageView)findViewById(R.id.imageView);
        package_name = (TextView)findViewById(R.id.package_name) ;
        price = (TextView)findViewById(R.id.price) ;
        send_enquiry = (Button) findViewById(R.id.send_enquiry);
        send_enquiry.setOnClickListener(this);

        AppConfig.PACKAGE_IMAGE_VIEW = imageView;
        AppConfig.PACKAGE_NAME = package_name;
        AppConfig.PACKAGE_PRICE = price;

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter);

        SmartTabLayout viewPagerTab = (SmartTabLayout) findViewById(R.id.viewpagertab);
        viewPagerTab.setViewPager(viewPager);

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.send_enquiry:
                selectedFragment = SendEnquiry.newInstance();
                intitialiseFragment_BottomToTopTransition(selectedFragment);
                break;
        }
    }
}
