CREATE TABLE sender (
                        id SERIAL PRIMARY KEY,
                        name VARCHAR(255) NOT NULL,
                        surname VARCHAR(255) NOT NULL,
                        pesel VARCHAR(11) NOT NULL,
                        address_id BIGINT,
                        contact_id BIGINT,
                        constraint fk_sender_address_id FOREIGN KEY (address_id) REFERENCES address(id),
                        constraint  fk_sender_contact_id FOREIGN KEY (contact_id) REFERENCES contact(id)
);