package com.audioord.dao;

import com.audioord.model.order.Order;

public class OrderDAO extends BaseEntityDao<Order, Long> {


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
    return false;
  }


}
