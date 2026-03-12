package edu.eci.dosw.tdd.library.book;

import java.util.Objects;

public class Book {
    private final String title;
    private final String author;
    private final String isbn;

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Book other = (Book) obj;
        return Objects.equals(isbn, other.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn);
    }
}