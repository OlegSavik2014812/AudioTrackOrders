package com.audioord.web.command.order;

import com.audioord.dao.DAOException;
import com.audioord.web.command.Command;
import com.audioord.web.command.Pages;
import com.audioord.web.http.Request;
import com.audioord.web.http.Response;

import java.io.IOException;

/**
 * @author owl
 */
public class OrderStatusEdit implements Command {

  public static final String NAME = "order_edit";

  @Override
  public String execute(Request request, Response response) throws DAOException, IOException {

    return Pages.INDEX_PAGE;
  }
}
