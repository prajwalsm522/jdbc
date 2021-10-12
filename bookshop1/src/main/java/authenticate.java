import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.*;
import java.util.Scanner;

public class authenticate implements userInterface {
    Statement stm;
    Connection con = null;
    String address = "jdbc:mysql://localhost:3306/bookstore";
    String userName = "root";
    String password = "Cat@8050";


    @Override
    public void Authenticate() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(address, userName, password);
            System.out.println("Connecting Securely...");
            stm = con.createStatement();
            PreparedStatement pstm = con.prepareStatement("select * from user where username = ? and password = ?");
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter username: ");
            String username = sc.next();
            System.out.println("Enter password: ");
            String password = sc.next();
            pstm.setString(1, username);
            pstm.setString(2, password);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                System.out.println("Login Successfull");
                Scanner op = new Scanner(System.in);
                System.out.println("enter choice");
                System.out.println("1:add items to cart");
                System.out.println("2:display Cart");
                System.out.println("3:remove items");
                int choice = op.nextInt();
                cartOperations cr = new cartOperations();

                switch (choice) {
                    case 1:
                        cr.addCartItems();
                        break;

                    case 2:
                        cr.displayCart();
                        break;

                    case 3:
                        cr.removeCartItems();
                        break;

                }

            } else {
                System.out.println("Login failed! Invalid username or password");
            }


        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void Register() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(address, userName, password);
            System.out.println("Connecting Securely...");
            stm = con.createStatement();
            PreparedStatement pstm = con.prepareStatement("INSERT INTO USER(USERNAME,PASSWORD) VALUES(?,?)");
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter username: ");
            String username = sc.next();
            System.out.println("Enter password: ");
            String password = sc.next();

            pstm.setString(1, username);
            pstm.setString(2, password);

            int i = pstm.executeUpdate();
            System.out.println(i + "rows affected");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);

        }
    }


}
