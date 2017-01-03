package information;

import java.io.Serializable;

/**
 * Personal Information Class used to store Personal Info.
 * 
 * @author team_0575
 *
 */
public class PersonalInformation implements Serializable {

  private String firstName;
  private String lastName;
  private String email;
  private String address;

  /**
   * Creates and initializes PersonalInformation object.
   * 
   * @param firstName Stores First Name
   * @param lastName Stores Last Name
   * @param email Stores email
   * @param address Stores address
   */
  public PersonalInformation(String lastName, String firstName, String email, String address) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.address = address;
  }

  /**
   * Returns first name of Personal Information object.
   * 
   * @return first name
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * Sets a new first name to the Personal Information object.
   * 
   * @param firstName first name P.I. object
   */
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /**
   * Returns the last name of Personal Information object.
   * 
   * @return last name of P.I. object
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * Sets new last name of Personal Information object.
   * 
   * @param lastName of P.I. object
   */
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  /**
   * Returns the email of P.I. object
   * 
   * @return email of P.I. object
   */
  public String getEmail() {
    return email;
  }

  /**
   * Returns the address of P.I. object.
   * 
   * @return the address of P.I. object
   */
  public String getAddress() {
    return address;
  }

  /**
   * Sets new address to P.I. object.
   * 
   * @param address of P.I. object
   */
  public void setAddress(String address) {
    this.address = address;
  }

  @Override
  public String toString() {
    return "PersonalInformation [firstName=" + firstName + ", lastName=" + lastName + ", email="
        + email + ", address=" + address + "]";
  }
}
