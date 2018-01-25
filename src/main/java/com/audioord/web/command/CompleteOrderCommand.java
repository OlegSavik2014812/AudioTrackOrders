package com.audioord.web.command;

import com.audioord.dao.DAOException;
import com.audioord.dao.TrackOrderDAO;
import com.audioord.model.account.ROLE;
import com.audioord.model.account.User;
import com.audioord.model.order.TrackOrder;
import com.audioord.web.http.Request;
import com.audioord.web.http.Response;

import java.io.IOException;

public class CompleteOrderCommand implements Command {
  public static final String NAME = "submit_order";
  private static final String PRM_ID_ORDER = "id_order";
  private TrackOrderDAO trackOrderDAO = new TrackOrderDAO();

  @Override
  public String execute(Request request, Response response) throws IOException, DAOException, ClassNotFoundException {
    User user = request.getSessionAttribute("USER", User.class);
    if (user == null) {
      return Pages.SIGN_IN_PAGE;
    }
    if (!user.getRole().equals(ROLE.ADMIN)) {
      return Pages.SIGN_IN_PAGE;
    }
    if (!request.hasAllParameters(PRM_ID_ORDER)) {
      return Pages.INDEX_PAGE;
    }
    int id = Integer.parseInt(request.getParameter(PRM_ID_ORDER));
    TrackOrder trackOrder = trackOrderDAO.getById((long) id);
    if (trackOrder == null) {
      return Pages.ALL_PURCHASES;
    }
    if (!trackOrderDAO.submitTrackOrder(trackOrder)) {
      return Pages.ALL_PURCHASES;
    }
    return "/action?name=purchases_list&page=1";
  }
}
