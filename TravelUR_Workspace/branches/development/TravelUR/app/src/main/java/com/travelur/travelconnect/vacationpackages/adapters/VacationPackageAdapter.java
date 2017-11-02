package com.travelur.travelconnect.vacationpackages.adapters;

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
import com.travelur.travelconnect.rewards.adapters.RewardsCouplesGatewayDomesticAdapter;
import com.travelur.travelconnect.rewards.models.Rewards;
import com.travelur.travelconnect.vacationpackages.models.VacationPackageListItem;
import com.travelur.utility.AppConfig;
import com.travelur.utility.GlideCircleTransformation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.security.AccessController.getContext;

/**
 * @author by Abhijit.
 */

public class VacationPackageAdapter extends RecyclerView.Adapter<VacationPackageAdapter.MyViewHolder> implements View.OnClickListener {

    private List<VacationPackageListItem> vacationPackagesList;
    private Context context;

    @Override
    public void onClick(View v) {
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView package_name, package_price, flight_layout,hotel_layout, transport_layout, meals_layout;
        public ImageView image_url;

        public MyViewHolder(View view) {
            super(view);

            image_url = (ImageView) view.findViewById(R.id.imageView);
            package_price = (TextView) view.findViewById(R.id.price);
            package_name = (TextView) view.findViewById(R.id.name);

            flight_layout = (TextView) view.findViewById(R.id.flights_layout);
            hotel_layout = (TextView) view.findViewById(R.id.hotels_layout);
            transport_layout = (TextView) view.findViewById(R.id.transport_layout);
            meals_layout = (TextView) view.findViewById(R.id.meals_layout);

        }
    }

    public VacationPackageAdapter(List<VacationPackageListItem> vacationPackagesList, Context context) {
        this.vacationPackagesList = vacationPackagesList;
        this.context = context;
    }

    @Override
    public VacationPackageAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.vacationpackage, parent, false);

        return new VacationPackageAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(VacationPackageAdapter.MyViewHolder holder, int position) {

        VacationPackageListItem vacationPackageListItem = vacationPackagesList.get(position);
        if(!vacationPackageListItem.getImage_url().isEmpty()){
            Glide.with(context)
                    .load(vacationPackageListItem.getImage_url())
                    .thumbnail(Glide.with(context).load(R.drawable.loader))
                    .crossFade()
                    //.placeholder(R.drawable.nophotos)
                    //.error(R.drawable.error_icon)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.image_url);
        }
        holder.image_url.setScaleType(ImageView.ScaleType.FIT_XY);
        holder.package_price.setText(vacationPackageListItem.getPackage_price());
        holder.package_name.setText(vacationPackageListItem.getPackege_name());
        List<String> packegeIncludesList = Arrays.asList(vacationPackageListItem.getPackage_includes().split("\\s*,\\s*"));
        if(!packegeIncludesList.isEmpty()){
            holder.flight_layout.setVisibility(View.GONE);
            holder.hotel_layout.setVisibility(View.GONE);
            holder.transport_layout.setVisibility(View.GONE);
            holder.meals_layout.setVisibility(View.GONE);
            for(int i=0;i<packegeIncludesList.size();i++){

                switch (packegeIncludesList.get(i)){
                    case "flight":
                        holder.flight_layout.setVisibility(View.VISIBLE);
                        break;
                    case "hotel":
                        holder.hotel_layout.setVisibility(View.VISIBLE);
                        break;
                    case "transport":
                        holder.transport_layout.setVisibility(View.VISIBLE);
                        break;
                    case "meals":
                        holder.meals_layout.setVisibility(View.VISIBLE);
                        break;

                }
               /* if(packegeIncludesList.get(i).equals("flight")){

                    holder.flight_layout.setVisibility(View.VISIBLE);
                }else{
                    holder.flight_layout.setVisibility(View.GONE);
                }
                if(packegeIncludesList.get(i).equals("hotel")){
                    holder.hotel_layout.setVisibility(View.VISIBLE);
                }else
                    holder.hotel_layout.setVisibility(View.GONE);
                if(packegeIncludesList.get(i).equals("transport")){
                    holder.transport_layout.setVisibility(View.VISIBLE);
                }else
                    holder.transport_layout.setVisibility(View.GONE);
                if(packegeIncludesList.get(i).equals("meals")){
                    holder.meals_layout.setVisibility(View.VISIBLE);
                }else
                    holder.meals_layout.setVisibility(View.GONE);*/
            }

        }

    }

    @Override
    public int getItemCount() {
        return vacationPackagesList.size();
    }
}
