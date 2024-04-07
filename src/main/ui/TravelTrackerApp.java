package ui;

import model.Flight;
import model.FlightsList;
import persistance.JsonReader;
import persistance.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// The way I set up the TravelTrackerApp() method and
// the runTravelTracker() method was guided by the TellerApp.
// I will attribute those methods to the TellerApp.
// Attributing source: https://github.students.cs.ubc.ca/CPSC210/TellerApp


// Travel Tracker application. Flights can be added, removed,
// edited, rated, and viewed. FlightList can be saved and loaded
public class TravelTrackerApp {

    private Scanner input;
    private Scanner integerInput;
    private Scanner doubleInput;
    private FlightsList flightsList;
    private String response = null;
    private boolean hasQuit = false;
    private int idResponse;
    private static final String JSON_STORE = "./data/flightsList.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    // Effects: runs the travel tracker application
    public TravelTrackerApp() throws FileNotFoundException {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runTravelTracker();
    }

    // Modifies: this
    // Effects: continues running the application unless
    //          user has selected quit option
    private void runTravelTracker() {
        init();
        System.out.println();
        System.out.println("Welcome to your personal travel tracker app! Let's get started.");

        while (!hasQuit) {
            if (flightsList.isEmpty()) {
                noFlightsYetMenu();
            } else {
                normalFlightsMenu();
            }
        }
        System.out.println("\n Don't forget to track your next flight! Goodbye for now!");
    }


    // Effects: displays the menu after 1 flight is in the flight list
    //          and processes what the user command
    private void normalFlightsMenu() {
        System.out.println("\n Please select a numbered option:");
        System.out.println("\t 1 - Add a flight");
        System.out.println("\t 2 - Remove a flight");
        System.out.println("\t 3 - Edit a flight");
        System.out.println("\t 4 - Rate a flight");
        System.out.println("\t 5 - View flight stats");
        System.out.println("\t 6 - View a flight");
        System.out.println("\t 7 - View all flights");
        System.out.println("\t 8 - Quit application");
        System.out.println("\t 9 - Save Travel Tracker to file");
        normalFlightsMenuOptions();
    }


    // Effects: selects the appropriate method depending on what user called;
    //          return to normalFlightsMenu if response was invalid
    private void normalFlightsMenuOptions() {
        response = input.nextLine();
        if (response.equals("1")) {
            addFlight();
        } else if (response.equals("2")) {
            removeFlight();
        } else if (response.equals("3")) {
            editFlight();
        } else if (response.equals("4")) {
            rateFlight();
        } else if (response.equals("5")) {
            viewStats();
        } else if (response.equals("6")) {
            viewAFlight();
        } else if (response.equals("7")) {
            viewFlights();
        } else if (response.equals("8")) {
            quit();
        } else if (response.equals("9")) {
            saveFlightsList();
        } else {
            System.out.println("\n Your response is invalid. Please enter a valid number.");
            normalFlightsMenu();
        }
    }

    // Effects: takes user input to determine which flight to view
    private void viewAFlight() {
        System.out.println("\n Please enter the id of the flight you would like to see.");
        System.out.println("Here is a list of all your flights:");
        allFlightIds();
        idResponse = integerInput.nextInt();
        if (!(flightsList.getIdNumbers().contains(idResponse))) {
            System.out.println("\n Invalid flight id. Back to the menu now.");
        } else {
            viewThisFlight();
        }
    }

