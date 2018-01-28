package com.audioord.model.account;

public enum Role {

  ADMIN, // site administrator

  CLIENT, // authenticated user

  UNKNOWN; // guest user

  public static Role fromString(String s) {
    for (Role role : values()) {
      if (role.name().equalsIgnoreCase(s)) {
        return role;
      }
    }
    return null;
  }

  @Override
  public String toString() {
    return this.name();
  }
}
