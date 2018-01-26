package com.audioord.dao;

import com.audioord.model.order.OrderedTrack;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderedTrackDAO extends BaseEntityDao<OrderedTrack, Long> {
  private static final String SQL_ADD_ORDERED_TRACK = "insert into TrackOrder(IdTrack,IdPurchase)values(?,?)";
  private final EntityMapper<OrderedTrack> mapper = new EntityMapper<OrderedTrack>() {
    @Override
    public OrderedTrack parse(ResultSet rs) throws SQLException, DAOException {
      OrderedTrack orderedTrack = new OrderedTrack(rs.getLong(2), rs.getLong(1));
      return orderedTrack;
    }

    @Override
    public void write(PreparedStatement st, OrderedTrack entity) throws SQLException {
      st.setLong(1, entity.getTrackId());
      st.setLong(2, entity.getPurchaseId());
    }
  };

  @Override
  public OrderedTrack getById(Long id) throws DAOException {
    return null;
  }

  @Override
  public OrderedTrack update(OrderedTrack entity) throws DAOException {
    return null;
  }

  @Override
  public boolean delete(Long id) throws DAOException {
    return false;
  }

  @Override
  public boolean create(OrderedTrack entity) throws DAOException {
    return update(entity, mapper, SQL_ADD_ORDERED_TRACK);
  }
}
