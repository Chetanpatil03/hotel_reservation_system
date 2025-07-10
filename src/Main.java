import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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
                System.out.println("6. EXIT");
                System.out.print("\nEnter Choice : ");
                int choice = sc.nextInt();

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}