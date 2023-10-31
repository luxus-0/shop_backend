--liquibase formatted sql
--changeset lnowogorski:9
alter table review add moderated boolean default false;
