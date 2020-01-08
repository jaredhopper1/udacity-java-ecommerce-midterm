CREATE TABLE product (id integer auto_increment,category varchar(255) not null,name varchar(255) not null,description varchar(255) not null,primary key (id));

CREATE TABLE review (id integer auto_increment, title varchar(255) not null, review_text varchar(255) not null, product_id integer not null, primary key (id), constraint review_product_fk foreign key (product_id) references product (id));

CREATE TABLE comment (id integer auto_increment, heading varchar(255) not null, comment_text varchar(255) not null, review_id integer not null, primary key (id), constraint comment_review_fk foreign key (review_id) references review (id));


