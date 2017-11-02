package com.travelur.travelconnect.profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.travelur.R;
import com.travelur.general.BaseFragment;
import com.travelur.travelconnect.messages.Messages;

/**
 * @author by Abhijit.
 */

public class EditProfile extends BaseFragment {
    public static EditProfile newInstance() {
        EditProfile fragment = new EditProfile();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.edit_profile, null, false);
        setHasOptionsMenu(true);//to activate search option in Menu
        layout_Title = (TextView) v.findViewById(R.id.action_bar_title);
        custom_toolbar_menu_item = (TextView) v.findViewById(R.id.custom_toolbar_menu_item);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        layout_Title.setText(R.string.Edit_Profile);
        layout_Title.setTypeface(font_Raleway_Light);
        custom_toolbar_menu_item.setText(R.string.Save);
        custom_toolbar_menu_item.setTypeface(font_Raleway_Light);
    }

}
