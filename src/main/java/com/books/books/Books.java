package com.books.books;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// Entity class for the Books table
@Entity
@Table(name = "books")
public class Books {
    
    // Auto-generate the id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    // Rest of the fields
    private String title;
    private String author;
    private Integer year;
    private Float rating;
    private boolean ispdf;

    // Default constructor
    private Books() {
    }

    // Constructor with all fields
    public Books(String title, String author, int year, Float rating, boolean ispdf) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.rating = rating;
        this.ispdf = ispdf;
    }

    // ------- Getters --------
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

    public Float getRating() {
        return rating;
    }

    public boolean getIspdf() {
        return ispdf;
    }

}