package com.audioord.web.cart;


import com.audioord.model.account.User;

public interface Cart extends Iterable<CartItem> {

  User getCartUser();

  void addCartItem(CartItem c);

  void deleteCartItem(CartItem c);

  void clearCart();

  int getItemsTotalCount();

  double getTotalCost();

}
