package com.audioord.web.cart;

/**
 * interface, provides implementation with additional methods
 */
public interface CartItem {

  Long getItemId();

  String getDisplayName();

  double getCost();

}
