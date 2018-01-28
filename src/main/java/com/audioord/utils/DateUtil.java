package com.audioord.utils;


import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class DateUtil {

  private static final Logger LOG = Logger.getLogger(DateUtil.class);
  private final static SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");

  private DateUtil() {
    super();
  }

  public static Date parseDate(String str) {
    return parseDate(str, formatter);
  }

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
