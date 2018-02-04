package com.audioord.dao.sql;

import java.sql.Connection;


/**
 * Connection source for DB
 */
public interface ConnectionSource {

  /**
   * Retrieves connection to database
   *
   * @return {@link java.sql.Connection}
   * @throws PoolException {@link PoolException}
   */
  java.sql.Connection getConnection() throws PoolException;

  /**
   * Allow to put connection back
   *
   * @param con {@link java.sql.Connection}
   */
  void returnConnection(java.sql.Connection con);

  /**
   * Removes connection from connection source
   *
   * @param con {@link Connection}
   */
  void freeConnection(java.sql.Connection con);
}
