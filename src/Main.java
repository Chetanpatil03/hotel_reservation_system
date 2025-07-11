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
//            thin, network,native api, jdbc-odbc drivers are present.
        }
        catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        try{
            Connection connection = DriverManager.getConnection(url,user,pass);

            while (true){
//                menu driven approach
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

                Statement statement = connection.createStatement();

                switch (choice){
                    case 1 :
                        reserveRoom(statement,sc);
                        break;
                    case 2 :
                        viewReservation(statement);
                        break;
                    case 3 :
                        getRoomNumber(statement,sc);
                        break;
                    case 4 :
                        updateReservation(statement,sc);
                        break;
                    case 5 :
                        deleteReservation(statement,sc);
                        break;
                    case 0 :
                        exit();
                        sc.close();
                        return;
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

    public static void reserveRoom(Statement statement,Scanner sc){
        System.out.print("Enter Guest name : ");
        String guestName = sc.next();
        sc.nextLine(); //for new line character
        System.out.println("Enter Room number : ");
        int roomNum = sc.nextInt();
        System.out.println("Enter contact number : ");
        String contactNumber = sc.next();

        String sql = "INSERT INTO reservation(guest_name,room_no,contact_no) " +
                "VALUES ('"+ guestName+"','"+roomNum+"','"+contactNumber+"') ";

        try{
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
    public static void viewReservation(Statement statement) throws SQLException{
        String sql = "SELECT res_id,guest_name,room_no,contact_no,reservation_date FROM reservation";

        try(ResultSet resultSet = statement.executeQuery(sql)) {

            System.out.println("Current Reservations:");
            System.out.println("+----------------+-----------------+---------------+----------------------+-------------------------+");
            System.out.println("| Reservation ID | Guest           | Room Number   | Contact Number      | Reservation Date        |");
            System.out.println("+----------------+-----------------+---------------+----------------------+-------------------------+");

            while (resultSet.next()) { //check the result set have content or not return true if it has content.

                int reservationId = resultSet.getInt("res_id");
                String guestName = resultSet.getString("guest_name");
                int roomNo = resultSet.getInt("room_no");
                String contactNo = resultSet.getString("contact_no");
                String reservationDate = resultSet.getTimestamp("reservation_date").toString(); //timestamp into string.

                System.out.printf("| %-14d | %-15s | %-13d | %-20s | %-19s   |\n",
                        reservationId, guestName, roomNo, contactNo, reservationDate);
            }

            System.out.println("+----------------+-----------------+---------------+----------------------+-------------------------+");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void getRoomNumber(Statement statement, Scanner sc){

        try{
            System.out.print("Enter reservation ID : ");
            int reservationId = sc.nextInt();
//            sc.nextLine();
            System.out.println("Enter guest name : ");
            String guestName = sc.next();

            String sql = "SELECT room_no FROM reservation WHERE res_id = "+reservationId +
                    " AND guest_name = '"+guestName+"' ";

            try(ResultSet resultSet = statement.executeQuery(sql) ){

                if (resultSet.next()){
                    int roomNo = resultSet.getInt("room_no");

                    System.out.println("Room number for Reservation ID" +reservationId +
                            " and Guest "+guestName+" is : "+roomNo);
                }
                else {
                    System.out.println("Reservation Not found for given ID and Guest name");
                }

            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }


    public static void updateReservation(Statement statement,Scanner sc){

        try{
            System.out.print("Enter reservation ID to update : ");
            int reservationID = sc.nextInt();
            sc.nextLine(); //consume new line character

            if (!reservationExist(statement,reservationID)){
                System.out.println("Reservation not exist for given ID");
                return;
            }

            System.out.print("Enter new guest name : ");
            String newGuestName = sc.nextLine();
            System.out.print("Enter new room number : ");
            int newRoomNum = sc.nextInt();
            System.out.print("Enter new Contact number : ");
            String newContactNo = sc.next();

            String sql = "UPDATE reservation SET guest_name = '"+newGuestName+"' , " +
                    "room_no = '"+newRoomNum+"' , contact_no = '"+newContactNo+"'" +
                    "WHERE res_id = '"+reservationID+"'";

            try {
                int affectedRow = statement.executeUpdate(sql);
                if (affectedRow > 0){
                    System.out.println("Reservation updated successfully...");
                }
                else {
                    System.out.println("Reservation update FAILED..");
                }
            }
            catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public static void deleteReservation(Statement statement, Scanner sc){
        try {
            System.out.print("Enter reservation ID to Delete : ");
            int reservationID = sc.nextInt();
            sc.nextLine(); //consume new line character

            if (!reservationExist(statement,reservationID)){
                System.out.println("Reservation not exist for given ID");
                return;
            }

            String sql = "DELETE FROM reservation where res_id = '"+reservationID+"'";

            try{
                int affectedRow = statement.executeUpdate(sql);
                if (affectedRow > 0){
                    System.out.println("Deletion of reservation successfully...");
                }
                else {
                    System.out.println("Deletion of reservation is FAILED...");
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void exit() throws InterruptedException{
        System.out.println("Exiting System ");
        int i = 5;
        while(i!= 0 ){
            System.out.print(".");
            Thread.sleep(450);
            i--;
        }
        System.out.println();
        System.out.println("Thank you for using Hotel Reservation System.!!!");
    }

    public static boolean reservationExist(Statement statement,int reservationID){
        String sql = "SELECT res_id from reservation";

        try(ResultSet resultSet = statement.executeQuery(sql)){

            return resultSet.next(); //if there is a result then reservation exists.

        }
        catch (SQLException e ){
            e.printStackTrace();
            return false;
        }
    }
}