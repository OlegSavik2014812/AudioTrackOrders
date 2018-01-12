package com.audioord.dao.sql;

import java.sql.Connection;
import java.sql.SQLException;

public class DBPool implements ConnectionSource {

  // TODO:

  public DBPool() {}

  @Override
  public Connection getConnection() throws SQLException {
    return null;
  }

  @Override
  public void returnConnection(Connection con) {}

  @Override
  public void freeConnection(Connection con) {}
}
