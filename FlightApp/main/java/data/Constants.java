package data;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.concurrent.TimeUnit;

/**
 * Constants used in the Flight Booking Application.
 * 
 * @author team_0575
 */
public class Constants {

  public static final long MIN_LAYOVER = 30 * 60; // 30 min in seconds
  public static final long MAX_LAYOVER = 6 * 60 * 60; // 6 hours in seconds

  // The format of the dates in this flight booking app.
  public static final DateFormat DATE_ONLY_FORMAT = new SimpleDateFormat ("yyyy-MM-dd");

  // The format of the date and time in this flight booking app.
  public static final DateFormat DATE_TIME_FORMAT = new SimpleDateFormat ("yyyy-MM-dd HH:mm");

  // The format of the time in this flight booking app.
  public static final DateFormat TIME_FORMAT = new SimpleDateFormat ("HH:mm");

  public  static  final String MAIN_KEY = "key";
}
