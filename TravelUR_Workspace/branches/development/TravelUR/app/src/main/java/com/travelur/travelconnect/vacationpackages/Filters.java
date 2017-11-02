package com.travelur.travelconnect.vacationpackages;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarChangeListener;
import com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar;
import com.travelur.R;
import com.travelur.backend.VolleyRequest;
import com.travelur.general.BaseFragment;
import com.travelur.travelconnect.settings.settingtype.YourPreferences;
import com.travelur.travelconnect.vacationpackages.models.DestinationListItem;
import com.travelur.travelconnect.vacationpackages.models.VacationTypeListItem;
import com.travelur.utility.AppConfig;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.travelur.utility.AppConfig.budget_listItems;
import static com.travelur.utility.AppConfig.checkbox_destination;
import static com.travelur.utility.AppConfig.checkbox_vacation_types;
import static com.travelur.utility.AppConfig.destination_listItems;
import static com.travelur.utility.AppConfig.selectedcheckBox;
import static com.travelur.utility.AppConfig.vacation_listItems;

/**
 * @author by Abhijit.
 */

public class Filters extends BaseFragment implements View.OnClickListener{

    private Button clear, apply;
    private TextView close, custom_toolbar_menu_item;
    public static Filters newInstance() {
        Filters fragment = new Filters();
        return fragment;
    }

    private TextView duration;
    private TextView duration2;
    //private SeekBar seekBar;
    private CrystalRangeSeekbar rangeSeekbar;
    private LinearLayout vacation_type_layout, budget_layout, destination_layout;
    private JSONObject jsonObject;
    private String MIN_VALUE, MAX_VALUE;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.vacationpackage_filter, null, false);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.setClickable(true);
        close = (TextView) view.findViewById(R.id.close);
        close.setText("Cancel");
        close.setTextColor(getResources().getColor(R.color.textView_color));
        title = (TextView) view.findViewById(R.id.action_bar_title);
        title.setText("Filter by");
        custom_toolbar_menu_item = (TextView) view.findViewById(R.id.custom_toolbar_menu_item);
        custom_toolbar_menu_item.setText("   ");
        duration = (TextView) view.findViewById(R.id.duration);
        duration2 = (TextView) view.findViewById(R.id.duration2);
        rangeSeekbar = (CrystalRangeSeekbar) view.findViewById(R.id.seekbar);
        //seekBar = (SeekBar) view.findViewById(R.id.seekbar);
        clear = (Button) view.findViewById(R.id.clear) ;
        apply = (Button) view.findViewById(R.id.apply) ;
        vacation_type_layout = (LinearLayout)view.findViewById(R.id.vacation_type_layout);
        budget_layout = (LinearLayout)view.findViewById(R.id.budget_layout);
        destination_layout = (LinearLayout)view.findViewById(R.id.destination_layout);
        // Initialize the textview with '0'.
        AppConfig.vacation_type_layout = vacation_type_layout;
        AppConfig.budget_layout = budget_layout;
        AppConfig.destination_layout = destination_layout;
        //duration.setText("Days: " + seekBar.getProgress() + "/" + seekBar.getMax());

        VolleyRequest volleyRequest = new VolleyRequest(getContext());
        volleyRequest.vacation_package_vactionType();
// set listener
        rangeSeekbar.setOnRangeSeekbarChangeListener(new OnRangeSeekbarChangeListener() {
            @Override
            public void valueChanged(Number minValue, Number maxValue) {
                duration.setText(String.valueOf(minValue));
                duration2.setText(" - "+String.valueOf(maxValue));
                MIN_VALUE = String.valueOf(minValue);
                MAX_VALUE = String.valueOf(maxValue);
            }
        });
       /* seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
                progress = progresValue;
                Toast.makeText(getContext(), "Changing Duration", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getContext(), "Tracking duration", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                duration.setText("Days: " + progress + "/" + seekBar.getMax());
                Toast.makeText(getContext(), "Stopped tracking seekbar", Toast.LENGTH_SHORT).show();
            }
        });*/

        close.setOnClickListener(this);
        clear.setOnClickListener(this);
        apply.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.clear:
                for(int i=0; i<selectedcheckBox.size(); i++) {
                    selectedcheckBox.get(i).setChecked(false);
                    if(!AppConfig.checkbox_vacation_types.isEmpty())
                        AppConfig.checkbox_vacation_types.clear();
                    if(!AppConfig.checkbox_budget.isEmpty())
                        AppConfig.checkbox_budget.clear();
                    if(!AppConfig.checkbox_destination.isEmpty())
                        AppConfig.checkbox_destination.clear();
                    //seekBar.setProgress(0);
                    rangeSeekbar.setMinValue(1);
                    rangeSeekbar.setMaxValue(12);

                }
                break;
            case R.id.apply:

                jsonObject = new JSONObject();
                try {
                    if(null!=AppConfig.getUser_id())
                    {
                        /*List<VacationTypeListItem> common = new ArrayList<VacationTypeListItem>(AppConfig.vacation_listItems);
                        common.retainAll(AppConfig.checkbox_vacation_types);
                        jsonObject.put("package_country", common.toArray());
                        List<DestinationListItem> common2 = new ArrayList<DestinationListItem>(destination_listItems);
                        common.retainAll(AppConfig.checkbox_destination);
                        jsonObject.put("package_type", common2.toArray());*/
                        if(!AppConfig.vacation_listItems.isEmpty()){
                            for(int i=0;i<AppConfig.vacation_listItems.size();i++){
                                for(int j=0;j<AppConfig.checkbox_vacation_types.size();j++){
                                    if(vacation_listItems.get(i).getPackage_types_name().equalsIgnoreCase(checkbox_vacation_types.get(j))){
                                        jsonObject.put("package_country", vacation_listItems.get(i).getPackage_types_id());
                                    }

                                }
                            }
                        }
                        if(!destination_listItems.isEmpty()){
                            for(int i = 0; i< destination_listItems.size(); i++){
                                for(int j=0;j<AppConfig.checkbox_destination.size();j++){
                                    if(destination_listItems.get(i).getCountry_name().equalsIgnoreCase(checkbox_destination.get(j))){
                                        jsonObject.put("package_type", destination_listItems.get(i).getPackage_country());
                                    }

                                }
                            }
                        }
                        if(!budget_listItems.isEmpty()){
                            for(int i = 0; i< budget_listItems.size(); i++){
                                for(int j=0;j<AppConfig.checkbox_vacation_types.size();j++){
                                    if(budget_listItems.get(i).getBudget_name().equalsIgnoreCase(checkbox_vacation_types.get(j))){
                                        jsonObject.put("prices", budget_listItems.get(i).getBudget_type());
                                    }

                                }
                            }
                        }

                        jsonObject.put("duration", MIN_VALUE+"-"+MAX_VALUE);

                        VolleyRequest volleyRequest = new VolleyRequest(getContext());
                        volleyRequest.vacation_package_filter_apply(jsonObject, getFragmentManager());
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.close:
                if (getFragmentManager().getBackStackEntryCount() > 0) {
                    getFragmentManager().popBackStack();
                }
                break;
        }
    }
}
