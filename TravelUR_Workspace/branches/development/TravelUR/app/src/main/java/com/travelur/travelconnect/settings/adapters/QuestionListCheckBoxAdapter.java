package com.travelur.travelconnect.settings.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.travelur.R;
import com.travelur.travelconnect.settings.models.questionlisttypedatamodel.QuestionListCheckBoxDataModel;
import com.travelur.travelconnect.settings.models.questionlisttypedatamodel.QuestionListCheckBoxDataModelOptions;
import com.travelur.utility.AppConfig;

import java.util.ArrayList;

/**
 * @author by Abhijit.
 */

public class QuestionListCheckBoxAdapter extends ArrayAdapter {

    private ArrayList<QuestionListCheckBoxDataModelOptions> dataSet;
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
            selectedIds.add(position);
            AppConfig.answer_list.add(dataSet.get(position).getOptions());
        }
    }
    public void toggleSelectedClean(){
        if(!selectedIds.isEmpty()){
            selectedIds.clear();
        }
    }

    // View lookup cache
    private static class ViewHolder {
        TextView txtName, question;
        CheckBox checkBox;
    }

    public QuestionListCheckBoxAdapter(ArrayList data, Context context) {
        super(context, R.layout.questionlist_checkbox_item, data);
        this.dataSet = data;
        this.mContext = context;

    }
    @Override
    public int getCount() {
        return dataSet.size();
    }

    @Override
    public QuestionListCheckBoxDataModelOptions getItem(int position) {
        return dataSet.get(position);
    }


    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        ViewHolder viewHolder;
        final View result;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.questionlist_checkbox_item, parent, false);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.questions_checkboc);
            //viewHolder.checkBox = (CheckBox) convertView.findViewById(R.id.checkbox);

            result=convertView;
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        QuestionListCheckBoxDataModelOptions item = getItem(position);


        viewHolder.txtName.setText(item.getOptions());
        //viewHolder.checkBox.setChecked(item.checked);
        if (selectedIds.contains(position)) {
            convertView.setSelected(true);
            convertView.setPressed(true);
            convertView.setBackgroundColor(Color.parseColor("#FF4AB3F0"));
            viewHolder.txtName.setTextColor(Color.parseColor("#FFFFFF"));
        }
        else
        {
            convertView.setSelected(false);
            convertView.setPressed(false);
            convertView.setBackgroundColor(Color.parseColor("#FFFFFF"));
            viewHolder.txtName.setTextColor(Color.parseColor("#736F6E"));
            AppConfig.answer_list.remove("");
        }


        return result;
    }
}