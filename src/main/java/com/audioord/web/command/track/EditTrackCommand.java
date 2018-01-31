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

  public static final String NAME = "edit_track";
  private TrackDAO trackDAO = new TrackDAO();

  @Override
  public String execute(Request request, Response response) throws IOException, DAOException {
    if (!request.hasAllParameters("trackId", "trackName", "album", "artist", "price")) {
      return Pages.ADD_TRACK_PAGE;
    }

    long trackId = Long.parseLong(request.getParameter("trackId"));
    Track track = trackDAO.getById(trackId);
    track.setName(request.getParameter("trackName"));
    track.setAlbum(request.getParameter("album"));
    track.setArtist(request.getParameter("artist"));
    track.setPrice(Double.parseDouble(request.getParameter("price")));

    track = trackDAO.update(track);
    if (track == null) {
      return Pages.ADD_TRACK_PAGE; // could not update track info
    }


    return Pages.ADD_TRACK_PAGE;
  }

}
