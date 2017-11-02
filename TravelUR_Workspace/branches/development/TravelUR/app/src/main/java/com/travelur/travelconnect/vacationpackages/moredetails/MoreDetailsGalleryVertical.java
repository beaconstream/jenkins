package com.travelur.travelconnect.vacationpackages.moredetails;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.travelur.R;
import com.travelur.backend.VolleyRequest;
import com.travelur.general.BaseFragment;
import com.travelur.travelconnect.rewards.Listners.RecyclerItemClickListener;
import com.travelur.travelconnect.rewards.rewardstype.rewardsmoredetailtype.RewardsMoreDetailsCouplesGatewayDomestic;
import com.travelur.travelconnect.vacationpackages.adapters.PhotoGalleryAdapter;
import com.travelur.travelconnect.vacationpackages.adapters.PhotoGalleryAdapterVertical;
import com.travelur.travelconnect.vacationpackages.adapters.VacationPackageMoredetailsAdapter;
import com.travelur.travelconnect.vacationpackages.models.VacationPackageMoreDetailsDetailItenaryGalleryListItem;
import com.travelur.utility.AppConfig;

import org.json.JSONException;
import org.json.JSONObject;

import static com.travelur.utility.AppConfig.vacationpackages_moredetails_listitem;

/**
 * @author by Abhijit.
 */

public class MoreDetailsGalleryVertical extends BaseFragment implements View.OnClickListener {

    private JSONObject jsonObject = null;
    private LinearLayoutManager lLayout;
    private RecyclerView rView;
    private static int position1 = 0;

    public static MoreDetailsGalleryVertical newInstance(int position) {
        MoreDetailsGalleryVertical fragment = new MoreDetailsGalleryVertical();
        position1 = position;
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.vacation_packages_more_details_gallery_vertical_content_main, null, false);

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.setClickable(true);
        close = (ImageView) view.findViewById(R.id.close);
        close.setBackgroundResource(R.drawable.ic_action_arrowleft);
        title = (TextView) view.findViewById(R.id.action_bar_title);
        title.setText("Gallery");
        lLayout = new LinearLayoutManager(getContext());

        rView = (RecyclerView)view.findViewById(R.id.recycler_view);
        rView.setLayoutManager(lLayout);

        PhotoGalleryAdapterVertical rcAdapter = new PhotoGalleryAdapterVertical(getContext(), AppConfig.vacationpackages_moredetails_gallery_listitem);
        rView.setAdapter(rcAdapter);
        lLayout.scrollToPositionWithOffset(position1, 0);
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
            case R.id.custom_toolbar_menu_item:

                break;
        }
    }

}
