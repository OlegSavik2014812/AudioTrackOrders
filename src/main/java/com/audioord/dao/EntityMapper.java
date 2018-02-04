package com.audioord.dao;

import com.audioord.model.Entity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Simple ResultSet mapper allow to fill {@link Entity}
 */
public interface EntityMapper<T extends Entity> {

  /**
   * Parse ResultSet and put values in to {@link Entity}.
   *
   * @param rs ResultSet
   * @return {@link Entity} object
   * @throws SQLException {@link SQLException}
   * @throws DAOException {@link DAOException}
   */
  T parse(ResultSet rs) throws SQLException, DAOException;

  /**
   * fill {@link PreparedStatement} with {@link Entity} info
   *
   * @param st     {@link PreparedStatement}
   * @param entity {@link Entity}
   * @throws SQLException {@link SQLException}
   */
  void write(PreparedStatement st, T entity) throws SQLException;
}
