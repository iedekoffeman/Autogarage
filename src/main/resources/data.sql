INSERT INTO users (username, password, enabled, email)
VALUES
('monteur', '$2y$10$pn4mvxgRMiFSelR/8LbTE.VgPVF4eYQFR0bd.vmkznvnesWfnUwxK', TRUE, 'dummy@novi.nl'),
('administratief_medewerker', '$2y$10$pn4mvxgRMiFSelR/8LbTE.VgPVF4eYQFR0bd.vmkznvnesWfnUwxK', TRUE, 'dummy@novi.nl');

/*In de database moet je ROLE_ gebruiken bij authority*/
INSERT INTO authorities(username, authority)
VALUES
('monteur', 'ROLE_MONTEUR'),
('administratief_medewerker', 'ROLE_ADMINISTRATIEFMEDEWERKER');

INSERT INTO customers (firstname, lastname)
VALUES
('Iede', 'Koffeman'),
('Pieter', 'Post'),
('Sandra', 'van Schelt'),
('Jan', 'Janssen'),
('Berry', 'de Vries');


INSERT INTO cars (licenseplate, customer_id)
VALUES
    ('24-XZ-ZG', 1),
    ('GT-512-Z', 1),
    ('XB-392-J', 2);

INSERT INTO inspections (appointment_date, appointment_status, car_id)
VALUES
    ('2021-12-28', 'APPOINTMENT_SCHEDULED', 1),
    ('2021-12-27', 'APPOINTMENT_SCHEDULED', 2);

INSERT INTO repairs (appointment_date, appointment_status, car_id)
VALUES
    ('2022-01-28', 'APPOINTMENT_SCHEDULED', 1),
    ('2022-01-27', 'APPOINTMENT_SCHEDULED', 2),
    ('2022-02-05', 'REPAIR_COMPLETED', 1);

INSERT INTO deficiencies (description, inspection_id)
VALUES
    ('Remvloeistof op', 1),
    ('Lamp rechtsvoor kapot', 1),
    ('Luchtfilter aan vervanging toe', 1),
    ('Carburateur moet vervangen worden', 1),
    ('Koppakking is lek', 2),
    ('Remschijven moeten vervangen worden', 2),
    ('Vloeistoffen moeten bijgevuld worden', 2);

INSERT INTO items(name, price, repair_id)
VALUES
    ('Remvloeistof vervangen', 11.99, 1),
    ('Remvloeistof Motul 100948', 10.00, 1),
    ('Lamp rechtsvoor vervangen', 5.30, 1),
    ('Lamp 5v', 1.99, 1),
    ('Luchtfilter Bosch volvo', 8.82, 1),
    ('Carburateur vervangen', 160.25, 1),
    ('Carburatuer Type 24mm', 45.45, 1),
    ('Koppakking vervangen', 230.25, 2),
    ('Koppakking Elring', 64.74, 2),
    ('Remschijven vervangen', 30.25, 2),
    ('Remschijven Bosch', 20.25, 2 ),
    ('Vloeistoffen bijgevuld', 15.99, 2),
    ('Luchtfilter Bosch volvo', 8.82, 3),
    ('Carburateur vervangen', 160.25, 3),
    ('Carburatuer Type 24mm', 45.45, 3),
    ('Koppakking vervangen', 230.25, 3),
    ('Koppakking Elring', 64.74, 3),
    ('Remschijven vervangen', 30.25, 3),
    ('Remschijven Bosch', 20.25, 3 ),
    ('Vloeistoffen bijgevuld', 15.99, 3);


