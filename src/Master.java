import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import java.util.ArrayList;
import java.util.List;
	
	public class Master {

		
		
		
	    public static void main(String[] args) {
	    	List<GPXCoordinates> gpxCoordinatesList = new ArrayList<>();
	    	
	    	try {
	            // Create a new DocumentBuilderFactory
	            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

	            // Use the factory to create a new DocumentBuilder
	            DocumentBuilder builder = factory.newDocumentBuilder();

	            // Get a list of all GPX files in the gpxs directory
	            File gpxDir = new File("gpxs");
	            File[] gpxFiles = gpxDir.listFiles((dir, name) -> name.endsWith(".gpx"));

	            // Iterate over the GPX files and parse each one
	            for (int i = 0; i < gpxFiles.length; i++) {
	                File gpxFile = gpxFiles[i];
	                System.out.println("Parsing file " + gpxFile.getName() + "...");
	                
	                // Parse the GPX file
	                Document doc = builder.parse(gpxFile);

	                // Get the creator of the GPX file
	                String creator = doc.getDocumentElement().getAttribute("creator");
	                //System.out.println("Creator: " + creator);

	                // Get a list of all "wpt" elements in the document
	                NodeList wptList = doc.getElementsByTagName("wpt");

	                // Iterate over the "wpt" elements and print out the latitude, longitude, elevation, and timestamp data
	                for (int j = 0; j < wptList.getLength(); j++) {
	                    Node wptNode = wptList.item(j);
	                    String latStr = wptNode.getAttributes().getNamedItem("lat").getNodeValue();
	                    String lonStr = wptNode.getAttributes().getNamedItem("lon").getNodeValue();
	                    String eleStr = wptNode.getChildNodes().item(1).getTextContent();
	                    String time = wptNode.getChildNodes().item(3).getTextContent();

	                    // Convert latitude, longitude, and elevation strings to doubles
	                    double latitude = Double.parseDouble(latStr);
	                    double longitude = Double.parseDouble(lonStr);
	                    double elevation = Double.parseDouble(eleStr);

	                    // Create a new GPXCoordinates object and add it to the list
	                    GPXCoordinates gpxCoords = new GPXCoordinates(latitude, longitude, elevation, time);
	                    gpxCoordinatesList.add(gpxCoords);

	                  /*  System.out.println("Waypoint " + (j+1) + ":");
	                    System.out.println("Latitude: " + latStr);
	                    System.out.println("Longitude: " + lonStr);
	                    System.out.println("Elevation: " + eleStr);
	                    System.out.println("Timestamp: " + time);
	                    */
	                }
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    	
	    	
	    	for (GPXCoordinates gpxCoords : gpxCoordinatesList) {
	    	    System.out.println("Latitude: " + gpxCoords.getLatitude());
	    	    System.out.println("Longitude: " + gpxCoords.getLongitude());
	    	    System.out.println("Elevation: " + gpxCoords.getElevation());
	    	    System.out.println("Timestamp: " + gpxCoords.getTimestamp());
	    	}
	    	
	    	
	    }
	}


