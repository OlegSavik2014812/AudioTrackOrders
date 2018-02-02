package com.audioord.dao;

import com.audioord.model.account.Role;
import com.audioord.model.account.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public final class UserDAO extends BaseEntityDao<User, Long> {
  private static final String USER_TABLE_COLUMN_ID = "Id";
  private static final String USER_TABLE_COLUMN_USERNAME = "Username";
  private static final String USER_TABLE_COLUMN_ROLE = "Role";
  private static final String USER_TABLE_COLUMN_FIRST_NAME = "FirstName";
  private static final String USER_TABLE_COLUMN_LAST_NAME = "LastName";

  private static final String SQL_UPDATE_USER_BY_ID =
  "UPDATE User SET UserName = ?, Role = ?, FirstName = ?, LastName = ? WHERE Id = ?";

  private static final String SQL_GET_USER_BY_ID =
  "SELECT UserName, Role,  FirstName, LastName, Id FROM User WHERE Id=?";

  private static final String SQL_GET_USER_BY_USERNAME =
  "SELECT UserName, Role,  FirstName, LastName, Id FROM User WHERE UserName=?";

  private static final String SQL_CREATE_USER =
  "INSERT INTO User (UserName, Role,  FirstName, LastName) VALUES (?,?,?,?)";

  private static final String SQL_DELETE_USER_BY_ID =
  "DELETE FROM User WHERE Id=?";

  private static final String SQL_COUNT_ALL =
  "SELECT count(id) FROM user";

  private static final String SQL_GET_ALL =
  "SELECT UserName, Role, FirstName, LastName, Id FROM user ORDER BY Id DESC LIMIT ?, ?";

  private final EntityMapper<User> userMapper = new EntityMapper<User>() {
    @Override
    public User parse(ResultSet rs) throws SQLException {
      User user = new User(rs.getString(USER_TABLE_COLUMN_USERNAME), Role.fromString(rs.getString(USER_TABLE_COLUMN_ROLE)));
      user.setFirstName(rs.getString(USER_TABLE_COLUMN_FIRST_NAME));
      user.setLastName(rs.getString(USER_TABLE_COLUMN_LAST_NAME));
      user.setId(rs.getLong(USER_TABLE_COLUMN_ID));
      return user;
    }

    @Override
    public void write(PreparedStatement st, User user) throws SQLException {
      st.setString(1, user.getUsername());
      st.setString(2, user.getRole().name());
      st.setString(3, user.getFirstName());
      st.setString(4, user.getLastName());
      if (user.getId() != null) {
        st.setLong(5, user.getId());
      }
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

  public int countAllUsers() throws DAOException {
    return countAll(SQL_COUNT_ALL);
  }

  public List<User> getAllUsers(int page, int noOfPages) throws DAOException {
    return super.findAll(userMapper, SQL_GET_ALL, page, noOfPages);
  }
}
