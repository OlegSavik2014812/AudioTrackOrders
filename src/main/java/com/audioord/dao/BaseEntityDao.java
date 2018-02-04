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

/**
 * abstract generic BaseEntity DAO class, implements {@link EntityDAO}
 */
public abstract class BaseEntityDao<E extends Entity<K>, K extends Serializable> implements EntityDAO<E, K> {

  private static final Logger LOG = Logger.getLogger(BaseEntityDao.class);

  private ConnectionSource connectionSource;

  /**
   * BaseEntityDao constructor,which determines what will be the source of connections
   * in this case the source of connections is DataBasePool, which is initialized once
   */
  public BaseEntityDao() {
    this.connectionSource = DBPool.getInstance();
  }

  /**
   * Additional BaseEntityDao constructor, which accepts as an input parameter the source of the connections
   *
   * @param connectionSource source of the connections
   */
  public BaseEntityDao(ConnectionSource connectionSource) {
    this.connectionSource = Objects.requireNonNull(connectionSource);
  }

  /**
   * Allows to get source of connections
   *
   * @return {@link ConnectionSource}
   */
  public ConnectionSource getConnectionSource() {
    return connectionSource;
  }

  /**
   * Allows to remove raw from database using parametrized id and input SQL query
   * also it checks input id to null
   * removeMethod gets connection from {@link ConnectionSource#getConnection()}, creates {@link PreparedStatement}
   * and fills it with input id
   * execution of statement returns number of affected rows
   * if it was affected at least one row, it's returns true,otherwise false
   *
   * @param id  parametrized input id
   * @param sql SQL query string
   * @return if the number of changed rows is greater than 0 - true, otherwise - false
   * @throws DAOException {@link DAOException}
   */
  protected boolean remove(K id, String sql) throws DAOException {
    Objects.requireNonNull(id, "Param Id could not be null");

    boolean isRemoved = false;

    try (Connection con = getConnectionSource().getConnection();
         PreparedStatement st = con.prepareCall(sql)) {

      LOG.debug(String.format("Executing query [%s] \n with params %s", sql, id));
      st.setObject(1, id);
      int i = st.executeUpdate();

      isRemoved = i > 0;

    } catch (SQLException | PoolException e) {
      throw new DAOException(e);
    }

    return isRemoved;
  }

  /**
   * Allows to get id of {@link Entity} from database using {@link Entity} object, {@link EntityMapper}
   * and SQL query string
   * createMethod gets connection from {@link ConnectionSource#getConnection()}, creates {@link PreparedStatement}
   * calls {@link EntityMapper#write(PreparedStatement, Entity)}, which fills {@link PreparedStatement} with info
   * of {@link Entity}
   * execute this statement and assign to {@link ResultSet}
   *
   * @param entity {@link Entity}
   * @param mapper {@link EntityMapper}
   * @param sql    SQL query string
   * @return {@link Entity} id
   * @throws DAOException {@link DAOException}
   */

  protected K create(E entity, EntityMapper<E> mapper, String sql) throws DAOException {
    K id = null;
    try (Connection con = connectionSource.getConnection();
         PreparedStatement st = con.prepareCall(sql)) {

      mapper.write(st, entity);
      LOG.debug(String.format("Executing query [%s] \n with entity %s", sql, String.valueOf(entity)));
      st.executeUpdate();
      ResultSet rs = st.getGeneratedKeys();
      if (rs.next()) {
        return (K) Long.valueOf(rs.getLong(1));
      }

    } catch (SQLException | PoolException e) {
      throw new DAOException(e);
    }

    return id;
  }

