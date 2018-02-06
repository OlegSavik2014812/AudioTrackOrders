package com.audioord.web.cart;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Class describing object for storing information about User Cart
 */
public class ShopCart implements Cart {

  private Set<CartItem> cartItems;

  /**
   * constructor, initialize list of {@link CartItem} objects
   */
  public ShopCart() {
    this.cartItems = new HashSet<>();
  }

  public ShopCart(Set<CartItem> cartItems) {
    this.cartItems = cartItems;
  }

  /**
   * @return {@link Iterator} with param {@link CartItem}
   */
  @Override
  public Iterator<CartItem> iterator() {
    return cartItems.iterator();
  }

  /**
   * add {@link CartItem} object to list
   *
   * @param c {@link CartItem} implementation
   */
  @Override
  public void addCartItem(CartItem c) {
    cartItems.add(c);
  }

  /**
   * delete {@link CartItem} object from list
   *
   * @param c {@link CartItem} implementation
   */
  @Override
  public void deleteCartItem(CartItem c) {
    cartItems.remove(c);
  }

  /**
   * clears list of {@link CartItem} objects
   */
  @Override
  public void clearCart() {
    cartItems.clear();
  }

  /**
   * @return size
   */
  @Override
  public int getItemsTotalCount() {
    return cartItems.size();
  }

  /**
   * sum total price of each {@link CartItem} object in list
   *
   * @return total cost
   */
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

  /**
   * Sets list of {@link CartItem} objects unmodifiable
   *
   * @return unmodifiable list of {@link CartItem} objects
   */
  @Override
  public Set<CartItem> getList() {
    return Collections.unmodifiableSet(cartItems);
  }

  /**
   * @return if list has items - true , otherwise - false
   */
  public boolean getHasItems() {
    return cartItems != null && !cartItems.isEmpty();
  }

}
