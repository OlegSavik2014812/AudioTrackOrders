package com.audioord.model.order;

import com.audioord.model.Entity;
import com.audioord.model.account.User;
import com.audioord.model.audio.Track;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Class describing object for storing information about Order, extends {@link Entity}
 */
public class Order extends Entity<Long> {

  private User user;
  private Date dateOrdered;
  private double totalPrice;
  private OrderStatus status;
  private List<Track> tracks;

  /**
   * constructor of {@link Order} with params
   *
   * @param user        {@link User} object
   * @param dateOrdered date when ordered
   */
  public Order(User user, Date dateOrdered) {
    this.user = user;
    this.dateOrdered = dateOrdered;
  }

  /**
   * @return {@link User} object
   */
  public User getUser() {
    return user;
  }

  /**
   * @param user {@link User} object
   */
  public void setUser(User user) {
    this.user = user;
  }

  /**
   * @return order date
   */
  public Date getDateOrdered() {
    return dateOrdered;
  }

  public void setDateOrdered(Date dateOrdered) {
    this.dateOrdered = dateOrdered;
  }

  /**
   * @return total price
   */
  public double getTotalPrice() {
    return totalPrice;
  }

  /**
   * Sets total price
   *
   * @param totalPrice total price
   */
  public void setTotalPrice(double totalPrice) {
    this.totalPrice = totalPrice;
  }

  /**
   * @return {@link OrderStatus} status
   */
  public OrderStatus getStatus() {
    return status;
  }

  /**
   * @param status {@link OrderStatus} status
   */
  public void setStatus(OrderStatus status) {
    this.status = status;
  }

  /**
   * @return list of {@link Track} objects
   */
  public List<Track> getTracks() {
    return tracks;
  }

  /**
   * @param tracks list of {@link Track} objects
   */
  public void setTracks(List<Track> tracks) {
    this.tracks = tracks;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Order)) return false;
    if (!super.equals(o)) return false;
    Order order = (Order) o;
    return Double.compare(order.totalPrice, totalPrice) == 0 &&
    Objects.equals(user, order.user) &&
    Objects.equals(dateOrdered, order.dateOrdered) &&
    status == order.status &&
    Objects.equals(tracks, order.tracks);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), user, dateOrdered, totalPrice, status, tracks);
  }

  @Override
  public String toString() {
    return "Order{" +
    "user=" + user +
    ", dateOrdered=" + dateOrdered +
    ", totalPrice=" + totalPrice +
    ", status=" + status +
    ", tracks=" + tracks +
    '}';
  }
}
