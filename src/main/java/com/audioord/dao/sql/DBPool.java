package com.audioord.dao.sql;

import com.audioord.web.http.StartServletListener;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public final class DBPool extends DBPoolBase {

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

  /**
   * New DB pool instance that used system props that has been initiated on application startup
   * {@link StartServletListener}
   */
  private DBPool() {
    super(
        System.getProperty("db.driver"),
        System.getProperty("db.username"),
        System.getProperty("db.password"),
        System.getProperty("db.url"));
  }

  /**
   * Thread save pool instance creation, we need to create it once.
   *
   * @return {@link ConnectionSource} instance
   */
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

  /** Internal pool load */
  @Override
  protected void initialize() {
    if (!validate()) {
      throw new RuntimeException("Failed to initialize DBPool");
    }

    try {

      free = new ArrayBlockingQueue<>(SIZE);
      used = new ArrayBlockingQueue<>(SIZE);

      /* Database connection driver */
      Driver driver = super.loadJdbcDriver();

      for (int i = 0; i < SIZE; i++) {
        Connection connection = super.newConnection(driver);
        free.add(connection);
      }

    } catch (SQLException e) {
      LOG.error(e);
    }
  }

  /**
   * Provides available connection from the pool.
   *
   * @return {@link Connection}
   * @throws PoolException in case of no connections
   */
  @Override
  public Connection getConnection() throws PoolException {
    Connection con = null;
    try {
      con = free.poll(1, TimeUnit.SECONDS);
      used.put(con);
    } catch (InterruptedException e) {
      throw new PoolException(e);
    }

    return con;
  }

  /**
   * Return connection back in the pool of free connections
   *
   * @param con {@link Connection}
   */
  @Override
  public void returnConnection(Connection con) {
    if (con == null) {
      LOG.error("Connection could not be null");
      return;
    }

    free.add(con);
    used.remove(con);
  }

  /**
   * Removes connection from the pool, only free connections can be removed.
   *
   * @param con {@link Connection}
   */
  @Override
  public void freeConnection(Connection con) {
    Objects.requireNonNull(con);

    free.remove(con);

    try {
      if (!con.isClosed()) con.close();
    } catch (SQLException ignored) {
    }
  }
}
