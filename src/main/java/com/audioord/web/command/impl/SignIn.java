package com.audioord.web.command.impl;

import com.audioord.web.command.Command;
import com.audioord.web.http.Request;
import com.audioord.web.http.Response;

public class SignIn implements Command {
  private static final String SIGN_IN_URL = "audioord/action?name=sign_in";

  @Override
  public String execute(Request request, Response response) {
    /*String page;
    page = */
    return "/";
  }
}
