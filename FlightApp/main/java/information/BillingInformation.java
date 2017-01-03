package information;

import data.Constants;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Billing Information Class used to store Billing Info.
 * 
 * @author team_0575
 *
 */
public class BillingInformation implements Serializable {

  private int cardNum;
  private Date expires;

  /**
   * Creates and initializes BillingInformation object.
   * 
   * @param cardNum Card Number of BillingInformation object
   * @param expires Expire date of card in Billing Information object
   */
  public BillingInformation(String cardNum, String expires) {
    this.cardNum = Integer.parseInt(cardNum);
    try {
      this.expires = Constants.DATE_ONLY_FORMAT.parse(expires);
    } catch (ParseException ex) {
      ex.printStackTrace();
    }
  }

  /**
   * Returns card number of BillingInformation object.
   * 
   * @return card number of B.I.
   */
  public String getCardNum() {
    return String.valueOf(cardNum);
  }

  /**
   * Sets new cardNumber to BillingInformation object.
   * 
   * @param cardNum Card number of B.I. object
   */
  public void setCardNum(String cardNum) {
    this.cardNum = Integer.parseInt(cardNum);
  }

  /**
   * Returns the expire date of the card in BillingInformation object.
   * 
   * @return the expire date of B.I.
   */
  public String getExpires() {
    System.out.println(expires.toString());
    return Constants.DATE_ONLY_FORMAT.format(expires);
  }

  /**
   * Sets new expire date of the card in B.I. object.
   * 
   * @param expires expire date of the card in B.I. object
   */
  public void setExpires(String expires) {
    try {
      this.expires = Constants.DATE_ONLY_FORMAT.parse(expires);
    } catch (ParseException ex) {
      ex.printStackTrace();
    }
  }

  @Override
  public String toString() {
    return "BillingInformation [cardNum=" + cardNum + ", expires=" + expires + "]";
  }

}
