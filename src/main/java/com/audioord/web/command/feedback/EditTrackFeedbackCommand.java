package com.audioord.web.command.feedback;

import com.audioord.dao.DAOException;
import com.audioord.dao.TrackDAO;
import com.audioord.dao.TrackFeedbackDAO;
import com.audioord.model.account.User;
import com.audioord.model.audio.Track;
import com.audioord.model.order.TrackFeedback;
import com.audioord.web.command.Command;
import com.audioord.web.command.Pages;
import com.audioord.web.http.Request;
import com.audioord.web.http.Response;

import java.io.IOException;
import java.util.Date;

public class EditTrackFeedbackCommand implements Command {
  public static final String NAME = "edit_feedback";
  private final TrackDAO trackDAO = new TrackDAO();
  private final TrackFeedbackDAO trackFeedbackDAO = new TrackFeedbackDAO();

  @Override
  public String execute(Request request, Response response) throws DAOException, IOException {
    User user = request.getSessionAttribute("USER", User.class);

    Long trackId = (Long) request.raw().getSession().getAttribute("trackId");
    Track track = trackDAO.getById(Long.valueOf(trackId));
    String comments = request.getParameter("comment");
    if (comments.length() > 255) {
      return Pages.FEEDBACK_PAGE;
    }
    TrackFeedback trackFeedback = new TrackFeedback(user, comments, track);
    trackFeedback.setCreatedAt(new Date());

    if (!trackFeedbackDAO.create(trackFeedback)) {
      return Pages.FEEDBACK_PAGE;
    }

    request.removeSessionAttribute("trackId");
    return Pages.INDEX_PAGE;
  }
}
