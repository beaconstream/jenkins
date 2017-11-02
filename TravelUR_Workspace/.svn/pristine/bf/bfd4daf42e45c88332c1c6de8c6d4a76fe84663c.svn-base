package com.travelur.travelconnect.home.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.travelur.R;
import com.travelur.travelconnect.home.models.Home;
import com.travelur.utility.GlideCircleTransformation;

import java.util.List;

/**
 * @author by Abhijit.
 */

public class CommentAdapter  extends BaseAdapter {
    Context context;
    List<Home> homeListItem;
    int comment_position;
    public CommentAdapter(Context context, List<Home> homeListItem, int position){
        this.context=context;
        this.homeListItem = homeListItem;
        this.comment_position = position;
    }
    @Override
    public int getCount() {
        return homeListItem.get(comment_position).getCommentListItems().size();
    }

    @Override
    public Object getItem(int position) {
        return homeListItem.get(comment_position).getCommentListItems().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view= inflater.inflate(R.layout.comment_list_item,null);

        ImageView profile_icon= (ImageView) view.findViewById(R.id.profile_pic);
        Glide.with(context).load(homeListItem.get(comment_position).getCommentListItems().get(position).getProfile_pic())
                .thumbnail(0.5f)
                .crossFade()
                .placeholder(R.drawable.profile_placeholder)
                //.error(R.drawable.error_icon)
                .bitmapTransform(new GlideCircleTransformation(context))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(profile_icon);
        Typeface font = Typeface.createFromAsset(context.getAssets(), "fontawesome-webfont.ttf");
        TextView Name = (TextView) view.findViewById(R.id.name);
        TextView message = (TextView) view.findViewById(R.id.message);
        TextView date = (TextView) view.findViewById(R.id.date);
        Name.setText(homeListItem.get(comment_position).getCommentListItems().get(position).getName());
        message.setText(homeListItem.get(comment_position).getCommentListItems().get(position).getMessage());
        date.setText(homeListItem.get(comment_position).getCommentListItems().get(position).getTime());
        return view;
    }

}
