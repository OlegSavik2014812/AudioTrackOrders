package com.audioord.web.command.cart;

import com.audioord.dao.DAOException;
import com.audioord.web.command.Command;
import com.audioord.web.command.Pages;
import com.audioord.web.http.Request;
import com.audioord.web.http.Response;


/**
 * Class describes the object-command, which shows all tracks added to user cart
 * implementation of {@link Command}
 */
public class ViewTrackCartCommand implements Command {

  public static final String NAME = "view_cart";

  /**
   * @param request  {@link Request}
   * @param response {@link Response}
   * @return string name of page
   * @throws DAOException {@link DAOException}
   */
  @Override
  public String execute(Request request, Response response) throws DAOException {
    return Pages.CART_LIST_PAGE;
  }
}
