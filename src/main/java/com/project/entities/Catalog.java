package com.project.entities;

import java.util.Objects;

public class Catalog {
    private int id;
    private int bookId;
    private String status;

    public Catalog(int id, int bookId, String status) {
        this.id = id;
        this.bookId = bookId;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Catalog catalog = (Catalog) o;
        return id == catalog.id && bookId == catalog.bookId && Objects.equals(status, catalog.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bookId, status);
    }

    @Override
    public String toString() {
        return "Catalog{" +
                "id=" + id +
                ", bookId=" + bookId +
                ", status='" + status + '\'' +
                '}';
    }
}
