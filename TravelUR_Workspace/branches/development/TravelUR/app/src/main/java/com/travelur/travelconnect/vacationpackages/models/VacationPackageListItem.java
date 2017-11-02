package com.travelur.travelconnect.vacationpackages.models;

/**
 * @author by Abhijit.
 */

public class VacationPackageListItem {

    private String image_url;
    private String packege_name;
    private String package_price;
    private String package_id;

    private String package_includes;

    public VacationPackageListItem(String image_url, String packege_name, String package_price, String package_id,String package_includes) {
        this.image_url = image_url;
        this.packege_name = packege_name;
        this.package_price = package_price;
        this.package_id = package_id;
        this.package_includes = package_includes;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getPackege_name() {
        return packege_name;
    }

    public void setPackege_name(String packege_name) {
        this.packege_name = packege_name;
    }

    public String getPackage_price() {
        return package_price;
    }
    public String getPackage_id() {
        return package_id;
    }

    public void setPackage_id(String package_id) {
        this.package_id = package_id;
    }
    public void setPackage_price(String package_price) {
        this.package_price = package_price;
    }

    public String getPackage_includes() {
        return package_includes;
    }

    public void setPackage_includes(String package_includes) {
        this.package_includes = package_includes;
    }
}
