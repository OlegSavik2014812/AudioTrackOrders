package com.audioord.web.command;

import com.audioord.dao.DAOException;
import com.audioord.dao.TrackDAO;
import com.audioord.model.audio.Track;
import com.audioord.web.http.Request;
import com.audioord.web.http.Response;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TrackListCommand implements Command {


  public static final String NAME = "filter";
  private static final String INDEX_PATH = "/index.jsp";
  private TrackDAO trackDAO = new TrackDAO();
  private enum TrackFilter {
    MOST_POPULAR,
    BEST_SELLING,
    BRAND_NEW
  }

  private TrackFilter getFilterParam(Request request) {
    for (TrackFilter v : TrackFilter.values()) {
      if (v.name().equalsIgnoreCase(request.getParameter(NAME))) {
        return v;
      }
    }
    return TrackFilter.MOST_POPULAR;
  }

  @Override
  public String execute(Request request, Response response) throws ServletException, IOException, DAOException {
    TrackFilter trackFilter = getFilterParam(request);

    List<Track> trackList = new ArrayList<>();
    switch (trackFilter) {
      case MOST_POPULAR: {
        trackList = trackDAO.getMostPoularTracks();
        break;
      }
      case BRAND_NEW: {
        trackList = trackDAO.getBrandNewTracks();
        break;
      }
      case BEST_SELLING: {
        trackList = trackDAO.getBestSellingTracks();
        break;
      }

    }
    request.addAttribute("TrackList", trackList);

    return INDEX_PATH;

  }
}
