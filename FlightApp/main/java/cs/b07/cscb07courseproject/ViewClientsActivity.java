package cs.b07.cscb07courseproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.AdapterView;
import android.widget.ListView;
import android.view.View;

import java.util.List;

import data.Constants;
import data.Data;
import user.Client;


/**
 * Created by Nathan on 12/1/2016.
 * Allows Admin to view all clients.
 */
public class ViewClientsActivity extends AppCompatActivity {

    private ListView lvClient;
    private ClientAdaptor adapter;

    /**
     * Creates user interface that displays all clients.
     * @param savedInstanceState Activities have the ability, under special circumstances,
     * to restore themselves to a previous state using the data stored in this bundle.
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_clients);
        setTitle("View All Clients");

        lvClient = (ListView) findViewById(R.id.display_clients);
        final List<String> listOfClients = Data.viewAllClients(this);

        adapter = new ClientAdaptor(this, listOfClients);
        lvClient.setAdapter(adapter);
        lvClient.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ClientMainActivity.class);
                String[] tokens = listOfClients.get(position).split(";");
                Client client = new Client(getApplicationContext(), tokens[2], "", tokens[0],
                        tokens[1], tokens[3], tokens[4], tokens[5]);
                intent.putExtra(Constants.MAIN_KEY, client);
                startActivity(intent);
            }
            });
    }
}
