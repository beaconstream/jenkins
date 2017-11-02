package com.travelur.travelconnect.vacationpackages.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.travelur.R;
import com.travelur.travelconnect.photos.models.PhotoDataModel;
import com.travelur.travelconnect.vacationpackages.models.VacationPackageMoreDetailsDetailItenaryGalleryListItem;

import java.util.List;

/**
 * @author by Abhijit.
 */

public class PhotoGalleryAdapterVertical extends RecyclerView.Adapter<PhotoGalleryAdapterVertical.RecyclerViewHolders> {
    private List<PhotoDataModel> itemList;
    private Context context;

    public PhotoGalleryAdapterVertical(Context context, List<PhotoDataModel> itemList) {
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.gallery_list_item, null);
        RecyclerViewHolders rcv = new RecyclerViewHolders(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolders holder, int position) {

       /* Glide.with(context)
                .load(itemList.get(position).getUrl())
                .placeholder(R.drawable.place_holder)
                //.override(600, 200)
                .fitCenter()
                .into(holder.countryPhoto);*/
       /* Glide.with(context)
                .load(itemList.get(position).getUrl())
                .thumbnail(Glide.with(context).load(R.drawable.loader))
                .crossFade()
                //.placeholder(R.drawable.nophotos)
                //.error(R.drawable.error_icon)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.countryPhoto);*/
        /*Glide.with(context)
                .load(itemList.get(position).getUrl())
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.countryPhoto);*/
        Glide.with(context)
                .load(itemList.get(position).getUrl())
                .override(Target.SIZE_ORIGINAL, 200)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        //progressBar.setVisibility(View.GONE);
                        return false; // important to return false so the error placeholder can be placed
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        //progressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(holder.countryPhoto);
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }

    class RecyclerViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener{

        public ImageView countryPhoto;

        public RecyclerViewHolders(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            //countryName = (TextView)itemView.findViewById(R.id.country_name);
            countryPhoto = (ImageView)itemView.findViewById(R.id.image);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(view.getContext(), "Clicked Country Position = " + getPosition(), Toast.LENGTH_SHORT).show();
        }
    }
}
