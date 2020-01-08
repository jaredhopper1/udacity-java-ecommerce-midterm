package com.udacity.course3.reviews.entities;

import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

public class ReviewDocument {
    @Id
    private Integer id;
    private String title;
    private String review;
    private Product product;
    private List<Comment> comments;

    public ReviewDocument() {
    }

    public ReviewDocument(Review review) {
        this.id = review.getId();
        this.title = review.getTitle();
        this.review = review.getReview();
        this.product = review.getProduct();
        this.comments = new ArrayList<>();
    }

    public void addComments(Comment comment) {
        comments.add(comment);
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReview() {
        return this.review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Comment> getComments() {
        return this.comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", title='" + getTitle() + "'" + ", review='" + getReview() + "'" + ", product='" + getProduct() + "'"
                + ", comments='" + getComments() + "'" + "}";
    }

}

