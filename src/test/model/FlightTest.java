package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FlightTest {

    private Flight testFlight1;
    private Flight testFlight2;


    @BeforeEach
    void runBefore() {
        testFlight1 = new Flight("Epic New York Trip", "Vancouver", "New York");
        testFlight2 = new Flight("-1000 Vegas Trip", "Vancouver", "Vegas");
    }

    @Test
    void testConstructor2Flights() {
        assertEquals("Epic New York Trip", testFlight1.getTitle());
        assertEquals("Vancouver", testFlight1.getOrigin());
        assertEquals("New York", testFlight1.getDestination());

        assertEquals("-1000 Vegas Trip", testFlight2.getTitle());
        assertEquals("Vancouver", testFlight2.getOrigin());
        assertEquals("Vegas", testFlight2.getDestination());
    }

    @Test
    void testGetIdNumber() {
        Flight testFlight3 = new Flight("awesome europe trip", "yvr", "ams", 4);
        Flight testFlight4 = new Flight("yolo trip", "yyc", "LA", 8);
        assertEquals(4, testFlight3.getIdNumber());
        assertEquals(8, testFlight4.getIdNumber());
    }

    @Test
    void testSetTitle() {
        testFlight1.setTitle("epic new york trip");
        assertEquals("epic new york trip", testFlight1.getTitle());
    }

    @Test
    void testSetAirline() {
        testFlight1.setAirline("westjet");
        assertEquals("westjet", testFlight1.getAirline());
    }

    @Test
    void testSetOrigin() {
        testFlight1.setOrigin("vancouver");
        assertEquals("vancouver", testFlight1.getOrigin());
    }

    @Test
    void testSetDestination() {
        testFlight1.setDestination("ams");
        assertEquals("ams", testFlight1.getDestination());
    }

    @Test
    void testSetPlaneModel() {
        testFlight1.setPlaneModel("747");
        assertEquals("747", testFlight1.getPlaneModel());
    }

    @Test
    void testSetDate() {
        testFlight1.setDate("sep 20, 1884");
        assertEquals("sep 20, 1884", testFlight1.getDate());
    }

    @Test
    void testSetFoodEaten() {
        testFlight1.setFoodEaten("pasta");
        assertEquals("pasta", testFlight1.getFoodEaten());
    }

    @Test
    void testSetHoursFlown() {
        testFlight1.setHoursFlown(8.5);
        assertEquals(8.5, testFlight1.getHoursFlown());
    }

    @Test
    void testSetSittingSpot() {
        testFlight1.setSittingSpot("middle");
        assertEquals("middle", testFlight1.getSittingSpot());
    }

    @Test
    void testSetRating() {
        testFlight1.setRating(8);
        assertEquals(8, testFlight1.getRating());
    }

    @Test
    void testGetTitle() {
        testFlight1.setTitle("epic new york trip");
        assertEquals("epic new york trip", testFlight1.getTitle());
    }

    @Test
    void testGetAirline() {
        testFlight1.setAirline("westjet");
        assertEquals("westjet", testFlight1.getAirline());
    }

    @Test
    void testGetOrigin() {
        testFlight1.setOrigin("vancouver");
        assertEquals("vancouver", testFlight1.getOrigin());
    }

    @Test
    void testGetDestination() {
        testFlight1.setDestination("ams");
        assertEquals("ams", testFlight1.getDestination());
    }

    @Test
    void testGetPlaneModel() {
        testFlight1.setPlaneModel("747");
        assertEquals("747", testFlight1.getPlaneModel());
    }

    @Test
    void testGetDate() {
        testFlight1.setDate("sep 20, 1884");
        assertEquals("sep 20, 1884", testFlight1.getDate());
    }

    @Test
    void testGetFoodEaten() {
        testFlight1.setFoodEaten("pasta");
        assertEquals("pasta", testFlight1.getFoodEaten());
    }

    @Test
    void testGetHoursFlown() {
        testFlight1.setHoursFlown(8.5);
        assertEquals(8.5, testFlight1.getHoursFlown());
    }

    @Test
    void testGetSittingSpot() {
        testFlight1.setSittingSpot("middle");
        assertEquals("middle", testFlight1.getSittingSpot());
    }

    @Test
    void testGetRating() {
        testFlight1.setRating(8);
        assertEquals(8, testFlight1.getRating());
    }
}