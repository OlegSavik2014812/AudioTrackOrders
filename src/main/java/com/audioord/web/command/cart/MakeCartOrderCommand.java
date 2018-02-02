package com.audioord.web.command.cart;


import com.audioord.dao.DAOException;
import com.audioord.dao.OrderDAO;
import com.audioord.dao.TrackDAO;
import com.audioord.model.account.User;
import com.audioord.model.audio.Track;
import com.audioord.model.order.Order;
import com.audioord.model.order.OrderDiscount;
import com.audioord.model.order.OrderStatus;
import com.audioord.web.cart.Cart;
import com.audioord.web.cart.CartItem;
import com.audioord.web.command.Command;
import com.audioord.web.command.Pages;
import com.audioord.web.http.Request;
import com.audioord.web.http.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MakeCartOrderCommand implements Command {

  public static final String NAME = "cart_order";

  private static final String PRM_CART = "cart";
  private static final String PRM_USER = "USER";
  private static final String PRM_CLEAR = "clear";

  private final OrderDAO orderDAO = new OrderDAO();
  private final TrackDAO trackDAO = new TrackDAO();

  @Override
  public String execute(Request request, Response response) throws DAOException, IOException {
    OrderDiscount orderDiscount = request.getSessionAttribute("BONUS", OrderDiscount.class);

    Cart cart = request.getSessionAttribute(PRM_CART, Cart.class);
    // check if we have items in cart
    if (cart == null || !cart.getHasItems()) {
      return Pages.INDEX_PAGE;
    }

    // ability to clear cart
    if (Boolean.valueOf(request.getParameter(PRM_CLEAR))) {
      cart.clearCart();
      return Pages.INDEX_PAGE;
    }

    List<Track> tracks = getTracks(cart);
    if (tracks.size() != cart.getItemsTotalCount()) {
      // not all tracks exists any more order can not be performed
      return Pages.CART_LIST_PAGE;
    }

    User user = request.getSessionAttribute(PRM_USER, User.class);
    // make new order
    double percent = (100 - orderDiscount.getDiscountPercent()) / 100;

    Order trackOrder = new Order(user, new Date());
    trackOrder.setStatus(OrderStatus.SUBMITTED);
    trackOrder.setTotalPrice(cart.getTotalCost() * percent);
    trackOrder.setTracks(tracks);

    // clear cart after order save
    if (orderDAO.create(trackOrder)) {
      for (Track track : tracks) {
        int popularity = track.getPopularity();
        track.setPopularity(popularity + 1);
        trackDAO.update(track);
      }
      cart.clearCart();
    }

    return Pages.INDEX_PAGE;
  }

  private List<Track> getTracks(Cart c) throws DAOException {
    List<Long> ids = new ArrayList<>();
    for (CartItem cartItem : c) {
      ids.add(cartItem.getItemId());
    }
    return trackDAO.getTracksByIds(ids);
  }
}
