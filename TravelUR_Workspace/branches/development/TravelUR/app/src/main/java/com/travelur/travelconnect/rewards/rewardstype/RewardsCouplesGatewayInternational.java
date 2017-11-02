package com.travelur.travelconnect.rewards.rewardstype;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.travelur.R;
import com.travelur.backend.VolleyRequest;
import com.travelur.general.BaseFragment;
import com.travelur.travelconnect.rewards.Listners.RecyclerItemClickListener;
import com.travelur.travelconnect.rewards.adapters.RewardsCouplesGatewayInternationalAdapter;
import com.travelur.travelconnect.rewards.rewardstype.rewardsmoredetailtype.RewardsMoreDetailsCouplesGatewayInternational;
import com.travelur.travelconnect.rewards.rewardstype.rewardsmoredetailtype.RewardsMoreDetailsWorldTour;
import com.travelur.utility.AppConfig;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author by Abhijit.
 */

public class RewardsCouplesGatewayInternational extends BaseFragment  implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private JSONObject jsonObject;
    private Toolbar toolbar;
    private TextView emptyView;
    private Button button_more_details;
    public static List<Integer> tour_image = new ArrayList<>();
    public static List<String> tour_name = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.rewards_couplesgateway_international_content_main, null, false);
        if(tour_image.isEmpty() && tour_name.isEmpty()) {
            tour_image.add(R.drawable.couples_gateway_international);
            tour_name.add("Couples Getaway(International)");
        }
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.setClickable(true);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorAccent,R.color.Ash_Gray,R.color.colorPrimary);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        emptyView = (TextView) view.findViewById(R.id.empty_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        //Finally initializing our adapter
        adapter = new RewardsCouplesGatewayInternationalAdapter(AppConfig.reward_couplesgateway_international_list, getContext());
        //Adding adapter to recyclerview
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
                //Calling method to get data
                refreshContent();
            }
        });
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        // TODO Handle item click
                        button_more_details = (Button) view.findViewById(R.id.button_more_details);
                        button_more_details.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                switch(v.getId()){
                                    case R.id.button_more_details:
                                        selectedFragment = RewardsMoreDetailsCouplesGatewayInternational.newInstance();
                                        intitialiseFragment_LeftToRightTransition(selectedFragment);
                                        break;
                                }
                            }
                        });

                    }
                })
        );

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){

        }
    }
    @Override
    public void onRefresh() {
        //Calling method to get data
        swipeRefreshLayout.setRefreshing(true);
        refreshContent();
    }

    private void refreshContent() {
        swipeRefreshLayout.setRefreshing(true);
        jsonObject = new JSONObject();
        try {
            if(null!=AppConfig.getUser_id())
            {
                jsonObject.put("user_id", AppConfig.getUser_id());
                VolleyRequest volleyRequest = new VolleyRequest(getContext());
                volleyRequest.rewards(jsonObject, adapter, swipeRefreshLayout, tour_image, tour_name, 2);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        swipeRefreshLayout.setRefreshing(false);
    }
}
