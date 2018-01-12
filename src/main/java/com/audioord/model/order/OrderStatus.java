package com.audioord.model.order;

public enum OrderStatus {
  SUBMITED, // client submit new order

  REJECTED, // admin can reject order

  COMPLETED // finished order, eg. can download tracks
}
