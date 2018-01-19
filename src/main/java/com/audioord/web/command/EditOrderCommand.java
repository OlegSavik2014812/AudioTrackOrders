package com.audioord.web.command;

import com.audioord.dao.DAOException;
import com.audioord.dao.TrackDAO;
import com.audioord.dao.TrackOrderDAO;
import com.audioord.model.account.User;
import com.audioord.model.audio.Track;
import com.audioord.model.order.OrderStatus;
import com.audioord.model.order.TrackOrder;
import com.audioord.web.http.Request;
import com.audioord.web.http.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EditOrderCommand implements Command {
  public static final String NAME = "make_order";
  private TrackDAO trackDAO = new TrackDAO();
  private TrackOrderDAO trackOrderDAO = new TrackOrderDAO();

  @Override
  public String execute(Request request, Response response) throws IOException, DAOException, ClassNotFoundException {
    double totalPrice = 0;
    Date date = new Date();
    User user = request.getSessionAttribute("USER", User.class);
    List<Track> tracks = new ArrayList<>();
    String[] list = request.raw().getParameterValues("check");
    for (String s : list) {
      Track track = trackDAO.getByParam(s).get(0);
      totalPrice += track.getPrice();
      tracks.add(track);
    }
    TrackOrder trackOrder = new TrackOrder(tracks, user, date, OrderStatus.SUBMITTED);
    trackOrder.setTotalPrice(totalPrice);
    if (!trackOrderDAO.create(trackOrder)) {
      return Pages.INDEX_PAGE;
    }

    return Pages.INDEX_PAGE;
  }
}
