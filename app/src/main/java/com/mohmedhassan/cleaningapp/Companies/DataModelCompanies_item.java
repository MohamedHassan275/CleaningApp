package com.mohmedhassan.cleaningapp.Companies;

public class DataModelCompanies_item {

    int photo;
    String CompanyName, ReviewsNumber, CommentsNumber, Munimum_Order_Number;
    int Comment,Reviews,Minimum_Order,car_Gps,Delivery_Gps;
    double Rating;

    public DataModelCompanies_item() {
    }

    public DataModelCompanies_item(int photo, String companyName, String reviewsNumber, String commentsNumber
            , String munimum_Order_Number, int comment, int reviews, int minimum_Order, int car_Gps, int delivery_Gps, double rating) {
        this.photo = photo;
        CompanyName = companyName;
        ReviewsNumber = reviewsNumber;
        CommentsNumber = commentsNumber;
        Munimum_Order_Number = munimum_Order_Number;
        Comment = comment;
        Reviews = reviews;
        Minimum_Order = minimum_Order;
        this.car_Gps = car_Gps;
        Delivery_Gps = delivery_Gps;
        Rating = rating;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public String getReviewsNumber() {
        return ReviewsNumber;
    }

    public void setReviewsNumber(String reviewsNumber) {
        ReviewsNumber = reviewsNumber;
    }

    public String getCommentsNumber() {
        return CommentsNumber;
    }

    public void setCommentsNumber(String commentsNumber) {
        CommentsNumber = commentsNumber;
    }

    public String getMunimum_Order_Number() {
        return Munimum_Order_Number;
    }

    public void setMunimum_Order_Number(String munimum_Order_Number) {
        Munimum_Order_Number = munimum_Order_Number;
    }

    public int getComment() {
        return Comment;
    }

    public void setComment(int comment) {
        Comment = comment;
    }

    public int getReviews() {
        return Reviews;
    }

    public void setReviews(int reviews) {
        Reviews = reviews;
    }

    public int getMinimum_Order() {
        return Minimum_Order;
    }

    public void setMinimum_Order(int minimum_Order) {
        Minimum_Order = minimum_Order;
    }

    public int getCar_Gps() {
        return car_Gps;
    }

    public void setCar_Gps(int car_Gps) {
        this.car_Gps = car_Gps;
    }

    public int getDelivery_Gps() {
        return Delivery_Gps;
    }

    public void setDelivery_Gps(int delivery_Gps) {
        Delivery_Gps = delivery_Gps;
    }

    public double getRating() {
        return Rating;
    }

    public void setRating(double rating) {
        Rating = rating;
    }


}
