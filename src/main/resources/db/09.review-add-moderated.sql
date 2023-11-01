--liquibase formatted sql
--changeset lnowogorski:9
alter table review add moderated set default false;
