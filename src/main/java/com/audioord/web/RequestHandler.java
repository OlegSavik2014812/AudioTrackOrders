package com.audioord.web;

import com.audioord.web.command.Command;
import com.audioord.web.command.Commands;
import com.audioord.web.command.NotFoundCommand;
import com.audioord.web.http.Request;
import com.audioord.web.http.Response;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Class describes object, handle {@link HttpServletRequest} and {@link HttpServletResponse}
 * extract from request command name and redirect to it
 */
public class RequestHandler {

  private static final Logger LOG = Logger.getLogger(RequestHandler.class);
  private static final String PRM_COMMAND_NAME = "name";

  /**
   * default constructor
   */
  public RequestHandler() {
    super();
  }

  /**
   * There is handling service, which extract command name string from input request
   * it's validate command name, in bad case logs error
   * if command name is valid, it's extract {@link Command} object from {@link Commands} util class
   * and execute it and forward to the corresponding page
   *
   * @param req  {@link HttpServletRequest}
   * @param resp {@link HttpServletResponse}
   * @param ctx  {@link ServletContext}
   */
  public void doHandle(HttpServletRequest req, HttpServletResponse resp, ServletContext ctx) {

    final Request request = new Request(req);
    final Response response = new Response(resp);

    String commandName = NotFoundCommand.NAME;
    if (request.hasParameter(PRM_COMMAND_NAME)) {
      commandName = request.getParameter(PRM_COMMAND_NAME);
    } else {
      LOG.error(String.format("Missing request command parameter <%s>", PRM_COMMAND_NAME));
    }

    Command command = Commands.getCommand(commandName);

    LOG.debug(String.format("Start executing command [%s]", commandName));
    execute(command, ctx, request, response);
  }

  private void execute(Command command, ServletContext ctx, Request req, Response res) {
    try {

      String page = command.execute(req, res);

      ctx.getRequestDispatcher(page).forward(req.raw(), res.raw());

    } catch (Exception e) {
      LOG.error(e);
    }
  }
}
