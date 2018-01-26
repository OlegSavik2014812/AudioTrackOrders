package com.audioord.web.cart;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class ShopCart implements Cart {

  private Collection<CartItem> cartItems;

  public ShopCart() {
    this.cartItems = new ArrayList<>();
  }

  public ShopCart(Collection<CartItem> cartItems) {
    this.cartItems = cartItems;
  }

  @Override
  public void addCartItem(CartItem c) {
    cartItems.add(c);
  }

  @Override
  public void deleteCartItem(CartItem c) {
    cartItems.remove(c);
  }

  @Override
  public void clearCart() {
    cartItems.clear();
  }

  @Override
  public int getItemsTotalCount() {
    return cartItems.size();
  }

  @Override
  public double getTotalCost() {
    double totalCost = 0;
    if (hasItems()) {
      for (CartItem cartItem : cartItems) {
        totalCost += cartItem.getCost();
      }
    }
    return totalCost;
  }

  @Override
  public Iterator<CartItem> iterator() {
    return cartItems.iterator();
  }

  public boolean hasItems() {
    return cartItems != null && !cartItems.isEmpty();
  }

}
