package com.audioord.web.cart;


import java.util.List;

public interface Cart extends Iterable<CartItem> {

  void addCartItem(CartItem c);

  void deleteCartItem(CartItem c);

  void clearCart();

  int getItemsTotalCount();

  double getTotalCost();

  List<CartItem> getList();

  boolean getHasItems();

}
