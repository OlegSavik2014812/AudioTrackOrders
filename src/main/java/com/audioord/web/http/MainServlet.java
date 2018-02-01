package com.audioord.web.http;

import com.audioord.web.RequestHandler;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainServlet extends HttpServlet {

  private static final long serialVersionUID = -1L;
  private static final Logger LOG = Logger.getLogger(MainServlet.class);

  public MainServlet() {
    super();
  }

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse res)
  throws ServletException, IOException {
    try {
      processRequest(req, res);
    } catch (IOException ignore) {
      // ignore, to late for this
    } catch (ThreadDeath td) {
      throw td; // nothing useful, need to die silently.
    } catch (Throwable t) {
      res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
      LOG.error(t);
    }
  }

  public void processRequest(HttpServletRequest req, HttpServletResponse res) throws Exception {
    RequestHandler requestHandler = new RequestHandler();
    requestHandler.doHandle(req, res, getServletContext());
  }
}
