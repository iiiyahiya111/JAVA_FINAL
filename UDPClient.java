import java.net.*;
import java.io.*;

public class UDPClient {
    public static void main(String[] args) throws Exception {
        DatagramSocket client = new DatagramSocket();
        BufferedReader user = new BufferedReader(
            new InputStreamReader(System.in));

        System.out.print("Enter message (max 1024 bytes): ");
        String msg = user.readLine();

        byte[] data = msg.getBytes();

        if (data.length > 1024) {
            System.out.println("Message is too long!");
            client.close();
            return;
        }

        InetAddress address = InetAddress.getByName("localhost");

        DatagramPacket request = new DatagramPacket(
            data, data.length, address, 5000);

        client.send(request);

        byte[] buffer = new byte[1024];
        DatagramPacket reply =
            new DatagramPacket(buffer, buffer.length);

        client.receive(reply);

        String result = new String(
            reply.getData(), 0, reply.getLength());

        System.out.println("Server: " + result);

        client.close();
    }
}
