package user;


import android.content.Context;

import data.Data;
import flight.Itinerary;
import information.BillingInformation;
import information.PersonalInformation;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



/**
 * Representation of a Client.
 * 
 * @author team_0575
 */
public class Client extends User implements Serializable {

  private PersonalInformation personalInfo;
  private BillingInformation billingInfo;
  private List<Itinerary> bookedFlights;

  /**
   * Create a new <code>Client</code> with the given email and password.
   * 
   * @param email The email/username of the <code>Client</code>
   * @param password The password of the <code>Client</code>
   */
  public Client(Context context, String email, String password, String lastName, String firstName,
                String address, String cardNum, String expires) {
    super(context, email, password);
    this.personalInfo = new PersonalInformation(lastName, firstName, email, address);
    this.billingInfo = new BillingInformation(cardNum, expires);
    this.bookedFlights = new ArrayList<>();
    Data.addClient(context, lastName, firstName, email, address, cardNum, expires);
  }

  /**
   * Set the <code>BillingInformation</code> of the <code>Client</code>.
   * 
   * @param firstName The first name of the <code>Client</code>.
   * @param lastName The last name of the <code>Client</code>.
   * @param address The address of the <code>Client</code>.
   */
  public void editPersonalInfo(Context context, String lastName, String firstName, String address) {
    this.personalInfo.setFirstName(firstName);
    this.personalInfo.setLastName(lastName);
    this.personalInfo.setAddress(address);
    Data.editClient(context, lastName, firstName, this.getEmail(), address,
            this.billingInfo.getCardNum(), this.billingInfo.getExpires());
  }

  /**
   * Set the <code>BillingInformation</code> of the <code>Client</code>.
   * 
   * @param cardNum The credit card number of the <code>Client</code>.
   * @param expires The expire date of the credit card
   */
  public void editBillingInfo(Context context, String cardNum, String expires) {
    this.billingInfo.setCardNum(cardNum);
    this.billingInfo.setExpires(expires);
    Data.editClient(context, this.getLastName(), this.getFirstName(), this.getEmail(),
            this.getAddress(), cardNum, expires);
  }

  /**
   * Replace the first name of the <code>Client</code> with the given new first name.
   * 
   * @param firstName The replacement first name of the <code>Client</code>.
   */
  public void editFirstName(Context context, String firstName) {
    this.personalInfo.setFirstName(firstName);
    Data.editClient(context, this.getLastName(), firstName, this.getEmail(), this.getAddress(),
        this.getCardNum(), this.getExpires());
  }

  /**
   * Replace the last name of the <code>Client</code> with the given new last name.
   * 
   * @param lastName The replacement last name of the <code>Client</code>.
   */
  public void editLastName(Context context, String lastName) {
    this.personalInfo.setLastName(lastName);
    Data.editClient(context, lastName, this.getFirstName(), this.getEmail(), this.getAddress(),
        this.getCardNum(), this.getExpires());

  }

  /**
   * Replace the address of the <code>Client</code> with the given new address.
   * 
   * @param address The replacement address of the <code>Client</code>.
   */
  public void editAddress(Context context, String address) {
    this.personalInfo.setAddress(address);
    Data.editClient(context, this.getLastName(), this.getFirstName(), this.getEmail(), address,
            this.getCardNum(), this.getExpires());
  }

  /**
   * Replace the credit card number of the <code>Client</code> with the given new number.
   * 
   * @param cardNum The replacement credit card number of the <code>Client</code>.
   */
  public void editCardNum(Context context, String cardNum) {
    this.billingInfo.setCardNum(cardNum);
    Data.editClient(context, this.getLastName(), this.getFirstName(), this.getEmail(),
            this.getAddress(), cardNum, this.getExpires());
  }

  /**
   * Replace the expiry date of the <code>Client</code> with the given new expiry date.
   * 
   * @param expires The replacement expiry date of the <code>Client</code>.
   */
  public void editExpires(Context context, String expires) {
    this.billingInfo.setExpires(expires);
    Data.editClient(context, this.getLastName(), this.getFirstName(), this.getEmail(),
            this.getAddress(), this.getCardNum(), expires);
  }

  /**
   * Get the first name of the <code>Client</code>.
   * 
   * @return The first name of the <code>Client</code>.
   */
  public String getFirstName() {
    return this.personalInfo.getFirstName();
  }

  /**
   * Get the last name of the <code>Client</code>.
   * 
   * @return The last name of the <code>Client</code>.
   */
  public String getLastName() {
    return this.personalInfo.getLastName();
  }

  /**
   * Get the address of the <code>Client</code>.
   * 
   * @return The address of the <code>Client</code>.
   */
  public String getAddress() {
    return this.personalInfo.getAddress();
  }

  /**
   * Get the email of the <code>Client</code>.
   * 
   * @return The email of the <code>Client</code>.
   */
  public String getEmail() {
    return this.personalInfo.getEmail();
  }

  /**
   * Get the credit card number of the <code>Client</code>.
   * 
   * @return The credit card number of the <code>Client</code>.
   */
  public String getCardNum() {
    return this.billingInfo.getCardNum();
  }

  /**
   * Get the expiry date of the <code>Client</code>.
   * 
   * @return The expiry date of the <code>Client</code>.
   */
  public String getExpires() {
    return this.billingInfo.getExpires();
  }

  /**
   * Book all the <code>Flight</code>s given the Itinerary.
   * 
   * @param itinerary The Itinerary that needs to be booked.
   */
  public void book(Context context, Itinerary itinerary) {
    this.bookedFlights.add(itinerary);
    Data.bookItinerary(context, this.getEmail(), itinerary);
  }

  /**
   * Select/Get the <code>Itinerary</code> in a given <code>List</code> of <code>Itinerary</code>s.
   * 
   * @param itineraries The <code>List</code> of <code>Itinerary</code>s that is chosen from.
   * @param position The position of the <code>Itinerary</code> in the <code>List</code> that is
   *        being selected.
   * @return The <code>Itinerary</code> that is selected.
   */
  public Itinerary select(List<Itinerary> itineraries, int position) {
    return itineraries.get(position);

  }

  /**
   * Return  <code>List</code> of <code>Itinerary</code>s that have been booked.
   * 
   * @return A <code>List</code> of <code>Itinerary</code>s that the Client booked.
   */
  public List<Itinerary> viewBooked() {
    return bookedFlights;
  }

}
