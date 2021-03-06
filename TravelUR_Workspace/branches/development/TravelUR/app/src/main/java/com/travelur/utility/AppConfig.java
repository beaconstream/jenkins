package com.travelur.utility;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.CountDownTimer;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.travelur.R;
import com.travelur.travelconnect.friends.models.YourFriendsListItems;
import com.travelur.travelconnect.friends.models.YourFriendsSuggestionListItems;
import com.travelur.travelconnect.home.adapters.CommentAdapter;
import com.travelur.travelconnect.home.models.Home;
import com.travelur.travelconnect.home.models.ItemObjects;
import com.travelur.travelconnect.photos.models.PhotoDataModel;
import com.travelur.travelconnect.photos.models.VideoDataModel;
import com.travelur.travelconnect.profile.models.Profile;
import com.travelur.travelconnect.profile.models.ProfileDetailsDataModel;
import com.travelur.travelconnect.rewards.models.Rewards;
import com.travelur.travelconnect.settings.adapters.QuestionListCheckBoxAdapter;
import com.travelur.travelconnect.settings.adapters.QuestionListInputBoxAdapter;
import com.travelur.travelconnect.settings.adapters.QuestionListRadioButtonAdapter;
import com.travelur.travelconnect.settings.models.AccountSettingsDataModel;
import com.travelur.travelconnect.settings.models.YourPreferencesDataModel;
import com.travelur.travelconnect.settings.models.questionlisttypedatamodel.QuestionListCheckBoxDataModel;
import com.travelur.travelconnect.settings.models.questionlisttypedatamodel.QuestionListCheckBoxDataModelOptions;
import com.travelur.travelconnect.settings.models.questionlisttypedatamodel.QuestionListInputBoxDataModel;
import com.travelur.travelconnect.settings.models.questionlisttypedatamodel.QuestionListInputBoxDataModelOptions;
import com.travelur.travelconnect.settings.models.questionlisttypedatamodel.QuestionListRadioButtonDataModel;
import com.travelur.travelconnect.settings.models.questionlisttypedatamodel.QuestionListRadioButtonDataModelOptions;
import com.travelur.travelconnect.vacationpackages.models.BudgetListItem;
import com.travelur.travelconnect.vacationpackages.models.DestinationListItem;
import com.travelur.travelconnect.vacationpackages.models.VacationPackageListItem;
import com.travelur.travelconnect.vacationpackages.models.VacationPackageMoreDetailsCancellationPolicyListItem;
import com.travelur.travelconnect.vacationpackages.models.VacationPackageMoreDetailsDetaileItenaryListItem;
import com.travelur.travelconnect.vacationpackages.models.VacationPackageMoreDetailsListItem;
import com.travelur.travelconnect.vacationpackages.models.VacationPackageMoreDetailsPricePolicyListItem;
import com.travelur.travelconnect.vacationpackages.models.VacationPackageMoreDetailsTermsConditionListItem;
import com.travelur.travelconnect.vacationpackages.models.VacationTypeListItem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/*
 * @author by Abhijit .
 */

public class AppConfig {

    public static final String IMAGE_DIRECTORY_NAME = "Android File Upload";;
    public static final String TAG_FRAGMENT = "TAG_FRAGMENT";
    public static final String TAG_FRAGMENT_QUESTIONLIST = "TAG_FRAGMENT_QUESTIONLIST";
    public static TextView PACKAGE_PRICE;
    public static TextView PACKAGE_NAME ;
    public static ImageView PACKAGE_IMAGE_VIEW;
    public static long splashdeay = 5000;
    public static final int REPEAT_COUNT = 1;
    public static final long DURATION = 150;
    public static String KEY_NAME = "first_name";
    public static String KEY_LASTNAME = "last_name";
    public static String KEY_EMAIL = "email";
    public static String KEY_USER_ID = "user_id";
    public static String KEY_PASSWORD = "password";
    public static String KEY_CONFIRM_PASSWORD = "confirm_password";
    public static String KEY_USERNAME = "username";
    public static String KEY_TYPE = "type";
    public static String KEY_ID = "id";
    public static String KEY_MOBILENO = "mobile_number";
    public static String KEY_IMAGEPATH = "IMAGEPATH";
    public static String KEY_MESSAGE = "message";
    public static String KEY_WALL_ID = "wall_id";
    public static String KEY_GROUP_ID = "group_id";
    public static String KEY_COMMENT = "comment";
    public static String KEY_GROUP_NAME = "group_name";
    public static String KEY_GROUP_DESC = "group_desc";
    public static String KEY_PACKAGE_ID = "package_id";

