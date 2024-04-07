package ui;

import model.Event;
import model.EventLog;
import model.Flight;
import model.FlightsList;
import persistance.JsonReader;
import persistance.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;

// Attributing source: https://github.students.cs.ubc.ca/CPSC210/AlarmSystem

// Travel Tracker GUI application. Flights can be added, removed, rated and all flights can be viewed.
// FlightList can be saved and loaded.
public class TravelTrackerAppGUI extends JFrame {

    private FlightsList flightsList;

    private static final String JSON_STORE = "./data/flightsList.json";
    private static final String FLIGHT_ADDED_IMG = "./src/main/images/flight_added.png";
    private static final String LAUNCH_SCREEN_FINAL_IMG = "./src/main/images/launch_screen_final.png";
    private static final String FLIGHT_REMOVED_IMG = "./src/main/images/flight_removed.png";
    private static final String FLIGHT_ICON = "./src/main/images/plane2.jpg";

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private JButton addFlightButton = new JButton("Add Flight");
    private JButton removeButton = new JButton("Remove Flight");
    private JButton loadFlightsButton = new JButton("Load Flights");
    private JButton saveFlightsButton = new JButton("Save Flights");
    private JButton viewAllFlightsButton = new JButton("View All Flights");
    private JButton quitButton = new JButton("Quit");
    private JButton rateButton = new JButton("Rate Flight");

    private JPanel mainPanel = new JPanel();

    private JTextField titleField = new JTextField();
    private JTextField originField = new JTextField();
    private JTextField destinationField = new JTextField();
    private JTextField removeField = new JTextField();
    private JTextField rateField = new JTextField();
    private JTextField ratingField = new JTextField();

    private JLabel titleLabel = new JLabel("  Title:");
    private JLabel originLabel = new JLabel("  Origin:");
    private JLabel destinationLabel = new JLabel("  Destination:");
    private JLabel removeLabel = new JLabel("  Remove (enter flight id):");
    private JLabel rateLabel = new JLabel("  Rate flight (enter flight id):");
    private JLabel ratingLabel = new JLabel("  Rate flight between 1-20:");


    // Effects: Runs the Travel Tracker GUI application
    public TravelTrackerAppGUI() {
        displayLaunchScreen();
    }

    // Effects: sets up the main JFrame, Adds the main panel,
    //          and initializes the application
    private void mainFrame() {
        ImageIcon icon = new ImageIcon(FLIGHT_ICON);
        setIconImage(icon.getImage());
        setTitle("Travel Tracker");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        init();
        buttonListeners();
        mainPanel();
        add(mainPanel);
        setVisible(true);
    }

    // Effects: Sets up the main panel with all the buttons
    //          as well as the grid layout
    private void mainPanel() {
        mainPanel.setLayout(new GridLayout(7, 1));
        mainPanel.add(addFlightButton);
        mainPanel.add(removeButton);
        mainPanel.add(rateButton);
        mainPanel.add(loadFlightsButton);
        mainPanel.add(saveFlightsButton);
        mainPanel.add(viewAllFlightsButton);
        mainPanel.add(quitButton);
    }

    // Effects: Adds all the action listeners to the buttons
    private void buttonListeners() {
        addFlightButton.addActionListener(e -> addFlightPanel());
        removeButton.addActionListener(e -> removeFlightPanel());
        rateButton.addActionListener(e -> rateFlightPanel());
        loadFlightsButton.addActionListener(e -> loadFlightsList());
        saveFlightsButton.addActionListener(e -> save());
        viewAllFlightsButton.addActionListener(e -> viewAllFlights());
        quitButton.addActionListener(e -> quit());
    }

