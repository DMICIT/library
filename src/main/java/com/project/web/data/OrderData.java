package com.project.web.data;

import java.sql.Date;

public class OrderData {
    private int id;
    private UserData userData;
    private BookData bookData;
    private String bookSpot;
    private String status;
    private Date returnDate;
    private PenaltyData penaltyData;

    public OrderData(int id, UserData userData, BookData bookData, String bookSpot, String status, Date returnDate) {
        this.id = id;
        this.userData = userData;
        this.bookData = bookData;
        this.bookSpot = bookSpot;
        this.status = status;
        this.returnDate = returnDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public BookData getBookData() {
        return bookData;
    }

    public void setBookData(BookData bookData) {
        this.bookData = bookData;
    }

    public String getBookSpot() {
        return bookSpot;
    }

    public void setBookSpot(String bookSpot) {
        this.bookSpot = bookSpot;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public PenaltyData getPenaltyData() {
        return penaltyData;
    }

    public void setPenaltyData(PenaltyData penaltyData) {
        this.penaltyData = penaltyData;
    }
}
