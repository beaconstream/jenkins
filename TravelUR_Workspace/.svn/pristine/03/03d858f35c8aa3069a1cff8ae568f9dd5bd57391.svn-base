package com.travelur.travelconnect.photos.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.travelur.R;
import com.travelur.travelconnect.photos.models.PhotoDataModel;
import com.travelur.travelconnect.photos.models.VideoDataModel;
import com.travelur.travelconnect.videos.DetailVideo;
import com.travelur.utility.AppConfig;

import java.util.List;

/**
 * @author by Abhijit.
 */

public class VideoGalleryAdapter extends RecyclerView.Adapter<VideoGalleryAdapter.MyViewHolder> {

    private Fragment selectedFragment = null;
    private FragmentManager fragmentManager = null;

    @Override
    public VideoGalleryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View photoView = inflater.inflate(R.layout.custom_item_media_layout, parent, false);
        VideoGalleryAdapter.MyViewHolder viewHolder = new VideoGalleryAdapter.MyViewHolder(photoView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(VideoGalleryAdapter.MyViewHolder holder, int position) {

        VideoDataModel spacePhoto = video.get(position);
        ImageView imageView = holder.mPhotoImageView;

        Glide.with(mContext)
                .load(spacePhoto.getThumbnail())
                .placeholder(R.drawable.place_holder)
                .into(imageView);
    }

    @Override
    public int getItemCount() {
        return (video.size());
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView mPhotoImageView;

        public MyViewHolder(View itemView) {

            super(itemView);
            mPhotoImageView = (ImageView) itemView.findViewById(R.id.iv_photo);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            int position = getAdapterPosition();
            if(position != RecyclerView.NO_POSITION) {
                VideoDataModel spaceVideo = video.get(position);
               /* Intent intent = new Intent(mContext, SpacePhotoActivity.class);
                intent.putExtra(Photo.EXTRA_GALLERY_PHOTO, spacePhoto);
                startActivity(intent);*/
                selectedFragment = DetailVideo.newInstance();
                Bundle bundle = new Bundle();
                bundle.putParcelable(DetailVideo.EXTRA_GALLERY_VIDEO, spaceVideo);
                selectedFragment.setArguments(bundle);
                intitialiseFragment(selectedFragment);
            }
        }
    }

    private List<VideoDataModel> video;
    private Context mContext;

    public VideoGalleryAdapter(Context context, List<VideoDataModel> videoPhotos, FragmentManager supportFragmentManager) {
        mContext = context;
        video = videoPhotos;
        fragmentManager = supportFragmentManager;
    }
    public void intitialiseFragment(Fragment selectedFragment){

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
        transaction.replace(R.id.contaner, selectedFragment);
        transaction.addToBackStack(AppConfig.TAG_FRAGMENT);
        transaction.commit();
    }
}
