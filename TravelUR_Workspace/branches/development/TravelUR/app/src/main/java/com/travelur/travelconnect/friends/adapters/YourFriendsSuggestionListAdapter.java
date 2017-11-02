package com.travelur.travelconnect.friends.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.travelur.R;
import com.travelur.travelconnect.friends.models.YourFriendsSuggestionListItems;
import com.travelur.utility.GlideCircleTransformation;

import java.util.ArrayList;
import java.util.List;

/**
 * @author by Abhijit.
 */

public class YourFriendsSuggestionListAdapter extends RecyclerView.Adapter<YourFriendsSuggestionListAdapter.ViewHolder> {

    List<YourFriendsSuggestionListItems> yourFriendsSuggestionListItems;
    Context context;

    public YourFriendsSuggestionListAdapter(Context context, List<YourFriendsSuggestionListItems> yourFriendsSuggestionListItems) {
        super();
        this.context = context;
        this.yourFriendsSuggestionListItems = yourFriendsSuggestionListItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.your_friends_vertical_suggestion_carditems, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        viewHolder.profile_name.setText(yourFriendsSuggestionListItems.get(i).getFirst_name()+" "+yourFriendsSuggestionListItems.get(i).getLast_name());
        viewHolder.profile_place.setText(yourFriendsSuggestionListItems.get(i).getCity());
        Glide.with(context).load(yourFriendsSuggestionListItems.get(i).getProfilepic())
                .thumbnail(0.5f)
                .crossFade()
                .placeholder(R.drawable.profile_placeholder)
                //.error(R.drawable.error_icon)
                .bitmapTransform(new GlideCircleTransformation(context))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(viewHolder.friends_profile_pic);

    }

    @Override
    public int getItemCount() {
        return yourFriendsSuggestionListItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        public ImageView friends_profile_pic;
        public TextView profile_name, profile_place;

        public ViewHolder(View itemView) {
            super(itemView);
            friends_profile_pic = (ImageView) itemView.findViewById(R.id.profile_pic);
            profile_name = (TextView) itemView.findViewById(R.id.profile_name);
            profile_place = (TextView) itemView.findViewById(R.id.profile_place);
        }

        @Override
        public void onClick(View v) {

        }

        @Override
        public boolean onLongClick(View v) {
            return false;
        }
    }

}