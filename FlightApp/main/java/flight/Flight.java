package flight;

import android.content.Context;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;

import data.Constants;
import data.Data;

/**
 * Representation of a flight.
 * 
 * @author team_0575
 */
public class Flight  implements Serializable {

  private String flightNum; // Number of the flight.
  private Date departureDateTime; // Departure date and time.
  private Date arrivalDateTime; // Arrival date and time.
  private String airline; // The type of airline for this flight.
  private String origin; // The origin from where the flight is departing.
  private String destination; // The destination to where the flight will arrive.
  private double cost; // The cost of the flight.
  private double travelTime; // The time it takes to travel from an origin to a destination.
  private int seatNum; // The seat number of the flight.

  /**
   * Creates a new <code>Flight</code> with the given flight number, departure date and time,
   * arrival date and time, type of airline, origin, destination, cost, and travel time.
   * 
   * @param flightNum the number of this <code>Flight</code>.
   * @param departureDateTime the departure date and time of this <code>Flight</code>, in format
   *        yyyy-MM-dd HH:mm.
   * @param arrivalDateTime this arrival time of this <code>Flight</code>, in format yyyy-MM-dd
   *        HH:mm.
   * @param airline the type of airline for this <code>Flight</code>.
   * @param origin the origin from where this <code>Flight</code> leaves.
   * @param destination the destination of this <code>Flight</code>.
   * @param cost the cost of this <code>Flight</code>.
   * @param seatNum the seat number of this <code>Flight</code>.
   */
  public Flight(String flightNum, String departureDateTime, String arrivalDateTime, String airline,
      String origin, String destination, double cost, int seatNum) {
    this.flightNum = flightNum;
    this.airline = airline;
    this.origin = origin;
    this.destination = destination;
    this.cost = cost;
    this.seatNum = seatNum;

    // Make sure the date and time is in the correct format of yyyy-MM-dd
    // HH:mm.
    try {
      this.departureDateTime = Constants.DATE_TIME_FORMAT.parse(departureDateTime);
    } catch (ParseException ex) {
      ex.printStackTrace();
    }
    try {
      this.arrivalDateTime = Constants.DATE_TIME_FORMAT.parse(arrivalDateTime);
    } catch (ParseException ex) {
      ex.printStackTrace();
    }
    // long hours = TimeUnit.MILLISECONDS.toHours((this.arrivalDateTime.getTime() -
    // this.departureDateTime.getTime()));
    // long mins = (this.arrivalDateTime.getTime() - this.departureDateTime.getTime())/((1000*60));

    this.travelTime =
        (this.arrivalDateTime.getTime() - this.departureDateTime.getTime()) / (60000);


  }

  /**
   * Returns the flight number of this <code>Flight</code>.
   * 
   * @return the flight number of this <code>Flight</code> as a string.
   */
  public String getFlightNum() {
    return flightNum;
  }

  /**
   * Sets the flight number of this <code>Flight</code> to a given flight number.
   * 
   * @param flightNum the flight number to set this <code>Flight</code> to.
   */
  public void setFlightNum(String flightNum) {
    this.flightNum = flightNum;
  }

  public void editFlightNum(Context context,String flightNum) {
    setFlightNum(flightNum);
    Data.editFlight(context, this.flightNum, this.departureDateTime.toString(),
            this.arrivalDateTime.toString(), this.airline, this.origin, this.destination,
            Double.toString(this.cost), Integer.toString(this.seatNum));
  }

  /**
   * Returns the date and time this <code>Flight</code> will depart at.
   * 
   * @return the departure date and time of this <code>Flight</code>, in format yyyy-MM-dd HH:mm.
   */
  public String getDepartureDateTime() {
    return Constants.DATE_TIME_FORMAT.format(departureDateTime);
  }

  /**
   * Returns the date this <code>Flight</code> will depart at.
   * 
   * @return the departure date of this <code>Flight</code>, in format yyyy-MM-dd.
   */
  public String getDepartureDate() {
    return getDepartureDateTime().split(" ")[0];
  }

