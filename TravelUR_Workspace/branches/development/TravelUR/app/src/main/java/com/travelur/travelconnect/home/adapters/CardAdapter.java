package com.travelur.travelconnect.home.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.travelur.R;
import com.travelur.backend.VolleyRequest;
import com.travelur.travelconnect.home.models.Home;
import com.travelur.travelconnect.home.models.ItemObjects;
import com.travelur.utility.AppConfig;
import com.travelur.utility.GlideCircleTransformation;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author by Abhijit
 */

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> implements Filterable, View.OnClickListener {

    protected MediaController mediaControls;
    //private ProgressDialog progressDialog;
    private Context context;
    private List<Home> items;
    private int videoPosition;
    private int selectedImagePosition;
    private Typeface font, font_Raleway_Light, font_Raleway_ExtraLight, font_Raleway_Medium, font_Raleway_Thin, font_OpenSans_Light, font_Raleway_SemiBold;
    private JSONObject jsonObject;
    private PopupWindow popWindow;
    private StaggeredGridLayoutManager gaggeredGridLayoutManager;
    private PopupWindow popup;
    /*private ImageView edit_profile_pic;
    private TextView profile_name;
    private TextView place;
    private TextView post_count;
    private TextView photos_count;
    private TextView videos_count;
    private TextView friends_count;
    private TextView groups_count;
    private ProgressBar profile_percentage;*/

    public CardAdapter(List<Home> items, Context context){
        super();
        //Getting all the Home_List
        this.items = items;
        this.context = context;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_card, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Home list =  items.get(position);

        if(null!=AppConfig.edit_profile_pic && !AppConfig.home_List.isEmpty()){
            Glide.with(context)
                    .load(AppConfig.home_List.get(0).getAccount_setting_List().get(0).getProfile_pic())
                    .crossFade()
                    .placeholder(R.drawable.profile_placeholder)
                    //.error(R.drawable.error_icon)
                    .bitmapTransform(new GlideCircleTransformation(context))
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(AppConfig.edit_profile_pic);

            AppConfig.profile_name.setText(AppConfig.home_List.get(0).getAccount_setting_List().get(0).getFirst_name()+" "+AppConfig.home_List.get(0).getAccount_setting_List().get(0).getLast_name());
            AppConfig.place.setText(AppConfig.home_List.get(0).getAccount_setting_List().get(0).getCity()+", "+AppConfig.home_List.get(0).getAccount_setting_List().get(0).getState());
            AppConfig.post_count.setText(AppConfig.home_List.get(0).getProfile_details_List().get(0).getPostcount());
            AppConfig.friends_count.setText(AppConfig.home_List.get(0).getProfile_details_List().get(0).getFriendscount());
            AppConfig.photos_count.setText(AppConfig.home_List.get(0).getProfile_details_List().get(0).getPhotoscount());
            AppConfig.videos_count.setText(AppConfig.home_List.get(0).getProfile_details_List().get(0).getVideoscount());
            AppConfig.groups_count.setText(AppConfig.home_List.get(0).getProfile_details_List().get(0).getGroupcount());
            //profile_percentage.setProgress(Integer.parseInt(profile_per.replace("%","")));
            AppConfig.profile_percentage_progressbar.setProgress(AppConfig.home_List.get(0).getProfile_details_List().get(0).getProfilepercentage());
        }


        videoPosition = 0;
        selectedImagePosition = position;
        Glide.with(context).load(list.getUser_image())
                .thumbnail(0.5f)
                .crossFade()
                .placeholder(R.drawable.profile_placeholder)
                //.error(R.drawable.error_icon)
                .bitmapTransform(new GlideCircleTransformation(context))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.user_image);
        holder.first_name.setText(list.getFirst_name());
        holder.created_date.setText(list.getCreated_date());
        if (list.getMessage()!="")
        {
            holder.message.setText(list.getMessage());
        }else{
            holder.message.setVisibility(View.GONE);
        }
        if (!list.getVideos().get(0).isEmpty())
        {
            holder.uploaded_video.setVisibility(View.VISIBLE);
            onSwitch_UploadVideo(holder, list);
        }else{
            holder.uploaded_video.setVisibility(View.GONE);
        }
        holder.comments_count.setText("View all " + list.getComments_count());
        holder.likes_count.setText(list.getLikes());

        holder.like.setTypeface(font);
        holder.comment.setTypeface(font);

        holder.first_name.setTypeface(font_Raleway_SemiBold);
        holder.created_date.setTypeface(font_OpenSans_Light);
        holder.message.setTypeface(font_OpenSans_Light);

        if(!list.getUser_id().equalsIgnoreCase(AppConfig.getUser_id())){
            holder.edit.setVisibility(View.GONE);
        }else
            holder.edit.setVisibility(View.VISIBLE);

        holder.edit.setOnClickListener(this);
        holder.like_filled.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*jsonObject = new JSONObject();
                try {
                    if(null!= AppConfig.getUser_id())
                    {
                        jsonObject.put("type", "1");
                        jsonObject.put("user_id", list.getUser_id());
                        jsonObject.put("id", list.getPost_id());
                        VolleyRequest volleyRequest = new VolleyRequest(context);
                        volleyRequest.signedInHome_Like(jsonObject);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }*/
            }
        });
        holder.comment_filled.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        holder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

       /* holder.leftArrowImageView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (selectedImagePosition > 0) {
                    --selectedImagePosition;

                }

                holder.gallery.setSelection(selectedImagePosition, false);
            }
        });

        holder.rightArrowImageView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (selectedImagePosition < list.getImages().size() - 1) {
                    ++selectedImagePosition;

                }

                holder.gallery.setSelection(selectedImagePosition, false);

            }
        });*/

        // ClickListener for NEXT button
        // When clicked on Button ImageSwitcher will switch between Images
        // The current Image will go OUT and next Image  will come in with specified animation
        /*holder.btnNext.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                holder.currentIndex++;
                //  Check If index reaches maximum then reset it
                if (holder.currentIndex == list.getImages().size())
                    holder.currentIndex = 0;
                Glide.with(context).load(list.getImages().get(holder.currentIndex)).into((ImageView) holder.uploadedImageSwitcher.getCurrentView());
            }
        });*/
        holder.recyclerView.setHasFixedSize(true);
       /* gaggeredGridLayoutManager = new StaggeredGridLayoutManager(3, 1);
        holder.recyclerView.setLayoutManager(gaggeredGridLayoutManager);*/
        PhotoGalleryAdapter rcAdapter = new PhotoGalleryAdapter(context, AppConfig.gaggeredList);
        holder.recyclerView.setAdapter(rcAdapter);
        if(list.getImages().get(0)!="")
        {
            holder.recyclerView.setVisibility(View.VISIBLE);
            if(!AppConfig.gaggeredList.isEmpty())
                AppConfig.gaggeredList.clear();


          /*  gaggeredGridLayoutManager = new StaggeredGridLayoutManager(3, 1);
            holder.recyclerView.setLayoutManager(gaggeredGridLayoutManager);*/

            for(int i=0; i<list.getImages().size(); i++){
                if(list.getImages().size()==1){
                    gaggeredGridLayoutManager = new StaggeredGridLayoutManager(1, 1);
                    holder.recyclerView.setLayoutManager(gaggeredGridLayoutManager);
                }else if(list.getImages().size()==2){
                    gaggeredGridLayoutManager = new StaggeredGridLayoutManager(2, 1);
                    holder.recyclerView.setLayoutManager(gaggeredGridLayoutManager);
                }else if(list.getImages().size()==3){
                    gaggeredGridLayoutManager = new StaggeredGridLayoutManager(3, 1);
                    holder.recyclerView.setLayoutManager(gaggeredGridLayoutManager);
                }else if(list.getImages().size()>=4){
                    gaggeredGridLayoutManager = new StaggeredGridLayoutManager(4, 1);
                    holder.recyclerView.setLayoutManager(gaggeredGridLayoutManager);
                }

                AppConfig.gaggeredList.add(new ItemObjects(list.getImages().get(i)));

            }
            rcAdapter.notifyDataSetChanged();

            //if(holder.Position<list.getImages().size()){
            // Set the ViewFactory of the ImageSwitcher that will create ImageView object when asked
            // Declare in and out animations and load them using AnimationUtils class
           /* Animation in = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            Animation out = AnimationUtils.loadAnimation(context, android.R.anim.slide_out_right);
            // set the animation type to ImageSwitcher
            holder.uploadedImageSwitcher.setInAnimation(in);
            holder.uploadedImageSwitcher.setOutAnimation(out);
            Glide.with(context).load(list.getImages().get(holder.Position)).into((ImageView) holder.uploadedImageSwitcher.getCurrentView());

            holder.gallery.setAdapter(new ImageGalleryAdapter(context, list));

            if (list.getImages().size() > 0 && selectedImagePosition<list.getImages().size()) {

                holder.gallery.setSelection(selectedImagePosition, false);

            }

            *//*if (list.getImages().size() == 1) {

                holder.leftArrowImageView.setText("");
                holder.leftArrowImageView.setEnabled(false);
                holder.rightArrowImageView.setText("");
                holder.rightArrowImageView.setEnabled(false);
            }*//*

            holder.gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                public void onItemClick(AdapterView<?> arg0, View arg1, int pos,
                                        long arg3) {
                    selectedImagePosition = pos;

                   *//* if (selectedImagePosition > 0 && selectedImagePosition < list.getImages().size() - 1) {

                        holder.leftArrowImageView.setTypeface(font);
                        holder.leftArrowImageView.setEnabled(true);
                        holder.rightArrowImageView.setTypeface(font);
                        holder.rightArrowImageView.setEnabled(true);

                    } else if (selectedImagePosition == 0) {

                        holder.leftArrowImageView.setText("");

                    } else if (selectedImagePosition == list.getImages().size() - 1) {

                        holder.rightArrowImageView.setText("");
                    }*//*

                    changeBorderForSelectedImage(selectedImagePosition, holder);
                    setSelectedImage(list, selectedImagePosition, holder);
                }
            });
            holder.gallery.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

                    selectedImagePosition = pos;

                   *//* if (selectedImagePosition > 0 && selectedImagePosition < list.getImages().size() - 1) {

                        holder.leftArrowImageView.setTypeface(font);
                        holder.leftArrowImageView.setEnabled(true);
                        holder.rightArrowImageView.setTypeface(font);
                        holder.rightArrowImageView.setEnabled(true);

                    } else if (selectedImagePosition == 0) {

                        holder.leftArrowImageView.setText("");
                        holder.leftArrowImageView.setEnabled(false);

                    } else if (selectedImagePosition == list.getImages().size() - 1) {

                        holder.rightArrowImageView.setText("");
                        holder.rightArrowImageView.setEnabled(false);
                    }*//*

                    changeBorderForSelectedImage(selectedImagePosition, holder);
                    setSelectedImage(list, selectedImagePosition, holder);
                }

                @Override
                public void onNothingSelected(AdapterView<?> arg0) {

                }

            });*/

        }
        else{
           /* holder.uploadedImageSwitcher.setVisibility(View.GONE);
            holder.gallery.setVisibility(View.GONE);*/
            rcAdapter.notifyDataSetChanged();
            holder.recyclerView.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        if (items != null) {
            return items.size();
        }
        return 0;
    }

    @Override
    public Filter getFilter() {
        return null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.edit:
                // Display popup attached to the button as a position anchor
                TextView editExperience, editTextView_experience, delete, editTextView_delete;

                View editPopUp = displayPopupWindow(v);
                editExperience = (TextView) editPopUp.findViewById(R.id.editExperience);
                editTextView_experience = (TextView) editPopUp.findViewById(R.id.editTextView_experience);
                delete = (TextView) editPopUp.findViewById(R.id.delete);
                editTextView_delete = (TextView) editPopUp.findViewById(R.id.editTextView_delete);

                delete.setOnClickListener(this);
                editTextView_delete.setOnClickListener(this);
                editExperience.setTypeface(font);
                editTextView_experience.setTypeface(font_Raleway_Light);
                delete.setTypeface(font);
                editTextView_delete.setTypeface(font_Raleway_Light);
                break;

            case R.id.editTextView_delete:
                popup.dismiss();
                jsonObject = new JSONObject();
                try {
                    if(null!= AppConfig.getUser_id())
                    {
                        jsonObject.put("type", "1");
                        jsonObject.put("user_id", AppConfig.getUser_id());
                        jsonObject.put("id", AppConfig.home_List.get(selectedImagePosition).getPost_id());
                        AppConfig.home_List.remove(selectedImagePosition);
                        notifyItemRemoved(selectedImagePosition);
                       // AppConfig.signInHome_ImageSelected = selectedImagePosition;
                        VolleyRequest volleyRequest = new VolleyRequest(context);
                        volleyRequest.signedInHome_Delete(jsonObject);


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView user_image;
        public ImageView edit;
        public TextView first_name;
        public TextView created_date;
        public TextView message;
        public VideoView uploaded_video;
        //public ImageSwitcher uploadedImageSwitcher;
        public TextView comments_count;
        public TextView likes_count;
        public TextView like, comment;
        public  ImageView like_filled, comment_filled, share, uploaded_image;
        //public Button btnNext;
        //public Gallery gallery;
        public int currentIndex = -1;
        public int Position = 0;
        public TextView leftArrowImageView;
        public TextView rightArrowImageView;
        public RecyclerView recyclerView;

        public ViewHolder(final View itemView) {
            super(itemView);

            recyclerView = (RecyclerView)itemView.findViewById(R.id.recycler_view);
            user_image = (ImageView) itemView.findViewById(R.id.profile_pic);
            first_name = (TextView) itemView.findViewById(R.id.name);
            created_date = (TextView) itemView.findViewById(R.id.post_date);
            message = (TextView) itemView.findViewById(R.id.message);
            uploaded_video = (VideoView) itemView.findViewById(R.id.uploaded_video);
            //uploaded_image = (ImageView) itemView.findViewById(R.id.uploaded_image);

            //uploadedImageSwitcher = (ImageSwitcher) itemView.findViewById(R.id.uploadedImage_imageSwitcher);
            likes_count = (TextView) itemView.findViewById(R.id.like_count);
            comments_count = (TextView) itemView.findViewById(R.id.comment_count);

            like= (TextView) itemView.findViewById(R.id.like);
            comment= (TextView) itemView.findViewById(R.id.comment);

            like_filled= (ImageView) itemView.findViewById(R.id.like_filled);
            comment_filled= (ImageView) itemView.findViewById(R.id.comment_filled);
            share= (ImageView) itemView.findViewById(R.id.share);
            edit = (ImageView) itemView.findViewById(R.id.edit);

            // get The references of Button and ImageSwitcher
            //btnNext = (Button) itemView.findViewById(R.id.buttonNext);
            //gallery = (Gallery) itemView.findViewById(R.id.gallery);
            //gallery.setSelection(4);
            //leftArrowImageView = (TextView) itemView.findViewById(R.id.left_arrow_imageview);
            //rightArrowImageView = (TextView) itemView.findViewById(R.id.right_arrow_imageview);

            font = Typeface.createFromAsset(context.getAssets(), "fontawesome-webfont.ttf");
            font_Raleway_Light= Typeface.createFromAsset(itemView.getContext().getAssets(), "fonts/Raleway-Light.ttf");
            font_Raleway_ExtraLight= Typeface.createFromAsset(itemView.getContext().getAssets(), "fonts/Raleway-ExtraLight.ttf");
            font_Raleway_Medium= Typeface.createFromAsset(itemView.getContext().getAssets(), "fonts/Raleway-Medium.ttf");
            font_Raleway_Thin= Typeface.createFromAsset(itemView.getContext().getAssets(), "fonts/Raleway-Thin.ttf");
            font_OpenSans_Light= Typeface.createFromAsset(itemView.getContext().getAssets(), "fonts/OpenSans-Light.ttf");
            font_Raleway_SemiBold = Typeface.createFromAsset(itemView.getContext().getAssets(), "fonts/Raleway-SemiBold.ttf");

            /*uploadedImageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
                @Override
                public View makeView(){
                    // TODO Auto-generated method stub

                    // Create a new ImageView and set it's properties
                    ImageView imageView = new ImageView(itemView.getContext());
                    // set Scale type of ImageView to Fit Center
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    // set the Height And Width of ImageView To FIll PARENT
                    imageView.setLayoutParams(new ImageSwitcher.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT));
                    return imageView;
                }
            });*/

        }
    }

    public void onSwitch_UploadVideo(final ViewHolder holder, Home list) {

        // create a progress bar while the video file is loading
        //progressDialog = new ProgressDialog(holder.itemView.getContext());
        //set the media controller buttons

        mediaControls = new MediaController(holder.itemView.getContext());

        /*// set a title for the progress bar
        progressDialog.setTitle("Please wait. We are loading the video...");
        // set a message for the progress bar
        progressDialog.setMessage("Loading...");
        //set the progress bar not cancelable on users' touch
        progressDialog.setCancelable(true);

        progressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "DISMISS", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                progressDialog.dismiss();
            }
        });*/

        try {
            //set the media controller in the VideoView
            holder.uploaded_video.setMediaController(mediaControls);

            //set the uri of the video to be played
            holder.uploaded_video.setVideoURI(Uri.parse(list.getVideos().get(videoPosition)));

        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }

        //holder.uploaded_video.requestFocus();
        //we also set an setOnPreparedListener in order to know when the video file is ready for playback
        holder.uploaded_video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            public void onPrepared(MediaPlayer mediaPlayer) {
                // close the progress bar and play the video
                //progressDialog.dismiss();
                //if we have a position on savedInstanceState, the video playback should start from here
                holder.uploaded_video.seekTo(videoPosition);
                if (videoPosition == 0) {
                    //holder.uploaded_video.start();
                } else {
                    //if we come from a resumed activity, video playback will be paused
                    holder.uploaded_video.pause();
                }
            }
        });

        holder.uploaded_video.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                Log.d("video", "setOnErrorListener ");
                return true;
            }
        });
        holder.uploaded_video.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent motionEvent) {
                if (holder.uploaded_video.isPlaying()) {
                    holder.uploaded_video.pause();
                    mediaControls.show(0);
                    videoPosition = holder.uploaded_video.getCurrentPosition();
                    return false;
                } else {
                    //progressDialog.show();
                    mediaControls.hide();
                    holder.uploaded_video.seekTo(videoPosition);
                    holder.uploaded_video.start();
                    return false;
                }
            }
        });

        holder.uploaded_video.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                //here
                holder.uploaded_video.seekTo(1);
            }
        });


        //here
        if (videoPosition != 1) {
            holder.uploaded_video.seekTo(videoPosition);
            //holder.uploaded_video.start();
        } else {
            //here
            holder.uploaded_video.seekTo(1);
        }
    }

    /*private void changeBorderForSelectedImage(int selectedItemPos, ViewHolder holder) {

        int count = holder.gallery.getChildCount();

        for (int i = 0; i < count; i++) {

            ImageView imageView = (ImageView) holder.gallery.getChildAt(i);
            imageView.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.image_border));
            imageView.setPadding(3, 3, 3, 3);

        }

        ImageView imageView = (ImageView) holder.gallery.getSelectedView();
        imageView.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.selected_image_border));
        imageView.setPadding(3, 3, 3, 3);
    }*/

    /*private void setSelectedImage(Home list, int selectedImagePosition, ViewHolder holder) {

        Glide.with(context).load(list.getImages().get(selectedImagePosition))
                //.thumbnail(0.5f)
                .thumbnail(Glide.with(context).load(R.drawable.loading))
                .crossFade()
                .placeholder(R.drawable.place_holder)
                //.error(R.drawable.error_icon)
                //.bitmapTransform(new GlideCircleTransformation(context))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into((ImageView) holder.uploadedImageSwitcher.getCurrentView());

    }*/

    // Display popup attached to the button as a position anchor
    private View displayPopupWindow(View anchorView) {
        popup = new PopupWindow(context);
        View layout = LayoutInflater.from(context).inflate(R.layout.signinhome_edit_popupcontent, null);
        popup.setContentView(layout);
        // Set content width and height
        popup.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        popup.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        // Closes the popup window when touch outside of it - when looses focus
        popup.setOutsideTouchable(true);
        popup.setFocusable(true);
        // Show anchored to button
        popup.setBackgroundDrawable(new BitmapDrawable());
        popup.showAsDropDown(anchorView);

        View container = (View) popup.getContentView().getParent().getParent();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        WindowManager.LayoutParams p = (WindowManager.LayoutParams) container.getLayoutParams();
        p.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        p.dimAmount = 0.8f;
        wm.updateViewLayout(container, p);
        return layout;
    }

}
