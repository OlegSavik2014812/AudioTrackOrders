package com.audioord.dao;

import com.audioord.model.account.ROLE;
import com.audioord.model.account.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class UserDAO extends BaseEntityDao<User, Long> {

  private static final String SQL_UPDATE_USER_BY_ID =
  "UPDATE User SET UserName = ?, Role = ?, FirstName = ?, LastName = ? WHERE Id = ?";

  private static final String SQL_GET_USER_BY_ID =
  "SELECT UserName, Role,  FirstName, LastName, Id FROM User WHERE Id=?";

  private static final String SQL_GET_USER_BY_USERNAME =
  "SELECT UserName, Role,  FirstName, LastName, Id FROM User WHERE UserName=?";

  private static final String SQL_CREATE_USER =
  "INSERT INTO User (UserName, Role,  FirstName, LastName) VALUES (?,?,?,?)";

  private static final String SQL_DELETE_USER_BY_ID = "DELETE FROM User WHERE Id=?";

  private final EntityMapper<User> userMapper = new EntityMapper<User>() {
    @Override
    public User parse(ResultSet rs) throws SQLException {
      User user = new User(rs.getString(1), ROLE.fromString(rs.getString(2)));
      user.setFirstName(rs.getString(3));
      user.setLastName(rs.getString(4));
      user.setId(rs.getLong(5));
      return user;
    }

    @Override
    public void write(PreparedStatement st, User user) throws SQLException {
      st.setString(1, user.getUsername());
      st.setString(2, user.getRole().name());
      st.setString(3, user.getFirstName());
      st.setString(4, user.getLastName());
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
  public User update(User user) throws DAOException {
    super.update(user, userMapper, SQL_UPDATE_USER_BY_ID);
    return getById(user.getId());
  }

  @Override
  public boolean delete(Long id) throws DAOException {
    return super.remove(id, SQL_DELETE_USER_BY_ID);
  }

  @Override
  public boolean create(User user) throws DAOException {
    return super.update(user, userMapper, SQL_CREATE_USER);
  }
}
