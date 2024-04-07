package model;

import org.json.JSONObject;
import persistance.Writable;

// Represents a flight with a title, airline, origin, destination, plane model,
// date of flight, food eaten on flight, seating (aisle, middle, or window),
// number of hours flown, the rating, and an id number (id number is unique). The hoursFlown and flight rating
// are by default set to 0. The airline, plane model, date, food eaten, and seating are
// by default set to "N/A"
public class Flight implements Writable {

    private String title;
    private String airline = "N/A";
    private String origin;
    private String destination;
    private String planeModel = "N/A";
    private String date = "N/A";
    private String foodEaten = "N/A";
    private String sittingSpot = "n/a";
    private double hoursFlown = 0;
    private int rating = 0;
    private int idNumber;
    private static int nextIdNumber = 1;

    // Requires: title, origin, and destination to have a non-zero length
    // Effects: the flight's title, origin, and destination are set to
    //          title, origin, and destination, respectively; flight is given an id
    //          number where id number increases by every new flight created
    public Flight(String title, String origin, String destination) {
        this.title = title;
        this.origin = origin;
        this.destination = destination;
        this.idNumber = nextIdNumber;
        nextIdNumber++;
    }

    // Created for testing purposes
    // Requires: title, origin, and destination to have a non-zero length
    // Effects: the flight's title, origin, destination, and idNumber are set to
    //          title, origin, destination, and idNumber, respectively
    public Flight(String title, String origin, String destination, int id) {
        this.title = title;
        this.origin = origin;
        this.destination = destination;
        this.idNumber = id;
    }

    public void setTitle(String title) {
        this.title = title;
        EventLog.getInstance().logEvent(new Event("Title changed to: " + title));
    }

    public void setAirline(String airline) {
        this.airline = airline;
        EventLog.getInstance().logEvent(new Event("Airline changed to: " + airline));
    }

    public void setOrigin(String origin) {
        this.origin = origin;
        EventLog.getInstance().logEvent(new Event("Origin changed to: " + origin));
    }

    public void setDestination(String destination) {
        this.destination = destination;
        EventLog.getInstance().logEvent(new Event("Destination changed to: " + destination));
    }

    public void setPlaneModel(String planeModel) {
        this.planeModel = planeModel;
        EventLog.getInstance().logEvent(new Event("Plane Model changed to: " + planeModel));
    }

    public void setDate(String date) {
        this.date = date;
        EventLog.getInstance().logEvent(new Event("Date changed to: " + date));
    }

    public void setFoodEaten(String foodEaten) {
        this.foodEaten = foodEaten;
        EventLog.getInstance().logEvent(new Event("Food eaten changed to: " + foodEaten));
    }

    public void setHoursFlown(double hoursFlown) {
        this.hoursFlown = hoursFlown;
        EventLog.getInstance().logEvent(new Event("Hours flown changed to: " + hoursFlown));
    }

    public void setSittingSpot(String sittingSpot) {
        this.sittingSpot = sittingSpot.toLowerCase();
        EventLog.getInstance().logEvent(new Event("Seat changed to: " + sittingSpot));
    }

    public void setRating(int rating) {
        this.rating = rating;
        EventLog.getInstance().logEvent(new Event("Flight \"" + this.title + "\" rating changed to: " + rating));
    }

    public double getHoursFlown() {
        return hoursFlown;
    }

    public String getSittingSpot() {
        return sittingSpot;
    }

    public int getRating() {
        return rating;
    }

    public String getTitle() {
        return title;
    }

    public String getAirline() {
        return airline;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public String getPlaneModel() {
        return planeModel;
    }

    public String getDate() {
        return date;
    }

    public String getFoodEaten() {
        return foodEaten;
    }

    public int getIdNumber() {
        return idNumber;
    }


    // Modifies: this
    // Effects: returns this as JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("title", title);
        json.put("origin", origin);
        json.put("destination", destination);
        json.put("airline", airline);
        json.put("planeModel", planeModel);
        json.put("date", date);
        json.put("foodEaten", foodEaten);
        json.put("sittingSpot", sittingSpot);
        json.put("hoursFlown", hoursFlown);
        json.put("rating", rating);
        return json;
    }
}
