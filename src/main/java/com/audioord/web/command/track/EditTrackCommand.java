package com.audioord.web.command.track;

import com.audioord.dao.DAOException;
import com.audioord.dao.TrackDAO;
import com.audioord.model.audio.Track;
import com.audioord.web.command.Command;
import com.audioord.web.command.Pages;
import com.audioord.web.http.Request;
import com.audioord.web.http.Response;

import java.io.IOException;

/**
 * Class describes the object-command, which edit new track and returns to add track page
 * implementation of {@link Command}
 */
public class EditTrackCommand implements Command {

  public static final String NAME = "edit_track";
  private static final String PRM_TRACK_ID = "trackId";
  private static final String PRM_TRACK_NAME = "trackName";
  private static final String PRM_TRACK_ALBUM = "album";
  private static final String PRM_TRACK_ARTIST = "artist";
  private static final String PRM_TRACK_PRICE = "price";
  private final TrackDAO trackDAO = new TrackDAO();

  /**
   * there is a check for the presence of the required input data,
   * if at least one parameter is not present, then there is a redirection to add track page
   * the variables are initialized and the {@link Track} object is created
   * then the object is checked for null and otherwise the redirection to add track page
   * <p>
   * if the command is executed successfully, then a new track is created and there is redirection to add track page
   *
   * @param request  {@link Request}
   * @param response {@link Response}
   * @return string name of page
   * @throws IOException  in case, when params incorrect
   * @throws DAOException {@link DAOException}
   */
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
