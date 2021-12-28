INSERT INTO users (username, password, enabled, email)
VALUES
('user', '$2y$10$pn4mvxgRMiFSelR/8LbTE.VgPVF4eYQFR0bd.vmkznvnesWfnUwxK', TRUE, 'dummy@novi.nl'),
('admin', '$2y$10$pn4mvxgRMiFSelR/8LbTE.VgPVF4eYQFR0bd.vmkznvnesWfnUwxK', TRUE, 'dummy@novi.nl');

/*In de database moet je ROLE_ gebruiken bij authority*/
INSERT INTO authorities(username, authority)
VALUES
('user', 'ROLE_USER'),
('admin', 'ROLE_USER'),
('admin', 'ROLE_ADMIN');

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

INSERT INTO inspections (appointment_date, appointment_status, car_id)
VALUES
    ('2021-12-28', 'APPOINTMENT_SCHEDULED', 1),
    ('2021-12-27', 'APPOINTMENT_SCHEDULED', 2),
    ('2021-12-26', 'APPOINTMENT_SCHEDULED',1);

