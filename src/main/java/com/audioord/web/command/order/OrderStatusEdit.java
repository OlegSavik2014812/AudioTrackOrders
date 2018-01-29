package com.audioord.web.command.order;

import com.audioord.dao.DAOException;
import com.audioord.dao.OrderDAO;
import com.audioord.model.order.Order;
import com.audioord.model.order.OrderStatus;
import com.audioord.web.command.Command;
import com.audioord.web.command.Pages;
import com.audioord.web.http.Request;
import com.audioord.web.http.Response;

import java.io.IOException;

public class OrderStatusEdit implements Command {

  public static final String NAME = "order_edit";
  private static final String PRM_ORDER_ID = "order_id";
  private static final String PRM_ORDER_STATUS = "status";

  private OrderDAO orderDAO = new OrderDAO();

  @Override
  public String execute(Request request, Response response) throws DAOException, IOException {

    // order didn't exists
    Order order = orderDAO.getById(Long.valueOf(request.getParameter(PRM_ORDER_ID)));
    if (order == null) {
      return Pages.ORDER_LIST_PAGE;
    }

    // wrong status value
    OrderStatus status = OrderStatus.fromString(request.getParameter(PRM_ORDER_STATUS));
    if (status == null) {
      return Pages.ORDER_LIST_PAGE;
    }

    // no changes has ben performed
    if (order.getStatus() == status) {
      return Pages.ORDER_LIST_PAGE;
    }

    order.setStatus(status);
    orderDAO.update(order);

    return Pages.INDEX_PAGE;
  }
}
