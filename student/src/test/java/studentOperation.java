import java.sql.*;

public class studentOperation implements studentInterface {
    Statement stm;
    Connection con = null;

    String address = "jdbc:mysql://localhost:3306/student_info";
    String userName = "root";
    String password = "Cat@8050";

    public void insertStudent() {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(address, userName, password);
            System.out.println("Connection Established Successfully");
            stm = con.createStatement();
            PreparedStatement pstm = con.prepareStatement("INSERT INTO STUDENT VALUES(?,?,?,?,?)");
//            Scanner sc=new Scanner(System.in);
//            String pri_name=sc.next();
//            String current_name=sc.next();
//            String sql1="update student set name=? where name=?";
//            PreparedStatement pstm=con.prepareStatement(sql1);
//            pstm.setString(1,pri_name);
//            pstm.setString(2,current_name);
            pstm.setString(1, "himesh");             //take buffer reader or Scanner class
            pstm.setString(2, String.valueOf(23));      //System.in
            pstm.setString(3, "CSE");
            pstm.setString(4, String.valueOf(2));
            pstm.setString(5, "mysore");
            int i = pstm.executeUpdate();
            System.out.println(i + " rows affected");
        } catch (ClassNotFoundException e) {
            System.out.println(e);
            System.out.println("failed..");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void removeStudent() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(address, userName, password);
            System.out.println("Connection Established Successfully");
//        stm = con.createStatement();
            PreparedStatement pstm = con.prepareStatement("DELETE FROM STUDENT WHERE USN = ?");
            pstm.setInt(1, 1);
            int i = pstm.executeUpdate();
            System.out.println(i + " rows affected");
        } catch (ClassNotFoundException e) {
            System.out.println(e);
            System.out.println("failed..");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void displayStudents() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(address, userName, password);
            System.out.println("Connected to DB bruhh...");
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM STUDENT WHERE USN = ?");
            pstm.setInt(1, 1);
            ResultSet rs = pstm.executeQuery();
            System.out.println("----------STUDENT DB----------");
            while(rs.next()){
                System.out.println(rs.getString("name")+rs.getInt("age")+rs.getString("branch")+ rs.getInt("usn")+rs.getString("place"));
            }
            System.out.println(rs + "rows affected");

        } catch (ClassNotFoundException e) {
            System.out.println(e);
        } catch (SQLException e) {
            System.out.println(e);
        }

    }
    public void exit(){

    }
}
