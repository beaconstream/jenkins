package com.travelur.travelconnect.groups;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.travelur.R;
import com.travelur.general.BaseFragment;
import com.travelur.travelconnect.settings.settingtype.questionlisttype.QuestionListCheckBox;

/**
 * @author by Abhijit.
 */

public class CreateGroup extends BaseFragment implements View.OnClickListener {

    TextView add_local_info, hide;
    View add_local_info_layout;

    public static CreateGroup newInstance() {
        CreateGroup fragment = new CreateGroup();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.createagroup, null, false);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        add_local_info = (TextView) view.findViewById(R.id.add_local_info);
        add_local_info_layout = view.findViewById(R.id.add_local_info_layout);
        hide = (TextView) view.findViewById(R.id.hide);
        add_local_info.setOnClickListener(this);
        hide.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_local_info:
                add_local_info_layout.setVisibility(View.VISIBLE);
                break;
            case R.id.hide:
                add_local_info_layout.setVisibility(View.GONE);
                break;
        }
    }
}
