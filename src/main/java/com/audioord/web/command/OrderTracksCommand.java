package com.audioord.web.command;

import com.audioord.dao.DAOException;
import com.audioord.web.http.Request;
import com.audioord.web.http.Response;

import java.io.IOException;


public class OrderTracksCommand implements Command {
  public static final String NAME = "order_tracks";

  @Override
  public String execute(Request request, Response response) throws IOException, DAOException {
    String mass = request.raw().getParameter("check");
    return null;
  }
}
