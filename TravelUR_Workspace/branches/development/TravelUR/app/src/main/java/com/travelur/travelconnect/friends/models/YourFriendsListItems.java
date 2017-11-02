package com.travelur.travelconnect.friends.models;

/**
 * @author by Abhijit
 */

public class YourFriendsListItems {

    String user_id;
    String first_name;
    String last_name;
    String profilepic;
    String created_datetime;
    String city;
    String state;
    String country_name;
    String pending_requests;
    String friends_count;

    public YourFriendsListItems(String user_id, String first_name, String last_name, String profilepic, String created_datetime, String city, String state, String country_name, String pending_requests, String friends_count) {
        this.user_id = user_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.profilepic = profilepic;
        this.created_datetime = created_datetime;
        this.city = city;
        this.state = state;
        this.country_name = country_name;
        this.pending_requests = pending_requests;
        this.friends_count = friends_count;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getProfilepic() {
        return profilepic;
    }

    public void setProfilepic(String profilepic) {
        this.profilepic = profilepic;
    }

    public String getCreated_datetime() {
        return created_datetime;
    }

    public void setCreated_datetime(String created_datetime) {
        this.created_datetime = created_datetime;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public String getPending_requests() {
        return pending_requests;
    }

    public void setPending_requests(String pending_requests) {
        this.pending_requests = pending_requests;
    }

    public String getFriends_count() {
        return friends_count;
    }

    public void setFriends_count(String friends_count) {
        this.friends_count = friends_count;
    }
}
