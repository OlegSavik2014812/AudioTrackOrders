package com.audioord.web.command;

import com.audioord.dao.DAOException;
import com.audioord.dao.UserDAO;
import com.audioord.model.account.User;
import com.audioord.web.http.Request;
import com.audioord.web.http.Response;

import java.io.IOException;

public class SearchUserCommand implements Command {
  public static final String NAME = "search_user";
  private static final String PRM_USERNAME = "userName";
  private UserDAO userDAO = new UserDAO();

  @Override
  public String execute(Request request, Response response) throws IOException, DAOException, ClassNotFoundException {
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
