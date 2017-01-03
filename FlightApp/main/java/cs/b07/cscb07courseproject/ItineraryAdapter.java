package cs.b07.cscb07courseproject;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import flight.Itinerary;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jay on 2016-11-30.
 */

public class ItineraryAdapter extends BaseAdapter{

    private Context mContext;

    //List of the formats of how the itineraries will appear.
    private List<ItineraryFormat> mItineraryList;

    /**
     * Creates a new <code>ItineraryAdaptor</code> with the given Context and List of
     * ItineraryFormats.
     *
     * @param mContext the context of this <code>ItineraryAdaptor</code>.
     * @param mItineraryList the list of ItineraryFormats of this <code>ItineraryAdaptor</code>.
     */
    public ItineraryAdapter(Context mContext, List<ItineraryFormat> mItineraryList) {
        this.mContext = mContext;
        this.mItineraryList = mItineraryList;
    }

    @Override
    public int getCount() {
        return mItineraryList.size();
    }

    @Override
    public Object getItem(int position) {
        return mItineraryList.get(position);
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
        View v = View.inflate(mContext, R.layout.itinerary_row_view, null);

        // Looks for the Id's of each of the TextViews in the itinerary_row_view layout
        // And sets their respective variable names to them.
        TextView numofStops = (TextView) v.findViewById(R.id.numberofStopsText);
        TextView originTextView = (TextView) v.findViewById(R.id.origin_text);
        TextView destinationTextView = (TextView) v.findViewById(R.id.destination_text);
        TextView departureTimeTextView = (TextView) v.findViewById(R.id.departing_time_text);
        TextView arrivalTimeTextView = (TextView) v.findViewById(R.id.arriving_time_text);
        TextView priceTextView = (TextView) v.findViewById(R.id.totalPriceText);
        TextView totaltravelTimeTextView = (TextView) v.findViewById(R.id.totaltravelTimeText);

        // Gets the departure time from the list of itineraryformats and then stores it as a
        // String in the format of #h #m
        String depTime = mItineraryList.get(position).getDepartureDateTime();
        String[] tokens = depTime.split(" ");
        String depTimeFormat = tokens[1];

        // Gets the arrival time from the list of itineraryformats and then stores it as a
        // String in the format of #h #m
        String arrTime = mItineraryList.get(position).getArrivalDateTime();
        String[] tokens1 = arrTime.split(" ");
        String arrTimeFormat = tokens1[1];

        // Get the number of stops and set their corresponding TextViews.
        if (mItineraryList.get(position).getNumberofStops() == (0)){
            numofStops.setText("  No Stops");
        }
        else if (mItineraryList.get(position).getNumberofStops() == (1)){
            numofStops.setText("  1 Stop");
        }
        else {
            numofStops.setText("  " + mItineraryList.get(position).getNumberofStops() + " Stops");
        }

        /* Set the get origin, destination, totalprice, traveltime,
           arrival date, and departure date from the ItineraryFormat and set them to their
           respective TextViews in the itinerary_row_view layout */
        originTextView.setText(mItineraryList.get(position).getOrigin());
        destinationTextView.setText(mItineraryList.get(position).getDestination());
        priceTextView.setText("$" + mItineraryList.get(position).getTotalPrice() + "  ");
        totaltravelTimeTextView.setText(mItineraryList.get(position).getTravelTime());
        departureTimeTextView.setText(depTimeFormat);
        arrivalTimeTextView.setText(arrTimeFormat);

        // return the view
        return v;

    }
}