    // Effects: displays the flight user has chosen
    private void viewThisFlight() {
        System.out.println();
        System.out.println("Flight id: " + flightsList.getFlightFromId(idResponse).getIdNumber());
        System.out.println("Title: " + flightsList.getFlightFromId(idResponse).getTitle());
        System.out.println("Origin: " + flightsList.getFlightFromId(idResponse).getOrigin());
        System.out.println("Destination: " + flightsList.getFlightFromId(idResponse).getDestination());
        System.out.println("Hours Flown: " + flightsList.getFlightFromId(idResponse).getHoursFlown());
        System.out.println("Airline: " + flightsList.getFlightFromId(idResponse).getAirline());
        System.out.println("Plane Model: " + flightsList.getFlightFromId(idResponse).getPlaneModel());
        System.out.println("Date: " + flightsList.getFlightFromId(idResponse).getDate());
        System.out.println("Food eaten: " + flightsList.getFlightFromId(idResponse).getFoodEaten());
        System.out.println("Seat: " + flightsList.getFlightFromId(idResponse).getSittingSpot());
        System.out.println("Rating: " + flightsList.getFlightFromId(idResponse).getRating());
        System.out.println();
    }

    // Effects: displays all the flights in travel tracker
    private void viewFlights() {
        for (Flight flight : flightsList.getFlightsList()) {
            System.out.println();
            System.out.println("Flight id: " + flight.getIdNumber());
            System.out.println("Title: " + flight.getTitle());
            System.out.println("Origin: " + flight.getOrigin());
            System.out.println("Destination: " + flight.getDestination());
            System.out.println("Hours Flown: " + flight.getHoursFlown());
            System.out.println("Airline: " + flight.getAirline());
            System.out.println("Plane Model: " + flight.getPlaneModel());
            System.out.println("Date: " + flight.getDate());
            System.out.println("Food eaten: " + flight.getFoodEaten());
            System.out.println("Seat: " + flight.getSittingSpot());
            System.out.println("Rating: " + flight.getRating());
        }
        System.out.println();
        System.out.println("These are all of your flights!");
    }

    // Effects: displays the stats the user wants to see
    private void viewStats() {
        System.out.println("Which stat would you like to see?");
        statsMenu();
        whichStatToView();
    }

    // Effects: selects the appropriate stat to view as chosen by user
    private void whichStatToView() {
        response = input.nextLine();
        if (response.equals("1")) {
            viewTotalFlights();
        } else if (response.equals("2")) {
            viewTotalHoursFlown();
        } else if (response.equals("3")) {
            viewHowManyTimesAisle();
        } else if (response.equals("4")) {
            viewHowManyTimesMiddle();
        } else if (response.equals("5")) {
            viewHowManyTimesWindow();
        } else if (response.equals("6")) {
            viewHighestRated();
        } else if (response.equals("7")) {
            viewLowestRated();
        } else if (response.equals("8")) {
            viewLongest();
        } else if (response.equals("9")) {
            viewShortest();
        } else {
            System.out.println("Your response is invalid. Back to the menu now.");
        }
    }

    // Effects: displays the shortest flight
    private void viewShortest() {
        System.out.println("Your shortest flight was "
                + flightsList.getShortestFlight().getTitle()
                + " where you travelled from " + flightsList.getShortestFlight().getOrigin() + " to "
                + flightsList.getShortestFlight().getDestination() + " which was "
                + flightsList.getShortestFlight().getHoursFlown() + " hours.");
    }

    // Effects: displays the longest flight
    private void viewLongest() {
        System.out.println("Your longest flight was "
                + flightsList.getLongestFlight().getTitle()
                + " where you travelled from " + flightsList.getLongestFlight().getOrigin() + " to "
                + flightsList.getLongestFlight().getDestination() + " which was "
                + flightsList.getLongestFlight().getHoursFlown() + " hours.");
    }

    // Effects: displays the lowest rated flight
    private void viewLowestRated() {
        System.out.println("Your lowest rated flight was "
                + flightsList.getLowestRatedFlight().getTitle()
                + " where you travelled from " + flightsList.getLowestRatedFlight().getOrigin() + " to "
                + flightsList.getLowestRatedFlight().getDestination() + " with a rating of "
                + flightsList.getLowestRatedFlight().getRating());
    }

