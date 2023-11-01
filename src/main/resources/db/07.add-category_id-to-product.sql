--liquibase formatted sql
--changeset lnowogorski:7
alter table product add category_id bigint;
alter table product add constraint fk_product_category_id foreign key(category_id) references category(id);