  /**
   * Sets departure date and time of this <code>Flight</code> to a given date and time.
   * 
   * @param departureDateTime the departure date and time of this <code>Flight</code>, in format
   *        yyyy-MM-dd HH:mm.
   */
  public void setDepartureDateTime(Date departureDateTime) {
    this.departureDateTime = departureDateTime;
  }


  /**
   * Sets departure date and time of this <code>Flight</code> to a given date and time.
   *
   * @param departureDateTime the departure date and time of this <code>Flight</code>, in format
   *        yyyy-MM-dd HH:mm.
   * @param  context The activity it is in.
   */
  public void editDepartureDateTime(Context context, Date departureDateTime) {
    setDepartureDateTime(departureDateTime);
    Data.editFlight(context, this.flightNum, this.getDepartureDateTime(),
            this.getArrivalDateTime(), this.airline, this.origin, this.destination,
            Double.toString(this.cost), Integer.toString(this.seatNum));
  }
  /**
   * Returns the date and time this <code>Flight</code> will arrive at.
   * 
   * @return the arrival date and time of this <code>Flight</code>, in format yyyy-MM-dd HH:mm.
   */
  public String getArrivalDateTime() {
    return Constants.DATE_TIME_FORMAT.format(arrivalDateTime);
  }

  /**
   * Sets arrival date and time of this <code>Flight</code> to a given date and time.
   * 
   * @param arrivalDateTime the arrival date and time of this <code>Flight</code>, in format
   *        yyyy-MM-dd HH:mm.
   */
  public void setArrivalDateTime(Date arrivalDateTime) {
    this.arrivalDateTime = arrivalDateTime;
  }


  /**
   * Sets arrival date and time of this <code>Flight</code> to a given date and time.
   *
   * @param arrivalDateTime the arrival date and time of this <code>Flight</code>, in format
   *        yyyy-MM-dd HH:mm.
   * @param  context The activity it is in.
   */
  public void editArrivalDateTime(Context context, Date arrivalDateTime) {
    setArrivalDateTime(arrivalDateTime);
    Data.editFlight(context, this.flightNum, this.departureDateTime.toString(),
            this.arrivalDateTime.toString(), this.airline, this.origin, this.destination,
            Double.toString(this.cost), Integer.toString(this.seatNum));
  }

  /**
   * Returns the date this <code>Flight</code> will arrive at.
   * 
   * @return the arrival date of this <code>Flight</code>, in format yyyy-MM-dd.
   */
  public String getArrivalDate() {
    return getArrivalDateTime().split(" ")[0];
  }

  /**
   * Returns the type of airline of this <code>Flight</code>.
   * 
   * @return the type of airline of this <code>Flight</code> as a string.
   */
  public String getAirline() {
    return airline;
  }

  /**
   * Sets the type of airline of this <code>Flight</code> to a given airline.
   * 
   * @param airline the airline this <code>Flight</code> will be.
   */
  public void setAirline(String airline) {
    this.airline = airline;
  }


  /**
   * Sets the type of airline of this <code>Flight</code> to a given airline.
   *
   * @param airline the airline this <code>Flight</code> will be.
   * @param  context The activity it is in.
   */
  public void editAirline(Context context,String airline) {
    setAirline(airline);
    Data.editFlight(context, this.flightNum, this.departureDateTime.toString(),
            this.arrivalDateTime.toString(), this.airline, this.origin, this.destination,
            Double.toString(this.cost), Integer.toString(this.seatNum));
  }

  /**
   * Returns the area of origin of this <code>Flight</code>.
   * 
   * @return the origin from where this <code>Flight</code> is departing.
   */
  public String getOrigin() {
    return origin;
  }

  /**
   * Sets the area of origin of this <code>Flight</code> to a origin.
   * 
   * @param origin the origin to from where this <code>Flight</code> will depart.
   */
  public void setOrigin(String origin) {
    this.origin = origin;
  }


  /**
   * Sets the area of origin of this <code>Flight</code> to a origin.
   *
   * @param origin the origin to from where this <code>Flight</code> will depart.
   * @param  context The activity it is in.
   */
  public void editOrigin(Context context,String origin) {
    setOrigin( origin);
    Data.editFlight(context, this.flightNum, this.departureDateTime.toString(),
            this.arrivalDateTime.toString(), this.airline, this.origin, this.destination,
            Double.toString(this.cost), Integer.toString(this.seatNum));
  }