    // Effects: displays the highest rated flight
    private void viewHighestRated() {
        System.out.println("Your highest rated flight was "
                + flightsList.getHighestRatedFlight().getTitle()
                + " where you travelled from " + flightsList.getHighestRatedFlight().getOrigin() + " to "
                + flightsList.getHighestRatedFlight().getDestination() + " with a rating of "
                + flightsList.getHighestRatedFlight().getRating());
    }

    // Effects: displays how many times sat in window seat
    private void viewHowManyTimesWindow() {
        if (flightsList.getSatWindow() == 1) {
            System.out.println("You have sat in the window seat "
                    + flightsList.getSatWindow() + " time.");
        } else {
            System.out.println("You have sat in the window seat "
                    + flightsList.getSatWindow() + " times.");
        }
    }

    // Effects: displays how many times sat in middle seat
    private void viewHowManyTimesMiddle() {
        if (flightsList.getSatMiddle() == 1) {
            System.out.println("You have sat in the middle seat "
                    + flightsList.getSatMiddle() + " time.");
        } else {
            System.out.println("You have sat in the middle seat "
                    + flightsList.getSatMiddle() + " times.");
        }
    }

    // Effects: displays how many times sat in aisle seat
    private void viewHowManyTimesAisle() {
        if (flightsList.getSatAisle() == 1) {
            System.out.println("You have sat in the aisle seat "
                    + flightsList.getSatAisle() + " time.");
        } else {
            System.out.println("You have sat in the aisle seat "
                    + flightsList.getSatAisle() + " times.");
        }
    }

    // Effects: displays total hours flown
    private void viewTotalHoursFlown() {
        if (flightsList.getTotalHoursFlown() == 1) {
            System.out.println("You have flown a total of "
                    + flightsList.getTotalHoursFlown() + " hour.");
        } else {
            System.out.println("You have flown a total of "
                    + flightsList.getTotalHoursFlown() + " hours.");
        }
    }

    // Effects: displays total number of flights
    private void viewTotalFlights() {
        if (flightsList.getNumFlights() == 1) {
            System.out.println("You have been on "
                    + flightsList.getNumFlights() + " flight.");
        } else {
            System.out.println("You have been on "
                    + flightsList.getNumFlights() + " flights.");
        }
    }

    // Effects: displays the types of stats available
    private void statsMenu() {
        System.out.println("\t 1 - The number of flights you have been on");
        System.out.println("\t 2 - Your total hours flown");
        System.out.println("\t 3 - The number of times you had an aisle seat");
        System.out.println("\t 4 - The number of times you had a middle seat");
        System.out.println("\t 5 - The number of times you had a window seat");
        System.out.println("\t 6 - Your highest rated flight");
        System.out.println("\t 7 - Your lowest rated flight");
        System.out.println("\t 8 - Your longest flight");
        System.out.println("\t 9 - Your shortest flight");
    }

    // Effects: user determines which flight they want to change the rating for
    private void rateFlight() {
        System.out.println("\n Please select the flight you would like to rate.");
        System.out.println("Here are the options of flights you are able to rate:");
        allFlightIds();
        idResponse = integerInput.nextInt();
        if (!(flightsList.getIdNumbers().contains(idResponse))) {
            System.out.println("\n Invalid flight id. Back to the menu now.");
        } else {
            whatToRate();
        }
    }

    // Modifies: this
    // Effects: user sets the rating for the flight they chose
    private void whatToRate() {
        System.out.println("What would you like to rate this flight? Enter a whole number from 1 - 20.");
        int rating = integerInput.nextInt();
        if (rating >= 1 && rating <= 20) {
            flightsList.getFlightFromId(idResponse).setRating(rating);
            System.out.println("You have set the rating to " + rating);
        } else {
            System.out.println("Invalid rating. Back to the menu.");
        }
    }

