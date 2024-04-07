package persistance;


import model.Flight;
import model.FlightsList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Attributing source: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

// Represents a reader that reads flightsList from JSON data stored in file
public class JsonReader {

    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads flightslist from file and returns it;
    // throws IOException if an error occurs reading data from file
    public FlightsList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseFlightsList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses flightsList from JSON object and returns it
    private FlightsList parseFlightsList(JSONObject jsonObject) {
        FlightsList flightsList = new FlightsList();
        addFlightsList(flightsList, jsonObject);
        return flightsList;
    }

    // MODIFIES: FlightsList
    // EFFECTS: parses flightsList from JSON object and adds them to Travel Tracker
    private void addFlightsList(FlightsList flightsList, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("flightsList");
        for (Object json : jsonArray) {
            JSONObject nextFlight = (JSONObject) json;
            addFlight(flightsList, nextFlight);
        }
    }

    // MODIFIES: flightsList
    // EFFECTS: parses flight from JSON object and adds it to flightsList
    private void addFlight(FlightsList flightsList, JSONObject jsonObject) {
        String title = jsonObject.getString("title");
        String origin = jsonObject.getString("origin");
        String destination = jsonObject.getString("destination");
        String airline = jsonObject.getString("airline");
        String planeModel = jsonObject.getString("planeModel");
        String date = jsonObject.getString("date");
        String foodEaten = jsonObject.getString("foodEaten");
        String sittingSpot = jsonObject.getString("sittingSpot");
        double hoursFlown = jsonObject.getDouble("hoursFlown");
        int rating = jsonObject.getInt("rating");
        Flight flight = new Flight(title, origin, destination);
        flight.setAirline(airline);
        flight.setPlaneModel(planeModel);
        flight.setDate(date);
        flight.setFoodEaten(foodEaten);
        flight.setSittingSpot(sittingSpot);
        flight.setHoursFlown(hoursFlown);
        flight.setRating(rating);
        flightsList.addFlight(flight);
    }
}
