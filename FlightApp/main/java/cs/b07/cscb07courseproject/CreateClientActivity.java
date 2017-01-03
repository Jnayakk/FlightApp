package cs.b07.cscb07courseproject;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.IOException;
import java.text.ParseException;

import data.Constants;
import data.Data;

/**
 * Class that creates clients used when registering or when admin creats a client.
 */
public class CreateClientActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_client);
        setTitle("New Client");
    }

    /**
     * Creates client with personal and billing information given by user
     * @param view create-new-client-button
     * @throws IOException crashes when intent is not passed correctly.
     */
    public void CreateClient(View view) throws IOException {
        EditText emailText = (EditText) findViewById(R.id.email_text);
        String email = emailText.getText().toString();

        EditText passwordText = (EditText) findViewById(R.id.password_text);
        String password = passwordText.getText().toString();

        EditText lastText = (EditText) findViewById(R.id.last_text);
        String last = lastText.getText().toString();

        EditText firstText = (EditText) findViewById(R.id.first_text);
        String first = firstText.getText().toString();

        EditText addressText = (EditText) findViewById(R.id.address_text);
        String address =addressText.getText().toString();

        EditText ccNumText = (EditText) findViewById(R.id.ccNum_text);
        String ccNum = ccNumText.getText().toString();

        EditText expireText = (EditText) findViewById(R.id.expire_text);
        String expire = expireText.getText().toString();

        Boolean invalid = false;

        try {
            Integer.parseInt(ccNum);
        } catch(NumberFormatException e) {
            invalid = true;
        } catch(NullPointerException e) {
            invalid = true;
        }
        try {
            Constants.DATE_ONLY_FORMAT.parse(expire);
        } catch (ParseException ex) {
            invalid = true;
        }
        if (invalid || email.matches("") || password.matches("") || last.matches("") ||
                first.matches("") || address.matches("") || ccNum.matches("") || expire.matches("")) {
            AlertDialog.Builder b = new AlertDialog.Builder(this);
            b.setTitle("Error").setMessage("Invalid Input.\n Try Again.");
            AlertDialog alert = b.create();
            alert.show();
        } else if (Data.viewUser(this, email) != null || Data.viewClient(this, email) != null) {
            AlertDialog.Builder b = new AlertDialog.Builder(this);
            b.setTitle("Error").setMessage("Email already in Use.\n Try Again.");
            AlertDialog alert = b.create();
            alert.show();
        } else {
            Data.addUser(this, email, password);
            Data.addClient(this, last, first, email, address, ccNum, expire);

            AlertDialog.Builder b = new AlertDialog.Builder(this);
            b.setTitle("Success").setMessage("Client created successfully.")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {finish();
                        }
                    });
            AlertDialog alert = b.create();
            alert.show();
        }
    }
}
