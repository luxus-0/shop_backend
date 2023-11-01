--liquibase formatted sql
--changeset lnowogorski:9
create table cart(
    id bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY not null,
    created timestamp not null
);

create table cartItem(
    id bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY not null,
    product_id bigint not null,
    quantity int,
    cart_id bigint not null ,
    constraint fk_cart_item_product_id foreign key (product_id) references product(id),
    constraint fk_cart_item_cart_id foreign key (cart_id) references cart(id)
);

ALTER TABLE cartItem ALTER COLUMN cart_id SET NOT NULL;