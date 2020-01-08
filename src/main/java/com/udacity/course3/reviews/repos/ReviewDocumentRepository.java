package com.udacity.course3.reviews.repos;

import com.udacity.course3.reviews.entities.ReviewDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReviewDocumentRepository extends MongoRepository<ReviewDocument, Integer> {
}
