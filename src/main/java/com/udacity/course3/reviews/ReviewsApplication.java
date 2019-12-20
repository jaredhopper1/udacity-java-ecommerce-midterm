package com.udacity.course3.reviews;

import org.flywaydb.core.Flyway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.*;

@SpringBootApplication
public class ReviewsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReviewsApplication.class, args);

		try {
			try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdnd?user=root&password=Vbs$gh!Jl")) {
				System.out.println("Connected to " + conn.getMetaData().getDatabaseProductName());

				//Seed Data
				try (Statement stmt = conn.createStatement()) {
					String sqlCreateProduct = "CREATE TABLE IF NOT EXISTS product (id integer auto_increment,category varchar(255) not null,name varchar(255) not null,description varchar(255) not null,primary key (id))";
					stmt.executeUpdate(sqlCreateProduct);

					String sqlCreateReview = "CREATE TABLE IF NOT EXISTS review (id integer auto_increment, title varchar(255) not null, review_text varchar(255) not null, product_id integer not null, primary key (id), constraint review_product_fk foreign key (product_id) references product (id))";
					stmt.executeUpdate(sqlCreateReview);

					String sqlCreateComment = "CREATE TABLE IF NOT EXISTS comment (id integer auto_increment, heading varchar(255) not null, comment_text varchar(255) not null, review_id integer not null, primary key (id), constraint comment_review_fk foreign key (review_id) references review (id))";
					stmt.executeUpdate(sqlCreateComment);

					String sqlInsertProduct1 = "INSERT INTO product (category, name, description) " + "VALUES ('Action Figure', 'Buzz Lightyear', 'Toy Story action figure')";
					stmt.executeUpdate(sqlInsertProduct1);

					String sqlInsertProduct2 = "INSERT INTO product (category, name, description) " + "VALUES ('Doll', 'Malibu Stacy', 'Barbie Doll from The Simpsons')";
					stmt.executeUpdate(sqlInsertProduct2);

					String sqlInsertProduct3 = "INSERT INTO product (category, name, description) " + "VALUES ('Doll', 'Raggedy Ann', 'Raggedy Ann 1950 collection')";
					stmt.executeUpdate(sqlInsertProduct3);

					String sqlInsertReview1 = "INSERT INTO review (title, review_text, product_id) " + "VALUES ('Average Doll', 'This doll is ok', 3)";
					stmt.executeUpdate(sqlInsertReview1);

					String sqlInsertReview2 = "INSERT INTO review (title, review_text, product_id) " + "VALUES ('Great Doll', 'This doll is great', 3)";
					stmt.executeUpdate(sqlInsertReview2);

					String sqlInsertComment1 = "INSERT INTO comment (heading, comment_text, review_id)" + "VALUES ('Bad Comment', 'I do no like this doll', 2)";
					stmt.executeUpdate(sqlInsertComment1);

					String sqlInsertComment2 = "INSERT INTO comment (heading, comment_text, review_id)" + "VALUES ('Better Comment', 'I liked this review better than the other one', 2)";
					stmt.executeUpdate(sqlInsertComment2);

				}

			}
		} catch (SQLException ex) {
			System.out.println("SQL Exception " + ex.getMessage());
			System.out.println("SQL Exception " + ex.getSQLState());
			System.out.println("SQL Exception " + ex.getErrorCode());


			Flyway flyway = Flyway.configure().dataSource("jdbc:mysql://localhost:3306/jdnd", "root", "Vbs$gh!Jl").load();

			flyway.baseline();
			flyway.migrate();
		}

	}
}