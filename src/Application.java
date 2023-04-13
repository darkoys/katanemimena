import java.io.*;
import java.net.*;
import java.nio.file.*;

public class Application {
    public static void main(String[] args) {
    	new Application().startApplication();
    }
    public void startApplication() {  
    	
    // The server hostname and port number
        String serverHost = "localhost";
        int serverPort = 8000;
        
        // The paths of the six GPX documents
        String[] filePaths = {
            "route1.gpx",
            "route2.gpx",
            "route3.gpx",
            "route4.gpx",
            "route5.gpx",
            "route6.gpx"
        };
        
        try {
            // Create a socket connection to the server
            Socket socket = new Socket(serverHost, serverPort);
            System.out.println("Connected to server: " + serverHost + ":" + serverPort);
            
            // Loop through the GPX files and send them to the server
            for (String filePath : filePaths) {
                // Read the contents of the file
                Path path = Paths.get(filePath);
                byte[] fileBytes = Files.readAllBytes(path);
                
                // Send the file contents to the server
                OutputStream outputStream = socket.getOutputStream();
                outputStream.write(fileBytes);
                outputStream.flush();
                System.out.println("Sent file to server: " + filePath);
            }
            
            // Close the socket connection
            socket.close();
            System.out.println("Disconnected from server");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}