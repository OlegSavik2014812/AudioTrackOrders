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

/**
 * Class describes the object-command, which edit order status and returns to index page
 * implementation of {@link Command}
 */
public class OrderStatusEdit implements Command {

  public static final String NAME = "order_edit";
  private static final String PRM_ORDER_ID = "order_id";
  private static final String PRM_ORDER_STATUS = "status";

  private final OrderDAO orderDAO = new OrderDAO();

  /**
   * There is creation of {@link Order} object and its validation, if order didn't exists, then redirecting to
   * order list page
   * if status of is invalid, then redirecting to order list page
   * if status has no changes, then redirecting to order list page
   * if command is successful, then updating {@link Order} object and redirection index page
   *
   * @param request  {@link Request}
   * @param response {@link Response}
   * @return string name of page
   * @throws IOException  in case, when params incorrect
   * @throws DAOException {@link DAOException}
   */
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
