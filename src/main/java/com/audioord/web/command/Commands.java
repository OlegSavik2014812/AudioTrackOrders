package com.audioord.web.command;

import java.util.HashMap;
import java.util.Map;

public final class Commands {

  private static final Map<String, Command> COMMAND_MAP = define(new HashMap<String, Command>());
  private static final NoCommand NO_COMMAND = new NoCommand();

  private Commands() {
    super();
  }

  private static Map<String, Command> define(Map<String, Command> commands) {

    commands.put(NotFoundCommand.NAME, new NotFoundCommand());
    commands.put(SignInCommand.NAME, new SignInCommand());
    commands.put(SignUpCommand.NAME, new SignUpCommand());
    commands.put(SignOutCommand.NAME, new SignOutCommand());

    return commands;
  }

  public static boolean hasCommand(String commandName) {
    return COMMAND_MAP.containsKey(commandName);
  }

  public static Command getCommand(String commandName) {
    if (!hasCommand(commandName)) {
      return NO_COMMAND;
    } else {
      return COMMAND_MAP.get(commandName);
    }
  }
}
