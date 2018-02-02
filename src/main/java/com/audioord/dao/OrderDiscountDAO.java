package com.audioord.dao;

import com.audioord.model.account.Role;
import com.audioord.model.account.User;
import com.audioord.model.order.OrderDiscount;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDiscountDAO extends BaseEntityDao<OrderDiscount, Long> {
  private static final String USER_TABLE_COLUMN_USERNAME = "Username";
  private static final String USER_TABLE_COLUMN_ROLE = "Role";
  private static final String USER_TABLE_COLUMN_FIRST_NAME = "FirstName";
  private static final String USER_TABLE_COLUMN_LAST_NAME = "LastName";

  private static final String DISCOUNT_TABLE_COLUMN_PERCENT = "Percent";
  private static final String DISCOUNT_TABLE_COLUMN_DATE_FROM = "DateFrom";
  private static final String DISCOUNT_TABLE_COLUMN_DATE_TO = "DateTo";

  private static final String SQL_CREATE_DISCOUNT = "insert into discount(Percent ,DateFrom,DateTo,IdUser) values(?,?,?,?)";
  private static final String SQL_GET_DISCOUNT_BY_USER_ID = "SELECT u.Id as UserId, u.Username, u.Role,  u.FirstName, u.LastName, d.Percent, d.DateFrom , d.DateTo FROM discount d JOIN USER u ON u.Id = d.IdUser where d.IdUser = ?";

  private EntityMapper<OrderDiscount> mapper = new EntityMapper<OrderDiscount>() {
    @Override
    public OrderDiscount parse(ResultSet rs) throws SQLException, DAOException {
      User user = new User(rs.getString(USER_TABLE_COLUMN_USERNAME), Role.fromString(rs.getString(USER_TABLE_COLUMN_ROLE)));
      user.setFirstName(rs.getString(USER_TABLE_COLUMN_FIRST_NAME));
      user.setLastName(rs.getString(USER_TABLE_COLUMN_LAST_NAME));
      double percent = rs.getDouble(DISCOUNT_TABLE_COLUMN_PERCENT);
      java.util.Date dateFrom = rs.getDate(DISCOUNT_TABLE_COLUMN_DATE_FROM);
      java.util.Date dateTo = rs.getDate(DISCOUNT_TABLE_COLUMN_DATE_TO);
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
