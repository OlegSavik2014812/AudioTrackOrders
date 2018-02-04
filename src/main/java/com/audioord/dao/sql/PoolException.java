package com.audioord.dao.sql;

/**
 * Custom pool exception, extends {@link Exception},that provides information on a connection pool
 * error or other errors
 */
public class PoolException extends Exception {

  public PoolException(String message) {
    super(message);
  }

  public PoolException(String message, Throwable cause) {
    super(message, cause);
  }

  public PoolException(Throwable cause) {
    super(cause);
  }

}
