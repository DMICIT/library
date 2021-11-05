package com.project.web.data;

import java.sql.Date;

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
}
