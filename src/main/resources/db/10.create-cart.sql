--liquibase formatted sql
--changeset lnowogorski:9
create table cart(
    id bigint auto_increment PRIMARY KEY not null,
    created datetime not null
);

create table cartItem(
    id bigint not null auto_increment PRIMARY KEY,
    product_id bigint not null,
    quantity int,
    constraint fk_cart_item_product_id foreign key (product_id) references product(id)
);