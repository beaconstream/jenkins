package com.travelur.travelconnect.rewards.rewardstype.rewardsmoredetailtype;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.travelur.R;
import com.travelur.general.BaseFragment;

/**
 * @author by Abhijit.
 */

public class RewardsMoreDetailsCouplesGatewayInternational extends BaseFragment implements View.OnClickListener {
    private ImageView close;
    private TextView action_bar_title;

    public static RewardsMoreDetailsCouplesGatewayInternational newInstance() {
        RewardsMoreDetailsCouplesGatewayInternational fragment = new RewardsMoreDetailsCouplesGatewayInternational();
        return fragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.rewards_more_details_couplesgateway_international, null, false);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.setClickable(true);
        close = (ImageView) view.findViewById(R.id.close);
        action_bar_title = (TextView) view.findViewById(R.id.action_bar_title);
        action_bar_title.setText("More Details");
        close.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.close:
                if (getFragmentManager().getBackStackEntryCount() > 0) {
                    getFragmentManager().popBackStack();
                }
                break;
        }
    }
}
