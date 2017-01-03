package cs.b07.cscb07courseproject;



/**
 * This class creates a flightformat to help the adaptor show the flights in a certain format
 */

public class FlightFormat {
    public String flightNum; // Number of the flight.
    public String departureDateTime; // Departure date and time.
    public String arrivalDateTime; // Arrival date and time.
    public String airline; // The type of airline for this flight.
    public String origin; // The origin from where the flight is departing.
    public String destination; // The destination to where the flight will arrive.
    public String cost; // The cost of the flight.
    public String travelTime; // The time it takes to travel from an origin to a destination.

    /**
     * Creates a new <code>FlightFormat</code> with the given flight number, departure date and
     * time, arrival date and time, type of airline, origin, destination, cost, and travel time.
     *
     * @param flightNum the Flight number of this <code>FlightFormat</code>.
     * @param departureDateTime the departure date and time of this <code>FlightAdaptor</code>,
     *                          in format yyyy-MM-dd HH:mm.
     * @param arrivalDateTime this arrival time of this <code>FlightFormat</code>, in format yyyy-MM-dd
     *        HH:mm.
     * @param airline the type of airline for this <code>FlightFormat</code>.
     * @param origin the origin from where this <code>FlightFormat</code> leaves.
     * @param destination the destination of this <code>FlightFormat</code>.
     * @param cost the cost of this <code>FlightFormat</code>.
     */
    public FlightFormat(String flightNum, String departureDateTime, String arrivalDateTime, String airline,
                   String origin, String destination, String cost, String travelTime) {
        this.flightNum = flightNum;
        this.departureDateTime = departureDateTime;
        this.arrivalDateTime = arrivalDateTime;
        this.airline = airline;
        this.origin = origin;
        this.destination = destination;
        this.cost = cost;
        this.travelTime = travelTime;
    }

    /**
     * Returns the travel time of this <code>FlightFormat</code>.
     *
     * @return the travel time of this <code>FlightFormat</code> as a string.
     */
    public String getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(String travelTime) {
        this.travelTime = travelTime;
    }

    /**
     * Returns the cost of this <code>FlightFormat</code>.
     *
     * @return the cost of this <code>FlightFormat</code> as a string.
     */
    public String getCost() {

        return cost;
    }

    /**
     * Sets the cost of this <code>FlightFormat</code> to a given cost.
     *
     * @param cost the cost to set the cost of this <code>FlightFormat</code> to.
     */
    public void setCost(String cost) {
        this.cost = cost;
    }


    /**
     * Sets the destination of this <code>FlightFormat</code> to a given destination.
     *
     * @param destination the destination to set of this <code>FlightFormat</code>.
     */
    public void setDestination(String destination) {

        this.destination = destination;
    }


    /**
     * Gets the destination of this <code>FlightFormat</code>.
     *
     * @Return the destination of this <code>FlightFormat</code>
     */
    public String getDestination() {

        return destination;
    }

    /**
     * Gets the origin of this <code>FlightFormat</code>.
     *
     * @Return the origin of this <code>FlightFormat</code>
     */
    public String getOrigin() {

        return origin;
    }

    /**
     * Sets the origin of this <code>FlightFormat</code> to a given origin.
     *
     * @Return the origin of this <code>FlightFormat</code>
     */
    public void setOrigin(String origin) {
        this.origin = origin;
    }

    /**
     * Returns the airline type of this <code>FlightFormat</code>.
     *
     * @Return the airline of this <code>FlightFormat</code>
     */
    public String getAirline() {

        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    /**
     * Returns the arrival date and time of this <code>FlightFormat</code>.
     *
     * @Return the arrivalDateTime of this <code>FlightFormat</code>
     */
    public String getArrivalDateTime() {

        return arrivalDateTime;
    }

    /**
     * Returns the departure date and time of this <code>FlightFormat</code>.
     *
     * @Return the departureDateTime of this <code>FlightFormat</code>
     */
    public String getDepartureDateTime() {

        return departureDateTime;
    }

    /**
     * Returns the flight number of this <code>FlightFormat</code>.
     *
     * @return the flight number of this <code>FlightFormat</code> as a string.
     */
    public String getFlightNum() {

        return flightNum;
    }

}
