package com.audioord.web.http;


import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;

public class FileServlet extends MainServlet {

  public static final String UPLOAD_DIR_PATH = "WEB-INF/uploads";
  private static final Logger LOG = Logger.getLogger(MainServlet.class);
  private static final int DEFAULT_BUFFER_SIZE = 10240; // 10KB.

  @Override
  public void processRequest(HttpServletRequest req, HttpServletResponse res) throws Exception {
    String requestedFile = req.getPathInfo();

    if (requestedFile == null) {
      // Do your thing if the file is not supplied to the request URI.
      // Throw an exception, or send 404, or show default/warning page, or just ignore it.
      res.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
      return;
    }

    // Decode the file name (might contain spaces and on) and prepare file object.
    ;
    File file = new File(getServletContext().getRealPath(FileServlet.UPLOAD_DIR_PATH), URLDecoder.decode(requestedFile, "UTF-8"));
    // Check if file actually exists in filesystem.
    if (!file.exists()) {
      res.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
      return;
    }

    // Get content type by filename.
    String contentType = getServletContext().getMimeType(file.getName());

    // If content type is unknown, then set the default value.
    if (contentType == null) {
      contentType = "application/octet-stream";
    }

    // Init servlet response.
    res.reset();
    res.setBufferSize(DEFAULT_BUFFER_SIZE);
    res.setContentType(contentType);
    res.setHeader("Content-Length", String.valueOf(file.length()));
    res.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");

    // read & write
    try (InputStream in = new BufferedInputStream(new FileInputStream(file), DEFAULT_BUFFER_SIZE);
         OutputStream out = new BufferedOutputStream(res.getOutputStream(), DEFAULT_BUFFER_SIZE)) {

      byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
      int length;
      while ((length = in.read(buffer)) > 0) {
        out.write(buffer, 0, length);
      }
    } catch (Exception e) {
      LOG.error(e);
    }
  }

}