  /**
   * Returns the destination of this <code>Flight</code>.
   * 
   * @return the destination of this <code>Flight</code>.
   */
  public String getDestination() {
    return destination;
  }

  /**
   * Sets the destination of this <code>Flight</code> to a destination.
   * 
   * @param destination the destination of this <code>Flight</code>.
   */
  public void setDestination(String destination) {
    this.destination = destination;
  }

  /**
   * Sets the destination of this <code>Flight</code> to a destination.
   *
   * @param destination the destination of this <code>Flight</code>.
   * @param  context The activity it is in.
   */
  public void editDestination(Context context,String destination) {
    setDestination(destination);
    Data.editFlight(context, this.flightNum, this.departureDateTime.toString(),
            this.arrivalDateTime.toString(), this.airline, this.origin, this.destination,
            Double.toString(this.cost), Integer.toString(this.seatNum));
  }

  /**
   * Returns the cost of this <code>Flight</code>.
   * 
   * @return the cost of this <code>Flight</code> which will be a double.
   */
  public double getCost() {
    return cost;
  }

  /**
   * Sets the price of this <code>Flight</code> to a certain amount.
   * 
   * @param cost the cost to set this <code>Flight</code> to.
   */
  public void setCost(double cost) {
    this.cost = cost;
  }

  /**
   * Sets the price of this <code>Flight</code> to a certain amount.
   *
   * @param  context The activity it is in. * @param context
   * @param  context The activity it is in.
     */
  public void editCost(Context context,double cost) {
    setCost(cost);
    Data.editFlight(context, this.flightNum, this.departureDateTime.toString(),
            this.arrivalDateTime.toString(), this.airline, this.origin, this.destination,
            Double.toString(this.cost), Integer.toString(this.seatNum));
  }

  /**
   * Returns the seat number of this <code>Flight</code>.
   * 
   * @return the seat number of this <code>Flight</code> .
   */
  public int getSeatNum() {
    return seatNum;
  }

  /**
   * Set the number of seats in the flight.
   *
   * @param seatNum The Number of seats in the flight.
     */
  public void setSeatNum(int seatNum) {
    this.seatNum = seatNum;
  }

  /**
   * Set the number of seats in the flight.
   *
   * @param seatNum The Number of seats in the flight.
   * @param  context The activity it is in.
   */
  public void editSeatNum(Context context, int seatNum) {
    setSeatNum(seatNum);
    Data.editFlight(context, this.flightNum, this.departureDateTime.toString(),
            this.arrivalDateTime.toString(), this.airline, this.origin, this.destination,
            Double.toString(this.cost), Integer.toString(this.seatNum));
  }

  /**
   * Returns the travel time of this <code>Flight</code>.
   *
   * @return the travel time of this <code>Flight</code> as a string.
   */
  public double getTravelTime() {
    return travelTime;
  }

  @Override
  public String toString() {
    return (flightNum + ";" + getDepartureDateTime() + ";" + getArrivalDateTime() + ";" + airline
        + ";" + origin + ";" + destination + ";" + String.format("%.2f", cost));
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((airline == null) ? 0 : airline.hashCode());
    result = prime * result + ((arrivalDateTime == null) ? 0 : arrivalDateTime.hashCode());
    long temp;
    temp = Double.doubleToLongBits(cost);
    result = prime * result + (int) (temp ^ (temp >>> 32));
    result = prime * result + ((departureDateTime == null) ? 0 : departureDateTime.hashCode());
    result = prime * result + ((destination == null) ? 0 : destination.hashCode());
    result = prime * result + ((flightNum == null) ? 0 : flightNum.hashCode());
    result = prime * result + ((origin == null) ? 0 : origin.hashCode());
    temp = Double.doubleToLongBits(travelTime);
    result = prime * result + (int) (temp ^ (temp >>> 32));
    result = prime * result + ((Constants.DATE_TIME_FORMAT == null) ? 0 :
            Constants.DATE_TIME_FORMAT.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    return this.toString().equals(obj.toString());
  }
}
