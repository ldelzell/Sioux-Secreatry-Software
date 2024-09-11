ALTER TABLE appointment
    ADD COLUMN client_name VARCHAR(255) NOT NULL,
    ADD COLUMN client_email VARCHAR(255) NOT NULL,
    ADD COLUMN license_plate VARCHAR(255) NOT NULL;