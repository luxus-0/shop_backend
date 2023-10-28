--liquibase formatted sql
--changeset lnowogorski:3
alter table product add slug varchar(255) after image;
alter table product add constraint product_slug unique key(slug);