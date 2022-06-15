CREATE SCHEMA my_customers AUTHORIZATION postgres;
CREATE TABLE my_customers.customer (
	id varchar NULL,
	first_name varchar NULL,
	last_name varchar NULL
);
INSERT INTO my_customers.customer (id, first_name, last_name) VALUES ('123', 'Martin', 'Klausen');
INSERT INTO my_customers.customer (id, first_name, last_name) VALUES ('456', 'Nancy', 'Müller');
INSERT INTO my_customers.customer (id, first_name, last_name) VALUES ('789', 'Karl', 'Vogel');