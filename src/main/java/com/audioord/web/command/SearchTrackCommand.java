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
  private static final String PRM_SEARCH = "track_name";
  private TrackDAO trackDAO = new TrackDAO();

  @Override
  public String execute(Request request, Response response) throws IOException, DAOException {
    if (!request.hasAllParameters(PRM_SEARCH)) {
      return Pages.TRACK_LIST_PAGE;
    }

    String trackName = request.getParameter(PRM_SEARCH);
    List<Track> tracks = trackDAO.findTracks(trackName);
    request.addAttribute("TrackList", tracks);
    return Pages.TRACK_LIST_PAGE;
  }
}

