package com.travelur.travelconnect.gallery.model;

import android.os.Parcel;
import android.os.Parcelable;

/*
 * @author by Abhijit .
 */

public class Media implements Parcelable {
    public String path;
    public boolean isSelected;

    public Media() {

    }

    protected Media(Parcel in) {
        path = in.readString();
        isSelected = in.readByte() != 0;
    }

    public static final Creator<Media> CREATOR = new Creator<Media>() {
        @Override
        public Media createFromParcel(Parcel in) {
            return new Media(in);
        }

        @Override
        public Media[] newArray(int size) {
            return new Media[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(path);
        parcel.writeByte((byte) (isSelected ? 1 : 0));
    }
}
