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
    commands.put(SignInCommand.NAME, new SignInCommand());
    commands.put(SignUpCommand.NAME, new SignUpCommand());
    commands.put(SignOutCommand.NAME, new SignOutCommand());
    commands.put(NotFoundCommand.NAME, new NotFoundCommand());
    commands.put(TrackListCommand.NAME, new TrackListCommand());
    commands.put(ChangeLocaleCommand.NAME, new ChangeLocaleCommand());
    commands.put(AdditionNewTrackCommand.NAME, new AdditionNewTrackCommand());
    commands.put(OrderedTrackListCommand.NAME, new OrderedTrackListCommand());
    commands.put(SearchTrackCommand.NAME, new SearchTrackCommand());
    commands.put(AdditionDiscountCommand.NAME, new AdditionDiscountCommand());
    return commands;
  }

  private static boolean hasCommand(String commandName) {
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
