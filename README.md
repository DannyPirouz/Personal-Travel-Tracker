# Personal Travel Tracker

## What does the application do?

Personal Travel Tracker is a handy tool that will allow a user to keep track of all of their flights. 
A user can keep track of all important aspects of their flight like the airline, hours flown, the origin,
the destination, and many more. At the end of the flight, the user can give a rating of 
their overall flight experience. A user can also retrieve valuable information, such as total hours flown, 
their longest flight, their highest rated flight, and many more.

## Who will use it?

Personal Travel Tracker is best suited for people that fly quite frequently; however, that does not mean that 
everyone can't use it. Everybody can certainly use Personal Travel Tracker to look back at the flights that 
occurred in their lives.

## Why is this application of interest to me?

Don't you ever wish you could look back and see which airlines you had a good time on? 
Or maybe you want to know how many hours of your life you have spent in the air. I have always enjoyed flying 
and wish I knew all the flights I have been on. It's too hard to remember all the facts about flights, 
so I created this application to have a simple way to look back at the flights a person has been on.

### User Stories:
- As a user, I want to be able to create a new flight and add it to my list of flights and specify
a title, origin, and destination.
- As a user, I want to select a flight and add hours flown, where I sat (aisle, middle, or window), what I ate, 
the date, the airline, and the model of the plane.
- As a user, I want to view the number of flights I have been on.
- As a user, I want to select a flight and rate the experience on a scale of 1-20.
- As a user, I want to delete a flight from my list of flights if it never happened.
- As a user, I want to view the total number of hours I have flown.
- As a user, I want to view how many times I have sat in the aisle, middle, and window seats.
- As a user, I want to view which flight has the highest rating and which has the lowest rating.
- As a user, I want to select a flight and view all the attributes of that flight.
- As a user, I want to view what my longest and shortest flights were.
- As a user, I want to have the option to save my Travel Tracker to file if I choose to.
- As a user, when I start the application, I want to be given the option to load my Travel Tracker from file.


## Instructions for Grader

- You can generate the first required action related to the user story "adding multiple Xs to a Y" by 
clicking "Add Flight" in the main menu. This will prompt you with the details you would like to include. Then
click "Add Flight" and your flight will be added.
- You can generate the second required action related to the user story "adding multiple Xs to a Y" by
clicking "Remove Flight" in the main menu. This will open a tab with all the flights that could possibly 
be removed. Press "OK" and you will get prompted with which flight you would like to remove. Enter the 
flight id, click "Remove Flight" and your flight will be removed.
- You can rate a flight by clicking "Rate Flight" in the main menu. A tab containing all the flights that can be 
rated will open. Press "OK" and you will be prompted with which flight to rate. Enter the flight id and press rate
flight. Then you will be prompted with which what rating you would like to give your flight. Enter a number between
1 - 20 and press "Rate Flight" to complete the rating of your flight.
- You can View all the flights by clicking "View All Flights" in the main menu. A list of all flight in the 
Travel Tracker will be displayed. 
- You can locate my visual component by opening the app. A visual will appear on launch. A visual will also appear
when a flight is added and removed.
- You can save the state of my application by clicking "Save Flights" in the main menu.
- You can reload the state of my application by clicking "Load Flights" in the main menu.

## Phase 4: Task 2

The events that are logged are flights added, removed, rated, and viewed. An example would be: 

Flight "Epic Europe Trip" Added <br />
Flight "-$1000 Vegas" Added <br />
Flight "New York Baby" Added <br />
All Flights Viewed <br />
Flight "New York Baby" rating changed to: 18 <br />
Flight "Epic Europe Trip" Removed <br />
All Flights Viewed <br />
Flight "-$1000 Vegas" rating changed to: 6 <br />
All Flights Viewed 

## Phase 4: Task 3

If I had more time to work on the project, I would refactor my design to include the Singleton Pattern. Right now, 
I make references to flights that I need access to by a field I made in the Flight class called idNumber. With
every new flight created, this idNumber increases by one and is assigned to the flight. So when I want to remove a
flight, for example, I remove it based on this idNumber associated. The problem with my method currently is that it
is not a very accepted approach because it is not good for testing purposes. This approach results in random passing
or failing tests. For instance, sometimes the constructor test will pass while other times it might fail. I implemented
better and different tests to ensure my tests will always pass; however, the Singleton Pattern approach would be
much more effective.

With the Singleton Pattern, I would have created the field "private static FlightsList singleton;" 
in the FlightsList class. Then, I would create a private constructor that instantiates the flightsList field. I would 
create a static method called getInstance() that returns singleton and also has a null checker. I would create 
a getUniqueId() method that returns the unique id for that flight so everytime a new flight is created, the flight 
would be given a unique id. This design pattern would make my code more clear, readable, and efficient. Testing 
would also be normal as it would not result in random passing or failing tests. Overall, this would be the main design 
structure I would change.