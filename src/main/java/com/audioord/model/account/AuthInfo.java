package com.audioord.model.account;

import com.audioord.model.Entity;

public class AuthInfo extends Entity<String> {

  private String password;

  public AuthInfo(String username, String password) {
    this.password = password;
    this.setId(username);
  }

  public String getUserName() {
    return super.getId();
  }

  public String getPassword() {
    return password;
  }

  @Override
  public String toString() {
    return "AuthInfo{" + "password='" + password + '\'' + '}';
  }
}
