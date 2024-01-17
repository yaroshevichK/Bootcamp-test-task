CREATE TABLE IF NOT EXISTS users
(
    id          int auto_increment primary key,
    first_name  varchar(20) not null,
    surname     varchar(40) not null,
    middle_name varchar(40) not null,
    email       varchar(50) not null,
    role        varchar(20) not null
);