CREATE SCHEMA IF NOT EXISTS testdb AUTHORIZATION sa;

CREATE TABLE IF NOT EXISTS testdb.category (
    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` varchar(32) UNIQUE
);

CREATE TABLE IF NOT EXISTS testdb.contact (
    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name varchar(128) NOT NULL,
    surname varchar(128) NOT NULL,
    email varchar(128) UNIQUE NOT NULL,
    phone varchar(16) NOT NULL,
    gender int,
    category_id int,
    property_id int,
    birthday date,
    created timestamp NOT NULL,
    updated timestamp
);

CREATE TABLE IF NOT EXISTS testdb.property (
    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` varchar(32) NOT NULL,
    category_id int
);

