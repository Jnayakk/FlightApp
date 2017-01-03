package data;

import android.content.Context;
import android.content.res.AssetFileDescriptor;

import flight.Flight;
import flight.Itinerary;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * A Data class to deal with all the file manipulations.
 *
 * @author team_0575
 */
public class Data {

  /**
   * Get and return all the <code>User</code>'s username and password in the system.
   *
   * @return A <code>List</code> of all the <code>Client</code>'s username
   * and password in the system.
   */
  public static List<String> viewAllUsers(Context context) {
    List<String> usersInfo = new ArrayList<>();
    try {
      String line;
      InputStream inputStream = context.openFileInput("users.txt");
      if (inputStream != null) {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        while ((line = bufferedReader.readLine()) != null) {
          usersInfo.add(line);
        }
      }
    } catch (FileNotFoundException ex) {
      return usersInfo;
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return usersInfo;
  }

  /**
   * Get and return a given's <code>Client</code>'s informations.
   *
   * @param email The email/username of the <code>Client</code>.
   * @return A String of the <code>Client</code>'s informations.
   */
  public static String viewUser(Context context, String email) {
    try {
      String line;
      InputStream inputStream = context.openFileInput("users.txt");
      if ( inputStream != null ) {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        while ((line = bufferedReader.readLine()) != null) {
          String[] tokens = line.split(";");
          if (tokens[0].equals(email)) {
            return line;
          }
        }
      }
    } catch (FileNotFoundException ex) {
      return null;
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return null;
  }

  /**
   * Upload <code>User</code>(s) to the system given a file with all the information of a
   * <code>User</code>.
   *
   * @param fileName The path of the file of all the user information.
   */
  public static void uploadUser(Context context, String fileName) {
    String line;
    try {
      File file = new File(fileName);
      FileInputStream inputStream = new FileInputStream(file);
      if ( inputStream != null ) {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        while ((line = bufferedReader.readLine()) != null) {
          String[] tokens = line.split(";");
          if (Data.viewUser(context, tokens[0]) == null) {
            addUser(context, tokens[0], tokens[1]);
          } else {
            editUser(context, tokens[0], tokens[1]);
          }
        }
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  /**
   * Add a new <code>User</code> to the system with the given email and password.
   *
   * @param email The email of the <code>User</code>.
   * @param password The password of the <code>User</code>
   */
  public static void addUser(Context context, String email, String password) {
    try {
      OutputStreamWriter outputStreamWriter =
              new OutputStreamWriter(context.openFileOutput("users.txt", Context.MODE_APPEND));
      outputStreamWriter.write(email + ';' + password + "\n");
      outputStreamWriter.close();
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  /**
   * Edit all of the <code>User</code>'s informations.
   *
   * @param email The email of the <code>User</code>.
   * @param password The password of the <code>User</code>.
   */
  public static void editUser(Context context, String email, String password) {
    String newLine = email + ";" + password;
    String line;
    String fileContent = "";
    try {
      InputStream inputStream = context.openFileInput("users.txt");
      if ( inputStream != null ) {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        while ((line = bufferedReader.readLine()) != null) {
          String[] tokens = line.split(";");
          if (tokens[0].equals(email)) {
            fileContent += newLine + "\n";
          } else {
            fileContent += line + "\n";
          }
        }
      }
      OutputStreamWriter outputStreamWriter =
              new OutputStreamWriter(context.openFileOutput("users.txt", Context.MODE_PRIVATE));
      outputStreamWriter.write(fileContent);
      outputStreamWriter.close();
    } catch (FileNotFoundException ex) {
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  /**
   * Get and return all the <code>Client</code>'s informations in the system.
   *
   * @return A <code>List</code> of all the <code>Client</code>'s informations in the system.
   */
  public static List<String> viewAllClients(Context context) {
    List<String> usersInfo = new ArrayList<>();
    try {
      String line;
      InputStream inputStream = context.openFileInput("clients.txt");
      if (inputStream != null) {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        while ((line = bufferedReader.readLine()) != null) {
          usersInfo.add(line);
        }
      }
    } catch (FileNotFoundException ex) {
      return usersInfo;
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return usersInfo;
  }

  /**
   * Get and return a given's <code>Client</code>'s informations.
   *
   * @param email The email/username of the <code>Client</code>.
   * @return A String of the <code>Client</code>'s informations.
   */
  public static String viewClient(Context context, String email) {
    try {
      String line;
      InputStream inputStream = context.openFileInput("clients.txt");
      if ( inputStream != null ) {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        while ((line = bufferedReader.readLine()) != null) {
          String[] tokens = line.split(";");
          if (tokens[2].equals(email)) {
            return line;
          }
        }
      }
    } catch (FileNotFoundException ex) {
      return null;
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return null;
  }

  /**
   * Edit all of the <code>Client</code>'s informations.
   *
   * @param lastName The last name of the <code>Client</code>.
   * @param firstName The first name of the <code>Client</code>.
   * @param email The email of the <code>Client</code>.
   * @param address The address of the <code>Client</code>.
   * @param cardNum The credit card number of the <code>Client</code>.
   * @param expires The expiry date of the <code>Client</code>'s credit card.
   */
  public static void editClient(Context context, String lastName, String firstName, String email,
                                String address, String cardNum, String expires) {
    String newLine =
            lastName + ";" + firstName + ";" + email + ";" + address + ";" + cardNum + ";" + expires;
    String line;
    String fileContent = "";
    try {
      InputStream inputStream = context.openFileInput("clients.txt");
      if ( inputStream != null ) {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        while ((line = bufferedReader.readLine()) != null) {
          String[] tokens = line.split(";");
          if (tokens[2].equals(email)) {
            fileContent += newLine + "\n";
          } else {
            fileContent += line + "\n";
          }
        }
      }
      OutputStreamWriter outputStreamWriter =
              new OutputStreamWriter(context.openFileOutput("clients.txt", Context.MODE_PRIVATE));
      outputStreamWriter.write(fileContent);
      outputStreamWriter.close();
    } catch (FileNotFoundException ex) {
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  /**
   * Add a new <code>Client</code> to the system with the given last name, first name, email,
   * address, credit card number and expiry date.
   *
   * @param lastName The last name of the <code>Client</code>.
   * @param firstName The first name of the <code>Client</code>.
   * @param email The email of the <code>Client</code>.
   * @param address The address of the <code>Client</code>.
   * @param cardNum The credit card number of the <code>Client</code>.
   * @param expires The expiry date of the <code>Client</code>'s credit card.
   */
  public static void addClient(Context context, String lastName, String firstName, String email,
                               String address, String cardNum, String expires) {
    try {
      OutputStreamWriter outputStreamWriter =
              new OutputStreamWriter(context.openFileOutput("clients.txt", Context.MODE_APPEND));
      outputStreamWriter.write(lastName + ';' + firstName + ';' + email + ';' + address + ';'
              + cardNum + ';' + expires + "\n");
      outputStreamWriter.close();
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  /**
   * Upload <code>Client</code>(s) to the system given a file with all the information of a
   * <code>Client</code>.
   *
   * @param fileName The path of the file of all the client information.
   */
  public static void uploadClient(Context context, String fileName) {
    String line;
    try {
      File file = new File(fileName);
      FileInputStream inputStream = new FileInputStream(file);
      if ( inputStream != null ) {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        while ((line = bufferedReader.readLine()) != null) {
          String[] tokens = line.split(";");
          if (Data.viewClient(context, tokens[2]) == null) {
            addClient(context, tokens[0], tokens[1], tokens[2], tokens[3], tokens[4], tokens[5]);
          } else {
            editClient(context, tokens[0], tokens[1], tokens[2], tokens[3], tokens[4], tokens[5]);
          }
        }
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  /**
   * Get and return all the <code>Flight</code>'s information.
   *
   * @return A <code>List</code> of <code>Flight</code>s.
   */
  public static List<Flight> viewAllFlights(Context context) {
    List<Flight> flightsInfo = new ArrayList<>();
    try {
      String line;
      InputStream inputStream = context.openFileInput("flights.txt");
      if (inputStream != null) {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        while ((line = bufferedReader.readLine()) != null) {
          String[] tokens = line.split(";");
          Flight flight = new Flight(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4], tokens[5],
                  Double.parseDouble(tokens[6]), Integer.parseInt(tokens[7]));
          flightsInfo.add(flight);
        }
      }
    } catch (FileNotFoundException ex) {
      return flightsInfo;
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return flightsInfo;
  }

  /**
   * Get and return the <code>Flight</code> given it's flight number.
   *
   * @param flightNum The flight number of the <code>Flight</code>.
   * @return A <code>Flight</code> in the system.
   */
  public static Flight viewFlight(Context context, String flightNum) {
    try {
      String line;
      InputStream inputStream = context.openFileInput("flights.txt");
      if ( inputStream != null ) {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        while ((line = bufferedReader.readLine()) != null) {
          String[] tokens = line.split(";");
          if (tokens[0].equals(flightNum)) {
            Flight flight = new Flight(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4],
                    tokens[5], Double.parseDouble(tokens[6]), Integer.parseInt(tokens[7]));
            return flight;
          }
        }
      }
    } catch (FileNotFoundException ex) {
      return null;
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return null;
  }

  /**
   * Add a new <code>Flight</code> to the system given it's flight number, departure date, arrival
   * date, airline, origin, destination, and cost.
   *
   * @param flightNum The flight number of the <code>Flight</code>.
   * @param departureTime The departure time of the <code>Flight</code>.
   * @param arrivalTime The arrival time of the <code>Flight</code>.
   * @param airline The airline of the <code>Flight</code>.
   * @param origin The origin of the <code>Flight</code>.
   * @param destination The destination of the <code>Flight</code>.
   * @param cost The cost of the<code>Flight</code>.
   */
  public static void addFlight(Context context, String flightNum, String departureTime,
                               String arrivalTime, String airline, String origin,
                               String destination, double cost) {
    try {
      OutputStreamWriter outputStreamWriter =
              new OutputStreamWriter(context.openFileOutput("flights.txt", Context.MODE_APPEND));
      outputStreamWriter.write(flightNum + ';' + departureTime + ';' + arrivalTime + ';'
              + airline + ';' + origin + ';' + destination + ';'
              + String.format("%.2f", cost) + '\n');
      outputStreamWriter.close();
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  /**
   * Upload <code>Flight</code>(s) to the system given a file with all the information of a
   * <code>Flight</code>.
   *
   * @param fileName The path of the file of all the flight information.
   */
  public static void uploadFlight(Context context, String fileName) {
    String line;
    try {
      File file = new File(fileName);
      FileInputStream inputStream = new FileInputStream(file);
      if (inputStream != null) {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        while ((line = bufferedReader.readLine()) != null) {
          String[] tokens = line.split(";");
          addFlight(context, tokens[0], tokens[1], tokens[2], tokens[3], tokens[4], tokens[5],
                  Double.parseDouble(tokens[6]));
        }
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  /**
   * Get and return all then booked <code>Itinerary</code>s for the given <code>User</code>.
   *
   * @param email The email/username of the <code>User</code>.
   * @return A <code>List</code> if <code>Itinerary</code>s that the <code>User</code> booked.
   */
  public static List<Itinerary> viewBookedItineraries(Context context, String email) {
    String line;
    List<Itinerary> itineraryList = new ArrayList<>();
    try {
      InputStream inputStream = context.openFileInput("booked.txt");
      if ( inputStream != null ) {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        while ((line = bufferedReader.readLine()) != null) {
          String[]tokens = line.split(";");
          if (tokens[0].equals(email)) {
            Itinerary itinerary = new Itinerary();
            for (int i = 1; i < tokens.length; i++) {
              Flight flight = Data.viewFlight(context, tokens[i]);
              itinerary.addFlight(flight);
            }
            itineraryList.add(itinerary);
          }
        }
      }
    } catch (FileNotFoundException ex) {
      return itineraryList;
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return itineraryList;
  }

  /**
   * Book the given <code>Itinerary</code> for the <code>User</code>
   *
   * @param email The email/username of the <code>User</code>.
   * @param itinerary The <code>Itinerary</code> that the <code>User</code> wants to book.
   */
  public static void bookItinerary(Context context, String email, Itinerary itinerary) {
    try {
      String bookInfo = email;
      for (Flight flight : itinerary.getFlights()) {
        bookInfo += (";" + flight.getFlightNum());
      }
      OutputStreamWriter outputStreamWriter =
              new OutputStreamWriter(context.openFileOutput("booked.txt", Context.MODE_APPEND));
      outputStreamWriter.write(bookInfo + "\n");
      outputStreamWriter.close();
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  public static void editFlight(Context context,String flightNum, String departureDateTime,
                                String arrivalDateTime, String airline, String origin,
                                String destination, String cost, String seatNum) {
    String newLine =
            flightNum + ";" + departureDateTime + ";" + arrivalDateTime + ";" + airline + ";"
                    + origin + ";" + destination + ";" + cost + ";" + seatNum;
    String line;
    String fileContent = "";
    try {
      InputStream inputStream = context.openFileInput("flights.txt");
      if (inputStream != null) {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        while ((line = bufferedReader.readLine()) != null) {
          String[] tokens = line.split(";");
          if (tokens[0].equals(flightNum)) {
            fileContent += newLine + "\n";
          } else {
            fileContent += line + "\n";
          }
        }
      }
      OutputStreamWriter outputStreamWriter =
              new OutputStreamWriter(context.openFileOutput("flights.txt", Context.MODE_PRIVATE));
      outputStreamWriter.write(fileContent);
      outputStreamWriter.close();
    } catch (FileNotFoundException ex) {
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

}

