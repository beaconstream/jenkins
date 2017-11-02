package com.travelur.travelconnect.rewards.models;

/**
 * @author by Abhijit.
 */

public class Rewards {

    String tourName;
    int tourImage;
    String countFriends;
    String countPosts;
    String userpercentage;
    String totalTransactions;
    String startingd_time;
    String endingd_time;

    /*public Rewards(String tourName, int tourImage, String countFriends, String countPosts, String userpercentage, String totalTransactions) {
        this.tourName = tourName;
        this.tourImage = tourImage;
        this.countFriends = countFriends;
        this.countPosts = countPosts;
        this.userpercentage = userpercentage;
        this.totalTransactions = totalTransactions;
    }*/

    public Rewards(String tourName, int tourImage, String countFriends, String countPosts, String userpercentage, String totalTransactions, String startingd_time, String endingd_time) {
        this.tourName = tourName;
        this.tourImage = tourImage;
        this.countFriends = countFriends;
        this.countPosts = countPosts;
        this.userpercentage = userpercentage;
        this.totalTransactions = totalTransactions;
        this.startingd_time = startingd_time;
        this.endingd_time = endingd_time;
    }

    public String getTourName() {
        return tourName;
    }

    public void setTourName(String tourName) {
        this.tourName = tourName;
    }

    public int getTourImage() {
        return tourImage;
    }

    public void setTourImage(int tourImage) {
        this.tourImage = tourImage;
    }

    public String getCountFriends() {
        return countFriends;
    }

    public void setCountFriends(String countFriends) {
        this.countFriends = countFriends;
    }

    public String getCountPosts() {
        return countPosts;
    }

    public void setCountPosts(String countPosts) {
        this.countPosts = countPosts;
    }

    public String getUserpercentage() {
        return userpercentage;
    }

    public void setUserpercentage(String userpercentage) {
        this.userpercentage = userpercentage;
    }

    public String getTotalTransactions() {
        return totalTransactions;
    }

    public void setTotalTransactions(String totalTransactions) {
        this.totalTransactions = totalTransactions;
    }
    public String getStartingd_time() {
        return startingd_time;
    }

    public void setStartingd_time(String startingd_time) {
        this.startingd_time = startingd_time;
    }

    public String getEndingd_time() {
        return endingd_time;
    }

    public void setEndingd_time(String endingd_time) {
        this.endingd_time = endingd_time;
    }

}
