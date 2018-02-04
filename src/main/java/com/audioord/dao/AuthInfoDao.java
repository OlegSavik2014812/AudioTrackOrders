package com.audioord.dao;

import com.audioord.model.Entity;
import com.audioord.model.account.AuthInfo;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Authentication Information DAO class, extends {@link BaseEntityDao}
 */
public final class AuthInfoDao extends BaseEntityDao<AuthInfo, String> {
  private static final String AUTH_INFO_TABLE_COLUMN_USERNAME = "UserName";
  private static final String AUTH_INFO_TABLE_COLUMN_PASSWORD = "Password";

  private static final String SQL_GET_AUTH_BY_ID =
  "SELECT UserName, Password  FROM AuthInfo WHERE UserName = ?";

  private static final String SQL_UPDATE_AUTH_BY_ID =
  "UPDATE AuthInfo SET UserName = ?, Password = ? WHERE UserName = ?";

  private static final String SQL_DELETE_AUTH_BY_ID =
  "DELETE FROM AuthInfo WHERE UserName=?";

  private static final String SQL_CREATE_AUTH =
  "INSERT INTO AuthInfo (UserName, Password) VALUES (?, ?)";

  private final EntityMapper<AuthInfo> mapper = new EntityMapper<AuthInfo>() {
    /**
     * Parse {@link ResultSet} and creates {@link AuthInfo} object
     * @param rs ResultSet {@link ResultSet}
     * @return {@link AuthInfo} object
     * @throws SQLException {@link SQLException}
     */
    @Override
    public AuthInfo parse(ResultSet rs) throws SQLException {
      return new AuthInfo(rs.getString(AUTH_INFO_TABLE_COLUMN_USERNAME), rs.getString(AUTH_INFO_TABLE_COLUMN_PASSWORD));
    }

    /**
     * Sets {@link AuthInfo} params to {@link PreparedStatement}
     * @param st {@link PreparedStatement}
     * @param entity {@link AuthInfo} object
     * @throws SQLException {@link SQLException}
     */
    @Override
    public void write(PreparedStatement st, AuthInfo entity) throws SQLException {
      st.setString(1, entity.getUserName());
      st.setString(2, entity.getPassword());
    }
  };

  /**
   * Allows to find {@link AuthInfo} object by id
   * check {@link BaseEntityDao#getById(Serializable, EntityMapper, String)}
   *
   * @param id in case of AuthInfo, it's a username of User, who connected with AuthInfo by username
   * @return {@link AuthInfo} object
   * @throws DAOException {@link DAOException}
   */
  @Override
  public AuthInfo getById(String id) throws DAOException {
    return getById(id, mapper, SQL_GET_AUTH_BY_ID);
  }

  /**
   * Allows to update {@link AuthInfo} object info
   * check {@link BaseEntityDao#update(Entity, EntityMapper, String)}
   * check {@link BaseEntityDao#getById(Serializable)}
   *
   * @param authInfo {@link AuthInfo} object
   * @return update {@link AuthInfo} object
   * @throws DAOException {@link DAOException}
   */
  @Override
  public AuthInfo update(AuthInfo authInfo) throws DAOException {
    super.update(authInfo, mapper, SQL_UPDATE_AUTH_BY_ID);
    return getById(authInfo.getId());
  }

  /**
   * Allows to delete information about {@link AuthInfo} object by id
   * check {@link BaseEntityDao#remove(Serializable, String)}
   *
   * @param id in case of AuthInfo, it's a username of User, who connected with AuthInfo by username
   * @return if transaction successful, returns true, otherwise returns false
   * @throws DAOException {@link DAOException}
   */
  @Override
  public boolean delete(String id) throws DAOException {
    return remove(id, SQL_DELETE_AUTH_BY_ID);
  }

  /**
   * Allows to create row, which have info of input {@link AuthInfo} object
   * check {@link BaseEntityDao#update(Entity, EntityMapper, String)}
   *
   * @param entity {@link AuthInfo} object
   * @return if transaction successful, returns true, otherwise returns false
   * @throws DAOException {@link DAOException}
   */
  @Override
  public boolean create(AuthInfo entity) throws DAOException {
    return super.update(entity, mapper, SQL_CREATE_AUTH);
  }
}
