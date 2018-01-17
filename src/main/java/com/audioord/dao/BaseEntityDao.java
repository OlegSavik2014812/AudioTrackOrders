package com.audioord.dao;

import com.audioord.dao.sql.ConnectionSource;
import com.audioord.dao.sql.DBPool;
import com.audioord.dao.sql.PoolException;
import com.audioord.model.Entity;
import org.apache.log4j.Logger;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

  protected boolean remove(K id, String sql) throws DAOException {
    Objects.requireNonNull(id, "Param Id could not be null");

    boolean isRemoved = false;

    try (Connection con = getConnectionSource().getConnection();
         PreparedStatement st = con.prepareCall(sql)) {

      LOG.debug(String.format("Executing query [%s] \n with params %s", sql, id));
      int i = st.executeUpdate(sql);

      isRemoved = i > 0;

    } catch (SQLException | PoolException e) {
      throw new DAOException(e);
    }

    return isRemoved;
  }

  protected boolean update(E entity, EntityMapper<E> mapper, String sql) throws DAOException {
    Objects.requireNonNull(entity, "Param Entity could not be null");
    Objects.requireNonNull(mapper, "Param Mapper could not be null");

    boolean isCreated = false;

    try (Connection con = getConnectionSource().getConnection();
         PreparedStatement st = con.prepareCall(sql)) {

      mapper.write(st, entity);
      LOG.debug(
      String.format("Executing query [%s] \n with entity %s", sql, String.valueOf(entity)));

      int i = st.executeUpdate();
      isCreated = i > 0;

    } catch (SQLException | PoolException e) {
      throw new DAOException(e);
    }
    return isCreated;
  }

  List<E> findAll(EntityMapper<E> mapper, String sql, Object... params) throws DAOException {
    Objects.requireNonNull(mapper, "Param Mapper could not be null");

    List<E> list = new ArrayList<>();

    try (Connection con = connectionSource.getConnection();
         PreparedStatement st = con.prepareCall(sql)) {

      if (params != null && params.length > 0) {
        for (int i = 0; i < params.length; i++) {
          Object prm = params[i];
          st.setObject(i + 1, prm);
        }
      }
      LOG.debug(
      String.format("Executing query [%s] \n with params %s", sql, Arrays.toString(params)));

      ResultSet rs = st.executeQuery();
      while (rs.next()) {
        E obj = mapper.parse(rs);
        list.add(obj);
      }

    } catch (PoolException | SQLException e) {
      throw new DAOException(e);
    }
    return list;
  }

  protected E find(EntityMapper<E> mapper, String sql, Object... params) throws DAOException {
    Objects.requireNonNull(mapper);

    E obj = null;

    try (Connection con = connectionSource.getConnection();
         PreparedStatement st = con.prepareCall(sql)) {
      if (params != null && params.length > 0) {
        for (int i = 0, length = params.length; i < length; i++) {
          Object prm = params[i];
          st.setObject(i + 1, prm);
        }
      }
      LOG.debug(
      String.format("Executing query [%s] \n with params %s", sql, Arrays.toString(params)));
      ResultSet rs = st.executeQuery();
      if (rs.next()) {
        obj = mapper.parse(rs);
      }
    } catch (SQLException | PoolException e) {
      throw new DAOException(e);
    }
    return obj;
  }

  protected E getById(K id, EntityMapper<E> mapper, String sql) throws DAOException {
    return find(mapper, sql, id);
  }

  protected int count(String sql) {
    int count = 0;
    Connection connection = null;
    try {
      connection = connectionSource.getConnection();
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery(sql);
      if (resultSet.next()) {
        count = resultSet.getInt(1);
      }
    } catch (SQLException | PoolException e) {
      e.printStackTrace();
    }
    return count;
  }
}
