package com.audioord.utils;


import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Simple util class, used to perform operations on a date
 */

public final class DateUtil {

  private static final Logger LOG = Logger.getLogger(DateUtil.class);
  private final static SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");

  private DateUtil() {
    super();
  }

  /**
   * adds to the input date the number of days and returns it
   *
   * @param dateTo input date
   * @param days   number of days
   * @return formatted date
   */
  public static Date addDays(Date dateTo, int days) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(dateTo);
    cal.add(Calendar.DATE, days);
    return cal.getTime();
  }

  /**
   * Parse date from String type to Date type using formatter of {@link DateUtil}
   *
   * @param str parsed string
   * @return formatted date
   */
  public static Date parseDate(String str) {
    return parseDate(str, formatter);
  }

  /**
   * Parse date from String type to Date type using input formatter
   *
   * @param str    parsed string
   * @param format input formatter
   * @return formatted date
   */
  public static Date parseDate(String str, SimpleDateFormat format) {
    if (str == null || str.isEmpty()) {
      return null;
    }
    try {
      return format.parse(str);
    } catch (ParseException e) {
      LOG.error(e);
    }
    return null;
  }
}
