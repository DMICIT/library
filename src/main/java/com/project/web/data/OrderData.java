package com.project.web.data;

import java.sql.Date;

public class OrderData {
    private int id;
    private int userId;
    private BookData bookData;
    private String bookSpot;
    private String status;
    private Date returnDate;

    public OrderData(int id, int userId, BookData bookData, String bookSpot, String status, Date returnDate) {
        this.id = id;
        this.userId = userId;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
}
