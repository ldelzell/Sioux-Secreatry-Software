-- V3__UpdateAppointmentTable.sql

-- Start a transaction
START TRANSACTION;

-- Drop the unnecessary columns
ALTER TABLE appointment
    DROP COLUMN date,
    DROP COLUMN time;

-- Add the new columns
ALTER TABLE appointment
    ADD COLUMN subject VARCHAR(255) NOT NULL,
    ADD COLUMN start_time DATETIME NOT NULL,
    ADD COLUMN end_time DATETIME NOT NULL,
    ADD COLUMN is_all_day BOOLEAN NOT NULL;

-- Commit the transaction
COMMIT;
