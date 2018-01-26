package com.audioord.web.cart;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ShopCart implements Cart {

  private List<CartItem> cartItems;

  public ShopCart() {
    this.cartItems = new ArrayList<>();
  }

  public ShopCart(List<CartItem> cartItems) {
    this.cartItems = cartItems;
  }

  @Override
  public Iterator<CartItem> iterator() {
    return cartItems.iterator();
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
    if (getHasItems()) {
      for (CartItem cartItem : cartItems) {
        totalCost += cartItem.getCost();
      }
    }
    return totalCost;
  }

  @Override
  public List<CartItem> getList() {
    return Collections.unmodifiableList(cartItems);
  }

  public boolean getHasItems() {
    return cartItems != null && !cartItems.isEmpty();
  }

}
