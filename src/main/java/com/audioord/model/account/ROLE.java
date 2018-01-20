package com.audioord.model.account;

public enum ROLE {

  ADMIN, // site administrator

  CLIENT, // authenticated user

  UNKNOWN; // guest user

  public static ROLE fromString(String s) {
    for (ROLE role : values()) {
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
