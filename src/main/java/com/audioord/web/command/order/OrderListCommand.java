package com.audioord.web.command.order;


import com.audioord.dao.DAOException;
import com.audioord.dao.OrderDAO;
import com.audioord.model.order.Order;
import com.audioord.utils.DateUtil;
import com.audioord.web.command.Command;
import com.audioord.web.command.Pages;
import com.audioord.web.http.Request;
import com.audioord.web.http.Response;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class OrderListCommand implements Command {

  public static final String NAME = "order_list";

  private static final String PRM_DATE_FROM = "date_from";
  private static final String PRM_DATE_TO = "date_to";
  private static final String PRM_ORDERS = "orders";


  private OrderDAO orderDAO = new OrderDAO();

  @Override
  public String execute(Request request, Response response) throws DAOException, IOException {

    Date dateFrom = DateUtil.parseDate(request.getParameter(PRM_DATE_FROM));
    Date dateTo = DateUtil.parseDate(request.getParameter(PRM_DATE_TO));

    if (dateTo == null) {
      dateTo = new Date();
    }

    if (dateFrom == null) {
      dateFrom = new Date(dateTo.getTime() - TimeUnit.DAYS.toMillis(1));
    }

    List<Order> orders = orderDAO.getOrders(dateFrom, dateTo);

    request.addAttribute(PRM_ORDERS, orders);

    return Pages.ORDER_LIST_PAGE;
  }
}
