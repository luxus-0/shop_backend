-- liquibase formatted sql
-- changeset lnowogorski:15
create table address
(
    id serial primary key not null,
    street  varchar(255) not null,
    zipCode varchar(255) not null,
    city    varchar(255) not null,
    customer_id bigint not null,
    constraint fk_customer_address foreign key (customer_id) REFERENCES  "Customer"(id)
);