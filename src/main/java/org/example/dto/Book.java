package org.example.dto;

import javax.validation.constraints.*;

public class Book {
    private Integer id;
    @NotBlank(message = "The Author should not be empty")
    private String author;
    @NotBlank(message = "The Author should not be empty")
    private String title;
    @Digits(integer = 4, fraction = 0, message = "The Size must be < 10000")
    @NotNull(message = "The Size should not be empty")
    @Positive(message = "The Size must be positive")
    private Integer size;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", size=" + size +
                '}';
    }
}
