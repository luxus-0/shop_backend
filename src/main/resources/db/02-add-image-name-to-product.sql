--liquibase formatted sql
--changeset lnowogorski:2
alter table product add Images varchar(128) after currency;