package com.travelur.App_Loading;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.travelur.R;

import java.lang.reflect.Array;

/**
 * Created by Priyanka on 30-03-2017.
 */

public class ViewPagerAdapter extends PagerAdapter {
    LayoutInflater inflater;
    Activity activity;
    Context context;
    ViewPager viewPager;
TextView icon,icon_label;
  int icons[]={R.string.globe,R.string.camera, R.string.unlock,R.string.images};
    int icons_label[]={R.string.globe_text,R.string.camera_text,R.string.unlock_text,R.string.images_text};
    @Override
    public int getCount() {
        return icons.length;
    }
    public  ViewPagerAdapter(Context context, ViewPager viewPager){
        this.context=context;
        this.viewPager=viewPager;

    }
    @Override
    public void destroyItem(View arg0, int arg1, Object arg2) {
        ((ViewPager) arg0).removeView((View) arg2);
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == ((View) arg1);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.view_pager_item, container,
                false);
        icon= (TextView) view.findViewById(R.id.startup_icon);
        icon_label= (TextView) view.findViewById(R.id.startup_text);
        Typeface font = Typeface.createFromAsset(context.getAssets(), "fontawesome-webfont.ttf");
        icon.setTypeface(font);
        icon.setText(icons[position]);
        icon_label.setText(icons_label[position]);
        ((ViewPager) container).addView(view);
        return  view;
    }
}
