-- liquibase formatted sql
-- changeset lnowogorski:13
CREATE TABLE "Customer" (
    id bigint generated always as identity primary key not null,
    firstName varchar(255) not null,
    lastName varchar(255) not null,
    pesel varchar(255) not null,
    order_id bigint not null ,
   constraint fk_customer_order FOREIGN KEY (order_id) REFERENCES "order"(id)
);