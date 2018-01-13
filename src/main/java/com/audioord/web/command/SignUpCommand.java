package com.audioord.web.command;

import com.audioord.model.account.ROLE;
import com.audioord.model.account.User;
import com.audioord.model.auth.AuthInfo;
import com.audioord.web.http.Request;
import com.audioord.web.http.Response;

public class SignUpCommand implements Command {
  public static final String NAME = "sign_up";
  private static final String INDEX_PATH = "/index.jsp";

  @Override
  public String execute(Request request, Response response) {
    AuthInfo authInfo = new AuthInfo(request.getParameter("userName"), request.getParameter("password"));
    User user = new User(request.getParameter("userName"), ROLE.CLIENT);
    user.setFirstName(request.getParameter("firstName"));
    user.setLastName(request.getParameter("lastName"));
    request.raw().getSession().setAttribute("user", user);
    return INDEX_PATH;
  }
}
