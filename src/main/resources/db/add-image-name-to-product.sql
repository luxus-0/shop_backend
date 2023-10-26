--liquibase formatted sql
--changeset lnowogorski:2
alter table PRODUCTS add image varchar(128) after currency