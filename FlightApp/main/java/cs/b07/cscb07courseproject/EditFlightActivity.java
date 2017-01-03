package cs.b07.cscb07courseproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import java.text.ParseException;
import java.util.Date;

import data.Constants;
import flight.Flight;

/**
 * Class lets users to view all flights that are available given their input.
 */
public class EditFlightActivity extends AppCompatActivity {
    Flight flight;

    /**
     * Creates text fields where a user can edit and put input and stores the information.
     * @param savedInstanceState Activities have the ability, under special circumstances,
     * to restore themselves to a previous state using the data stored in this bundle.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_flight);
        Intent intent = getIntent();
        flight = (Flight) intent.getSerializableExtra(Constants.MAIN_KEY);

        String flightNum = flight.getFlightNum();
        String departureDateTime = flight.getDepartureDateTime();
        String arrivalDateTime = flight.getArrivalDateTime();
        String airline = flight.getAirline();
        String origin = flight.getOrigin();
        String destination = flight.getDestination();
        String cost = Double.toString(flight.getCost());
        String seatNum = Integer.toString(flight.getSeatNum());

        TextView flightNumText = (TextView) findViewById(R.id.FlightNum);
        TextView departureDateTimeText = (TextView) findViewById(R.id.depDate);
        TextView arrivalDateTimeText = (TextView) findViewById(R.id.arrDate);
        TextView airlineText = (TextView) findViewById(R.id.airline);
        TextView originText = (TextView) findViewById(R.id.origin);
        TextView destinationText = (TextView) findViewById(R.id.destination);
        TextView costText = (TextView) findViewById(R.id.cost);
        TextView seatNumText = (TextView) findViewById(R.id.seatNum);


        flightNumText.setText(flightNum);
        departureDateTimeText.setText(departureDateTime);
        arrivalDateTimeText.setText(arrivalDateTime);
        airlineText.setText(airline);
        originText.setText(origin);
        destinationText.setText(destination);
        costText.setText(cost);
        seatNumText.setText(seatNum);
    }

    /**
     * Switches ViewTexts to EditTexts so user can input and find flights based on his needs.
     * @param view EditText fields.
     */
    public void TextViewClicked(View view) {

        //Save button appears if you edit anything
        Button saveButton = (Button) findViewById(R.id.SaveButton);
        saveButton.setVisibility(View.VISIBLE);


        //Swaps to EditText when user clicks to edit any informations
        int parentId = ((View) view.getParent()).getId(); //id of parent view

        ViewSwitcher switcher = (ViewSwitcher) findViewById(parentId);
        switcher.showNext(); //or switcher.showPrevious();

    }

    /**
     * Saves the information a user inputted and determines all possibilities base on given info.
     * @param view Save button.
     */
    public void save (View view){
        Boolean invalid = false;
        //TextView editFlightNumText = (TextView) findViewById(R.id.editFlightNum);
        //String editFlightNum = editFlightNumText.getText().toString();
        TextView editDepartureDateTimeText = (TextView) findViewById(R.id.editDeparture);
        String editDepartureDateTime = editDepartureDateTimeText.getText().toString();
        TextView editArrivalDateTimeText = (TextView) findViewById(R.id.editArrival);
        String editArrivalDateTime = editArrivalDateTimeText.getText().toString();
        TextView editAirlineText = (TextView) findViewById(R.id.editAirline);
        String editAirline = editAirlineText.getText().toString();
        TextView editOriginText = (TextView) findViewById(R.id.editOrigin);
        String editOrigin = editOriginText.getText().toString();
        TextView editDestinationText = (TextView) findViewById(R.id.editDestination);
        String editDestination = editDestinationText.getText().toString();
        TextView editCostText = (TextView) findViewById(R.id.editCost);
        String editCost = editCostText.getText().toString();
        TextView editSeatNumText = (TextView) findViewById(R.id.editSeatNum);
        String editSeatNum = editSeatNumText.getText().toString();
        Date depDateTime;
        Date arrDateTime;


        if (!editSeatNum.matches("")) {
            try {
                int eSeatNum = Integer.parseInt(editSeatNum);
                flight.editSeatNum(this, eSeatNum);
            } catch(NumberFormatException e) {
                invalid = true;
            }
        }

        if (!editCost.matches("")) {
            try {
                Double eCost = Double.parseDouble(editCost);
                flight.editCost(this, eCost);
            } catch(NumberFormatException e) {
                invalid = true;
            }
        }

        if (!editDepartureDateTime.matches("")) {
                try {
                    depDateTime = Constants.DATE_TIME_FORMAT.parse(editDepartureDateTime);
                    flight.editDepartureDateTime(this, depDateTime);
                } catch (ParseException ex) {
                    invalid = true;
                }
            }

        if (!editArrivalDateTime.matches("")) {
            try {
                arrDateTime = Constants.DATE_TIME_FORMAT.parse(editArrivalDateTime);
                flight.editArrivalDateTime(this,arrDateTime);
            } catch (ParseException ex) {
                invalid = true;
            }
        }
        if (invalid) {
            AlertDialog.Builder b = new AlertDialog.Builder(this);
            b.setTitle("Error").setMessage("Invalid Input.\nTry Again.");
            AlertDialog alert = b.create();
            alert.show();
        } else {
            if (!editAirline.matches("")) {
                flight.editAirline(this, editAirline);
            }

            if (!editOrigin.matches("")) {
                flight.editOrigin(this, editOrigin);
            }

            if (!editDestination .matches("")) {
                flight.editDestination (this, editDestination );
            }


            AlertDialog.Builder b = new AlertDialog.Builder(this);
            b.setTitle("Success").setMessage("Flight info successfully changed.")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent
                                    = new Intent(getApplicationContext(), ViewAllFlightsActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            finish();
                        }
                    });
            AlertDialog alert = b.create();
            alert.show();
        }
    }
    public void cancel(View v) {
        finish();
    }
}
