package com.audioord.model.account;

import com.audioord.model.Entity;

import java.util.Objects;

public class User extends Entity<Long> {

  private String username;
  private String firstName;
  private String lastName;
  private Role role;

  public User(String username, Role role) {
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

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof User)) return false;
    if (!super.equals(o)) return false;
    User user = (User) o;
    return Objects.equals(username, user.username) &&
    Objects.equals(firstName, user.firstName) &&
    Objects.equals(lastName, user.lastName) &&
    role == user.role;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), username, firstName, lastName, role);
  }

  @Override
  public String toString() {
    return "User{" +
    "username='" + username + '\'' +
    ", firstName='" + firstName + '\'' +
    ", lastName='" + lastName + '\'' +
    ", role=" + role +
    '}';
  }
}