    // Effects: Creates the rate flight panel with text fields, labels,
    //          and buttons needed. Action listeners are also added.
    private void rateFlightPanel() {
        JFrame rateFlightFrame = new JFrame("Rate Flight");
        rateFlightFrame.setSize(325, 125);
        rateFlightFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        rateFlightFrame.setLocationRelativeTo(null);

        JButton afterMenuRateFlightButton = new JButton("Rate Flight");

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));
        panel.add(rateLabel);
        panel.add(rateField);
        panel.add(new JLabel());
        panel.add(afterMenuRateFlightButton);

        viewAllFlightIds("rate");

        afterMenuRateFlightButton.addActionListener(e -> {
            whichFlightToRate();
            rateFlightFrame.dispose();
        });
        rateFlightFrame.add(panel);
        rateFlightFrame.setVisible(true);
    }

    // Effects: Rates flight if input is within 1-20.
    //          Otherwise, prints out error message.
    private void whichFlightToRate() {
        if (flightsList.getIdNumbers().contains(Integer.parseInt(rateField.getText()))) {
            rateFlight();
        } else {
            JOptionPane.showMessageDialog(null, "Invalid flight id. Back to the menu now.");
        }
    }

    // Effects: Creates the rating flight panel with text fields, labels
    //          and buttons needed. Action listeners are also added. This is
    //          the second panel of the rate flight action.
    private void rateFlight() {
        JFrame ratingFlightFrame = new JFrame("Rate Flight");
        ratingFlightFrame.setSize(325, 125);
        ratingFlightFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ratingFlightFrame.setLocationRelativeTo(null);

        JButton afterMenuRatingFlightButton = new JButton("Rate Flight");

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));
        panel.add(ratingLabel);
        panel.add(ratingField);
        panel.add(new JLabel());
        panel.add(afterMenuRatingFlightButton);

        afterMenuRatingFlightButton.addActionListener(e -> {
            ratingFlight();
            ratingFlightFrame.dispose();
        });
        ratingFlightFrame.add(panel);
        ratingFlightFrame.setVisible(true);
    }

    // Modifies: this, flight
    // Effects: If rating is between 1-20, set flight rating to input, and reset field inputs
    //          otherwise, print error message.
    private void ratingFlight() {
        if (Integer.parseInt(ratingField.getText()) >= 1 && Integer.parseInt(ratingField.getText()) <= 20) {
            flightsList.getFlightFromId(Integer.parseInt(rateField.getText()))
                    .setRating(Integer.parseInt(ratingField.getText()));
            rateField.setText("");
            ratingField.setText("");
        } else {
            JOptionPane.showMessageDialog(null, "Invalid rating. Back to the menu now.");
        }
    }

    // Effects: Constructs the remove flight panel with text fields, labels,
    //          and buttons needed. Action listeners are also added.
    private void removeFlightPanel() {
        JFrame removeFlightFrame = new JFrame("Remove Flight");
        removeFlightFrame.setSize(325, 125);
        removeFlightFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        removeFlightFrame.setLocationRelativeTo(null);

        JButton afterMenuRemoveFlightButton = new JButton("Remove Flight");

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));
        panel.add(removeLabel);
        panel.add(removeField);
        panel.add(new JLabel());
        panel.add(afterMenuRemoveFlightButton);

        viewAllFlightIds("remove");

        afterMenuRemoveFlightButton.addActionListener(e -> {
            removeFlight();
            removeFlightFrame.dispose();
        });
        removeFlightFrame.add(panel);
        removeFlightFrame.setVisible(true);
    }

    // Effects: Displays the flight image for 1.5 seconds
    //          and does not repeat
    private void displayPlaneImage(String img) {
        ImageIcon planeIcon = new ImageIcon(img);
        JLabel planeLabel = new JLabel(planeIcon);
        JFrame planeFrame = new JFrame();
        planeFrame.setUndecorated(true);
        planeFrame.getContentPane().add(planeLabel);
        planeFrame.pack();
        planeFrame.setLocationRelativeTo(null);
        planeFrame.setVisible(true);

        Timer timer = new Timer(1500, e -> {
            planeFrame.dispose();
        });
        timer.setRepeats(false);
        timer.start();
    }

    // Modifies: this
    // Effects: If input number is in flightsList id, remove flight and displayRemoveFlightImage()
    //          Otherwise, print error message. Always set removeField to "" after.
    private void removeFlight() {
        if (flightsList.getIdNumbers().contains(Integer.parseInt(removeField.getText()))) {
            flightsList.removeFlight(flightsList.getFlightFromId(Integer.parseInt(removeField.getText())));
            displayPlaneImage(FLIGHT_REMOVED_IMG);
        } else {
            JOptionPane.showMessageDialog(null, "Invalid flight id. Back to the menu now.");
        }
        removeField.setText("");
    }

    // Effects: Displays the launch screen image for 2.5 seconds
    //          and does not repeat
    private void displayLaunchScreen() {
        ImageIcon planeIcon = new ImageIcon(LAUNCH_SCREEN_FINAL_IMG);
        JLabel planeLabel = new JLabel(planeIcon);
        JFrame planeFrame = new JFrame();
        planeFrame.setUndecorated(true);
        planeFrame.getContentPane().add(planeLabel);
        planeFrame.pack();
        planeFrame.setLocationRelativeTo(null);
        planeFrame.setVisible(true);

        Timer timer = new Timer(2500, e -> {
            planeFrame.dispose();
            mainFrame();
        });
        timer.setRepeats(false);
        timer.start();
    }

    // Effects: Quits the application
    private void quit() {
        printLog(EventLog.getInstance());
        System.exit(0);
    }

    private void printLog(EventLog el) {
        for (Event event : el) {
            System.out.println(event.getDescription());
        }
    }

    // Effects: Initializes the application by instantiating
    //          jsonWriter, jsonReader, and flightsList.
    private void init() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        flightsList = new FlightsList();
    }

    // Effects: Loads flightList from file and print "Travel Tracker Loaded From File"
    //          otherwise, prints an error message
    private void loadFlightsList() {
        try {
            flightsList = jsonReader.read();
            JOptionPane.showMessageDialog(null, "Travel Tracker Loaded From File");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Unable to Load Travel Tracker From File");
        }
    }


    // Effects: creates a scrollPane that displays every flight detail for every flight
    //          added to the travel tracker
    private void viewAllFlights() {
        JTextArea textArea = new JTextArea(30, 30);
        StringBuilder flightsInfo = new StringBuilder();
        for (Flight flight : flightsList.getFlightsList()) {
            flightsInfo.append("Flight id: ").append(flight.getIdNumber()).append("\n");
            flightsInfo.append("Title: ").append(flight.getTitle()).append("\n");
            flightsInfo.append("Origin: ").append(flight.getOrigin()).append("\n");
            flightsInfo.append("Destination: ").append(flight.getDestination()).append("\n");
            flightsInfo.append("Hours Flown: ").append(flight.getHoursFlown()).append("\n");
            flightsInfo.append("Airline: ").append(flight.getAirline()).append("\n");
            flightsInfo.append("Plane Model: ").append(flight.getPlaneModel()).append("\n");
            flightsInfo.append("Date: ").append(flight.getDate()).append("\n");
            flightsInfo.append("Food eaten: ").append(flight.getFoodEaten()).append("\n");
            flightsInfo.append("Seat: ").append(flight.getSittingSpot()).append("\n");
            flightsInfo.append("Rating: ").append(flight.getRating()).append("\n");
            flightsInfo.append("\n");
        }
        textArea.setText(flightsInfo.toString());
        JScrollPane scrollPane = new JScrollPane(textArea);
        JOptionPane.showMessageDialog(null, scrollPane, "All Flights", JOptionPane.PLAIN_MESSAGE);
        flightsList.addViewAllFlights();
    }

    // Effects: Creates a scrollPane for that displays flight id's and
    //          titles for every flight added to the Travel Tracker
    private void viewAllFlightIds(String action) {
        JTextArea textArea = new JTextArea(30, 30);
        StringBuilder flightsInfo = new StringBuilder();
        flightsInfo.append("  Which Flight would you like to " + action + " based on flight id:");
        flightsInfo.append("\n");
        flightsInfo.append("\n");
        for (Flight flight : flightsList.getFlightsList()) {
            flightsInfo.append("  Flight id: ").append(flight.getIdNumber()).append("\n");
            flightsInfo.append("  Title: ").append(flight.getTitle()).append("\n");
            flightsInfo.append("\n");
        }
        flightsInfo.append("\n");
        flightsInfo.append("  Answer on the next tab");
        textArea.setText(flightsInfo.toString());
        JScrollPane scrollPane = new JScrollPane(textArea);
        JOptionPane.showMessageDialog(null, scrollPane, "All Flight Ids", JOptionPane.PLAIN_MESSAGE);
    }


    // Effects: Saves flightsList to file and prints "Travel Tracker Saved To File"
    //          otherwise, print error message
    private void save() {
        try {
            jsonWriter.open();
            jsonWriter.write(flightsList);
            jsonWriter.close();
            JOptionPane.showMessageDialog(null, "Travel Tracker Saved To File");
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Not Able To Save To File");
        }
    }

    // Effects: Constructs the add flight panel with text fields, labels
    //          and buttons needed. Action listener is also added.
    private void addFlightPanel() {
        JFrame addFlightFrame = new JFrame("Add Flight");
        addFlightFrame.setSize(500, 400);
        addFlightFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addFlightFrame.setLocationRelativeTo(null);

        JButton afterMenuAddFlightButton = new JButton("Add Flight");

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));
        panel.add(titleLabel);
        panel.add(titleField);
        panel.add(originLabel);
        panel.add(originField);
        panel.add(destinationLabel);
        panel.add(destinationField);
        panel.add(new JLabel());
        panel.add(afterMenuAddFlightButton);

        afterMenuAddFlightButton.addActionListener(e -> {
            addingFlight();
            addFlightFrame.dispose();
            displayPlaneImage(FLIGHT_ADDED_IMG);
        });
        addFlightFrame.add(panel);
        addFlightFrame.setVisible(true);
    }

    // Modifies: this
    // Effects: adds flight to flightsList and deletes field text
    private void addingFlight() {
        Flight myFlight = new Flight(titleField.getText(), originField.getText(), destinationField.getText());
        flightsList.addFlight(myFlight);
        deleteText();
    }

    // Modifies: this
    // Effects: sets text for these fields back to ""
    private void deleteText() {
        titleField.setText("");
        originField.setText("");
        destinationField.setText("");
    }
}

