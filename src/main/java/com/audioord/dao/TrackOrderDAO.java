package com.audioord.dao;

import com.audioord.model.order.TrackOrder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TrackOrderDAO extends BaseEntityDao<TrackOrder, Long> {
  EntityMapper<TrackOrder> mapper = new EntityMapper<TrackOrder>() {
    @Override
    public TrackOrder parse(ResultSet rs) throws SQLException, DAOException {
      return null;
    }

    @Override
    public void write(PreparedStatement st, TrackOrder entity) throws SQLException {
      st.setDouble(1, entity.getTotalPrice());
      st.setObject(2, entity.getStatus());
      st.setInt(3, Math.toIntExact(entity.getUser().getId()));
      st.setObject(4, entity.getDateOrdered());
    }
  };

  @Override
  public TrackOrder getById(Long id) throws DAOException {
    return null;
  }

  @Override
  public TrackOrder update(TrackOrder entity) throws DAOException {
    return null;
  }

  @Override
  public boolean delete(Long id) throws DAOException {
    return false;
  }

  @Override
  public boolean create(TrackOrder entity) throws DAOException {
    return false;
  }
}
