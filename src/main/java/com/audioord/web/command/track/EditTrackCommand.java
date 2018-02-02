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
  private static final String PRM_TRACK_ID = "trackId";
  private static final String PRM_TRACK_NAME = "trackName";
  private static final String PRM_TRACK_ALBUM = "album";
  private static final String PRM_TRACK_ARTIST = "artist";
  private static final String PRM_TRACK_PRICE = "price";
  private final TrackDAO trackDAO = new TrackDAO();

  @Override
  public String execute(Request request, Response response) throws IOException, DAOException {
    if (!request.hasAllParameters(PRM_TRACK_ID, PRM_TRACK_NAME, PRM_TRACK_ALBUM, PRM_TRACK_ARTIST, PRM_TRACK_PRICE)) {
      return Pages.ADD_TRACK_PAGE;
    }

    long trackId = Long.parseLong(request.getParameter(PRM_TRACK_ID));
    Track track = trackDAO.getById(trackId);
    track.setName(request.getParameter(PRM_TRACK_NAME));
    track.setAlbum(request.getParameter(PRM_TRACK_ALBUM));
    track.setArtist(request.getParameter(PRM_TRACK_ARTIST));
    track.setPrice(Double.parseDouble(request.getParameter(PRM_TRACK_PRICE)));

    track = trackDAO.update(track);
    if (track == null) {
      return Pages.ADD_TRACK_PAGE; // could not update track info
    }

    return Pages.ADD_TRACK_PAGE;
  }

}
