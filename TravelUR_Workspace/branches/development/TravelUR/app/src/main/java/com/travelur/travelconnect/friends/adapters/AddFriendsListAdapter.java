package com.travelur.travelconnect.friends.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.travelur.R;
import com.travelur.travelconnect.friends.YourFriendsSuggestion;
import com.travelur.travelconnect.friends.models.YourFriendsSuggestionListItems;
import com.travelur.utility.AppConfig;
import com.travelur.utility.GlideCircleTransformation;

import java.util.ArrayList;
import java.util.List;

import static com.travelur.utility.AppConfig.yourFriendsListItems;

/*
 * @author by Abhijit .
 */

public class AddFriendsListAdapter extends BaseAdapter implements Filterable, View.OnClickListener {
    Context context;
    List<YourFriendsSuggestionListItems> yourFriendsSuggestionListItems;
    List<YourFriendsSuggestionListItems> mStringFilterList;
    ValueFilter valueFilter;
    public AddFriendsListAdapter(Context context, List<YourFriendsSuggestionListItems> yourFriendsSuggestionListItems){
        this.context=context;
        this.yourFriendsSuggestionListItems = yourFriendsSuggestionListItems;
        mStringFilterList = yourFriendsSuggestionListItems;
    }
    @Override
    public int getCount() {
        return yourFriendsSuggestionListItems.size();
    }

    @Override
    public Object getItem(int position) {
        return yourFriendsSuggestionListItems.get(position);
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

        Button add_friend= (Button) view.findViewById(R.id.add_frnds);
        ImageView profile_icon= (ImageView) view.findViewById(R.id.profile_icon);
        Glide.with(context).load(yourFriendsSuggestionListItems.get(position).getProfilepic())
                .thumbnail(0.5f)
                .crossFade()
                .placeholder(R.drawable.profile_placeholder)
                //.error(R.drawable.error_icon)
                .bitmapTransform(new GlideCircleTransformation(context))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(profile_icon);
        Typeface font = Typeface.createFromAsset(context.getAssets(), "fontawesome-webfont.ttf");
        TextView stringName = (TextView) view.findViewById(R.id.name);
        TextView city = (TextView) view.findViewById(R.id.city);
        stringName.setText(yourFriendsSuggestionListItems.get(position).getFirst_name()+" "+yourFriendsSuggestionListItems.get(position).getLast_name());
        city.setText(yourFriendsSuggestionListItems.get(position).getCity()+","+yourFriendsSuggestionListItems.get(position).getState()+","+yourFriendsSuggestionListItems.get(position).getCountry_name());
        return view;
    }

    @Override
    public Filter getFilter() {
        if (valueFilter == null) {
            valueFilter = new ValueFilter();
        }
        return valueFilter;
    }

    @Override
    public void onClick(View v) {

    }

    private class ValueFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if (constraint != null && constraint.length() > 0) {
                List filterList = new ArrayList();
                for (int i = 0; i < mStringFilterList.size(); i++) {
                    if ((mStringFilterList.get(i).getFirst_name().toString().toUpperCase()).contains(constraint.toString().toUpperCase())) {
                        filterList.add(mStringFilterList.get(i).getFirst_name());
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
            yourFriendsSuggestionListItems = (List) results.values;
            notifyDataSetChanged();
        }

    }
}
