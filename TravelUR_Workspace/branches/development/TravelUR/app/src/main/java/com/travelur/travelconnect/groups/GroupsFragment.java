package com.travelur.travelconnect.groups;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.travelur.R;
import com.travelur.general.BaseFragment;

/**
 * @author by Abhijit.
 */

public class GroupsFragment extends BaseFragment implements View.OnClickListener {

    private Button button_create_group;

    public static GroupsFragment newInstance() {
        GroupsFragment fragment = new GroupsFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.groups, null, false);
        setHasOptionsMenu(true);//to activate search option in Menu
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        button_create_group = (Button) view.findViewById(R.id.button_create_group);
        button_create_group.setOnClickListener(this);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_create_group:
                selectedFragment = CreateGroup.newInstance();
                intitialiseFragment_LeftToRightTransition(selectedFragment);
                break;
        }
    }
}
