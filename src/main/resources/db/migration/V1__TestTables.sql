CREATE TABLE product (id integer auto_increment,category varchar(255) not null,name varchar(255) not null,description varchar(255) not null,primary key (id));

CREATE TABLE review (id integer auto_increment, title varchar(255) not null, review_text varchar(255) not null, product_id integer not null, primary key (id), constraint review_product_fk foreign key (product_id) references product (id));

CREATE TABLE comment (id integer auto_increment, heading varchar(255) not null, comment_text varchar(255) not null, review_id integer not null, primary key (id), constraint comment_review_fk foreign key (review_id) references review (id));

INSERT INTO product (category, name, description) VALUES ('Action Figure', 'Buzz Lightyear', 'Toy Story action figure');

INSERT INTO product (category, name, description) VALUES ('Doll', 'Malibu Stacy', 'Barbie Doll from The Simpsons');

INSERT INTO product (category, name, description) VALUES ('Doll', 'Raggedy Ann', 'Raggedy Ann 1950 collection');

INSERT INTO review (title, review_text, product_id) VALUES ('Average Doll', 'This doll is ok', 3);

INSERT INTO review (title, review_text, product_id) VALUES ('Great Doll', 'This doll is great', 3);

INSERT INTO comment (heading, comment_text, review_id) VALUES ('Bad Comment', 'I do no like this doll', 2);

INSERT INTO comment (heading, comment_text, review_id) VALUES ('Better Comment', 'I liked this review better than the other one', 2);