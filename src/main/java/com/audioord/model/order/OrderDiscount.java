package com.audioord.model.order;

import com.audioord.model.Entity;

import java.util.Date;

public class OrderDiscount extends Entity<Long> {


  private double discountPercent;
  private Date effectiveFrom;
  private Date effectiveTo;

  public OrderDiscount(double discountPercent, Date effectiveFrom, Date effectiveTo) {
    this.discountPercent = discountPercent;
    this.effectiveFrom = effectiveFrom;
    this.effectiveTo = effectiveTo;
  }

  @Override
  public void setId(Long id) {
    super.setId(id);
  }

  @Override
  public Long getId() {
    return super.getId();
  }

  public double getDiscountPercent() {
    return discountPercent;
  }

  public void setDiscountPercent(double discountPercent) {
    this.discountPercent = discountPercent;
  }

  public Date getEffectiveFrom() {
    return effectiveFrom;
  }

  public void setEffectiveFrom(Date effectiveFrom) {
    this.effectiveFrom = effectiveFrom;
  }

  public Date getEffectiveTo() {
    return effectiveTo;
  }

  public void setEffectiveTo(Date effectiveTo) {
    this.effectiveTo = effectiveTo;
  }
}
