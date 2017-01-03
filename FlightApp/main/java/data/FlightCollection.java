package data;

import android.content.Context;

import flight.Flight;
import flight.FlightDatabase;
import flight.Itinerary;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;


/**
 * A FlightCollection class to deal with all the searching and sorting of <code>Flight</code>s and
 * <code>Itinerary</code>s.
 *
 * @author team_0575
 */
public class FlightCollection {

  /**
   * Search for <code>Flight</code>s with the given departure date, origin and destination.
   *
   * @param departureDate The departure date of the <code>Flight</code>.
   * @param origin The origin of the <code>Flight</code>.
   * @param destination The destination of the <code>Flight</code>.
   * @return A <code>List</code> of <code>Flight</code>s that matches the given parameters
   */
  public static List<Flight> searchFlight(Context context, String departureDate,
                                          String origin, String destination) {
    List<Flight> flights = Data.viewAllFlights(context);
    List<Flight> matchedFlights = new ArrayList<Flight>();
    for (Flight flight : flights) {
      if (flight.getDepartureDate().equals(departureDate) && flight.getOrigin().equals(origin)
              && flight.getDestination().equals(destination)) {
        matchedFlights.add(flight);
      }
    }
    return matchedFlights;
  }

  /**
   * Sort the <code>List</code> of <code>Flight</code>s by time.
   *
   * @param flights The <code>List</code> of <code>Flight</code>s to be sorted.
   * @return A sorted <code>List</code> of <code>Flight</code>s by their time.
   */
  public static List<Flight> flightSortByTime(List<Flight> flights) {

    Collections.sort(flights, new Comparator<Flight>() {
      @Override
      public int compare(Flight flight1, Flight flight2) {
        return (int) (flight1.getTravelTime() - flight2.getTravelTime());
      }
    });
    return flights;
  }

  /**
   * Sort the <code>List</code> of <code>Flight</code>s by cost.
   *
   * @param flights The <code>List</code> of <code>Flight</code>s to be sorted.
   * @return A sorted <code>List</code> of <code>Flight</code>s by their cost.
   */
  public static List<Flight> flightSortByCost(List<Flight> flights) {

    Collections.sort(flights, new Comparator<Flight>() {
      @Override
      public int compare(Flight flight1, Flight flight2) {
        return Double.compare(flight1.getCost(), flight2.getCost());
      }
    });
    return flights;
  }

  /**
   * Search for <code>Itinerary</code>s with the given departure date, origin and destination.
   *
   * @param graph The FlightDatabse of all the <code>Flight</code>s in the system.
   * @param departureDate The departure date of the <code>Itinerary</code>.
   * @param origin The origin of the <code>Itinerary</code>.
   * @param destination The destination of the <code>Itinerary</code>.
   * @return A <code>List</code> of <code>Itinerary</code>s that matches the given parameters
   */
  public static List<Itinerary> searchItinerary(Context context, FlightDatabase graph,
                                                String departureDate, String origin,
                                                String destination) {
    List<String> prevOrigins = new ArrayList<>();
    List<List<String>> flightNums = new ArrayList<List<String>>();
    Boolean notBeenTo = true;
    Date departDate = new Date();
    try {
      departDate = Constants.DATE_ONLY_FORMAT.parse(departureDate);
    } catch (ParseException ex) {
      ex.printStackTrace();
    }
    for (Flight flight : graph.getFlights()) {

      Date flightDepartureDate = new Date();

      try {
        flightDepartureDate = Constants.DATE_ONLY_FORMAT.parse(flight.getDepartureDate());
      } catch (ParseException ex) {
        ex.printStackTrace();
      }

      for (String prevOrigin : prevOrigins) {
        if (flight.getDestination().equals(prevOrigin)) {
          notBeenTo = false;
        }
      }

      if (departDate.equals(flightDepartureDate) && notBeenTo
          && flight.getOrigin().equals(origin)) {

        List<String> itinerary = new ArrayList<>();

        if (flight.getDestination().equals(destination)) {
          itinerary.add(flight.getFlightNum());
          flightNums.add(itinerary);
        } else {
          List<Flight> connectingFlights = graph.getConnectedFlights(flight);
          prevOrigins.add(flight.getOrigin());
          for (Flight connectingFlight : connectingFlights) {

            List<List<String>> connects = FlightCollection.searchConnectingFlights(graph,
                connectingFlight, destination, prevOrigins);

            for (List<String> flightNumList : connects) {
              flightNumList.add(0, flight.getFlightNum());
              flightNums.add(flightNumList);
            }
          }
        }
      }
    }
    List<Itinerary> itineraries = new ArrayList<Itinerary>();
    for (List<String> itinerary : flightNums) {
      Itinerary itin = new Itinerary();
      itin.addFlight(context, itinerary);
      itineraries.add(itin);
    }
    return itineraries;
  }

