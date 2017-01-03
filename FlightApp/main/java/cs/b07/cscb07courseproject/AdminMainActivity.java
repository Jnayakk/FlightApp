package cs.b07.cscb07courseproject;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import data.Constants;
import data.Data;
import user.Administrator;

/**
 * Deals with Admin privileges.
 */
public class AdminMainActivity extends AppCompatActivity {

    private Administrator admin;

    /**
     * Admin account is made on creation.
     * @param savedInstanceState Activities have the ability, under special circumstances,.
     * to restore themselves to a previous state using the data stored in this bundle.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);
        setTitle("Administrator");

        Intent intent = getIntent();
        admin = (Administrator) intent.getSerializableExtra(Constants.MAIN_KEY);

        TextView userNameText = (TextView) findViewById(R.id.userAdmin);
        userNameText.setText("User: " + admin.getEmail());
    }

    /**
     * Admin can search all flights/itineraries.
     * @param view "search flight/itinerary" button.
     * @throws IOException crashes when intent is given incorrectly.
     */
    public void AdminSearchFI(View view) throws IOException {
        Intent intent = new Intent(this, SearchActivity.class);
        //intent.putExtra(Constants.MAIN_KEY, admin);
        startActivity(intent);
    }

    /**
     * As an admin view all of clients.
     * @param view "view all clients" button.
     * @throws IOException crashes when intent is passed incorrectly.
     */
    public void ViewAllClients(View view) throws IOException {
        Intent intent = new Intent(this, ViewClientsActivity.class);
        startActivity(intent);
    }

    /**
     * As an admin view all flights already made.
     * @param view "View All flights" button.
     * @throws IOException throws exception when intent is passed incorrectly.
     */
    public void ViewAllFlights(View view) throws IOException {
        Intent intent = new Intent(this, ViewAllFlightsActivity.class);
        intent.putExtra(Constants.MAIN_KEY, admin);
        startActivity(intent);
    }

    /**
     * As an admin, upload users (stores info in a text file, can be either client or admin).
     * @param view upload users button.
     * @throws IOException crashes when intent is given incorrectly.
     */
    public void UploadUsers(View view) throws IOException {
        EditText fileNameText = (EditText) findViewById(R.id.upload_users_file);
        String fileName = fileNameText.getText().toString();
        try {
            InputStream inputStream = this.openFileInput(fileName);
            Data.uploadUser(this, fileName);
            AlertDialog.Builder b = new AlertDialog.Builder(this);
            b.setTitle("Success").setMessage("Users uploaded!")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                            finish();
                            startActivity(getIntent());
                        }
                    });
            AlertDialog alert = b.create();
            alert.show();
        } catch (FileNotFoundException ex) {
            AlertDialog.Builder b = new AlertDialog.Builder(this);
            b.setTitle("Error").setMessage("File Not Found!");
            AlertDialog alert = b.create();
            alert.show();
        }
    }

    /**
     * As an admin, upload clients only (stores info in a text file on the phone).
     * @param view upload clients button.
     * @throws IOException crashes when intent is passed incorrectly.
     */
    public void UploadClients(View view) throws IOException {
        EditText fileNameText = (EditText) findViewById(R.id.upload_clients_file);
        String fileName = fileNameText.getText().toString();
        try {
            InputStream inputStream = this.openFileInput(fileName);
            Data.uploadClient(this, fileName);
            AlertDialog.Builder b = new AlertDialog.Builder(this);
            b.setTitle("Success").setMessage("Users uploaded!")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                            finish();
                            startActivity(getIntent());
                        }
                    });
            AlertDialog alert = b.create();
            alert.show();
        } catch (FileNotFoundException ex) {
            AlertDialog.Builder b = new AlertDialog.Builder(this);
            b.setTitle("Error").setMessage("File Not Found!");
            AlertDialog alert = b.create();
            alert.show();
        }
    }

    /**
     * As an admin, you can upload a flights for a client.
     * @param view upload flights button.
     * @throws IOException crashes when intent is passed incorrectly.
     */
    public void UploadFlights(View view) throws IOException {
        EditText fileNameText = (EditText) findViewById(R.id.upload_flights_file);
        String fileName = fileNameText.getText().toString();
        try {
            InputStream inputStream = this.openFileInput(fileName);
            Data.uploadFlight(this, fileName);
            AlertDialog.Builder b = new AlertDialog.Builder(this);
            b.setTitle("Success").setMessage("Users uploaded!")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                            finish();
                            startActivity(getIntent());
                        }
                    });
            AlertDialog alert = b.create();
            alert.show();
        } catch (FileNotFoundException ex) {
            AlertDialog.Builder b = new AlertDialog.Builder(this);
            b.setTitle("Error").setMessage("File Not Found!");
            AlertDialog alert = b.create();
            alert.show();
        }
    }
}
