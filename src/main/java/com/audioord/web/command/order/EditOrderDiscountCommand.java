package com.audioord.web.command.order;

import com.audioord.dao.DAOException;
import com.audioord.dao.OrderDiscountDAO;
import com.audioord.dao.UserDAO;
import com.audioord.model.account.User;
import com.audioord.model.order.OrderDiscount;
import com.audioord.utils.DateUtil;
import com.audioord.web.command.Command;
import com.audioord.web.command.Pages;
import com.audioord.web.http.Request;
import com.audioord.web.http.Response;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EditOrderDiscountCommand implements Command {
  public static final String NAME = "add_discount";

  private static final String PRM_USER = "username";
  private static final String PRM_DATE_FROM = "date_from";
  private static final String PRM_DATE_TO = "date_to";

  private final OrderDiscountDAO orderDiscountDAO = new OrderDiscountDAO();
  private final UserDAO userDAO = new UserDAO();
  private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

  @Override
  public String execute(Request request, Response response) throws DAOException, IOException {

    User user = userDAO.getByUsername(request.getParameter(PRM_USER));
    if (user == null) {
      return Pages.USER_LIST_PAGE;
    }

    double percent = Double.parseDouble(request.getParameter("percent"));
    Date dateFrom = DateUtil.parseDate(request.getParameter(PRM_DATE_FROM), simpleDateFormat);
    Date dateTo = DateUtil.parseDate(request.getParameter(PRM_DATE_TO), simpleDateFormat);
    OrderDiscount orderDiscount = new OrderDiscount(percent, dateFrom, dateTo, user);
    if (!orderDiscountDAO.create(orderDiscount)) {
      return Pages.USER_LIST_PAGE;
    }

    return Pages.USER_LIST_PAGE;
  }
}
