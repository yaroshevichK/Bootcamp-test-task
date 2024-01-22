use bootcamp;

DROP TABLE IF EXISTS users;
CREATE TABLE users
(
    id          int auto_increment primary key,
    first_name  varchar(20) not null,
    surname     varchar(40) not null,
    middle_name varchar(40) not null,
    email       varchar(50) not null,
    role        varchar(20) not null
);

INSERT INTO users (first_name, surname, middle_name, email, role)
VALUES ('Jeremie', 'Christiansen', 'Parker', 'Maude_Harris@yahoo.com', 'ADMINISTRATOR'),
       ('Sandra', 'Dach', 'Phoenix', 'Mittie.Orn98@yahoo.com', 'ADMINISTRATOR'),
       ('Alysa', 'Barton', 'Billie', 'Melody74@hotmail.com', 'SECURE_API_USER'),
       ('Olga', 'Bergstrom', 'Skyler', 'Angel91@yahoo.com', 'SECURE_API_USER'),
       ('Sheridan', 'Marvin', 'Quinn', 'Julianne_Schoen71@yahoo.com', 'SALE_USER'),
       ('Chris', 'Auer', 'North', 'Tobin21@gmail.com', 'SALE_USER'),
       ('Emanuel', 'Baumbach', 'Austin', 'Durward_Cole@gmail.com', 'SALE_USER'),
       ('Arne', 'Olson', 'Billie', 'Rosalee4@yahoo.com', 'SECURE_API_USER'),
       ('Oliver', 'Beatty', 'Sawyer', 'Anastasia38@yahoo.com', 'SECURE_API_USER'),
       ('Thaddeus', 'Schultz', 'Shiloh', 'Sydni23@yahoo.com', 'CUSTOMER_USER'),
       ('Willard', 'Lind', 'Addison', 'Jason_Deckow@yahoo.com', 'CUSTOMER_USER'),
       ('Tre', 'Hamill', 'Cameron', 'Jeffrey.Will77@yahoo.com', 'CUSTOMER_USER'),
       ('Leola', 'Hintz', 'Corey', 'Berta88@hotmail.com', 'SECURE_API_USER'),
       ('Zelma', 'Turner', 'Leslie', 'Cody94@yahoo.com', 'CUSTOMER_USER'),
       ('Amely', 'Beier', 'Greer', 'Alyce80@hotmail.com', 'SALE_USER');