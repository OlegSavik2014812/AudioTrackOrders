package com.audioord.dao;

import com.audioord.model.order.OrderDiscount;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDiscountDAO extends BaseEntityDao<OrderDiscount, Long> {
  private static final String SQL_CREATE_DISCOUNT = "insert into Discount(Percent, DateBegin,DateEnd)values(?,?,?)";


  private final EntityMapper<OrderDiscount> mapper = new EntityMapper<OrderDiscount>() {

    @Override
    public OrderDiscount parse(ResultSet rs) throws SQLException, DAOException {
      java.util.Date dateUtilBegin = (java.util.Date) rs.getObject(3);
      java.util.Date dateUtilEnd = (java.util.Date) rs.getObject(4);
      double discountPercent = rs.getDouble(2);
      Date dateBegin = new Date(dateUtilBegin.getTime());
      Date dateEnd = new Date(dateUtilEnd.getTime());
      OrderDiscount discount = new OrderDiscount(discountPercent, dateBegin, dateEnd);
      return discount;
    }

    @Override
    public void write(PreparedStatement st, OrderDiscount entity) throws SQLException {
      st.setDouble(1, entity.getDiscountPercent());
      st.setObject(2, entity.getEffectiveFrom());
      st.setObject(3, entity.getEffectiveTo());
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
}
