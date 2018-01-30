package com.audioord.web.http;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Request {

  private final FileItemFactory factory = new DiskFileItemFactory();
  private final ServletFileUpload upload = new ServletFileUpload(factory);
  private HttpServletRequest rawRequest;

  public Request(HttpServletRequest rawRequest) {
    this.rawRequest = rawRequest;
  }

  public HttpServletRequest raw() {
    return rawRequest;
  }

  public boolean hasParameter(String paramName) {
    String value = rawRequest.getParameter(paramName);
    return value != null && !value.isEmpty();
  }

  public boolean hasAllParameters(String... params) {
    for (String param : params) {
      if (hasParameter(param)) {
        continue;
      }
      return false;
    }
    return true;
  }

  public void addAttribute(String name, Object v) {
    Objects.requireNonNull(v, "NULL attribute not allowed");
    rawRequest.setAttribute(name, v);
  }

  public FileItem getFileItem(String name) {
    List<FileItem> fileItems;
    try {
      fileItems = upload.parseRequest(rawRequest);
    } catch (FileUploadException e) {
      fileItems = Collections.emptyList();
    }
    for (FileItem fileItem : fileItems) {
      if (fileItem.getFieldName().equalsIgnoreCase(name)) {
        return fileItem;
      }
    }
    return null;
  }

  public String getParameter(String paramName) {
    return rawRequest.getParameter(paramName);
  }

  public <T> T getAttribute(String attrName, Class<T> type) {
    return type.cast(rawRequest.getAttribute(attrName));
  }

  public void removeAttribute(String attrName){
    rawRequest.removeAttribute(attrName);
  }

  public void setSessionAttribute(String name, Object v) {
    rawRequest.getSession().setAttribute(name, v);
  }

  public <T> T getSessionAttribute(String name, Class<T> type) {
    return type.cast(rawRequest.getSession().getAttribute(name));
  }

  public void removeSessionAttribute(String name) {
    rawRequest.getSession().removeAttribute(name);
  }

  public void invalidateSession() {
    rawRequest.getSession().invalidate();
  }
}
