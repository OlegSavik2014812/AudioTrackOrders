package com.audioord.dao;

import com.audioord.model.Entity;
import com.audioord.model.account.Role;
import com.audioord.model.account.User;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * User DAO class, extends {@link BaseEntityDao}
 */
public final class UserDAO extends BaseEntityDao<User, Long> {
  private static final String USER_TABLE_COLUMN_ID = "Id";
  private static final String USER_TABLE_COLUMN_USERNAME = "Username";
  private static final String USER_TABLE_COLUMN_ROLE = "Role";
  private static final String USER_TABLE_COLUMN_FIRST_NAME = "FirstName";
  private static final String USER_TABLE_COLUMN_LAST_NAME = "LastName";
  private static final String USER_TABLE_COLUMN_CASH = "Cash";

  private static final String SQL_UPDATE_USER_BY_ID =
  "UPDATE User SET UserName = ?, Role = ?, FirstName = ?, LastName = ? ,Cash = ? WHERE Id = ?";

  private static final String SQL_GET_USER_BY_ID =
  "SELECT UserName, Role,  FirstName, LastName, Id,Cash FROM User WHERE Id=?";

  private static final String SQL_GET_USER_BY_USERNAME =
  "SELECT UserName, Role,  FirstName, LastName, Id,Cash FROM User WHERE UserName=?";

  private static final String SQL_CREATE_USER =
  "INSERT INTO User (UserName, Role,  FirstName, LastName,Cash) VALUES (?,?,?,?,?)";

  private static final String SQL_DELETE_USER_BY_ID =
  "DELETE FROM User WHERE Id=?";

  private static final String SQL_COUNT_ALL =
  "SELECT count(id) FROM user where Role = 'CLIENT'";

  private static final String SQL_GET_ALL =
  "SELECT UserName, Role, FirstName, LastName, Id,Cash FROM user where Role = 'CLIENT' ORDER BY Id DESC LIMIT ?, ?";


  private final EntityMapper<User> userMapper = new EntityMapper<User>() {
    /**
     *Parse {@link ResultSet} and creates {@link User} object
     * @param rs ResultSet {@link ResultSet}
     * @return {@link User} object
     * @throws SQLException {@link SQLException}
     */
    @Override
    public User parse(ResultSet rs) throws SQLException {
      User user = new User(rs.getString(USER_TABLE_COLUMN_USERNAME), Role.fromString(rs.getString(USER_TABLE_COLUMN_ROLE)));
      user.setFirstName(rs.getString(USER_TABLE_COLUMN_FIRST_NAME));
      user.setLastName(rs.getString(USER_TABLE_COLUMN_LAST_NAME));
      user.setId(rs.getLong(USER_TABLE_COLUMN_ID));
      user.setCash(rs.getDouble(USER_TABLE_COLUMN_CASH));
      return user;
    }

    /**
     *fill {@link PreparedStatement} with {@link User} info
     * @param st     {@link PreparedStatement}
     * @param user {@link User} object
     * @throws SQLException {@link SQLException}
     */
    @Override
    public void write(PreparedStatement st, User user) throws SQLException {
      st.setString(1, user.getUsername());
      st.setString(2, user.getRole().name());
      st.setString(3, user.getFirstName());
      st.setString(4, user.getLastName());
      st.setDouble(5, user.getCash());
      if (user.getId() != null) {
        st.setLong(6, user.getId());
      }

    }
  };

  /**
   * Allows to find {@link User} object by id
   *
   * @param id id
   * @return {@link User} object
   * @throws DAOException {@link DAOException}
   */
  @Override
  public User getById(Long id) throws DAOException {
    return super.getById(id, userMapper, SQL_GET_USER_BY_ID);
  }

  /**
   * Allows to find {@link User} object by username
   *
   * @param username username
   * @return {@link User} object
   * @throws DAOException {@link DAOException}
   */
  public User getByUsername(String username) throws DAOException {
    return super.find(userMapper, SQL_GET_USER_BY_USERNAME, username);
  }

  /**
   * Allows to update {@link User} object info
   * check {@link BaseEntityDao#update(Entity, EntityMapper, String)}
   * check {@link BaseEntityDao#getById(Serializable)}
   *
   * @param user {@link User} object
   * @return updated {@link User} object
   * @throws DAOException {@link DAOException}
   */
  @Override
  public User update(User user) throws DAOException {
    super.update(user, userMapper, SQL_UPDATE_USER_BY_ID);
    return getById(user.getId());
  }

  @Override
  public boolean delete(Long id) throws DAOException {
    return super.remove(id, SQL_DELETE_USER_BY_ID);
  }

  /**
   * Allows to create row, which have info of input {@link User} object
   * check {@link BaseEntityDao#update(Entity, EntityMapper, String)}
   *
   * @param user {@link User} object
   * @return if the number of changed rows is greater than 0 - true, otherwise - false
   * @throws DAOException {@link DAOException}
   */
  @Override
  public boolean create(User user) throws DAOException {
    return super.update(user, userMapper, SQL_CREATE_USER);
  }

  /**
   * Allows to count all rows, which contain info of {@link User} objects
   * check {@link BaseEntityDao#countAll(String)}
   *
   * @return number of rows
   * @throws DAOException {@link DAOException}
   */
  public int countAllUsers() throws DAOException {
    return countAll(SQL_COUNT_ALL);
  }

  /**
   * Allows to get brand new {@link User} objects
   * check {@link BaseEntityDao#findAll(EntityMapper, String, Object...)}
   *
   * @param page      number of row in a table "User"
   * @param noOfPages quantity of rows
   * @return list of {@link User} object
   * @throws DAOException {@link DAOException}
   */
  public List<User> getAllUsers(int page, int noOfPages) throws DAOException {
    return super.findAll(userMapper, SQL_GET_ALL, page, noOfPages);
  }
}
