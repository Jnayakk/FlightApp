package cs.b07.cscb07courseproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;

import data.Constants;
import data.Data;
import user.Client;

/**
 * Client access and privileges.
 */
public class ClientMainActivity extends AppCompatActivity {

    private Client client;

    /**
     * A client interface is created to search for/book flights/itins or change account settings.
     * @param savedInstanceState Activities have the ability, under special circumstances,
     * to restore themselves to a previous state using the data stored in this bundle.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_main);
        setTitle("Client");

        Intent intent = getIntent();
        client = (Client) intent.getSerializableExtra(Constants.MAIN_KEY);

        TextView userNameText = (TextView) findViewById(R.id.userClient);
        userNameText.setText("User: " + client.getEmail());
    }

    /**
     * Client can search all flight/itineraries.
     * @param view Search Flight/Itin button.
     * @throws IOException crashes when itent is passed incorrectly.
     */
    public void ClientSearchFI(View view) throws IOException {
        Intent intent = new Intent(this, SearchActivity.class);
        intent.putExtra(Constants.MAIN_KEY, client);
        startActivity(intent);
    }

    /**
     * Client can view all of his booked flights
     * @param view "view booked" button
     * @throws IOException crashes when intent is not passed correctly.
     */
    public void ViewBooked(View view) throws IOException {
        Intent intent = getIntent();
        client = (Client) intent.getSerializableExtra(Constants.MAIN_KEY);
        Intent i = new Intent(this, BookedActivity.class);
        i.putExtra(Constants.MAIN_KEY, client);
        startActivity(i);
    }

    /**
     * Client can change his account settings
     * @param view "account settings button"
     * @throws IOException crashes when intent is not passed correctly.
     */
    public void AccountSetting(View view) throws IOException {
        Intent intent = new Intent(this, AccountSettingActivity.class);
        intent.putExtra(Constants.MAIN_KEY, client);
        startActivity(intent);
    }

}
