package com.travelur.travelconnect.photos.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author  by Abhijit on 8/29/2017.
 */

public class PhotoDataModel implements Parcelable {

    private String mUrl;
    private String mTitle;

    public PhotoDataModel(String url, String title) {
        mUrl = url;
        mTitle = title;
    }

    protected PhotoDataModel(Parcel in) {
        mUrl = in.readString();
        mTitle = in.readString();
    }

    public static final Creator<PhotoDataModel> CREATOR = new Creator<PhotoDataModel>() {
        @Override
        public PhotoDataModel createFromParcel(Parcel in) {
            return new PhotoDataModel(in);
        }

        @Override
        public PhotoDataModel[] newArray(int size) {
            return new PhotoDataModel[size];
        }
    };

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public static  PhotoDataModel[] getSpacePhotos() {

        return new PhotoDataModel[]{
                new PhotoDataModel("http://i.imgur.com/zuG2bGQ.jpg", "Galaxy"),
                new PhotoDataModel("http://i.imgur.com/ovr0NAF.jpg", "Space Shuttle"),
                new PhotoDataModel("http://i.imgur.com/n6RfJX2.jpg", "Galaxy Orion"),
                new PhotoDataModel("http://i.imgur.com/qpr5LR2.jpg", "Earth"),
                new PhotoDataModel("http://i.imgur.com/pSHXfu5.jpg", "Astronaut"),
                new PhotoDataModel("http://i.imgur.com/3wQcZeY.jpg", "Satellite"),
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mUrl);
        parcel.writeString(mTitle);
    }
}
