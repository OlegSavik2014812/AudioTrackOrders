package com.audioord.web.command.auth;

import com.audioord.dao.AuthInfoDao;
import com.audioord.dao.DAOException;
import com.audioord.dao.UserDAO;
import com.audioord.model.account.AuthInfo;
import com.audioord.model.account.Role;
import com.audioord.model.account.User;
import com.audioord.web.command.Command;
import com.audioord.web.command.Pages;
import com.audioord.web.http.Request;
import com.audioord.web.http.Response;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.Serializable;
import java.util.Objects;

/**
 * Class describes the object-command, which add new client to the system
 * and put his info to session.
 * * implementation of {@link Command}
 */
public class SignUpCommand implements Command {

  public static final String NAME = "sign_up";

  private static final String PRM_USERNAME = "userName";
  private static final String PRM_PASSWORD1 = "password1";
  private static final String PRM_PASSWORD2 = "password2";
  private static final String PRM_FIRST_NAME = "firstName";
  private static final String PRM_LAST_NAME = "lastName";
  private static final String ATTRIBUTE_USER = "USER";

  private final AuthInfoDao authInfoDao = new AuthInfoDao();
  private final UserDAO userDAO = new UserDAO();

  /**
   * the input parameters are checked - the username and password received from the request
   * if the parameters are checked, then the variables are initialized
   * the parameters are validate using method {@link AuthInfoDao#getById(Serializable)}
   * then a new {@link AuthInfo} object and {@link User} object is created with using valide params
   * if the command successful, user added to system and redirected to index page
   *
   * @param request  {@link Request}
   * @param response {@link Response}
   * @return string name of page
   * @throws DAOException {@link DAOException}
   */
  @Override
  public String execute(Request request, Response response) throws DAOException {
    if (!request.hasAllParameters(PRM_USERNAME, PRM_PASSWORD1, PRM_PASSWORD2)) {
      return Pages.SIGN_UP_PAGE;
    }

    String userName = request.getParameter(PRM_USERNAME);
    String firstName = request.getParameter(PRM_FIRST_NAME);
    String lastName = request.getParameter(PRM_LAST_NAME);
    String password1 = request.getParameter(PRM_PASSWORD1);
    String password2 = request.getParameter(PRM_PASSWORD2);

    if (!Objects.equals(password1, password2) || password1.length() < 4) {
      request.setSessionAttribute("error", "error");
      return Pages.SIGN_UP_PAGE; // password not confirmed, or have incorrect length
    }

    if (!validateUserParams(userName, firstName, lastName, password1, password2)) {
      return Pages.SIGN_UP_PAGE;
    }

    if (authInfoDao.getById(userName) != null) {
      request.addAttribute("error", "exist");
      return Pages.SIGN_UP_PAGE; // user already exists
    }
    String encyptedPassword = DigestUtils.md5Hex(password1);

    AuthInfo authInfo = new AuthInfo(userName, encyptedPassword);

    if (!authInfoDao.create(authInfo)) {
      return Pages.SIGN_UP_PAGE; // could not create auth info
    }

    User user = new User(userName, Role.CLIENT);
    user.setFirstName(firstName);
    user.setLastName(lastName);


    if (!userDAO.create(user)) {
      return Pages.SIGN_UP_PAGE; // could not create user
    }

    User userWithId = userDAO.getByUsername(userName);
    // add current user to session
    request.setSessionAttribute(ATTRIBUTE_USER, userWithId);

    return Pages.INDEX_PAGE;
  }

  private boolean validateUserParams(String... params) {
    for (String param : params) {
      if (!param.isEmpty()) {
        return true;
      }
    }
    return false;
  }
}
