package com.audioord.dao;

import com.audioord.dao.sql.ConnectionSource;
import com.audioord.dao.sql.DBPool;
import com.audioord.dao.sql.PoolException;
import com.audioord.model.Entity;
import org.apache.log4j.Logger;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Objects;

public abstract class BaseEntityDao<E extends Entity<K>, K extends Serializable>
    implements EntityDAO<E, K> {

  private static final Logger LOG = Logger.getLogger(BaseEntityDao.class);

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

  protected E find(EntityMapper<E> mapper, String sql, Object... params) throws DAOException {
    Objects.requireNonNull(mapper);

    E obj = null;

    try (Connection con = connectionSource.getConnection();
        PreparedStatement st = con.prepareCall(sql)) {

      if (params != null && params.length > 0) {
        for (int i = 0, length = params.length; i < length; i++) {
          Object prm = params[i];
          st.setObject(i, prm);
        }
      }

      LOG.debug(
          String.format("Executing query [%s] \n with params %s", sql, Arrays.toString(params)));
      obj = mapper.parse(st.executeQuery());

    } catch (SQLException | PoolException e) {
      throw new DAOException(e);
    }

    return obj;
  }

  protected E getById(K id, EntityMapper<E> mapper, String sql) throws DAOException {
    return find(mapper, sql, id);
  }
}
