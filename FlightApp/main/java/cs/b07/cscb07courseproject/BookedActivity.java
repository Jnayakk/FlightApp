package cs.b07.cscb07courseproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import data.Constants;
import data.Data;
import data.FlightCollection;
import flight.Flight;
import flight.FlightDatabase;
import flight.Itinerary;
import user.Client;

public class BookedActivity extends AppCompatActivity {
    private Client client;
    public final static String FLIGHT_LIST = "flightListKey";
    public final static String TRAVEL_TIMES = "traveltimeKey";
    public final static String TOTAL_TRAVEL_TIMES = "totaltraveltimeKey";
    public final static String TOTAL_COST_ITINERARY = "totalcostKey";
    public final static String FLIGHT_OBJECT = "flightobjectKey";
    private ListView lvItinerary;
    private ItineraryAdapter adapter;
    private List<ItineraryFormat> mItineraryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booked);
        Intent intent = getIntent();
        client = (Client) intent.getSerializableExtra(Constants.MAIN_KEY);

        setTitle("Booked Itineraries");

        // Gets all of the itineraries that are booked by the client.
        List<Itinerary> listofBookedItineraries = Data.viewBookedItineraries(this, client.getEmail());

        // Find the id of the layout of where the itineraries will be displayed.
        lvItinerary = (ListView) findViewById(R.id.display_booked_itineraries);
        mItineraryList = new ArrayList<>();

        // For each itinerary in the list of booked itineraries.
        for (int i = 0; i < listofBookedItineraries.size(); i++) {
            // Get departure and arrival date and time, origin, destination, price, and
            // travel time.
            String depDate = listofBookedItineraries.get(i).getDepartureDateTime();
            String arrDate = listofBookedItineraries.get(i).getArrivalDateTime();
            List<Flight> listofFlights = listofBookedItineraries.get(i).getFlights();
            String origin = listofFlights.get(0).getOrigin();
            String destination = listofFlights.get(listofFlights.size() - 1).getDestination();
            Double priceDouble = listofBookedItineraries.get(i).getTotalCost();
            String priceString = priceDouble.toString();
            int numStops = listofBookedItineraries.get(i).getNumberOfStops();
            Double travelTime = listofBookedItineraries.get(i).getTotalTravelTime();
            int travelT = travelTime.intValue();
            int hours = travelT / 60;
            int mins = travelT % 60;
            String travelTimeString = hours + "h " + mins + "m";
            if (hours == 0) {
                travelTimeString = mins + "m";
            }

            // Create a new ItineraryFormat with the departure and arrival dates, travel origin
            // destination, price, traveltime and number of stops.
            mItineraryList.add(new ItineraryFormat(depDate, arrDate, origin,
                    destination, priceString, travelTimeString, numStops));
        }

        // Take the format of the ItineraryFormat adapter and then set the empty listview layout to
        // the adapter.
        adapter = new ItineraryAdapter(getApplicationContext(), mItineraryList);
        lvItinerary.setAdapter(adapter);

        // Set and onItemClickListener for the list so that each itinerary can be clicked.
        lvItinerary.setOnItemClickListener(onListClick);
    }

    // This method takes users to an IndividualBookedItineraryActivity to show them the
    // list of flights in the clicked itinerary.
    private AdapterView.OnItemClickListener onListClick = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(view.getContext(), IndividualBookedItineraryActivity.class);

            // Get the list of booked itineraries
            List<Itinerary> listofBookedItineraries = Data.viewBookedItineraries(view.getContext(), client.getEmail());
            List<Flight> flightsinthisItinerary = listofBookedItineraries.get(position).getFlights();

            // Create a new list of Flights and traveltimes in strings.
            ArrayList<String> stringFlights = new ArrayList<>();
            ArrayList<String> stringTravelTimes = new ArrayList<>();

            // For each itinerary in the list of itineraries.
            for (int i = 0; i < flightsinthisItinerary.size(); i++) {

                // Add each flight from this itinerary to a list of flights.
                stringFlights.add(flightsinthisItinerary.get(i).toString());

                // Get the total travel time and convert it to a string.
                Double travelTimeDouble = flightsinthisItinerary.get(i).getTravelTime();
                int travelT = travelTimeDouble.intValue();
                int hours = travelT / 60;
                int mins = travelT % 60;
                String travelTimeString = hours + "h " + mins + "m";
                if (hours == 0) {
                    travelTimeString = mins + "m";
                }
                stringTravelTimes.add(travelTimeString);
            }
            // Get the total cost and travel time of the itinerary and convert them into
            // strings.
            Double totalcostofItinerary = listofBookedItineraries.get(position).getTotalCost();
            Double totaltraveltimeofItinerary = listofBookedItineraries.get(position).getTotalTravelTime();
            int travelT = totaltraveltimeofItinerary.intValue();
            int hours = travelT / 60;
            int mins = travelT % 60;
            String travelTimeString = hours + "h " + mins + "m";
            if (hours == 0) {
                travelTimeString = mins + "m";
            }
            // Pass the itinerary being clicked, total cost and total travel time of the itinerary
            // to the ItineraryIndividualActivity
            intent.putExtra("ITINERARY", (Serializable) listofBookedItineraries.get(position));
            intent.putExtra(TOTAL_COST_ITINERARY, totalcostofItinerary.toString());
            intent.putExtra(TOTAL_TRAVEL_TIMES, travelTimeString);

            // Pass the list of flights and list of travel times for each flights to the
            // ItineraryIndividualActivity
            intent.putStringArrayListExtra(TRAVEL_TIMES, stringTravelTimes);
            intent.putStringArrayListExtra(FLIGHT_LIST, stringFlights);
            startActivity(intent);
        }
    };
}
