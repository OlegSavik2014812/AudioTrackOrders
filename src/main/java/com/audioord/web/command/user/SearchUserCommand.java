package com.audioord.web.command.user;

import com.audioord.dao.DAOException;
import com.audioord.dao.UserDAO;
import com.audioord.model.account.User;
import com.audioord.web.command.Command;
import com.audioord.web.command.Pages;
import com.audioord.web.http.Request;
import com.audioord.web.http.Response;

public class SearchUserCommand implements Command {
  public static final String NAME = "search_user";
  private static final String PRM_USERNAME = "userName";
  private UserDAO userDAO = new UserDAO();

  @Override
  public String execute(Request request, Response response) throws DAOException {
    if (!request.hasAllParameters(PRM_USERNAME)) {
      return "/";
    }
    String userName = request.getParameter(PRM_USERNAME);
    if (userName.isEmpty()) {
      return "/";
    }
    User user = userDAO.getByUsername(userName);
    if (user == null) {
      return "/";
    }
    request.raw().setAttribute("user", user);
    return Pages.INDEX_PAGE;
  }
}
