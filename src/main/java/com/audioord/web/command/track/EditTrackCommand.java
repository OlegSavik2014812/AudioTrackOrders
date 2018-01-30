package com.audioord.web.command.track;

import com.audioord.dao.DAOException;
import com.audioord.dao.TrackDAO;
import com.audioord.model.audio.Track;
import com.audioord.web.command.Command;
import com.audioord.web.command.Pages;
import com.audioord.web.http.Request;
import com.audioord.web.http.Response;

import java.io.IOException;

public class EditTrackCommand implements Command {

  public static final String NAME = "add_track";
  private static final String PRM_TRACK = "track";

  private TrackDAO trackDAO = new TrackDAO();

  @Override
  public String execute(Request request, Response response) throws IOException, DAOException {
    Track track = request.getAttribute(PRM_TRACK, Track.class);
    if (track == null) {
      return Pages.ADD_TRACK_PAGE;
    }

    track.setName(request.getParameter("name"));
    track.setAlbum(request.getParameter("album"));
    track.setArtist(request.getParameter("artist"));
    track.setPrice(Double.parseDouble(request.getParameter("price")));

    if (trackDAO.create(track)) {
      request.raw().removeAttribute(PRM_TRACK);
    }

    return Pages.ADD_TRACK_PAGE;
  }

}
