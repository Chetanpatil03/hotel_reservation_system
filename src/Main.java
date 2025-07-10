import java.sql.*;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

        private static final String url = "jdbc:mysql://localhost:3306/hotel_db";
        private static final String user = "root";
        private static final String pass = "root";


    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Scanner sc = new Scanner(System.in);

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
//            loading the database driver that are com.mysql.cj package
        }
        catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        try{
            Connection connection = DriverManager.getConnection(url,user,pass);

            while (true){
                System.out.println();
                System.out.println("HOTEL MANAGEMENT SYSTEM ");
                System.out.println("1. Reserve a room");
                System.out.println("2. View Reservations");
                System.out.println("3. Get Room number");
                System.out.println("4. Update Reservations");
                System.out.println("5. Delete Reservations");
                System.out.println("0. EXIT");
                System.out.print("\nEnter Choice : ");
                int choice = sc.nextInt();

                switch (choice){
                    case 0 :
                        exit();
                    case 1 :
                        reserveRoom(connection,sc);
                        break;
                    case 2 :
                        viewReservation(connection);
                        break;
                    case 3 :
                        getRoomNumber();
                        break;
                    case 4 :
                        updateReservation();
                        break;
                    case 5 :
                        deleteReservation();
                        break;
                    default:
                        System.out.println("Invalid choice... Try again");

                }

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void reserveRoom(Connection connection,Scanner sc){
        System.out.print("Enter Guest name : ");
        String guestName = sc.next();
        sc.nextLine();
        System.out.println("Enter Room number : ");
        int roomNum = sc.nextInt();
        System.out.println("Enter contact number : ");
        String contactNumber = sc.next();

        String sql = "INSERT INTO reservation(guest_name,room_no,contact_no) " +
                "VALUES ('"+ guestName+"','"+roomNum+"','"+contactNumber+"') ";

        try(Statement statement = connection.createStatement()){
            int affectedRows = statement.executeUpdate(sql);
            if (affectedRows > 0){
                System.out.println("Reservation successful");
            }
            else {
                System.out.println("Reservation failed");
            }
        }
        catch (SQLException e){
//            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }
    public static void viewReservation(Connection connection) throws SQLException{
        String sql = "SELECT res_id,guest_name,room_no,contact_no,reservation_date FROM reservation";

        try(Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql)) {

            System.out.println("Current Reservations:");
            System.out.println("+----------------+-----------------+---------------+----------------------+-------------------------+");
            System.out.println("| Reservation ID | Guest           | Room Number   | Contact Number      | Reservation Date        |");
            System.out.println("+----------------+-----------------+---------------+----------------------+-------------------------+");

            while (resultSet.next()) {
                int reservationId = resultSet.getInt("res_id");
                String guestName = resultSet.getString("guest_name");
                int roomNo = resultSet.getInt("room_no");
                String contactNo = resultSet.getString("contact_no");
                String reservationDate = resultSet.getTimestamp("reservation_date").toString();

                System.out.printf("| %-14d | %-15s | %-13d | %-20s | %-19s   |\n",
                        reservationId, guestName, roomNo, contactNo, reservationDate);
            }

            System.out.println("+----------------+-----------------+---------------+----------------------+-------------------------+");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void getRoomNumber(){

    }
    public static void updateReservation(){

    }
    public static void deleteReservation(){

    }
    public static void exit(){

    }
}