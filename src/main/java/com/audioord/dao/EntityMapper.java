package com.audioord.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface EntityMapper<T> {

  T parse(ResultSet rs) throws SQLException;
}
