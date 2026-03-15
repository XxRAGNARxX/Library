package models;

import java.util.List;

public class Book {
    private String isbn;
    private String title;
    private String author;
    private String genre;
    private String shortDescription;
    private int publishYear;
    private List<String> keywords;
    private double rating;

    public Book(String isbn, String title, String author, String genre) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.genre = genre;
    }

    public String getIsbn() { return isbn; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getGenre() { return genre; }
    public int getPublishYear() { return publishYear; }
    public double getRating() { return rating; }

    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                ", publishYear=" + publishYear +
                ", keywords=" + keywords +
                ", rating=" + rating +
                '}';
    }
}