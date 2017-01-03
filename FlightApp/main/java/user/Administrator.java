package user;

import android.content.Context;

import data.Data;
import flight.Flight;
import information.BillingInformation;
import information.PersonalInformation;

import java.io.Serializable;
import java.util.List;



/**
 * Representation of am Administrator.
 * 
 * @author team_0575
 */
public class Administrator extends User implements Serializable{

  /**
   * Create a new Administrator with the given email and password.
   * 
   * @param email The email/username of the <code>Administrator</code>
   * @param password The password of the <code>Administrator</code>
   */
  public Administrator(Context context, String email, String password) {
    super(context, email, password);
  }

  /**
   * Return a <code>List</code> of <code>String</code> of all the Clients and their informations.
   * 
   * @return a <code>List</code> of <code>String</code> of all the Clients and their informations.
   */
  public List<String> viewAllClients(Context context) {
    return Data.viewAllClients(context);
  }


  /**
   * Return a String of the <code>Client</code>'s informations given the email.
   * 
   * @param email The email of the <code>Client</code>.
   * @return a <code>String</code> of the <code>Client</code>'s informations.
   */
  public String viewClient(Context context, String email) {
    return Data.viewClient(context, email);
  }

  /**
   * Edit a <code>Client</code>'s <code>PersonalInformation</code>.
   * 
   * @param client The <code>Client</code> that is being modified.
   * @param info The new information that is to be changed to the <code>CLient</code>.
   */
  public void editPersonalInfo(Context context, Client client, PersonalInformation info) {
    client.editFirstName(context, info.getFirstName());
    client.editLastName(context, info.getLastName());
    client.editAddress(context, info.getAddress());
    Data.editClient(context, info.getLastName(), info.getFirstName(), client.getEmail(),
            info.getAddress(), client.getCardNum(), client.getExpires());
  }

  /**
   * Edit a <code>Client</code>'s <code>PersonalInformation</code>.
   * 
   * @param client client The <code>Client</code> that is being modified.
   * @param info The new information that is to be changed to the <code>CLient</code>.
   */
  public void editBillingInfo(Context context, Client client, BillingInformation info) {
    client.editCardNum(context, info.getCardNum());
    client.editExpires(context, info.getExpires());
    Data.editClient(context, client.getLastName(), client.getFirstName(), client.getEmail(),
        client.getAddress(), info.getCardNum(), info.getExpires());
  }

  /**
   * Upload a new <code>Client</code> to the system.
   * 
   * @param file The file with the new <code>Client</code>'s informations.
   */
  public void uploadClientInfo(Context context, String file) {
    Data.uploadClient(context, file);
  }

  /**
   * Add a new <code>Flight</code> to the system.
   * 
   * @param flight The <code>Flight</code> that is to be added the the system.
   */
  public void addFlight(Context context, Flight flight) {
    Data.addFlight(context, flight.getFlightNum(), flight.getDepartureDateTime(),
        flight.getArrivalDateTime(), flight.getAirline(), flight.getOrigin(),
        flight.getDestination(), flight.getCost());
  }

  /**
   * Upload a new <code>Flight</code> to the system.
   * 
   * @param file The file with the new <code>Flight</code> informations.
   */
  public void uploadFlight(Context context, String file) {
    Data.uploadFlight(context, file);
  }

}
