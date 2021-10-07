package com.progect.entities;

import java.sql.Date;
import java.util.Objects;

public class Book {
    private int id;
    private String author;
    private String bookName;
    private String bookEdition;
    private Date reliaseDate;

    public Book(int id, String author, String bookName, String bookEdition, Date reliaseDate) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id && Objects.equals(author, book.author) && Objects.equals(bookName, book.bookName) && Objects.equals(bookEdition, book.bookEdition) && Objects.equals(reliaseDate, book.reliaseDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, bookName, bookEdition, reliaseDate);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", bookName='" + bookName + '\'' +
                ", bookEdition='" + bookEdition + '\'' +
                ", reliaseDate=" + reliaseDate +
                '}';
    }
}
