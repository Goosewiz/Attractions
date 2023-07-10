package com.example.attractions.Models;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.w3c.dom.Attr;

import java.time.LocalDate;

@Entity
public class Attractions {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String city;
    private String name;
    private String category;
    private int Xcoord;
    private int Ycoord;
    private double review;
    private double numberOfReviews;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getXcoord() {
        return Xcoord;
    }

    public void setXcoord(int xcoord) {
        Xcoord = xcoord;
    }

    public int getYcoord() {
        return Ycoord;
    }

    public void setYcoord(int ycoord) {
        Ycoord = ycoord;
    }

    public double getReview() {
        return review;
    }

    public void setReview(double review) {
        this.review = review;
    }

    public double getNumberOfReviews() {
        return numberOfReviews;
    }

    public void setNumberOfReviews(double numberOfReviews) {
        this.numberOfReviews = numberOfReviews;
    }
    public Attractions(){
    }

    public Attractions(String city, String name, String category, int xcoord, int ycoord, double review, double numberOfReviews) {
        this.city = city;
        this.name = name;
        this.category = category;
        this.Xcoord = xcoord;
        this.Ycoord = ycoord;
        this.review = review;
        this.numberOfReviews = numberOfReviews;
    }
}
