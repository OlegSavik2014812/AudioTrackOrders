package com.audioord.web.command;

import com.audioord.dao.DAOException;
import com.audioord.dao.OrderDiscountDAO;
import com.audioord.model.order.OrderDiscount;
import com.audioord.web.http.Request;
import com.audioord.web.http.Response;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AdditionDiscountCommand implements Command {
  public static final String NAME = "add_discount";
  private OrderDiscountDAO orderDiscountDAO = new OrderDiscountDAO();
  private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");

  @Override
  public String execute(Request request, Response response) throws IOException, DAOException {
    double percent;
    Date effectiveFrom = null;
    Date effectiveTo = null;
    if (!request.hasAllParameters("percent", "effectiveFrom")) {
      return Pages.ADD_DISCOUNT_PAGE;
    }
    percent = Double.parseDouble(request.getParameter("percent"));
    try {
      effectiveFrom = simpleDateFormat.parse(request.getParameter("effectiveFrom"));
      effectiveTo = simpleDateFormat.parse(request.getParameter("effectiveTo"));
    } catch (ParseException e) {
      e.printStackTrace();
    }

    OrderDiscount orderDiscount = new OrderDiscount(percent, effectiveFrom, effectiveTo);
    orderDiscount.setEffectiveTo(effectiveTo);
    if (!orderDiscountDAO.create(orderDiscount)) {
      return Pages.ADD_DISCOUNT_PAGE;
    }
    return Pages.INDEX_PAGE;
  }
}

