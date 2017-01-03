package cs.b07.cscb07courseproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import data.Constants;
import data.FlightCollection;
import flight.Flight;
import flight.FlightDatabase;
import flight.Itinerary;
import user.Client;

public class ItineraryActivity extends AppCompatActivity {
    // Objects to pass to next activity
    public final static String FLIGHT_LIST = "flightListKey";
    public final static String TRAVEL_TIMES = "traveltimeKey";
    public final static String TOTAL_TRAVEL_TIMES = "totaltraveltimeKey";
    public final static String TOTAL_COST_ITINERARY = "totalcostKey";

    private ListView lvItinerary;
    private ItineraryAdapter adapter;
    private List<ItineraryFormat> mItineraryList;
    private Client client;

    /**
     * List of Itineraries is made on creation.
     * @param savedInstanceState Activities have the ability, under special circumstances,.
     * to restore themselves to a previous state using the data stored in this bundle.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itinerary);
        setTitle("Itineraries");

        // Get the departure, travelorigin and destination inputs from the user from that
        // previous activities and declare them to be Strings.
        String departureInput = getIntent().getStringExtra(SearchActivity.DEPARTURE_DATE);
        String traveloriginInput = getIntent().getStringExtra(SearchActivity.TRAVEL_ORIGIN);
        String destinationInput = getIntent().getStringExtra(SearchActivity.DESTINATION_AREA);

        // Create a FlightDatabase and then use that database to search for all itineraries
        // in the system with the given departure, travel orgin and destination.
        FlightDatabase graph = new FlightDatabase(this);
        List<Itinerary> listofItineraries = FlightCollection.searchItinerary(this, graph,
                departureInput, traveloriginInput, destinationInput);

        // Find the id of the layout of where the itineraries will be displayed.
        lvItinerary = (ListView) findViewById(R.id.display_itineraries);
        mItineraryList = new ArrayList<>();

        // For each itinerary in the list of itineraries.
        for (int i = 0; i < listofItineraries.size(); i++){

            // Get the departure date and arrival dates and convert them to strings.
            String depDate = listofItineraries.get(i).getDepartureDateTime();
            String arrDate = listofItineraries.get(i).getArrivalDateTime();

            // Get the price, number of stops and total travel time and convert them to strings.
            Double priceDouble = listofItineraries.get(i).getTotalCost();
            String priceString = priceDouble.toString();
            int numStops = listofItineraries.get(i).getNumberOfStops();
            Double travelTime = listofItineraries.get(i).getTotalTravelTime();
            int travelT = travelTime.intValue();
            int hours = travelT/60;
            int mins = travelT%60;
            String travelTimeString = hours + "h " + mins + "m";
            if (hours == 0){
                travelTimeString = mins + "m";
            }

            // Create a new ItineraryFormat with the departure and arrival dates, travel origin
            // destination, price, traveltime and number of stops.
            mItineraryList.add(new ItineraryFormat(depDate, arrDate, traveloriginInput,
                    destinationInput, priceString, travelTimeString, numStops));
        }

        // Take the format of the ItineraryFormat adapter and then set the empty listview layout to
        // the adapter.
        adapter = new ItineraryAdapter(getApplicationContext(), mItineraryList);
        lvItinerary.setAdapter(adapter);

        // Set and onItemClickListener for the list so that each itinerary can be clicked.
        lvItinerary.setOnItemClickListener(onListClick);
    }

    // This method takes users to an ItineraryIndividualActivity to show them the list of flights
    // in the clicked itinerary.
    private AdapterView.OnItemClickListener onListClick = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> parent, View view, int position, long id){
            Intent intent = new Intent(view.getContext(), ItineraryIndividualActivity.class);

            // Get the departure, travelorigin and destination inputs from the user from that
            // previous activities and declare them to be Strings.
            String departureInput = getIntent().getStringExtra(SearchActivity.DEPARTURE_DATE);
            String traveloriginInput = getIntent().getStringExtra(SearchActivity.TRAVEL_ORIGIN);
            String destinationInput = getIntent().getStringExtra(SearchActivity.DESTINATION_AREA);

            // pass the client info to the ItineraryIndividualActivity
            Intent ic = getIntent();
            client = (Client) ic.getSerializableExtra(Constants.MAIN_KEY);

            // Create a FlightDatabase and then use that database to search for all itineraries
            // in the system with the given departure, travel orgin and destination.
            FlightDatabase graph = new FlightDatabase(view.getContext());
            List<Itinerary> listofItineraries = FlightCollection.searchItinerary(view.getContext(),
                    graph, departureInput, traveloriginInput, destinationInput);

            // Get the current itinerary that is being clicked
            List<Flight> flightsinthisItinerary = listofItineraries.get(position).getFlights();

            // Create a new list of Flights and traveltimes in strings.
            ArrayList<String> stringFlights = new ArrayList<>();
            ArrayList<String> stringTravelTimes = new ArrayList<>();

            // For each itinerary in the list of itineraries.
            for (int i = 0; i < flightsinthisItinerary.size(); i++){

                // Add each flight from this itinerary to a list of flights.
                stringFlights.add(flightsinthisItinerary.get(i).toString());
                // Get the total travel time and convert it to a string.
                Double travelTimeDouble = flightsinthisItinerary.get(i).getTravelTime();
                int travelT = travelTimeDouble.intValue();
                int hours = travelT/60;
                int mins = travelT%60;
                String travelTimeString = hours + "h " + mins + "m";
                if (hours == 0){
                    travelTimeString = mins + "m";
                }
                stringTravelTimes.add(travelTimeString);
            }

            // Get the total cost and travel time of the itinerary and convert them into
            // strings.
            Double totalcostofItinerary = listofItineraries.get(position).getTotalCost();
            Double totaltraveltimeofItinerary = listofItineraries.get(position).getTotalTravelTime();
            int travelT = totaltraveltimeofItinerary.intValue();
            int hours = travelT/60;
            int mins = travelT%60;
            String travelTimeString = hours + "h " + mins + "m";
            if (hours == 0){
                travelTimeString = mins + "m";
            }

            // Pass the itinerary being clicked, total cost and total travel time of the itinerary
            // to the ItineraryIndividualActivity
            intent.putExtra("ITINERARY", listofItineraries.get(position));
            intent.putExtra(TOTAL_COST_ITINERARY, totalcostofItinerary.toString());
            intent.putExtra(TOTAL_TRAVEL_TIMES, travelTimeString);

            // Pass the list of flights and list of travel times for each flights to the
            // ItineraryIndividualActivity
            intent.putStringArrayListExtra(TRAVEL_TIMES, stringTravelTimes);
            intent.putStringArrayListExtra(FLIGHT_LIST, stringFlights);
            intent.putExtra(Constants.MAIN_KEY, client);
            startActivity(intent);
        }

    };

    /**
     * Sort all of the itineraries in the listview by time.
     * @param view the sort by time button.
     */
    public void itinerarysortbyTime(View view) {

        // Get the departure, travelorigin and destination inputs from the user from that
        // previous activities and declare them to be Strings.
        String departureInput = getIntent().getStringExtra(SearchActivity.DEPARTURE_DATE);
        String traveloriginInput = getIntent().getStringExtra(SearchActivity.TRAVEL_ORIGIN);
        String destinationInput = getIntent().getStringExtra(SearchActivity.DESTINATION_AREA);

        // Create a FlightDatabase and then use that database to search for all itineraries
        // in the system with the given departure, travel orgin and destination.
        FlightDatabase graph = new FlightDatabase(this);
        List<Itinerary> listofItinerariesoriginal = FlightCollection.searchItinerary(this, graph,
                departureInput, traveloriginInput, destinationInput);

        // Sort all of the itineraries by time using the itinerarySortByTime method in the
        // FlightCollection class
        List<Itinerary> listofItineraries = FlightCollection.itinerarySortByTime(listofItinerariesoriginal);

        // Find the id of the layout of where the itineraries will be displayed.
        lvItinerary = (ListView) findViewById(R.id.display_itineraries);
        mItineraryList = new ArrayList<>();

        // For each itinerary in the list of itineraries.
        for (int i = 0; i < listofItineraries.size(); i++){

            // Get the departure and arrival date of the itinerary
            String depDate = listofItineraries.get(i).getDepartureDateTime();
            String arrDate = listofItineraries.get(i).getArrivalDateTime();

            // Get the price, number of stops and total travel time and convert them to strings.
            Double priceDouble = listofItineraries.get(i).getTotalCost();
            String priceString = priceDouble.toString();
            int numStops = listofItineraries.get(i).getNumberOfStops();
            Double travelTime = listofItineraries.get(i).getTotalTravelTime();
            int travelT = travelTime.intValue();
            int hours = travelT/60;
            int mins = travelT%60;
            String travelTimeString = hours + "h " + mins + "m";
            if (hours == 0){
                travelTimeString = mins + "m";
            }

            // Create a new ItineraryFormat with the departure and arrival dates, travel origin
            // destination, price, traveltime and number of stops.
            mItineraryList.add(new ItineraryFormat(depDate, arrDate, traveloriginInput,
                    destinationInput, priceString, travelTimeString, numStops));
        }

        // Take the format of the ItineraryFormat adapter and then set the empty listview layout to
        // the adapter.
        adapter = new ItineraryAdapter(getApplicationContext(), mItineraryList);
        lvItinerary.setAdapter(adapter);

        // Set and onItemClickListener for the list so that each itinerary can be clicked.
        lvItinerary.setOnItemClickListener(onListClick);
    }

