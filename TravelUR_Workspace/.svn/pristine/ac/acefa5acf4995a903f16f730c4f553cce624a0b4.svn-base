package com.travelur.travelconnect.settings.settingtype;

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

public class PrivacySettings extends BaseFragment implements View.OnClickListener {

    public static PrivacySettings newInstance() {
        PrivacySettings fragment = new PrivacySettings();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_privacy, null, false);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.setClickable(true);
        close = (ImageView) view.findViewById(R.id.close);
        close.setBackgroundResource(R.drawable.ic_action_arrowleft);
        title = (TextView) view.findViewById(R.id.action_bar_title);
        title.setText("Privacy Settings");

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
