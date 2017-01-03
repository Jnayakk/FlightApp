package cs.b07.cscb07courseproject;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.IOException;

import data.Constants;
import flight.Itinerary;
import user.Client;

/**
 * Class searches for all available flights to be associated with clients.
 */
public class SearchActivity extends AppCompatActivity {
    public final static String DEPARTURE_DATE = "depdateKey";
    public final static String DESTINATION_AREA = "destareaKey";
    public final static String TRAVEL_ORIGIN = "traveloriginKey";

    private Client client;

    /**
     * Creates user interface layout.
     * @param savedInstanceState Activities have the ability, under special circumstances,
     * to restore themselves to a previous state using the data stored in this bundle.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        setTitle("Search Flight/Itinerary");
        Intent intent = getIntent();
        client = (Client) intent.getSerializableExtra(Constants.MAIN_KEY);
    }

    /**
     * Shows the available flights.
     * @param view Show Flights button.
     * @throws IOException crashes if intent is incorrectly passed.
     */
    public void showFlights(View view) throws IOException {
        Intent intent = new Intent(this, FlightsActivity.class);
        EditText editDep = (EditText) findViewById(R.id.edit_departure_date);
        EditText editTrav = (EditText) findViewById(R.id.edit_travel_origin);
        EditText editDest = (EditText) findViewById(R.id.edit_destination);

        String departureText = editDep.getText().toString();
        String traveloriginText = editTrav.getText().toString();
        String destinationText = editDest.getText().toString();
        intent.putExtra(DEPARTURE_DATE, departureText);
        intent.putExtra(TRAVEL_ORIGIN, traveloriginText);
        intent.putExtra(DESTINATION_AREA, destinationText);
        startActivity(intent);
    }

    /**
     * Displays all the itineraries for the destination the client is trying to arrive at.
     * @param view Show Itineraries button.
     * @throws IOException crashes if intent is incorrectly passed.
     */
    public void showItineraries(View view) throws IOException {
        Intent intent = new Intent(this, ItineraryActivity.class);
        EditText editDep = (EditText) findViewById(R.id.edit_departure_date);
        EditText editTrav = (EditText) findViewById(R.id.edit_travel_origin);
        EditText editDest = (EditText) findViewById(R.id.edit_destination);

        String departureText = editDep.getText().toString();
        String traveloriginText = editTrav.getText().toString();
        String destinationText = editDest.getText().toString();
        intent.putExtra(DEPARTURE_DATE, departureText);
        intent.putExtra(TRAVEL_ORIGIN, traveloriginText);
        intent.putExtra(DESTINATION_AREA, destinationText);
        intent.putExtra(Constants.MAIN_KEY, client);
        startActivity(intent);
    }
}
