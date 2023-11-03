-- liquibase formatted sql
-- changeset lnowogorski:16
CREATE TABLE shipment (
                          id SERIAL primary key not null ,
                          trackingNumber uuid NOT NULL,
                          status VARCHAR(255) not null ,
                          shippingType VARCHAR(255) not null ,
                          deliveryDate TIMESTAMP not null ,
                          sender_id BIGINT,
                          recipient_id BIGINT,
                          constraint  fk_shipment_sender FOREIGN KEY (sender_id) REFERENCES sender(id),
                          constraint  fk_shipment_recipient FOREIGN KEY (recipient_id) REFERENCES recipient(id)
);