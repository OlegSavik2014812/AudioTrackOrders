package com.audioord.web.filter;


import javax.servlet.*;
import java.io.IOException;

public class CharsetFilter implements Filter {
  private static final String PRM_ENCODING = "requestEncoding";
  private static final String ENCODING_UTF_8 = "UTF-8";
  private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
  private String encoding;

  public void init(FilterConfig config) throws ServletException {
    encoding = config.getInitParameter(PRM_ENCODING);
    if (encoding == null) encoding = ENCODING_UTF_8;
  }

  public void doFilter(ServletRequest request, ServletResponse response, FilterChain next)
  throws IOException, ServletException {
    // Respect the client-specified character encoding
    // (see HTTP specification section 3.4.1)
    if (null == request.getCharacterEncoding()) {
      request.setCharacterEncoding(encoding);
    }

    // Set the default response content type and encoding
    response.setContentType(CONTENT_TYPE);
    response.setCharacterEncoding(ENCODING_UTF_8);

    next.doFilter(request, response);
  }

  public void destroy() {
  }
}
