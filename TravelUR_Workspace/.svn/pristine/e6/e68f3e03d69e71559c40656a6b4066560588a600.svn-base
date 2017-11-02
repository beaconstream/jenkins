package com.travelur.Friendss;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.travelur.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Priyanka on 19-04-2017.
 */

public class FriendsListAdapter extends BaseAdapter implements Filterable {
    Context context;
    List mData;
    List mStringFilterList;
    ValueFilter valueFilter;
    public FriendsListAdapter(Context context, List cancel_type){
        this.context=context;
        mData = cancel_type;
        mStringFilterList = cancel_type;
    }
    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view= inflater.inflate(R.layout.friends_list_item,null);
        TextView add_friend= (TextView) view.findViewById(R.id.add_frnds);
        TextView profile_icon= (TextView) view.findViewById(R.id.profile_icon);
        Typeface font = Typeface.createFromAsset(context.getAssets(), "fontawesome-webfont.ttf");
        TextView stringName = (TextView) view.findViewById(R.id.textView15);
        stringName.setText((CharSequence) mData.get(position));
        add_friend.setTypeface(font);
        profile_icon.setTypeface(font);
        return view;
    }

    @Override
    public Filter getFilter() {
        if (valueFilter == null) {
            valueFilter = new ValueFilter();
        }
        return valueFilter;
    }

    private class ValueFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if (constraint != null && constraint.length() > 0) {
                List filterList = new ArrayList();
                for (int i = 0; i < mStringFilterList.size(); i++) {
                    if ((mStringFilterList.get(i).toString().toUpperCase()).contains(constraint.toString().toUpperCase())) {
                        filterList.add(mStringFilterList.get(i));
                    }
                }
                results.count = filterList.size();
                results.values = filterList;
            } else {
                results.count = mStringFilterList.size();
                results.values = mStringFilterList;
            }
            return results;

        }

        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {
            mData = (List) results.values;
            notifyDataSetChanged();
        }

    }
}
