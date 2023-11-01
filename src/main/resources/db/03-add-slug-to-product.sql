--liquibase formatted sql
--changeset lnowogorski:3
alter table product add constraint product_slug unique(slug);