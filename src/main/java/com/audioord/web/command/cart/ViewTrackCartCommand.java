package com.audioord.web.command.cart;

import com.audioord.dao.DAOException;
import com.audioord.model.account.User;
import com.audioord.web.cart.Cart;
import com.audioord.web.command.Command;
import com.audioord.web.command.Pages;
import com.audioord.web.http.Request;
import com.audioord.web.http.Response;


/**
 * View all tracks added to cart
 */
public class ViewTrackCartCommand implements Command {

  public static final String NAME = "view_cart";

  @Override
  public String execute(Request request, Response response) throws DAOException {

    User u = request.getSessionAttribute("USER", User.class);
    Cart cart = request.getSessionAttribute("cart", Cart.class);

    //TODO: fin and add Discount

    return Pages.CART_LIST_PAGE;
  }
}
