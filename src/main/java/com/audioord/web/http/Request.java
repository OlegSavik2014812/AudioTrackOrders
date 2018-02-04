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

/**
 * Class describes object, which wraps {@link HttpServletRequest} to custom {@link Request} object
 */
public class Request {

  private final FileItemFactory factory = new DiskFileItemFactory();
  private final ServletFileUpload upload = new ServletFileUpload(factory);
  private HttpServletRequest rawRequest;

  /**
   * @param rawRequest {@link HttpServletRequest}
   */
  public Request(HttpServletRequest rawRequest) {
    this.rawRequest = rawRequest;
  }

  /**
   * @return {@link HttpServletRequest}
   */
  public HttpServletRequest raw() {
    return rawRequest;
  }

  /**
   * checks, if {@link HttpServletRequest} has input param name
   *
   * @param paramName param name
   * @return if has - true , otherwise - false
   */
  public boolean hasParameter(String paramName) {
    String value = rawRequest.getParameter(paramName);
    return value != null && !value.isEmpty();
  }

  /**
   * checks, if {@link HttpServletRequest} has array of string params
   *
   * @param params array of params
   * @return if has - true , otherwise - false
   */
  public boolean hasAllParameters(String... params) {
    for (String param : params) {
      if (hasParameter(param)) {
        continue;
      }
      return false;
    }
    return true;
  }

  /**
   * adds attribute to {@link HttpServletRequest}
   *
   * @param name attribute name
   * @param v    attribute type
   */
  public void addAttribute(String name, Object v) {
    Objects.requireNonNull(v, "NULL attribute not allowed");
    rawRequest.setAttribute(name, v);
  }

  /**
   * returns file item using name
   *
   * @param name param name
   * @return {@link FileItem}
   */
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

  /**
   * @param paramName param name
   * @return object consider to input param name
   */
  public String getParameter(String paramName) {
    return rawRequest.getParameter(paramName);
  }

  /**
   * @param attrName attribute name
   * @param type     class
   * @param <T>      type
   * @return casted object
   */
  public <T> T getAttribute(String attrName, Class<T> type) {
    return type.cast(rawRequest.getAttribute(attrName));
  }

  /**
   * @param attrName attribute name
   */
  public void removeAttribute(String attrName) {
    rawRequest.removeAttribute(attrName);
  }

  /**
   * @param name session attribute name
   * @param v    session attribute type
   */
  public void setSessionAttribute(String name, Object v) {
    rawRequest.getSession().setAttribute(name, v);
  }

  /**
   * @param name session attribute name
   * @param type class
   * @param <T>  session attribute type
   * @return casted object
   */
  public <T> T getSessionAttribute(String name, Class<T> type) {
    return type.cast(rawRequest.getSession().getAttribute(name));
  }

  /**
   * @param name attribute name
   */
  public void removeSessionAttribute(String name) {
    rawRequest.getSession().removeAttribute(name);
  }

  /**
   * invalidates session
   */
  public void invalidateSession() {
    rawRequest.getSession().invalidate();
  }
}
