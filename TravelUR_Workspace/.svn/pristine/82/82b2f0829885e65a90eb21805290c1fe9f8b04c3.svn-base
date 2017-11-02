package com.travelur.travelconnect.settings.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

import com.travelur.R;
import com.travelur.general.BaseFragment;
import com.travelur.travelconnect.settings.models.questionlisttypedatamodel.QuestionListCheckBoxDataModelOptions;
import com.travelur.travelconnect.settings.models.questionlisttypedatamodel.QuestionListRadioButtonDataModelOptions;
import com.travelur.utility.AppConfig;

import java.util.ArrayList;

/**
 * @author by Abhijit.
 */

public class QuestionListRadioButtonAdapter extends ArrayAdapter {

    private ArrayList<QuestionListRadioButtonDataModelOptions> dataSet;
    public ArrayList<Integer> selectedIds = new ArrayList<Integer>();
    Context mContext;

    public void toggleSelected(Integer position)
    {
        if(selectedIds.contains(position))
        {
            selectedIds.remove(position);
            AppConfig.answer_list.remove(dataSet.get(position).getOptions());
        }
        else
        {
            if(selectedIds.size()>=1)
                selectedIds.clear();
            selectedIds.add(position);
            if(AppConfig.answer_list.size()>=1)
                AppConfig.answer_list.clear();

            AppConfig.answer_list.add(dataSet.get(position).getOptions());
        }
    }
    public void toggleSelectedClean(){
        if(!selectedIds.isEmpty()){
            selectedIds.clear();
        }
    }

    private static class ViewHolder {
        TextView txtName, question;
        RadioButton radioButton;
    }

    public QuestionListRadioButtonAdapter(ArrayList data, Context context) {
        super(context, R.layout.questionlist_radiobutton_item, data);
        this.dataSet = data;
        this.mContext = context;

    }
    @Override
    public int getCount() {
        return dataSet.size();
    }

    @Override
    public QuestionListRadioButtonDataModelOptions getItem(int position) {
        return dataSet.get(position);
    }

    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        QuestionListRadioButtonAdapter.ViewHolder viewHolder;
        final View result;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.questionlist_radiobutton_item, parent, false);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.questions_radiobutton);
            viewHolder.radioButton = (RadioButton) convertView.findViewById(R.id.radioButton);

            result=convertView;
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (QuestionListRadioButtonAdapter.ViewHolder) convertView.getTag();
            result=convertView;
        }

        QuestionListRadioButtonDataModelOptions item = getItem(position);


        viewHolder.txtName.setText(item.getOptions());
       //viewHolder.radioButton.setChecked(item.checked);
        if (selectedIds.contains(position)) {
            convertView.setSelected(true);
            convertView.setPressed(true);
            viewHolder.radioButton.setChecked(true);
            //convertView.setBackgroundColor(Color.parseColor("#FF4AB3F0"));
            viewHolder.txtName.setTextColor(Color.parseColor("#FF4AB3F0"));
        }
        else
        {
            convertView.setSelected(false);
            convertView.setPressed(false);
            viewHolder.radioButton.setChecked(false);
            //convertView.setBackgroundColor(Color.parseColor("#FFFFFF"));
            viewHolder.txtName.setTextColor(Color.parseColor("#736F6E"));
        }


        return result;
    }
}
