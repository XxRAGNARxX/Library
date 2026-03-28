package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Book implements Serializable {
    private String isbn;
    private String title;
    private String author;
    private String genre;
    private String description;
    private int year;
    private List<String> tags;
    private double rating;

    public Book(String isbn, String title, String author, String genre, String description, int year, double rating) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.description = description;
        this.year = year;
        this.rating = rating;
        this.tags = new ArrayList<>();
    }

    public String getIsbn() { return isbn; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getGenre() { return genre; }
    public String getDescription() { return description; }
    public int getYear() { return year; }
    public double getRating() { return rating; }
    public List<String> getTags() { return tags; }

    public void addTag(String tag) {
        if (tag != null && !tag.trim().isEmpty()) {
            this.tags.add(tag.trim().toLowerCase());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(isbn, book.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn);
    }

    public String shortInfo() {
        return String.format("ISBN: %s | Title: %s | Author: %s | Genre: %s", isbn, title, author, genre);
    }

    @Override
    public String toString() {
        return "ISBN: " + isbn + "\n" +
                "Title: " + title + "\n" +
                "Author: " + author + "\n" +
                "Genre: " + genre + "\n" +
                "Year: " + year + "\n" +
                "Rating: " + rating + "\n" +
                "Tags: " + String.join(", ", tags) + "\n" +
                "Description: " + description + "\n";
    }
}