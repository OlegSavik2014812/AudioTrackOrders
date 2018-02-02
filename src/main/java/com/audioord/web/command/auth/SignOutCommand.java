package com.audioord.web.command.auth;

import com.audioord.dao.DAOException;
import com.audioord.web.command.Command;
import com.audioord.web.command.Pages;
import com.audioord.web.http.Request;
import com.audioord.web.http.Response;

import java.io.IOException;

public class SignOutCommand implements Command {

  public static final String NAME = "sign_out";
  private static final String ATTRIBUTE_USER = "USER";

  @Override
  public String execute(Request request, Response response) throws DAOException, IOException {

    request.invalidateSession();
    request.removeSessionAttribute(ATTRIBUTE_USER);

    return Pages.INDEX_PAGE;
  }
}
