package com.audioord.dao;

import com.audioord.dao.sql.ConnectionSource;
import com.audioord.dao.sql.DBPool;
import com.audioord.model.Entity;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Objects;

public abstract class BaseEntityDao<E extends Entity<K>, K extends Serializable>
    implements EntityDAO<E, K> {

  private ConnectionSource connectionSource;

  public BaseEntityDao() {
    this.connectionSource = DBPool.getInstance();
  }

  public BaseEntityDao(ConnectionSource connectionSource) {
    this.connectionSource = Objects.requireNonNull(connectionSource);
  }

  public ConnectionSource getConnectionSource() {
    return connectionSource;
  }

  protected E getById(K id, EntityMapper<E> mapper, String sql) throws DAOException {
    Objects.requireNonNull(mapper);

    E obj = null;
    try (Connection con = connectionSource.getConnection();
        PreparedStatement st = con.prepareCall(sql)) {

      st.setObject(1, id);

      obj = mapper.parse(st.executeQuery());

    } catch (Exception e) {
      throw new DAOException(e);
    }

    return obj;
  }
}
