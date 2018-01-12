package com.audioord.model.order;

import com.audioord.model.Entity;
import com.audioord.model.account.User;

import java.util.Date;

public class TrakcFeedback extends Entity<Long> {

  private User user;
  private TrackOrder order;
  private String comments;
  private Date createdAt;

  public TrakcFeedback(User user, TrackOrder order, String comments, Date createdAt) {
    this.user = user;
    this.order = order;
    this.comments = comments;
    this.createdAt = createdAt;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public TrackOrder getOrder() {
    return order;
  }

  public void setOrder(TrackOrder order) {
    this.order = order;
  }

  public String getComments() {
    return comments;
  }

  public void setComments(String comments) {
    this.comments = comments;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }
}
