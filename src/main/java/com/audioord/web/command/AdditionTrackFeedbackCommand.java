package com.audioord.web.command;

import com.audioord.dao.DAOException;
import com.audioord.dao.TrackFeedbackDAO;
import com.audioord.model.account.User;
import com.audioord.web.http.Request;
import com.audioord.web.http.Response;

import java.io.IOException;

public class AdditionTrackFeedbackCommand implements Command {
  public static final String NAME = "add_feedback";
  private TrackFeedbackDAO trackFeedbackDAO = new TrackFeedbackDAO();

  @Override
  public String execute(Request request, Response response) throws IOException, DAOException {
    User user = request.getSessionAttribute("USER", User.class);

    return null;
  }
}
