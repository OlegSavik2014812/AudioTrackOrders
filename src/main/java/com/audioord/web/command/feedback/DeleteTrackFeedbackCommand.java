package com.audioord.web.command.feedback;

import com.audioord.dao.DAOException;
import com.audioord.dao.TrackFeedbackDAO;
import com.audioord.model.order.TrackFeedback;
import com.audioord.web.command.Command;
import com.audioord.web.command.Pages;
import com.audioord.web.http.Request;
import com.audioord.web.http.Response;

import java.io.IOException;

/**
 * Class describes the object-command, which delete track feedback
 * implementation of {@link Command}
 */
public class DeleteTrackFeedbackCommand implements Command {
  public static final String NAME = "delete_feedback";
  private static final String PRM_FEEDBACK_ID = "feedback_id";
  private final TrackFeedbackDAO trackFeedbackDAO = new TrackFeedbackDAO();

  /**
   * There is validation of input params for deleting {@link TrackFeedback}
   * if command is successful, the {@link TrackFeedback} is deleted and redirection to index page
   *
   * @param request  {@link Request}
   * @param response {@link Response}
   * @return string name of page
   * @throws IOException  in case, when params incorrect
   * @throws DAOException {@link DAOException}
   */
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
