package com.audioord.web;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.io.Writer;

public class PagingTag extends SimpleTagSupport {

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

  @Override
  public void doTag() throws JspException, IOException {
    super.doTag();
  }
}
