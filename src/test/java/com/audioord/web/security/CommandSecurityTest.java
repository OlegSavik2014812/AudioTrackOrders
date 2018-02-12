package com.audioord.web.security;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CommandSecurityTest {
  private final String commandNameAdmin = "edit_track";
  private final String commandNameUser = "view_cart";
  private final String commandNameGuest = "change_local";
  private final String commandNameForbidden = "do_something";

  @Test
  public void shouldAllowAdmin() throws Exception {
    boolean isAllowed = CommandSecurity.isAllowedToAdmin(commandNameAdmin);
    Assert.assertTrue(isAllowed);
  }

  @Test
  public void shouldNotAllowAdmin() throws Exception {
    boolean isAllowed = CommandSecurity.isAllowedToGuest(commandNameUser);
    Assert.assertFalse(isAllowed);
  }

  @Test
  public void shouldAllowAdminAsGuest() throws Exception {
    boolean isAllowed = CommandSecurity.isAllowedToAdmin(commandNameGuest);
    Assert.assertTrue(isAllowed);
  }

  @Test
  public void shouldAllowClient() throws Exception {
    boolean isAllowed = CommandSecurity.isAllowedToClient(commandNameUser);
    Assert.assertTrue(isAllowed);
  }

  @Test
  public void shouldNotAllowClient() throws Exception {
    boolean isAllowed = CommandSecurity.isAllowedToAdmin(commandNameUser);
    Assert.assertFalse(isAllowed);
  }

  @Test
  public void shouldAllowClientAsGuest() throws Exception {
    boolean isAllowed = CommandSecurity.isAllowedToClient(commandNameGuest);
    Assert.assertTrue(isAllowed);
  }

  @Test
  public void shouldAllowGuest() throws Exception {
    boolean isAllowed = CommandSecurity.isAllowedToGuest(commandNameGuest);
    Assert.assertTrue(isAllowed);
  }

  @Test
  public void shouldNotAllowGuest() throws Exception {
    boolean isAllowed = CommandSecurity.isAllowedToGuest(commandNameForbidden);
    Assert.assertFalse(isAllowed);
  }
}
