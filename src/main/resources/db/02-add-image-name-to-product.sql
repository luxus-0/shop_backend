--liquibase formatted sql
--changeset lnowogorski:2
alter table product add column Image varchar(125);