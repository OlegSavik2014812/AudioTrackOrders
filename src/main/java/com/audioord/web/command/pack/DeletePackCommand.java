package com.audioord.web.command.pack;

import com.audioord.dao.DAOException;
import com.audioord.dao.PackDAO;
import com.audioord.model.pack.Pack;
import com.audioord.web.command.Command;
import com.audioord.web.command.Pages;
import com.audioord.web.http.Request;
import com.audioord.web.http.Response;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeletePackCommand implements Command {
  public static final String NAME = "delete_pack";
  private static final String PRM_PACK_ID = "pack_id";
  private final PackDAO packDAO = new PackDAO();
  private static final String FORWARD_PACK_LIST = "/action?name=show_packs";

  @Override
  public String execute(Request request, Response response) throws DAOException, IOException {
    if (!request.hasParameter(PRM_PACK_ID)) {
      return FORWARD_PACK_LIST;
    }
    if (!validateRequestPackId(request.getParameter(PRM_PACK_ID))) {
      return Pages.INDEX_PAGE;
    }
    Pack pack = packDAO.getById(Long.valueOf(request.getParameter(PRM_PACK_ID)));
    if (pack == null) {
      return Pages.INDEX_PAGE;
    }
    if (!packDAO.deletePack(pack)) {
      return FORWARD_PACK_LIST;
    }
    return FORWARD_PACK_LIST;
  }

  private boolean validateRequestPackId(String id) {
    Pattern pattern = Pattern.compile("^\\d+$");
    Matcher matcher = pattern.matcher(id);
    return matcher.find();
  }
}
