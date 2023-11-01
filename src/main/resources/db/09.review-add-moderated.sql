--liquibase formatted sql
--changeset lnowogorski:9
alter table review alter column moderated set default false;
