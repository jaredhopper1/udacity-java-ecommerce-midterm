package com.udacity.course3.reviews.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.udacity.course3.reviews.entities.ReviewDocument;
import com.udacity.course3.reviews.repos.ReviewDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.udacity.course3.reviews.entities.Comment;
import com.udacity.course3.reviews.entities.Review;
import com.udacity.course3.reviews.repos.CommentRepository;
import com.udacity.course3.reviews.repos.ReviewRepository;

/**
 * Spring REST controller for working with comment entity.
 */
@RestController
@RequestMapping("/comments")
public class CommentsController {

    // Completed: Wire needed JPA repositories here

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ReviewDocumentRepository reviewDocumentRepository;

    /**
     * Creates a comment for a review.
     *
     * 1. Add argument for comment entity. Use {@link RequestBody} annotation. - Completed
     * 2. Check for existence of review.
     * 3. If review not found, return NOT_FOUND.
     * 4. If found, save comment.
     *
     * @param reviewId The id of the review.
     */
    @RequestMapping(value = "/reviews/{reviewId}", method = RequestMethod.POST)
    public ResponseEntity<?> createCommentForReview(@PathVariable("reviewId") Integer reviewId,
                                                    @RequestBody Comment comment) {
        // find review in mysql
        Optional<Review> review = reviewRepository.findById(reviewId);
        if (!review.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        comment.setReview(review.get());
        // save in mysql
        Comment mysqlComment = commentRepository.save(comment);

        // find review in mongo
        Optional<ReviewDocument> mongoReviewOpt = reviewDocumentRepository.findById(reviewId);
        if (!mongoReviewOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        ReviewDocument mongoReview = mongoReviewOpt.get();
        mongoReview.addComments(mysqlComment);
        // save in mongo
        return ResponseEntity.ok(reviewDocumentRepository.save(mongoReview));
    }

    /**
     * List comments for a review.
     *
     * 2. Check for existence of review.
     * 3. If review not found, return NOT_FOUND.
     * 4. If found, return list of comments.
     *
     * @param reviewId The id of the review.
     */
    @RequestMapping(value = "/reviews/{reviewId}", method = RequestMethod.GET)
    public List<?> listCommentsForReview(@PathVariable("reviewId") Integer reviewId) {

        List<Comment> comments =
                commentRepository.findAllByReview(new Review(reviewId));

        Optional<ReviewDocument> mongoReviewOpt = reviewDocumentRepository.findById(reviewId);

        if (!mongoReviewOpt.isPresent()) {
            return new ArrayList<>();
        }

        ReviewDocument mongoReview = mongoReviewOpt.get();

        return mongoReview.getComments();
    }
}