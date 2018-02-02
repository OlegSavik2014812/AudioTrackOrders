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

public class UserTracksCommand implements Command {

  public static final String NAME = "user_tracks";

  private final TrackDAO trackDAO = new TrackDAO();

  @Override
  public String execute(Request request, Response response) throws DAOException, IOException {
    User user = request.getSessionAttribute("USER", User.class);

    List<Track> allUserTracks = trackDAO.getAllUserTracks(user.getId());

    request.addAttribute("tracks", allUserTracks);
    return Pages.USER_TRACKS_PAGE;
  }
}
