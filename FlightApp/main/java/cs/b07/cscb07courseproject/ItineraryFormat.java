package cs.b07.cscb07courseproject;

import flight.Itinerary;

/**
 * Created by Jay on 2016-11-30.
 */

public class ItineraryFormat {
    public String departureDateTime; // Departure date and time.
    public String arrivalDateTime; // Arrival date and time.
    public String origin; // The origin from where the flight is departing.
    public String destination; // The destination to where the flight will arrive.
    public String travelTime; // The time it takes to travel from an origin to a destination.
    public String totalPrice; // The total price of the itinerary.
    public int numberofStops; // The number of stops of the itinerary.

    /**
     * Creates a new <code>ItineraryFormat</code> with the given departure date and
     * time, arrival date and time, origin, destination, cost, and travel time, and number of
     * stops.
     *
     * @param departureDateTime the departure date and time of this <code>FlightAdaptor</code>,
     *                          in format yyyy-MM-dd HH:mm.
     * @param arrivalDateTime this arrival time of this <code>ItineraryFormat</code>, in format yyyy-MM-dd
     *        HH:mm.
     * @param origin the origin from where this <code>ItineraryFormat</code> leaves.
     * @param destination the destination of this <code>ItineraryFormat</code>.
     * @param totalPrice the total price of this <code>ItineraryFormat</code>.
     * @param travelTime the total travel time of this <code>ItineraryFormat</code>.
     */
    public ItineraryFormat(String departureDateTime, String arrivalDateTime,
                            String origin, String destination, String totalPrice, String travelTime,
                           int numberofStops) {
        this.departureDateTime = departureDateTime;
        this.arrivalDateTime = arrivalDateTime;
        this.origin = origin;
        this.destination = destination;
        this.totalPrice = totalPrice;
        this.travelTime = travelTime;
        this.numberofStops = numberofStops;
    }

    /**
     * Returns the travel time of this <code>ItineraryFormat</code>.
     *
     * @return the travel time of this <code>ItineraryFormat</code> as a string.
     */
    public String getTravelTime() {
        return travelTime;
    }

    /**
     * Sets the destination of this <code>ItineraryFormat</code> to a given destination.
     *
     * @param destination the destination to set of this <code>ItineraryFormat</code>.
     */
    public void setDestination(String destination) {

        this.destination = destination;
    }

    /**
     * Gets the destination of this <code>ItineraryFormat</code>.
     *
     * @Return the destination of this <code>ItineraryFormat</code>
     */
    public String getDestination() {

        return destination;
    }

    /**
     * Gets the origin of this <code>ItineraryFormat</code>.
     *
     * @Return the origin of this <code>ItineraryFormat</code>
     */
    public String getOrigin() {

        return origin;
    }

    /**
     * Sets the origin of this <code>ItineraryFormat</code> to a given origin.
     *
     * @param origin the origin of this <code>ItineraryFormat</code>
     */
    public void setOrigin(String origin) {
        this.origin = origin;
    }

    /**
     * Returns the number of stops of this <code>ItineraryFormat</code>.
     *
     * @Return the numberofStops of this <code>ItineraryFormat</code>
     */
    public int getNumberofStops() {

        return numberofStops;
    }

    /**
     * Returns the total price of this <code>ItineraryFormat</code>.
     *
     * @return the totalprice of this <code>ItineraryFormat</code> as a string.
     */
    public String getTotalPrice() {

        return totalPrice;
    }

    /**
     * Sets the price of this <code>ItineraryFormat</code> to a given cost.
     *
     * @param totalPrice the price to set of this <code>ItineraryFormat</code>.
     */
    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getArrivalDateTime() {

        return arrivalDateTime;
    }

    /**
     * Returns the departure date and time of this <code>ItineraryFormat</code>.
     *
     * @Return the departureDateTime of this <code>ItineraryFormat</code>
     */
    public String getDepartureDateTime() {

        return departureDateTime;
    }

}
