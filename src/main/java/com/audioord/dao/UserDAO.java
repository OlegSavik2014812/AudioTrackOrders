package com.audioord.dao;

import com.audioord.model.account.ROLE;
import com.audioord.model.account.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class UserDAO extends BaseEntityDao<User, Long> {

  private static final String SQL_GET_USER_BY_ID =
      "SELECT UserName, Role,  FirstName, LastName, Id FROM User WHERE Id=?";
  private static final String SQL_GET_USER_BY_USERNAME =
      "SELECT UserName, Role,  FirstName, LastName, Id FROM User WHERE UserName=?";
  private static final String SQL_CREATE_USER =
      "INSERT INTO User (UserName, Role,  FirstName, LastName) VALUES (?,?,?,?)";

  public static final String SQL_UPDATE_USER =
  "UPDATE User SET UserName = ?, Role = ?, FirstName = ?, LastName = ? WHERE order_id = ?";

  private final EntityMapper<User> userMapper =
      new EntityMapper<User>() {
        @Override
        public User parse(ResultSet rs) throws SQLException {
          User user = new User(rs.getString(1), ROLE.valueOf(rs.getString(2)));
          user.setFirstName(rs.getString(3));
          user.setLastName(rs.getString(4));
          user.setId(rs.getLong(5));
          return user;
        }
      };

  @Override
  public User getById(Long id) throws DAOException {
    return super.getById(id, userMapper, SQL_GET_USER_BY_ID);
  }

  public User getByUsername(String username) throws DAOException {
    return super.find(userMapper, SQL_GET_USER_BY_USERNAME, username);
  }

  @Override
  public User update(User entity) {
    return null;
  }

  @Override
  public boolean delete(Long id) {
    return false;
  }

  @Override
  public boolean create(User entity) {
    return false;
  }
}
