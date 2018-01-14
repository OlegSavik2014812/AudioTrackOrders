package com.audioord.web.command;

import com.audioord.dao.DAOException;
import com.audioord.web.http.Request;
import com.audioord.web.http.Response;

import javax.servlet.ServletException;
import java.io.IOException;

public class SignOutCommand implements Command {
  public static final String NAME = "sign_out";
  private static final String INDEX_PATH = "/index.jsp";

  @Override
  public String execute(Request request, Response response) throws ServletException, IOException, DAOException {
    request.raw().getSession().invalidate();
    request.raw().getSession().removeAttribute("USER");
    return INDEX_PATH;
  }
}
