--liquibase formatted sql
--changeset lnowogorski:6
create table category (
    id bigint not null auto_increment primary key,
    name varchar(255) not null,
    description text,
    slug varchar(255) not null
    )