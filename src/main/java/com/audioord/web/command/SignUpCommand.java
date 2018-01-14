package com.audioord.web.command;

import com.audioord.dao.AuthInfoDao;
import com.audioord.dao.DAOException;
import com.audioord.dao.UserDAO;
import com.audioord.model.account.ROLE;
import com.audioord.model.account.User;
import com.audioord.model.auth.AuthInfo;
import com.audioord.web.http.Request;
import com.audioord.web.http.Response;

public class SignUpCommand implements Command {

  public static final String NAME = "sign_up";

  private AuthInfoDao authInfoDao = new AuthInfoDao();
  private UserDAO userDAO = new UserDAO();

  @Override
  public String execute(Request request, Response response) throws DAOException {
    if (!request.hasAllParameters("userName", "password")) {
      return Pages.SIGN_UP_PAGE;
    }

    String userName = request.getParameter("userName");
    String password = request.getParameter("password");
    String firstName = request.getParameter("firstName");
    String lastName = request.getParameter("lastName");

    AuthInfo authInfo = new AuthInfo(userName, password);
    authInfoDao.create(authInfo);
    User user = new User(userName, ROLE.CLIENT);
    user.setFirstName(firstName);
    user.setLastName(lastName);
    request.raw().getSession().setAttribute("user", user);
    userDAO.create(user);

    return Pages.INDEX_PAGE;
  }
}
