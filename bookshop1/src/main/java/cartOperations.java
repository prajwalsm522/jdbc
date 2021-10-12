import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.*;
import java.util.Scanner;

public class cartOperations extends authenticate{
    Scanner sc = new Scanner(System.in);
    public void addCartItems() throws SQLException {
        try{
            int copies = 0;
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(address,userName,password);
            System.out.println("connecting..");
            System.out.println("Enter the book name");
            String BOOKNAME = sc.next();
            System.out.println("Enter the bookID");
            int bookid = sc.nextInt();
            System.out.println("Enter the number of copies");
            int addCopies = sc.nextInt();
            System.out.println("Enter the User ID");
            int USERID = sc.nextInt();
            PreparedStatement cstm = con.prepareStatement("SELECT STOCK FROM BOOKS WHERE BOOKNAME =  '"+ BOOKNAME + "'");

            ResultSet rs = cstm.executeQuery();
            while (rs.next()){
                copies = rs.getInt(1);
//                System.out.println("working");
            }
            if (copies>=addCopies){

              String sqlAdd = "INSERT INTO CART VALUES(" +addCopies+",'"+ BOOKNAME +"' ,"+ USERID+ ","+ bookid +","+ USERID+")";

//                PreparedStatement cstm3 = con.prepareStatement("INSERT INTO CART VALUES(?,?,?,?,?)");
//                cstm3.setInt(1,addCopies);
//                cstm3.setString(2,BOOKNAME);
//                cstm3.setInt(3,USERID);
//                cstm3.setInt(4,bookid);
//                cstm3.setInt(5,USERID);

                int status = cstm.executeUpdate(sqlAdd);


                copies = copies-addCopies;
                if(status == 0){
                    System.out.println("ENTER THE DIFFERENT BOOKNAME");
                }
                else {
                    System.out.println("added books to cart");
                }
                if (status > 0){
                    if(copies > 0){
                        PreparedStatement cstm2 = con.prepareStatement("UPDATE BOOKS SET COPIES= " + copies + "where bookname="+ BOOKNAME);
                        int statusbook = cstm2.executeUpdate();
                    }
                }
            }
            else {
                System.out.println("ENTERED BOOK IS NOT IN BOOK STORE");
           }


        } catch (ClassNotFoundException | SQLException e){
            System.out.println(e);
        }
    }

        public void displayCart() throws Exception{                                                                                        //displayCart starts here
                try{
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    con = DriverManager.getConnection(address,userName,password);
                    System.out.println("Connecting...");

                    PreparedStatement cstm = con.prepareStatement("SELECT * FROM CART");
                    ResultSet rs = cstm.executeQuery();
                    System.out.println("----BOOKS____CART----");
                    while (rs.next()){
                        System.out.println(rs.getInt(1)+" ");
                        System.out.println(rs.getInt(2) +" ");
                        System.out.println(rs.getString(3)+" ");
                        System.out.println(rs.getInt(4)+" ");
                        System.out.println(rs.getInt(5)+" ");
                    }

                }catch (ClassNotFoundException  | SQLException e){
                    System.out.println(e);

                }


        }

    public void removeCartItems(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(address, userName, password);
            System.out.println("Connecting Securely...");
            stm = con.createStatement();
            PreparedStatement dstm = con.prepareStatement("DELETE FROM CART WHERE BOOKS_BOOKID = ?");
            Scanner ct = new Scanner(System.in);
            System.out.println("Enter the book id to remove item");
            int BOOKS_BOOKID = ct.nextInt();
            dstm.setInt(1,BOOKS_BOOKID);
            int rs = dstm.executeUpdate();
            if(rs == 1){
                System.out.println(rs + "rows affected");
            }
            else{
                System.out.println("Book not found...");
            }

        }catch (ClassNotFoundException | SQLException e){
            System.out.println(e);

        }

    }

}
