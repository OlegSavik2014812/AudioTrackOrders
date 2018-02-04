package com.audioord.model.order;

/**
 * Enum contains status of {@link Order}
 */
public enum OrderStatus {
  /**
   * client submit new order
   */
  SUBMITTED,
  /**
   * admin can reject order
   */
  REJECTED,
  /**
   * finished order, eg. can download tracks
   */
  COMPLETED;

  /**
   * checks if the string matches the string value status, then returns a match
   *
   * @param s string equivalent of order status
   * @return {@link OrderStatus}
   */

  public static OrderStatus fromString(String s) {
    for (OrderStatus status : values()) {
      if (status.name().equalsIgnoreCase(s)) {
        return status;
      }
    }
    return null;
  }

  @Override
  public String toString() {
    return this.name();
  }
}
