package flight;

import android.content.Context;

import data.Constants;
import data.Data;

import java.text.ParseException;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A representation of a directed graph of flights.
 * 
 * @author team_0575
 */
public class FlightDatabase {
  // instance variables
  private List<Flight> listofFlights;
  private Map<Flight, ArrayList<Flight>> nextFlight = new HashMap<Flight, ArrayList<Flight>>();

  /**
   * Creates a new <code>FlightDatabase</code> with the given flights.
   */
  public FlightDatabase(Context context) {

    this.listofFlights = Data.viewAllFlights(context);

    // for each flight, map it to an empty list which represents the
    // different
    // flights it will go to.
    for (Flight flight : listofFlights) {
      ArrayList<Flight> emptyList = new ArrayList<Flight>();
      nextFlight.put(flight, emptyList);
    }

    connectFlights();
  }

  /**
   * Returns an <code>ArrayList</code> of <code>Flight</code>s in this <code>FlightDatabase</code>.
   * 
   * @return a <code>ArrayList</code> of <code>Flight</code>s in this <code>FlightDatabase</code>
   */
  public List<Flight> getFlights() {
    return listofFlights;
  }

  /**
   * Returns the <code>Flight</code> from this <code>FlightDatabase</code> with the given flight
   * number Since each flight has a unique flight number.
   * 
   * @param flightNum the flight number of the <code>Flight</code> to return
   * @return the <code>Flight</code> from this <code>FlightDatabase</code> with the given flight
   *         number, if such <code>Flight</code> exists in this <code>FlightDatabase</code>,
   *         otherwise return null.
   */
  public Flight getFlight(String flightNum) {
    // Check each flight in our list of flights, and if its flight number
    // matches
    // with the given flight number, then return the flight.
    for (Flight flight : listofFlights) {
      if (flight.getFlightNum().equals(flightNum)) {
        return flight;
      }
    }
    // return null if the flight does not exist in our list of flights.
    return null;
  }

  /**
   * Returns a <code>ArrayList</code> of connected flights to the given <code>Flight</code>.
   * 
   * @param flight the <code>Flight</code> whose connect flights are returned.
   * @return a <code>ArrayList</code> of connected flights of <code>Flight</code>.
   */
  public List<Flight> getConnectedFlights(Flight flight) {
    return nextFlight.get(flight);
  }

  /**
   * Returns the number of flights in this <code>FlightDatabase</code>.
   * 
   * @return the number of flights in this <code>FlightDatabase</code>
   */
  public int getNumFlights() {
    return getFlights().size();
  }

  /**
   * Returns the number of itineraries in this <code>FlightDatabase</code>, (returns the number of
   * possible flight sequences.).
   * 
   * @return the number of itineraries in this <code>FlightDatabase</code>
   */
  public int getTotalNumItineraries() {

    // Each individual flights from a certain origin and destination are
    // also itineraries.
    int total = getFlights().size();
    for (Flight flight : getFlights()) {
      total += getConnectedFlights(flight).size();
    }
    return total;
  }


  /**
   * Given Flight flight1 and Flight flight1, this methods returns whether the second flight departs
   * within the minimum and maximum lay over duration of the first flight's arrival.
   * 
   * @param flight1 the first flight that arrives.
   * @param flight2 the second flight that departs.
   * @return true if the second given flight departs between the minimum and maximum lay over
   *         duration of the first flight's arrival, and false other wise.
   */
  public boolean leavesAfter(Flight flight1, Flight flight2) {

    // Get the date when the first flight arrives.
    String flightoneDate = flight1.getArrivalDateTime();
    // Get the date when the second flight departs
    String flighttwoDate = flight2.getDepartureDateTime();


    // Convert the minimum and maximum lay over duration to milliseconds.
    long minMill = Constants.MIN_LAYOVER*1000;
    long maxMill = Constants.MAX_LAYOVER*1000;

    try {

      // Convert flightoneDate and flighttwoDate to Date type.
      Date flightoneArrivalDate = Constants.DATE_TIME_FORMAT.parse(flightoneDate);
      Date flighttwoDepartureDate = Constants.DATE_TIME_FORMAT.parse(flighttwoDate);

      // Convert flightoneDate to milliseconds and add the minimum lay over duration in
      // milliseconds.
      long minlayovermill = flightoneArrivalDate.getTime() + minMill;

      // Convert flightoneDate to milliseconds and add the maximum lay over duration in
      // milliseconds.
      long maxlayovermill = flightoneArrivalDate.getTime() + maxMill;

      // Convert the minimum and maximum lay over dates back to Date type.
      String minlayoverdateTime = Constants.DATE_TIME_FORMAT.format(new Date(minlayovermill));
      String maxlayoverdateTime = Constants.DATE_TIME_FORMAT.format(new Date(maxlayovermill));
      Date date1min = Constants.DATE_TIME_FORMAT.parse(minlayoverdateTime);
      Date date1max = Constants.DATE_TIME_FORMAT.parse(maxlayoverdateTime);

      // Return true if the departure date for the second flight is between the
      // minimum and maximum lay over date of the first flight's arrival date.
      if ((flighttwoDepartureDate.after(date1min)) && (flighttwoDepartureDate.before(date1max))) {
        return true;
      }

    } catch (ParseException ex) {
      ex.printStackTrace();
    }

    // Return false if the minimum time difference between the first and second flights are
    // less than the minimum lay over duration or greater than the maximum lay over duration.
    return false;
  }

  /**
   * Connects all flights to other possible flights in this <code>FlightDatabase</code> if they
   * depart within a specific duration of the first flights arrival.
   */
  public void connectFlights() {

    // Get the destination for each flight in our list of flights.
    for (Flight flight1 : listofFlights) {
      String flight1Dest = flight1.getDestination();

      for (Flight flight2 : listofFlights) {

        // If the destination of the flight matches the origin of any other flights in this
        // <code>FlightDatabase</code> and it departs between the minimum and maximum
        // lay over duration, connect the first flight to the second..
        if (flight1Dest.equals(flight2.getOrigin())) {
          if (leavesAfter(flight1, flight2) == true) {
            getConnectedFlights(flight1).add(flight2);
          }
        }
      }
    }
  }

  /**
   * Adds a new <code>Flight</code> with the given flight to this <code>FlightDatabase</code>.
   * 
   * @param flight the flight to add to this <code>FlightDatabase</code>.
   */
  public void addFlight(Flight flight) {
    if (flight != null) {

      // Add the flight to the list of flights and map it to an empty list
      // which could later contain any flight it connects to.
      listofFlights.add(flight);
      ArrayList<Flight> emptyList = new ArrayList<Flight>();
      nextFlight.put(flight, emptyList);

    }
  }

  @Override
  public String toString() {

    // To show the representation of this flight database in a string, first print the total
    // number of flights and itineraries in this database.
    String result = String.format("Number of flights: %d\nTotal number of itineraries: %d\n",
        getNumFlights(), getTotalNumItineraries());

    // Then for each flight print what other flights it connects to, that is if it connects
    // to a flight.
    for (Flight flight : getFlights()) {
      result += String.format("%s connects to: ", flight);
      for (Flight connectedFlights : getConnectedFlights(flight)) {
        result += String.format("%s ", connectedFlights);
      }
      result += "\n";
    }
    return result;
  }
}
