import java.sql.*;
import java.util.Scanner;

public class Exp12_MySQL_CRUD {
    public static void main(String[] args) throws Exception {
        String user = "root", pass = "1234";
        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection c = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/", user, pass);
             Statement s = c.createStatement()) {
            s.executeUpdate("CREATE DATABASE IF NOT EXISTS student_db");
        }

        try (Connection c = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/student_db", user, pass);
             Statement s = c.createStatement();
             Scanner sc = new Scanner(System.in)) {

            s.executeUpdate(
                "CREATE TABLE IF NOT EXISTS Student (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "Name VARCHAR(100), Email VARCHAR(100), Phone VARCHAR(30))"
            );

            while (true) {
                System.out.println("\n1.Insert  2.Delete  3.Edit  4.Show  5.Exit");
                System.out.print("Choose: ");
                int ch = Integer.parseInt(sc.nextLine());

                if (ch == 5) break;

                if (ch == 4) {
                    try (ResultSet r = s.executeQuery("SELECT * FROM Student")) {
                        while (r.next())
                            System.out.println(r.getInt("id") + " | " +
                                r.getString("Name") + " | " +
                                r.getString("Email") + " | " +
                                r.getString("Phone"));
                    }
                    continue;
                }

                if (ch < 1 || ch > 3) {
                    System.out.println("Invalid choice.");
                    continue;
                }

                String sql = ch == 1
                    ? "INSERT INTO Student (Name,Email,Phone) VALUES (?,?,?)"
                    : ch == 2
                    ? "DELETE FROM Student WHERE id=?"
                    : "UPDATE Student SET Name=?,Email=?,Phone=? WHERE id=?";

                try (PreparedStatement p = c.prepareStatement(sql)) {
                    int id = 0;

                    if (ch != 1) {
                        System.out.print("Enter ID: ");
                        id = Integer.parseInt(sc.nextLine());
                    }

                    if (ch == 2) {
                        p.setInt(1, id);
                    } else {
                        System.out.print("Name: ");
                        p.setString(1, sc.nextLine());
                        System.out.print("Email: ");
                        p.setString(2, sc.nextLine());
                        System.out.print("Phone: ");
                        p.setString(3, sc.nextLine());

                        if (ch == 3) p.setInt(4, id);
                    }

                    int n = p.executeUpdate();
                    System.out.println(ch == 1 ? "Record inserted."
                        : n == 0 ? "Student ID not found."
                        : ch == 2 ? "Record deleted." : "Record updated.");
                }
            }
        }
    }
}


/*
javac -cp ".;*" Exp12_MySQL_CRUD.java
java -cp ".;*" Exp12_MySQL_CRUD
*/