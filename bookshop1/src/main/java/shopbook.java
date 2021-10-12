import java.util.Scanner;

public class shopbook {
    public static void main(String[] args) {
        System.out.println("Enter the option");
        System.out.print("1. LOGIN    2.REGISTER     3.Admin_BooKs ");
        authenticate aut = new authenticate();
        bookOperation bo = new bookOperation();

        Scanner sc = new Scanner(System.in);
        int option = sc.nextInt();

        switch (option) {
            case 1 -> aut.Authenticate();  // this has inner class to cartOperation
            case 2 -> aut.Register();
            case 3 -> bo.adminBooks();
        }
    }
}
