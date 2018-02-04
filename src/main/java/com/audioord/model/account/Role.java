package com.audioord.model.account;

/**
 * Enum contains roles of user
 */
public enum Role {
  /**
   * role of Administrator
   */
  ADMIN, // site administrator
  /**
   * role of signed user
   */
  CLIENT, // authenticated user
  /**
   * role of unsigned user
   */
  UNKNOWN; // guest user

  /**
   * checks if the string matches the string value roles, then returns a match
   *
   * @param s string equivalent of role
   * @return {@link Role}
   */
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
