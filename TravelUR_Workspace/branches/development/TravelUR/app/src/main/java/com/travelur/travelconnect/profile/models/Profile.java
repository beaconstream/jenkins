package com.travelur.travelconnect.profile.models;

/**
 * Created by Abhijit on 7/28/2017.
 */

public class Profile {

    private String user_id;
    private String group_name;
    private String group_pic;
    private String group_desc;
    private String group_id;
    private String group_slug;

    public Profile(String user_id, String group_name, String group_pic, String group_desc, String group_id, String group_slug, String group_image_path) {
        this.user_id = user_id;
        this.group_name = group_name;
        this.group_pic = group_pic;
        this.group_desc = group_desc;
        this.group_id = group_id;
        this.group_slug = group_slug;
        this.group_image_path = group_image_path;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public String getGroup_pic() {
        return group_pic;
    }

    public void setGroup_pic(String group_pic) {
        this.group_pic = group_pic;
    }

    public String getGroup_desc() {
        return group_desc;
    }

    public void setGroup_desc(String group_desc) {
        this.group_desc = group_desc;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getGroup_slug() {
        return group_slug;
    }

    public void setGroup_slug(String group_slug) {
        this.group_slug = group_slug;
    }

    public String getGroup_image_path() {
        return group_image_path;
    }

    public void setGroup_image_path(String group_image_path) {
        this.group_image_path = group_image_path;
    }

    String group_image_path;
}
