package cs.b07.cscb07courseproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.IOException;

import data.Constants;
import data.Data;

/**
 * Creates and stores password associated with the email address.
 */
public class CreatePassActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_pass);

    }

    /**
     * Saves the new new password and indicates to user if successful or not.
     * @param view Save button
     * @throws IOException crashes when intent is not passed correctly.
     */
    public void Save(View view) throws IOException {
        Intent intent = getIntent();
        String email = intent.getStringExtra(Constants.MAIN_KEY);

        EditText passwordText = (EditText) findViewById(R.id.ccNum_text);
        String password = passwordText.getText().toString();
        EditText verifyText = (EditText) findViewById(R.id.expire_text);
        String verify = verifyText.getText().toString();

        if (!password.equals(verify)) {
            AlertDialog.Builder b = new AlertDialog.Builder(this);
            b.setTitle("Error").setMessage("Password does not match.\nTry Again.");
            AlertDialog alert = b.create();
            alert.show();
        } else {
            Data.addUser(this, email, password);
            AlertDialog.Builder b = new AlertDialog.Builder(this);
            b.setTitle("Success").setMessage("Password created successfully.")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {finish();
                        }
                    });
            AlertDialog alert = b.create();
            alert.show();
        }
    }
}