    public static String KEY_PACKAGE_COUNTRY = "package_country";
    public static String KEY_PACKAGE_TYPE = "package_type";
    public static String KEY_PRICES = "prices";
    public static String KEY_DURATION = "duration";

    public static String KEY_OLD_PASSWORD = "old_password";

    public static int MIN_FRIEND_COUNT_WORLD_TOUR = 25;
    public static int MIN_TRANSACTION_COUNT_WORLD_TOUR = 5;
    public static int MIN_PROFILE_PERCENTAGE_WORLD_TOUR = 100;
    public static int MIN_POST_COUNT_WORLD_TOUR = 50;

    public static int MIN_FRIEND_COUNT_EUROPEAN_TOUR = 15;
    public static int MIN_TRANSACTION_COUNT_EUROPEAN_TOUR = 3;
    public static int MIN_PROFILE_PERCENTAGE_EUROPEAN_TOUR = 100;
    public static int MIN_POST_COUNT_EUROPEAN_TOUR = 10;

    public static int MIN_FRIEND_COUNT_COUPLES_GATEWAY_INTERNATIONAL = 15;
    public static int MIN_TRANSACTION_COUNT_COUPLES_GATEWAY_INTERNATIONAL = 3;
    public static int MIN_PROFILE_PERCENTAGE_COUPLES_GATEWAY_INTERNATIONAL = 100;
    public static int MIN_POST_COUNT_COUPLES_GATEWAY_INTERNATIONAL = 10;

    public static int MIN_FRIEND_COUNT_COUPLES_GATEWAY_DOMESTIC = 15;
    public static int MIN_TRANSACTION_COUNT_COUPLES_GATEWAY_DOMESTIC = 3;
    public static int MIN_PROFILE_PERCENTAGE_COUPLES_GATEWAY_DOMESTIC = 100;
    public static int MIN_POST_COUNT_COUPLES_GATEWAY_DOMESTIC = 50;

    public static int MIN_FRIEND_COUNT_KUN_GATEWAY = 15;
    public static int MIN_TRANSACTION_COUNT_KUN_GATEWAY = 3;
    public static int MIN_PROFILE_PERCENTAGE_KUN_GATEWAY = 100;
    public static int MIN_POST_COUNT_KUN_GATEWAY = 50;

    public static int MIN_FRIEND_COUNT_LOCAL_SPLENDOR_GATEWAY = 15;
    public static int MIN_TRANSACTION_COUNT_LOCAL_SPLENDOR_GATEWAY = 3;
    public static int MIN_PROFILE_PERCENTAGE_LOCAL_SPLENDOR_GATEWAY = 100;
    public static int MIN_POST_COUNT_LOCAL_SPLENDOR_GATEWAY = 50;

    public static List<String> checkbox_vacation_types = new ArrayList<>();
    public static List<String> checkbox_budget = new ArrayList<>();
    public static List<String> checkbox_destination = new ArrayList<>();

    public static List<VacationTypeListItem> vacation_listItems = new ArrayList<>();
    public static List<BudgetListItem> budget_listItems = new ArrayList<>();
    public static List<DestinationListItem> destination_listItems = new ArrayList<>();

    public static String changePassword_email;
    public static String changePassword_old_password ;
    public static String changePassword_password ;
    public static String changePassword_confirm_password ;

    public static TextInputLayout changePassword_oldPassword_til ;

