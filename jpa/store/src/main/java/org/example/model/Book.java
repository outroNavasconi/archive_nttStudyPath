package org.example.model;

import jakarta.persistence.Entity;

@Entity
public class Book extends Product {
    private String author;
    private Integer pages;

    public Book(){}

    public Book(String author, Integer pages) {
        this.author = author;
        this.pages = pages;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }
}
