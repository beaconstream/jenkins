package com.travelur.travelconnect.profile.models;

/**
 * @author by Abhijit.
 */

public class ProfileDetailsDataModel {
    private String postcount;
    private String friendscount ;
    private String groupcount;
    private String photoscount;
    private String videoscount;
    private int profilepercentage;
    private String email;

    public ProfileDetailsDataModel(String postcount, String friendscount, String groupcount, String photoscount, String videoscount, int profilepercentage, String email) {
        this.postcount = postcount;
        this.friendscount = friendscount;
        this.groupcount = groupcount;
        this.photoscount = photoscount;
        this.videoscount = videoscount;
        this.profilepercentage = profilepercentage;
        this.email = email;
    }

    public String getPostcount() {
        return postcount;
    }

    public void setPostcount(String postcount) {
        this.postcount = postcount;
    }

    public String getFriendscount() {
        return friendscount;
    }

    public void setFriendscount(String friendscount) {
        this.friendscount = friendscount;
    }

    public String getGroupcount() {
        return groupcount;
    }

    public void setGroupcount(String groupcount) {
        this.groupcount = groupcount;
    }

    public String getPhotoscount() {
        return photoscount;
    }

    public void setPhotoscount(String photoscount) {
        this.photoscount = photoscount;
    }

    public String getVideoscount() {
        return videoscount;
    }

    public void setVideoscount(String videoscount) {
        this.videoscount = videoscount;
    }
    public int getProfilepercentage() {
        return profilepercentage;
    }

    public void setProfilepercentage(int profilepercentage) {
        this.profilepercentage = profilepercentage;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
