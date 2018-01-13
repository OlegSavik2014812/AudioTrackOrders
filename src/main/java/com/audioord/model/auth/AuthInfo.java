package com.audioord.model.auth;

import com.audioord.model.Entity;

public class AuthInfo extends Entity<String> {

  private String password;

  public AuthInfo(String password, String username) {
    this.password = password;
    this.setId(username);
  }

  public String getUserName() {
    return super.getId();
  }

  public String getPassword() {
    return password;
  }
}
