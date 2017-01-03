package cs.b07.cscb07courseproject;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import data.FlightCollection;
import flight.Flight;

/*
 * A class to display all of the searched flights in a proper ordered format.
 */
public class FlightsActivity extends AppCompatActivity {
    private ListView lvFlight; // The listview of the flights;
    private FlightAdaptor adapter; // The adapter where it will take the format of each flight of.
    private List<FlightFormat> mFlightList; // The list of FlightFormats.

    /**
     * List of flights is made on creation.
     * @param savedInstanceState Activities have the ability, under special circumstances,.
     * to restore themselves to a previous state using the data stored in this bundle.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flights);
        setTitle("Flights");

        // Get the departure, travelorigin and destination inputs from the user from that
        // previous activities and declare them to be Strings.
        String departureInput = getIntent().getStringExtra(SearchActivity.DEPARTURE_DATE);
        String traveloriginInput = getIntent().getStringExtra(SearchActivity.TRAVEL_ORIGIN);
        String destinationInput = getIntent().getStringExtra(SearchActivity.DESTINATION_AREA);

        // Search the flights in the system given the departure, travel origin and destination
        // and set them to be the variable listofFlights.
        List<Flight> listofFlights = FlightCollection.searchFlight(this, departureInput,
                traveloriginInput, destinationInput);

        // Find the id of the layout of where the flights will be displayed.
        lvFlight = (ListView) findViewById(R.id.display_flights);
        mFlightList = new ArrayList<>();

        // For each flight in the list of flights.
        for (int i = 0; i < listofFlights.size(); i++){

            // Get the travel time and set it to be in the format of: #h #m
            Double travelTimeDouble = listofFlights.get(i).getTravelTime();
            int travelT = travelTimeDouble.intValue();
            int hours = travelT/60;
            int mins = travelT%60;
            String travelTimeString = hours + "h " + mins + "m";
            if (hours == 0){
                travelTimeString = mins + "m";
            }

            // Split each flight up with the token ";".
            String flight = listofFlights.get(i).toString();
            String[] tokens = flight.split(";");

            // Create a new FlightFormat with the 6 tokens and travel time string and add it
            // to the list of flights to display.
            mFlightList.add(new FlightFormat(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4],
                    tokens[5], tokens[6], travelTimeString));
        }

        // Take the format of the FlightFormat adapter and then set the empty listview layout to
        // the adapter.
        adapter = new FlightAdaptor(getApplicationContext(), mFlightList);
        lvFlight.setAdapter(adapter);
    }

    /**
     * Sort all of the flights in the listview by cost.
     * @param view the sort by cost button.
     */
    public void flightssortByCost(View view) {

        // Get the departure, travelorigin and destination inputs from the user from that
        // previous activities and declare them to be Strings.
        String departureInput = getIntent().getStringExtra(SearchActivity.DEPARTURE_DATE);
        String traveloriginInput = getIntent().getStringExtra(SearchActivity.TRAVEL_ORIGIN);
        String destinationInput = getIntent().getStringExtra(SearchActivity.DESTINATION_AREA);

        // Search the flights in the system given the departure, travel origin and destination
        // and set them to be the variable listofFlights.
        List<Flight> originalFlights = FlightCollection.searchFlight(this, departureInput,
                traveloriginInput, destinationInput);

        // Sort the list of flights by cost using the backend method in the FlightCollection class
        List<Flight> listofFlights = FlightCollection.flightSortByCost(originalFlights);

        // Find the id of the layout of where the flights will be displayed.
        lvFlight = (ListView) findViewById(R.id.display_flights);
        mFlightList = new ArrayList<>();

        // For each flight in the list of flights.
        for (int i = 0; i < listofFlights.size(); i++){

            // Get the travel time and set it to be in the format of: #h #m
            Double travelTimeDouble = listofFlights.get(i).getTravelTime();
            int travelT = travelTimeDouble.intValue();
            int hours = travelT/60;
            int mins = travelT%60;
            String travelTimeString = hours + "h " + mins + "m";
            if (hours == 0){
                travelTimeString = mins + "m";
            }

            // Split each flight up with the token ";".
            String flight = listofFlights.get(i).toString();
            String[] tokens = flight.split(";");

            // Create a new FlightFormat with the 6 tokens and travel time string and add it
            // to the list of flights to display.
            mFlightList.add(new FlightFormat(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4],
                    tokens[5], tokens[6], travelTimeString));
        }

        // Take the format of the FlightFormat adapter and then set the empty listview layout to
        // the adapter.
        adapter = new FlightAdaptor(getApplicationContext(), mFlightList);
        lvFlight.setAdapter(adapter);
    }

    /**
     * Sort all of the flights in the listview by time.
     * @param view the sort by time button.
     */
    public void flightssortByTime(View view) {

        // Get the departure, travelorigin and destination inputs from the user from that
        // previous activities and declare them to be Strings.
        String departureInput = getIntent().getStringExtra(SearchActivity.DEPARTURE_DATE);
        String traveloriginInput = getIntent().getStringExtra(SearchActivity.TRAVEL_ORIGIN);
        String destinationInput = getIntent().getStringExtra(SearchActivity.DESTINATION_AREA);

        // Search the flights in the system given the departure, travel origin and destination
        // and set them to be the variable listofFlights.
        List<Flight> originalFlights = FlightCollection.searchFlight(this, departureInput,
                traveloriginInput, destinationInput);

        // Sort the list of flights by time using the backend method in the FlightCollection class.
        List<Flight> listofFlights = FlightCollection.flightSortByTime(originalFlights);

        // Find the id of the layout of where the flights will be displayed.
        lvFlight = (ListView) findViewById(R.id.display_flights);
        mFlightList = new ArrayList<>();

        // For each flight in the list of flights.
        for (int i = 0; i < listofFlights.size(); i++){

            // Get the travel time and set it to be in the format of: #h #m
            Double travelTimeDouble = listofFlights.get(i).getTravelTime();
            int travelT = travelTimeDouble.intValue();
            int hours = travelT/60;
            int mins = travelT%60;
            String travelTimeString = hours + "h " + mins + "m";
            if (hours == 0){
                travelTimeString = mins + "m";
            }

            // Split each flight up with the token ";".
            String flight = listofFlights.get(i).toString();
            String[] tokens = flight.split(";");

            // Create a new FlightFormat with the 6 tokens and travel time string and add it
            // to the list of flights to display.
            mFlightList.add(new FlightFormat(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4],
                    tokens[5], tokens[6], travelTimeString));
        }

        // Take the format of the FlightFormat adapter and then set the empty listview layout to
        // the adapter.
        adapter = new FlightAdaptor(getApplicationContext(), mFlightList);
        lvFlight.setAdapter(adapter);
    }
}


