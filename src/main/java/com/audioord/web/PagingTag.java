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

  private boolean isLastPage() {
    return currPage == totalPages;
  }

  private boolean isFirstPage() {
    return currPage == 1;
  }

  @Override
  public void doTag() throws JspException, IOException {
    try {
      if (totalPages > 0) {
        displayTag(getWriter());
      }
    } catch (Exception e) {
      LOG.error("Error processing page tag", e);
    }
  }

  private void displayTag(Writer out) throws Exception {
    out.write("<nav><ul class='pagination pagination-sm'>");
    displayButtonPrevious(out);
    displayPages(out);
    displayButtonNext(out);
    out.write("</nav></ul>");
  }

  private void displayButtonPrevious(Writer out) throws IOException {
    if (isFirstPage()) {
      out.write("<li class='page-item disabled'><a class='page-link'>Previous</a></li>");
    } else {
      out.write("<li class='page-item'><a class='page-link' href='");
      out.write(uri.replace("##", String.valueOf(currPage - 1)));
      out.write("'>Previous</a></li>");
    }
  }

  private void displayPages(Writer out) throws IOException {
    for (int i = 1; i <= totalPages; i++) {
      if (currPage == i) {
        out.write("<li class='page-item active'><a class='page-link disabled'>" + i + "</a></li>");
      } else {
        out.write("<li class='page-item'><a class='page-link' href='");
        out.write(uri.replace("##", String.valueOf(i)));
        out.write("'>" + i + "</a></li>");
      }
    }
  }

  private void displayButtonNext(Writer out) throws IOException {
    if (isLastPage()) {
      out.write("<li class='page-item disabled'><a class='page-link'>Next</a></li>");
    } else {
      out.write("<li class='page-item'><a class='page-link' href='");
      out.write(uri.replace("##", String.valueOf(currPage + 1)));
      out.write("'>Next</a></li>");
    }
  }
}
