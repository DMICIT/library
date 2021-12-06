package com.project.web.data;

import java.sql.Date;
import java.util.Objects;

public class BookData {
    private int id;
    private String author;
    private String bookName;
    private String bookEdition;
    private Date reliaseDate;
    private CatalogData catalogData;

    public BookData(int id, String author, String bookName, String bookEdition, Date reliaseDate) {
        this.id = id;
        this.author = author;
        this.bookName = bookName;
        this.bookEdition = bookEdition;
        this.reliaseDate = reliaseDate;
    }

    public BookData(int id, String author, String bookName, String bookEdition, Date reliaseDate, CatalogData catalogData) {
        this.id = id;
        this.author = author;
        this.bookName = bookName;
        this.bookEdition = bookEdition;
        this.reliaseDate = reliaseDate;
        this.catalogData = catalogData;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookEdition() {
        return bookEdition;
    }

    public void setBookEdition(String bookEdition) {
        this.bookEdition = bookEdition;
    }

    public Date getReliaseDate() {
        return reliaseDate;
    }

    public void setReliaseDate(Date reliaseDate) {
        this.reliaseDate = reliaseDate;
    }

    public CatalogData getCatalogData() {
        return catalogData;
    }

    public void setCatalogData(CatalogData catalogData) {
        this.catalogData = catalogData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookData bookData = (BookData) o;
        return id == bookData.id && Objects.equals(author, bookData.author) && Objects.equals(bookName, bookData.bookName) && Objects.equals(bookEdition, bookData.bookEdition) && Objects.equals(reliaseDate, bookData.reliaseDate) && Objects.equals(catalogData, bookData.catalogData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, bookName, bookEdition, reliaseDate, catalogData);
    }

    @Override
    public String toString() {
        return "BookData{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", bookName='" + bookName + '\'' +
                ", bookEdition='" + bookEdition + '\'' +
                ", reliaseDate=" + reliaseDate +
                ", catalogData=" + catalogData +
                '}';
    }
}
