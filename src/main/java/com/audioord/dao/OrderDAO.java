package com.audioord.dao;

import com.audioord.dao.sql.PoolException;
import com.audioord.model.Entity;
import com.audioord.model.account.Role;
import com.audioord.model.account.User;
import com.audioord.model.audio.Track;
import com.audioord.model.order.Order;
import com.audioord.model.order.OrderStatus;

import java.math.BigInteger;
import java.sql.*;
import java.util.Date;
import java.util.List;

/**
 * Order DAO class, extends {@link BaseEntityDao}
 */
public final class OrderDAO extends BaseEntityDao<Order, Long> {
  private static final String USER_TABLE_COLUMN_ID = "UserID";
  private static final String USER_TABLE_COLUMN_USERNAME = "Username";
  private static final String USER_TABLE_COLUMN_ROLE = "Role";
  private static final String USER_TABLE_COLUMN_FIRST_NAME = "FirstName";
  private static final String USER_TABLE_COLUMN_LAST_NAME = "LastName";

  private static final String ORDER_TABLE_COLUMN_DATE = "Date";
  private static final String ORDER_TABLE_COLUMN_ID = "OrderID";
  private static final String ORDER_TABLE_COLUMN_STATUS = "Status";
  private static final String ORDER_TABLE_COLUMN_PRICE = "TotalPrice";

  private static final String SQL_GET_ORDER_BY_ID =
  "SELECT u.Username, u.Role, u.Id as UserID, u.FirstName, u.LastName, o.Date, o.Id as OrderId, o.Status, o.TotalPrice FROM `Order` o JOIN USER u ON u.Id = o.IdUser WHERE o.id = ?";

  private static final String SQL_CREATE_ORDER =
  "INSERT INTO `Order` (TotalPrice, Status, IdUser, Date) VALUES (?,?,?,?)";

  private static final String SQL_CREATE_TRACK_ORDER =
  "INSERT INTO TrackOrder (IdTrack, IdOrder) VALUES (?, ?)";

  private static final String SQL_GET_ORDERS_BY_DATE =
  "SELECT u.Username, u.Role, u.Id as UserID, u.FirstName, u.LastName, o.Date, o.Id as OrderID, o.Status, o.TotalPrice FROM `Order` o JOIN USER u ON u.Id = o.IdUser WHERE o.DATE >= ? AND o.Date <= ?";

  private static final String SQL_GET_ORDERS_BY_DATE_AND_STATUS =
  "SELECT u.Username, u.Role, u.Id as UserID, u.FirstName, u.LastName, o.Date, o.Id as OrderID, o.Status, o.TotalPrice FROM `Order` o JOIN USER u ON u.Id = o.IdUser WHERE o.DATE >= ? AND o.Date <= ? AND o.Status=?";

  private static final String SQL_UPDATE_ORDER_BY_ID =
  "UPDATE `Order` SET TotalPrice =?, Status = ?, IdUser=?, Date=? WHERE Id = ?";

  private final EntityMapper<Order> mapper = new EntityMapper<Order>() {

    /**
     * Parse {@link ResultSet} and creates {@link Order} object
     *
     * @param rs ResultSet {@link ResultSet}
     * @return {@link Order} object
     * @throws SQLException {@link SQLException}
     * @throws DAOException {@link DAOException}
     */
    @Override
    public Order parse(ResultSet rs) throws SQLException, DAOException {
      User user = new User(rs.getString(USER_TABLE_COLUMN_USERNAME), Role.fromString(rs.getString(USER_TABLE_COLUMN_ROLE)));
      user.setId(rs.getLong(USER_TABLE_COLUMN_ID));
      user.setFirstName(rs.getString(USER_TABLE_COLUMN_FIRST_NAME));
      user.setLastName(rs.getString(USER_TABLE_COLUMN_LAST_NAME));
      Order order = new Order(user, rs.getDate(ORDER_TABLE_COLUMN_DATE));
      order.setId(rs.getLong(ORDER_TABLE_COLUMN_ID));
      order.setStatus(OrderStatus.fromString(rs.getString(ORDER_TABLE_COLUMN_STATUS)));
      order.setTotalPrice(rs.getDouble(ORDER_TABLE_COLUMN_PRICE));
      order.setTracks(new TrackDAO().getTracksByOrder(order));
      return order;
    }

    /**
     * fill {@link PreparedStatement} with {@link Order} info
     * @param st     {@link PreparedStatement}
     * @param entity {@link Order}
     * @throws SQLException {@link SQLException}
     */
    @Override
    public void write(PreparedStatement st, Order entity) throws SQLException {
      st.setDouble(1, entity.getTotalPrice());
      st.setString(2, entity.getStatus().name());
      st.setLong(3, entity.getUser().getId());
      st.setDate(4, new java.sql.Date(entity.getDateOrdered().getTime()));
      if (entity.getId() != null) {
        st.setLong(5, entity.getId());
      }
    }
  };

