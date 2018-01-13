package com.audioord.web.command;

import com.audioord.dao.AuthInfoDao;
import com.audioord.dao.DAOException;
import com.audioord.dao.UserDAO;
import com.audioord.model.account.User;
import com.audioord.model.auth.AuthInfo;
import com.audioord.web.http.Request;
import com.audioord.web.http.Response;

import javax.servlet.ServletException;
import java.io.IOException;

public class SignInCommand implements Command {
  public static final String NAME = "sign_in";
  private static final String INDEX_PATH = "/index.jsp";

  private AuthInfoDao authInfoDao = new AuthInfoDao();
  private UserDAO userDAO = new UserDAO();

  @Override
  public String execute(Request request, Response response) throws ServletException, IOException, DAOException {
    String userName = request.getParameter("userName");
    String password = request.getParameter("password");
    if (userName == null || userName.isEmpty() || password == null) {
      return "/pages/SignIn.jsp";
    }

    AuthInfo authInfo = authInfoDao.getById(userName);
    if (!password.equals(authInfo.getPassword())) {
      return "/pages/SignIn.jsp";
    }

    User user = userDAO.getByUsername(authInfo.getUserName());
    if (user != null) {
      request.raw().getSession().setAttribute("USER", user);
    }

    return INDEX_PATH;
  }
}

