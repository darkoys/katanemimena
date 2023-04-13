import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class Application {

    public static void main(String[] args) {
        try {
            // Get a list of all GPX files in the "gpxs" directory
            File directory = new File("gpxs");
            File[] files = directory.listFiles((dir, name) -> name.endsWith(".gpx"));

            // Establish a socket connection to the server
            Socket socket = new Socket("localhost", 8000);
            OutputStream outputStream = socket.getOutputStream();

            for (File file : files) {
                ArrayList<Waypoint> waypoints = new ArrayList<Waypoint>();
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();

                // Parse the GPX file
                Document document = builder.parse(file);
                NodeList nodeList = document.getElementsByTagName("wpt");

                // Extract the waypoint information
                for (int i = 0; i < nodeList.getLength(); i++) {
                    Element element = (Element) nodeList.item(i);
                    double lat = Double.parseDouble(element.getAttribute("lat"));
                    double lon = Double.parseDouble(element.getAttribute("lon"));
                    double ele = Double.parseDouble(element.getElementsByTagName("ele").item(0).getTextContent());
                    String time = element.getElementsByTagName("time").item(0).getTextContent();
                    Waypoint waypoint = new Waypoint(lat, lon, ele, time);
                    waypoints.add(waypoint);
                }

                // Send the waypoints to the server
                for (Waypoint waypoint : waypoints) {
                    String message = String.format("%.6f,%.6f,%.2f,%s,%s\n", waypoint.getLatitude(), waypoint.getLongitude(), waypoint.getElevation(), waypoint.getTimestamp(), file.getName());
                    outputStream.write(message.getBytes());
                }
            }

            // Close the output stream and socket connection
            outputStream.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}




