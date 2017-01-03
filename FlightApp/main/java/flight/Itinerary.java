package flight;

import android.content.Context;

import data.Constants;
import data.Data;
import data.FlightCollection;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * A representation of itinerary.
 * 
 * @author team_0575
 */
public class Itinerary implements Serializable {

  private List<Flight> flights;
  private double totalCost;
  private double stopoverTime;

  /**
   * Creates a new <code>Itinerary</code>.
   */
  public Itinerary() {
    this.flights = new ArrayList<Flight>();
    this.totalCost = 0;
    this.stopoverTime = 0;
  }


  /**
   * Take a list flight number and add flights it represent to this <code>Itinerary</code>.
   * 
   * @param flightNums a list of flight number to add to this <code>Itinerary</code>.
   */
  public void addFlight(Context context, List<String> flightNums) {
    for (String flightNum : flightNums) {
      if (!flights.contains(flightNum)) {
        flights.add(Data.viewFlight(context, flightNum));
        totalCost += Data.viewFlight(context, flightNum).getCost();
      }
    }

  }

  /**
   * Add a <code>Flight</code> to the <code>Itinerary</code> given the <code>Flight</code>.
   * 
   * @param flight The <code>Flight</code> to be added.
   */
  public void addFlight(Flight flight) {
    if (!flights.contains(flight)) {
      flights.add(flight);
      totalCost += flight.getCost();
    }
  }

  /**
   * Get and return all the <code>Flight</code>s in the <code>Itinerary</code>.
   * 
   * @return A <code>List</code> of <code>Flight</code>s.
   */
  public List<Flight> getFlights() {
    return flights;
  }

  /**
   * Get and return the departure date of the <code>Itinerary</code>.
   * 
   * @return The departure date of the <code>Itinerary</code> in string format.
   */
  public String getDepartureDate() {
    return flights.get(0).getDepartureDate();
  }

  /**
   * Get and return the departure date and time of the <code>Itinerary</code>.
   * 
   * @return The departure date and time of the <code>Itinerary</code>.
   */
  public String getDepartureDateTime() {
    return flights.get(0).getDepartureDateTime();

  }

  /**
   * Get and return the arrival date of the <code>Itinerary</code>.
   * 
   * @return The arrival date of the <code>Itinerary</code> in string format.
   */
  public String getArrivalDate() {
    return flights.get(-1).getArrivalDate();
  }

  /**
   * Get and return the arrival date and time of the <code>Itinerary</code>.
   * 
   * @return The arrival date and time of the <code>Itinerary</code> in string format.
   */
  public String getArrivalDateTime() {
    return flights.get(flights.size()-1).getArrivalDateTime();
  }

  /**
   * Get and return the total cost of the <code>Itinerary</code>.
   * 
   * @return The total cost of the <code>Itinerary</code> in string format.
   */
  public double getTotalCost() {
    return totalCost;
  }

  /**
   * Get and return the total travel time of the <code>Itinerary</code>.
   * 
   * @return The total travel time of the <code>Itinerary</code>.
   */
  public double getTotalTravelTime() {
    List<Flight> sortedFlights = FlightCollection.flightSortByTime(flights);
    Date arrival = new Date();
    Date departure = new Date();
    try {
      arrival = Constants.DATE_TIME_FORMAT.parse(sortedFlights.get(sortedFlights.size()-1).getArrivalDateTime());
      departure = Constants.DATE_TIME_FORMAT.parse(sortedFlights.get(0).getDepartureDateTime());
    } catch (ParseException ex) {
      ex.printStackTrace();
    }
    double duration =  (arrival.getTime() - departure.getTime()) / 60000;
    return duration;
  }
  
  public double getTotalStopoverTime(){
    return stopoverTime;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((flights == null) ? 0 : flights.hashCode());
    return result;
  }

  public int getNumberOfStops() {
    return flights.size()-1;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    Itinerary other = (Itinerary) obj;
    if (flights == null) {
      if (other.flights != null) {
        return false;
      }
    } else if (!flights.equals(other.flights)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return flights.toString();
  }
}
