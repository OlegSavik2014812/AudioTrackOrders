package com.audioord.model.order;

import com.audioord.model.Entity;

public class OrderedTrack extends Entity<Long> {

  private Long purchaseId;
  private Long trackId;

  public OrderedTrack(Long purchaseId, Long trackId) {
    this.purchaseId = purchaseId;
    this.trackId = trackId;
  }

  public Long getPurchaseId() {
    return purchaseId;
  }

  public void setPurchaseId(Long purchaseId) {
    this.purchaseId = purchaseId;
  }

  public Long getTrackId() {
    return trackId;
  }

  public void setTrackId(Long trackId) {
    this.trackId = trackId;
  }
}
