package com.books.books;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "books")
public class Books {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String title;
    private String author;
    private Integer year;
    private Integer rating;
    private boolean ispdf;

    private Books() {
    }

    public Books(String title, String author, int year, int rating, boolean ispdf) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.rating = rating;
        this.ispdf = ispdf;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Integer getYear() {
        return year;
    }

    public Integer getRating() {
        return rating;
    }

    public boolean isPdf() {
        return ispdf;
    }

}