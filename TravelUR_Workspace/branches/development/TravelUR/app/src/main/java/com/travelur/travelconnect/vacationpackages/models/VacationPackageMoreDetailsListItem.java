package com.travelur.travelconnect.vacationpackages.models;

/**
 * @author by Abhijit.
 */

public class VacationPackageMoreDetailsListItem {

    private String package_name;
    private String package_price;
    private String image;

    public VacationPackageMoreDetailsListItem(String url, String package_name, String package_price, String image) {
        this.url = url;
        this.package_name = package_name;
        this.package_price = package_price;
        this.image = image;
    }

    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPackage_name() {
        return package_name;
    }

    public void setPackage_name(String package_name) {
        this.package_name = package_name;
    }

    public String getPackage_price() {
        return package_price;
    }

    public void setPackage_price(String package_price) {
        this.package_price = package_price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


}
