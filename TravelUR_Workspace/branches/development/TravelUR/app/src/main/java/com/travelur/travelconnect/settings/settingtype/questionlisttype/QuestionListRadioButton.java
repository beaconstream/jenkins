package com.travelur.travelconnect.settings.settingtype.questionlisttype;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.travelur.R;
import com.travelur.backend.VolleyRequest;
import com.travelur.general.BaseFragment;
import com.travelur.travelconnect.settings.adapters.QuestionListRadioButtonAdapter;
import com.travelur.travelconnect.settings.models.questionlisttypedatamodel.QuestionListRadioButtonDataModelOptions;
import com.travelur.utility.AppConfig;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import static com.travelur.utility.AppConfig.adapter_radiobutton;

/**
 * @author by Abhijit.
 */

public class QuestionListRadioButton extends BaseFragment implements View.OnClickListener {


    private ListView listView;
    private ImageView arrow_left, arrow_right;
    private TextView question, profile_percentage_value;
    private ProgressBar profile_percentage;
    private JSONObject jsonObject;
    private JSONObject jsonObject1;
    private int i=0;

    public static QuestionListRadioButton newInstance() {
        QuestionListRadioButton fragment = new QuestionListRadioButton();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.questionlist_checkbox, null, false);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        arrow_left = AppConfig.arrow_left;
        arrow_right = AppConfig.arrow_right;
        listView = (ListView) view.findViewById(R.id.listView);
        listView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
       /* arrow_left = (ImageView) view.findViewById(R.id.arrow_left);
        arrow_right = (ImageView) view.findViewById(R.id.arrow_right);*/
        question = (TextView) view.findViewById(R.id.question);
        profile_percentage = (ProgressBar) view.findViewById(R.id.profile_percentage);
        profile_percentage_value = (TextView) view.findViewById(R.id.profile_percentage_value);
        profile_percentage.setProgress(AppConfig.profile_percentage);
        profile_percentage_value.setText(AppConfig.profile_percentage+"/100");
        adapter_radiobutton = new QuestionListRadioButtonAdapter(AppConfig.question_List_radioButton_optionType, getContext());

        listView.setAdapter(adapter_radiobutton);

        jsonObject = new JSONObject();
        try {
            if(null!= AppConfig.getUser_id())
            {
                jsonObject.put("user_id", AppConfig.getUser_id());
                VolleyRequest volleyRequest = new VolleyRequest(getContext());
                volleyRequest.settings_Your_Prefernces(jsonObject, question);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {

                ((QuestionListRadioButtonAdapter)listView.getAdapter()).toggleSelected(new Integer(position));
                adapter_radiobutton.notifyDataSetChanged();

            }
        });

        arrow_left.setOnClickListener(this);
        arrow_right.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.arrow_left:
               /* if(i<0){
                    *//*if (getFragmentManager().getBackStackEntryCount() > 0)
                        getFragmentManager().popBackStack();*//*

                }*/
                if (!AppConfig.question_List_radioButton_optionType.isEmpty()) {
                    AppConfig.question_List_radioButton_optionType.clear();
                }
                jsonObject1 = new JSONObject();
                try {
                    if(null!= AppConfig.getUser_id() && !AppConfig.answer_list.isEmpty())
                    {
                        jsonObject1.put("user_id", AppConfig.getUser_id());
                        jsonObject1.put("answer", Arrays.toString(AppConfig.answer_list.toArray()).replace("[", "").replace("]", ""));
                        jsonObject1.put("qid",AppConfig.question_List_radioButton.get(i).qid);
                        VolleyRequest volleyRequest = new VolleyRequest(getContext());
                        volleyRequest.settings_Account_settings_YourPreference_QuestionSubmit(jsonObject1, profile_percentage, profile_percentage_value);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if(i==0){
                    AppConfig.adapter_radiobutton = null;
                    selectedFragment = QuestionListCheckBox.newInstance();
                    intitialiseFragment_LeftToRightTransition_questionList(selectedFragment);
                }
                else{
                    i--;

                    ((QuestionListRadioButtonAdapter)listView.getAdapter()).toggleSelectedClean();
                    if(!AppConfig.question_List_radioButton.isEmpty()) {
                        if (i >=0 && i<AppConfig.question_List_radioButton.size()) {
                            for(int j=0; j<AppConfig.question_List_radioButton.get(i).options.size(); j++) {
                                AppConfig.question_List_radioButton_optionType.add(new QuestionListRadioButtonDataModelOptions(AppConfig.question_List_radioButton.get(i).options.get(j)));
                            }
                            question.setText(AppConfig.question_List_radioButton.get(i).question);
                            adapter_radiobutton.notifyDataSetChanged();

                        }
                    }
                }

                break;
            case R.id.arrow_right:
                if(!AppConfig.question_List_radioButton_optionType.isEmpty())
                {
                    AppConfig.question_List_radioButton_optionType.clear();
                }
                jsonObject1 = new JSONObject();
                try {
                    if(null!= AppConfig.getUser_id() && !AppConfig.answer_list.isEmpty())
                    {
                        jsonObject1.put("user_id", AppConfig.getUser_id());
                        jsonObject1.put("answer", Arrays.toString(AppConfig.answer_list.toArray()).replace("[", "").replace("]", ""));
                        jsonObject1.put("qid",AppConfig.question_List_radioButton.get(i).qid);
                        VolleyRequest volleyRequest = new VolleyRequest(getContext());
                        volleyRequest.settings_Account_settings_YourPreference_QuestionSubmit(jsonObject1, profile_percentage, profile_percentage_value);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                ((QuestionListRadioButtonAdapter)listView.getAdapter()).toggleSelectedClean();
                if(!AppConfig.question_List_radioButton.isEmpty()){
                if(i==AppConfig.question_List_radioButton.size()-1){
                    AppConfig.adapter_radiobutton = null;
                    selectedFragment = QuestionListInputBox.newInstance();
                    intitialiseFragment_LeftToRightTransition_questionList(selectedFragment);
                }else{
                    i++;
                    if(i>=0 && i<AppConfig.question_List_radioButton.size()){

                        for(int j=0; j<AppConfig.question_List_radioButton.get(i).options.size(); j++)
                        {
                            AppConfig.question_List_radioButton_optionType.add(new QuestionListRadioButtonDataModelOptions(AppConfig.question_List_radioButton.get(i).options.get(j)));
                        }
                        question.setText(AppConfig.question_List_radioButton.get(i).question);
                        adapter_radiobutton.notifyDataSetChanged();


                    }
                }

                    /*else if(AppConfig.question_List.get(i).getQuestiontype().equals(2)){
                        for( ; i<AppConfig.question_List_checkBox.size(); i++){
                            question.setText(AppConfig.question_List_checkBox.get(i).question);
                        }
                    }else if(AppConfig.question_List.get(i).getQuestiontype().equals(3)){
                        for( ; i<AppConfig.question_List_checkBox.size(); i++){
                            question.setText(AppConfig.question_List_checkBox.get(i).question);
                        }
                    }*/

                }
                break;
        }
    }
}
