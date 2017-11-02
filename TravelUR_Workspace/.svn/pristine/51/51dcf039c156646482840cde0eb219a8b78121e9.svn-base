package com.travelur.travelconnect.gallery.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.travelur.R;
import com.travelur.travelconnect.gallery.model.Media;

import java.io.File;
import java.util.ArrayList;

import static com.travelur.utility.AppConfig.MEDIA_TYPE_PHOTOS;
import static com.travelur.utility.AppConfig.MEDIA_TYPE_PHOTOS_SINGLE_SELECTION;

/*
 * @author by Abhijit .
 */

public class GridViewAdapter extends ArrayAdapter<Media> {

    private Context mContext;
    private int mediaType;
    private ArrayList<Media> mMediaArrayList = new ArrayList<>();

    public GridViewAdapter(Context context, ArrayList<Media> mediaArrayList, int mediaType) {
        super(context, R.layout.media_tile, mediaArrayList);
        this.mContext = context;
        this.mMediaArrayList = mediaArrayList;
        this.mediaType = mediaType;

    }

    @Override
    public int getCount() {
        return mMediaArrayList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {

            convertView = LayoutInflater.from(mContext).inflate(R.layout.media_tile, parent, false);
            viewHolder.selecTickMark = (ImageView) convertView.findViewById(R.id.tickImage);
            viewHolder.videoIcon = (ImageView) convertView.findViewById(R.id.videoIcon);
            viewHolder.mediaImage = (ImageView) convertView.findViewById(R.id.media_image);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Media media = mMediaArrayList.get(position);
        viewHolder.media = media;
        String absolutePathOfMedia = media.path;
        if (mediaType == MEDIA_TYPE_PHOTOS || mediaType == MEDIA_TYPE_PHOTOS_SINGLE_SELECTION) {
            viewHolder.videoIcon.setVisibility(View.GONE);
            Glide.with(mContext).load("file://" + absolutePathOfMedia)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .centerCrop()
                    .fitCenter()
                    .into(viewHolder.mediaImage);
        } else {
            viewHolder.videoIcon.setVisibility(View.VISIBLE);
            Bitmap bmThumbnail = ThumbnailUtils.createVideoThumbnail("file://" + absolutePathOfMedia, MediaStore.Images.Thumbnails.MICRO_KIND);;
            Glide.with(mContext).load(Uri.fromFile(new File(absolutePathOfMedia)))
                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
                    .centerCrop()
                    .fitCenter()
                    .into(viewHolder.mediaImage);
        }


        if (media.isSelected) {
            viewHolder.selecTickMark.setVisibility(View.VISIBLE);
        } else {
            viewHolder.selecTickMark.setVisibility(View.GONE);
        }

        return convertView;
    }



    public static class ViewHolder {
        public ImageView selecTickMark, videoIcon;
        public ImageView mediaImage;
        public Media media;
    }


}
