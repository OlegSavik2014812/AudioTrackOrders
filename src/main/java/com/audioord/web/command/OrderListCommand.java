package com.audioord.web.command;

import com.audioord.dao.DAOException;
import com.audioord.dao.TrackDAO;
import com.audioord.model.account.User;
import com.audioord.model.audio.Track;
import com.audioord.web.http.Request;
import com.audioord.web.http.Response;

import java.io.IOException;
import java.util.List;

public class OrderListCommand implements Command {
  public static final String NAME = "order_list";
  private static final String ORDER_FILTER = "filter";
  private TrackDAO trackDAO = new TrackDAO();

  @Override
  public String execute(Request request, Response response) throws IOException, DAOException {
    OrderListFilter orderListFilter = OrderListFilter.fromString(request.getParameter(ORDER_FILTER));
    String filter = orderListFilter.toString();

    User user = request.getSessionAttribute("USER", User.class);
    if (user == null) {
      // user not authorized
      return Pages.INDEX_PAGE;
    }

    List<Track> trackList = null;
    String username = user.getUsername();
    switch (orderListFilter) {
      case COMPLETED: {
        trackList = trackDAO.getUserTrackss(username, filter);
        break;
      }
      case REJECTED: {
        trackList = trackDAO.getUserTrackss(username, filter);
        break;
      }
      case SUBMITTED: {
        trackList = trackDAO.getUserTrackss(username, filter);
        break;
      }
      case ALL: {
        trackList = trackDAO.getAllUserTracks(username);
      }
    }

    request.addAttribute("TrackList", trackList);
    return Pages.PURCHASES_PAGE;
  }

  private enum OrderListFilter {
    REJECTED,
    COMPLETED,
    SUBMITTED,
    ALL;

    public static OrderListFilter fromString(String value) {
      for (OrderListFilter v : OrderListFilter.values()) {
        if (v.name().equalsIgnoreCase(value)) {
          return v;
        }
      }
      return ALL;
    }
  }
}
