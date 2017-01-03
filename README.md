# FlightApp


### About
The Flight Booking android application is for booking airline tickets. It allows users to search for trips by providing the origin, destination and travel dates information in order to display a list of available flights. In addition, this app provides an admin the functionality to store, retrieve and update flight information, as well as client information.

#### Login Screen:

![loginscreen](https://cloud.githubusercontent.com/assets/24378046/21598246/4386724c-d127-11e6-82f2-b79695dcb959.png)

#### Main Client Screen

![clientmain](https://cloud.githubusercontent.com/assets/24378046/21598253/97ef43cc-d127-11e6-8c1e-ebf48f7f15a7.png)

#### Search Screen:

![searchactivity](https://cloud.githubusercontent.com/assets/24378046/21598254/9b6c62f0-d127-11e6-83ca-c0e1b3b04b37.png)

#### Flights Screen:
This screen is displayed after a user searches for flights from New York to Chicago on the date September 30th 2016.

![flightsactivity](https://cloud.githubusercontent.com/assets/24378046/21598255/a3e57e30-d127-11e6-9a09-0e62d382a38c.png)

#### Itineraries Screen:
This screen is displayed after a user searches for itineraries (connecting flights) from New York to Chicago on the date September 30th 2016.

![showitineraries](https://cloud.githubusercontent.com/assets/24378046/21598258/b575bee4-d127-11e6-90c3-0b6dcafca839.png)

#### Booking Screen:
After clicking on an itinerary, it shows all of the connecting flights, travel times, prices and an option to book the flight.

![showitineraryflights](https://cloud.githubusercontent.com/assets/24378046/21598261/bba50086-d127-11e6-87e4-6abd1af7b8f8.png)

### How the data is stored
The data in this application is stored in three different csv files. In Users.csv, all corresponding information about the users is stored including usernames, first and last names, passwords, phone numbers, addresses, credit card numbers, and emails. For Flights.csv, since each flight number is unique, to save more space only the flight numbers are stored in the csv file, same goes for booked flights. Storing it this way makes it much easier to search through any flights or users, add flights or even remove them.
