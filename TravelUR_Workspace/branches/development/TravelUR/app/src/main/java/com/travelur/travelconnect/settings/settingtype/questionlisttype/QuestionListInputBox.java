package com.travelur.travelconnect.settings.settingtype.questionlisttype;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.travelur.R;
import com.travelur.backend.VolleyRequest;
import com.travelur.general.BaseFragment;
import com.travelur.travelconnect.settings.adapters.QuestionListCheckBoxAdapter;
import com.travelur.travelconnect.settings.adapters.QuestionListInputBoxAdapter;
import com.travelur.travelconnect.settings.adapters.QuestionListRadioButtonAdapter;
import com.travelur.travelconnect.settings.models.questionlisttypedatamodel.QuestionListCheckBoxDataModelOptions;
import com.travelur.travelconnect.settings.models.questionlisttypedatamodel.QuestionListRadioButtonDataModelOptions;
import com.travelur.utility.AppConfig;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import static com.travelur.utility.AppConfig.adapter_checkbox;
import static com.travelur.utility.AppConfig.adapter_inputbox;
import static com.travelur.utility.AppConfig.adapter_radiobutton;

/**
 * @author by Abhijit.
 */

public class QuestionListInputBox extends BaseFragment implements View.OnClickListener {

    private ImageView arrow_left, arrow_right;
    private static TextView question;
    private TextView profile_percentage_value;
    private ProgressBar profile_percentage;
    private JSONObject jsonObject;
    private JSONObject jsonObject1;
    private EditText input_box;
    private int i=0;

    public static QuestionListInputBox newInstance() {
        QuestionListInputBox fragment = new QuestionListInputBox();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.questionlist_inputbox, null, false);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        arrow_left = AppConfig.arrow_left;
        arrow_right = AppConfig.arrow_right;
        /*arrow_left = (ImageView) view.findViewById(R.id.arrow_left);
        arrow_right = (ImageView) view.findViewById(R.id.arrow_right);*/
        question = (TextView) view.findViewById(R.id.question);
        profile_percentage = (ProgressBar) view.findViewById(R.id.profile_percentage);
        profile_percentage_value = (TextView) view.findViewById(R.id.profile_percentage_value);
        profile_percentage.setProgress(AppConfig.profile_percentage);
        profile_percentage_value.setText(AppConfig.profile_percentage+"/100");
        input_box = (EditText) view.findViewById(R.id.input_box);

        adapter_inputbox = new QuestionListInputBoxAdapter(AppConfig.question_List_checkBox_optionType, getContext());


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

        arrow_left.setOnClickListener(this);
        arrow_right.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.arrow_left:
                jsonObject1 = new JSONObject();
                try {
                    if(null!= AppConfig.getUser_id() && input_box.getText().length()>0)
                    {
                        jsonObject1.put("user_id", AppConfig.getUser_id());
                        jsonObject1.put("answer", input_box.getText().toString());
                        jsonObject1.put("qid",AppConfig.question_List_inputBox.get(i).qid);
                        VolleyRequest volleyRequest = new VolleyRequest(getContext());
                        volleyRequest.settings_Account_settings_YourPreference_QuestionSubmit(jsonObject1, profile_percentage, profile_percentage_value);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if(input_box.getText().length()!=0)
                    input_box.getText().clear();
                if(i==0){
                    AppConfig.adapter_inputbox = null;
                    selectedFragment = QuestionListRadioButton.newInstance();
                    intitialiseFragment_LeftToRightTransition_questionList(selectedFragment);
                }
                else{
                    i--;

                    if(!AppConfig.question_List_inputBox.isEmpty()) {
                        if (i >=0 && i<AppConfig.question_List_inputBox.size()) {
                            question.setText(AppConfig.question_List_inputBox.get(i).question);

                        }
                    }
                }

                break;
            case R.id.arrow_right:
                jsonObject1 = new JSONObject();
                try {
                    if(null!= AppConfig.getUser_id() && input_box.getText().length()>0)
                    {
                        jsonObject1.put("user_id", AppConfig.getUser_id());
                        jsonObject1.put("answer", input_box.getText().toString());
                        jsonObject1.put("qid",AppConfig.question_List_inputBox.get(i).qid);
                        VolleyRequest volleyRequest = new VolleyRequest(getContext());
                        volleyRequest.settings_Account_settings_YourPreference_QuestionSubmit(jsonObject1, profile_percentage, profile_percentage_value);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if(input_box.getText().length()!=0)
                    input_box.getText().clear();
                if(!AppConfig.question_List_inputBox.isEmpty()){
                    if(i==AppConfig.question_List_inputBox.size()-1){
                        Toast.makeText(getContext(),"No More Questions...",Toast.LENGTH_SHORT).show();
                    }else{
                        i++;
                        if(i>=0 && i<AppConfig.question_List_inputBox.size()){
                            question.setText(AppConfig.question_List_inputBox.get(i).question);
                        }
                    }
                }
                break;
        }
    }
}