    public static String KEY_FIRST_NAME = "first_name";
    public static String KEY_LAST_NAME = "last_name";
    public static String KEY_COUNTRY_NAME = "country_name";
    public static String KEY_PIN_CODE = "pin_code";
    public static String KEY_ADDRESS = "address";
    public static String KEY_STATE = "state";
    public static String KEY_COUNTRY_CODE = "country_code";
    public static String KEY_DATE_OF_BIRTH = "date_of_birth";
    public static String KEY_PHONE = "phone";
    public static String KEY_HOME_AIRPORT = "home_airport";
    public static String KEY_CITY = "city";
    public static String KEY_IMAGE = "image";
    public static ImageView arrow_left, arrow_right;

    public static String KEY_QID = "qid";
    public static String KEY_ANSWER = "answer";

    public static String MESSAGE = "Please wait. We are loading the video...";
    public static String TITLE = "Loading...";
    public static String BUTTON_NEGATIVE = "DISMISS";
    public static String user_id;
    public static Typeface font;
    public static PopupWindow popup;

    public static Typeface font_OpenSans_Light;

    public static final  String VIDEO_DIALOG_MSG = "Please wait. We are loading the video...";

    //public static final String BASE_URL = "https://travelurdev001.azurewebsites.net/userwebservice/";
    public static final String BASE_URL = "https://www.traveluruat4u.com/userwebservice/";
    //public static  final String VIDEO_URL_MOBILE = "http://travelurdev001.azurewebsites.net/extras/assets/video/Travel_UR_mobile.mp4";
    public static  final String VIDEO_URL_MOBILE = "https://www.traveluruat4u.com/extras/assets/video/Travel_UR_mobile.mp4";

    private static PasswordValidator passwordValidator;

    public static RecyclerView.Adapter volleyRecyclerViewAdapter =null;
    public static BaseAdapter volleyBaseAdapter = null;
    public static CommentAdapter volleyCommentListAdapter = null;
    public static SwipeRefreshLayout volleySwipeRefreshLayout = null;


