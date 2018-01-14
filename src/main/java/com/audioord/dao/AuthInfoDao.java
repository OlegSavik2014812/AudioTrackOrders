package com.audioord.dao;

import com.audioord.model.auth.AuthInfo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class AuthInfoDao extends BaseEntityDao<AuthInfo, String> {

  private static final String SQL_GET_AUTH_BY_ID =
  "SELECT Password, UserName  FROM AuthInfo WHERE UserName = ?";
  private static final String SQL_UPDATE_AUTH_BY_ID =
  "UPDATE AuthInfo SET UserName = ?, Password = ? WHERE UserName = ?";

  private final EntityMapper<AuthInfo> mapper =
  new EntityMapper<AuthInfo>() {
    @Override
    public AuthInfo parse(ResultSet rs) throws SQLException {
      AuthInfo authInfo = new AuthInfo(rs.getString(1), rs.getString(2));
      return authInfo;
    }

    @Override
    public void write(PreparedStatement st, AuthInfo entity) throws SQLException {
      st.setString(1, entity.getUserName());
      st.setString(2, entity.getPassword());
    }
  };

  @Override
  public AuthInfo getById(String id) throws DAOException {
    return getById(id, mapper, SQL_GET_AUTH_BY_ID);
  }

  @Override
  public AuthInfo update(AuthInfo authInfo) throws DAOException {
    super.update(authInfo, mapper, SQL_UPDATE_AUTH_BY_ID);
    return getById(authInfo.getId());
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
