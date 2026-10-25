import java.net.*;
import java.io.*;

public class Client9 {
    public static void main(String[] args) throws Exception {
        Socket s = new Socket("localhost", 5000);

        BufferedReader in = new BufferedReader(
            new InputStreamReader(s.getInputStream()));
        PrintWriter out = new PrintWriter(s.getOutputStream(), true);
        BufferedReader user = new BufferedReader(
            new InputStreamReader(System.in));

        System.out.print("Enter message: ");
        String msg = user.readLine();

        out.println(msg);
        System.out.println("Server: " + in.readLine());

        s.close();
    }
}

