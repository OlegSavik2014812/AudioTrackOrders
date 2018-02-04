package com.audioord.web.command.track;


import com.audioord.dao.DAOException;
import com.audioord.dao.TrackDAO;
import com.audioord.model.audio.Track;
import com.audioord.utils.FileUtils;
import com.audioord.web.command.Command;
import com.audioord.web.command.Pages;
import com.audioord.web.http.FileServlet;
import com.audioord.web.http.Request;
import com.audioord.web.http.Response;
import com.mpatric.mp3agic.*;
import org.apache.commons.fileupload.FileItem;

import java.io.File;
import java.io.IOException;

/**
 * Class describes the object-command, which upload new track and returns to add track page
 * implementation of {@link Command}
 */
public class UploadTrackCommand implements Command {

  public static final String NAME = "upload_track";
  private static final String PRM_TRACK = "track";

  private final TrackDAO trackDAO = new TrackDAO();

  /**
   * there is extraction file from request and it's assigned to {@link FileItem} object
   * if fileItem is equal to null then redirection to track add page
   * <p>
   * then writing file using {@link FileUtils#writeFile(FileItem, String)}
   * after that there is creation of {@link Track} object with file params, in bad case there is deleting file, that
   * can not be parsed
   * if command executed successfully, then creation of new {@link Track} object with real params of file and
   * redirection to add track page
   *
   * @param request  {@link Request}
   * @param response {@link Response}
   * @return string name of page
   * @throws IOException  in case, when params incorrect
   * @throws DAOException {@link DAOException}
   */
  @Override
  public String execute(Request request, Response response) throws DAOException, IOException {
    FileItem fileItem = request.getFileItem("file");
    if (fileItem == null) {
      return Pages.ADD_TRACK_PAGE;
    }

    File tFile = FileUtils.writeFile(fileItem, getUploadDirPath(request));
    if (tFile == null) {
      return Pages.ADD_TRACK_PAGE; // something gos wrong, stop processing
    }

    Track track = new Track();
    if (!parseMp3File(tFile, track)) { // delete file that can't be parsed
      FileUtils.deleteFile(tFile);
      return Pages.ADD_TRACK_PAGE;
    }

    if (!trackDAO.create(track)) {
      return Pages.ADD_TRACK_PAGE;
    }

    // add track meta to request for editing
    request.addAttribute(PRM_TRACK, track);
    return Pages.ADD_TRACK_PAGE;
  }

  private boolean parseMp3File(File f, Track track) {
    try {
      Mp3File mp3file = new Mp3File(f);
      if (mp3file.hasId3v1Tag()) {
        ID3v1 tag = mp3file.getId3v1Tag();
        track.setName(tag.getTitle() != null ? "unknown" : tag.getTitle());
        track.setArtist(tag.getArtist() != null ? "unknown" : tag.getArtist());
        track.setAlbum(tag.getAlbum());

      } else if (mp3file.hasId3v2Tag()) {
        ID3v2 tag = mp3file.getId3v2Tag();
        track.setName(tag.getTitle() != null ? "unknown" : tag.getTitle());
        track.setArtist(tag.getArtist() != null ? "unknown" : tag.getArtist());
        track.setAlbum(tag.getAlbum());
      }

      track.setUri(f.getName());
      track.setDuration(mp3file.getLengthInMilliseconds());
      return true;
    } catch (IOException | UnsupportedTagException | InvalidDataException e) {
      return false;
    }
  }

  private String getUploadDirPath(Request req) {
    return req.raw().getServletContext().getRealPath(FileServlet.UPLOAD_DIR_PATH);
  }
}
