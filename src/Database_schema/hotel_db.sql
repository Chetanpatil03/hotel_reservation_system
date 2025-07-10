show databases;

create database hotel_db;
use hotel_db;


CREATE TABLE reservation (
    res_id INT AUTO_INCREMENT PRIMARY KEY,
    guest_name VARCHAR(255) NOT NULL,
    room_no INT NOT NULL,
    contact_no VARCHAR(12) NOT NULL,
    resrvation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

describe reservation;


