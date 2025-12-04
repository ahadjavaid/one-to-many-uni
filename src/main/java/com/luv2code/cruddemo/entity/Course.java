package com.luv2code.cruddemo.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "course")
public class Course {

    // define our fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH })
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    private List<Review> reviews;

    // define constructor

    public Course() {

    }

    public Course(String title) {
        this.title = title;
    }

    // define getter/setter


    public List<Review> getReviews() {
        return reviews;
    }

    public void setReview(List<Review> reviews) {
        this.reviews = reviews;
    }


    // add a convenience method

    public void addReview(Review theReview) {
        if(reviews == null) {
            reviews = new ArrayList<>();
        }
        reviews.add(theReview);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor theInstructor) {
        this.instructor = theInstructor;
    }

    // define toString()

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }

    // annotate fields
}
