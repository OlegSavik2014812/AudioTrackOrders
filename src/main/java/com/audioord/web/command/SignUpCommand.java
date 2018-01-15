package com.audioord.web.command;

import com.audioord.dao.AuthInfoDao;
import com.audioord.dao.DAOException;
import com.audioord.dao.UserDAO;
import com.audioord.model.account.AuthInfo;
import com.audioord.model.account.ROLE;
import com.audioord.model.account.User;
import com.audioord.web.http.Request;
import com.audioord.web.http.Response;

import java.util.Objects;

public class SignUpCommand implements Command {

  public static final String NAME = "sign_up";

  private AuthInfoDao authInfoDao = new AuthInfoDao();
  private UserDAO userDAO = new UserDAO();

  @Override
  public String execute(Request request, Response response) throws DAOException {
    if (!request.hasAllParameters("userName", "password1", "password2")) {
      return Pages.SIGN_UP_PAGE;
    }

    String userName = request.getParameter("userName");
    String firstName = request.getParameter("firstName");
    String lastName = request.getParameter("lastName");

    String password1 = request.getParameter("password1");
    String password2 = request.getParameter("password2");

    if (!Objects.equals(password1, password2) || password1.length() < 4) {
      return Pages.SIGN_UP_PAGE; // password not confirmed, or have incorrect length
    }

    AuthInfo authInfo = new AuthInfo(userName, password1);

    if (!authInfoDao.create(authInfo)) {
      return Pages.SIGN_UP_PAGE; // could not create auth info, probably username already used
    }

    User user = new User(userName, ROLE.CLIENT);
    user.setFirstName(firstName);
    user.setLastName(lastName);

    if (!userDAO.create(user)) {
      return Pages.SIGN_UP_PAGE; // could not create user
    }

    // add current user to session
    request.setSessionAttribute("user", user);

    return Pages.INDEX_PAGE;
  }
}
