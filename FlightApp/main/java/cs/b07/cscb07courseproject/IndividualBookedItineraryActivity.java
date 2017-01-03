package cs.b07.cscb07courseproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import user.Client;

/*
 * This class is to display the flights contained in the itinerary booked by the client
 */
public class IndividualBookedItineraryActivity extends AppCompatActivity {
    private ListView lvFlight;
    private FlightAdaptor adapter;
    private List<FlightFormat> mFlightList;

    /**
     * List of flights is made on creation.
     * @param savedInstanceState Activities have the ability, under special circumstances,.
     * to restore themselves to a previous state using the data stored in this bundle.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_booked_itinerary);
        setTitle("Booked Flights");

        // Get the flights and travel times for the flights in this itineraries that was passed
        // through the BookedActivity
        ArrayList<String> flightsinthisItinerary = getIntent().getStringArrayListExtra(BookedActivity.FLIGHT_LIST);
        ArrayList<String> travelTimesForFlights = getIntent().getStringArrayListExtra(BookedActivity.TRAVEL_TIMES);

        // Find the id of the layout of where the itineraries will be displayed.
        lvFlight = (ListView) findViewById(R.id.display_booked_itinerary_flights);
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
    }
}
