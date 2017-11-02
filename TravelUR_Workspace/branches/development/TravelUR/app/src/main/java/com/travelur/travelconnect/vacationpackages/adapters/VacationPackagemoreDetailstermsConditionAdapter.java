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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.travelur.R;
import com.travelur.travelconnect.vacationpackages.models.VacationPackageMoreDetailsListItem;
import com.travelur.travelconnect.vacationpackages.models.VacationPackageMoreDetailsTermsConditionListItem;
import com.travelur.utility.AppConfig;

import java.util.List;

/**
 * @author by Abhijit.
 */

public class VacationPackagemoreDetailstermsConditionAdapter extends RecyclerView.Adapter<VacationPackagemoreDetailstermsConditionAdapter.MyViewHolder> implements View.OnClickListener {
    private List<VacationPackageMoreDetailsTermsConditionListItem> moreDetailsTermsConditionList;
    private Context context;

    @Override
    public void onClick(View v) {
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public WebView overview_view;


        public MyViewHolder(View view) {
            super(view);

            overview_view = (WebView) view.findViewById(R.id.overview_view);
        }
    }


    public VacationPackagemoreDetailstermsConditionAdapter(List<VacationPackageMoreDetailsTermsConditionListItem> moreDetailsTermsConditionList, Context context) {
        this.moreDetailsTermsConditionList = moreDetailsTermsConditionList;
        this.context = context;
    }

    @Override
    public VacationPackagemoreDetailstermsConditionAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.vacationpackages_moredeatails_termscondition_item, parent, false);

        return new VacationPackagemoreDetailstermsConditionAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(VacationPackagemoreDetailstermsConditionAdapter.MyViewHolder holder, int position) {
        VacationPackageMoreDetailsTermsConditionListItem moreDetails = moreDetailsTermsConditionList.get(position);

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
        holder.overview_view.loadData(moreDetails.getTermscondition(), "text/html; charset=utf-8", "UTF-8");

    }

    @Override
    public int getItemCount() {
        return moreDetailsTermsConditionList.size();
    }
}
