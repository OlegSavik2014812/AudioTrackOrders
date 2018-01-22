package com.audioord.web.command;

import com.audioord.dao.DAOException;
import com.audioord.dao.UserDAO;
import com.audioord.model.account.User;
import com.audioord.web.http.Request;
import com.audioord.web.http.Response;

import java.io.IOException;

public class SearchUserCommand implements Command {
  public static final String NAME = "search_user";
  private UserDAO userDAO = new UserDAO();

  @Override
  public String execute(Request request, Response response) throws IOException, DAOException, ClassNotFoundException {
    if (!request.hasAllParameters("userName")) {
      return Pages.INDEX_PAGE;
    }
    String userName = request.getParameter("userName");
    if (userName.isEmpty()) {
      return Pages.INDEX_PAGE;
    }
    User user = userDAO.getByUsername(userName);
    if (user == null) {
      return Pages.INDEX_PAGE;
    }
    request.raw().setAttribute("user", user);
    return Pages.INDEX_PAGE;
  }
}