    public void itinerarysortbyCost(View view){

        // Get the departure, travelorigin and destination inputs from the user from that
        // previous activities and declare them to be Strings.
        String departureInput = getIntent().getStringExtra(SearchActivity.DEPARTURE_DATE);
        String traveloriginInput = getIntent().getStringExtra(SearchActivity.TRAVEL_ORIGIN);
        String destinationInput = getIntent().getStringExtra(SearchActivity.DESTINATION_AREA);

        // Create a FlightDatabase and then use that database to search for all itineraries
        // in the system with the given departure, travel orgin and destination.
        FlightDatabase graph = new FlightDatabase(this);
        List<Itinerary> listofItinerariesoriginal = FlightCollection.searchItinerary(this, graph,
                departureInput, traveloriginInput, destinationInput);

        // Sort all of the itineraries by cost using the itinerarySortByCost method in the
        // FlightCollection class
        List<Itinerary> listofItineraries = FlightCollection.itinerarySortByCost(listofItinerariesoriginal);

        // Find the id of the layout of where the itineraries will be displayed.
        lvItinerary = (ListView) findViewById(R.id.display_itineraries);
        mItineraryList = new ArrayList<>();

        // For each itinerary in the list of itineraries.
        for (int i = 0; i < listofItineraries.size(); i++){

            // Get the departure and arrival date of the itinerary
            String depDate = listofItineraries.get(i).getDepartureDateTime();
            String arrDate = listofItineraries.get(i).getArrivalDateTime();

            // Get the price, number of stops and total travel time and convert them to strings.
            Double priceDouble = listofItineraries.get(i).getTotalCost();
            String priceString = priceDouble.toString();
            int numStops = listofItineraries.get(i).getNumberOfStops();
            Double travelTime = listofItineraries.get(i).getTotalTravelTime();
            int travelT = travelTime.intValue();
            int hours = travelT/60;
            int mins = travelT%60;
            String travelTimeString = hours + "h " + mins + "m";
            if (hours == 0){
                travelTimeString = mins + "m";
            }

            // Create a new ItineraryFormat with the departure and arrival dates, travel origin
            // destination, price, traveltime and number of stops.
            mItineraryList.add(new ItineraryFormat(depDate, arrDate, traveloriginInput,
                    destinationInput, priceString, travelTimeString, numStops));
        }

        // Take the format of the ItineraryFormat adapter and then set the empty listview layout to
        // the adapter.
        adapter = new ItineraryAdapter(getApplicationContext(), mItineraryList);
        lvItinerary.setAdapter(adapter);

        // Set and onItemClickListener for the list so that each itinerary can be clicked.
        lvItinerary.setOnItemClickListener(onListClick);
    }
}
