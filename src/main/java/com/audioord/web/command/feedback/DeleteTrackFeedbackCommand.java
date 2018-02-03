package com.audioord.web.command.feedback;

import com.audioord.dao.DAOException;
import com.audioord.dao.TrackFeedbackDAO;
import com.audioord.web.command.Command;
import com.audioord.web.command.Pages;
import com.audioord.web.http.Request;
import com.audioord.web.http.Response;

import java.io.IOException;

public class DeleteTrackFeedbackCommand implements Command {
  public static final String NAME = "delete_feedback";
  private final TrackFeedbackDAO trackFeedbackDAO = new TrackFeedbackDAO();
  private static final String PRM_FEEDBACK_ID = "feedback_id";

  @Override
  public String execute(Request request, Response response) throws DAOException, IOException {
    if (!request.hasAllParameters(PRM_FEEDBACK_ID)) {
      return Pages.INDEX_PAGE;
    }
    long feedbackId = Long.parseLong(request.getParameter(PRM_FEEDBACK_ID));
    if (feedbackId == 0) {
      return Pages.INDEX_PAGE;
    }
    if (!trackFeedbackDAO.delete(feedbackId)) {
      return Pages.INDEX_PAGE;
    }
    return Pages.INDEX_PAGE;
  }
}
