
public class GPXCoordinates {
    private double latitude;
    private double longitude;
    private double elevation;
    private String timestamp;

    public GPXCoordinates(double latitude, double longitude, double elevation, String timestamp) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.elevation = elevation;
        this.timestamp = timestamp;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getElevation() {
        return elevation;
    }

    public String getTimestamp() {
        return timestamp;
    }
}