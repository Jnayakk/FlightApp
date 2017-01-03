package user;

import android.content.Context;

import data.FlightCollection;
import flight.Flight;
import flight.FlightDatabase;
import flight.Itinerary;

import java.io.Serializable;
import java.util.List;



/**
 * A representation of a <code>User</code>.
 * 
 * @author team_0575
 */
public class User implements Serializable{
  private String email;
  private String password;


  /**
   * Create a new User given an email and password.
   * 
   * @param email The email/username of the <code>User</code>.
   * @param password The password of the <code>User</code>.
   */
  public User(Context context, String email, String password) {
    this.email = email;
    this.password = password;

  }

  /**
   * Return the password of the <code>User</code>.
   * 
   * @return the password of the <code>User</code>.
   */
  public String getPassword() {
    return password;
  }

  /**
   * Return the email/username of the <code>User</code>.
   * 
   * @return The email of the <code>User</code>.
   */
  public String getEmail() {
    return email;
  }

  /**
   * Search for <code>Flight</code>s that have specified departure date, origin and destination and
   * sorted by the time.
   * 
   * @param departureDate The departure date of the <code>Flight</code>.
   * @param origin The origin of the <code>Flight</code>.
   * @param destination The destination of the <code>Flight</code>.
   * @return A <code>List</code> of <code>Flight</code>s that have the given departure date, origin
   *         and destination sorted by time.
   */
  public List<Flight> searchFlightByTime(Context context, String departureDate, String origin, String destination) {
    return FlightCollection
        .flightSortByTime(FlightCollection.searchFlight(context, departureDate, origin, destination));
  }

  /**
   * Search for <code>Flight</code>s that have specified departure date, origin and destination and
   * sorted by the cost.
   * 
   * @param departureDate The departure date of the <code>Flight</code>.
   * @param origin The origin of the <code>Flight</code>.
   * @param destination The destination of the <code>Flight</code>.
   * @return A <code>List</code> of <code>Flight</code>s that have the given departure date, origin
   *         and destination sorted by cost.
   */
  public List<Flight> searchFlightByCost(Context context, String departureDate, String origin, String destination) {
    return FlightCollection
        .flightSortByCost(FlightCollection.searchFlight(context, departureDate, origin, destination));
  }

  /**
   * Search for <code>Itinerary</code>s with given of the FlightDatabase and have specified
   * departure date, origin and destination and sorted by the time.
   * 
   * @param graph The graph of all the <code>Itinerary</code>s.
   * @param departureDate The departure date of the <code>Itinerary</code>.
   * @param origin The origin of the <code>Itinerary</code>.
   * @param destination The destination of the <code>Itinerary</code>.
   * @return A <code>List</code> of <code>Itinerary</code>s that have the given departure date,
   *         origin and destination sorted by time.
   */
  public List<Itinerary> searchItineraryTime(Context context, FlightDatabase graph,
                                             String departureDate, String origin,
                                             String destination) {
    return FlightCollection.itinerarySortByTime(
        FlightCollection.searchItinerary(context, graph, departureDate, origin, destination));
  }

  /**
   * Search for <code>Itinerary</code>s with given of the FlightDatabase and have specified
   * departure date, origin and destination and sorted by the cost.
   * 
   * @param graph The graph of all the <code>Itinerary</code>s.
   * @param departureDate The departure date of the <code>Itinerary</code>.
   * @param origin The origin of the <code>Itinerary</code>.
   * @param destination The destination of the <code>Itinerary</code>.
   * @return A <code>List</code> of <code>Itinerary</code>s that have the given departure date,
   *         origin and destination sorted by cost.
   */
  public List<Itinerary> searchItineraryByCost(Context context, FlightDatabase graph,
                                               String departureDate, String origin,
                                               String destination) {
    return FlightCollection.itinerarySortByCost(
        FlightCollection.searchItinerary(context, graph, departureDate, origin, destination));
  }
}
