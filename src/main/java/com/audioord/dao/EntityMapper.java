package com.audioord.dao;

import com.audioord.model.Entity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** Simple ResultSet mapper allow to fill {@link Entity} */
public interface EntityMapper<T extends Entity> {

  /**
   * Parse ResultSet and put values in to Entity.
   *
   * @param rs ResultSet
   * @return {@link Entity} object
   * @throws SQLException in case of nay issues
   */
  T parse(ResultSet rs) throws SQLException;

  void write(PreparedStatement st, T entity) throws SQLException;
}
