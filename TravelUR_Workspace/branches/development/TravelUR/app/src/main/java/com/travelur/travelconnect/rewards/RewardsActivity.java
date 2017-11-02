package com.travelur.travelconnect.rewards;

import android.os.CountDownTimer;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;
import com.travelur.R;
import com.travelur.travelconnect.rewards.models.Rewards;
import com.travelur.travelconnect.rewards.rewardstype.RewardAll;
import com.travelur.travelconnect.rewards.rewardstype.RewardsCouplesGatewayDomestic;
import com.travelur.travelconnect.rewards.rewardstype.RewardsCouplesGatewayInternational;
import com.travelur.travelconnect.rewards.rewardstype.RewardsEuropeanTour;
import com.travelur.travelconnect.rewards.rewardstype.RewardsKnowUrNeighbourGateway;
import com.travelur.travelconnect.rewards.rewardstype.RewardsLocalSplendors;
import com.travelur.travelconnect.rewards.rewardstype.RewardsWorldTour;
import com.travelur.utility.AppConfig;

import java.util.concurrent.TimeUnit;

public class RewardsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rewards);
        final TextView days = AppConfig.DAYS = (TextView)findViewById(R.id.days);
        final TextView hours = AppConfig.HOURS = (TextView)findViewById(R.id.hours);
        final TextView mins = AppConfig.MINS = (TextView)findViewById(R.id.minutes);
        final TextView seconds = AppConfig.SECS = (TextView)findViewById(R.id.seconds);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_action_arrowleft);
        toolbar.setTitle("Rewards");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), FragmentPagerItems.with(this)
                //.add(R.string.all, RewardAll.class)
                .add(R.string.worltour, RewardsWorldTour.class)
                .add(R.string.europeantour, RewardsEuropeanTour.class)
                .add(R.string.couples_gateway_international, RewardsCouplesGatewayInternational.class)
                .add(R.string.couples_gateway_domestic, RewardsCouplesGatewayDomestic.class)
                .add(R.string.know_ur_neighbour, RewardsKnowUrNeighbourGateway.class)
                .add(R.string.local_splendors_gateway, RewardsLocalSplendors.class)
                .create());
        //startCountDown();

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter);

        SmartTabLayout viewPagerTab = (SmartTabLayout) findViewById(R.id.viewpagertab);
        viewPagerTab.setViewPager(viewPager);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    /*public void startCountDown(){
    *//*    creating object for all text views    *//*

        final TextView days = (TextView)findViewById(R.id.days);
        final TextView hours = (TextView)findViewById(R.id.hours);
        final TextView mins = (TextView)findViewById(R.id.minutes);
        final TextView seconds = (TextView)findViewById(R.id.seconds);
    *//*    172800000 milliseconds = 5days

    1000(1sec) is time interval to call onTick method

    millisUntilFinished is amount of until finished

    *//*

        long result = AppConfig.getElapsedTime();
        //new CountDownTimer(Long.parseLong("7516800000"), 1000){
        new CountDownTimer(result, 1000){

            @Override

            public void onTick(long millisUntilFinished) {
            *//*            converting the milliseconds into days, hours, minutes and seconds and displaying it in textviews             *//*

                days.setText(TimeUnit.HOURS.toDays(TimeUnit.MILLISECONDS.toHours(millisUntilFinished))+"");

                hours.setText((TimeUnit.MILLISECONDS.toHours(millisUntilFinished) - TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(millisUntilFinished)))+"");

                mins.setText((TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished)))+"");

                seconds.setText((TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)))+"");
            }

            @Override

            public void onFinish() {
            *//*            clearing all fields and displaying countdown finished message             *//*

                days.setText("00");
                hours.setText("00");
                mins.setText("00");
                seconds.setText("00");
            }
        }.start();
    }*/
}
