import java.net.*;

public class UDPServer {
    public static void main(String[] args) throws Exception {
        DatagramSocket server = new DatagramSocket(5000);
        byte[] buffer = new byte[1024];

        System.out.println("Server waiting for messages...");

        DatagramPacket request =
            new DatagramPacket(buffer, buffer.length);

        server.receive(request);

        String msg = new String(
            request.getData(), 0, request.getLength());

        System.out.println("Client: " + msg);

        Thread.sleep(6);

        byte[] data = msg.toUpperCase().getBytes();

        DatagramPacket reply = new DatagramPacket(
            data, data.length,
            request.getAddress(), request.getPort());

        server.send(reply);
        server.close();
    }
}


