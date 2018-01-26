package com.audioord.web.cart;

import com.audioord.model.account.User;

import java.util.Iterator;
import java.util.List;

public class ShopCart implements Cart {

  private List<CartItem> cartItems;
  private User user;

  public ShopCart(List<CartItem> cartItems, User user) {
    this.cartItems = cartItems;
    this.user = user;
  }

  @Override
  public User getCartUser() {
    return user;
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
