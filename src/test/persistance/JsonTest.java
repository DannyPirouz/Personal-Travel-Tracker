package persistance;

import model.Flight;

import static org.junit.jupiter.api.Assertions.*;

// Attributing source: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

public class JsonTest {

    // Effects: checks to see if flight attributes match what is being read
    protected void checkEntireFlight(String title, String origin, String destination,
                                     String airline,
                                     String planeModel, String date, String foodEaten,
                                     String sittingSpot, double hoursFlown, int rating, Flight flight) {

        assertEquals(title, flight.getTitle());
        assertEquals(origin, flight.getOrigin());
        assertEquals(destination, flight.getDestination());
        assertEquals(airline, flight.getAirline());
        assertEquals(planeModel, flight.getPlaneModel());
        assertEquals(date, flight.getDate());
        assertEquals(foodEaten, flight.getFoodEaten());
        assertEquals(sittingSpot, flight.getSittingSpot());
        assertEquals(hoursFlown, flight.getHoursFlown());
        assertEquals(rating, flight.getRating());
    }
}
