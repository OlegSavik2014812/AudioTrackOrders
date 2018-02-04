package com.audioord.web.command.track;

import com.audioord.dao.DAOException;
import com.audioord.dao.TrackDAO;
import com.audioord.model.audio.Track;
import com.audioord.web.command.Command;
import com.audioord.web.command.Pages;
import com.audioord.web.http.Request;
import com.audioord.web.http.Response;

import java.util.ArrayList;
import java.util.List;

/**
 * Class describes the object-command, which shows all tracks in the system and returns to track list page
 * implementation of {@link Command}
 */
public class TrackListCommand implements Command {

  public static final String NAME = "track_list";
  private static final String PRM_SORT = "sort";
  private static final String PRM_PAGE = "page";

  private static final String ATTRIBUTE_SORT = "sort";
  private static final String ATTRIBUTE_TRACK_LIST = "TrackList";
  private static final String ATTRIBUTE_NUMBER_OF_PAGES = "noOfPages";
  private static final String ATTRIBUTE_CURRENT_PAGE = "currentPage";
  private final TrackDAO trackDAO = new TrackDAO();

  /**
   * The input parameters for paging are checked if they are not valid. then the default value is initialized
   * Further, the parameter is extracted for sorting, and if this parameter is valid, then a sorted track list is created
   * if command executed successfully, then creation of list of {@link Track} objects and redirection to track list page
   *
   * @param request  {@link Request}
   * @param response {@link Response}
   * @return name page string
   * @throws DAOException {@link DAOException}
   */
  @Override
  public String execute(Request request, Response response) throws DAOException {
    int page = 1;
    int recordsPerPage = 8;

    if (request.hasParameter(PRM_PAGE)) {
      page = Integer.parseInt(request.getParameter(PRM_PAGE));
    }
    TrackSort trackSort = TrackSort.fromString(request.getParameter(PRM_SORT));

    List<Track> trackList = new ArrayList<>();
    int noOfRecords = trackDAO.countAllTracks();
    int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
    switch (trackSort) {
      case MOST_POPULAR: {
        trackList = trackDAO.getMostPopularTracks((page - 1) * recordsPerPage,
        recordsPerPage);
        break;
      }
      case BRAND_NEW: {
        trackList = trackDAO.getBrandNewTracks((page - 1) * recordsPerPage,
        recordsPerPage);
        break;
      }
      case BEST_SELLING: {
        trackList = trackDAO.getBestSellingTracks((page - 1) * recordsPerPage,
        recordsPerPage);
        break;
      }
    }

    request.addAttribute(ATTRIBUTE_SORT, trackSort.name());
    request.addAttribute(ATTRIBUTE_TRACK_LIST, trackList);
    request.addAttribute(ATTRIBUTE_NUMBER_OF_PAGES, noOfPages);
    request.addAttribute(ATTRIBUTE_CURRENT_PAGE, page);

    return Pages.TRACK_LIST_PAGE;
  }

  private enum TrackSort {
    MOST_POPULAR,
    BEST_SELLING,
    BRAND_NEW;

    public static TrackSort fromString(String value) {
      for (TrackSort v : TrackSort.values()) {
        if (v.name().equalsIgnoreCase(value)) {
          return v;
        }
      }
      return MOST_POPULAR;
    }
  }
}
