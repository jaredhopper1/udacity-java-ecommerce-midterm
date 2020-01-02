package com.udacity.course3.reviews.repos;

import com.udacity.course3.reviews.entities.Comment;
import com.udacity.course3.reviews.entities.Product;
import com.udacity.course3.reviews.entities.Review;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;


@RunWith(SpringRunner.class)
@DataJpaTest
public class CommentRepositoryTest {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private ProductRepository productRepository;

    private static Product product;
    private static Review review;
    private static Comment comment;

    /**
     * Initilization.
     */
    @BeforeClass
    public static void init() {
        product = new Product();
        product.setCategory("Action Figure");
        product.setName("Woody");
        product.setDescription("Toy Story Action Figure");
        review = new Review();
        review.setTitle("Good Review");
        review.setReview("This is a great toy");
        comment = new Comment();
        comment.setHeading("First Comment");
        comment.setComment("This is the first comment for testing");

    }

    /**
     * Test injected components are not null.
     */
    @Test
    public void testInjectedComponentsAreNotNull() {
        assertThat(productRepository).isNotNull();
        assertThat(commentRepository).isNotNull();
        assertThat(reviewRepository).isNotNull();
        assertThat(dataSource).isNotNull();
        assertThat(jdbcTemplate).isNotNull();
        assertThat(entityManager).isNotNull();
        assertThat(testEntityManager).isNotNull();
    }

    /**
     * Test save comment in review.
     */
    @Test
    public void testSaveCommentInReview() {
        Product savedProduct = productRepository.save(product);
        assertThat(savedProduct).isNotNull();
        review.setProduct(savedProduct);
        Review expectedReview = reviewRepository.save(review);
        assertThat(expectedReview).isNotNull();
        Optional<Review> optionalReview = reviewRepository.findById(expectedReview.getId());
        Review actualReview = optionalReview.get();
        assertThat(actualReview).isNotNull();
        assertTrue(actualReview.getId().equals(expectedReview.getId()));
        comment.setReview(expectedReview);
        Comment expectedComment = commentRepository.save(comment);
        Comment actualComment = commentRepository.findById(expectedComment.getId()).get();
        assertTrue(actualComment.getId().equals(expectedComment.getId()));
    }

    /**
     * Test find all comment by review.
     */
    @Test
    public void testFindAllCommentByReview() {
        Product savedProduct = productRepository.save(product);
        assertThat(savedProduct).isNotNull();
        review.setProduct(savedProduct);
        Review expectedReview = reviewRepository.save(review);
        assertThat(expectedReview).isNotNull();
        Optional<Review> optionalReview = reviewRepository.findById(expectedReview.getId());
        Review actualReview = optionalReview.get();
        assertThat(actualReview).isNotNull();
        assertTrue(actualReview.getId().equals(expectedReview.getId()));
        comment.setReview(expectedReview);
        Comment expectedComment = commentRepository.save(comment);
        List<Comment> actualCommentList = commentRepository.findAllByReview(expectedReview);
        assertTrue(actualCommentList.size() == 1);
        assertTrue(actualCommentList.get(0).getId().equals(expectedComment.getId()));
    }

}
