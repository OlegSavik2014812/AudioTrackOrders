package com.audioord.web.command;

import com.audioord.dao.DAOException;
import com.audioord.web.http.Request;
import com.audioord.web.http.Response;

import java.io.IOException;

public class SignOutCommand implements Command {

  public static final String NAME = "sign_out";

  @Override
  public String execute(Request request, Response response) throws IOException, DAOException {

    request.invalidateSession();
    request.removeSessionAttribute("USER");

    return Pages.INDEX_PAGE;
  }
}
