package com.audioord.web.command.track;

import com.audioord.dao.DAOException;
import com.audioord.dao.TrackDAO;
import com.audioord.model.audio.Track;
import com.audioord.web.command.Command;
import com.audioord.web.command.Pages;
import com.audioord.web.http.Request;
import com.audioord.web.http.Response;

import java.io.IOException;

public class AddTrackCartCommand implements Command {

  public static final String NAME = "add_cart";
  public static final String CART_ATTR = "cart";
  private static final String PRM_TRACK_ID = "track_id";
  private TrackDAO trackDAO = new TrackDAO();

  @Override
  public String execute(Request request, Response response) throws IOException, DAOException, ClassNotFoundException {
    if (!request.hasParameter(PRM_TRACK_ID)) {
      return Pages.TRACK_LIST_PAGE;
    }

    // parse track id from request
    String prmTrackId = request.getParameter(PRM_TRACK_ID);
    long trackId = Long.parseLong(prmTrackId);

    // find track by id
    Track track = trackDAO.getById(trackId);
    if (track == null) {
      return Pages.TRACK_LIST_PAGE;
    }

    return Pages.TRACK_LIST_PAGE;
  }

}
