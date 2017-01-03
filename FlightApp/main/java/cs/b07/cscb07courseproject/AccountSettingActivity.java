package cs.b07.cscb07courseproject;

//Ismail Class :)
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.content.Intent;
import android.widget.TextView;
import android.widget.*;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import data.Constants;
import data.Data;
import user.Client;

import static android.R.attr.id;
import static android.R.attr.visible;

/**
 * Class deals with account settings, to view or modify changes to billing or perosnal info
 */
public class AccountSettingActivity extends AppCompatActivity {

    TextView firstNameText;
    TextView lastNameText;
    TextView emailText;
    TextView addressText;
    TextView cardNumText;
    TextView expireText;
    ViewSwitcher switcher;
    String firstName;
    String lastName;
    String email;
    String address;
    String cardNum;
    String expire;

    Client client;

    /**
     * Creates ViewTexts which display Clients personal and billing information.
     * When clicked ViewText becomes EditText and changes can be made.
     * @param savedInstanceState Activities have the ability, under special circumstances,
     * to restore themselves to a previous state using the data stored in this bundle.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_setting);
        setTitle("Account Setting");

        Intent intent = getIntent();
        client = (Client) intent.getSerializableExtra(Constants.MAIN_KEY);

        //Personal information
        firstName = client.getFirstName();
        System.out.println(firstName);
        lastName = client.getLastName();
        email = client.getEmail();
        address = client.getAddress();
        //Billing information
        cardNum = client.getCardNum();
        expire = client.getExpires();

        firstNameText = (TextView) findViewById(R.id.accountSettingFirstName);
        lastNameText = (TextView) findViewById(R.id.accountSettingLastName);
        emailText = (TextView) findViewById(R.id.accountSettingEmail);
        addressText = (TextView) findViewById(R.id.accountSettingAddress);
        cardNumText = (TextView) findViewById(R.id.accountSettingCardNum);
        expireText = (TextView) findViewById(R.id.accountSettingExpire);

        //Shows user's personal and billing info at the very start
        firstNameText.setText(firstName);
        lastNameText.setText(lastName);
        emailText.setText(email);
        addressText.setText(address);
        cardNumText.setText(cardNum);
        expireText.setText(expire);
    }

    /**
     * Switches from ViewText to EditText when any information is clicked using a switcher.
     * @param view the view being clicked (any TextView info that is clicked).
     */
    public void TextViewClicked(View view) {

        //Save button appears if you edit anything
        Button saveButton = (Button) findViewById(R.id.accountSettingSaveButton);
        saveButton.setVisibility(View.VISIBLE);


        //Swaps to EditText when user clicks to edit any informations
        int parentId = ((View) view.getParent()).getId(); //id of parent view

        switcher = (ViewSwitcher) findViewById(parentId);
        switcher.showNext(); //or switcher.showPrevious();

    }

    /**
     * Appears only when changes are made.
     * Does not update if field is left blank (restores to previous).
     * Makes sure it takes correct input type ie. date, card number.
     * Updates Clients information when changes have been made and submitted.
     * @param view the view being clicked (save button)
     */
    public void save (View view){
        Boolean invalid = false;
        TextView ccNum = (TextView) findViewById(R.id.editCardNum);
        String cardNum = ccNum.getText().toString();
        TextView exp = (TextView) findViewById(R.id.editExpire);
        String expire = exp.getText().toString();

        if (!cardNum.matches("")) {
            try {
                Integer.parseInt(cardNum);
            } catch(NumberFormatException e) {
                invalid = true;
            } catch(NullPointerException e) {
                invalid = true;
            }
        }

        if (!expire.matches("")) {
            try {
                Constants.DATE_ONLY_FORMAT.parse(expire);
            } catch (ParseException ex) {
                invalid = true;
            }
        }

        if (invalid) {
            AlertDialog.Builder b = new AlertDialog.Builder(this);
            b.setTitle("Error").setMessage("Invalid CreditCard# or Expire Date.\n Try Again.");
            AlertDialog alert = b.create();
            alert.show();
        } else {

            if (!cardNum.matches("")) {
                client.editCardNum(this, cardNum);
            }

            if (!expire.matches("")) {
                client.editExpires(this, expire);
            }

            TextView firstName = (TextView) findViewById(R.id.editFirstName);
            String first = firstName.getText().toString();
            if (!first.matches("")) {
                client.editFirstName(this, first);
            }

            TextView lastName = (TextView) findViewById(R.id.editLastName);
            String last = lastName.getText().toString();
            if (!last.matches("")) {
                client.editLastName(this, last);
            }

            TextView add = (TextView) findViewById(R.id.editAddress);
            String address = add.getText().toString();
            if (!address.matches("")) {
                client.editAddress(this, address);
            }

            AlertDialog.Builder b = new AlertDialog.Builder(this);
            b.setTitle("Success").setMessage("Client info successfully changed.")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        Intent intent
                                = new Intent(getApplicationContext(), ClientMainActivity.class);
                        intent.putExtra(Constants.MAIN_KEY, client);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                        }
                    });
            AlertDialog alert = b.create();
            alert.show();
        }

    }

    /**
     * Disregards all unsaved changes and goes to previous screen.
     * @param v the view being clicked (cancel button)
     */
    //cancel button makes no changes and goes to back screen
    public void cancel(View v) {
        finish();
    }

}