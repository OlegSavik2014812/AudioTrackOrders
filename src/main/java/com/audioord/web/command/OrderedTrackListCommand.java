package com.audioord.web.command;

import com.audioord.dao.DAOException;
import com.audioord.dao.TrackDAO;
import com.audioord.dao.UserDAO;
import com.audioord.model.account.ROLE;
import com.audioord.model.account.User;
import com.audioord.model.audio.Track;
import com.audioord.model.order.OrderStatus;
import com.audioord.web.http.Request;
import com.audioord.web.http.Response;

import java.io.IOException;
import java.util.List;

public class OrderedTrackListCommand implements Command {
  public static final String NAME = "order_list";
  private static final String PRM_ORDER_STATUS = "status";
  private static final String PRM_PAGE = "page";
  private static final String PRM_NAME = "user";
  private TrackDAO trackDAO = new TrackDAO();
  private UserDAO userDAO = new UserDAO();

  @Override
  public String execute(Request request, Response response) throws IOException, DAOException {
    int page = 1;
    int recordsPerPage = 8;
    int noOfPages;
    if (request.hasParameter(PRM_PAGE)) {
      page = Integer.parseInt(request.getParameter(PRM_PAGE));
    }
    if (page < 0) {
      return Pages.INDEX_PAGE;
    }
    User user = request.getSessionAttribute("USER", User.class);
    if (user == null) {
      // user not authorized
      return Pages.SIGN_IN_PAGE;
    }
    String username = user.getUsername();
    List<Track> trackList;
    OrderStatus status = OrderStatus.fromString(request.getParameter(PRM_ORDER_STATUS));
    if (user.getRole().equals(ROLE.CLIENT)) {
      if (status != null) {
        int noOfRecords = trackDAO.countUserSpecialTracks(username, status);
        noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
      } else {
        int noOfRecords = trackDAO.countAllUserTracks(username);
        noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
      }
      if (status != null) {
        trackList = trackDAO.getUserTracks(username, status, (page - 1) * recordsPerPage, recordsPerPage);
      } else {
        trackList = trackDAO.getAllUserTracks(username, (page - 1) * recordsPerPage, recordsPerPage);
      }
      request.addAttribute("TrackList", trackList);
      request.addAttribute("noOfPages", noOfPages);
      request.addAttribute("currentPage", page);
      request.addAttribute("pageName", "order");
      return Pages.PURCHASES_PAGE;
    }
    if (user.getRole().equals(ROLE.ADMIN)) {
      User user1 = userDAO.getByUsername(request.getParameter(PRM_NAME));
      if (status != null) {
        int noOfRecords = trackDAO.countUserSpecialTracks(user1.getUsername(), status);
        noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
      } else {
        int noOfRecords = trackDAO.countAllUserTracks(user1.getUsername());
        noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
      }
      if (status != null) {
        trackList = trackDAO.getUserTracks(user1.getUsername(), status, (page - 1) * recordsPerPage, recordsPerPage);
      } else {
        trackList = trackDAO.getAllUserTracks(user1.getUsername(), (page - 1) * recordsPerPage, recordsPerPage);
      }
      request.addAttribute("TrackList", trackList);
      request.addAttribute("noOfPages", noOfPages);
      request.addAttribute("currentPage", page);
      request.addAttribute("user", user1.getUsername());
      request.addAttribute("pageName", "order");
      return Pages.PURCHASES_PAGE;
    }
    return Pages.INDEX_PAGE;
  }

}
