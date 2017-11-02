package com.travelur.travelconnect.settings;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.travelur.R;
import com.travelur.general.BaseFragment;
import com.travelur.travelconnect.settings.adapters.SettingListAdapter;
import com.travelur.travelconnect.settings.models.SettingDataModel;
import com.travelur.travelconnect.settings.settingtype.AccountSettings;
import com.travelur.travelconnect.settings.settingtype.EmailPassword;
import com.travelur.travelconnect.settings.settingtype.FrequentFlyers;
import com.travelur.travelconnect.settings.settingtype.PrivacySettings;
import com.travelur.travelconnect.settings.settingtype.YourPreferences;

import java.util.ArrayList;

/**
 * @author by Abhijit.
 */

public class Setting extends BaseFragment implements View.OnClickListener{

    public static Setting newInstance() {
        Setting fragment = new Setting();
        return fragment;
    }

    private ArrayList<SettingDataModel> dataModels;
    private ListView listView;
    private static SettingListAdapter listAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.settings, null, false);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.setClickable(true);
        close = (ImageView) view.findViewById(R.id.close);
        close.setBackgroundResource(R.drawable.ic_action_arrowleft);
        title = (TextView) view.findViewById(R.id.action_bar_title);
        title.setText("Settings");

        listView=(ListView) view.findViewById(R.id.setting_list);

        dataModels= new ArrayList<>();

        dataModels.add(new SettingDataModel("Account Settings"));
        dataModels.add(new SettingDataModel("Frequent Flyer Rewards"));
        dataModels.add(new SettingDataModel("Privacy Settings"));
        dataModels.add(new SettingDataModel("Your Preferences"));
        dataModels.add(new SettingDataModel("Email & Password"));

        listAdapter= new SettingListAdapter(dataModels,getContext());

        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //SettingDataModel dataModel= dataModels.get(position);
                switch (position)
                {
                    case 0:
                        selectedFragment = AccountSettings.newInstance();
                        intitialiseFragment_LeftToRightTransition(selectedFragment);
                        break;
                    case 1:
                        selectedFragment = FrequentFlyers.newInstance();
                        intitialiseFragment_LeftToRightTransition(selectedFragment);
                        break;
                    case 2:
                        selectedFragment = PrivacySettings.newInstance();
                        intitialiseFragment_LeftToRightTransition(selectedFragment);
                        break;
                    case 3:
                        selectedFragment = YourPreferences.newInstance();
                        intitialiseFragment_LeftToRightTransition(selectedFragment);
                        break;
                    case 4:
                        selectedFragment = EmailPassword.newInstance();
                        intitialiseFragment_LeftToRightTransition(selectedFragment);
                        break;

                }

            }
        });
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
