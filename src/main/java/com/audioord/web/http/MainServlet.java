package com.audioord.web.http;

import com.audioord.web.RequestHandler;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Front application controller.
 */


public class MainServlet extends HttpServlet {

  private static final long serialVersionUID = -1L;
  private static final Logger LOG = Logger.getLogger(MainServlet.class);

  public MainServlet() {
    super();
  }

  /**
   * There is main processing service, put input {@link HttpServletRequest} and {@link HttpServletResponse}
   * to {@link MainServlet#processRequest(HttpServletRequest, HttpServletResponse)}
   *
   * @param req {@link HttpServletRequest}
   * @param res {@link HttpServletResponse}
   * @throws ServletException in case, when it encounters difficulty.
   * @throws IOException      in case, when input is invalid
   */
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

  /**
   * There is initialization of {@link RequestHandler} handler, which used to handle {@link HttpServletRequest}
   * and {@link HttpServletResponse}
   *
   * @param req {@link HttpServletRequest}
   * @param res {@link HttpServletResponse}
   * @throws Exception in case of errors in subsequent method calls
   */
  public void processRequest(HttpServletRequest req, HttpServletResponse res) throws Exception {
    RequestHandler requestHandler = new RequestHandler();
    requestHandler.doHandle(req, res, getServletContext());
  }
}