  /**
   * Get all the connecting flights with the given origin and destination.
   *
   * @param graph The FlightDatabse of all the <code>Flight</code>s in the system.
   * @param destination The destination of the <code>Itinerary</code>.
   * @param prevOrigins A <code>List</code> of all the previous origins.
   * @return A <code>List</code> of <code>List</code> of <code>String</code>s of all the connecting
   *         <code>Flight</code>'s flight number.
   */
  private static List<List<String>> searchConnectingFlights(FlightDatabase graph, Flight flight,
      String destination, List<String> prevOrigins) {
    List<List<String>> flightNums = new ArrayList<List<String>>();
    Boolean notBeenTo = true;
    for (String prevOrigin : prevOrigins) {
      if (flight.getDestination().equals(prevOrigin)) {
        notBeenTo = false;
      }
    }
    if (notBeenTo) {
      List<String> itinerary = new ArrayList<>();
      if (flight.getDestination().equals(destination)) {
        itinerary.add(flight.getFlightNum());
        flightNums.add(itinerary);
      } else {
        List<Flight> connectingFlights = graph.getConnectedFlights(flight);
        prevOrigins.add(flight.getOrigin());
        for (Flight connectingFlight : connectingFlights) {
          List<List<String>> connects = FlightCollection.searchConnectingFlights(graph,
              connectingFlight, destination, prevOrigins);
          for (List<String> flightNumList : connects) {
            flightNumList.add(0, flight.getFlightNum());
            flightNums.add(flightNumList);
          }
        }
      }
    }
    return flightNums;
  }

  /**
   * Sort the <code>List</code> of <code>Itinerary</code>s by time.
   *
   * @param itineraries The <code>List</code> of <code>Itinerary</code>s to be sorted.
   * @return A sorted <code>List</code> of <code>Itinerary</code>s by their time.
   */
  public static List<Itinerary> itinerarySortByCost(List<Itinerary> itineraries) {

    Collections.sort(itineraries, new Comparator<Itinerary>() {
      @Override
      public int compare(Itinerary itinerary1, Itinerary itinerary2) {
        return Double.compare(itinerary1.getTotalCost(), itinerary2.getTotalCost());
      }
    });
    return itineraries;
  }

  /**
   * Sort the <code>List</code> of <code>Itinerary</code>s by cost.
   *
   * @param itineraries The <code>List</code> of <code>Itinerary</code>s to be sorted.
   * @return A sorted <code>List</code> of <code>Itinerary</code>s by their cost.
   */
  public static List<Itinerary> itinerarySortByTime(List<Itinerary> itineraries) {
    Collections.sort(itineraries, new Comparator<Itinerary>() {
      @Override
      public int compare(Itinerary itinerary1, Itinerary itinerary2) {
        return (int) (itinerary1.getTotalTravelTime() - itinerary2.getTotalTravelTime());
      }
    });
    return itineraries;
  }
}
