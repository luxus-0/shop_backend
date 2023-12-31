--liquibase formatted sql
--changeset lnowogorski:4


CREATE TABLE Image (
                       id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                       name VARCHAR(255),
                       type VARCHAR(255),
                       path VARCHAR(255),
                       product_id INT,
                       admin_product_id INT,
                       FOREIGN KEY (product_id) REFERENCES Product(id),
                       FOREIGN KEY (admin_product_id) REFERENCES AdminProduct(id)
);