    // Effects: user determines which flight they want to edit
    private void editFlight() {
        System.out.println("\n Please select the flight you would like to edit.");
        System.out.println("Here are the options of the flights you are able to edit:");
        allFlightIds();
        idResponse = integerInput.nextInt();
        if (!(flightsList.getIdNumbers().contains(idResponse))) {
            System.out.println("\n Invalid flight id. Back to the menu now.");
        } else {
            editMenu();
            whatToEdit();
        }
    }

    // Effects: appropriate method is run based on user input of what to edit
    private void whatToEdit() {
        response = input.nextLine();
        if (response.equals("1")) {
            changeTitle();
        } else if (response.equals("2")) {
            changeOrigin();
        } else if (response.equals("3")) {
            changeDestination();
        } else if (response.equals("4")) {
            changeHoursFlown();
        } else if (response.equals("5")) {
            changeAirline();
        } else if (response.equals("6")) {
            changePlaneModel();
        } else if (response.equals("7")) {
            changeDate();
        } else if (response.equals("8")) {
            changeFood();
        } else if (response.equals("9")) {
            changeSeat();
        } else {
            System.out.println("Your response is invalid. Back to the menu now.");
        }
    }

    // Modifies: this
    // Effects: changes seat for the flight chosen
    private void changeSeat() {
        System.out.println("\n What would you like the seat to be changed to?");
        System.out.println("Please enter aisle, middle, or window.");
        response = input.nextLine();
        flightsList.getFlightFromId(idResponse).setSittingSpot(response);
        System.out.println("The seat has been changed to " + response);
    }

    // Modifies: this
    // Effects: changes food for the flight chosen
    private void changeFood() {
        System.out.println("\n What would you like the food to be changed to?");
        response = input.nextLine();
        flightsList.getFlightFromId(idResponse).setFoodEaten(response);
        System.out.println("The food has been changed to " + response);
    }

    // Modifies: this
    // Effects: changes date for the flight chosen
    private void changeDate() {
        System.out.println("\n What would you like the date to be changed to?");
        response = input.nextLine();
        flightsList.getFlightFromId(idResponse).setDate(response);
        System.out.println("The date has been changed to " + response);
    }

    // Modifies: this
    // Effects: changes model of the plane for the flight chosen
    private void changePlaneModel() {
        System.out.println("\n What would you like the model of the plane to be changed to?");
        response = input.nextLine();
        flightsList.getFlightFromId(idResponse).setPlaneModel(response);
        System.out.println("The model of the plane has been changed to " + response);
    }

    // Modifies: this
    // Effects: changes airline for the flight chosen
    private void changeAirline() {
        System.out.println("\n What would you like the airline to be changed to?");
        response = input.nextLine();
        flightsList.getFlightFromId(idResponse).setAirline(response);
        System.out.println("The airline has been changed to " + response);
    }

    // Modifies: this
    // Effects: changes hours flown for the flight chosen
    private void changeHoursFlown() {
        System.out.println("\n What would you like the hours flown to be changed to?");
        double hoursFlown = doubleInput.nextDouble();
        flightsList.getFlightFromId(idResponse).setHoursFlown(hoursFlown);
        System.out.println("The hours flown has been changed to " + hoursFlown + " hours.");
    }

    // Modifies: this
    // Effects: changes destination for the flight chosen
    private void changeDestination() {
        System.out.println("\n What would you like the destination to be changed to?");
        response = input.nextLine();
        flightsList.getFlightFromId(idResponse).setDestination(response);
        System.out.println("The destination has been changed to " + response);
    }

    // Modifies: this
    // Effects: changes origin for the flight chosen
    private void changeOrigin() {
        System.out.println("\n What would you like the origin to be changed to?");
        response = input.nextLine();
        flightsList.getFlightFromId(idResponse).setOrigin(response);
        System.out.println("The origin has been changed to " + response);
    }

    // Modifies: this
    // Effects: changes title for the flight chosen
    private void changeTitle() {
        System.out.println("\n What would you like the title to be changed to?");
        response = input.nextLine();
        flightsList.getFlightFromId(idResponse).setTitle(response);
        System.out.println("The title has been changed to " + response);
    }

