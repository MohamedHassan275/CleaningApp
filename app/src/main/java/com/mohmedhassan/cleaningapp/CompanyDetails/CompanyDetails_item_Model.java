package com.mohmedhassan.cleaningapp.CompanyDetails;

public class CompanyDetails_item_Model {

    int imageCompany;
    String  carwish, Clean, Price,Price_Number;

    public CompanyDetails_item_Model() {
    }

    public CompanyDetails_item_Model(int imageCompany, String carwish, String clean, String price, String price_Number) {
        this.imageCompany = imageCompany;
        this.carwish = carwish;
        Clean = clean;
        Price = price;
        Price_Number = price_Number;
    }

    public int getImageCompany() {
        return imageCompany;
    }

    public void setImageCompany(int imageCompany) {
        this.imageCompany = imageCompany;
    }

    public String getCarwish() {
        return carwish;
    }

    public void setCarwish(String carwish) {
        this.carwish = carwish;
    }

    public String getClean() {
        return Clean;
    }

    public void setClean(String clean) {
        Clean = clean;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getPrice_Number() {
        return Price_Number;
    }

    public void setPrice_Number(String price_Number) {
        Price_Number = price_Number;
    }
}
