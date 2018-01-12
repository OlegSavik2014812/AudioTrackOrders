package com.audioord.model.order;

import com.audioord.model.Entity;
import com.audioord.model.audio.Track;
import com.audioord.model.account.User;

import java.util.Date;
import java.util.List;

public class TrackOrder extends Entity<Long> {

  private List<Track> tracks;
  private User user;
  private Date dateOrdered;
  private double totalPrice;
  private OrderStatus status;

  public TrackOrder(List<Track> tracks, User user, Date dateOrdered, OrderStatus status) {
    this.tracks = tracks;
    this.user = user;
    this.dateOrdered = dateOrdered;
    this.status = status;
  }

  public List<Track> getTracks() {
    return tracks;
  }

  public void setTracks(List<Track> tracks) {
    this.tracks = tracks;
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

  public boolean isPaid() {
    return tracks != null && !tracks.isEmpty() && totalPrice > 0;
  }

  public OrderStatus getStatus() {
    return status;
  }

  public void setStatus(OrderStatus status) {
    this.status = status;
  }
}
