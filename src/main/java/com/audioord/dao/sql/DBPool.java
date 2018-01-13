package com.audioord.dao.sql;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;

public class DBPool implements ConnectionSource {

  private static final Logger LOG = Logger.getLogger(DBPool.class);

  /** Pool max connections number */
  private static final int SIZE = 15;

  /** available connections */
  private BlockingQueue<Connection> free;

  /** used connections */
  private BlockingQueue<Connection> used;

  /** Database connection driver */
  private Driver driver;

  /** Driver class name */
  private String driverName;

  /** Database username */
  private String user;

  /** Database password */
  private String password;

  /** Database connection url */
  private String url;

  private DBPool() {
    initConfig();
  }

  private void initConfig() {
    driverName = System.getProperty("db.driver");
    user = System.getProperty("db.user");
    password = System.getProperty("db.password");
    url = System.getProperty("db.url");
  }

  private Driver getJdbcDriver() {
    String driverClass = System.getProperty("db.driver");
    if (driverClass == null || driverClass.isEmpty()) {
      throw new RuntimeException("Missing config db.driver");
    }

    Driver d = null;

    // first load driver using forName
    try {
      Class<?> cls = Class.forName(driverClass);
      d = (Driver) cls.newInstance();
    } catch (Exception e) {
      LOG.error(e);
    }

    // try load driver using driver manager
    if (d == null) {
      try {
        DriverManager.getDriver(driverClass);
      } catch (SQLException e) {
        LOG.error(e);
      }
    }

    // fail, could not load
    if (d == null) {
      throw new RuntimeException("Missing driver");
    }

    return d;
  }

  @Override
  public Connection getConnection() throws SQLException, InterruptedException {
    return null;
  }

  @Override
  public void returnConnection(Connection con) {}

  @Override
  public void freeConnection(Connection con) {}
}
