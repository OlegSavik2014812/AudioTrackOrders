package com.audioord.utils;


import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.Date;
import java.util.Random;

public final class FileUtils {

  private static final Logger LOG = Logger.getLogger(FileUtils.class);

  private FileUtils() {
    super();
  }

  public static File writeFile(FileItem fi, String dirPath) {
    String ext = fi.getName().substring(fi.getName().lastIndexOf(".") + 1, fi.getName().length());
    String name = String.format("%s_%s.%s", new Date().getTime(), new Random().nextInt(99999), ext);
    File f = new File(dirPath, name);
    try {
      if (f.createNewFile()) {
        fi.write(f);
      }
      return f;
    } catch (Exception e) {
      LOG.error(e);
      return null;
    }
  }

  public static boolean deleteFile(File f) {
    return f != null && f.delete();
  }
}
