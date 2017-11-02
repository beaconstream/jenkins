package com.travelur.travelconnect.settings.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.travelur.R;
import com.travelur.travelconnect.settings.models.SettingDataModel;

import java.util.ArrayList;

/**
 * author by Abhijit.
 */

public class SettingListAdapter extends ArrayAdapter<SettingDataModel> implements View.OnClickListener{

    private ArrayList<SettingDataModel> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView txtType;
    }

    public SettingListAdapter(ArrayList<SettingDataModel> data, Context context) {
        super(context, R.layout.setting_list_item, data);
        this.dataSet = data;
        this.mContext=context;

    }

    @Override
    public void onClick(View v) {

        int position=(Integer) v.getTag();
        Object object= getItem(position);
        SettingDataModel dataModel=(SettingDataModel) object;

        switch (v.getId())
        {
            /*case R.id.item_info:
                Snackbar.make(v, "Release date " +dataModel.getFeature(), Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();
                break;*/
        }
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        SettingDataModel dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.setting_list_item, parent, false);
            viewHolder.txtType = (TextView) convertView.findViewById(R.id.type);


            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        /*Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;*/

        viewHolder.txtType.setText(dataModel.getTypes());
        // Return the completed view to render on screen
        return convertView;
    }
}
