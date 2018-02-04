package com.audioord.dao.sql;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;

public abstract class DBPoolBase implements ConnectionSource {

  private static final Logger LOG = Logger.getLogger(DBPoolBase.class);

  /**
   * Driver class name
   */
  private String driverName;

  /**
   * Database username
   */
  private String user;

  /**
   * Database password
   */
  private String password;

  /**
   * Database connection url
   */
  private String url;

  DBPoolBase(String driverName, String user, String password, String url) {
    this.driverName = driverName;
    this.user = user;
    this.password = password;
    this.url = url;
  }

  protected abstract void initialize();

  // GET

  public String getDriverName() {
    return driverName;
  }

  public String getUser() {
    return user;
  }

  public String getPassword() {
    return password;
  }

  public String getUrl() {
    return url;
  }

  // UTIL

  protected Driver loadJdbcDriver() throws PoolException {
    Objects.requireNonNull(driverName);

    Driver d = null;

    // first load driver using forName
    try {
      Class<?> cls = Class.forName(driverName);
      d = (Driver) cls.newInstance();
    } catch (Exception e) {
      LOG.error(e);
    }

    // try load driver using driver manager
    if (d == null) {
      try {
        DriverManager.getDriver(driverName);
      } catch (SQLException e) {
        LOG.error(e);
      }
    }

    // fail, could not load
    if (d == null) {
      throw new PoolException("Missing driver: " + driverName);
    }

    return d;
  }

  protected Connection newConnection(Driver driver) throws SQLException {
    Objects.requireNonNull(driver, "Driver could not be null");

    Properties props = new Properties();
    props.setProperty("user", getUser());
    props.setProperty("password", getPassword());
    Connection connection = driver.connect(getUrl(), props);
    return new ConnectionWrapper(connection, this);
  }

  protected boolean validate() {
    if (driverName == null) {
      LOG.error("Missing driver name");
      return false;
    }

    if (user == null) {
      LOG.error("Missing user name");
      return false;
    }

    if (password == null) {
      LOG.error("Missing password");
    }

    if (url == null) {
      LOG.error("Missing database url");
      return false;
    }

    return true;
  }
}
