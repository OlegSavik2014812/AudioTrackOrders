package com.audioord.model.account;

import com.audioord.model.Entity;

/**
 * Class describing object for storing information about authentication information of user, extends {@link Entity}
 */


public class AuthInfo extends Entity<String> {

  private String password;


  /**
   * constructor creates object of {@link AuthInfo}
   *
   * @param username username
   * @param password password
   */
  public AuthInfo(String username, String password) {
    this.password = password;
    this.setId(username);
  }

  /**
   * @return username value
   */
  public String getUserName() {
    return super.getId();
  }

  /**
   * @return password value
   */
  public String getPassword() {
    return password;
  }

  @Override
  public String toString() {
    return "AuthInfo{" + "password='" + password + '\'' + '}';
  }
}
