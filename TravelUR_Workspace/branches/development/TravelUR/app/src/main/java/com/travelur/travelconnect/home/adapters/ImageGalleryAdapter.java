package com.travelur.travelconnect.home.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.travelur.R;
import com.travelur.travelconnect.home.models.Home;

/**
 * @author by Abhijit.
 */

public class ImageGalleryAdapter extends BaseAdapter {

    private Context context;
    private Home list;

    public ImageGalleryAdapter(Context context, Home list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.getImages().size();
    }

    @Override
    public Object getItem(int position) {
        return list.getImages().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView galleryImageView = new ImageView(context);
        //galleryImageView.setImageURI(Uri.parse(list.getImages().get(position)));
        Glide.with(context).load(list.getImages().get(position))
                //.thumbnail(0.5f)
                .thumbnail(Glide.with(context).load(R.drawable.loading))
                .crossFade()
                .placeholder(R.drawable.place_holder)
                //.error(R.drawable.error_icon)
                //.bitmapTransform(new GlideCircleTransformation(context))
                //.diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(galleryImageView);
        //galleryImageView.setAdjustViewBounds(true);
        galleryImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        galleryImageView.setLayoutParams(new Gallery.LayoutParams(150, 100));

        return galleryImageView;
    }
}
