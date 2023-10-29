--liquibase formatted sql
--changeset lnowogorski:5
alter table product add full_description not null varchar(255) after description;