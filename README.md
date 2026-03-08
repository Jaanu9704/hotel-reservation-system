# Hotel Reservation System

A console-based Hotel Reservation System developed using Java, JDBC, and MySQL.  
The system allows users to manage hotel room reservations through a menu-driven interface.

---

## Features

- Reserve a room
- View all reservations
- Get room number using reservation ID and guest name
- Update reservation details
- Delete reservations
- Menu-driven console interface

---

## Technologies Used

- Java
- JDBC (Java Database Connectivity)
- MySQL

---

## Project Structure

src/com/hotel
 ├── HotelReservationSystem.java
 └── ReservationService.java

---

## Database Setup

Run the following SQL commands in MySQL to create the database and table:

```sql
CREATE DATABASE hotel_db;

USE hotel_db;

CREATE TABLE reservations (
    reservation_id INT AUTO_INCREMENT PRIMARY KEY,
    guest_name VARCHAR(100),
    room_number INT,
    contact_number VARCHAR(15),
    reservation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
