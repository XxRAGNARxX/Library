package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a book in the library catalogue.
 *
 * <p>Books are identified uniquely by their ISBN. Each book carries
 * bibliographic metadata and an optional list of searchable tags.
 * Instances are serialized as part of the library data file.
 *
 * <p>Equality and hashing are based solely on {@code isbn}.
 */
public class Book implements Serializable {
    private String isbn;
    private String title;
    private String author;
    private String genre;
    private String description;
    private int year;
    private List<String> tags;
    private double rating;

    /**
     * Constructs a book with the supplied bibliographic data.
     *
     * <p>An empty tag list is initialised automatically; tags can be added
     * later via {@link #addTag(String)}.
     *
     * @param isbn        International Standard Book Number (unique identifier)
     * @param title       book title
     * @param author      primary author name
     * @param genre       genre / category
     * @param description short synopsis or description
     * @param year        publication year
     * @param rating      reader rating (typically 0.0–10.0)
     */

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

    /**
     * Returns the mutable list of tags associated with this book.
     *
     * @return list of lowercase, trimmed tag strings
     */
    public List<String> getTags() { return tags; }

    /**
     * Adds a tag to this book after normalising it to lowercase and trimming whitespace.
     *
     * <p>Null or blank tags are silently ignored.
     *
     * @param tag tag string to add
     */

    public void addTag(String tag) {
        if (tag != null && !tag.trim().isEmpty()) {
            this.tags.add(tag.trim().toLowerCase());
        }
    }

    /**
     * Two books are equal when their ISBNs match.
     */
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

    /**
     * Returns a compact single-line summary used by the {@code books all} listing.
     *
     * @return {@code "ISBN: … | Title: … | Author: … | Genre: …"}
     */
    public String shortInfo() {
        return String.format("ISBN: %s | Title: %s | Author: %s | Genre: %s", isbn, title, author, genre);
    }

    /**
     * Returns a full multi-line representation of this book including all fields.
     *
     * @return formatted book details
     */
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