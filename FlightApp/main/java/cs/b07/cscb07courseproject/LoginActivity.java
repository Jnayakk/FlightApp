package cs.b07.cscb07courseproject;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.content.Intent;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import data.Constants;
import data.Data;
import user.Administrator;
import user.Client;

/**
 * Class deals with the login page and only allows users to login with credible email and password.
 */
public class LoginActivity extends AppCompatActivity {

    /**
     * User interface is created.
     * @param savedInstanceState Activities have the ability, under special circumstances,
     * to restore themselves to a previous state using the data stored in this bundle.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("");
        try {
            InputStream inputStream = this.openFileInput("users.txt");
        } catch (FileNotFoundException ex) {
            try {
                OutputStreamWriter outputStreamWriter =
                        new OutputStreamWriter(this
                                .openFileOutput("users.txt", Context.MODE_PRIVATE));
                outputStreamWriter.write("admin;123\n");
                outputStreamWriter.close();
            } catch (IOException except) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Matches user's input to client and admin accounts stored in a text file.
     * @param view login button
     * @throws IOException crashes if input is passed incorrectly.
     */
    public void Login(View view) throws IOException {
        EditText emailText = (EditText) findViewById(R.id.editEmail);
        final String email = emailText.getText().toString();
        EditText passwordText = (EditText) findViewById(R.id.editPassword);
        String password = passwordText.getText().toString();

        ////////////////////TESTING PURPOSES///////////////////////////////
        try {
            OutputStreamWriter outputStreamWriter =
                    new OutputStreamWriter(this
                            .openFileOutput("users.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write("a;123\nc1;123\n");
            outputStreamWriter.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        try {
            OutputStreamWriter outputStreamWriter =
                    new OutputStreamWriter(this
                            .openFileOutput("clients.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write("c23;123\nc13;123\n");
            outputStreamWriter.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try {
            OutputStreamWriter outputStreamWriter =
                    new OutputStreamWriter(this
                            .openFileOutput("clients.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write("IsAwesome;Bob;sampleclient;123 avenue;1234567890;2017-12-23\n" +
                    "TheBest;Brian;c2;123abc avenue;1234567890;2017-12-23");
            outputStreamWriter.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        try {
            OutputStreamWriter outputStreamWriter =
                    new OutputStreamWriter(this.openFileOutput("flights.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write("AC489;2016-09-30 20:23;2016-09-30 23:27;LuftFlights;Chicago;Los Angelos;300.99;2\n");
            outputStreamWriter.write("KL490;2016-09-30 22:40;2016-10-01 01:59;Go Airline;New York;Los Angelos;532.00;200\n");
            outputStreamWriter.write("KL102;2016-09-30 16:43;2016-09-30 17:15;LuftFlights;New York;Chicago;290.50;134\n");
            outputStreamWriter.write("KK234;2016-09-30 09:37;2016-09-30 12:22;Unc Airline;New York;California;290.32;145\n");
            outputStreamWriter.write("EC224;2016-09-30 14:37;2016-09-30 15:22;JozyFlights;California;Miami;295.44;145\n");
            outputStreamWriter.write("CV123;2016-09-30 17:37;2016-09-30 23:22;Go Airline;Miami;Chicago;523.12;145\n");
            outputStreamWriter.write("AA103;2016-09-30 16:37;2016-09-30 17:22;Go Airline;New York;Chicago;300.00;145\n");
            outputStreamWriter.write("RS244;2016-09-30 18:37;2016-09-30 19:22;Delta Airline;New York;Chicago;413.00;145\n");
            outputStreamWriter.write("KI334;2016-09-30 11:37;2016-09-30 14:22;Unc Airline;New York;Chicago;290.32;145\n");
            outputStreamWriter.write("ES948;2016-09-30 14:37;2016-09-30 17:22;JozyFlights;New York;Chicago;295.44;145\n");
            outputStreamWriter.write("RU223;2016-09-30 13:37;2016-09-30 17:22;Go Airline;New York;Chicago;523.12;145\n");
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
        /////////////////////////////////////////////////////////////////////

        String userInfo = Data.viewUser(this, email);
        String clientInfo = Data.viewClient(this, email);

        if (userInfo != null) {
            String[] userToken = userInfo.split(";");
            if (userToken[0].equals(email) && userToken[1].equals(password)) {
                if (clientInfo == null) {
                    Intent intent = new Intent(this, AdminMainActivity.class);
                    Administrator admin = new Administrator(this, email, password);
                    intent.putExtra(Constants.MAIN_KEY, admin);
                    startActivity(intent);
                } else if (clientInfo != null) {
                    Intent intent = new Intent(this, ClientMainActivity.class);
                    String[] tokens = clientInfo.split(";");
                    Client client = new Client(this, email, password, tokens[0],
                            tokens[1], tokens[3], tokens[4], tokens[5]);
                    intent.putExtra(Constants.MAIN_KEY, client);
                    startActivity(intent);
                }
            } else {
                AlertDialog.Builder b = new AlertDialog.Builder(this);
                b.setTitle("Error").setMessage("Invalid username or password.\nTry again");
                AlertDialog alert = b.create();
                alert.show();
            }
        } else {
            Intent intent = new Intent(this, ClientMainActivity.class);
            String[] tokens = clientInfo.split(";");
            Client client = new Client(this, email, password, tokens[0],
                    tokens[1], tokens[3], tokens[4], tokens[5]);
            intent.putExtra(Constants.MAIN_KEY, client);
            startActivity(intent);
        }
    }

    /**
     * Passes the user's info to secondary page after login.
     * @param view login button.
     * @throws IOException crashes if intent is incorrectly passed.
     */
    public void Create(View view) throws IOException {
        Intent intent = new Intent(this, CreateClientActivity.class);
        startActivity(intent);
    }
}
