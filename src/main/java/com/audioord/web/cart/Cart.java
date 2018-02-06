package com.audioord.web.cart;

import java.util.Set;

/**
 * interface, provides implementation with additional methods
 */
public interface Cart extends Iterable<CartItem> {

  void addCartItem(CartItem c);

  void deleteCartItem(CartItem c);

  void clearCart();

  int getItemsTotalCount();

  double getTotalCost();

  Set<CartItem> getList();

  boolean getHasItems();

}
