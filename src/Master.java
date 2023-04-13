import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Master {
    public static void main(String[] args) {
        int portNumber = 8000;
        try {
            ServerSocket serverSocket = new ServerSocket(portNumber);
            System.out.println("Server started on port " + portNumber);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress().getHostName());

                // Read the GPX file sent by the client
                InputStream inputStream = clientSocket.getInputStream();
                Scanner scanner = new Scanner(inputStream);
                scanner.useDelimiter("</wpt>");

                // Extract the <wpt> elements from the GPX file and add them to an ArrayList
                ArrayList<Waypoint> waypoints = new ArrayList<>();
                while (scanner.hasNext()) {
                    String line = scanner.next();
                    if (line.contains("<wpt")) {
                        double lat = Double.parseDouble(line.split("lat=\"")[1].split("\"")[0]);
                        double lon = Double.parseDouble(line.split("lon=\"")[1].split("\"")[0]);
                        double ele = Double.parseDouble(line.split("<ele>")[1].split("</ele>")[0]);
                        String time = line.split("<time>")[1].split("</time>")[0];
                        waypoints.add(new Waypoint(lat, lon, ele, time));
                    }
                }

                // Print the waypoints to the console
                System.out.println("Received waypoints from client:");
                for (Waypoint waypoint : waypoints) {
                    System.out.println(waypoint.toString());
                }

                // Close the client socket connection
                clientSocket.close();
                System.out.println("Client disconnected");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}