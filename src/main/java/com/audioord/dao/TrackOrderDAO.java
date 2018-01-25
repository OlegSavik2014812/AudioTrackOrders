package com.audioord.dao;

import com.audioord.model.account.User;
import com.audioord.model.order.OrderStatus;
import com.audioord.model.order.TrackOrder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TrackOrderDAO extends BaseEntityDao<TrackOrder, Long> {
  private static final String SQL_COUNT_ALL = "select count(id) from purchase";
  private static final String SQL_COUNT_STATUS_ALL = "select count(id) from purchase where status = ?";
  private static final String SQL_CREATE_TRACKORDER = "insert into purchase(TotalPrice,Status,IdUser)values(?,?,?)";
  private static final String SQL_FIND_TRACK_ORDER_BY_ID = "select Id,TotalPrice,Status,IdUser,Date from purchase where Id = ?";
  private static final String SQL_GET_LAST_CREATED = "SELECT Id,TotalPrice,Status,IdUser,Date FROM purchase ORDER BY id DESC LIMIT 1";
  private static final String SQL_GET_ALL_STATUS_ORDERS = "select Id,TotalPrice,Status,IdUser,Date from purchase where Status=? order by Date desc  limit ?, ?";
  private static final String SQL_GET_ALL_ORDERS = "select Id,TotalPrice,Status,IdUser,Date from purchase order by Date desc  limit ?, ?";
  private static final String SQL_SUBMIT_TRACK_ORDER = "update purchase set status ='COMPLETED' where totalPrice = ? and status = ? and IdUser = ?";
  private static final String SQL_REJECT_TRACK_ORDER = "update purchase set status ='REJECTED' where totalPrice = ? and status = ? and IdUser = ?";

  private final EntityMapper<TrackOrder> mapper = new EntityMapper<TrackOrder>() {
    @Override
    public TrackOrder parse(ResultSet rs) throws SQLException, DAOException {
      UserDAO userDAO = new UserDAO();

      User user = userDAO.getById((long) rs.getInt(4));
      TrackOrder trackOrder = new TrackOrder(user, rs.getDate(5), OrderStatus.fromString(rs.getString(3)));
      trackOrder.setTotalPrice(rs.getDouble(2));
      trackOrder.setId(rs.getLong(1));
      return trackOrder;
    }

    @Override
    public void write(PreparedStatement st, TrackOrder entity) throws SQLException {
      st.setDouble(1, entity.getTotalPrice());
      st.setString(2, entity.getStatus().toString());
      st.setInt(3, Math.toIntExact(entity.getUser().getId()));
    }
  };

  @Override
  public TrackOrder getById(Long id) throws DAOException {
    return find(mapper, SQL_FIND_TRACK_ORDER_BY_ID, id);
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
    return update(entity, mapper, SQL_CREATE_TRACKORDER);
  }

  public TrackOrder getLastCreated() throws DAOException {
    return find(mapper, SQL_GET_LAST_CREATED);
  }

  public int countAllOrders() throws DAOException {
    return countAll(SQL_COUNT_ALL);
  }

  public int countAllSpecialOrders(OrderStatus orderStatus) throws DAOException {
    return countSpecial(SQL_COUNT_STATUS_ALL, orderStatus.name());
  }

  public List<TrackOrder> getAllSpaecialOrders(OrderStatus orderStatus, int page, int noOfPages) throws DAOException {
    return findAll(mapper, SQL_GET_ALL_STATUS_ORDERS, orderStatus.name(), page, noOfPages);
  }

  public List<TrackOrder> getAllOrders(int page, int noOfPages) throws DAOException {
    return findAll(mapper, SQL_GET_ALL_ORDERS, page, noOfPages);
  }

  public boolean submitTrackOrder(TrackOrder trackOrder) throws DAOException {
    return update(trackOrder, mapper, SQL_SUBMIT_TRACK_ORDER);
  }

  public boolean rejectTrackOrder(TrackOrder trackOrder) throws DAOException {
    return update(trackOrder, mapper, SQL_REJECT_TRACK_ORDER);
  }
}

