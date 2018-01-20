package com.audioord.model.order;

import com.audioord.model.Entity;

public class OrderedTrack extends Entity<Long> {
  private int purchaseId;
  private int trackId;

  public OrderedTrack(Long purchaseId, Long trackId) {
    this.purchaseId = Math.toIntExact(purchaseId);
    this.trackId = Math.toIntExact(trackId);
  }

  @Override
  public Long getId() {
    return super.getId();
  }

  public int getPurchaseId() {
    return purchaseId;
  }

  public void setPurchaseId(int purchaseId) {
    this.purchaseId = purchaseId;
  }

  public int getTrackId() {
    return trackId;
  }

  public void setTrackId(int trackId) {
    this.trackId = trackId;
  }
}
