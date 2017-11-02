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

public class RewardsWorldTourAdapter  extends RecyclerView.Adapter<RewardsWorldTourAdapter.MyViewHolder> implements View.OnClickListener {

    private List<Rewards> rewardsList;
    private Context context;

    @Override
    public void onClick(View v) {
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView count_friends, count_posts, profile_percentage, total_transactions, tour_name;
        public ImageView tour_image, checkmark1, checkmark2, checkmark3, checkmark4;
        public TextView text_count_friends, text_count_posts, text_profile_percentage, text_total_transactions;

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

            text_count_friends = (TextView) view.findViewById(R.id.text_count_friends);
            text_count_posts = (TextView) view.findViewById(R.id.text_post_count);
            text_profile_percentage = (TextView) view.findViewById(R.id.text_profile_percentage);
            text_total_transactions = (TextView) view.findViewById(R.id.text_total_transactions);
        }
    }


    public RewardsWorldTourAdapter(List<Rewards> rewardsList, Context context) {
        this.rewardsList = rewardsList;
        this.context = context;
    }

    @Override
    public RewardsWorldTourAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rewards_worldtour_item, parent, false);

        return new RewardsWorldTourAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RewardsWorldTourAdapter.MyViewHolder holder, int position) {
        Rewards rewards = rewardsList.get(position);

        holder.tour_image.setBackgroundResource(rewards.getTourImage());
        holder.tour_name.setText(rewards.getTourName());
        holder.count_friends.setText(rewards.getCountFriends());
        if(position == 0){

            holder.text_count_friends.setText(R.string.new_friend);
            holder.text_total_transactions.setText(R.string.make_bookings);
            holder.text_profile_percentage.setText(R.string.complete_profile);
            holder.text_count_posts.setText(R.string.action_quarter_contributor);
            if(Integer.parseInt(rewards.getCountFriends())>= AppConfig.MIN_FRIEND_COUNT_WORLD_TOUR)
            {
                holder.checkmark1.setVisibility(View.VISIBLE);
                holder.count_friends.setTextColor(context.getResources().getColor(R.color.textView_color1));
            }else{
                holder.checkmark1.setVisibility(View.INVISIBLE);
                holder.count_friends.setTextColor(context.getResources().getColor(R.color.Gray_Goose));
            }

            holder.total_transactions.setText(rewards.getTotalTransactions());
            if(Integer.parseInt(rewards.getTotalTransactions())>=AppConfig.MIN_TRANSACTION_COUNT_WORLD_TOUR)
            {
                holder.checkmark2.setVisibility(View.VISIBLE);
                holder.total_transactions.setTextColor(context.getResources().getColor(R.color.textView_color1));
            }
            else{
                holder.checkmark2.setVisibility(View.INVISIBLE);
                holder.total_transactions.setTextColor(context.getResources().getColor(R.color.Gray_Goose));
            }
            holder.profile_percentage.setText(rewards.getUserpercentage());
            if(Integer.parseInt(rewards.getUserpercentage().replace("%",""))>=AppConfig.MIN_PROFILE_PERCENTAGE_WORLD_TOUR)
            {
                holder.checkmark3.setVisibility(View.VISIBLE);
                holder.profile_percentage.setTextColor(context.getResources().getColor(R.color.textView_color1));
            }else{
                holder.checkmark3.setVisibility(View.INVISIBLE);
                holder.profile_percentage.setTextColor(context.getResources().getColor(R.color.Gray_Goose));
            }

            holder.count_posts.setText(rewards.getCountPosts());
            if(Integer.parseInt(rewards.getCountPosts())>=AppConfig.MIN_POST_COUNT_WORLD_TOUR)
            {
                holder.checkmark4.setVisibility(View.VISIBLE);
                holder.count_posts.setTextColor(context.getResources().getColor(R.color.textView_color1));
            }else{
                holder.checkmark4.setVisibility(View.INVISIBLE);
                holder.count_posts.setTextColor(context.getResources().getColor(R.color.Gray_Goose));
            }
        }
        if(position == 1){
            holder.text_count_friends.setText(R.string.new_friend1);
            holder.text_total_transactions.setText(R.string.make_bookings1);
            holder.text_profile_percentage.setText(R.string.complete_profile1);
            holder.text_count_posts.setText(R.string.action_quarter_contributor1);
            if(Integer.parseInt(rewards.getCountFriends())>= AppConfig.MIN_FRIEND_COUNT_EUROPEAN_TOUR)
            {
                holder.checkmark1.setVisibility(View.VISIBLE);
                holder.count_friends.setTextColor(context.getResources().getColor(R.color.textView_color1));
            }else{
                holder.checkmark1.setVisibility(View.INVISIBLE);
                holder.count_friends.setTextColor(context.getResources().getColor(R.color.Gray_Goose));
            }

            holder.total_transactions.setText(rewards.getTotalTransactions());
            if(Integer.parseInt(rewards.getTotalTransactions())>=AppConfig.MIN_TRANSACTION_COUNT_EUROPEAN_TOUR)
            {
                holder.checkmark2.setVisibility(View.VISIBLE);
                holder.total_transactions.setTextColor(context.getResources().getColor(R.color.textView_color1));
            }
            else{
                holder.checkmark2.setVisibility(View.INVISIBLE);
                holder.total_transactions.setTextColor(context.getResources().getColor(R.color.Gray_Goose));
            }
            holder.profile_percentage.setText(rewards.getUserpercentage());
            if(Integer.parseInt(rewards.getUserpercentage().replace("%",""))>=AppConfig.MIN_PROFILE_PERCENTAGE_EUROPEAN_TOUR)
            {
                holder.checkmark3.setVisibility(View.VISIBLE);
                holder.profile_percentage.setTextColor(context.getResources().getColor(R.color.textView_color1));
            }else{
                holder.checkmark3.setVisibility(View.INVISIBLE);
                holder.profile_percentage.setTextColor(context.getResources().getColor(R.color.Gray_Goose));
            }

            holder.count_posts.setText(rewards.getCountPosts());
            if(Integer.parseInt(rewards.getCountPosts())>=AppConfig.MIN_POST_COUNT_EUROPEAN_TOUR)
            {
                holder.checkmark4.setVisibility(View.VISIBLE);
                holder.count_posts.setTextColor(context.getResources().getColor(R.color.textView_color1));
            }else{
                holder.checkmark4.setVisibility(View.INVISIBLE);
                holder.count_posts.setTextColor(context.getResources().getColor(R.color.Gray_Goose));
            }
        }
        if(position == 2){
            holder.text_count_friends.setText(R.string.new_friend2);
            holder.text_total_transactions.setText(R.string.make_bookings2);
            holder.text_profile_percentage.setText(R.string.complete_profile2);
            holder.text_count_posts.setText(R.string.action_quarter_contributor2);
            if(Integer.parseInt(rewards.getCountFriends())>= AppConfig.MIN_FRIEND_COUNT_COUPLES_GATEWAY_INTERNATIONAL)
            {
                holder.checkmark1.setVisibility(View.VISIBLE);
                holder.count_friends.setTextColor(context.getResources().getColor(R.color.textView_color1));
            }else{
                holder.checkmark1.setVisibility(View.INVISIBLE);
                holder.count_friends.setTextColor(context.getResources().getColor(R.color.Gray_Goose));
            }

            holder.total_transactions.setText(rewards.getTotalTransactions());
            if(Integer.parseInt(rewards.getTotalTransactions())>=AppConfig.MIN_TRANSACTION_COUNT_COUPLES_GATEWAY_INTERNATIONAL)
            {
                holder.checkmark2.setVisibility(View.VISIBLE);
                holder.total_transactions.setTextColor(context.getResources().getColor(R.color.textView_color1));
            }
            else{
                holder.checkmark2.setVisibility(View.INVISIBLE);
                holder.total_transactions.setTextColor(context.getResources().getColor(R.color.Gray_Goose));
            }
            holder.profile_percentage.setText(rewards.getUserpercentage());
            if(Integer.parseInt(rewards.getUserpercentage().replace("%",""))>=AppConfig.MIN_PROFILE_PERCENTAGE_COUPLES_GATEWAY_INTERNATIONAL)
            {
                holder.checkmark3.setVisibility(View.VISIBLE);
                holder.profile_percentage.setTextColor(context.getResources().getColor(R.color.textView_color1));
            }else{
                holder.checkmark3.setVisibility(View.INVISIBLE);
                holder.profile_percentage.setTextColor(context.getResources().getColor(R.color.Gray_Goose));
            }

            holder.count_posts.setText(rewards.getCountPosts());
            if(Integer.parseInt(rewards.getCountPosts())>=AppConfig.MIN_POST_COUNT_COUPLES_GATEWAY_INTERNATIONAL)
            {
                holder.checkmark4.setVisibility(View.VISIBLE);
                holder.count_posts.setTextColor(context.getResources().getColor(R.color.textView_color1));
            }else{
                holder.checkmark4.setVisibility(View.INVISIBLE);
                holder.count_posts.setTextColor(context.getResources().getColor(R.color.Gray_Goose));
            }
        }
        if(position == 3){
            holder.text_count_friends.setText(R.string.new_friend3);
            holder.text_total_transactions.setText(R.string.make_bookings3);
            holder.text_profile_percentage.setText(R.string.complete_profile3);
            holder.text_count_posts.setText(R.string.action_quarter_contributor3);
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
        if(position == 4){
            holder.text_count_friends.setText(R.string.new_friend4);
            holder.text_total_transactions.setText(R.string.make_bookings4);
            holder.text_profile_percentage.setText(R.string.complete_profile4);
            holder.text_count_posts.setText(R.string.action_quarter_contributor4);
            if(Integer.parseInt(rewards.getCountFriends())>= AppConfig.MIN_FRIEND_COUNT_KUN_GATEWAY)
            {
                holder.checkmark1.setVisibility(View.VISIBLE);
                holder.count_friends.setTextColor(context.getResources().getColor(R.color.textView_color1));
            }else{
                holder.checkmark1.setVisibility(View.INVISIBLE);
                holder.count_friends.setTextColor(context.getResources().getColor(R.color.Gray_Goose));
            }

            holder.total_transactions.setText(rewards.getTotalTransactions());
            if(Integer.parseInt(rewards.getTotalTransactions())>=AppConfig.MIN_TRANSACTION_COUNT_KUN_GATEWAY)
            {
                holder.checkmark2.setVisibility(View.VISIBLE);
                holder.total_transactions.setTextColor(context.getResources().getColor(R.color.textView_color1));
            }
            else{
                holder.checkmark2.setVisibility(View.INVISIBLE);
                holder.total_transactions.setTextColor(context.getResources().getColor(R.color.Gray_Goose));
            }
            holder.profile_percentage.setText(rewards.getUserpercentage());
            if(Integer.parseInt(rewards.getUserpercentage().replace("%",""))>=AppConfig.MIN_PROFILE_PERCENTAGE_KUN_GATEWAY)
            {
                holder.checkmark3.setVisibility(View.VISIBLE);
                holder.profile_percentage.setTextColor(context.getResources().getColor(R.color.textView_color1));
            }else{
                holder.checkmark3.setVisibility(View.INVISIBLE);
                holder.profile_percentage.setTextColor(context.getResources().getColor(R.color.Gray_Goose));
            }

            holder.count_posts.setText(rewards.getCountPosts());
            if(Integer.parseInt(rewards.getCountPosts())>=AppConfig.MIN_POST_COUNT_KUN_GATEWAY)
            {
                holder.checkmark4.setVisibility(View.VISIBLE);
                holder.count_posts.setTextColor(context.getResources().getColor(R.color.textView_color1));
            }else{
                holder.checkmark4.setVisibility(View.INVISIBLE);
                holder.count_posts.setTextColor(context.getResources().getColor(R.color.Gray_Goose));
            }
        }
        if(position == 5){
            holder.text_count_friends.setText(R.string.new_friend5);
            holder.text_total_transactions.setText(R.string.make_bookings5);
            holder.text_profile_percentage.setText(R.string.complete_profile5);
            holder.text_count_posts.setText(R.string.action_quarter_contributor5);
            if(Integer.parseInt(rewards.getCountFriends())>= AppConfig.MIN_FRIEND_COUNT_LOCAL_SPLENDOR_GATEWAY)
            {
                holder.checkmark1.setVisibility(View.VISIBLE);
                holder.count_friends.setTextColor(context.getResources().getColor(R.color.textView_color1));
            }else{
                holder.checkmark1.setVisibility(View.INVISIBLE);
                holder.count_friends.setTextColor(context.getResources().getColor(R.color.Gray_Goose));
            }

            holder.total_transactions.setText(rewards.getTotalTransactions());
            if(Integer.parseInt(rewards.getTotalTransactions())>=AppConfig.MIN_TRANSACTION_COUNT_LOCAL_SPLENDOR_GATEWAY)
            {
                holder.checkmark2.setVisibility(View.VISIBLE);
                holder.total_transactions.setTextColor(context.getResources().getColor(R.color.textView_color1));
            }
            else{
                holder.checkmark2.setVisibility(View.INVISIBLE);
                holder.total_transactions.setTextColor(context.getResources().getColor(R.color.Gray_Goose));
            }
            holder.profile_percentage.setText(rewards.getUserpercentage());
            if(Integer.parseInt(rewards.getUserpercentage().replace("%",""))>=AppConfig.MIN_PROFILE_PERCENTAGE_LOCAL_SPLENDOR_GATEWAY)
            {
                holder.checkmark3.setVisibility(View.VISIBLE);
                holder.profile_percentage.setTextColor(context.getResources().getColor(R.color.textView_color1));
            }else{
                holder.checkmark3.setVisibility(View.INVISIBLE);
                holder.profile_percentage.setTextColor(context.getResources().getColor(R.color.Gray_Goose));
            }

            holder.count_posts.setText(rewards.getCountPosts());
            if(Integer.parseInt(rewards.getCountPosts())>=AppConfig.MIN_POST_COUNT_LOCAL_SPLENDOR_GATEWAY)
            {
                holder.checkmark4.setVisibility(View.VISIBLE);
                holder.count_posts.setTextColor(context.getResources().getColor(R.color.textView_color1));
            }else{
                holder.checkmark4.setVisibility(View.INVISIBLE);
                holder.count_posts.setTextColor(context.getResources().getColor(R.color.Gray_Goose));
            }
        }
    }

    @Override
    public int getItemCount() {
        return rewardsList.size();
    }
}