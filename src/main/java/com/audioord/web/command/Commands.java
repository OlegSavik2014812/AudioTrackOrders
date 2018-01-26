package com.audioord.web.command;

import com.audioord.web.command.auth.SignInCommand;
import com.audioord.web.command.auth.SignOutCommand;
import com.audioord.web.command.auth.SignUpCommand;
import com.audioord.web.command.cart.AddTrackCartCommand;
import com.audioord.web.command.cart.MakeCartOrderCommand;
import com.audioord.web.command.cart.ViewTrackCartCommand;
import com.audioord.web.command.track.SearchTrackCommand;
import com.audioord.web.command.track.TrackListCommand;

import java.util.HashMap;
import java.util.Map;

public final class Commands {

  private static final Map<String, Command> COMMAND_MAP = define(new HashMap<>());
  private static final NoCommand NO_COMMAND = new NoCommand();

  private Commands() {
    super();
  }

  private static Map<String, Command> define(Map<String, Command> commands) {
    commands.put(NotFoundCommand.NAME, new NotFoundCommand());

    commands.put(SignInCommand.NAME, new SignInCommand());
    commands.put(SignUpCommand.NAME, new SignUpCommand());
    commands.put(SignOutCommand.NAME, new SignOutCommand());


    commands.put(TrackListCommand.NAME, new TrackListCommand());
    commands.put(SearchTrackCommand.NAME, new SearchTrackCommand());

    commands.put(AddTrackCartCommand.NAME, new AddTrackCartCommand());
    commands.put(ViewTrackCartCommand.NAME, new ViewTrackCartCommand());
    commands.put(MakeCartOrderCommand.NAME, new MakeCartOrderCommand());

    commands.put(ChangeLocaleCommand.NAME, new ChangeLocaleCommand());
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
