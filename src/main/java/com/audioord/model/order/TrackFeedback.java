package com.audioord.model.order;

import com.audioord.model.Entity;
import com.audioord.model.account.User;
import com.audioord.model.audio.Track;

import java.util.Date;

/**
 * Class describing object for storing information about TrackFeedback, extends {@link Entity}
 */
public class TrackFeedback extends Entity<Long> {

  private User user;
  private String comments;
  private Date createdAt;
  private Track track;

  /**
   * constructor of {@link TrackFeedback} with params
   *
   * @param user     {@link User} object
   * @param comments comments
   * @param track    {@link Track} object
   */
  public TrackFeedback(User user, String comments, Track track) {
    this.user = user;
    this.comments = comments;
    this.track = track;
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
   * @return comments
   */
  public String getComments() {
    return comments;
  }

  /**
   * @param comments comments
   */
  public void setComments(String comments) {
    this.comments = comments;
  }

  /**
   * @return date, when feedback was created
   */
  public Date getCreatedAt() {
    return createdAt;
  }

  /**
   * @param createdAt date, when feedback was created
   */
  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  /**
   * @return {@link Track} object
   */
  public Track getTrack() {
    return track;
  }

  /**
   * @param track {@link Track} object
   */
  public void setTrack(Track track) {
    this.track = track;
  }
}
