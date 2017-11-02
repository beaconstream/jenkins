package com.travelur.travelconnect.home.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.travelur.R;
import com.travelur.utility.GlideCircleTransformation;

import java.util.ArrayList;
import java.util.List;

/**
 * @author by Abhijit.
 */

public class CustomGalleryAdapter extends BaseAdapter {

    private Context context;
    private List<String> images;

    public CustomGalleryAdapter(Context c, List<String> images) {
        context = c;
        this.images = images;
    }

    // returns the number of images
    public int getCount() {
        return images.size();
    }

    // returns the ID of an item
    public Object getItem(int position) {
        return position;
    }

    // returns the ID of an item
    public long getItemId(int position) {
        return position;
    }

    // returns an ImageView view
    public View getView(int position, View convertView, ViewGroup parent) {

        // create a ImageView programmatically
        ImageView imageView = new ImageView(context);
        /*imageView.setImageResource(images.get(position)); // set image in ImageView
        imageView.setLayoutParams(new Gallery.LayoutParams(200, 200)); // set ImageView param*/
        Glide.with(context)
                .load(images.get(position))
                .crossFade()
                .placeholder(R.drawable.profile_placeholder)
                //.error(R.drawable.error_icon)
                .bitmapTransform(new GlideCircleTransformation(context))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
        return imageView;
    }
}

