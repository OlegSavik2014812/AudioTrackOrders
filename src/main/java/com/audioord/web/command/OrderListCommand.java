package com.audioord.web.command;

import com.audioord.dao.DAOException;
import com.audioord.dao.TrackDAO;
import com.audioord.model.audio.Track;
import com.audioord.web.http.Request;
import com.audioord.web.http.Response;

import java.io.IOException;
import java.util.List;

public class OrderListCommand implements Command {
  public static final String NAME = "order_list";
  private TrackDAO trackDAO = new TrackDAO();

  @Override
  public String execute(Request request, Response response) throws IOException, DAOException {
    String userName = String.valueOf(request.raw().getSession().getAttribute("username"));
    List<Track> trackList = trackDAO.getUserTracks(userName);
    request.addAttribute("TrackList", trackList);
    return Pages.PURCHASES_PAGE;
  }
}
