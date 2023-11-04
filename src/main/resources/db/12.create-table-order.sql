-- liquibase formatted sql
-- changeset lnowogorski:12
CREATE TABLE "order" (
                         id bigint generated always as identity primary key not null,
                         placeDate timestamp not null,
                         orderStatus varchar(255) not null,
                         grossValue decimal(6,2) not null
);

create table order_row(
                          id bigint generated always as identity primary key,
                          order_id bigint,
                          product_id bigint,
                          shipment_id bigint,
                          quantity int not null,
                          price decimal(6,2) not null,
                          constraint fk_order_row_order_id foreign key (order_id) references "order"(id),
                          constraint fk_order_row_product_id foreign key (product_id) references product(id),
                          constraint fk_order_row_shipment_id foreign key (shipment_id) references shipment(id)
);