import java.util.Date;

class Waypoint {
    private double latitude;
    private double longitude;
    private double elevation;
    private String timestamp;

    public Waypoint(double latitude, double longitude, double elevation, String time) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.elevation = elevation;
        this.timestamp = time;
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
    @Override
    public String toString() {
        return "latitude:" + latitude +"longitude:"+longitude+"elevation:"+elevation+"timestamp:"+timestamp;
    }
}
