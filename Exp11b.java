import java.io.File;
import java.sql.*;

public class Exp11b {

    public static void main(String[] args) {

        File file = new File("ICE_PUST.accdb");

        if (!file.exists()) {
            System.out.println("Database not found. Run Exp11a first.");
            return;
        }

        String url = "jdbc:ucanaccess://" +
            file.getAbsolutePath().replace("\\", "/");

        String sql = "SELECT [Name], Email, Phone FROM Student";

        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

            try (Connection con = DriverManager.getConnection(url);
                 Statement st = con.createStatement();
                 ResultSet rs = st.executeQuery(sql)) {

                System.out.println("\n--- All Student Records ---");

                boolean found = false;

                while (rs.next()) {
                    found = true;
                    System.out.println(
                        rs.getString("Name") + " | " +
                        rs.getString("Email") + " | " +
                        rs.getString("Phone")
                    );
                }

                if (!found)
                    System.out.println("No records found.");
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

/*
javac -cp ".;*" Exp11b.java
java -cp ".;*" Exp11b
*/