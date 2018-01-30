package com.audioord.web.command;

import com.audioord.web.command.auth.SignInCommand;
import com.audioord.web.command.auth.SignOutCommand;
import com.audioord.web.command.auth.SignUpCommand;
import com.audioord.web.command.cart.AddTrackCartCommand;
import com.audioord.web.command.cart.MakeCartOrderCommand;
import com.audioord.web.command.cart.ViewTrackCartCommand;
import com.audioord.web.command.order.OrderListCommand;
import com.audioord.web.command.order.OrderStatusEdit;
import com.audioord.web.command.track.EditTrackCommand;
import com.audioord.web.command.track.SearchTrackCommand;
import com.audioord.web.command.track.TrackListCommand;
import com.audioord.web.command.track.UploadTrackCommand;

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
    commands.put(ChangeLocaleCommand.NAME, new ChangeLocaleCommand());
    //auth
    commands.put(SignInCommand.NAME, new SignInCommand());
    commands.put(SignUpCommand.NAME, new SignUpCommand());
    commands.put(SignOutCommand.NAME, new SignOutCommand());
    // track
    commands.put(EditTrackCommand.NAME, new EditTrackCommand());
    commands.put(UploadTrackCommand.NAME, new UploadTrackCommand());
    //track list
    commands.put(TrackListCommand.NAME, new TrackListCommand());
    commands.put(SearchTrackCommand.NAME, new SearchTrackCommand());
    //cart
    commands.put(AddTrackCartCommand.NAME, new AddTrackCartCommand());
    commands.put(ViewTrackCartCommand.NAME, new ViewTrackCartCommand());
    commands.put(MakeCartOrderCommand.NAME, new MakeCartOrderCommand());
    //order
    commands.put(OrderListCommand.NAME, new OrderListCommand());
    commands.put(OrderStatusEdit.NAME, new OrderStatusEdit());

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
