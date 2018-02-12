package com.audioord.web.command.album;

import com.audioord.dao.AlbumDAO;
import com.audioord.dao.DAOException;
import com.audioord.model.album.Album;
import com.audioord.web.command.Command;
import com.audioord.web.command.Pages;
import com.audioord.web.http.Request;
import com.audioord.web.http.Response;

import java.io.IOException;
import java.util.List;

public class ShowAlbumsCommand implements Command {
  public static final String NAME = "show_albums";
  private final AlbumDAO albumDAO = new AlbumDAO();

  @Override
  public String execute(Request request, Response response) throws DAOException, IOException {
    List<Album> albums = albumDAO.getAllAlbums();
    request.addAttribute("AlbumList", albums);
    return Pages.ALBUM_LIST;
  }
}
