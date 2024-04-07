package ui;

import java.io.FileNotFoundException;

// Main method that runs the console-based program
public class Main {
    public static void main(String[] args) {
        try {
            new TravelTrackerApp();
        } catch (FileNotFoundException e) {
            System.out.println("Not able to run Travel Tracker. File was not found.");
        }
    }
}
