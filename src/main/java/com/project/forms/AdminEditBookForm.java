package com.project.forms;

import java.util.Objects;

public class AdminEditBookForm {
    private String bookId;
    private String author;
    private String bookName;
    private String bookEdition;
    private String reliaseDate;
    private String count;

    public AdminEditBookForm(String bookId, String author, String bookName, String bookEdition, String reliaseDate, String count) {
        this.bookId = bookId;
        this.author = author;
        this.bookName = bookName;
        this.bookEdition = bookEdition;
        this.reliaseDate = reliaseDate;
        this.count = count;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
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

    public String getReliaseDate() {
        return reliaseDate;
    }

    public void setReliaseDate(String reliaseDate) {
        this.reliaseDate = reliaseDate;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdminEditBookForm that = (AdminEditBookForm) o;
        return count == that.count && Objects.equals(bookId, that.bookId) && Objects.equals(author, that.author) && Objects.equals(bookName, that.bookName) && Objects.equals(bookEdition, that.bookEdition) && Objects.equals(reliaseDate, that.reliaseDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, author, bookName, bookEdition, reliaseDate, count);
    }

    @Override
    public String toString() {
        return "AdminEditBookForm{" +
                "bookId='" + bookId + '\'' +
                ", author='" + author + '\'' +
                ", bookName='" + bookName + '\'' +
                ", bookEdition='" + bookEdition + '\'' +
                ", reliaseDate=" + reliaseDate +
                ", count=" + count +
                '}';
    }
}
