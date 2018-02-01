package com.audioord.web.security;

public class Security {

  public static boolean isAllowedToAdmin(String commandName) {
    boolean isAllowed = false;
    for (AdminCommand adminCommand : AdminCommand.values()) {
      if (commandName.equals(adminCommand.toString().toLowerCase())) {
        isAllowed = true;
        break;
      }
    }
    if (!isAllowed) {
      isAllowed = isAllowedToGuest(commandName);
    }
    return isAllowed;
  }

  public static boolean isAllowedToClient(String commandName) {
    boolean isAllowed = false;
    for (UserCommand userCommand : UserCommand.values()) {
      if (commandName.equals(userCommand.toString().toLowerCase())) {
        isAllowed = true;
        break;
      }
    }
    if (!isAllowed) {
      isAllowed = isAllowedToGuest(commandName);
    }
    return isAllowed;
  }

  public static boolean isAllowedToGuest(String commandName) {
    boolean isAllowed = false;
    for (CommonCommand commonCommand : CommonCommand.values()) {
      if (commandName.equals(commonCommand.toString().toLowerCase())) {
        isAllowed = true;
        break;
      }
    }
    return isAllowed;
  }
}
