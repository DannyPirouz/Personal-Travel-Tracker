package persistance;

import model.Flight;
import model.FlightsList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Attributing source: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            FlightsList flightsList = new FlightsList();
            JsonWriter writer = new JsonWriter("./data/illegal\7filename.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // good
        }
    }

    @Test
    void testWriterEmptyFlightsList() {
        try {
            FlightsList flightsList = new FlightsList();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyFlightsList.json");
            writer.open();
            writer.write(flightsList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyFlightsList.json");
            flightsList = reader.read();
            assertTrue(flightsList.isEmpty());
        } catch (IOException e) {
            fail("Exception not expected");
        }
    }

    @Test
    void testWriterNormalFlightsList() {
        try {
            FlightsList flightsList = new FlightsList();
            flightsList.addFlight(new Flight("epic ny trip", "yvr", "nyc"));
            flightsList.addFlight(new Flight("-1000 vegas", "yyc", "las vegas"));
            JsonWriter writer = new JsonWriter("./data/testWriterNormalFlightsList.json");
            writer.open();
            writer.write(flightsList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterNormalFlightsList.json");
            flightsList = reader.read();
            List<Flight> flights = flightsList.getFlightsList();
            assertEquals(2, flights.size());
            checkEntireFlight("epic ny trip", "yvr", "nyc", "N/A",
                    "N/A", "N/A", "N/A", "n/a", 0,
                    0, flights.get(0));
            checkEntireFlight("-1000 vegas", "yyc", "las vegas", "N/A",
                    "N/A", "N/A", "N/A", "n/a", 0,
                    0, flights.get(1));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