    public static int signInHome_ImageSelected;
    //Initializing our Home list
    //Creating a List of Home Elements
    public static List<Home> home_List = new ArrayList<>();
    public static List<Home> home_Profile_List = new ArrayList<>();
   // public static List<CommentListItem> comment_List = new ArrayList<>();
    public static List<Profile> group_List = new ArrayList<>();
    public static List<YourPreferencesDataModel> question_List = new ArrayList<>();
    public static List<AccountSettingsDataModel> account_setting_List = new ArrayList<>();
    public static List<ProfileDetailsDataModel> profile_details_List = new ArrayList<>();
    public static List<Rewards> reward_worldtour_list = new ArrayList<>();
    public static List<Rewards> reward_europeantour_list = new ArrayList<>();
    public static List<Rewards> reward_couplesgateway_international_list = new ArrayList<>();
    public static List<Rewards> reward_couplesgateway_domestic_list = new ArrayList<>();
    public static List<Rewards> reward_knowurneighbour_list = new ArrayList<>();
    public static List<Rewards> reward_localsplendors_list = new ArrayList<>();
    public static List<VacationPackageListItem> vacationpackages_get_all = new ArrayList<>();
    public static List<VacationPackageListItem> vacationpackages_get_all_filter = new ArrayList<>();
    public static List<VacationPackageMoreDetailsListItem> vacationpackages_moredetails_listitem = new ArrayList<>();
    public static List<VacationPackageMoreDetailsTermsConditionListItem> vacationpackages_moredetails_termscondition_listitem = new ArrayList<>();
    public static List<VacationPackageMoreDetailsDetaileItenaryListItem> vacationpackages_moredetails_detaileditenary_listitem = new ArrayList<>();
    public static List<PhotoDataModel> vacationpackages_moredetails_gallery_listitem = new ArrayList<>();
    public static List<VacationPackageMoreDetailsPricePolicyListItem> vacationpackages_moredetails_pricePolicy_listitem = new ArrayList<>();
    public static List<VacationPackageMoreDetailsCancellationPolicyListItem> vacationpackages_moredetails_cancellationPolicy_listitem = new ArrayList<>();
    public static List<YourFriendsListItems> yourFriendsListItems = new ArrayList<>();
    public static List<YourFriendsSuggestionListItems> yourFriendsSuggestionListItems = new ArrayList<>();
    public static List<ItemObjects> gaggeredList = new ArrayList<ItemObjects>();
    public static ArrayList<QuestionListCheckBoxDataModel> question_List_checkBox = new ArrayList<>();
    public static ArrayList<QuestionListRadioButtonDataModel> question_List_radioButton = new ArrayList<>();
    public static ArrayList<QuestionListInputBoxDataModel> question_List_inputBox = new ArrayList<>();
    public static ArrayList<QuestionListCheckBoxDataModelOptions> question_List_checkBox_optionType = new ArrayList<>();
    public static ArrayList<QuestionListRadioButtonDataModelOptions> question_List_radioButton_optionType = new ArrayList<>();
    public static ArrayList<QuestionListInputBoxDataModelOptions> question_List_inputBox_optionType = new ArrayList<>();
    public static String vacation_package_seletedppackege;
    public static QuestionListCheckBoxAdapter adapter_checkbox = null;
    public static QuestionListRadioButtonAdapter adapter_radiobutton = null;
    public static QuestionListInputBoxAdapter adapter_inputbox = null;
    public static ArrayList<String> answer_list = new ArrayList<>();
    public static List<PhotoDataModel> urphoto_List = new ArrayList<>();
    public static List<VideoDataModel> urvideo_List = new ArrayList<>();
    public static java.lang.String TermsOfService = "http://travelurdev001.azurewebsites.net/userwebservice/terms";
    public static java.lang.String Cancellation = "http://travelurdev001.azurewebsites.net/userwebservice/cancelation";
    public static java.lang.String PrivacyPolicy = "http://travelurdev001.azurewebsites.net/userwebservice/helpsupport";
    public static int profile_percentage;

    public static TextView DAYS;
    public static TextView HOURS;
    public static TextView MINS;
    public static TextView SECS;

    public static int MEDIA_TYPE_PHOTOS_SINGLE_SELECTION = 427;
    public static int MEDIA_TYPE_PHOTOS = 327;
    public static int MEDIA_TYPE_VIDEOS = 123;
    public static int DEFAULT = 234;
    public static String MEDIA_TYPE = "MEDIA_TYPE";
    public static String PHOTO_LIST = "PHOTO_LIST";
    public static String VIDEO_EXTRA = "VIDEO_EXTRA";
    public static String SINGLE_PHOTO_LIST = "SINGLE_PHOTO_EXTRA";
    public static final int REQUEST_CODE_PHOTOS = 912;
    public static final int REQUEST_CODE_VIDEOS = 972;
    public static RecyclerView.Adapter friendsSuggestionAdapter;
    //public static TextView friends_count;
    public static TextView pending_requests_count;
    public static CountDownTimer countDownTimer = null;
    public static LinearLayout vacation_type_layout;
    public static LinearLayout budget_layout;
    public static LinearLayout destination_layout;
    public static List<CheckBox> selectedcheckBox = new ArrayList<CheckBox> ();

    public static TextView profile_name, place, post_count, photos_count, videos_count, friends_count, groups_count;
    public static ImageView edit_profile_pic;
    public static ProgressBar profile_percentage_progressbar;

    public static  boolean validateEmail(CharSequence email)
    {
        if (email == null) {
            return false;
        } else {
            return Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }
    }

    public static boolean validatePassword(String password) {
        passwordValidator = new PasswordValidator();
        if (password == null) {
            return false;
        } else {
            return passwordValidator.validate(password);
        }
    }
    public static String getUser_id() {
        return user_id;
    }

    public static void setUser_id(String user_id) {
        AppConfig.user_id = user_id;
    }
    public static Typeface getFont() {
        return font;
    }

