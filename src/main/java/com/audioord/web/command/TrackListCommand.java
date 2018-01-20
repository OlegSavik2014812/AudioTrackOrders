package com.audioord.web.command;

import com.audioord.dao.DAOException;
import com.audioord.dao.TrackDAO;
import com.audioord.model.audio.Track;
import com.audioord.web.http.Request;
import com.audioord.web.http.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TrackListCommand implements Command {

  public static final String NAME = "track_list";
  private static final String PRM_FILTER = "filter";
  private TrackDAO trackDAO = new TrackDAO();

  @Override
  public String execute(Request request, Response response) throws IOException, DAOException {
    int page = 1;
    int recordsPerPage = 10;
    if (request.getParameter("page") != null) {
      page = Integer.parseInt(request.getParameter("page"));
    }
    TrackFilter trackFilter = TrackFilter.fromString(request.getParameter(PRM_FILTER));

    List<Track> trackList = new ArrayList<>();
    int noOfRecords = trackDAO.countTracks();
    int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
    switch (trackFilter) {
      case MOST_POPULAR: {
        trackList = trackDAO.getMostPopularTracks((page - 1) * recordsPerPage,
        recordsPerPage);
        request.addAttribute("Name","most_popular");
        break;
      }
      case BRAND_NEW: {
        trackList = trackDAO.getBrandNewTracks((page - 1) * recordsPerPage,
        recordsPerPage);
        request.addAttribute("Name","brand_new");
        break;
      }
      case BEST_SELLING: {
        trackList = trackDAO.getBestSellingTracks((page - 1) * recordsPerPage,
        recordsPerPage);
        request.addAttribute("Name","best_selling");
        break;
      }
    }
    request.addAttribute("TrackList", trackList);
    request.addAttribute("noOfPages", noOfPages);
    request.addAttribute("currentPage", page);

    return Pages.INDEX_PAGE;
  }

  private enum TrackFilter {
    MOST_POPULAR,
    BEST_SELLING,
    BRAND_NEW;

    public static TrackFilter fromString(String value) {
      for (TrackFilter v : TrackFilter.values()) {
        if (v.name().equalsIgnoreCase(value)) {
          return v;
        }
      }
      return MOST_POPULAR;
    }
  }
}
