package com.audioord.web.command.feedback;

import com.audioord.dao.DAOException;
import com.audioord.dao.TrackDAO;
import com.audioord.dao.TrackFeedbackDAO;
import com.audioord.model.audio.Track;
import com.audioord.model.order.TrackFeedback;
import com.audioord.web.command.Command;
import com.audioord.web.command.Pages;
import com.audioord.web.http.Request;
import com.audioord.web.http.Response;

import java.io.IOException;
import java.util.List;

public class FeedbackListCommand implements Command {
  public static final String NAME = "feedback_list";
  private final TrackFeedbackDAO trackFeedbackDAO = new TrackFeedbackDAO();
  private static final String PRM_TRACK_ID = "track_id";
  private final TrackDAO trackDAO = new TrackDAO();

  @Override
  public String execute(Request request, Response response) throws DAOException, IOException {
    if (!request.hasAllParameters(PRM_TRACK_ID)) {
      return Pages.INDEX_PAGE;
    }
    long trackId = Long.parseLong(request.getParameter(PRM_TRACK_ID));
    if (trackId == 0) {
      return Pages.INDEX_PAGE;
    }
    Track track = trackDAO.getById(trackId);
    List<TrackFeedback> trackFeedbackList = trackFeedbackDAO.getFeedbacksByTrackId(trackId);
    request.setSessionAttribute("trackId", track.getId());
    request.addAttribute("track",track);
    request.addAttribute("Feedbacks", trackFeedbackList);

    return Pages.FEEDBACK_PAGE;
  }
}
