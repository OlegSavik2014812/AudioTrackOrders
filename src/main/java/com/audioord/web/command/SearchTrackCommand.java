package com.audioord.web.command;

import com.audioord.dao.DAOException;
import com.audioord.dao.TrackDAO;
import com.audioord.model.audio.Track;
import com.audioord.web.http.Request;
import com.audioord.web.http.Response;

import java.io.IOException;
import java.util.List;

public class SearchTrackCommand implements Command {
  public static final String NAME = "search_track";
  private static final String PRM_SEARCH = "trackname";
  private TrackDAO trackDAO = new TrackDAO();

  @Override
  public String execute(Request request, Response response) throws IOException, DAOException {
    List<Track> trackList;
    String parameter = request.getParameter(PRM_SEARCH);
    trackList = trackDAO.getByParam(parameter);
    request.addAttribute("TrackList", trackList);
    return Pages.INDEX_PAGE;
  }
}

