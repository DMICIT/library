package com.project.entities;

import java.sql.Date;
import java.util.Objects;

public class Order {
    private int id;
    private int userId;
    private int bookId;
    private String bookSpot;
    private String status;
    private Date returnDate;
    private String penalty;

    public Order(int userId, int bookId, String bookSpot, String status, Date returnDate) {
        this.userId = userId;
        this.bookId = bookId;
        this.bookSpot = bookSpot;
        this.status = status;
        this.returnDate = returnDate;
    }

    public Order(int id, int userId, int bookId, String bookSpot, String status, Date returnDate) {
        this.id = id;
        this.userId = userId;
        this.bookId = bookId;
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

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id && userId == order.userId && bookId == order.bookId && Objects.equals(bookSpot, order.bookSpot) && Objects.equals(status, order.status) && Objects.equals(returnDate, order.returnDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, bookId, bookSpot, status, returnDate);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", bookId=" + bookId +
                ", bookSpot='" + bookSpot + '\'' +
                ", status='" + status + '\'' +
                ", returnDate=" + returnDate +
                '}';
    }
}