    public static void setFont(Typeface font) {
        AppConfig.font = font;
    }
    public static Typeface getFont_OpenSans_Light() {
        return font_OpenSans_Light;
    }

    public static void setFont_OpenSans_Light(Typeface font_OpenSans_Light) {
        AppConfig.font_OpenSans_Light = font_OpenSans_Light;
    }

    public static long getElapsedTime(String startingd_time, String endingd_time){
        long diff = 0;
        // String rewardsEndDate = "2017-11-30 24:00:00";
        String serverCurrentDate = startingd_time+" "+"12:00:00";
        String rewardsEndDate = endingd_time+" "+"24:00:00";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {

            Date lastDate = dateFormat.parse(rewardsEndDate);
            Date serverCurrent_Date = dateFormat.parse(serverCurrentDate);
            System.out.println(lastDate);

            Date currentDate = new Date();
            diff = lastDate.getTime()-serverCurrent_Date.getTime();
            if(serverCurrent_Date.after(currentDate)){
                if(countDownTimer!=null)
                    countDownTimer.cancel();
                diff = lastDate.getTime()-serverCurrent_Date.getTime();
            }else if(serverCurrent_Date.before(currentDate)){
                diff = lastDate.getTime()-currentDate.getTime();
            }


            long seconds = diff / 1000;
            long minutes = seconds / 60;
            long hours = minutes / 60;
            long days = hours / 24;

            // if (lastDate.before(currentDate)) {
            if (lastDate.before(currentDate)) {

                Log.e("oldDate", "is previous date");
                Log.e("Difference: ", " seconds: " + seconds + " minutes: " + minutes
                        + " hours: " + hours + " days: " + days);

            }

            // Log.e("toyBornTime", "" + toyBornTime);

        } catch (ParseException e) {

            e.printStackTrace();
        }
        return diff;
    }
    //This step is optional, but if you want you can configure some Toasty parameters. Place this anywhere in your app:

         /*   Toasty.Config.getInstance()
            .setErrorColor(@ColorInt int errorColor) // optional
    .setInfoColor(@ColorInt int infoColor) // optional
    .setSuccessColor(@ColorInt int successColor) // optional
    .setWarningColor(@ColorInt int warningColor) // optional
    .setTextColor(@ColorInt int textColor) // optional
    .tintIcon(boolean tintIcon) // optional (apply textColor also to the icon)
    .setToastTypeface(@NonNull Typeface typeface) // optional
    .setTextSize(int sizeInSp) // optional
    .apply(); // required*/

    //You can reset the configuration by using reset() method:

    //      Toasty.Config.reset();

    /*To display an error Toast:

            Toasty.error(yourContext, "This is an error toast.", Toast.LENGTH_SHORT, true).show();
    To display a success Toast:

            Toasty.success(yourContext, "Success!", Toast.LENGTH_SHORT, true).show();
    To display an info Toast:

            Toasty.info(yourContext, "Here is some info for you.", Toast.LENGTH_SHORT, true).show();
    To display a warning Toast:

            Toasty.warning(yourContext, "Beware of the dog.", Toast.LENGTH_SHORT, true).show();
    To display the usual Toast:

            Toasty.normal(yourContext, "Normal toast w/o icon").show();
    To display the usual Toast with icon:

            Toasty.normal(yourContext, "Normal toast w/ icon", yourIconDrawable).show();
    You can also create your custom Toasts with the custom() method:

            Toasty.custom(yourContext, "I'm a custom Toast", yourIconDrawable, tintColor, duration, withIcon,
    shouldTint).show();*/

