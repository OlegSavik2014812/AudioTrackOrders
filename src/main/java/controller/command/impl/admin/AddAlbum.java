package controller.command.impl.admin;

import controller.command.Command;
import entity.Album;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddAlbum implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String page = null;
        Album album = new Album();


    }
}
