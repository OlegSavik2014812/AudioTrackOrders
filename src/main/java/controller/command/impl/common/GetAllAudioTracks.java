package controller.command.impl.common;

import controller.command.Command;
import entity.AudioTrack;
import exception.ServiceException;
import resource.AtributeName;
import resource.JspPageName;
import service.CommonService;
import service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetAllAudioTracks implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException, ServletException, IOException {
        String page = null;
        ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
        CommonService commonService = serviceFactory.getCommonService();
        List<AudioTrack> audioTrackList = commonService.getAllAudioTracks();
        request.setAttribute(AtributeName.AUDIOTRACK_LIST, audioTrackList);
        page = JspPageName.AUDIOTRACK_LIST;
        request.getRequestDispatcher(page).forward(request, response);
    }
}
