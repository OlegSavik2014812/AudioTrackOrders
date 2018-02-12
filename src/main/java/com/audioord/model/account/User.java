package com.audioord.model.account;

import com.audioord.model.Entity;

import java.util.Objects;

/**
 * Class describing object for storing information about user, extends {@link Entity}
 */
public class User extends Entity<Long> {
  private String username;
  private String firstName;
  private String lastName;
  private Role role;
  private double cash;

  /**
   * constructor creates object of User class
   *
   * @param username string value of user name
   * @param role     user role
   */
  public User(String username, Role role) {
    this.username = username;
    this.role = role;
  }

  /**
   * @return username value
   */

  public String getUsername() {
    return username;
  }

  /**
   * @param username username value
   */
  public void setUsername(String username) {
    this.username = username;
  }

  /**
   * @return firstName value
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * @param firstName firstName value
   */
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /**
   * @return lastName balue
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * @param lastName last name
   */
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  /**
   * @return role of user
   */
  public Role getRole() {
    return role;
  }

  /**
   * @param role role of user
   */
  public void setRole(Role role) {
    this.role = role;
  }

  public double getCash() {
    return cash;
  }

  public void setCash(double cash) {
    this.cash = cash;
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
