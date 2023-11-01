--liquibase formatted sql
--changeset lnowogorski:8
insert into category(id, name, description, slug) values (1, 'Phone', 'Phone-1','dama')
update product set category_id = 1 WHERE category_id > 0;
alter table product alter column categoryId type bigint;