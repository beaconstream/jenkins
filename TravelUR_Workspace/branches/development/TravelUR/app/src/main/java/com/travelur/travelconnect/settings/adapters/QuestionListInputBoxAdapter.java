package com.travelur.travelconnect.settings.adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

import com.travelur.R;

import java.util.ArrayList;

/**
 * @author by Abhijit.
 */

public class QuestionListInputBoxAdapter extends ArrayAdapter {
    Context mContext;
    public QuestionListInputBoxAdapter(ArrayList data, Context context) {
        super(context, R.layout.questionlist_inputbox);
        this.mContext = context;

    }
}
