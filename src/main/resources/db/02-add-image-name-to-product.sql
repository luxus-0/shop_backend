--liquibase formatted sql
--changeset lnowogorski:2
alter table product add Image varchar(128) after currency;