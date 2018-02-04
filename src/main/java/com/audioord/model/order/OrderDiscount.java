package com.audioord.model.order;

import com.audioord.model.Entity;
import com.audioord.model.account.User;

import java.util.Date;

/**
 * Class describing object for storing information about OrderDiscount, extends {@link Entity}
 */
public class OrderDiscount extends Entity<Long> {

  private double discountPercent;
  private Date effectiveFrom;
  private Date effectiveTo;
  private User user;

  /**
   * constructor of {@link OrderDiscount} with params
   *
   * @param discountPercent percent of discount
   * @param effectiveFrom   date, begin of discount usage
   * @param effectiveTo     date, end of discount usage
   * @param user            {@link User} object
   */
  public OrderDiscount(double discountPercent, Date effectiveFrom, Date effectiveTo, User user) {
    this.discountPercent = discountPercent;
    this.effectiveFrom = effectiveFrom;
    this.effectiveTo = effectiveTo;
    this.user = user;
  }

  /**
   * @return discountPercent
   */
  public double getDiscountPercent() {
    return discountPercent;
  }

  public void setDiscountPercent(double discountPercent) {
    this.discountPercent = discountPercent;
  }

  /**
   * @return date, begin of discount usage
   */
  public Date getEffectiveFrom() {
    return effectiveFrom;
  }

  public void setEffectiveFrom(Date effectiveFrom) {
    this.effectiveFrom = effectiveFrom;
  }

  /**
   * @return date, end of discount usage
   */
  public Date getEffectiveTo() {
    return effectiveTo;
  }

  public void setEffectiveTo(Date effectiveTo) {
    this.effectiveTo = effectiveTo;
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
}
