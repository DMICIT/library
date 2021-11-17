package com.project.entities;

import java.util.Objects;

public class Catalog {
    private int id;
    private int bookId;
    private int count;

    public Catalog(int bookId, int count) {
        this.bookId = bookId;
        this.count = count;
    }

    public Catalog(int id, int bookId, int count) {
        this.id = id;
        this.bookId = bookId;
        this.count = count;
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Catalog catalog = (Catalog) o;
        return id == catalog.id && bookId == catalog.bookId && Objects.equals(count, catalog.count);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bookId, count);
    }

    @Override
    public String toString() {
        return "Catalog{" +
                "id=" + id +
                ", bookId=" + bookId +
                ", status='" + count + '\'' +
                '}';
    }
}
