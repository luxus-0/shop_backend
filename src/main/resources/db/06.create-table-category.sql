--liquibase formatted sql
--changeset lnowogorski:6
create table category (
    id bigint GENERATED ALWAYS AS IDENTITY NOT NULL ,
    name varchar(255) not null,
    description text,
    slug varchar(255) not null
    )