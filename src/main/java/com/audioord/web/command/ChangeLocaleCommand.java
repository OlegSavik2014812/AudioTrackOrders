package com.audioord.web.command;

import com.audioord.dao.DAOException;
import com.audioord.web.http.Request;
import com.audioord.web.http.Response;

import javax.servlet.ServletException;
import java.io.IOException;

public class ChangeLocaleCommand implements Command {

  public static final String NAME = "change_local";

  private enum local {
    EN,
    RU
  }

  @Override
  public String execute(Request request, Response response) throws IOException, DAOException, ServletException {
    String locale = request.raw().getParameter("local");
    if (locale != null && !locale.isEmpty()) {
      if (locale.equalsIgnoreCase(String.valueOf(local.EN)) || locale.equalsIgnoreCase(String.valueOf(local.RU))) {
        request.raw().getSession().setAttribute("local", locale);
      }
    }
    return Pages.INDEX_PAGE;
  }
}
