--liquibase formatted sql
--changeset lnowogorski:3
alter table PRODUCT add slug varchar(255) after image;
alter table PRODUCT add constraint product_slug unique key(slug);