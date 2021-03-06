package com.travelur.travelconnect.vacationpackages.adapters;

/**
 * @author by Abhijit.
 */

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
import com.travelur.travelconnect.photos.adapters.DetailPhoto;
import com.travelur.travelconnect.photos.models.PhotoDataModel;
import com.travelur.travelconnect.vacationpackages.models.VacationPackageMoreDetailsDetailItenaryGalleryListItem;
import com.travelur.utility.AppConfig;

import java.util.List;

import android.content.Context;
import android.content.Intent;
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
import com.travelur.general.BaseFragment;
import com.travelur.travelconnect.photos.Photo;
import com.travelur.travelconnect.photos.models.PhotoDataModel;
import com.travelur.travelconnect.settings.Setting;
import com.travelur.utility.AppConfig;

import java.util.List;

/**
 * @author  by Abhijit.
 */

public class PhotoGalleryAdapter extends RecyclerView.Adapter<PhotoGalleryAdapter.MyViewHolder>  {

    private Fragment selectedFragment = null;
    private FragmentManager fragmentManager = null;

    @Override
    public PhotoGalleryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View photoView = inflater.inflate(R.layout.custom_item_media_layout, parent, false);
        PhotoGalleryAdapter.MyViewHolder viewHolder = new PhotoGalleryAdapter.MyViewHolder(photoView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PhotoGalleryAdapter.MyViewHolder holder, int position) {

        PhotoDataModel spacePhoto = photos.get(position);
        ImageView imageView = holder.mPhotoImageView;

        Glide.with(mContext)
                .load(spacePhoto.getUrl())
                .placeholder(R.drawable.place_holder)
                .into(imageView);
    }

    @Override
    public int getItemCount() {
        return (photos.size());
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView mPhotoImageView;

        public MyViewHolder(View itemView) {

            super(itemView);
            mPhotoImageView = (ImageView) itemView.findViewById(R.id.iv_photo);
            //itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            int position = getAdapterPosition();
            if(position != RecyclerView.NO_POSITION) {
                PhotoDataModel spacePhoto = photos.get(position);
               /* Intent intent = new Intent(mContext, SpacePhotoActivity.class);
                intent.putExtra(Photo.EXTRA_GALLERY_PHOTO, spacePhoto);
                startActivity(intent);*/
                selectedFragment = DetailPhoto.newInstance();
                Bundle bundle = new Bundle();
                bundle.putParcelable(DetailPhoto.EXTRA_GALLERY_PHOTO, spacePhoto);
                selectedFragment.setArguments(bundle);
                intitialiseFragment(selectedFragment);
            }
        }
    }

    private List<PhotoDataModel> photos;
    private Context mContext;

    public PhotoGalleryAdapter(Context context, List<PhotoDataModel> spacePhotos, FragmentManager supportFragmentManager) {
        mContext = context;
        photos = spacePhotos;
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

