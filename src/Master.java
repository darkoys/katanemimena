import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Master {
    private int portNumber;

    public Master(int portNumber) {
        this.portNumber = portNumber;
    }

    public void startServer() {
        try {
            // Create a server socket
            ServerSocket serverSocket = new ServerSocket(portNumber);

            System.out.println("Server started on port " + portNumber);

            // Wait for a client to connect
            Socket clientSocket = serverSocket.accept();

            System.out.println("Client connected: " + clientSocket.getInetAddress().getHostName());

            // Create a reader to read from the client socket
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            // Read messages from the client
            String message;
            while ((message = reader.readLine()) != null) {
                System.out.println("Received waypoint: " + message);
            }

            // Close the reader and socket
            reader.close();
            clientSocket.close();

            // Close the server socket
            serverSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Master server = new Master(8000);
        server.startServer();
    }
}