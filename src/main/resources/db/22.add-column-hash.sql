-- liquibase formatted sql
-- changeset lnowogorski:23
alter table users add hash varchar(120);
-- changeset lnowogorski:24
alter table users add hash_date timestamptz;
