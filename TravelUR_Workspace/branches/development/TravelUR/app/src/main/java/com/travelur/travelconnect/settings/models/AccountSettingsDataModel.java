package com.travelur.travelconnect.settings.models;

/**
 * @author by Abhijit.
 */

public class AccountSettingsDataModel {

    String profile_pic, first_name, last_name, country_code, phone_no, dob, home_airport, country, zip_code, home_address, city, state;

    public AccountSettingsDataModel(String profile_pic, String first_name, String last_name, String country_code, String phone_no, String dob, String home_airport, String country, String zip_code, String home_address, String city, String state) {
        this.profile_pic = profile_pic;
        this.first_name = first_name;
        this.last_name = last_name;
        this.country_code = country_code;
        this.phone_no = phone_no;
        this.dob = dob;
        this.home_airport = home_airport;
        this.country = country;
        this.zip_code = zip_code;
        this.home_address = home_address;
        this.city = city;
        this.state = state;
    }

    public String getProfile_pic() {
        return profile_pic;
    }

    public void setProfile_pic(String profile_pic) {
        this.profile_pic = profile_pic;
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

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getHome_airport() {
        return home_airport;
    }

    public void setHome_airport(String home_airport) {
        this.home_airport = home_airport;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZip_code() {
        return zip_code;
    }

    public void setZip_code(String zip_code) {
        this.zip_code = zip_code;
    }

    public String getHome_address() {
        return home_address;
    }

    public void setHome_address(String home_address) {
        this.home_address = home_address;
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

}
