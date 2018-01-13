package com.audioord.model.account;

import com.audioord.model.Entity;

public class User extends Entity<Long> {

  private String username;
  private String firstName;
  private String lastName;
  private ROLE role;


  public User(String username, ROLE role) {
    this.username = username;
    this.role = role;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public ROLE getRole() {
    return role;
  }

  public void setRole(ROLE role) {
    this.role = role;
  }
}
