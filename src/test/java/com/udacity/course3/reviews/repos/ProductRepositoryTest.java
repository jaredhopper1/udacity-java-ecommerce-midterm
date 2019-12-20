package com.udacity.course3.reviews.repos;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.udacity.course3.reviews.entities.Product;



@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductRepositoryTest {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private ProductRepository productRepository;

    private static Product product;

    /**
     * Initilization.
     */
    @BeforeClass
    public static void init() {
        product = new Product();
        product.setId(1);
        product.setCategory("Action Figure");
        product.setName("Woody");
        product.setDescription("Toy Story Action Figure");
    }

    /**
     * Test injected components are not null.
     */
    @Test
    public void testInjectedComponentsAreNotNull() {
        assertThat(productRepository).isNotNull();
        assertThat(dataSource).isNotNull();
        assertThat(jdbcTemplate).isNotNull();
        assertThat(entityManager).isNotNull();
        assertThat(testEntityManager).isNotNull();
    }

    /**
     * Test save product and find product.
     */
    @Test
    public void testSaveProductAndFindProduct() {
        Product expectedProduct = productRepository.save(product);
        assertThat(expectedProduct).isNotNull();
        Product actualProduct = productRepository.findById(expectedProduct.getId()).get();
        assertTrue(expectedProduct.getId().equals(actualProduct.getId()));
    }

    /**
     * Test find all products.
     */
    @Test
    public void testFindAllProducts() {
        Product expectedProduct = productRepository.save(product);
        assertThat(expectedProduct).isNotNull();
        List<Product> actualProductList = productRepository.findAll();
        assertTrue(actualProductList.size() == 1);
        Product actualProduct = actualProductList.get(0);
        assertTrue(expectedProduct.getId().equals(actualProduct.getId()));
    }
}
