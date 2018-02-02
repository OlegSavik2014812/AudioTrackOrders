package com.audioord.web.security;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SecurityTest {
  private final String commandNameAdmin = "edit_track";
  private final String commandNameUser = "view_cart";
  private final String commandNameGuest = "change_local";
  private final String commandNameForbidden = "do_something";

  @Test
  public void testIsAllowedToAdmin() throws Exception {
    boolean isAllowed = Security.isAllowedToAdmin(commandNameAdmin);
    Assert.assertTrue(isAllowed);
  }

  @Test
  public void testIsNotAllowedToAdmin() throws Exception {
    boolean isAllowed = Security.isAllowedToGuest(commandNameUser);
    Assert.assertFalse(isAllowed);
  }

  @Test
  public void testIsAllowedToAdminAsGuest() throws Exception {
    boolean isAllowed = Security.isAllowedToAdmin(commandNameGuest);
    Assert.assertTrue(isAllowed);
  }

  @Test
  public void testIsAllowedToClient() throws Exception {
    boolean isAllowed = Security.isAllowedToClient(commandNameUser);
    Assert.assertTrue(isAllowed);
  }

  @Test
  public void testIsNotAllowedToClient() throws Exception {
    boolean isAllowed = Security.isAllowedToAdmin(commandNameUser);
    Assert.assertFalse(isAllowed);
  }

  @Test
  public void testIsAllowedToClientAsGuest() throws Exception {
    boolean isAllowed = Security.isAllowedToClient(commandNameGuest);
    Assert.assertTrue(isAllowed);
  }

  @Test
  public void testIsAllowedToGuest() throws Exception {
    boolean isAllowed = Security.isAllowedToGuest(commandNameGuest);
    Assert.assertTrue(isAllowed);
  }

  @Test
  public void testIsNotAllowedToGuest() throws Exception {
    boolean isAllowed = Security.isAllowedToGuest(commandNameForbidden);
    Assert.assertFalse(isAllowed);
  }
}
