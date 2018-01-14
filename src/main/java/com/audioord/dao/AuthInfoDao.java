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

  private static final String SQL_DELETE_AUTH_BY_ID = "DELETE FROM AuthInfo WHERE UserName=?";

  private static final String SQL_CREATE_AUTH =
      "INSERT INTO AuthInfo (UserName, Password) VALUES (?, ?)";

  private final EntityMapper<AuthInfo> mapper =
      new EntityMapper<AuthInfo>() {
        @Override
        public AuthInfo parse(ResultSet rs) throws SQLException {
          return new AuthInfo(rs.getString(1), rs.getString(2));
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
  public boolean delete(String id) throws DAOException {
    return remove(id, SQL_DELETE_AUTH_BY_ID);
  }

  @Override
  public boolean create(AuthInfo entity) throws DAOException {
    return super.update(entity, mapper, SQL_CREATE_AUTH);
  }
}
