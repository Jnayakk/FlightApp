package cs.b07.cscb07courseproject;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import data.Constants;
import data.Data;
import data.FlightCollection;
import flight.Flight;

/**
 * Allows client or admin to view all flights.
 */
public class ViewAllFlightsActivity extends AppCompatActivity {
    private List<Flight> allFlights;
    private ListView lvFlight;
    private FlightAdaptor adapter;
    private List<FlightFormat> mFlightList;

    /**
     * Creates user interface that displays all flights.
     * @param savedInstanceState Activities have the ability, under special circumstances,
     * to restore themselves to a previous state using the data stored in this bundle.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("View All Flights");
        setContentView(R.layout.activity_view_all_flights);
        lvFlight = (ListView) findViewById(R.id.display_flights);
        allFlights = Data.viewAllFlights(this);
        mFlightList = new ArrayList<>();
        for (int i = 0; i < allFlights.size(); i++){
            Double travelTimeDouble = allFlights.get(i).getTravelTime();
            int travelT = travelTimeDouble.intValue();
            int hours = travelT/60;
            int mins = travelT%60;
            String travelTimeString = hours + "h " + mins + "m";
            if (hours == 0){
                travelTimeString = mins + "m";
            }
            String flight = allFlights.get(i).toString();
            String[] tokens = flight.split(";");
            mFlightList.add(new FlightFormat(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4],
                    tokens[5], tokens[6], travelTimeString));
        }
        adapter = new FlightAdaptor(getApplicationContext(), mFlightList);
        lvFlight.setAdapter(adapter);
        lvFlight.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        lvFlight.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), EditFlightActivity.class);
                Flight flight = allFlights.get(position);
                intent.putExtra(Constants.MAIN_KEY, flight);
                startActivity(intent);
            }
        });
    }

    /**
     * Sorts the flights by cost.
     * @param view Sort by Cost indicator.
     */
    public void flightssortByCost(View view) {
        List<Flight> allFlights = Data.viewAllFlights(this);
        List<Flight> listofFlights = FlightCollection.flightSortByCost(allFlights);
        lvFlight = (ListView) findViewById(R.id.display_flights);
        mFlightList = new ArrayList<>();
        for (int i = 0; i < listofFlights.size(); i++){
            Double travelTimeDouble = listofFlights.get(i).getTravelTime();
            int travelT = travelTimeDouble.intValue();
            int hours = travelT/60;
            int mins = travelT%60;
            String travelTimeString = hours + "h " + mins + "m";
            if (hours == 0){
                travelTimeString = mins + "m";
            }
            String flight = listofFlights.get(i).toString();
            String[] tokens = flight.split(";");
            mFlightList.add(new FlightFormat(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4],
                    tokens[5], tokens[6], travelTimeString));
        }

        adapter = new FlightAdaptor(getApplicationContext(), mFlightList);
        lvFlight.setAdapter(adapter);
    }

    /**
     * Sorts the flights by time.
     * @param view Sort by time indicator.
     */
    public void flightssortByTime(View view) {
        List<Flight> allFlights = Data.viewAllFlights(this);
        List<Flight> listofFlights = FlightCollection.flightSortByTime(allFlights);
        lvFlight = (ListView) findViewById(R.id.display_flights);
        mFlightList = new ArrayList<>();
        for (int i = 0; i < listofFlights.size(); i++){
            Double travelTimeDouble = listofFlights.get(i).getTravelTime();
            int travelT = travelTimeDouble.intValue();
            int hours = travelT/60;
            int mins = travelT%60;
            String travelTimeString = hours + "h " + mins + "m";
            if (hours == 0){
                travelTimeString = mins + "m";
            }
            String flight = listofFlights.get(i).toString();
            String[] tokens = flight.split(";");
            mFlightList.add(new FlightFormat(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4],
                    tokens[5], tokens[6], travelTimeString));
        }

        adapter = new FlightAdaptor(getApplicationContext(), mFlightList);
        lvFlight.setAdapter(adapter);
    }

}
