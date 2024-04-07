package persistance;

import model.Flight;
import model.FlightsList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Attributing source: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            FlightsList flightsList = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // should pass
        }
    }

    @Test
    void testReaderEmptyFlightsList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyFlightsList.json");
        try {
            FlightsList flightsList = reader.read();
            assertTrue(flightsList.isEmpty());
        } catch (IOException e) {
            fail("Unexpected Exception");
        }
    }

    @Test
    void testReaderNormalFlightsList() {
        JsonReader reader = new JsonReader("./data/testReaderNormalFlightsList.json");
        try {
            FlightsList flightsList = reader.read();
            List<Flight> flights = flightsList.getFlightsList();
            assertEquals(2, flights.size());
            checkEntireFlight("ny trip", "vancouver",
                    "new york city", "air canada",
                    "a320", "jan 18th", "chicken",
                    "middle", 5, 19, flights.get(0));
            checkEntireFlight("europe", "yyc",
                    "ams", "KLM", "N/A", "N/A",
                    "pasta", "n/a", 9.5, 6, flights.get(1));
        } catch (IOException e) {
            fail("Unexpected Exception");
        }
    }
}
