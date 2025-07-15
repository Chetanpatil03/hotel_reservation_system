# 🏨 Hotel Reservation System (Mini JDBC Project)

A simple console-based Hotel Reservation System using Java and JDBC. This mini project demonstrates basic database operations like inserting guest details and viewing reservations using MySQL.

---

## 📁 Project Structure

<pre>
HotelReservationSystem/
│
├── .idea/ # IntelliJ project files
├── src/
│ ├── Database_schema/
│ │ └── hotel_db.sql # SQL script to create and initialize the database
│ └── Main.java # Contains JDBC logic for reservation handling
├── .gitignore
└── HotelReservationSystem.iml # IntelliJ project file
</pre>


---

## ⚙️ Technologies Used

- Java (JDK 8+)
- JDBC
- MySQL
- IntelliJ IDEA

---

## 🧠 Features

- Add guest reservation data into database
- View all reservation records
- Uses `Statement` and `Connection` classes only (no advanced concepts)
- Simple and beginner-friendly

---

## 🗄️ Database Details

### Database Name:
<pre>
  hotel_db
</pre>


### Table: `reservation`

| Field              | Type          | Description                    |
|-------------------|---------------|--------------------------------|
| `res_id`          | int           | Primary key, auto-increment    |
| `guest_name`      | varchar(255)  | Name of the guest              |
| `room_no`         | int           | Room number                    |
| `contact_no`      | varchar(12)   | Contact number                 |
| `reservation_date`| timestamp     | Date of reservation (auto set) |

📂 SQL File: 
<pre>
  src/Database_schema/hotel_db.sql
</pre>

---

## ▶️ How to Run

1. Clone the repository:
<pre>
   git clone https://github.com/your-username/HotelReservationSystem.git
   cd HotelReservationSystem
</pre>

2. Open the project in IntelliJ IDEA.
3. Import the `hotel_db.sql` in MySQL:
   
<pre>
  SOURCE path_to_project/src/Database_schema/hotel_db.sql;
</pre>

4. Ensure MySQL is running and update DB credentials in Main.java.
5. Run `Main.java` to access the reservation system in the console.

## 💡 Example Console Output
<pre>
  Welcome to Hotel Reservation System
  1. Add Reservation
  2. View All Reservations
  3. Exit
</pre>
   
