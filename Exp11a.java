import com.healthmarketscience.jackcess.*;
import java.io.File;
import java.sql.*;
import java.util.Scanner;

public class Exp11a {

    static void createTable(Database db) throws Exception {
        new TableBuilder("Student")
            .addColumn(new ColumnBuilder("Name", DataType.TEXT))
            .addColumn(new ColumnBuilder("Email", DataType.TEXT))
            .addColumn(new ColumnBuilder("Phone", DataType.TEXT))
            .toTable(db);
    }

    public static void main(String[] args) {

        File file = new File("ICE_PUST.accdb");

        try {
            if (!file.exists()) {
                try (Database db = DatabaseBuilder.create(
                        Database.FileFormat.V2010, file)) {
                    createTable(db);
                }
                System.out.println("Database created.");
            } else {
                try (Database db = DatabaseBuilder.open(file)) {
                    if (db.getTable("Student") == null)
                        createTable(db);
                }
            }

            String url = "jdbc:ucanaccess://" +
                file.getAbsolutePath().replace("\\", "/");

            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

            try (Scanner sc = new Scanner(System.in);
                 Connection con = DriverManager.getConnection(url);
                 PreparedStatement ps = con.prepareStatement(
                     "INSERT INTO Student ([Name], Email, Phone) " +
                     "VALUES (?, ?, ?)")) {

                String more;

                do {
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Email: ");
                    String email = sc.nextLine();

                    System.out.print("Enter Phone: ");
                    String phone = sc.nextLine();

                    ps.setString(1, name);
                    ps.setString(2, email);
                    ps.setString(3, phone);
                    ps.executeUpdate();

                    System.out.println("Record inserted!");

                    System.out.print("Add another? (y/n): ");
                    more = sc.nextLine().trim();

                } while (more.equalsIgnoreCase("y"));
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

/*
javac -cp ".;*" Exp11a.java
java -cp ".;*" Exp11a
*/