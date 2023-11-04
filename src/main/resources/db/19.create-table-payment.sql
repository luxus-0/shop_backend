-- liquibase formatted sql
-- changeset lnowogorski:19
create table payment(
id serial primary key not null,
    name varchar(64) not null,
    type varchar(32) not null,
    isPayment boolean default false,
    description text
);

alter table "order" add payment_id bigint;