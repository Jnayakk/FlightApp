package cs.b07.cscb07courseproject;

import android.content.Context;

import android.view.View;
import android.view.ViewGroup;

import android.widget.BaseAdapter;

import android.widget.TextView;

import java.util.List;

/**
 * This class sets the TextViews to the given FlightFormat so that each flight is shown in the
 * format of flight_row_view.
 */

public class FlightAdaptor extends BaseAdapter{
    private Context mContext;
    private List<FlightFormat> mFlightList; //List of the formats of how the flights will appear.

    /**
     * Creates a new <code>FlightAdaptor</code> with the given Context and List of FlightFormats
     *
     * @param mContext the context of this <code>FlightAdaptor</code>.
     * @param mFlightList the list of FlightFormats of this <code>FlightAdaptor</code>.
     */
    public FlightAdaptor(Context mContext, List<FlightFormat> mFlightList) {
        this.mContext = mContext;
        this.mFlightList = mFlightList;
    }

    @Override
    public int getCount() {
        return mFlightList.size();
    }

    @Override
    public Object getItem(int position) {
        return mFlightList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Returns a View with the given position, convertView and parent.
     *
     * @param position the position of the View.
     * @param convertView the convertView of the View.
     * @param parent the parent of the View.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(mContext, R.layout.flight_row_view, null);

        // Looks for the Id's of each of the TextViews in the flight_row_view layout
        // And sets their respective variable names to them.
        TextView flightNumTextView = (TextView)v.findViewById(R.id.flightnumText);
        TextView originTextView = (TextView)v.findViewById(R.id.originText);
        TextView destinationTextView = (TextView)v.findViewById(R.id.destinationText);
        TextView flightNameTextView = (TextView)v.findViewById(R.id.flightnameText);
        TextView departureDateTextView = (TextView)v.findViewById(R.id.departuredateText);
        TextView arrivalDateTextView = (TextView)v.findViewById(R.id.arrivaldateText);
        TextView priceTextView = (TextView)v.findViewById(R.id.priceText);
        TextView travelTimeTextView = (TextView)v.findViewById(R.id.traveltimeText);

        // Gets the departure date from the list of flightformats and then stores it as a
        // String in the format of "Depart: + DepartureDate + at + DepartureTime
        String depDate = mFlightList.get(position).getDepartureDateTime();
        String[] tokens = depDate.split(" ");
        String depDateFormat = "Depart: " + tokens[0] + " at " + tokens[1];

        // Gets the arival date from the list of flightformats and then stores it as a
        // String in the format of "Arrive: + ArrivalDate + at + ArrivalTime
        String arrDate = mFlightList.get(position).getArrivalDateTime();
        String[] tokens1 = arrDate.split(" ");
        String arrDateFormat = "Arrive: " + tokens1[0] + " at " + tokens1[1];

        /* Set the get Flight number, origin, destination, airline, departure date,
           arrival date, price, and travel time from the FlightFormat and set them to their
           respective TextViews in the flight_row_view layout */
        flightNumTextView.setText(" " + mFlightList.get(position).getFlightNum());
        originTextView.setText((mFlightList.get(position).getOrigin()));
        destinationTextView.setText(mFlightList.get(position).getDestination());
        flightNameTextView.setText(mFlightList.get(position).getAirline() + " ");
        departureDateTextView.setText(" " + depDateFormat);
        arrivalDateTextView.setText(arrDateFormat + " ");
        priceTextView.setText("$"+ mFlightList.get(position).getCost() + "  ");
        travelTimeTextView.setText(" Duration: " + mFlightList.get(position).getTravelTime());

        // return the view.
        return v;
    }
}
