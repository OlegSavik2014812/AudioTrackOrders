package com.audioord.web.command;

import com.audioord.dao.DAOException;
import com.audioord.dao.TrackDAO;
import com.audioord.model.audio.Track;
import com.audioord.web.http.Request;
import com.audioord.web.http.Response;

import java.io.IOException;
import java.net.URI;

public class AdditionNewTrackCommand implements Command {
  public static final String NAME = "add_track";

  private TrackDAO trackDAO = new TrackDAO();

  @Override
  public String execute(Request request, Response response) throws IOException, DAOException {
    if (!request.hasAllParameters("trackName", "artist", "album")) {
      return Pages.ADD_TRACK_PAGE;
    }
    String name = request.getParameter("trackName");
    String artist = request.getParameter("artist");
    String album = request.getParameter("album");
    Track track = new Track(name, artist, album);
    track.setPopularity(Integer.parseInt(request.getParameter("popularity")));
    track.setDuration(Long.parseLong(request.getParameter("duration")));
    track.setUri(URI.create(request.getParameter("uri")));
    track.setPrice(Integer.parseInt(request.getParameter("price")));
    if (!trackDAO.create(track)) {
      return Pages.ADD_TRACK_PAGE;
    }

    return Pages.ADD_TRACK_PAGE;
  }
}
