package com.audioord.dao;

import com.audioord.model.Entity;

import java.io.Serializable;

/**
 * Entity DAO interface, which provides implementation with additional methods
 */
public interface EntityDAO<E extends Entity<K>, K extends Serializable> {
  /**
   * Allows to find {@link Entity} by id
   *
   * @param id {@link Entity} id
   * @return {@link Entity} object
   * @throws DAOException {@link DAOException}
   */
  E getById(K id) throws DAOException;

  /**
   * Allows to update {@link Entity} object info
   *
   * @param entity {@link Entity}
   * @return updated {@link Entity}
   * @throws DAOException {@link DAOException}
   */
  E update(E entity) throws DAOException;

  /**
   * Allows to delete info about {@link Entity} using id
   *
   * @param id {@link Entity} id
   * @return if raw was deleted - true, otherwise - false
   * @throws DAOException {@link DAOException}
   */
  boolean delete(K id) throws DAOException;

  /**
   * Allows to create row, which have info of input {@link Entity} object
   *
   * @param entity {@link Entity}
   * @return if raw was created - true, otherwise - false
   * @throws DAOException {@link DAOException}
   */
  boolean create(E entity) throws DAOException;
}
