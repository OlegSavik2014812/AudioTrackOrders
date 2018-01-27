package com.audioord.dao;

import com.audioord.dao.sql.PoolException;
import com.audioord.model.audio.Track;
import com.audioord.model.order.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDAO extends BaseEntityDao<Order, Long> {

  public static final String SQL_CREATE_ORDER =
  "INSERT INTO `Order` (TotalPrice, Status, IdUser, Date) VALUES (?,?,?,?)";

  public static final String SQL_CREATE_TRACK_ORDER =
  "INSERT INTO TrackOrder (IdTrack, IdOrder) VALUES (?,?)";

  private final EntityMapper<Order> mapper = new EntityMapper<Order>() {
    @Override
    public Order parse(ResultSet rs) throws SQLException, DAOException {
      //
      return null;
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
