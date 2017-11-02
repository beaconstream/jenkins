package com.travelur.travelconnect.photos.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author by Abhijit.
 */

public class VideoDataModel implements Parcelable{
    String url;
    String thumbnail;
    String id;

    public VideoDataModel(String url, String thumbnail, String id) {
        this.url = url;
        this.thumbnail = thumbnail;
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(url);
        parcel.writeString(id);
        parcel.writeString(thumbnail);
    }

}
