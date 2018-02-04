package com.audioord.web.command.track;

import com.audioord.dao.DAOException;
import com.audioord.dao.TrackDAO;
import com.audioord.model.audio.Track;
import com.audioord.web.command.Command;
import com.audioord.web.command.Pages;
import com.audioord.web.http.Request;
import com.audioord.web.http.Response;

import java.io.IOException;
import java.util.List;

/**
 * Class describes the object-command, which search track and returns to track list page
 * implementation of {@link Command}
 */
public class SearchTrackCommand implements Command {

  public static final String NAME = "search_track";
  private static final String PRM_SEARCH = "track_name";
  private static final String ATTRIBUTE_TRACK_LIST = "TrackList";
  private final TrackDAO trackDAO = new TrackDAO();

  /**
   * there is a check for the presence of the required input data,
   * if at least one parameter is not present, then there is a redirection to track list page
   * Next, a search is made for the name of the track and a it's assign link to list of {@link Track} objects
   * if command executed successfully, then creation of track list an d redirection to track list page
   *
   * @param request  {@link Request}
   * @param response {@link Response}
   * @return string name of page
   * @throws IOException  in case, when params incorrect
   * @throws DAOException {@link DAOException}
   */
  @Override
  public String execute(Request request, Response response) throws IOException, DAOException {
    if (!request.hasAllParameters(PRM_SEARCH)) {
      return Pages.TRACK_LIST_PAGE;
    }

    String trackName = request.getParameter(PRM_SEARCH);
    List<Track> tracks = trackDAO.findTracks(trackName);
    request.addAttribute(ATTRIBUTE_TRACK_LIST, tracks);
    return Pages.TRACK_LIST_PAGE;
  }
}

