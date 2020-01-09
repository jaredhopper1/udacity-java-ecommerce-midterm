package com.udacity.course3.reviews.repos;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.udacity.course3.reviews.entities.Comment;
import com.udacity.course3.reviews.entities.Product;
import com.udacity.course3.reviews.entities.ReviewDocument;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataMongoTest

public class ReviewDocumentRepositoryTest {

    @Autowired
    private ReviewDocumentRepository reviewDocumentRepository;



   @AfterEach
    public void tearDown(){reviewDocumentRepository.deleteAll();}

    @Test
    public void testPostReview() {

        // insert a new review document
        ReviewDocument review = new ReviewDocument();
        review.setId(1);
        review.setReview("Quick test for a post call");


        // check to see if review was posted
        Assert.assertNotNull(reviewDocumentRepository.save(review));

    }

    @Test
    public void TestGetReview() {

        Product product = new Product();
        product.setId(1);
        product.setName("Forky");
        product.setDescription("It is a fork");
        product.setCategory("Action figure");

        ReviewDocument review = new ReviewDocument();
        review.setId(1);
        review.setTitle("test");
        review.setReview("Testing review");
        review.setProduct(product);

        Comment comment = new Comment();
        comment.setId(1);
        comment.setHeading("Test Comment");
        comment.setComment("This is a comment body");

        List<Comment> commentList = new ArrayList<Comment>();
        commentList.add(comment);
        review.setComments(commentList);

        reviewDocumentRepository.save(review);


       //check to see if review was saved to db
       Assert.assertNotNull(reviewDocumentRepository.save(review));


       //make sure all values from above were saved in correct location
        Assert.assertNotNull(reviewDocumentRepository.findAllByProductId(1));
        List<ReviewDocument> returnedReviewSize =
                reviewDocumentRepository.findAllByProductId(1);
        Assert.assertEquals(1, returnedReviewSize.size());
        Assert.assertNotNull(reviewDocumentRepository.findAllByProductId(review.getId()));

        ReviewDocument returnedReviewsContent =
                reviewDocumentRepository.findById(1).get();
        assertEquals(1, returnedReviewsContent.getId().intValue());
        assertEquals("test", returnedReviewsContent.getTitle());
        assertEquals("Testing review", returnedReviewsContent.getReview());

    }

    @Test
    public void testPostComments() {
        Product product = new Product();
        product.setId(1);
        product.setName("Forky");
        product.setDescription("It is a fork");
        product.setCategory("Action figure");

        ReviewDocument review = new ReviewDocument();
        review.setId(1);
        review.setTitle("test");
        review.setReview("Testing review");
        review.setProduct(product);

        Comment comment = new Comment();
        comment.setId(1);
        comment.setHeading("Test Comment");
        comment.setComment("This is a comment body");

        List<Comment> commentList = new ArrayList<Comment>();
        commentList.add(comment);
        review.setComments(commentList);


        Assert.assertNotNull(commentList);
    }

    @Test
    public void testGetComments() {

        Product product = new Product();
        product.setId(1);
        product.setName("Forky");
        product.setDescription("It is a fork");
        product.setCategory("Action figure");

        ReviewDocument review = new ReviewDocument();
        review.setId(1);
        review.setTitle("test");
        review.setReview("Testing review");
        review.setProduct(product);

        Comment comment = new Comment();
        comment.setId(1);
        comment.setHeading("Test Comment");
        comment.setComment("This is a comment body");
        List<Comment> commentList = new ArrayList<Comment>();
        commentList.add(comment);
        review.setComments(commentList);

        Assert.assertNotNull(reviewDocumentRepository.save(review));

        //comments from the database
        Assert.assertNotNull(reviewDocumentRepository.findById(1).get().getComments());
        List<Comment> retrievedComments =
                reviewDocumentRepository.findById(1).get().getComments();
        assertEquals(1, retrievedComments.size());
        assertEquals(1, retrievedComments.get(0).getId().intValue());
        assertEquals("Test Comment", retrievedComments.get(0).getHeading());
        assertEquals("This is a comment body", retrievedComments.get(0).getComment());
    }


    }

