package com.audioord.model.order;

import com.audioord.model.Entity;
import com.audioord.model.account.User;
import com.audioord.model.audio.Track;

import java.util.Date;
import java.util.List;

public class Order extends Entity<Long> {

  private User user;
  private Date dateOrdered;
  private double totalPrice;
  private OrderStatus status;
  private List<Track> tracks;

  public Order(User user, Date dateOrdered) {
    this.user = user;
    this.dateOrdered = dateOrdered;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Date getDateOrdered() {
    return dateOrdered;
  }

  public void setDateOrdered(Date dateOrdered) {
    this.dateOrdered = dateOrdered;
  }

  public double getTotalPrice() {
    return totalPrice;
  }

  public void setTotalPrice(double totalPrice) {
    this.totalPrice = totalPrice;
  }

  public OrderStatus getStatus() {
    return status;
  }

  public void setStatus(OrderStatus status) {
    this.status = status;
  }

  public List<Track> getTracks() {
    return tracks;
  }

  public void setTracks(List<Track> tracks) {
    this.tracks = tracks;
  }
}
