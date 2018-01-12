package com.audioord.model;

import java.io.Serializable;
import java.util.Objects;

public abstract class Entity<T extends Serializable> implements Serializable {

  private static final long serialVersionUID = -2565570290688784024L;

  private T id;

  public T getId() {
    return id;
  }

  public void setId(T id) {
    this.id = id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Entity)) return false;
    Entity<?> entity = (Entity<?>) o;
    return Objects.equals(id, entity.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
