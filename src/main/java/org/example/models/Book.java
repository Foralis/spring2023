package org.example.models;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Book {
    private int id;

    @NotEmpty(message = "name should not be empty")
    @Size(min = 3, max = 255, message = "name should be between 1 and 255")
    private String name;

    @NotEmpty(message = "author should not be empty")
    @Size(min = 3, max = 255, message = "author should be between 1 and 255")
    private String author;

    @Pattern(regexp = "\\d{4}", message = "date should be in format yyyy")
    private String established;

    public Book(int id, String name, String author, String established) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.established = established;
    }

    public Book() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getEstablished() {
        return established;
    }

    public void setEstablished(String established) {
        this.established = established;
    }

    @Override
    public String toString() {
        return getName() + ", " + getAuthor() + ", " + getEstablished();
    }
}
