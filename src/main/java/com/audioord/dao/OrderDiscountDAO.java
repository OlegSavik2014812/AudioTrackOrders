package com.audioord.dao;

import com.audioord.model.account.Role;
import com.audioord.model.account.User;
import com.audioord.model.order.OrderDiscount;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDiscountDAO extends BaseEntityDao<OrderDiscount, Long> {
  private static final String SQL_CREATE_DISCOUNT = "insert into discount(Percent ,DateFrom,DateTo,IdUser) values(?,?,?,?)";
  private static final String SQL_GET_DISCOUNT_BY_USER_ID = "SELECT u.Id, u.Username, u.Role,  u.FirstName, u.LastName, d.Percent, d.DateFrom , d.DateTo FROM discount d JOIN USER u ON u.Id = d.IdUser where d.IdUser = ?";

  private EntityMapper<OrderDiscount> mapper = new EntityMapper<OrderDiscount>() {
    @Override
    public OrderDiscount parse(ResultSet rs) throws SQLException, DAOException {
      User user = new User(rs.getString(2), Role.fromString(rs.getString(3)));
      user.setFirstName(rs.getString(4));
      user.setLastName(rs.getString(5));
      double percent = rs.getDouble(6);
      java.util.Date dateFrom = rs.getDate(7);
      java.util.Date dateTo = rs.getDate(8);
      return new OrderDiscount(percent, dateFrom, dateTo, user);
    }

    @Override
    public void write(PreparedStatement st, OrderDiscount entity) throws SQLException {
      st.setDouble(1, entity.getDiscountPercent());
      st.setDate(2, new Date(entity.getEffectiveFrom().getTime()));
      st.setDate(3, new Date(entity.getEffectiveTo().getTime()));
      st.setLong(4, entity.getUser().getId());
    }
  };

  @Override
  public OrderDiscount getById(Long id) throws DAOException {
    return null;
  }

  @Override
  public OrderDiscount update(OrderDiscount entity) throws DAOException {
    return null;
  }

  @Override
  public boolean delete(Long id) throws DAOException {
    return false;
  }

  @Override
  public boolean create(OrderDiscount entity) throws DAOException {
    return update(entity, mapper, SQL_CREATE_DISCOUNT);
  }

  public OrderDiscount getByUserId(Long id) throws DAOException {
    return find(mapper, SQL_GET_DISCOUNT_BY_USER_ID, id);
  }
}
