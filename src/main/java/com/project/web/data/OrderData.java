package com.project.web.data;

import java.sql.Date;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderData orderData = (OrderData) o;
        return id == orderData.id && Objects.equals(userData, orderData.userData) && Objects.equals(bookData, orderData.bookData) && Objects.equals(bookSpot, orderData.bookSpot) && Objects.equals(status, orderData.status) && Objects.equals(returnDate, orderData.returnDate) && Objects.equals(penaltyData, orderData.penaltyData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userData, bookData, bookSpot, status, returnDate, penaltyData);
    }

    @Override
    public String toString() {
        return "OrderData{" +
                "id=" + id +
                ", userData=" + userData +
                ", bookData=" + bookData +
                ", bookSpot='" + bookSpot + '\'' +
                ", status='" + status + '\'' +
                ", returnDate=" + returnDate +
                ", penaltyData=" + penaltyData +
                '}';
    }
}
