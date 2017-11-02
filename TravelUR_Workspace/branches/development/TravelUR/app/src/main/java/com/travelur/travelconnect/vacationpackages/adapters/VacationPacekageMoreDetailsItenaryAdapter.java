package com.travelur.travelconnect.vacationpackages.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.travelur.R;
import com.travelur.travelconnect.vacationpackages.models.VacationPackageMoreDetailsDetaileItenaryListItem;
import com.travelur.travelconnect.vacationpackages.models.VacationPackageMoreDetailsListItem;
import com.travelur.utility.AppConfig;

import org.w3c.dom.Text;

import java.util.List;

/**
 * @author by Abhijit.
 */

public class VacationPacekageMoreDetailsItenaryAdapter extends RecyclerView.Adapter<VacationPacekageMoreDetailsItenaryAdapter.MyViewHolder> implements View.OnClickListener{

    private List<VacationPackageMoreDetailsDetaileItenaryListItem> moreDetailsItinerary_descriptionList;
    private Context context;

    @Override
    public void onClick(View v) {
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public WebView overview_view;
        private TextView day, place;

        public MyViewHolder(View view) {
            super(view);

           /* day = (TextView) view.findViewById(R.id.day);
            place = (TextView) view.findViewById(R.id.place);*/
            overview_view = (WebView) view.findViewById(R.id.overview_view);
        }
    }


    public VacationPacekageMoreDetailsItenaryAdapter(List<VacationPackageMoreDetailsDetaileItenaryListItem> moreDetailsList, Context context) {
        this.moreDetailsItinerary_descriptionList = moreDetailsList;
        this.context = context;
    }

    @Override
    public VacationPacekageMoreDetailsItenaryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.vacationpackages_moredetails_detaileditenary_item, parent, false);

        return new VacationPacekageMoreDetailsItenaryAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(VacationPacekageMoreDetailsItenaryAdapter.MyViewHolder holder, int position) {
        VacationPackageMoreDetailsDetaileItenaryListItem moreDetails = moreDetailsItinerary_descriptionList.get(position);

        /*holder.day.setText("Day "+moreDetails.getDay()+":");
        holder.place.setText(moreDetails.getPlace());*/
        holder.overview_view.getSettings().setTextSize(WebSettings.TextSize.LARGEST);
        holder.overview_view.getSettings().setJavaScriptEnabled(true);
        holder.overview_view.getSettings().setUseWideViewPort(true);
        holder.overview_view.getSettings().setLoadWithOverviewMode(true);
        //holder.overview_view.getSettings().setBuiltInZoomControls(true);
        holder.overview_view.getSettings().setPluginState(WebSettings.PluginState.ON);
        holder.overview_view.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(context, description, Toast.LENGTH_SHORT).show();
            }

        });
        holder.overview_view.loadData(moreDetails.getItinerary_description(), "text/html; charset=utf-8", "UTF-8");

    }

    @Override
    public int getItemCount() {
        return moreDetailsItinerary_descriptionList.size();
    }
}