    // Effects: displays menu of what could be edited
    private void editMenu() {
        System.out.println("\n What would you like to edit?");
        System.out.println("\t 1 - Title");
        System.out.println("\t 2 - Origin");
        System.out.println("\t 3 - Destination");
        System.out.println("\t 4 - Hours flown");
        System.out.println("\t 5 - Airline");
        System.out.println("\t 6 - Plane model");
        System.out.println("\t 7 - Date of flight");
        System.out.println("\t 8 - Food eaten on flight");
        System.out.println("\t 9 - Seat (aisle, middle, or window)");
    }

    // Modifies: this
    // Effects: removes flight from travel tracker;
    //          if invalid flight selected, return to menu
    private void removeFlight() {
        System.out.println("Please enter the id of the flight you would like to remove.");
        System.out.println("Here are the options of flights you are able to remove:");
        allFlightIds();
        System.out.println("Enter 0 if you would like to go back to menu.");
        idResponse = integerInput.nextInt();
        if (idResponse == 0) {
            System.out.println("Back to the menu now.");
        } else if (flightsList.getIdNumbers().contains(idResponse)) {
            flightsList.removeFlight(flightsList.getFlightFromId(idResponse));
            System.out.println("\n Flight has been removed. Back to the menu now.");
        } else {
            System.out.println("Invalid flight id. Back to the menu now.");
        }
    }

    // Effects: displays all the flights by their titles and ids
    private void allFlightIds() {
        for (Flight flight : flightsList.getFlightsList()) {
            System.out.println("Title: " + flight.getTitle() + " - id: " + flight.getIdNumber());
        }
    }

    // Effects: displays the menu when there are 0 flights recorded
    //          and allows for user to input their selection
    private void noFlightsYetMenu() {
        System.out.println("\n Please select a numbered option:");
        System.out.println("\t 1 - Add a flight");
        System.out.println("\t 2 - Quit application");
        System.out.println("\t 3 - Load Travel Tracker from file");
        noFlightsOption();
    }

    // Effects: either adds a flight or quits application
    //          depending on user input
    private void noFlightsOption() {
        response = input.nextLine();
        if (response.equals("1")) {
            addFlight();
        } else if (response.equals("2")) {
            quit();
        } else if (response.equals("3")) {
            loadFlightsList();
        } else {
            System.out.println("\n Your response is invalid. Please enter a valid number.");
            noFlightsOption();
        }
    }

    // Effects: saves the flightsList to file
    private void saveFlightsList() {
        try {
            jsonWriter.open();
            jsonWriter.write(flightsList);
            jsonWriter.close();
            System.out.println("Saved your Travel Tracker to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Not able to write to file");
        }
    }

    // Effects: loads the flightsList from file
    private void loadFlightsList() {
        try {
            flightsList = jsonReader.read();
            System.out.println("Loaded your Travel Tracker from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Not able to read from file");
        }
    }

    // Effects: ends the application
    private void quit() {
        hasQuit = true;
    }

    // Modifies: this
    // Effects: adds a flight to the tracker
    private void addFlight() {
        System.out.println("What would you like the title of your flight to be?");
        String title = input.nextLine();
        System.out.println("Where is the origin of this flight?");
        String origin = input.nextLine();
        System.out.println("Where is the destination of this flight?");
        String destination = input.nextLine();
        Flight myFlight = new Flight(title, origin, destination);
        flightsList.addFlight(myFlight);
        System.out.println("\n Flight has been added! You can add more details about this flight whenever you want!");
    }

    // Modifies: this
    // Effects: initializes the travel tracker and user input selections
    private void init() {
        flightsList = new FlightsList();
        input = new Scanner(System.in);
        integerInput = new Scanner(System.in);
        doubleInput = new Scanner(System.in);
    }
}
