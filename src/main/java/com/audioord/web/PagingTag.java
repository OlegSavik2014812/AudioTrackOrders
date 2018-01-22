package com.audioord.web;

import org.apache.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.io.Writer;

public class PagingTag extends SimpleTagSupport {

  private static final Logger LOG = Logger.getLogger(PagingTag.class);

  private String uri;
  private int currPage;
  private int totalPages;
  private int maxLinks = 10;

  public void setUri(String uri) {
    this.uri = uri;
  }

  public void setCurrPage(int currPage) {
    this.currPage = currPage;
  }

  public void setTotalPages(int totalPages) {
    this.totalPages = totalPages;
  }

  private Writer getWriter() {
    return getJspContext().getOut();
  }

  @Override
  public void doTag() throws JspException, IOException {
    Writer out = getWriter();

    boolean lastPage = currPage == totalPages;
    int pgStart = Math.max(currPage - maxLinks / 2, 1);
    int pgEnd = pgStart + maxLinks;
    if (pgEnd > totalPages + 1) {
      int diff = pgEnd - totalPages;
      pgStart -= diff - 1;
      if (pgStart < 1)
        pgStart = 1;
      pgEnd = totalPages + 1;
    }
    try {
      out.write("<ul class=\"pagination pagination-sm\">");

      if (currPage > 1)
        out.write(constructLink(currPage - 1, "Previous", "page-item"));

      for (int i = pgStart; i < pgEnd; i++) {
        if (i == currPage)
          out.write("<li class=\"page-item" + (lastPage && i == totalPages ? " paginatorLast" : "") + "\">" + currPage + "</li>");
        else
          out.write(constructLink(i));
      }

      if (!lastPage)
        out.write(constructLink(currPage + 1, "Next", "page-item"));

      out.write("</ul>");

    } catch (java.io.IOException ex) {
      throw new JspException("Error in Paginator tag", ex);
    }
  }

    private String constructLink(int page) {
    return constructLink(page, String.valueOf(page), "page-item");
  }

  private String constructLink(int page, String text, String className) {
    StringBuilder link = new StringBuilder("<li");
    if (className != null) {
      link.append(" class=\"");
      link.append(className);
      link.append("\"");
    }
    link.append(">")
    .append("<a href=\"")
    .append(uri.replace("##", String.valueOf(page)))
    .append("\">")
    .append(text)
    .append("</a></li>");
    return link.toString();
  }


}