  /**
   * Allows to find {@link Order} object by id
   *
   * @param id {@link Order} id
   * @return {@link Order}
   * @throws DAOException {@link DAOException}
   */
  @Override
  public Order getById(Long id) throws DAOException {
    return getById(id, mapper, SQL_GET_ORDER_BY_ID);
  }

  /**
   * Allows to update {@link Order} object info
   * check {@link BaseEntityDao#update(Entity, EntityMapper, String)}
   *
   * @param entity {@link Order}
   * @return updated {@link Order}
   * @throws DAOException {@link DAOException}
   */
  @Override
  public Order update(Order entity) throws DAOException {
    update(entity, mapper, SQL_UPDATE_ORDER_BY_ID);
    return getById(entity.getId());
  }

  @Override
  public boolean delete(Long id) throws DAOException {
    return false;
  }

  /**
   * Allows to create row, which have info of input {@link Order} object
   * and creates TrackOrder rows, which connect tracks and orders
   * check {@link BaseEntityDao#create(Entity, EntityMapper, String)}
   *
   * @param entity {@link Order}
   * @return if the number of changed rows is greater than 0 - true, otherwise - false
   * @throws DAOException {@link DAOException}
   */
  @Override
  public boolean create(Order entity) throws DAOException {
    Long orderId = (Long)create(entity, mapper, SQL_CREATE_ORDER);
    // create track order here as we wat to keep in in a single method
    try (Connection con = getConnectionSource().getConnection();
         PreparedStatement st = con.prepareCall(SQL_CREATE_TRACK_ORDER)) {

      for (Track track : entity.getTracks()) {
        st.setLong(1, track.getId());
        st.setLong(2, orderId);
        st.addBatch();
      }

      int[] nCreated = st.executeBatch();
      return nCreated.length > 0;
    } catch (SQLException | PoolException e) {
      throw new DAOException(e);
    }
  }

  /**
   * Allows to get list of specified status {@link Order} objects from time period
   * check {@link BaseEntityDao#findAll(EntityMapper, String, Object...)}
   *
   * @param from   {@link Date} begin
   * @param to     {@link Date} end
   * @param status {@link OrderStatus}
   * @return list of {@link Order} objects
   * @throws DAOException {@link DAOException}
   */
  public List<Order> getOrders(Date from, Date to, OrderStatus status) throws DAOException {
    Timestamp timestampFrom = new Timestamp(from.getTime());
    Timestamp timestampTo = new Timestamp(to.getTime());
    return findAll(mapper, SQL_GET_ORDERS_BY_DATE_AND_STATUS, timestampFrom.toString(), timestampTo.toString(), status.name());
  }

  /**
   * Allows to get list of {@link Order} objects from time period
   * check {@link BaseEntityDao#findAll(EntityMapper, String, Object...)}
   *
   * @param from {@link Date} begin
   * @param to   {@link Date} end
   * @return list of {@link Order}
   * @throws DAOException {@link DAOException}
   */
  public List<Order> getOrders(Date from, Date to) throws DAOException {
    Timestamp timestampFrom = new Timestamp(from.getTime());
    Timestamp timestampTo = new Timestamp(to.getTime());
    return findAll(mapper, SQL_GET_ORDERS_BY_DATE, timestampFrom.toString(), timestampTo.toString());
  }
}
