--liquibase formatted sql
--changeset lnowogorski:4
CREATE TABLE Image (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255),
    type VARCHAR(255),
    path VARCHAR(255)
    product_id INT,
    admin_product_id INT,
    FOREIGN KEY (fk_product_id) REFERENCES Product(id),
    FOREIGN KEY(fk_admin_product_id) REFERENCES AdminProduct(id)
);