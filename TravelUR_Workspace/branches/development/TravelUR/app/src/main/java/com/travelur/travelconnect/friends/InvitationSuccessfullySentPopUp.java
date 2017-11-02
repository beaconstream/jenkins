package com.travelur.travelconnect.friends;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.travelur.R;
import com.travelur.general.BaseFragment;

/**
 * @author by Abhijit.
 */

public class InvitationSuccessfullySentPopUp extends BaseFragment implements View.OnClickListener{

    ImageView close;

    public static InvitationSuccessfullySentPopUp newInstance() {
        InvitationSuccessfullySentPopUp fragment = new InvitationSuccessfullySentPopUp();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.friends_invitation_successfullysent, null, false);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.setClickable(true);
        close = (ImageView)view.findViewById(R.id.close);
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