  /**
   * Allows to update(create) raw from database using input{@link Entity} object, input {@link EntityMapper}
   * and SQL query string
   * also it checks input {@link Entity} and {@link EntityMapper} to null
   * updateMethod gets connection from {@link ConnectionSource#getConnection()}, creates {@link PreparedStatement}
   * calls {@link EntityMapper#write(PreparedStatement, Entity)}, which fills {@link PreparedStatement} with params
   * of {@link Entity}
   * execution of statement returns number of affected rows
   * if it was affected at least one row, it's returns true,otherwise false
   *
   * @param entity {@link Entity} object
   * @param mapper {@link EntityMapper}
   * @param sql    update(create) SQL query string
   * @return if the number of changed rows is greater than 0 - true, otherwise false
   * @throws DAOException {@link DAOException}
   */
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

  /**
   * Allows to get {@link List} of {@link Entity} from database using input {@link EntityMapper}
   * ,input SQL query string and {@link List} of parametrized id
   * also it checks input {@link EntityMapper} to null
   * getByIdsMethod gets connection from {@link ConnectionSource#getConnection()}, creates {@link PreparedStatement}
   * fills {@link PreparedStatement} with input ids, execute this statement and assign to {@link ResultSet}
   * Further in the cycle there is a creation of {@link Entity} objects and addition to {@link List} of {@link Entity}
   *
   * @param mapper {@link EntityMapper}
   * @param sql    get SQL query string
   * @param ids    {@link List} of ids
   * @return {@link List} of {@link Entity} objects
   * @throws DAOException {@link DAOException}
   */
  protected List<E> getByIds(EntityMapper<E> mapper, String sql, List<K> ids) throws DAOException {
    Objects.requireNonNull(mapper, "Param Mapper could not be null");

    List<E> list = new ArrayList<>();

    try (Connection con = connectionSource.getConnection();
         PreparedStatement st = con.prepareCall(sql)) {

      for (int i = 0; i < ids.size(); i++) {
        st.setObject(i + 1, ids.get(i));
      }

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

  /**
   * Allows to get {@link List} of {@link Entity} from database using input SQL query string,
   * input {@link EntityMapper} and array of {@link Object} params
   * also it checks input {@link EntityMapper} to null
   * findAllMethod gets connection from {@link ConnectionSource#getConnection()}, creates {@link PreparedStatement}
   * checks array to null and array size
   * fills {@link PreparedStatement} with input params,execute this statement and assign to {@link ResultSet}
   * Further in the cycle there is a creation of {@link Entity} objects and addition to {@link List} of {@link Entity}
   *
   * @param mapper {@link EntityMapper}
   * @param sql    SQL query string
   * @param params array of {@link Object}
   * @return {@link List} of {@link Entity}
   * @throws DAOException {@link DAOException}
   */
  protected List<E> findAll(EntityMapper<E> mapper, String sql, Object... params) throws DAOException {
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

  /**
   * Allows to get {@link Entity} from database using input SQL query string,
   * input {@link EntityMapper} and array of {@link Object} params
   * also it checks input {@link EntityMapper} to null
   * findMethod gets connection from {@link ConnectionSource#getConnection()}, creates {@link PreparedStatement}
   * checks array of {@link Object} to null and array size
   * fills {@link PreparedStatement} with input params,execute this statement and assign to {@link ResultSet}
   * Further there is a creation of {@link Entity} object
   *
   * @param mapper {@link EntityMapper}
   * @param sql    SQL query string
   * @param params array of {@link Object}
   * @return {@link Entity} object
   * @throws DAOException {@link DAOException}
   */
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

  protected int countAll(String sql) throws DAOException {
    int count = 0;

    try (Connection con = connectionSource.getConnection();
         Statement st = con.createStatement()) {

      ResultSet resultSet = st.executeQuery(sql);
      if (resultSet.next()) {
        count = resultSet.getInt(1);
      }
    } catch (SQLException | PoolException e) {
      throw new DAOException(e);
    }
    return count;
  }

  protected int countAll(String sql, Object... params) throws DAOException {
    int count = 0;
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
        count = rs.getInt(1);
      }
    } catch (SQLException | PoolException e) {
      throw new DAOException(e);
    }
    return count;
  }
}