    public static void startCountDown(String startingd_time, String endingd_time){
        long diff = 0;
        // String rewardsEndDate = "2017-11-30 24:00:00";
        final String serverCurrentDate = startingd_time+" "+"12:00:00";
        String rewardsEndDate = endingd_time+" "+"24:00:00";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date lastDate=null;
        Date serverCurrent_Date =null;
        Date currentDate=null;
        try {

            lastDate = dateFormat.parse(rewardsEndDate);
            serverCurrent_Date = dateFormat.parse(serverCurrentDate);
            System.out.println(lastDate);

            currentDate = new Date();

        }catch (ParseException e){

        }


    /*    creating object for all text views    */

    /*    172800000 milliseconds = 5days

    1000(1sec) is time interval to call onTick method

    millisUntilFinished is amount of until finished

    */

        //new CountDownTimer(Long.parseLong("7516800000"), 1000){

        long result = AppConfig.getElapsedTime(startingd_time, endingd_time);
        final Date finalServerCurrent_Date = serverCurrent_Date;
        final Date finalCurrentDate = currentDate;
        countDownTimer = new CountDownTimer(result, 1000) {

            @Override

            public void onTick(long millisUntilFinished) {
            /*            converting the milliseconds into days, hours, minutes and seconds and displaying it in textviews             */

                if (finalServerCurrent_Date.after(finalCurrentDate)) {

                    //countDownTimer.cancel();
                    DAYS.setText(TimeUnit.HOURS.toDays(TimeUnit.MILLISECONDS.toHours(millisUntilFinished)) + "");

                    HOURS.setText((TimeUnit.MILLISECONDS.toHours(millisUntilFinished) - TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(millisUntilFinished))) + "");

                    MINS.setText((TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished))) + "");

                    SECS.setText((TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))) + "");
                    countDownTimer.cancel();

                } else if(finalServerCurrent_Date.before(finalCurrentDate)){

                    DAYS.setText(TimeUnit.HOURS.toDays(TimeUnit.MILLISECONDS.toHours(millisUntilFinished)) + "");

                    HOURS.setText((TimeUnit.MILLISECONDS.toHours(millisUntilFinished) - TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(millisUntilFinished))) + "");

                    MINS.setText((TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished))) + "");

                    SECS.setText((TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))) + "");
                }

            }

            @Override

            public void onFinish() {
            /*            clearing all fields and displaying countdown finished message             */

                DAYS.setText("00");
                HOURS.setText("00");
                MINS.setText("00");
                SECS.setText("00");
            }
        }.start();

    }

    public static void setCheckbox_vacation_types(Context context, LinearLayout layout, List<VacationTypeListItem> vacation_listItems){

        int table_size = vacation_listItems.size();//table_size=/*the size of your answer from the server*/
        //int table_size = 5;//table_size=/*the size of your answer from the server*/
        TableLayout tl = new TableLayout(context);
        int offset_in_column=0;
        TableRow tr=null;
        //View rowView = .inflate(R.layout.vacatio, null, true);
        for (int offset_in_table=0; offset_in_table < table_size; offset_in_table++) {
                /* maybe you want to do something special with the data from the server here ? */

            if (offset_in_column == 0) {
                tr = new TableRow(context);
                tr.setLayoutParams(new TableLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            }
            final CheckBox check = new CheckBox(context);
            check.setText(vacation_listItems.get(offset_in_table).getPackage_types_name());
            check.setTextColor(Color.parseColor("#B6B6B4"));
            //check.setButtonTintList(ColorStateList.valueOf(Color.parseColor("#FF02548B")));
            check.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AppConfig.selectedcheckBox.add(check);

                }
            });
            check.setLayoutParams(new TableRow.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1));
            tr.addView(check);

            offset_in_column++;
            if (offset_in_column == 2) {
                tl.addView(tr);
                offset_in_column = 0;
            }
            check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        AppConfig.checkbox_vacation_types.add(buttonView.getText().toString());

                    }
                    else{
                        AppConfig.checkbox_vacation_types.remove(buttonView.getText().toString());

                    }
                }
            });
        }
        if (offset_in_column != 0)
            tl.addView(tr);

        layout.addView(tl);
    }

    public static void setCheckbox_budget(Context context, LinearLayout layout, List<BudgetListItem> budgetListItems){

        int table_size = budgetListItems.size();//table_size=/*the size of your answer from the server*/
        //int table_size = 5;//table_size=/*the size of your answer from the server*/
        TableLayout tl = new TableLayout(context);
        int offset_in_column=0;
        TableRow tr=null;
        //View rowView = .inflate(R.layout.vacatio, null, true);
        for (int offset_in_table=0; offset_in_table < table_size; offset_in_table++) {
                /* maybe you want to do something special with the data from the server here ? */

            if (offset_in_column == 0) {
                tr = new TableRow(context);
                tr.setLayoutParams(new TableLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            }
            final CheckBox check = new CheckBox(context);
            check.setText(budgetListItems.get(offset_in_table).getBudget_name());
            check.setTextColor(Color.parseColor("#B6B6B4"));
            //check.setButtonTintList(ColorStateList.valueOf(Color.parseColor("#FF02548B")));

            check.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AppConfig.selectedcheckBox.add(check);

                }
            });
            check.setLayoutParams(new TableRow.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1));
            tr.addView(check);

            offset_in_column++;
            if (offset_in_column == 2) {
                tl.addView(tr);
                offset_in_column = 0;
            }
            check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        AppConfig.checkbox_budget.add(buttonView.getText().toString());
                    }
                    else{
                        AppConfig.checkbox_budget.remove(buttonView.getText().toString());
                    }
                }
            });
        }
        if (offset_in_column != 0)
            tl.addView(tr);

        layout.addView(tl);
    }

    public static void setCheckbox_destination(Context context, LinearLayout layout, List<DestinationListItem> destinationListItems){

        int table_size = destinationListItems.size();//table_size=/*the size of your answer from the server*/
        //int table_size = 5;//table_size=/*the size of your answer from the server*/
        TableLayout tl = new TableLayout(context);
        int offset_in_column=0;
        TableRow tr=null;
        //View rowView = .inflate(R.layout.vacatio, null, true);
        for (int offset_in_table=0; offset_in_table < table_size; offset_in_table++) {
                /* maybe you want to do something special with the data from the server here ? */

            if (offset_in_column == 0) {
                tr = new TableRow(context);
                tr.setLayoutParams(new TableLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            }
            final CheckBox check = new CheckBox(context);
            check.setText(destinationListItems.get(offset_in_table).getCountry_name());
            check.setTextColor(Color.parseColor("#B6B6B4"));
            //check.setButtonTintList(ColorStateList.valueOf(Color.parseColor("#FF02548B")));
            check.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AppConfig.selectedcheckBox.add(check);

                }
            });
            check.setLayoutParams(new TableRow.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1));
            tr.addView(check);

            offset_in_column++;
            if (offset_in_column == 2) {
                tl.addView(tr);
                offset_in_column = 0;
            }
            check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        AppConfig.checkbox_destination.add(buttonView.getText().toString());
                    }
                    else{
                        AppConfig.checkbox_destination.remove(buttonView.getText().toString());
                    }
                }
            });
        }
        if (offset_in_column != 0)
            tl.addView(tr);

        layout.addView(tl);
    }

    public static View displayPopupWindow(View anchorView, Context context) {
        popup = new PopupWindow(context);
        View layout = LayoutInflater.from(context).inflate(R.layout.send_enquiry_thanksmessage, null);
        popup.setContentView(layout);
        // Set content width and height
        popup.setHeight(WindowManager.LayoutParams.MATCH_PARENT);
        popup.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        // Closes the popup window when touch outside of it - when looses focus
        popup.setOutsideTouchable(true);
        popup.setFocusable(true);
        // Show anchored to button
        popup.setBackgroundDrawable(new BitmapDrawable());
        popup.showAsDropDown(anchorView);

        View container = (View) popup.getContentView().getParent().getParent();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        WindowManager.LayoutParams p = (WindowManager.LayoutParams) container.getLayoutParams();
        p.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        p.dimAmount = 0.8f;
        wm.updateViewLayout(container, p);
        return layout;
    }
}
