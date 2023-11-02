--liquibase formatted sql
--changeset lnowogorski:10
CREATE TYPE Currency AS ENUM('PLN', 'EUR', 'USD');
create table AdminProduct
(
    id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name varchar(255) not null ,
    categoryId bigint not null ,
    price numeric not null ,
    currency Currency not null ,
    description varchar(255) not null,
    fullDescription varchar(255) not null
);