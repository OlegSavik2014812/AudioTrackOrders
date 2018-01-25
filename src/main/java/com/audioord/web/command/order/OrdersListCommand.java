package com.audioord.web.command.order;

import com.audioord.dao.DAOException;
import com.audioord.dao.TrackOrderDAO;
import com.audioord.model.account.ROLE;
import com.audioord.model.account.User;
import com.audioord.model.order.OrderStatus;
import com.audioord.model.order.TrackOrder;
import com.audioord.web.command.Command;
import com.audioord.web.command.Pages;
import com.audioord.web.http.Request;
import com.audioord.web.http.Response;

import java.io.IOException;
import java.util.List;

public class OrdersListCommand implements Command {
  public static final String NAME = "purchases_list";
  private TrackOrderDAO trackOrderDAO = new TrackOrderDAO();
  private static final String PRM_PAGE = "page";
  private static final String PRM_ORDER_STATUS = "status";

  @Override
  public String execute(Request request, Response response) throws IOException, DAOException, ClassNotFoundException {
    User user = request.getSessionAttribute("USER", User.class);
    if (user == null) {
      // user not authorized
      return Pages.SIGN_IN_PAGE;
    }
    if (!user.getRole().equals(ROLE.ADMIN)) {
      return Pages.INDEX_PAGE;
    }
    int page = 1;
    int recordsPerPage = 8;
    int noOfPages;
    if (request.hasParameter(PRM_PAGE)) {
      page = Integer.parseInt(request.getParameter(PRM_PAGE));
    }
    OrderStatus status = OrderStatus.fromString(request.getParameter(PRM_ORDER_STATUS));
    List<TrackOrder> trackOrderList;
    if (status != null) {
      int noOfRecords = trackOrderDAO.countAllSpecialOrders(status);
      noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
    } else {
      int noOfRecords = trackOrderDAO.countAllOrders();
      noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
    }
    if (status != null) {
      trackOrderList = trackOrderDAO.getAllSpaecialOrders(status, (page - 1) * recordsPerPage, recordsPerPage);
    } else {
      trackOrderList = trackOrderDAO.getAllOrders((page - 1) * recordsPerPage, recordsPerPage);
    }
    request.addAttribute("PurchaseList", trackOrderList);
    request.addAttribute("noOfPages", noOfPages);
    request.addAttribute("currentPage", page);
    return Pages.ALL_PURCHASES;
  }
}
