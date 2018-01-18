package com.audioord.web.command;

import com.audioord.dao.DAOException;
import com.audioord.dao.TrackDAO;
import com.audioord.model.audio.Track;
import com.audioord.web.http.Request;
import com.audioord.web.http.Response;

import java.io.IOException;

public class AdditionNewTrackCommand implements Command {
  public static final String NAME = "add_track";

  private TrackDAO trackDAO = new TrackDAO();

  @Override
  public String execute(Request request, Response response) throws IOException, DAOException {
    if (!request.hasAllParameters("trackName", "artist")) {
      return Pages.ADD_TRACK_PAGE;
    }
    String name = request.getParameter("trackName");
    String artist = request.getParameter("artist");
    String popularityParam = request.getParameter("popularity");
    String durationParam = request.getParameter("duration");
    String priceParam = request.getParameter("price");
    int popularity = 0;
    double duration = 0;
    int price = 0;
    if (popularityParam != null && !popularityParam.isEmpty()) {
      popularity = Integer.parseInt(popularityParam);
    }
    if (durationParam != null && !durationParam.isEmpty()) {
      duration = Double.parseDouble(durationParam);
    }
    if (priceParam != null && !priceParam.isEmpty()) {
      price = Integer.parseInt(priceParam);
    }

    Track track = new Track(name, artist);
    track.setAlbum(request.getParameter("album"));
    track.setUri(request.getParameter("uri"));
    track.setPopularity(popularity);
    track.setDuration(duration);
    track.setPrice(price);


    if (!trackDAO.create(track)) {
      return Pages.ADD_TRACK_PAGE;
    }

    return Pages.ADD_TRACK_PAGE;
  }
}
