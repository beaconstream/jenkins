package com.travelur.travelconnect.vacationpackages.models;

/**
 * @author by Abhijit.
 */

public class FilterApply {

    String package_country, package_type, prices, duration;

    public FilterApply(String package_country, String package_type, String prices, String duration) {
        this.package_country = package_country;
        this.package_type = package_type;
        this.prices = prices;
        this.duration = duration;
    }

    public String getPackage_country() {
        return package_country;
    }

    public void setPackage_country(String package_country) {
        this.package_country = package_country;
    }

    public String getPackage_type() {
        return package_type;
    }

    public void setPackage_type(String package_type) {
        this.package_type = package_type;
    }

    public String getPrices() {
        return prices;
    }

    public void setPrices(String prices) {
        this.prices = prices;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

}
