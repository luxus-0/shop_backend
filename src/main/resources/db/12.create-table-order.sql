-- liquibase formatted sql
-- changeset lnowogorski:12
CREATE TABLE "order" (
                         id bigint generated always as identity primary key not null,
                         placeDate timestamp not null,
                         orderStatus varchar(255) not null,
                         grossValue decimal(6,2) not null
);

create table order_row(
                          id bigint generated always as identity primary key not null,
                          order_id bigint not null,
                          product_id bigint not null,
                          quantity int not null,
                          price decimal(6,2) not null,
                          constraint fk_order_row_order_id foreign key (order_id) references "order"(id),
                          constraint fk_order_row_product_id foreign key (product_id) references product(id)
);