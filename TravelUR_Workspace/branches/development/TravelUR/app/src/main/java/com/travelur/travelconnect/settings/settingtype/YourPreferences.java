package com.travelur.travelconnect.settings.settingtype;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.travelur.R;
import com.travelur.general.BaseFragment;
import com.travelur.travelconnect.settings.settingtype.questionlisttype.QuestionListCheckBox;
import com.travelur.utility.AppConfig;

/**
 * @author by Abhijit.
 */

public class YourPreferences extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener{

    private ImageView arrow_left, arrow_right;
    public static YourPreferences newInstance() {
        YourPreferences fragment = new YourPreferences();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.your_prefernces, null, false);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.setClickable(true);
        arrow_left = AppConfig.arrow_left=(ImageView) view.findViewById(R.id.arrow_left);
        arrow_right = AppConfig.arrow_right= (ImageView) view.findViewById(R.id.arrow_right);

        close = (ImageView) view.findViewById(R.id.close);
        close.setBackgroundResource(R.drawable.ic_action_arrowleft);
        title = (TextView) view.findViewById(R.id.action_bar_title);
        custom_toolbar_menu_item = (TextView) view.findViewById(R.id.custom_toolbar_menu_item);
        custom_toolbar_menu_item.setText("    ");
        title.setText("Your Preferences ");

        selectedFragment = QuestionListCheckBox.newInstance();
        intitialiseFragment_LeftToRightTransition_questionList(selectedFragment);

        close.setOnClickListener(this);
        custom_toolbar_menu_item.setOnClickListener(this);
    }
  /*  private void refreshContent() {
        swipeRefreshLayout.setRefreshing(true);
        jsonObject = new JSONObject();
        try {
            if(null!= AppConfig.getUser_id())
            {
                jsonObject.put("user_id", AppConfig.getUser_id());
                VolleyRequest volleyRequest = new VolleyRequest(getActivity().getApplicationContext());
                volleyRequest.settings_Your_Prefernces(jsonObject, adapter, swipeRefreshLayout);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        swipeRefreshLayout.setRefreshing(false);
    }*/



    @Override
    public void onRefresh() {

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
