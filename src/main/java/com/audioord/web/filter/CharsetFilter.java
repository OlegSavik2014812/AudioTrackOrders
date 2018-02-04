package com.audioord.web.filter;


import javax.servlet.*;
import java.io.IOException;

/**
 * Class describes the filter, which checks page encoding and if it's equals to null, sets utf-8
 * encoding as default
 */
public class CharsetFilter implements Filter {
  private static final String PRM_ENCODING = "requestEncoding";
  private static final String ENCODING_UTF_8 = "UTF-8";
  private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
  private String encoding;

  public void init(FilterConfig config) throws ServletException {
    encoding = config.getInitParameter(PRM_ENCODING);
    if (encoding == null) encoding = ENCODING_UTF_8;
  }

  /**
   * checks page encoding and if it's equal to null set utf-8 encoding as default
   *
   * @param req  {@link ServletRequest}
   * @param res  {@link ServletResponse}
   * @param next {@link FilterChain}
   * @throws IOException      in case, when params incorrect
   * @throws ServletException in case, servlet encounters difficulty.
   */
  public void doFilter(ServletRequest req, ServletResponse res, FilterChain next)
  throws IOException, ServletException {
    // Respect the client-specified character encoding
    // (see HTTP specification section 3.4.1)
    if (null == req.getCharacterEncoding()) {
      req.setCharacterEncoding(encoding);
    }

    // Set the default response content type and encoding
    res.setContentType(CONTENT_TYPE);
    res.setCharacterEncoding(ENCODING_UTF_8);

    next.doFilter(req, res);
  }

  public void destroy() {
  }
}
