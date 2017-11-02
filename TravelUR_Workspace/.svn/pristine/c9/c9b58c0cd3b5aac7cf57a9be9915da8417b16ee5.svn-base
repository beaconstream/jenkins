package com.travelur.travelconnect.friends.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.travelur.R;
import com.travelur.travelconnect.friends.models.YourFriendsListItems;
import com.travelur.utility.AppConfig;
import com.travelur.utility.GlideCircleTransformation;

import java.util.ArrayList;
import java.util.List;

/*
 * @author by Abhijit .
 */

public class YourFriendsListAdapter extends BaseAdapter implements Filterable {
    Context context;
    List mStringFilterList;
    ValueFilter valueFilter;
    List<YourFriendsListItems> yourFriendsListItems;
    public YourFriendsListAdapter(Context context, List<YourFriendsListItems> yourFriendsListItems){
        this.context=context;
        this.yourFriendsListItems = yourFriendsListItems;
        this.mStringFilterList = yourFriendsListItems;
    }
    @Override
    public int getCount() {
        return yourFriendsListItems.size();
    }

    @Override
    public Object getItem(int position) {
        return yourFriendsListItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view= inflater.inflate(R.layout.your_friends_list_item,null);

        AppConfig.friends_count.setText(yourFriendsListItems.get(position).getFriends_count());
        AppConfig.pending_requests_count.setText(yourFriendsListItems.get(position).getPending_requests());

        ImageView message= (ImageView) view.findViewById(R.id.message);
        ImageView more= (ImageView) view.findViewById(R.id.more);
        ImageView profile_icon= (ImageView) view.findViewById(R.id.profile_pic);
        Glide.with(context).load(yourFriendsListItems.get(position).getProfilepic())
                .thumbnail(0.5f)
                .crossFade()
                .placeholder(R.drawable.profile_placeholder)
                //.error(R.drawable.error_icon)
                .bitmapTransform(new GlideCircleTransformation(context))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(profile_icon);
        Typeface font = Typeface.createFromAsset(context.getAssets(), "fontawesome-webfont.ttf");
        TextView profile_Name = (TextView) view.findViewById(R.id.profile_name);
        TextView profile_Place = (TextView) view.findViewById(R.id.profile_place);
        profile_Name.setText(yourFriendsListItems.get(position).getFirst_name()+" "+yourFriendsListItems.get(position).getLast_name());
        profile_Place.setText(yourFriendsListItems.get(position).getCity()+","+yourFriendsListItems.get(position).getCountry_name());

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
            yourFriendsListItems = (List)results.values;
            notifyDataSetChanged();
        }

    }
}
