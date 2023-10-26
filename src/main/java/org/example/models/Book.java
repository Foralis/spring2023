package org.example.models;

public class Book {
    private String name;
    private String author;
    private String established;

    public String getName() {
        return name;
    }

    public Book() {
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

    public Book(String name, String author, String established) {
        this.name = name;
        this.author = author;
        this.established = established;
    }
}
