package com.audioord.dao;

import com.audioord.dao.sql.ConnectionSource;
import com.audioord.dao.sql.DBPool;

import java.util.Objects;

public abstract class BaseEntityDao implements EntityDAO {

  private ConnectionSource connectionSource;

  public BaseEntityDao() {
    this.connectionSource = new DBPool();
  }

  public BaseEntityDao(ConnectionSource connectionSource) {
    this.connectionSource = Objects.requireNonNull(connectionSource);
  }

  public ConnectionSource getConnectionSource() {
    return connectionSource;
  }
}
