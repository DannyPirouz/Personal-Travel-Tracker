package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistance.Writable;

import java.util.ArrayList;

// Represents a list of flights for the TravelTrackerApp
// New flights that are added go to the back of the list
public class FlightsList implements Writable {

    private ArrayList<Flight> flightsList;

    // Effects: creates an empty flightsList
    public FlightsList() {
        flightsList = new ArrayList<>();
    }

    // Modifies: this
    // Effects: adds flight to flightsList
    public void addFlight(Flight flight) {
        flightsList.add(flight);
        EventLog.getInstance().logEvent(new Event("Flight \"" + flight.getTitle() + "\" Added"));
    }

    // Requires: flight must be in flightsList
    // Modifies: this
    // Effects: remove flight from flightsList
    public void removeFlight(Flight flight) {
        flightsList.remove(flight);
        EventLog.getInstance().logEvent(new Event("Flight \"" + flight.getTitle() + "\" Removed"));
    }

    // Effects: returns number of flights in flightsList
    public int getNumFlights() {
        return flightsList.size();
    }

    // Effects: returns total number of hours flown by adding all hoursFlown
    //          of flights in flightsList; returns 0 if flightList is empty
    public double getTotalHoursFlown() {
        if (flightsList.isEmpty()) {
            return 0;
        } else {
            double totalHours = 0;
            for (Flight flight : flightsList) {
                totalHours += flight.getHoursFlown();
            }
            return totalHours;
        }
    }

    // Effects: returns flight with most hoursFlown;
    //          in case of tie, return first Flight found;
    //          return null if flightsList is empty
    public Flight getLongestFlight() {
        if (flightsList.isEmpty()) {
            return null;
        } else {
            Flight longestFlight = flightsList.get(0);
            for (Flight flight : flightsList) {
                if (flight.getHoursFlown() > longestFlight.getHoursFlown()) {
                    longestFlight = flight;
                }
            }
            return longestFlight;
        }
    }

    // Effects: returns flight with lowest hoursFlown;
    //          in case of tie, return first flight found;
    //          return null if flightsList is empty
    public Flight getShortestFlight() {
        if (flightsList.isEmpty()) {
            return null;
        } else {
            Flight lowestFlight = flightsList.get(0);
            for (Flight flight : flightsList) {
                if (flight.getHoursFlown() < lowestFlight.getHoursFlown()) {
                    lowestFlight = flight;
                }
            }
            return lowestFlight;
        }
    }

    // Effects: returns how many times sat in aisle seat;
    //          if flightList is empty, return 0
    public int getSatAisle() {
        if (flightsList.isEmpty()) {
            return 0;
        }
        int aisle = 0;
        for (Flight flight : flightsList) {
            if (flight.getSittingSpot().equals("aisle")) {
                aisle += 1;
            }
        }
        return aisle;
    }

    // Effects: returns how many times sat in middle seat;
    //          if flightList is empty, return 0
    public int getSatMiddle() {
        if (flightsList.isEmpty()) {
            return 0;
        }
        int middle = 0;
        for (Flight flight : flightsList) {
            if (flight.getSittingSpot().equals("middle")) {
                middle += 1;
            }
        }
        return middle;
    }

    // Effects: returns how many times sat in window seat;
    //          if flightList is empty, return 0
    public int getSatWindow() {
        if (flightsList.isEmpty()) {
            return 0;
        }
        int window = 0;
        for (Flight flight : flightsList) {
            if (flight.getSittingSpot().equals("window")) {
                window += 1;
            }
        }
        return window;
    }

    // Effects: returns flight with the highest rating;
    //          in case of tie, return first flight found;
    //          return null if flightsList is empty
    public Flight getHighestRatedFlight() {
        if (flightsList.isEmpty()) {
            return null;
        } else {
            Flight highestRating = flightsList.get(0);
            for (Flight flight : flightsList) {
                if (flight.getRating() > highestRating.getRating()) {
                    highestRating = flight;
                }
            }
            return highestRating;
        }
    }

    // Effects: returns flight with the lowest rating
    //          in case of tie, return first flight found;
    //          return null if flightsList is empty
    public Flight getLowestRatedFlight() {
        if (flightsList.isEmpty()) {
            return null;
        } else {
            Flight lowestRating = flightsList.get(0);
            for (Flight flight : flightsList) {
                if (flight.getRating() < lowestRating.getRating()) {
                    lowestRating = flight;
                }
            }
            return lowestRating;
        }
    }

    // Effects: returns flightsList
    public ArrayList<Flight> getFlightsList() {
        return flightsList;
    }

    // Effects: adds a new log event to EventLog
    public void addViewAllFlights() {
        EventLog.getInstance().logEvent(new Event("All Flights Viewed"));
    }

    // Effects: return true is flightsList is empty, false otherwise
    public boolean isEmpty() {
        return flightsList.isEmpty();
    }

    // Effects: returns a list that contains all the ids in flightsList
    public ArrayList<Integer> getIdNumbers() {
        ArrayList<Integer> idNumbers = new ArrayList<>();
        for (Flight flight : flightsList) {
            idNumbers.add(flight.getIdNumber());
        }
        return idNumbers;
    }

    // Effects: returns the flight with the given id;
    //          returns null if id not found
    public Flight getFlightFromId(int id) {
        for (Flight flight : flightsList) {
            if (flight.getIdNumber() == id) {
                return flight;
            }
        }
        return null;
    }

    // Modifies: this
    // Effects: returns this as JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("flightsList", flightsListToJson());
        return json;
    }

    // Effects: returns flights in flightsList as a JSON array
    private JSONArray flightsListToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Flight flight : flightsList) {
            jsonArray.put(flight.toJson());
        }
        return jsonArray;
    }
}
