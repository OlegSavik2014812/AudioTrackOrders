package com.audioord.dao;

import com.audioord.dao.sql.PoolException;
import com.audioord.model.account.ROLE;
import com.audioord.model.account.User;
import com.audioord.model.audio.Track;
import com.audioord.model.order.Order;
import com.audioord.model.order.OrderStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDAO extends BaseEntityDao<Order, Long> {

  private static final String SQL_GET_ORDER_BY_ID =
  "SELECT u.Username, u.Role, u.Id, u.FirstName, u.LastName, o.Date, o.Id, o.Status, o.TotalPrice FROM `Order` o JOIN USER u ON u.Id = o.IdUser WHERE o.id = ?";

  private static final String SQL_CREATE_ORDER =
  "INSERT INTO `Order` (TotalPrice, Status, IdUser, Date) VALUES (?,?,?,?)";

  private static final String SQL_CREATE_TRACK_ORDER =
  "INSERT INTO TrackOrder (IdTrack, IdOrder) VALUES (?, ?)";

  private final EntityMapper<Order> mapper = new EntityMapper<Order>() {
    @Override
    public Order parse(ResultSet rs) throws SQLException, DAOException {
      User user = new User(rs.getString(1), ROLE.fromString(rs.getString(2)));
      user.setId(rs.getLong(3));
      user.setFirstName(rs.getString(4));
      user.setLastName(rs.getString(5));
      Order order = new Order(user, rs.getDate(6));
      order.setId(rs.getLong(7));
      order.setStatus(OrderStatus.fromString(rs.getString(8)));
      order.setTotalPrice(rs.getDouble(9));
      order.setTracks(new TrackDAO().getTracksByOrder(order));
      return order;
    }

    @Override
    public void write(PreparedStatement st, Order entity) throws SQLException {
      st.setDouble(1, entity.getTotalPrice());
      st.setString(2, entity.getStatus().name());
      st.setLong(3, entity.getUser().getId());
      st.setDate(4, new java.sql.Date(entity.getDateOrdered().getTime()));
    }
  };

  @Override
  public Order getById(Long id) throws DAOException {
    try (Connection con = getConnectionSource().getConnection();
         PreparedStatement st = con.prepareCall(SQL_GET_ORDER_BY_ID)) {
      st.setObject(1, id);
      ResultSet rs = st.executeQuery();

      while (rs.next()) {


      }

    } catch (SQLException | PoolException e) {
      throw new DAOException(e);
    }

    return null;
  }

  @Override
  public Order update(Order entity) throws DAOException {
    return null;
  }

  @Override
  public boolean delete(Long id) throws DAOException {
    return false;
  }

  @Override
  public boolean create(Order entity) throws DAOException {
    Long orderId = create(entity, mapper, SQL_CREATE_ORDER);
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
}
