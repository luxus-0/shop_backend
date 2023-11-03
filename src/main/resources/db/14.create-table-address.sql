-- liquibase formatted sql
-- changeset lnowogorski:14
create table address
(
    street  varchar(255) not null,
    zipCode varchar(255) not null,
    city    varchar(255) not null,
    customer_id varchar(255) not null,
    constraint fk_customer_address foreign key (customer_id) REFERENCES  "Customer"(id)
);