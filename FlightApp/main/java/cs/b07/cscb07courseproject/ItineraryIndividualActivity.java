package cs.b07.cscb07courseproject;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import data.Constants;
import data.Data;
import data.FlightCollection;
import flight.Flight;
import flight.FlightDatabase;
import flight.Itinerary;
import user.Client;

/*
 * A class to display each individual itinerary and the flights contained within them.
 */
public class ItineraryIndividualActivity extends AppCompatActivity {
    private ListView lvFlight;
    private FlightAdaptor adapter;
    private List<FlightFormat> mFlightList;
    private Client client;

    /**
     * List of flights is made on creation.
     * @param savedInstanceState Activities have the ability, under special circumstances,.
     * to restore themselves to a previous state using the data stored in this bundle.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itinerary_individual);

        // Get the flights and travel times for the flights in this itineraries that was passed
        // through the ItineraryActivity
        ArrayList<String> flightsinthisItinerary = getIntent().getStringArrayListExtra(ItineraryActivity.FLIGHT_LIST);
        ArrayList<String> travelTimesForFlights = getIntent().getStringArrayListExtra(ItineraryActivity.TRAVEL_TIMES);

        // Find the id of the layout of where the itineraries will be displayed.
        lvFlight = (ListView) findViewById(R.id.display_itinerary_flights);
        mFlightList = new ArrayList<>();

        // For each flight in the list of flights.
        for (int i = 0; i < flightsinthisItinerary.size(); i++) {
            // Split each flight up with the token ";".
            String[] tokens = flightsinthisItinerary.get(i).split(";");

            // Create a new FlightFormat with the 6 tokens and travel time string and add it
            // to the list of flights to display.
            mFlightList.add(new FlightFormat(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4],
                    tokens[5], tokens[6], travelTimesForFlights.get(i)));
        }

        // Take the format of the FlightFormat adapter and then set the empty listview layout to
        // the adapter.
        adapter = new FlightAdaptor(getApplicationContext(), mFlightList);
        lvFlight.setAdapter(adapter);

        // Get the total cost and travel time of the itinerary and set it to their corresponding
        // TextViews
        TextView totalcosttextView = (TextView) findViewById(R.id.totalitinerarycostText);
        TextView totaltimetextView = (TextView) findViewById(R.id.totalitinerarytraveltimeText);
        String totalCost = getIntent().getStringExtra(ItineraryActivity.TOTAL_COST_ITINERARY);
        String totalTime = getIntent().getStringExtra(ItineraryActivity.TOTAL_TRAVEL_TIMES);
        totalcosttextView.setText("Total Cost: " + totalCost);
        totaltimetextView.setText("Total Time: " + totalTime);

        Intent intent = getIntent();
        client = (Client) intent.getSerializableExtra(Constants.MAIN_KEY);

        // If the user is an admin, then remove the button to book flights.
        try {
            System.out.println(client.getFirstName());
        } catch (NullPointerException ex) {
           View book = findViewById(R.id.book_button_text);
           book.setVisibility(View.GONE);
        }
    }

    /**
     * Allow clients to book itineraries.
     * @param view the book itinerary button.
     */
    public void bookItinerary(View view) {
        Intent i = getIntent();

        // Get the itinerary from the ItineraryActivity class
        Itinerary thisItinerary = (Itinerary) i.getSerializableExtra("ITINERARY");
        List<Itinerary> clientBooked = Data.viewBookedItineraries(this, client.getEmail());
        Boolean booked = false;
        for (Itinerary itinerary : clientBooked) {
            if (itinerary.equals(thisItinerary)) {
                booked = true;
            }
        }

        // Check if the client has already booked the itinerary, if they have not, then allow
        // them to book it, if they have, do not allow them to book the itinerary.
        if (booked) {
            Toast.makeText(getBaseContext(), "Itinerary already booked", Toast.LENGTH_SHORT).show();
        } else {
            List<Flight> listFlights = thisItinerary.getFlights();
            for (Flight flight : listFlights) {
                flight.setSeatNum(flight.getSeatNum() - 1);
            }
            client.book(this, thisItinerary);
            Toast.makeText(getBaseContext(), "Itinerary Booked", Toast.LENGTH_SHORT).show();
        }
    }
}
