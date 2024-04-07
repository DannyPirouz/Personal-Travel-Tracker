package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FlightsListTest {

    private FlightsList testFlightsList;
    private Flight testFlight1;
    private Flight testFlight2;
    private Flight testFlight3;


    @BeforeEach
    void runBefore() {
        testFlightsList = new FlightsList();
        testFlight1 = new Flight("Epic New York Trip", "Vancouver", "New York");
        testFlight2 = new Flight("-1000 Vegas Trip", "Vancouver", "Vegas");
        testFlight3 = new Flight("Never Again Barca", "Toronto", "Barcelona");
    }

    @Test
    void testConstructor() {
        assertTrue(testFlightsList.isEmpty());
    }

    @Test
    void testAddFlightToEmptyList() {
        testFlightsList.addFlight(testFlight1);
        assertEquals(1, testFlightsList.getNumFlights());
    }

    @Test
    void testAddFlightToNonEmptyList() {
        testFlightsList.addFlight(testFlight1);
        testFlightsList.addFlight(testFlight2);
        assertEquals(2, testFlightsList.getNumFlights());
    }

    @Test
    void testRemoveFlight() {
        testFlightsList.addFlight(testFlight1);
        testFlightsList.addFlight(testFlight2);
        testFlightsList.removeFlight(testFlight1);
        assertEquals(1, testFlightsList.getNumFlights());
        assertFalse(testFlightsList.getFlightsList().contains(testFlight1));
    }

    @Test
    void testRemoveFlightToEmpty() {
        testFlightsList.addFlight(testFlight3);
        testFlightsList.removeFlight(testFlight3);
        assertEquals(0, testFlightsList.getNumFlights());
        assertFalse(testFlightsList.getFlightsList().contains(testFlight3));
    }

    @Test
    void testGetNumFlights() {
        testFlightsList.addFlight(testFlight1);
        testFlightsList.addFlight(testFlight2);
        testFlightsList.addFlight(testFlight3);
        assertEquals(3, testFlightsList.getNumFlights());
    }

    @Test
    void testGetTotalHoursFlownEmptyList() {
        assertEquals(0, testFlightsList.getTotalHoursFlown());
    }

    @Test
    void testGetTotalHoursFlown() {
        testFlight2.setHoursFlown(3.5);
        testFlight1.setHoursFlown(5);
        testFlightsList.addFlight(testFlight1);
        testFlightsList.addFlight(testFlight2);
        assertEquals(8.5, testFlightsList.getTotalHoursFlown());
    }

    @Test
    void testGetLongestFlight() {
        testFlightsList.addFlight(testFlight1);
        testFlightsList.addFlight(testFlight2);
        testFlightsList.addFlight(testFlight3);
        testFlight1.setHoursFlown(5);
        testFlight2.setHoursFlown(2);
        testFlight3.setHoursFlown(13.5);
        assertEquals(testFlight3, testFlightsList.getLongestFlight());
    }

    @Test
    void testGetLongestFlightTie() {
        testFlightsList.addFlight(testFlight1);
        testFlightsList.addFlight(testFlight2);
        testFlightsList.addFlight(testFlight3);
        testFlight1.setHoursFlown(11);
        testFlight2.setHoursFlown(5);
        testFlight3.setHoursFlown(11);
        assertEquals(testFlight1, testFlightsList.getLongestFlight());
    }

    @Test
    void testGetLongestFlightNull() {
        assertNull(testFlightsList.getLongestFlight());
    }

    @Test
    void testGetShortestFlight() {
        testFlightsList.addFlight(testFlight1);
        testFlightsList.addFlight(testFlight2);
        testFlightsList.addFlight(testFlight3);
        testFlight1.setHoursFlown(5);
        testFlight2.setHoursFlown(2);
        testFlight3.setHoursFlown(13.5);
        assertEquals(testFlight2, testFlightsList.getShortestFlight());
    }

    @Test
    void testGetShortestFlightTie() {
        testFlightsList.addFlight(testFlight1);
        testFlightsList.addFlight(testFlight2);
        testFlightsList.addFlight(testFlight3);
        testFlight1.setHoursFlown(2);
        testFlight2.setHoursFlown(5);
        testFlight3.setHoursFlown(2);
        assertEquals(testFlight1, testFlightsList.getShortestFlight());
    }

    @Test
    void testGetShortestFlightNull() {
        assertNull(testFlightsList.getShortestFlight());
    }

    @Test
    void getHighestRatedFlight() {
        testFlight1.setRating(8);
        testFlight3.setRating(4);
        testFlight2.setRating(9);
        testFlightsList.addFlight(testFlight1);
        testFlightsList.addFlight(testFlight2);
        testFlightsList.addFlight(testFlight3);
        assertEquals(testFlight2, testFlightsList.getHighestRatedFlight());
    }

    @Test
    void getHighestRatedFlightTie() {
        testFlight1.setRating(3);
        testFlight3.setRating(7);
        testFlight2.setRating(7);
        testFlightsList.addFlight(testFlight1);
        testFlightsList.addFlight(testFlight2);
        testFlightsList.addFlight(testFlight3);
        assertEquals(testFlight2, testFlightsList.getHighestRatedFlight());
    }

    @Test
    void getHighestRatedFlightEmpty() {
        assertNull(testFlightsList.getHighestRatedFlight());
    }

    @Test
    void getLowestRatedFlight() {
        testFlight1.setRating(8);
        testFlight3.setRating(4);
        testFlight2.setRating(9);
        testFlightsList.addFlight(testFlight1);
        testFlightsList.addFlight(testFlight2);
        testFlightsList.addFlight(testFlight3);
        assertEquals(testFlight3, testFlightsList.getLowestRatedFlight());
    }

    @Test
    void getLowestRatedFlightTie() {
        testFlight1.setRating(3);
        testFlight3.setRating(7);
        testFlight2.setRating(3);
        testFlightsList.addFlight(testFlight1);
        testFlightsList.addFlight(testFlight2);
        testFlightsList.addFlight(testFlight3);
        assertEquals(testFlight1, testFlightsList.getLowestRatedFlight());
    }

    @Test
    void getLowestRatedFlightEmpty() {
        assertNull(testFlightsList.getLowestRatedFlight());
    }


    @Test
    void testIsEmptyTrue() {
        assertTrue(testFlightsList.isEmpty());
    }

    @Test
    void testIsEmptyFalse() {
        testFlightsList.addFlight(testFlight2);
        assertFalse(testFlightsList.isEmpty());
    }

    @Test
    void testGetSatAisle() {
        testFlightsList.addFlight(testFlight1);
        testFlightsList.addFlight(testFlight2);
        testFlightsList.addFlight(testFlight3);
        testFlight1.setSittingSpot("aisle");
        testFlight2.setSittingSpot("window");
        testFlight3.setSittingSpot("aisle");
        assertEquals(2, testFlightsList.getSatAisle());
    }

    @Test
    void testGetSatAisleEmpty() {
        assertEquals(0, testFlightsList.getSatAisle());
    }

    @Test
    void testGetSatMiddle() {
        testFlightsList.addFlight(testFlight1);
        testFlightsList.addFlight(testFlight2);
        testFlightsList.addFlight(testFlight3);
        testFlight1.setSittingSpot("middle");
        testFlight2.setSittingSpot("window");
        testFlight3.setSittingSpot("middle");
        assertEquals(2, testFlightsList.getSatMiddle());
    }

    @Test
    void testGetSatMiddleEmpty() {
        assertEquals(0, testFlightsList.getSatMiddle());
    }

    @Test
    void testGetSatWindow() {
        testFlightsList.addFlight(testFlight1);
        testFlightsList.addFlight(testFlight2);
        testFlightsList.addFlight(testFlight3);
        testFlight1.setSittingSpot("middle");
        testFlight2.setSittingSpot("window");
        testFlight3.setSittingSpot("window");
        assertEquals(2, testFlightsList.getSatWindow());
    }

    @Test
    void testGetSatWindowEmpty() {
        assertEquals(0, testFlightsList.getSatWindow());
    }

    @Test
    void testGetIdNumbersEmpty() {
        assertTrue(testFlightsList.getIdNumbers().isEmpty());
    }

    @Test
    void testGetIdNumbers1() {
        testFlightsList.addFlight(testFlight1);
        assertTrue(testFlightsList.getIdNumbers().contains(testFlight1.getIdNumber()));
        assertEquals(1, testFlightsList.getIdNumbers().size());
    }

    @Test
    void testGetIdNumbersMultiple() {
        testFlightsList.addFlight(testFlight1);
        testFlightsList.addFlight(testFlight2);
        assertTrue(testFlightsList.getIdNumbers().contains(testFlight1.getIdNumber()));
        assertTrue(testFlightsList.getIdNumbers().contains(testFlight2.getIdNumber()));
        assertEquals(2, testFlightsList.getIdNumbers().size());
    }

    @Test
    void testGetFlightFromIdEmptyNull() {
        assertNull(testFlightsList.getFlightFromId(5));
    }

    @Test
    void testGetFlightFromIdNull() {
        testFlightsList.addFlight(testFlight1);
        testFlightsList.addFlight(testFlight2);
        assertNull(testFlightsList.getFlightFromId(5));
    }

    @Test
    void testGetFlightFromId() {
        testFlightsList.addFlight(testFlight1);
        testFlightsList.addFlight(testFlight2);
        testFlightsList.addFlight(testFlight3);
        assertEquals(testFlight3, testFlightsList.getFlightFromId(testFlight3.getIdNumber()));
    }
}
