package com.audioord.dao;

/**
 * Custom DAO exception, extends {@link Exception},that provides information on a database access
 * error or other errors
 */
public class DAOException extends Exception {

  public DAOException(String message) {
    super(message);
  }

  public DAOException(String message, Throwable cause) {
    super(message, cause);
  }

  public DAOException(Throwable cause) {
    super(cause);
  }
}
