import com.sun.source.tree.LambdaExpressionTree;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class bookOperation extends authenticate implements bookInterface {
    public void adminBooks() {
        System.out.println("<<<<<---WELCOME BACK ADMIN---->>>>");
        Scanner sc = new Scanner(System.in);
        System.out.println("1. addBooks 2. removeBooks 3.displayBooks");
        int choice = sc.nextInt();
        switch (choice) {
            case 1 -> addNewBooks();
            case 2 -> removeBooks();
            case 3 -> displayBooks();

        }

    }

    public void addNewBooks() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(address, userName, password);
            System.out.println("Connecting Securely...");
            stm = con.createStatement();
            PreparedStatement bstm = con.prepareStatement("INSERT INTO BOOKS(BOOKID,BOOKNAME,STOCK,PRICE) VALUES(?,?,?,?)");
            Scanner ct = new Scanner(System.in);
            System.out.println("Enter the book id to add to Stock");
            int BOOKID = ct.nextInt();
            System.out.println("enter the book name");
            String BOOKNAME = ct.next();
            System.out.println("Enter the stock");
            int STOCK = ct.nextInt();
//            int COPIES = STOCK;
            System.out.println("enter the price");
            int PRICE = ct.nextInt();

            bstm.setInt(1, BOOKID);
            bstm.setString(2, BOOKNAME);
            bstm.setInt(3, STOCK);
            bstm.setInt(4, PRICE);

            int rs = bstm.executeUpdate();
            if (rs == 1) {
                System.out.println(rs + "rows affected");
            }
//            else{
//                System.out.println("FAILED TO ADD BOOKS..");
//            }

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);

        }
    }

    public void removeBooks() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(address, userName, password);
            System.out.println("Connecting Securely...");
            stm = con.createStatement();
            PreparedStatement dstm = con.prepareStatement("DELETE FROM BOOKS WHERE BOOKNAME = ?");
            Scanner ct = new Scanner(System.in);
            System.out.println("Enter the bookname to remove item");
            String BOOKNAME = ct.next();
            dstm.setString(1, BOOKNAME);
            int rs = dstm.executeUpdate();
            if (rs == 1) {
                System.out.println(rs + "rows affected");
            } else {
                System.out.println("Book not found...");
            }

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);

        }
    }
    public void displayBooks(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(address,userName,password);
            stm = con.createStatement();

            PreparedStatement dstm = con.prepareStatement("select * from books");
            ResultSet rs = dstm.executeQuery();
            System.out.println("* * ***  BOOKS IN STOCK  ****** * *");
            while(rs.next()){
                System.out.println(rs.getInt(1));
                System.out.println(rs.getString(2));
                System.out.println(rs.getInt(3));
                System.out.println(rs.getInt(4));

            }

        }catch (ClassNotFoundException  | SQLException e){
            System.out.println(e);
        }

    }
}