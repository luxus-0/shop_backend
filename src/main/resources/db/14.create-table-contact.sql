-- liquibase formatted sql
-- changeset lnowogorski:14
create table contact(
    id serial primary key not null,
    email varchar(255) not null,
    phone varchar(255) not null,
    customer_id bigint not null,
    CONSTRAINT fk_customer_contact FOREIGN KEY (customer_id) REFERENCES "Customer"(id)
);