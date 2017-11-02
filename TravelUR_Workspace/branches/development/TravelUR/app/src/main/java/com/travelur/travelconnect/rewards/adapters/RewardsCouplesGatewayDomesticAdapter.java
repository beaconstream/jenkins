package com.travelur.travelconnect.rewards.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.travelur.R;
import com.travelur.travelconnect.rewards.models.Rewards;
import com.travelur.utility.AppConfig;

import java.util.List;

/**
 * @author by Abhijit.
 */

public class RewardsCouplesGatewayDomesticAdapter extends RecyclerView.Adapter<RewardsCouplesGatewayDomesticAdapter.MyViewHolder> implements View.OnClickListener {

    private List<Rewards> rewardsList;
    private Context context;

    @Override
    public void onClick(View v) {
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView count_friends, count_posts, profile_percentage, total_transactions, tour_name;
        public ImageView tour_image, checkmark1, checkmark2, checkmark3, checkmark4;

        public MyViewHolder(View view) {
            super(view);

            checkmark1 = (ImageView) view.findViewById(R.id.checkmark1);
            checkmark2 = (ImageView) view.findViewById(R.id.checkmark2);
            checkmark3 = (ImageView) view.findViewById(R.id.checkmark3);
            checkmark4 = (ImageView) view.findViewById(R.id.checkmark4);
            tour_image = (ImageView) view.findViewById(R.id.tour_image);
            tour_name = (TextView) view.findViewById(R.id.tour_name);
            count_friends = (TextView) view.findViewById(R.id.count_friends);
            count_posts = (TextView) view.findViewById(R.id.count_posts);
            profile_percentage = (TextView) view.findViewById(R.id.profile_percentage);
            total_transactions = (TextView) view.findViewById(R.id.total_transactions);
        }
    }


    public RewardsCouplesGatewayDomesticAdapter(List<Rewards> rewardsList, Context context) {
        this.rewardsList = rewardsList;
        this.context = context;
    }

    @Override
    public RewardsCouplesGatewayDomesticAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rewards_couplesgateway_domestic_item, parent, false);

        return new RewardsCouplesGatewayDomesticAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RewardsCouplesGatewayDomesticAdapter.MyViewHolder holder, int position) {

        Rewards rewards = rewardsList.get(position);
        holder.tour_image.setBackgroundResource(rewards.getTourImage());
        holder.tour_name.setText(rewards.getTourName());
        holder.count_friends.setText(rewards.getCountFriends());
        if(Integer.parseInt(rewards.getCountFriends())>= AppConfig.MIN_FRIEND_COUNT_COUPLES_GATEWAY_DOMESTIC)
        {
            holder.checkmark1.setVisibility(View.VISIBLE);
            holder.count_friends.setTextColor(context.getResources().getColor(R.color.textView_color1));
        }else{
            holder.checkmark1.setVisibility(View.INVISIBLE);
            holder.count_friends.setTextColor(context.getResources().getColor(R.color.Gray_Goose));
        }

        holder.total_transactions.setText(rewards.getTotalTransactions());
        if(Integer.parseInt(rewards.getTotalTransactions())>=AppConfig.MIN_TRANSACTION_COUNT_COUPLES_GATEWAY_DOMESTIC)
        {
            holder.checkmark2.setVisibility(View.VISIBLE);
            holder.total_transactions.setTextColor(context.getResources().getColor(R.color.textView_color1));
        }
        else{
            holder.checkmark2.setVisibility(View.INVISIBLE);
            holder.total_transactions.setTextColor(context.getResources().getColor(R.color.Gray_Goose));
        }
        holder.profile_percentage.setText(rewards.getUserpercentage());
        if(Integer.parseInt(rewards.getUserpercentage().replace("%",""))>=AppConfig.MIN_PROFILE_PERCENTAGE_COUPLES_GATEWAY_DOMESTIC)
        {
            holder.checkmark3.setVisibility(View.VISIBLE);
            holder.profile_percentage.setTextColor(context.getResources().getColor(R.color.textView_color1));
        }else{
            holder.checkmark3.setVisibility(View.INVISIBLE);
            holder.profile_percentage.setTextColor(context.getResources().getColor(R.color.Gray_Goose));
        }

        holder.count_posts.setText(rewards.getCountPosts());
        if(Integer.parseInt(rewards.getCountPosts())>=AppConfig.MIN_POST_COUNT_COUPLES_GATEWAY_DOMESTIC)
        {
            holder.checkmark4.setVisibility(View.VISIBLE);
            holder.count_posts.setTextColor(context.getResources().getColor(R.color.textView_color1));
        }else{
            holder.checkmark4.setVisibility(View.INVISIBLE);
            holder.count_posts.setTextColor(context.getResources().getColor(R.color.Gray_Goose));
        }
    }

    @Override
    public int getItemCount() {
        return rewardsList.size();
    }
}
