package com.audioord.web.cart;


public interface Cart extends Iterable<CartItem> {

  void addCartItem(CartItem c);

  void deleteCartItem(CartItem c);

  void clearCart();

  int getItemsTotalCount();

  double getTotalCost();

}
