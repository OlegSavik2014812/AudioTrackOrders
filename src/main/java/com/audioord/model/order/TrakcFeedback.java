package com.audioord.model.order;

import com.audioord.model.Entity;
import com.audioord.model.account.User;
import com.audioord.model.audio.Track;

import java.util.Date;

public class TrakcFeedback extends Entity<Long> {

  private User user;
  private String comments;
  private Date createdAt;
  private Track track;

  public TrakcFeedback(User user, String comments, Track track) {
    this.user = user;
    this.comments = comments;
    this.track = track;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
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

  public Track getTrack() {
    return track;
  }

  public void setTrack(Track track) {
    this.track = track;
  }
}
