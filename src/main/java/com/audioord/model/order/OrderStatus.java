package com.audioord.model.order;

public enum OrderStatus {

  SUBMITTED, // client submit new order

  REJECTED, // admin can reject order

  COMPLETED; // finished order, eg. can download tracks

  public static OrderStatus fromString(String s) {
    for (OrderStatus status : values()) {
      if (status.name().equalsIgnoreCase(s)) {
        return status;
      }
    }
    return null;
  }

}
