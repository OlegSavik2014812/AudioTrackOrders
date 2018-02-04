package com.audioord.web.security;

/**
 * Class describes,should command executed or not
 */
public class CommandSecurity {
  /**
   * checks input command name string, if execution allowed to admin
   *
   * @param commandName command name string
   * @return if execution allowed - true,else - false
   */
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

  /**
   * checks input command name string, if execution allowed to signed user(client)
   *
   * @param commandName command name string
   * @return if execution allowed - true,else - false
   */
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

  /**
   * checks input command name string, if execution allowed to unsigned user
   *
   * @param commandName command name string
   * @return if execution allowed - true,else - false
   */
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
