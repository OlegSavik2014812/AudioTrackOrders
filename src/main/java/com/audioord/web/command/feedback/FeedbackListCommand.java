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

/**
 * Class describes the object-command, which shows all feedback on a chosen track and returns to feedback list page
 * implementation of {@link Command}
 */
public class FeedbackListCommand implements Command {
  public static final String NAME = "feedback_list";
  private static final String PRM_TRACK_ID = "track_id";
  private final TrackFeedbackDAO trackFeedbackDAO = new TrackFeedbackDAO();
  private final TrackDAO trackDAO = new TrackDAO();

  /**
   * There is validation input params for {@link Track} object and in good case creation of it
   * Further, a search is performed on the input parameters for the {@link TrackFeedbackDAO#getFeedbacksByTrackId(Long)}
   * if feedbacks was found, they put to list
   * if command is successful, then it shows list of feedbacks on feedback page
   *
   * @param request  {@link Request}
   * @param response {@link Response}
   * @return string name of page
   * @throws IOException  in case, when params incorrect
   * @throws DAOException {@link DAOException}
   */
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
    request.addAttribute("track", track);
    request.addAttribute("Feedbacks", trackFeedbackList);

    return Pages.FEEDBACK_PAGE;
  }
}
