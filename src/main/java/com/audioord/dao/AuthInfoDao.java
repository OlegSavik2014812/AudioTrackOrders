package com.audioord.dao;

import com.audioord.model.auth.AuthInfo;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthInfoDao extends BaseEntityDao<AuthInfo, String> {
  public static final String SQL_GET_AUTH_BY_ID = "SELECT";

  private EntityMapper<AuthInfo> mapper = new EntityMapper<AuthInfo>() {
    @Override
    public AuthInfo parse(ResultSet rs) throws SQLException {
      AuthInfo authInfo = new AuthInfo(rs.getString(1), rs.getString(2));
      return authInfo;
    }
  };


  @Override
  public AuthInfo getById(String id) throws DAOException {
    return super.getById(id, mapper, SQL_GET_AUTH_BY_ID);
  }

  @Override
  public AuthInfo update(AuthInfo entity) {
    return null;
  }

  @Override
  public boolean delete(String id) {
    return false;
  }

  @Override
  public boolean create(AuthInfo entity) {
    return false;
  }
}
