-- liquibase formatted sql
-- changeset lnowogorski:20
create table order_log(
    id bigint  generated always as identity primary key not null,
    order_id bigint not null,
    created timestamp not null,
    description text
);