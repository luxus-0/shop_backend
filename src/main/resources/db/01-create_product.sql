--liquibase formatted sql
--changeset lnowogorski:1
CREATE TABLE product (
    id bigint PRIMARY KEY generated always as identity not null ,
    name VARCHAR(255) NOT NULL,
    category VARCHAR(255) NOT NULL,
    price DECIMAL(19, 2) NOT NULL,
    currency VARCHAR(3) NOT NULL,
    description TEXT NOT NULL
);