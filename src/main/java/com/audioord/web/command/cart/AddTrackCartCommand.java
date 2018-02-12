package com.audioord.web.command.cart;

import com.audioord.dao.DAOException;
import com.audioord.dao.TrackDAO;
import com.audioord.model.account.User;
import com.audioord.model.audio.Track;
import com.audioord.web.cart.Cart;
import com.audioord.web.cart.CartItem;
import com.audioord.web.cart.ShopCart;
import com.audioord.web.command.Command;
import com.audioord.web.command.Pages;
import com.audioord.web.http.Request;
import com.audioord.web.http.Response;

import java.io.IOException;

/**
 * Class describes the object-command, which add {@link CartItem} object to signed
 * user's {@link Cart} object
 * implementation of {@link Command}
 */
public class AddTrackCartCommand implements Command {

  public static final String NAME = "add_cart";
  private static final String PRM_CART = "cart";
  private static final String PRM_TRACK_ID = "track_id";

  private final TrackDAO trackDAO = new TrackDAO();

  /**
   * the input parameters are checked,then they received from the request
   * creates a track and a  user cart of input parameters
   * then track added to cart
   * cart added to seesion
   * if the command successful, user cart added to session and redirection to track list page
   *
   * @param request  {@link Request}
   * @param response {@link Response}
   * @return string name of page
   * @throws IOException  in case, when params incorrect
   * @throws DAOException {@link DAOException}
   */
  @Override
  public String execute(Request request, Response response) throws IOException, DAOException {
    if (!request.hasParameter(PRM_TRACK_ID)) {
      return Pages.TRACK_LIST_PAGE;
    }
    User user = request.getSessionAttribute("USER", User.class);
    if (user == null) {
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
    if (user.getCash() - track.getPrice() < 0) {
      return Pages.TRACK_LIST_PAGE;
    }
    // put selected track to user session cart
    Cart cart = request.getSessionAttribute(PRM_CART, Cart.class);
    if (cart == null) {
      cart = new ShopCart();
      request.setSessionAttribute(PRM_CART, cart);
    }
    cart.addCartItem(track);

    return Pages.TRACK_LIST_PAGE;
  }

}
