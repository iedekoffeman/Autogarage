INSERT INTO users (username, password, enabled, email)
VALUES
('user', 'password', TRUE, 'dummy@novi.nl'),
('admin', 'password', TRUE, 'dummy@novi.nl');

INSERT INTO customers (firstname, lastname)
VALUES
('Iede', 'Koffeman'),
('Pieter', 'Post'),
('Sandra', 'van Schelt');

INSERT INTO cars (licenseplate, customer_id)
VALUES
    ('24-XZ-ZG', 1),
    ('GT-512-Z', 1),
    ('XB-392-J', 2);

