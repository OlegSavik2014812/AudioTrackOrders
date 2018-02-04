package com.audioord.web.command.track;

import com.audioord.dao.DAOException;
import com.audioord.dao.TrackDAO;
import com.audioord.model.account.User;
import com.audioord.model.audio.Track;
import com.audioord.web.command.Command;
import com.audioord.web.command.Pages;
import com.audioord.web.http.Request;
import com.audioord.web.http.Response;

import java.io.IOException;
import java.util.List;

/**
 * Class describes the object-command, which shows user all paid tracks and returns to user tracks page
 * implementation of {@link Command}
 */
public class UserTracksCommand implements Command {

  public static final String NAME = "user_tracks";
  private static final String ATTRIBUTE_USER = "USER";
  private static final String ATTRIBUTE_USER_TRACKS = "tracks";
  private final TrackDAO trackDAO = new TrackDAO();

  /**
   * if params are valid, then assignment for user link an {@link User} object extracted from request
   * and creation of list of track, which are paid by user
   * if command is executed successfully, then creation of user's track list and redirection to user tracks page
   *
   * @param request  {@link Request}
   * @param response {@link Response}
   * @return string name of page
   * @throws IOException  in case, when params incorrect
   * @throws DAOException {@link DAOException}
   */
  @Override
  public String execute(Request request, Response response) throws DAOException, IOException {
    User user = request.getSessionAttribute(ATTRIBUTE_USER, User.class);

    List<Track> allUserTracks = trackDAO.getAllUserTracks(user.getId());

    request.addAttribute(ATTRIBUTE_USER_TRACKS, allUserTracks);
    return Pages.USER_TRACKS_PAGE;
  }
}
