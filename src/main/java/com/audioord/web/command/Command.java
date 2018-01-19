package com.audioord.web.command;

import com.audioord.dao.DAOException;
import com.audioord.web.http.Request;
import com.audioord.web.http.Response;

import java.io.IOException;

public interface Command {

  String execute(Request request, Response response) throws IOException, DAOException, ClassNotFoundException;
}
