package com.audioord.dao.sql;

/** Connection source for DB */
public interface ConnectionSource {

  /**
   * Retrieves connection to database
   *
   * @return {@link java.sql.Connection}
   * @throws PoolException
   */
  java.sql.Connection getConnection() throws PoolException;

  /**
   * Allow to put connection back
   *
   * @param con {@link java.sql.Connection}
   */
  void returnConnection(java.sql.Connection con);

  /**
   *
   * @param con */
  void freeConnection(java.sql.Connection con);
}
