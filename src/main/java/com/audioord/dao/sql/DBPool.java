package com.audioord.dao.sql;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.Driver;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class DBPool extends DBPoolBase {

  private static final Logger LOG = Logger.getLogger(DBPool.class);

  private static final ReentrantLock LOCK = new ReentrantLock();

  /** Pool max connections number */
  private static final int SIZE = 15;

  /** Pool state */
  private static AtomicBoolean isInitialized = new AtomicBoolean(false);

  /** current pool instance * */
  private static DBPool instance;

  /** available connections */
  private BlockingQueue<Connection> free;

  /** used connections */
  private BlockingQueue<Connection> used;

  /** Database connection driver */
  private Driver driver;

  private DBPool() {
    super(
        System.getProperty("db.driver"),
        System.getProperty("db.username"),
        System.getProperty("db.password"),
        System.getProperty("db.url"));
  }

  public static ConnectionSource getInstance() {
    if (!isInitialized.get()) {

      try {
        LOCK.lock();
        if (!isInitialized.get()) {
          instance = new DBPool();
          instance.initialize();
          isInitialized.set(true);
        }
      } finally {
        LOCK.unlock();
      }
    }
    return instance;
  }

  @Override
  protected void initialize() {
    if (!validate()) {
      throw new RuntimeException("Failed to initialize DBPool");
    }

    try {

      free = new ArrayBlockingQueue<>(SIZE);
      used = new ArrayBlockingQueue<>(SIZE);

      driver = super.loadJdbcDriver();

      for (int i = 0; i < SIZE; i++) {
        Connection connection = newConnection(driver);
        free.add(connection);
      }

    } catch (Exception e) {
      LOG.error(e);
    }
  }

  @Override
  public Connection getConnection() throws InterruptedException {
    Connection con = free.poll(1, TimeUnit.SECONDS);
    used.put(con);
    return con;
  }

  @Override
  public void returnConnection(Connection con) {
    if (con == null) {
      LOG.error("Connection could not be null");
      return;
    }

    free.add(con);
    used.remove(con);
  }

  @Override
  public void freeConnection(Connection con) {
    free.remove(con);
  }
}
