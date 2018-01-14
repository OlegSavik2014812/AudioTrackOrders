package com.audioord.web.command;

import com.audioord.dao.DAOException;
import com.audioord.model.audio.Track;
import com.audioord.web.http.Request;
import com.audioord.web.http.Response;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

public class GetMostPopularTrackCommand implements Command {
  @Override
  public String execute(Request request, Response response) throws ServletException, IOException, DAOException {
    List<Track> trackList =
  }
}
